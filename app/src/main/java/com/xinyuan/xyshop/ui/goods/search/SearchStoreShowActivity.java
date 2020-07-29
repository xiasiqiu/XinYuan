package com.xinyuan.xyshop.ui.goods.search;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.SearchStoreAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.bean.ClassItemBean;
import com.xinyuan.xyshop.bean.SearchHistoryBean;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.model.SearchStoreModel;
import com.xinyuan.xyshop.mvp.contract.SearchStoreView;
import com.xinyuan.xyshop.mvp.presenter.SearchStorePresenter;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.widget.EditTextWithDel;
import com.xinyuan.xyshop.widget.RecycleViewDivider;
import com.youth.xframe.cache.XCache;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * Created by Administrator on 2017/8/3.
 */

public class SearchStoreShowActivity extends BaseActivity<SearchStorePresenter> implements SearchStoreView, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.loadingView)
    XLoadingView loadingView;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rvStoreList)
    RecyclerView rvStoreList;
    @BindView(R.id.search_btn_back)
    ImageView search_btn_back;
    @BindView(R.id.iv_search_msg)
    BGABadgeImageView iv_search_msg;
    @BindView(R.id.search_et)
    EditTextWithDel search_et;

    @BindView(R.id.rg_good_select)
    RadioGroup rg_good_select;

    @BindView(R.id.rb_comm_sort)
    RadioButton rb_comm;
    @BindView(R.id.rb_sell_sort)
    RadioButton rb_sell_sort;
    @BindView(R.id.rb_filter_sort)
    RadioButton rb_filter_sort;

    @BindView(R.id.flListType)
    FrameLayout btnListType;

    public static PopupWindow popSort;

    private SearchGoodSelectDialog selectDialog;
    private static String keyword;
    private List<ClassItemBean> classList;
    private SearchStoreAdapter adapter;
    private static int page;
    private static int limit = 10;
    private static String sort = "";
    private XCache xCache;  //缓存控件
    public static SearchHistoryBean searchBean;

    @Override
    protected SearchStorePresenter createPresenter() {
        return new SearchStorePresenter(this, this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_search_store;
    }

    @Override
    public void initView() {
        CommUtil.initToolBar(this, search_btn_back, iv_search_msg);
        btnListType.setVisibility(View.GONE);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        this.selectDialog = new SearchGoodSelectDialog(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(1);
        this.rvStoreList.setLayoutManager(layoutManager);
        this.rvStoreList.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL));
        xCache = XCache.get(this);
        searchBean = (SearchHistoryBean) xCache.getAsObject(Constants.SEARCH_HISTORY);
    }

    @Override
    public void initData() {
        this.keyword = getIntent().getStringExtra("KEYWORD");
        if (!XEmptyUtils.isEmpty(keyword)) {
            search_et.setText(keyword);
            page = 1;
            mPresenter.getStore(keyword, sort, page, limit);
        }
    }

    @Override
    public void showStore(SearchStoreModel searchStoreModel) {

        if (XEmptyUtils.isEmpty(adapter)) {
            if (XEmptyUtils.isEmpty(searchStoreModel.getStoreList())) {
                this.adapter = new SearchStoreAdapter(R.layout.item_store_list, searchStoreModel.getStoreList());
                this.adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
                adapter.setOnLoadMoreListener(this, rvStoreList);
                this.rvStoreList.setAdapter(adapter);
                showState(3);
            } else {
                this.adapter = new SearchStoreAdapter(R.layout.item_store_list, searchStoreModel.getStoreList());
                adapter.setOnLoadMoreListener(this, rvStoreList);
                this.rvStoreList.setAdapter(adapter);
                showState(1);
                classList = searchStoreModel.getStoreClassList();
            }
        } else {
            if (XEmptyUtils.isEmpty(searchStoreModel.getStoreList())) {
                if (page == 1) {
                    showState(3);
                } else {
                    adapter.loadMoreEnd();

                }
            } else {
                if (page == 1) {
                    rvStoreList.scrollToPosition(0);
                    adapter.setNewData(searchStoreModel.getStoreList());
                    mSwipeRefreshLayout.setRefreshing(false);
                    adapter.setEnableLoadMore(true);
                    showState(1);
                } else {
                    adapter.addData(searchStoreModel.getStoreList());
                    mSwipeRefreshLayout.setRefreshing(false);
                    adapter.loadMoreComplete();
                    showState(1);
                }
                classList = searchStoreModel.getStoreClassList();

            }
        }


    }

    @Override
    public void initListener() {
        loadingView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XToast.success("重新加载");
                loadingView.showLoading();
            }
        });

        rb_comm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popSort == null) {
                    View viewPopSort = LayoutInflater.from(SearchStoreShowActivity.this).inflate(R.layout.item_search_sort_popwindow, null);
                    popSort = new PopupWindow(viewPopSort, -50, -50, true);
                    popSort.setTouchable(true);
                    popSort.setOutsideTouchable(true);
                    popSort.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
                    viewPopSort.setOnTouchListener(new View.OnTouchListener() {
                        public boolean onTouch(View v, MotionEvent event) {
                            if (popSort != null && popSort.isShowing()) {
                                popSort.dismiss();
                            }
                            return false;
                        }
                    });

                    Button rb_SortDefault = (Button) viewPopSort.findViewById(R.id.rb_SortDefault);    //综合排序
                    Button rb_SortHon = (Button) viewPopSort.findViewById(R.id.rb_SortHon);    //评论数排序
                    Button rb_SortPriceL = (Button) viewPopSort.findViewById(R.id.rb_SortPriceL);    //价格从低到高
                    Button btnSortPriceU = (Button) viewPopSort.findViewById(R.id.btnSortPriceU);    //价格从高到低
                    rb_SortPriceL.setVisibility(View.GONE);
                    btnSortPriceU.setVisibility(View.GONE);
                    rb_SortDefault.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            rb_comm.setText("综合排序");
                            sort = "";
                            page = 1;
                            showState(0);
                            mPresenter.getStore(keyword, sort, page, limit);
                            popSort.dismiss();
                        }
                    });
                    rb_SortHon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            rb_comm.setText("评论数排序");
                            sort = "evaluates";
                            page = 1;
                            showState(0);
                            mPresenter.getStore(keyword, sort, page, limit);
                            popSort.dismiss();
                        }
                    });
                }


                popSort.showAsDropDown(rb_comm);
            }
        });

        rb_filter_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDialog.show();
            }
        });

        rg_good_select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.rb_comm_sort:

                        break;
                    case R.id.rb_sell_sort:
                        sort = "goods_salenum";
                        page = 1;
                        mPresenter.getStore(keyword, sort, page, limit);
                        break;
                    case R.id.rb_filter_sort:

                        break;

                }
            }
        });
    }


    @Override
    public void onRefresh() {
        adapter.setEnableLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                mPresenter.getStore(keyword, sort, page, limit);
            }
        }, 500);
    }

    @Override
    public void onLoadMoreRequested() {
        rvStoreList.postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                mPresenter.getStore(keyword, sort, page, limit);
            }

        }, 500);
    }


    @Override
    public void onResume() {
        if (MyShopApplication.IsNewMsg) {
            iv_search_msg.showCirclePointBadge();
        } else {
            iv_search_msg.hiddenBadge();
        }
        super.onResume();
    }

    @Override
    public LifecycleTransformer<SearchStoreModel> bindLife() {
        return this.<SearchStoreModel>bindUntilEvent(ActivityEvent.DESTROY);
    }

    @Override
    public void showState(int state) {
        switch (state) {
            case 0:
                loadingView.showLoading();
                break;
            case 1:
                loadingView.showContent();
                break;
            case 2:
                loadingView.showError();
                break;
            case 3:
                loadingView.showEmpty();
                break;

        }
    }

    @OnClick(R.id.search_btn_search)
    public void Search() {
        if (!XEmptyUtils.isEmpty(searchBean)) {
            searchBean.getHistoryList().add(search_et.getText().toString().trim());
            searchBean.setHistoryList(CommUtil.distinctList(searchBean.getHistoryList()));//数据去重
            xCache.put(Constants.SEARCH_HISTORY, searchBean, xCache.TIME_DAY);  //存入缓存
        }
        page = 1;
        sort = "";
        keyword = search_et.getText().toString().trim();
        mPresenter.getStore(keyword, sort, page, limit);
        showState(0);
    }

}
