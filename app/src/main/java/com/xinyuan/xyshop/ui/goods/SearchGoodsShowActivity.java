package com.xinyuan.xyshop.ui.goods;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.SearchGoodListAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.callback.DialogCallback;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.entity.PageEntity;
import com.xinyuan.xyshop.entity.SearchGoodsList;
import com.xinyuan.xyshop.entity.SelectFilter;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.JsonUtil;
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

	private TextView btnSortDefault;
	private TextView btnSortView;

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
	private PageEntity pageEntity;
	private SelectFilter selectFilter;
	private int currentItemPosition = 0;

	@Override
	public int getLayoutId() {
		return R.layout.activity_search_goods_show;
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
			this.adapter = new SearchGoodListAdapter(R.layout.recyclerview_searchgood_list_item, goodses, isList);
			this.rvGoods.setAdapter(adapter);
		} else {
			GridLayoutManager layoutManager2 = new GridLayoutManager(this.context, 2, 1, false);
			this.rvGoods.setLayoutManager(layoutManager2);
			this.manager = layoutManager2;
			this.adapter = new SearchGoodListAdapter(R.layout.recyclerview_searchgood_grid_item, goodses, isList);
			this.rvGoods.setAdapter(adapter);
		}

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


	@OnClick(R.id.btnListType)
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
		}
	}

	private void sortSelected(boolean sortFlag, boolean sale, String price, boolean screen) {
		this.btnSort.setSelected(sortFlag);
		this.ivSort.setSelected(sortFlag);
		this.btnSale.setSelected(sale);
		this.btnScreen.setSelected(screen);
		this.ivScreen.setSelected(screen);
	}
}


