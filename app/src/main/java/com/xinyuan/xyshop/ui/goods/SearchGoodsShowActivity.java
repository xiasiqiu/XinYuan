package com.xinyuan.xyshop.ui.goods;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.SearchGoodListAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.entity.GoodCategory;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.entity.PageEntity;
import com.xinyuan.xyshop.entity.SearchGoodsList;
import com.xinyuan.xyshop.entity.SearchGoodsListTest;
import com.xinyuan.xyshop.entity.SelectFilter;
import com.xinyuan.xyshop.entity.SelectFilterTest;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.mvp.contract.GoodSearchShowContract;
import com.xinyuan.xyshop.mvp.presenter.HomePresenterImpl;
import com.xinyuan.xyshop.mvp.presenter.SearchGoodsShowPresenterImpl;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.JsonUtil;
import com.xinyuan.xyshop.widget.dialog.SearchGoodSelectDialog;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.loadingview.XLoadingView;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class SearchGoodsShowActivity extends BaseActivity implements GoodSearchShowContract.GoodSearchShowView {


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
	AutoLinearLayout llScreen; //筛选_筛选layout
	@BindView(R.id.ivScreen)
	ImageView ivScreen; //筛选_筛选ImageView
	@BindView(R.id.btnScreen)
	TextView btnScreen; //筛选_筛选TextView

	@BindView(R.id.goodshow_et_search)
	EditText goodshow_et_search; //搜索栏

	@BindView(R.id.search_drawer)
	DrawerLayout drawer;  //筛选界面_drawer
	@BindView(R.id.nav_view)
	LinearLayout nav_view;//筛选界面_layout


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
	private List<GoodsVo> goodses = new ArrayList();
	private SearchGoodListAdapter adapter;
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

		keyword = getIntent().getStringExtra("keyword");
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
		goodshow_et_search.setHint(keyword);
		this.isList = true;
	}

	@Override
	public void setPresenter(GoodSearchShowContract.GoodSearchShowPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void showState(int Sate) {
		switch (Sate) {
			case 0:
				//xLoadingView.showLoading();
				break;
			case 1:

				//xLoadingView.showContent();
				break;
			case 2:
				//xLoadingView.showError();
				break;

		}
	}


	private void showListView() {
		if (this.isList) {
			LinearLayoutManager layoutManager = new LinearLayoutManager(this);
			layoutManager.setOrientation(1);
			this.rvGoods.setLayoutManager(layoutManager);
			this.manager = layoutManager;
			this.adapter = new SearchGoodListAdapter(R.layout.searchgood_item_list, goodses, isList);
			this.rvGoods.setAdapter(adapter);
		} else {
			GridLayoutManager layoutManager2 = new GridLayoutManager(this.context, 2, 1, false);
			this.rvGoods.setLayoutManager(layoutManager2);
			this.manager = layoutManager2;
			this.adapter = new SearchGoodListAdapter(R.layout.searchgood_item_grid, goodses, isList);
			this.rvGoods.setAdapter(adapter);
		}


		adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
				CommUtil.gotoActivity(SearchGoodsShowActivity.this, GoodDetailsActivity.class, false, null);
			}
		});

	}

	@Override
	public void showGoodList(List<GoodsVo> goodsVoList, SelectFilterTest selectFilterTest) {
		showListView();
		Log.v("shenme","执行到这儿了？？？？？？？");
		if (goodsVoList != null) {
			goodses.addAll(goodsVoList);
		}
		if (goodses.size() > 0) {
			rvGoods.setVisibility(View.VISIBLE);
			adapter.setNewData(goodses);
			adapter.notifyDataSetChanged();
		}

		showState(1);
		this.selectFilterTests = new SelectFilterTest();
		selectFilterTests = selectFilterTest;

		menuHeaderView = new RightSearchPly(SearchGoodsShowActivity.this, selectFilterTests);
		nav_view.addView(menuHeaderView);
		drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
	}


	public void showSortPopWindow(View view) {
		this.btnListType.setSelected(true);
		sortSelected(true, false, "", false);
		if (this.popSort == null) {
			View viewPopSort = LayoutInflater.from(this).inflate(R.layout.popwindow_goods_sort, null);
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
					SearchGoodsShowActivity.this.sortSelected(true, false, "", false);
					SearchGoodsShowActivity.this.sort = "";
					SearchGoodsShowActivity.this.page = 1;
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
					SearchGoodsShowActivity.this.sortSelected(true, false, "", false);
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
					SearchGoodsShowActivity.this.sortSelected(true, false, "price_desc", false);
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
					SearchGoodsShowActivity.this.sortSelected(true, false, "price_asc", false);
					SearchGoodsShowActivity.this.page = 1;
					presenter.initData(keyword, brandId, cat, sort, selectValue, page);
					SearchGoodsShowActivity.this.popSort.dismiss();

				}
			});


		}
		this.popSort.showAsDropDown(view);
	}


	@OnClick({R.id.btnListType, R.id.llSort, R.id.llScreen,})
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
				this.rvGoods.smoothScrollToPosition(this.currentItemPosition + 3);
				this.adapter.notifyDataSetChanged();
				return;
			case R.id.llSort:
				sortSelected(true, false, "", false);
				showSortPopWindow(view);
				this.sort = this.btnSortDefault.isSelected() ? "" : "comment_desc";
				this.page = 1;
				presenter.initData(keyword, brandId, cat, sort, selectValue, page);
				return;
			case R.id.llScreen:
				XLog.v("点击了筛选");


				if(drawer.isDrawerVisible(GravityCompat.END)){
					closeMenu();
				}else {
					openMenu();
				}


				sortSelected(false, false, "", true);

				return;
		}
	}


	private static boolean showing = false;

	public void closeMenu() {
		drawer.closeDrawer(GravityCompat.END);
	}

	public void openMenu() {
		drawer.openDrawer(GravityCompat.END);
	}

	private void sortSelected(boolean sortFlag, boolean sale, String price, boolean screen) {
		this.btnSort.setSelected(sortFlag);
		this.ivSort.setSelected(sortFlag);
		this.btnSale.setSelected(sale);
		this.btnScreen.setSelected(screen);
		this.ivScreen.setSelected(screen);
		if (price.equals("")) {

		} else {
			this.sort = price;
		}


	}
}


