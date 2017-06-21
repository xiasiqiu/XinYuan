package com.xinyuan.xyshop.ui.goods.store.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.SearchGoodListAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.entity.PageEntity;
import com.xinyuan.xyshop.entity.SelectFilterTest;
import com.xinyuan.xyshop.mvp.contract.GoodSearchShowContract;
import com.xinyuan.xyshop.mvp.presenter.SearchGoodsShowPresenterImpl;
import com.xinyuan.xyshop.ui.goods.RightSearchPly;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailsActivity;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/1.
 */

public class StoreAllGoodFragment extends BaseFragment implements GoodSearchShowContract.GoodSearchShowView, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
	@BindView(R.id.rvGoodList)
	RecyclerView rvGoods;  //商品列表View

	@BindView(R.id.btnListType)
	ImageView btnListType; //列表表格切换View

	@BindView(R.id.btnSort)
	TextView btnSort;   //筛选_综合_TextView
	@BindView(R.id.ivSort)
	ImageView ivSort;   //筛选_综合_ImageView

	@BindView(R.id.btnSale)
	TextView btnSale; //筛选_销量优先

	@BindView(R.id.llScreen)
	LinearLayout llScreen; //筛选_筛选layout
	@BindView(R.id.ivScreen)
	ImageView ivScreen; //筛选_筛选ImageView
	@BindView(R.id.btnScreen)
	TextView btnScreen; //筛选_筛选TextView


	@BindView(R.id.search_drawer)
	DrawerLayout drawer;  //筛选界面_drawer
	@BindView(R.id.nav_view)
	LinearLayout nav_view;//筛选界面_layout

	@BindView(R.id.search_loadingView)
	XLoadingView xLoadingView;
	@BindView(R.id.swipeLayout)
	SwipeRefreshLayout mSwipeRefreshLayout;

	private TextView btnSortDefault;
	private TextView btnSortHon;
	private TextView btnSortPriceL;
	private TextView btnSortPriceU;


	private int page = 1;
	private String keyword;
	private int brandId = -1;
	private int cat = -1;
	private String sort = "";
	private String selectValue = "";
	private List<GoodsVo> goodses;
	private SearchGoodListAdapter adapter;
	private PageEntity pageEntity;

	private Context context;
	private boolean isList;
	private LinearLayoutManager manager;
	private int currentItemPosition = 0;
	private GoodSearchShowContract.GoodSearchShowPresenter presenter;
	private SelectFilterTest selectFilterTests;
	private RightSearchPly menuHeaderView;
	private PopupWindow popSort;

	@Override
	public int getLayoutId() {
		return R.layout.fragment_store_all;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		new SearchGoodsShowPresenterImpl(this);

		keyword = "手机";
		presenter.initData(keyword, brandId, cat, sort, selectValue, page);
	}

	@Override
	public void initView() {
		showState(0);


		this.isList = true;
		xLoadingView.setOnRetryClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				XToast.success("重新加载");
				xLoadingView.showLoading();
			}
		});
		mSwipeRefreshLayout.setOnRefreshListener(this);
		mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));

	}

	@Override
	public void onRefresh() {

	}

	@Override
	public void onLoadMoreRequested() {

	}

	@Override
	public void setPresenter(GoodSearchShowContract.GoodSearchShowPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void showState(int Sate) {
		switch (Sate) {
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
	public void showGoodList(List<GoodsVo> goodsVoList, SelectFilterTest selectFilterTest, PageEntity pageEntity) {
		if (page > 1) {
			this.pageEntity = pageEntity;
			adapter.addData(goodsVoList);
			adapter.loadMoreComplete();
		} else {
			goodses = new ArrayList<>();
			this.pageEntity = pageEntity;
			showListView();
			mSwipeRefreshLayout.setRefreshing(false);
			adapter.setEnableLoadMore(true);
			Log.v("shenme", "执行到这儿了？？？？？？？");
			if (goodsVoList != null) {
				goodses = goodsVoList;
			}

			if (goodses.size() > 0) {
				rvGoods.setVisibility(View.VISIBLE);
				adapter.setNewData(goodses);
				adapter.notifyDataSetChanged();
				showState(1);
				this.selectFilterTests = new SelectFilterTest();
				selectFilterTests = selectFilterTest;
			} else {
				showState(3);
			}

			adapter.setAutoLoadMoreSize(3);

		}

	}

	private void showListView() {
		if (this.isList) {
			LinearLayoutManager layoutManager = new LinearLayoutManager(context);
			layoutManager.setOrientation(1);
			this.rvGoods.setLayoutManager(layoutManager);
			this.manager = layoutManager;
			this.adapter = new SearchGoodListAdapter(R.layout.item_good_list, goodses, isList);
			this.adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
			this.rvGoods.setAdapter(adapter);
		} else {
			GridLayoutManager layoutManager2 = new GridLayoutManager(this.context, 2, 1, false);
			this.rvGoods.setLayoutManager(layoutManager2);
			this.manager = layoutManager2;
			this.adapter = new SearchGoodListAdapter(R.layout.activity_searchgood_item_grid, goodses, isList);
			this.rvGoods.setAdapter(adapter);
		}


		adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
				Intent intent = new Intent(getActivity(), GoodDetailsActivity.class);
				startActivity(intent);
			}
		});


		adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
			@Override
			public void onLoadMoreRequested() {

				rvGoods.postDelayed(new Runnable() {
					@Override
					public void run() {
						mSwipeRefreshLayout.setEnabled(false);
						XLog.v("有没有剩下的数据" + pageEntity.toString());
						if (pageEntity.isHasMore()) {
							page = page + 1;
							presenter.initData(keyword, brandId, cat, sort, selectValue, page);
							return;
						} else {
							adapter.loadMoreEnd(false);
						}
					}

				}, 1000);
			}
		}, rvGoods);
	}

	private List<GoodsVo> newData;
}
