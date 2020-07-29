package com.xinyuan.xyshop.ui.mine.info;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.FavAdapters;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.bean.FavGoodBean;
import com.xinyuan.xyshop.model.FavGoodModel;
import com.xinyuan.xyshop.model.HomeModel;
import com.xinyuan.xyshop.mvp.contract.FavGoodView;
import com.xinyuan.xyshop.mvp.presenter.FavGoodPresenter;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.RecycleViewDivider;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fx on 2017/7/31.
 * 收藏商品列表Fragment
 */

public class FavGoodFragment extends BaseFragment<FavGoodPresenter> implements FavGoodView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.recycler_view)
    RecyclerView rv_fav;
    @BindView(R.id.tv_header_center)
    TextView tv_header_center;
    @BindView(R.id.tv_header_right)
    TextView tv_header_right;
    @BindView(R.id.toolbar_tv)
    Toolbar msg_toolbar;
    @BindView(R.id.lodingView)
    XLoadingView loadingView;
    @BindView(R.id.iv_header_left)
    ImageView iv_header_left;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rl_edit)
    RelativeLayout rl_edit;


    private FavAdapters adapter;
    private List<FavGoodBean> storeList;
    private List<String> idList = new ArrayList<>();
    private boolean falg = true;
    private int page = 1;
    private static final int limit = 10;

    public static FavGoodFragment newInstance() {
        FavGoodFragment fragment = new FavGoodFragment();
        return fragment;
    }


    @Override
    public void initView(View rootView) {
        tv_header_right.setText("编辑");
        tv_header_center.setText("收藏夹");
        SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
        SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
        CommUtil.initToolBar(getActivity(), iv_header_left, null);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(1);
        rv_fav.setLayoutManager(new LinearLayoutManager(mContext));// 布局管理器。
        rv_fav.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.VERTICAL));


    }

    @Override
    public void initData() {
        mPresenter.getFavGoods(page, limit);
    }

    @Override
    protected FavGoodPresenter createPresenter() {
        return new FavGoodPresenter(mContext, this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_favorite;

    }

    @Override
    public void showList(FavGoodModel favGoodModel) {
        if (XEmptyUtils.isEmpty(adapter)) {
            if (XEmptyUtils.isEmpty(favGoodModel)) {
                showState(3);
            } else {
                this.storeList = favGoodModel.getGoodsList();
                adapter = new FavAdapters(R.layout.activity_favorite_item, storeList, false);
                rv_fav.setAdapter(adapter);
                adapter.setOnLoadMoreListener(this);
                adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
                adapter.setAutoLoadMoreSize(3);
                showState(1);
                page++;
            }

        } else {
            if (XEmptyUtils.isEmpty(favGoodModel)) {
                if (page == 1) {
                    loadingView.showEmpty();
                } else {
                    adapter.loadMoreEnd();

                }
            } else {
                if (page == 1) {
                    adapter.setNewData(favGoodModel.getGoodsList());
                    mSwipeRefreshLayout.setRefreshing(false);
                    adapter.setEnableLoadMore(true);
                } else {
                    adapter.addData(favGoodModel.getGoodsList());
                    mSwipeRefreshLayout.setRefreshing(false);
                    adapter.loadMoreComplete();
                }
                page++;
            }

        }


    }

    @Override
    public void detelteRes(boolean res) {
        XToast.info("删除成功");
        adapter.choeseList.clear();
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

        }
    }

    @OnClick({R.id.bt_detelte, R.id.bt_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_detelte:
                if (!XEmptyUtils.isEmpty(adapter.choeseList)) {
                    Iterator iter = adapter.choeseList.entrySet().iterator();
                    while (iter.hasNext()) {
                        Map.Entry entry = (Map.Entry) iter.next();
                        idList.add((String) entry.getValue().toString().trim());
                        adapter.remove((Integer) entry.getKey());
                    }
                    adapter.notifyDataSetChanged();
                    mPresenter.deteleFavGood(idList);
                } else {
                    XToast.error("请选择商品");
                }

                break;
            case R.id.bt_share:
                XToast.info("分享了" + adapter.choeseList.toString());
                break;
        }
    }

    @Override
    public void onRefresh() {
        adapter.setEnableLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                mPresenter.getFavGoods(page, limit);
            }
        }, 500);
    }

    @Override
    public void initListener() {
        loadingView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getFavGoods(page, limit);
            }
        });
        tv_header_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (falg) {
                    adapter.isDetele = true;
                    falg = false;
                    rl_edit.setVisibility(View.VISIBLE);
                    adapter.setNewData(storeList);
                    tv_header_right.setText("完成");
                } else {
                    adapter.isDetele = false;
                    falg = true;
                    rl_edit.setVisibility(View.GONE);
                    tv_header_right.setText("编辑");
                    adapter.setNewData(adapter.getData());
                }
            }
        });
    }

    @Override
    public void onLoadMoreRequested() {
        rv_fav.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.getFavGoods(page, limit);
            }

        }, 500);
    }


    @Override
    public LifecycleTransformer<HomeModel> bindLife() {
        return this.<HomeModel>bindUntilEvent(FragmentEvent.DESTROY_VIEW);
    }
}
