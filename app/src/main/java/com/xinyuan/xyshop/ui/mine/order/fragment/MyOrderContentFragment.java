package com.xinyuan.xyshop.ui.mine.order.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.OrderAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.model.OrderModel;
import com.xinyuan.xyshop.mvp.contract.OrderListContract;
import com.xinyuan.xyshop.mvp.presenter.OrderListPresenterImpl;
import com.xinyuan.xyshop.ui.goods.SearchGoodsShowActivity;
import com.youth.xframe.adapter.XRecyclerViewAdapter;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/16.
 */

public class MyOrderContentFragment extends BaseFragment implements OrderListContract.OrderListView, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
	private String mTitle;
	@BindView(R.id.rv_order)
	RecyclerView rv_order;
	private String mParent;
	private OrderAdapter adapter;
	private static int ORDER_STATUS = 0;
	private static int ORDER_Type = 0;
	private static boolean GETAll = false;

	OrderListContract.OrderListPresenter presenter;

	public static MyOrderContentFragment getInstance(String title, String parent) {
		MyOrderContentFragment sf = new MyOrderContentFragment();
		sf.mTitle = title;
		sf.mParent = parent;
		return sf;
	}


	@Override
	public int getLayoutId() {
		return R.layout.fragment_order_content;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		new OrderListPresenterImpl(this);

	}

	@Override
	public void initView() {
		XLog.v("ContentFragment:" + mParent + ":" + mTitle);
		if (!mParent.equals("")) {

			if (mParent.equals("实物订单")) {
				ORDER_Type = 0;

				if (mTitle.equals("全部")) {
					GETAll = true;
				} else if (mTitle.equals("待付款")) {
					ORDER_STATUS = 0;
				} else if (mTitle.equals("待发货")) {
					ORDER_STATUS = 1;
				} else if (mTitle.equals("待收货")) {
					ORDER_STATUS = 2;
				} else if (mTitle.equals("待评价")) {
					ORDER_STATUS = 3;
				}
			} else if (mParent.equals("虚拟订单")) {
				ORDER_Type = 1;
				if (mTitle.equals("全部")) {
					GETAll = true;
				} else if (mTitle.equals("待付款")) {
					ORDER_STATUS = 0;
				} else if (mTitle.equals("待使用")) {
					ORDER_STATUS = 1;
				} else if (mTitle.equals("待评价")) {
					ORDER_STATUS = 2;
				}

			}
		}
		presenter.initData(ORDER_Type, ORDER_STATUS, GETAll);
		GETAll = false;
		ORDER_Type = 0;
		ORDER_STATUS = 0;
	}

	@BindView(R.id.swipeLayout)
	SwipeRefreshLayout mSwipeRefreshLayout;

	@Override
	public void showView(List<OrderModel.OrderBean> orderList) {

		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		mSwipeRefreshLayout.setOnRefreshListener(this);
		mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
		layoutManager.setOrientation(1);
		this.rv_order.setLayoutManager(layoutManager);
		this.adapter = new OrderAdapter(R.layout.fragment_order_item, orderList);
		this.adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
		this.rv_order.setAdapter(adapter);

		adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
			@Override
			public void onLoadMoreRequested() {

				rv_order.postDelayed(new Runnable() {
					@Override
					public void run() {
						adapter.loadMoreEnd(false);
					}

				}, 1000);
			}
		}, rv_order);
		XLog.v("加载数据"+mTitle);
		mSwipeRefreshLayout.setRefreshing(false);
		adapter.setEnableLoadMore(true);
		loadingView.showContent();

	}


	@Override
	public void setPresenter(OrderListContract.OrderListPresenter presenter) {
		this.presenter = presenter;
	}

	@BindView(R.id.loadingView)
	XLoadingView loadingView;

	@Override
	public void showState(int Sate) {
		switch (Sate) {
			case 0:
				loadingView.showLoading();
				break;
			case 1:
				loadingView.showContent();
				break;
		}
	}


	@Override
	public void onLoadMoreRequested() {

	}

	private int pageEntity = 1;

	@Override
	public void onRefresh() {
		XLog.v("下拉刷新啦");
		adapter.setEnableLoadMore(false);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				initView();
			}
		}, 1000);
	}
}