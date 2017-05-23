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
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.entity.PageEntity;
import com.xinyuan.xyshop.entity.SearchGoodsList;
import com.xinyuan.xyshop.entity.SearchGoodsListTest;
import com.xinyuan.xyshop.entity.SelectFilter;
import com.xinyuan.xyshop.entity.SelectFilterTest;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.JsonUtil;
import com.xinyuan.xyshop.widget.dialog.SearchGoodSelectDialog;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class SearchGoodsShowActivity extends BaseActivity {
	private String keyword;
	private int brandId = -1;

	@BindView(R.id.rvGoods)
	RecyclerView rvGoods;
	@BindView(R.id.btnListType)
	ImageView btnListType;
	@BindView(R.id.btnSale)
	TextView btnSale;
	@BindView(R.id.btnScreen)
	TextView btnScreen;
	@BindView(R.id.btnSort)
	TextView btnSort;
	@BindView(R.id.goodshow_et_search)
	EditText goodshow_et_search;


	@BindView(R.id.ivSort)
	ImageView ivSort;
	@BindView(R.id.ivScreen)
	ImageView ivScreen;

	@BindView(R.id.search_drawer)
	DrawerLayout drawer;
	@BindView(R.id.nav_view)
	LinearLayout nav_view;


	private LinearLayoutManager manager;
	private Context context;
	private MyShopApplication application;
	private SearchGoodListAdapter adapter;
	private boolean isList;
	private int page = 1;
	private String selectValue = "";
	private String sort = "";
	private List<GoodsVo> goodses = new ArrayList();
	private int cat = -1;
	private SearchGoodsList searchGoodsList;
	private SearchGoodsListTest searchGoodsListTest;

	private PageEntity pageEntity;
	private SelectFilter selectFilter;
	private SelectFilterTest selectFilterTest;

	private List<SelectFilterTest.FilterKey> keyList = new ArrayList<>();
	private int currentItemPosition = 0;


	private PopupWindow popSort;
	private SearchGoodSelectDialog selectDialog;
	private RightSearchPly menuHeaderView;

	@Override
	public int getLayoutId() {
		return R.layout.activity_search_goodshow;
	}


	@Override
	public void initView() {
		ButterKnife.bind(this);
		context = SearchGoodsShowActivity.this;
		this.keyword = getIntent().getStringExtra("keyword");
		this.brandId = getIntent().getIntExtra("brandId", -1);
		XLog.v("keyword" + keyword);
		this.context = this;
		this.application = MyShopApplication.getInstance();
		goodshow_et_search.setHint(keyword);
		this.btnListType.setSelected(true);
		this.isList = true;
		this.selectDialog = new SearchGoodSelectDialog(this.context);
		sortSelected(true, false, "", false);
		loadGoods();


	}

	@Override
	public void initData(Bundle savedInstanceState) {
		this.cat = getIntent().getIntExtra("cat", -1);
	}

	private void initRecyclerView() {
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

	private void loadGoods() {

		String url = "https://java.bizpower.com/api/search?page=" + this.page + "&pageSize=" + 10;
		if (CommUtil.isNotEmpty(this.keyword)) {
			url = url + "&keyword=" + this.keyword;
		}
		if (this.brandId != -1) {
			url = url + "&brand=" + this.brandId;
		}
		if (CommUtil.isNotEmpty(this.sort)) {
			url = url + "&sort=" + this.sort;
		}
		if (CommUtil.isNotEmpty(this.selectValue)) {
			url = url + this.selectValue;
		}
		if (this.cat != -1) {
			url = url + "&cat=" + this.cat;
		}
		XLog.v("loadGoods: url = " + url);

		if (keyword.equals("手机")) {
			OkGo.get(Urls.URL_SEARCH_SHOUJI)
					.execute(new StringCallback() {
						@Override
						public void onSuccess(String s, Call call, Response response) {
							searchGoodsListTest = JsonUtil.toBean(s, SearchGoodsListTest.class);
							SearchGoodsListTest.SearchGoodsData data = searchGoodsListTest.getDatas();
							selectFilterTest = data.getSelectFilter();
							XLog.v("selectFilterTest" + selectFilterTest);
							keyList = selectFilterTest.getKeyList();
							XLog.list(keyList);
							pageEntity = data.getPageEntity();
							List<GoodsVo> goodsVoList = data.getGoodsList();
							initRecyclerView();
							if (goodsVoList != null) {
								SearchGoodsShowActivity.this.goodses.addAll(goodsVoList);
							}
							if (SearchGoodsShowActivity.this.goodses.size() > 0) {
								SearchGoodsShowActivity.this.rvGoods.setVisibility(View.VISIBLE);
								SearchGoodsShowActivity.this.adapter.setNewData(SearchGoodsShowActivity.this.goodses);
								SearchGoodsShowActivity.this.adapter.notifyDataSetChanged();
								return;
							}
						}
					});

		} else {


			OkGo.get(url)
					.execute(new StringCallback() {
						@Override
						public void onSuccess(String s, Call call, Response response) {

							searchGoodsList = JsonUtil.toBean(s, SearchGoodsList.class);
							XLog.v("datas" + searchGoodsList);
							SearchGoodsList.SearchGoodsData data = searchGoodsList.getDatas();
							pageEntity = data.getPageEntity();
							selectFilter = data.getSelectFilter();
							List<GoodsVo> goodsVoList = data.getGoodsList();
							initRecyclerView();

							if (goodsVoList != null) {
								SearchGoodsShowActivity.this.goodses.addAll(goodsVoList);
							}
							if (SearchGoodsShowActivity.this.goodses.size() > 0) {
								SearchGoodsShowActivity.this.rvGoods.setVisibility(View.VISIBLE);
								SearchGoodsShowActivity.this.adapter.setNewData(SearchGoodsShowActivity.this.goodses);
								SearchGoodsShowActivity.this.adapter.notifyDataSetChanged();
								return;
							}

						}
					});
		}
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
				initRecyclerView();
				this.adapter.setNewData(this.goodses);
				this.rvGoods.smoothScrollToPosition(this.currentItemPosition + 3);
				this.adapter.notifyDataSetChanged();
				return;
			case R.id.llSort:
				sortSelected(true, false, "", false);
				showSortPopWindow(view);
				this.sort = this.btnSortDefault.isSelected() ? "" : "comment_desc";
				this.page = 1;
				loadGoods();
				return;
			case R.id.llScreen:
				sortSelected(false, false, "", true);
				menuHeaderView = new RightSearchPly(SearchGoodsShowActivity.this,selectFilterTest);
				drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
				nav_view.addView(menuHeaderView);
				openMenu();
				return;
		}
	}

	public void closeMenu() {
		drawer.closeDrawer(GravityCompat.END);
	}

	public void openMenu() {
		drawer.openDrawer(GravityCompat.END);
	}

	private TextView btnSortDefault;
	private TextView btnSortHon;
	private TextView btnSortPriceL;
	private TextView btnSortPriceU;

	private void showSortPopWindow(View view) {
		if (this.popSort == null) {
			View viewPopSort = LayoutInflater.from(this).inflate(R.layout.popwindow_goods_sort, null);
			this.popSort = new PopupWindow(viewPopSort, -1, -1, true);
			this.popSort.setTouchable(true);
			this.popSort.setOutsideTouchable(true);
			this.popSort.setBackgroundDrawable(new BitmapDrawable(this.context.getResources(), (Bitmap) null));
			viewPopSort.setOnTouchListener(new View.OnTouchListener() {
				public boolean onTouch(View v, MotionEvent event) {
					if (SearchGoodsShowActivity.this.popSort != null && SearchGoodsShowActivity.this.popSort.isShowing()) {
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
					SearchGoodsShowActivity.this.btnSort.setText("综合");
					SearchGoodsShowActivity.this.sortSelected(true, false, "", false);
					SearchGoodsShowActivity.this.sort = "";
					SearchGoodsShowActivity.this.page = 1;
					SearchGoodsShowActivity.this.loadGoods();
					SearchGoodsShowActivity.this.popSort.dismiss();
				}
			});
			this.btnSortHon = (TextView) viewPopSort.findViewById(R.id.btnSortHon);
			this.btnSortHon.setSelected(false);
			this.btnSortHon.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					if (SearchGoodsShowActivity.this.btnSortDefault.isSelected()) {
						SearchGoodsShowActivity.this.popSort.dismiss();
						return;
					}
					SearchGoodsShowActivity.this.btnSortDefault.setSelected(false);
					SearchGoodsShowActivity.this.btnSortHon.setSelected(true);
					SearchGoodsShowActivity.this.btnSortPriceL.setSelected(false);
					SearchGoodsShowActivity.this.btnSortPriceU.setSelected(false);
					SearchGoodsShowActivity.this.btnSort.setText("信用");
					SearchGoodsShowActivity.this.sortSelected(true, false, "", false);
					SearchGoodsShowActivity.this.sort = "";
					SearchGoodsShowActivity.this.page = 1;
					SearchGoodsShowActivity.this.loadGoods();
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
					SearchGoodsShowActivity.this.loadGoods();
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
					SearchGoodsShowActivity.this.loadGoods();
					SearchGoodsShowActivity.this.popSort.dismiss();

				}
			});


		}
		this.popSort.showAsDropDown(view);

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


