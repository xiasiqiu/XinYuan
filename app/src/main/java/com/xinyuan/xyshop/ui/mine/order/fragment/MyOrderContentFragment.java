package com.xinyuan.xyshop.ui.mine.order.fragment;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.OrderAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.even.OrderPageRefresh;
import com.xinyuan.xyshop.model.OnOrderModel;
import com.xinyuan.xyshop.model.OrderModel;
import com.xinyuan.xyshop.mvp.contract.OrderContentView;
import com.xinyuan.xyshop.mvp.presenter.OrderContentPresenter;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.loadingview.XLoadingView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * Created by fx on 2017/7/31.
 * 实物订单列表fragment
 */

public class MyOrderContentFragment extends BaseFragment<OrderContentPresenter> implements OrderContentView, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
	@BindView(R.id.rv_order)
	RecyclerView rv_order;
	@BindView(R.id.swipeLayout)
	SwipeRefreshLayout mSwipeRefreshLayout;
	@BindView(R.id.loadingView)
	XLoadingView loadingView;

	private String mTitle = "实物订单";//当前fragment名称（订单状态）
	private String mParent = "全部";//上级视图名称（订单类型）
	private OrderAdapter adapter;
	private String ORDER_STATUS;    //订单状态
	private int ORDER_TYPE;      //订单类型
	private int page = 1;
	private int limit = 10;

	public static MyOrderContentFragment getInstance(String title, String parent) {
		MyOrderContentFragment sf = new MyOrderContentFragment();
		sf.mTitle = title;
		sf.mParent = parent;
		return sf;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_order_content;
	}

	@Override
	protected OrderContentPresenter createPresenter() {
		return new OrderContentPresenter(this, getActivity());
	}


	@Override
	public void initView(View rootView) {
		if (!mParent.equals("")) {
			if (mParent.equals("实物订单")) {
				ORDER_TYPE = 1;
				if (mTitle.equals("全部")) {
					ORDER_STATUS = "";
				} else if (mTitle.equals("待付款")) {
					ORDER_STATUS = "1";
				} else if (mTitle.equals("待发货")) {
					ORDER_STATUS = "2";
				} else if (mTitle.equals("待收货")) {
					ORDER_STATUS = "3";
				} else if (mTitle.equals("待评价")) {
					ORDER_STATUS = "4";
				}
			} else if (mParent.equals("虚拟订单")) {
				ORDER_TYPE = 2;
				if (mTitle.equals("全部")) {
					ORDER_STATUS = "";
				} else if (mTitle.equals("待付款")) {
					ORDER_STATUS = "1";
				} else if (mTitle.equals("待使用")) {
					ORDER_STATUS = "2";
				} else if (mTitle.equals("待评价")) {
					ORDER_STATUS = "3";
				}

			}
		}

		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		mSwipeRefreshLayout.setOnRefreshListener(this);
		mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
		layoutManager.setOrientation(1);
		this.rv_order.setLayoutManager(layoutManager);
	}

	@Override
	public void initData() {
		if (ORDER_TYPE == 1) {
			mPresenter.getOrderList(page, limit, ORDER_STATUS);
		} else {
			mPresenter.getonlineOrderList(page, limit, ORDER_STATUS);
		}
	}

	@Override
	public void initListener() {
		loadingView.setOnRetryClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (ORDER_TYPE == 1) {
					mPresenter.getOrderList(page, limit, ORDER_STATUS);
				} else {
					mPresenter.getonlineOrderList(page, limit, ORDER_STATUS);
				}
			}
		});
	}

	@Override
	public void showList(OrderModel orderModel) {
		if (XEmptyUtils.isEmpty(adapter)) {
			if (XEmptyUtils.isEmpty(orderModel.getOrderList())) {
				showState(3);
			} else {
				this.adapter = new OrderAdapter(R.layout.fragment_order_item, orderModel.getOrderList(), ORDER_TYPE);
				this.adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
				this.rv_order.setAdapter(adapter);
				adapter.setOnLoadMoreListener(this, rv_order);
				mSwipeRefreshLayout.setRefreshing(false);
				adapter.setEnableLoadMore(true);
				showState(1);
				page++;
			}
		} else {
			if (XEmptyUtils.isEmpty(orderModel.getOrderList())) {
				if (page == 1) {
					loadingView.showEmpty();
				} else {
					adapter.loadMoreEnd();
				}
			} else {
				if (page == 1) {
					adapter.setNewData(orderModel.getOrderList());
					mSwipeRefreshLayout.setRefreshing(false);
					adapter.setEnableLoadMore(true);

				} else {
					adapter.addData(orderModel.getOrderList());
					mSwipeRefreshLayout.setRefreshing(false);

				}
				page++;
				showState(1);

			}


		}
	}

	@Override
	public void showOnLineList(OnOrderModel orderModel) {
		if (XEmptyUtils.isEmpty(adapter)) {
			if (XEmptyUtils.isEmpty(orderModel.getOrderList())) {
				showState(3);
			} else {
				this.adapter = new OrderAdapter(R.layout.fragment_order_item, orderModel.getOrderList(), ORDER_TYPE);
				this.adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
				this.rv_order.setAdapter(adapter);
				adapter.setOnLoadMoreListener(this, rv_order);
				mSwipeRefreshLayout.setRefreshing(false);
				adapter.setEnableLoadMore(true);
				showState(1);
				page++;
			}
		} else {
			if (XEmptyUtils.isEmpty(orderModel.getOrderList())) {
				if (page == 1) {
					loadingView.showEmpty();
				} else {
					adapter.loadMoreEnd();
				}
			} else {
				if (page == 1) {
					adapter.setNewData(orderModel.getOrderList());
					mSwipeRefreshLayout.setRefreshing(false);
					adapter.setEnableLoadMore(true);

				} else {
					adapter.addData(orderModel.getOrderList());
					mSwipeRefreshLayout.setRefreshing(false);

				}
				page++;
				showState(1);

			}


		}
	}


	@Override
	public LifecycleTransformer<OrderModel> bindLife() {
		return this.<OrderModel>bindUntilEvent(FragmentEvent.DESTROY);

	}

	@Override
	public LifecycleTransformer<OnOrderModel> bindtoLife() {
		return this.<OnOrderModel>bindUntilEvent(FragmentEvent.DESTROY);
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

	@Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
	public void OrderBusEven(OrderPageRefresh event) {
		if (event.isRefresh()) {
			showState(0);
			onRefresh();
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
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				page = 1;
				if (!XEmptyUtils.isEmpty(mPresenter)) {

					if (ORDER_TYPE == 1) {
						mPresenter.getOrderList(page, limit, ORDER_STATUS);
					} else {
						mPresenter.getonlineOrderList(page, limit, ORDER_STATUS);
					}
				}

			}
		}, 500);
	}


	@Override
	public void onLoadMoreRequested() {
		rv_order.postDelayed(new Runnable() {
			@Override
			public void run() {
				if (!XEmptyUtils.isEmpty(mPresenter)) {

					if (ORDER_TYPE == 1) {
						mPresenter.getOrderList(page, limit, ORDER_STATUS);
					} else {
						mPresenter.getonlineOrderList(page, limit, ORDER_STATUS);
					}
				}
			}

		}, 500);
	}


}
