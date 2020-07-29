package com.xinyuan.xyshop.ui.goods.store.fragment;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.CreditGoodsAdapter;
import com.xinyuan.xyshop.adapter.GroupGoodsAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.GoodListItemBean;
import com.xinyuan.xyshop.bean.GroupGoodBean;
import com.xinyuan.xyshop.model.CreditGoodModel;
import com.xinyuan.xyshop.mvp.contract.StoreActivityView;
import com.xinyuan.xyshop.mvp.presenter.StoreActivityPresenter;
import com.xinyuan.xyshop.mvp.presenter.StorePresenter;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by fx on 2017/6/1.
 * 店铺活动fragment
 */

public class StoreActivityFragment extends BaseFragment<StoreActivityPresenter> implements StoreActivityView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
	@BindView(R.id.loadingView)
	XLoadingView loadingView;
	@BindView(R.id.rv_activity)
	RecyclerView rv_activity;
	@BindView(R.id.swipeLayout)
	SwipeRefreshLayout swipeLayout;

	private GroupGoodsAdapter goodsAdapter;

	private int page = 1;
	private static final int limit = 10;

	@Override
	public void initView(View rootView) {
		swipeLayout.setOnRefreshListener(this);
		swipeLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
		GridLayoutManager layoutManager2 = new GridLayoutManager(mContext, 2, 1, false);
		this.rv_activity.setLayoutManager(layoutManager2);
	}

	@Override
	public void initData() {
		mPresenter.getActivityList(page, limit);
		loadingView.setOnRetryClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mPresenter.getActivityList(page, limit);
			}
		});
	}

	@Override
	protected StoreActivityPresenter createPresenter() {
		return new StoreActivityPresenter(getActivity(), this);
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_store_activity;
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
	public LifecycleTransformer<List<GoodListItemBean>> bindLife() {
		return this.<List<GoodListItemBean>>bindUntilEvent(FragmentEvent.DESTROY_VIEW);

	}

	@Override
	public void showActivitys(List<GoodListItemBean> list) {
		if (XEmptyUtils.isEmpty(goodsAdapter)) {
			if (XEmptyUtils.isEmpty(list)) {
				showState(3);
			} else {
				goodsAdapter = new GroupGoodsAdapter(R.layout.activity_group_item_grid, list);
				goodsAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
				rv_activity.setAdapter(goodsAdapter);
				goodsAdapter.setOnLoadMoreListener(this);
				showState(1);
				page++;
			}

		} else {
			if (XEmptyUtils.isEmpty(list)) {
				if (page == 1) {
					showState(3);
				} else {
					goodsAdapter.loadMoreEnd();

				}
			} else {
				if (page == 1) {
					goodsAdapter.setNewData(list);
					swipeLayout.setRefreshing(false);
					goodsAdapter.notifyDataSetChanged();
					goodsAdapter.setEnableLoadMore(true);
				} else {
					goodsAdapter.addData(list);
					goodsAdapter.loadMoreComplete();
					swipeLayout.setRefreshing(false);
					goodsAdapter.notifyDataSetChanged();

				}
				page++;

			}

		}
	}

	@Override
	public void onRefresh() {
		goodsAdapter.setEnableLoadMore(false);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				page = 1;
				mPresenter.getActivityList(page, limit);
			}
		}, 500);
	}

	@Override
	public void onLoadMoreRequested() {
		rv_activity.postDelayed(new Runnable() {
			@Override
			public void run() {
				mPresenter.getActivityList(page, limit);
			}

		}, 500);
	}
}
