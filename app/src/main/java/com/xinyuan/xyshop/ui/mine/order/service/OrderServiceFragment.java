package com.xinyuan.xyshop.ui.mine.order.service;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.OrderAdapter;
import com.xinyuan.xyshop.adapter.ServiceAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.OrderGoodBean;
import com.xinyuan.xyshop.bean.OrderServiceStoreInfoBean;
import com.xinyuan.xyshop.bean.ServiceGoodBean;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.even.OrderBusEven;
import com.xinyuan.xyshop.even.OrderPageRefresh;
import com.xinyuan.xyshop.even.ServiceBusEven;
import com.xinyuan.xyshop.model.ServiceModel;
import com.xinyuan.xyshop.mvp.contract.OrderServiceView;
import com.xinyuan.xyshop.mvp.presenter.OrderServicePresenter;
import com.xinyuan.xyshop.ui.mine.order.fragment.LogisticFragment;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.loadingview.XLoadingView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/7/1.
 * 售后订单列表
 */

public class OrderServiceFragment extends BaseFragment<OrderServicePresenter> implements OrderServiceView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.rv_service)
    RecyclerView rv_service;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.loadingView)
    XLoadingView loadingView;


    @BindView(R.id.tv_header_center)
    TextView tv_header_center;
    @BindView(R.id.iv_header_left)
    ImageView iv_header_left;
    @BindView(R.id.iv_header_right)
    ImageView iv_header_right;


    @BindView(R.id.toolbar_iv)
    Toolbar msg_toolbar;
    private ServiceAdapter adapter;

    private int page = 1;
    private final static int limit = 10;

    public static OrderServiceFragment newInstance() {
        OrderServiceFragment fragment = new OrderServiceFragment();
        return fragment;
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(1);


    }

    @Override
    public void initView(View rootView) {
        SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
        SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
        tv_header_center.setText("退款/售后");
        CommUtil.initToolBar(getActivity(), iv_header_left, iv_header_right);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        layoutManager.setOrientation(1);
        this.rv_service.setLayoutManager(layoutManager);
    }


    @Override
    public void initData() {
        mPresenter.getServiceList(page);
    }

    @Override
    public void initListener() {
        loadingView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getServiceList(page);
            }
        });
    }

    @Override
    protected OrderServicePresenter createPresenter() {
        return new OrderServicePresenter(getActivity(), this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_orderservice;
    }


    @Override
    public void showServiceList(ServiceModel model) {
        if (XEmptyUtils.isEmpty(adapter)) {
            if (XEmptyUtils.isEmpty(model.getOrderList())) {
                showState(3);
            } else {
                this.adapter = new ServiceAdapter(R.layout.fragment_orderservice_item, model.getOrderList());
                this.adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
                this.rv_service.setAdapter(adapter);
                adapter.setOnLoadMoreListener(this, rv_service);
                mSwipeRefreshLayout.setRefreshing(false);
                adapter.setEnableLoadMore(true);
                showState(1);
                page++;
            }
        } else {
            if (XEmptyUtils.isEmpty(model.getOrderList())) {
                if (page == 1) {
                    loadingView.showEmpty();
                } else {
                    adapter.loadMoreEnd();
                }
            } else {
                if (page == 1) {
                    adapter.setNewData(model.getOrderList());
                    mSwipeRefreshLayout.setRefreshing(false);
                    adapter.setEnableLoadMore(true);
                    loadingView.showContent();
                } else {
                    adapter.addData(model.getOrderList());
                    mSwipeRefreshLayout.setRefreshing(false);
                    adapter.loadMoreComplete();
                    loadingView.showContent();
                }
                page++;
            }


        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ServiceBusEven(ServiceBusEven event) {
        if (event.getFlag().equals(ServiceBusEven.ToServiceGoodDetail)) {
            start(ServiceGoodsFragment.newInstance((OrderServiceStoreInfoBean) event.getObj()));
        } else if (event.getFlag().equals(ServiceBusEven.ToServiceMoneyDetail)) {
            start(ServiceMoneyDetailFragment.newInstance((OrderServiceStoreInfoBean) event.getObj()));
        }
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

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onRefresh() {
        adapter.setEnableLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                if (!XEmptyUtils.isEmpty(mPresenter)) {
                    mPresenter.getServiceList(page);
                }

            }
        }, 500);
    }

    @Override
    public void onLoadMoreRequested() {
        rv_service.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!XEmptyUtils.isEmpty(mPresenter)) {
                    mPresenter.getServiceList(page);

                }
            }

        }, 500);
    }
}