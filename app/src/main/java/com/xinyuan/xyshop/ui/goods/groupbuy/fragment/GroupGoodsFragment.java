package com.xinyuan.xyshop.ui.goods.groupbuy.fragment;

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
import com.xinyuan.xyshop.adapter.GroupGoodsAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.model.GoodListModel;
import com.xinyuan.xyshop.mvp.contract.GroupGoodView;
import com.xinyuan.xyshop.mvp.presenter.GroupGoodPresenter;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.HashMap;

import butterknife.BindView;

/**
 * Created by fx on 2017/6/21.
 * 团购商城列表页
 */

public class GroupGoodsFragment extends BaseFragment<GroupGoodPresenter> implements GroupGoodView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
	@BindView(R.id.swipeLayout)
	SwipeRefreshLayout swipeLayout;
	@BindView(R.id.rv_group)
	RecyclerView rv_group;
	@BindView(R.id.loadingView)
	XLoadingView loadingView;

	private GroupGoodsAdapter groupGoodsAdapter;
	private int page;
	private static final int limit = 10;
	private String type;

	public static GroupGoodsFragment getInstance(String type) {
		GroupGoodsFragment sf = new GroupGoodsFragment();
		sf.type = type;
		return sf;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_group_buy_goods;
	}

	@Override
	public void initView(View rootView) {
		swipeLayout.setOnRefreshListener(this);
		swipeLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
		GridLayoutManager layoutManager2 = new GridLayoutManager(mContext, 2, 1, false);
		this.rv_group.setLayoutManager(layoutManager2);
	}

	@Override
	public void initData() {
		mPresenter.getGoodList(type, page, limit);
	}

	@Override
	protected GroupGoodPresenter createPresenter() {
		return new GroupGoodPresenter(getActivity(), this);
	}


	@Override
	public void showGoodList(GoodListModel goodList) {
		if (XEmptyUtils.isEmpty(groupGoodsAdapter)) {
			if (XEmptyUtils.isEmpty(goodList)) {
				showState(3);
			} else {
				groupGoodsAdapter = new GroupGoodsAdapter(R.layout.item_good_group, goodList.getGoodsList());
				groupGoodsAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
				rv_group.setAdapter(groupGoodsAdapter);
				groupGoodsAdapter.setOnLoadMoreListener(this);
				showState(1);
				page++;
				groupGoodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
					@Override
					public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
						HashMap<String, String> params;
						params = new HashMap();
						params.put(Constants.GOODID, groupGoodsAdapter.getData().get(position).getGoodsId());
						params.put(Constants.GOODTYPE, groupGoodsAdapter.getData().get(position).getGoodsType());
						CommUtil.gotoActivity(mContext, GoodDetailActivity.class, false, params);
					}
				});
			}

		} else {
			if (XEmptyUtils.isEmpty(goodList)) {
				if (page == 1) {
					showState(3);
				} else {
					groupGoodsAdapter.loadMoreEnd();

				}
			} else {
				if (page == 1) {
					groupGoodsAdapter.setNewData(goodList.getGoodsList());
					swipeLayout.setRefreshing(false);
					groupGoodsAdapter.notifyDataSetChanged();
					groupGoodsAdapter.setEnableLoadMore(true);
				} else {
					groupGoodsAdapter.addData(goodList.getGoodsList());
					groupGoodsAdapter.loadMoreComplete();
					swipeLayout.setRefreshing(false);
					groupGoodsAdapter.notifyDataSetChanged();

				}
				page++;

			}

		}

	}

	public RecyclerView getRvList() {
		return rv_group;
	}

	@Override
	public void onRefresh() {
		groupGoodsAdapter.setEnableLoadMore(false);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				page = 1;
				mPresenter.getGoodList(type, page, limit);
			}
		}, 500);
	}

	@Override
	public void onLoadMoreRequested() {
		rv_group.postDelayed(new Runnable() {
			@Override
			public void run() {
				if (!XEmptyUtils.isEmpty(mPresenter)) {
					mPresenter.getGoodList(type, page, limit);

				}
			}

		}, 500);
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


	public LifecycleTransformer<GoodListModel> bindLife() {
		return this.<GoodListModel>bindUntilEvent(FragmentEvent.DESTROY_VIEW);
	}
}
