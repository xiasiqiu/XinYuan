package com.xinyuan.xyshop.ui.goods.search;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.SearchGoodListAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.bean.GoodBradnBean;
import com.xinyuan.xyshop.bean.GoodListItemBean;
import com.xinyuan.xyshop.bean.GoodPropertyBean;
import com.xinyuan.xyshop.bean.SearchHistoryBean;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.even.SearchBusBean;
import com.xinyuan.xyshop.even.SearchEven;
import com.xinyuan.xyshop.model.SearchGoodModel;
import com.xinyuan.xyshop.mvp.contract.SearchGoodView;
import com.xinyuan.xyshop.mvp.presenter.SearchGoodPresenter;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.widget.EditTextWithDel;
import com.youth.xframe.cache.XCache;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * Created by fx on 2017/7/31.
 */

public class SearchGoodShowActivity extends BaseActivity<SearchGoodPresenter> implements SearchGoodView, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.rvGoodList)
    RecyclerView rvGoods;  //商品列表View

    @BindView(R.id.btnListType)
    ImageView btnListType; //列表表格切换View
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

    @BindView(R.id.search_loadingView)
    XLoadingView xLoadingView;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.search_toolbar)
    Toolbar search_toolbar;
    @BindView(R.id.search_btn_back)
    ImageView iv_header_left;
    @BindView(R.id.iv_search_msg)
    BGABadgeImageView iv_search_msg;

    private SearchGoodSelectDialog selectDialog;
    private LinearLayoutManager manager;

    private int currentItemPosition = 0;
    private static SearchGoodModel goodses;
    private SearchGoodListAdapter adapter;
    private static List<GoodPropertyBean> propertyList;
    private static List<GoodBradnBean> bradnList;
    public static PopupWindow popSort;

    private String keyword;
    private boolean isList = false;
    private int page = 1;
    private int pageSize = 6;

    private List<String> goodsBrandName;
    private List<String> goodsBrandId;
    private String sort = "";
    private String xyself = "no";
    private String price = "";
    private String property = "";
    private XCache xCache;  //缓存控件
    public static SearchHistoryBean searchBean;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_search_goodshow;

    }

    @Override
    protected SearchGoodPresenter createPresenter() {
        return new SearchGoodPresenter(this);
    }

    @Override
    public void initView() {
        CommUtil.initToolBar(this, iv_header_left, iv_search_msg);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        this.selectDialog = new SearchGoodSelectDialog(SearchGoodShowActivity.this);

    }

    @Override
    public void initData() {
        this.keyword = getIntent().getStringExtra(Constants.KEYWORD);
        xCache = XCache.get(this);
        searchBean = (SearchHistoryBean) xCache.getAsObject(Constants.SEARCH_HISTORY);

        if (!XEmptyUtils.isEmpty(keyword)) {
            search_et.setText(keyword);
            page = 1;
            mPresenter.getSearchGoods(keyword, sort, page, pageSize, price, goodsBrandId, goodsBrandName, xyself, property);
        }


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setBrandPropery(SearchEven searchEven) {

        if (searchEven.getFlag().equals(SearchEven.SelectValue)) {

            SearchBusBean busBean = (SearchBusBean) searchEven.getObj();

            goodsBrandId = new ArrayList<>();
            goodsBrandName = new ArrayList<>();
            property = "";
            if (!XEmptyUtils.isEmpty(busBean.getSelectedBrandList())) {
                for (GoodBradnBean bradnBean : busBean.getSelectedBrandList()) {
                    goodsBrandId.add(String.valueOf(bradnBean.getGoodsBrandsId()));
                    goodsBrandName.add(bradnBean.getGoodsBrandsName());
                }

            }
            if (!XEmptyUtils.isEmpty(busBean.getProList())) {
                String str = busBean.getProList().toString();
                property = str.substring(1, str.length() - 1);
            }
            sort = "";
            page = 1;
            showState(0);
            mPresenter.getSearchGoods(keyword, sort, page, pageSize, busBean.getPrice(), goodsBrandId, goodsBrandName, busBean.getXyself(), property);
            rb_filter_sort.setChecked(false);
            rb_comm.setChecked(true);


        } else if (searchEven.getFlag().equals(SearchEven.EmptSelect)) {
            rb_filter_sort.setChecked(false);
            rb_comm.setChecked(true);
        }


    }


    private void showListView(List<GoodListItemBean> GoodListItemBeans) {
        if (this.isList) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(1);
            this.rvGoods.setLayoutManager(layoutManager);
            this.manager = layoutManager;
            this.adapter = new SearchGoodListAdapter(R.layout.item_good_list, GoodListItemBeans, isList);
            this.adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
            adapter.setOnLoadMoreListener(this, rvGoods);
            this.rvGoods.setAdapter(adapter);
        } else {
            GridLayoutManager layoutManager2 = new GridLayoutManager(this, 2, 1, false);
            this.rvGoods.setLayoutManager(layoutManager2);
            this.manager = layoutManager2;
            this.adapter = new SearchGoodListAdapter(R.layout.item_good_grid, GoodListItemBeans, isList);
            adapter.setOnLoadMoreListener(this, rvGoods);
            this.rvGoods.setAdapter(adapter);
        }
    }


    @Override
    public void showGoods(SearchGoodModel searchModel) {
        if (XEmptyUtils.isEmpty(adapter)) {
            if (XEmptyUtils.isEmpty(searchModel.getGoodsList())) {
                showState(3);
            } else {
                goodses = searchModel;
                showListView(searchModel.getGoodsList());
                propertyList = searchModel.getGoodsPropertyList();
                bradnList = searchModel.getGoodsBrandsList();
                page++;
                xLoadingView.showContent();
            }


        } else {
            if (XEmptyUtils.isEmpty(searchModel.getGoodsList())) {
                if (page == 1) {
                    showState(3);
                } else {
                    adapter.loadMoreEnd();
                }

            } else {
                goodses = searchModel;
                if (page == 1) {
                    rvGoods.scrollToPosition(0);
                    adapter.setNewData(searchModel.getGoodsList());
                    mSwipeRefreshLayout.setRefreshing(false);
                    adapter.setEnableLoadMore(true);
                    showState(1);
                } else {
                    adapter.addData(searchModel.getGoodsList());
                    mSwipeRefreshLayout.setRefreshing(false);
                    adapter.loadMoreComplete();
                    showState(1);

                }
                propertyList = searchModel.getGoodsPropertyList();
                bradnList = searchModel.getGoodsBrandsList();
                page++;
            }
        }
    }


    public static List<GoodPropertyBean> getProperty() {
        return propertyList;
    }

    public static List<GoodBradnBean> getBrand() {
        return bradnList;
    }


    @Override
    public void initListener() {
        xLoadingView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!XEmptyUtils.isEmpty(keyword)) {
                    search_et.setText(keyword);
                    page = 1;
                    mPresenter.getSearchGoods(keyword, sort, page, pageSize, price, goodsBrandId, goodsBrandName, xyself, property);
                }
            }
        });
        rb_comm.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                if (popSort == null) {
                    View viewPopSort = LayoutInflater.from(SearchGoodShowActivity.this).inflate(R.layout.item_search_sort_popwindow, null);
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


                    rb_SortDefault.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            rb_comm.setText("综合排序");
                            sort = "";
                            page = 1;
                            showState(0);
                            mPresenter.getSearchGoods(keyword, sort, page, pageSize, price, goodsBrandId, goodsBrandName, xyself, property);
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
                            mPresenter.getSearchGoods(keyword, sort, page, pageSize, price, goodsBrandId, goodsBrandName, xyself, property);
                            popSort.dismiss();
                        }
                    });
                    rb_SortPriceL.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            rb_comm.setText("价格从低到高");
                            sort = "upPrice";
                            page = 1;
                            showState(0);

                            mPresenter.getSearchGoods(keyword, sort, page, pageSize, price, goodsBrandId, goodsBrandName, xyself, property);
                            popSort.dismiss();
                        }
                    });
                    btnSortPriceU.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            rb_comm.setText("价格从高到低");
                            sort = "downPrice";
                            page = 1;
                            showState(0);
                            mPresenter.getSearchGoods(keyword, sort, page, pageSize, price, goodsBrandId, goodsBrandName, xyself, property);
                            popSort.dismiss();
                        }
                    });

                }
                popSort.showAsDropDown(rb_comm);
            }
        });


        rg_good_select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()

        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.rb_comm_sort:
                        break;
                    case R.id.rb_sell_sort:
                        sort = "goods_salenum";
                        page = 1;
                        mPresenter.getSearchGoods(keyword, sort, page, pageSize, price, goodsBrandId, goodsBrandName, xyself, property);
                        break;
                    case R.id.rb_filter_sort:
                        selectDialog.show();
                        break;

                }
            }
        });
    }

    @Override
    public LifecycleTransformer<SearchGoodModel> bindLife() {
        return this.<SearchGoodModel>bindUntilEvent(ActivityEvent.DESTROY);
    }

    @OnClick({R.id.btnListType, R.id.search_btn_search})
    public void onClick(View view) {
        boolean z = true;
        switch (view.getId()) {
            case R.id.btnListType:
                this.btnListType.setSelected(!this.btnListType.isSelected());
                if (!XEmptyUtils.isEmpty(manager)) {
                    if (this.isList) {
                        z = false;
                    }
                    this.isList = z;
                    page = 1;
                    this.currentItemPosition = this.manager.findFirstVisibleItemPosition();
                    showListView(goodses.getGoodsList());
                    this.rvGoods.smoothScrollToPosition(this.currentItemPosition);
                    this.adapter.notifyDataSetChanged();
                }

                break;
            case R.id.search_btn_search:
                page = 1;
                sort = "";
                if (!XEmptyUtils.isEmpty(searchBean)) {
                    searchBean.getHistoryList().add(search_et.getText().toString().trim());
                    searchBean.setHistoryList(CommUtil.distinctList(searchBean.getHistoryList()));//数据去重
                    xCache.put(Constants.SEARCH_HISTORY, searchBean, xCache.TIME_DAY);  //存入缓存
                }
                keyword = search_et.getText().toString().trim();
                mPresenter.getSearchGoods(keyword, sort, page, pageSize, price, goodsBrandId, goodsBrandName, xyself, property);
                showState(0);
                break;
        }
    }


    @Override
    public void showState(int state) {
        switch (state) {
            case 0:
                xLoadingView.showLoading();
                break;
            case 1:
                xLoadingView.showContent();
                break;
            case 2:
                xLoadingView.showError();
                break;
            case 3:
                xLoadingView.showEmpty();
                break;

        }
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
    protected void onStart() {
        super.onStart();
        registerEventBus(SearchGoodShowActivity.this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterEventBus(SearchGoodShowActivity.this);
    }

    @Override
    public void onRefresh() {
        adapter.setEnableLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                mPresenter.getSearchGoods(keyword, sort, page, pageSize, price, goodsBrandId, goodsBrandName, xyself, property);
            }
        }, 500);

    }

    @Override
    public void onLoadMoreRequested() {
        rvGoods.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.getSearchGoods(keyword, sort, page, pageSize, price, goodsBrandId, goodsBrandName, xyself, property);
            }

        }, 500);
    }
}
