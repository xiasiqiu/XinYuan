package com.xinyuan.xyshop.ui.goods;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.SearchGoodListAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.entity.PageEntity;
import com.xinyuan.xyshop.entity.SelectFilterTest;
import com.xinyuan.xyshop.mvp.contract.GoodSearchShowContract;
import com.xinyuan.xyshop.mvp.presenter.SearchGoodsShowPresenterImpl;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailsActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchGoodsShowActivity extends BaseActivity implements GoodSearchShowContract.GoodSearchShowView, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {


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
	private String brandName;
	private int cat = -1;
	private int brandId = -1;
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

	public int getLayoutId() {
		return R.layout.activity_search_goodshow;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		new SearchGoodsShowPresenterImpl(this);
		Intent intent = getIntent();
		brandName = intent.getStringExtra("keyword");
		if (!brandName.equals("")) {
			keyword = brandName;
		} else {
			keyword = MyShopApplication.getKeyWord();
		}

		presenter.initData(keyword, brandId, cat, sort, selectValue, page);
	}

	@Override
	public void initView() {
		ButterKnife.bind(this);
		showState(0);
		context = SearchGoodsShowActivity.this;
		this.keyword = getIntent().getStringExtra("keyword");
		this.brandId = getIntent().getIntExtra("brandId", -1);
		this.cat = getIntent().getIntExtra("cat", -1);
		this.isList = true;
		xLoadingView.setOnRetryClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				XToast.success("重新加载");
				xLoadingView.showLoading();
			}
		});
		mSwipeRefreshLayout.setOnRefreshListener(SearchGoodsShowActivity.this);
		mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));


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


	private void showListView() {
		if (this.isList) {
			LinearLayoutManager layoutManager = new LinearLayoutManager(this);
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
				CommUtil.gotoActivity(SearchGoodsShowActivity.this, GoodDetailsActivity.class, false, null);
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
							page = SearchGoodsShowActivity.this.page + 1;
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

		menuHeaderView = new RightSearchPly(SearchGoodsShowActivity.this, selectFilterTests);
		nav_view.addView(menuHeaderView);
		drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
	}


	public void showSortPopWindow(View view) {
		sortSelected(true, false, false);
		if (this.popSort == null) {
			View viewPopSort = LayoutInflater.from(this).inflate(R.layout.item_good_sort_popwindow, null);
			this.popSort = new PopupWindow(viewPopSort, -50, -50, true);
			this.popSort.setTouchable(true);
			this.popSort.setOutsideTouchable(true);
			this.popSort.setBackgroundDrawable(new BitmapDrawable(this.context.getResources(), (Bitmap) null));
			viewPopSort.setOnTouchListener(new View.OnTouchListener() {
				public boolean onTouch(View v, MotionEvent event) {
					if (popSort != null && popSort.isShowing()) {
						SearchGoodsShowActivity.this.popSort.dismiss();
					}
					return false;
				}
			});

			this.btnSortDefault = (TextView) viewPopSort.findViewById(R.id.btnSortDefault);
			this.btnSortDefault.setSelected(true);
			this.btnSortDefault.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					if (SearchGoodsShowActivity.this.btnSortDefault.isSelected()) {
						SearchGoodsShowActivity.this.popSort.dismiss();
						return;
					}
					SearchGoodsShowActivity.this.btnSortDefault.setSelected(true);
					SearchGoodsShowActivity.this.btnSortHon.setSelected(false);
					SearchGoodsShowActivity.this.btnSortPriceL.setSelected(false);
					SearchGoodsShowActivity.this.btnSortPriceU.setSelected(false);
					SearchGoodsShowActivity.this.btnSort.setText("综合排序");
					SearchGoodsShowActivity.this.sort = "comment_desc";
					page = 1;
					presenter.initData(keyword, brandId, cat, sort, selectValue, page);
					SearchGoodsShowActivity.this.popSort.dismiss();
				}
			});
			this.btnSortHon = (TextView) viewPopSort.findViewById(R.id.btnSortHon);
			this.btnSortHon.setSelected(false);
			this.btnSortHon.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					if (SearchGoodsShowActivity.this.btnSortHon.isSelected()) {
						SearchGoodsShowActivity.this.popSort.dismiss();
						return;
					}
					SearchGoodsShowActivity.this.btnSortDefault.setSelected(false);
					SearchGoodsShowActivity.this.btnSortHon.setSelected(true);
					SearchGoodsShowActivity.this.btnSortPriceL.setSelected(false);
					SearchGoodsShowActivity.this.btnSortPriceU.setSelected(false);
					SearchGoodsShowActivity.this.btnSort.setText("信用排序");
					SearchGoodsShowActivity.this.sort = "";
					SearchGoodsShowActivity.this.page = 1;
					presenter.initData(keyword, brandId, cat, sort, selectValue, page);
					SearchGoodsShowActivity.this.popSort.dismiss();
				}
			});
			this.btnSortPriceL = (TextView) viewPopSort.findViewById(R.id.btnSortPriceL);
			this.btnSortPriceL.setSelected(false);
			this.btnSortPriceL.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					if (SearchGoodsShowActivity.this.btnSortPriceL.isSelected()) {
						SearchGoodsShowActivity.this.popSort.dismiss();
						return;
					}
					SearchGoodsShowActivity.this.btnSortDefault.setSelected(false);
					SearchGoodsShowActivity.this.btnSortHon.setSelected(false);
					SearchGoodsShowActivity.this.btnSortPriceL.setSelected(true);
					SearchGoodsShowActivity.this.btnSortPriceU.setSelected(false);
					SearchGoodsShowActivity.this.btnSort.setText("价格从低到高");
					sort = "price_asc";
					SearchGoodsShowActivity.this.page = 1;
					presenter.initData(keyword, brandId, cat, sort, selectValue, page);
					SearchGoodsShowActivity.this.popSort.dismiss();
				}
			});
			this.btnSortPriceU = (TextView) viewPopSort.findViewById(R.id.btnSortPriceU);
			this.btnSortPriceU.setSelected(false);
			this.btnSortPriceU.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					if (SearchGoodsShowActivity.this.btnSortPriceU.isSelected()) {
						SearchGoodsShowActivity.this.popSort.dismiss();
						return;
					}
					SearchGoodsShowActivity.this.btnSortDefault.setSelected(false);
					SearchGoodsShowActivity.this.btnSortHon.setSelected(false);
					SearchGoodsShowActivity.this.btnSortPriceL.setSelected(false);
					SearchGoodsShowActivity.this.btnSortPriceU.setSelected(true);
					SearchGoodsShowActivity.this.btnSort.setText("价格从高到低");
					sort = "price_desc";
					SearchGoodsShowActivity.this.page = 1;
					presenter.initData(keyword, brandId, cat, sort, selectValue, page);
					SearchGoodsShowActivity.this.popSort.dismiss();

				}
			});


		}
		this.popSort.showAsDropDown(view);
	}


	@OnClick({R.id.btnListType, R.id.llSort, R.id.llScreen, R.id.btnSale})
	public void onClick(View view) {
		boolean z = true;
		switch (view.getId()) {

			case R.id.btnListType:
				this.btnListType.setSelected(!this.btnListType.isSelected());
				if (this.isList) {
					z = false;
				}
				this.isList = z;
				this.currentItemPosition = this.manager.findFirstVisibleItemPosition();
				showListView();
				this.adapter.setNewData(this.goodses);
				this.rvGoods.smoothScrollToPosition(this.currentItemPosition);
				this.adapter.notifyDataSetChanged();
				return;
			case R.id.llSort:
				sortSelected(true, false, false);
				showSortPopWindow(view);
				return;
			case R.id.btnSale:
				sortSelected(false, true, false);
				this.sort = "sale_desc";
				this.page = 1;
				presenter.initData(keyword, brandId, cat, sort, selectValue, page);
				return;
			case R.id.llScreen:
				XLog.v("点击了筛选");


				if (drawer.isDrawerVisible(GravityCompat.END)) {
					closeMenu();
				} else {
					openMenu();
				}


				sortSelected(false, false, true);

				return;
		}
	}


	public void closeMenu() {
		drawer.closeDrawer(GravityCompat.END);
	}

	public void openMenu() {
		drawer.openDrawer(GravityCompat.END);
	}

	private void sortSelected(boolean sortFlag, boolean sale, boolean screen) {
		this.btnSort.setSelected(sortFlag);
		this.ivSort.setSelected(sortFlag);

		btnSale.setSelected(sale);

		this.btnScreen.setSelected(screen);
		this.ivScreen.setSelected(screen);


	}

	@Override
	public void onRefresh() {
		XLog.v("刷新了" + pageEntity.toString());
		adapter.setEnableLoadMore(false);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				presenter.initData(keyword, brandId, cat, sort, selectValue, page);
			}
		}, 1000);

	}


	@Override
	public void onLoadMoreRequested() {

	}
}


