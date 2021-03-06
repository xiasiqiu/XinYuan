package com.xinyuan.xyshop.ui.goods.store.fragment;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.GoodsGridAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.bean.GoodListItemBean;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.mvp.contract.StoreGoodView;
import com.xinyuan.xyshop.mvp.presenter.StoreGoodPresenter;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;
import com.xinyuan.xyshop.ui.goods.store.StoreActivity;
import com.xinyuan.xyshop.ui.goods.store.StoreFragment;
import com.xinyuan.xyshop.util.CommUtil;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by fx on 2017/6/1.
 * 店铺全部商品列表fragment
 */

public class StoreAllGoodFragment extends BaseFragment<StoreGoodPresenter> implements StoreGoodView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
	@BindView(R.id.rvGoodList)
	RecyclerView rvGoods;  //商品列表View
	@BindView(R.id.search_loadingView)
	XLoadingView lodingView;
	@BindView(R.id.swipeLayout)
	SwipeRefreshLayout mSwipeRefreshLayout;

	private GoodsGridAdapter goodsAdapter;
	private int page = 1;
	private static int limit = 10;
	private int type = 0;

	@Override
	public void initView(View rootView) {
		GridLayoutManager layoutManager = new GridLayoutManager(mContext, 2);
		this.rvGoods.setLayoutManager(layoutManager);
		mSwipeRefreshLayout.setOnRefreshListener(this);
		mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
	}

	@Override
	public void initData() {
		mPresenter.getGoods(StoreFragment.storeId, type, page, limit);
		lodingView.setOnRetryClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				lodingView.showLoading();
				mPresenter.getGoods(StoreFragment.storeId, type, page, limit);

			}
		});
	}


	@Override
	public void showGoods(final List<GoodListItemBean> list) {
		if (XEmptyUtils.isEmpty(goodsAdapter)) {
			if (XEmptyUtils.isEmpty(list)) {
				showState(0);
			} else {
				this.goodsAdapter = new GoodsGridAdapter(R.layout.item_good_grid, list);
				this.rvGoods.setAdapter(goodsAdapter);
				goodsAdapter.setOnLoadMoreListener(this);
				goodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
					@Override
					public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
						Map<String, String> params = new HashMap();
						params.put(Constants.GOODID, goodsAdapter.getData().get(position).getGoodsId());
						params.put(Constants.GOODTYPE, goodsAdapter.getData().get(position).getGoodsType());
						CommUtil.gotoActivity(getActivity(), GoodDetailActivity.class, false, params);
					}
				});
				showState(1);
				page++;
			}
		} else {
			if (XEmptyUtils.isEmpty(list)) {
				if (page == 1) {
					showState(3);
				} else {
					showState(1);
					goodsAdapter.loadMoreEnd();

				}

			} else {
				if (page == 1) {
					goodsAdapter.setNewData(list);
					mSwipeRefreshLayout.setRefreshing(false);
					goodsAdapter.notifyDataSetChanged();
					goodsAdapter.setEnableLoadMore(true);
					showState(1);
				} else {
					goodsAdapter.addData(list);
					mSwipeRefreshLayout.setRefreshing(false);
					goodsAdapter.notifyDataSetChanged();
					goodsAdapter.loadMoreComplete();
					showState(1);
				}
				page++;

			}


		}
	}

	@Override
	protected StoreGoodPresenter createPresenter() {
		return new StoreGoodPresenter(getActivity(), this);
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_store_all;
	}


	@Override
	public void showState(int state) {
		switch (state) {
			case 0:
				lodingView.showLoading();
				break;
			case 1:
				lodingView.showContent();
				break;
			case 2:
				lodingView.showError();
				break;
			case 3:
				lodingView.showEmpty();
				break;

		}
	}

	@Override
	public void onRefresh() {
		goodsAdapter.setEnableLoadMore(false);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				page = 1;
				mPresenter.getGoods(StoreFragment.storeId, type, page, limit);
			}
		}, 500);
	}

	@Override
	public void onLoadMoreRequested() {
		rvGoods.postDelayed(new Runnable() {
			@Override
			public void run() {
				mPresenter.getGoods(StoreFragment.storeId, type, page, limit);
			}

		}, 500);


	}

	@Override
	public void onDestroyView() {
		page = 1;
		super.onDestroyView();
	}

}
