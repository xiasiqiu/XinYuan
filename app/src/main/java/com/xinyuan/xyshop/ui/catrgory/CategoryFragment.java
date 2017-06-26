package com.xinyuan.xyshop.ui.catrgory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.GoodsCategoryAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.mvp.contract.CategoryContract;
import com.xinyuan.xyshop.mvp.presenter.CategoryPresenterImpl;
import com.xinyuan.xyshop.ui.home.HomeFragment;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import com.xinyuan.xyshop.model.CategoryModel.CategoryData;

/**
 * Created by fx on 2017/5/2 0002.
 */

public class CategoryFragment extends BaseFragment implements CategoryContract.CategoryView {

	private CategoryContract.CategoryPresenter presenter;
	private AddViewHolder currentGoodsClassView;
	private int currentItem = 0;
	public static List<CategoryData> goodsCategoryList_one;
	public static List<CategoryData> goodsCategoryList_three;
	public static List<CategoryData> goodsCategoryList_two;

	@BindView(R.id.llGoodsClassRoot)
	LinearLayout llGoodsClassRoot;
	@BindView(R.id.svGoodsClassRoot)
	ScrollView svGoodsClassRoot;
	@BindView(R.id.svGoodsClass)
	ScrollView svGoodsClass;
	@BindView(R.id.llGoodsClass)
	LinearLayout llGoodsClass;
	@BindView(R.id.cagetory_img)
	ImageView categroy_img;
	@BindView(R.id.category_loadingView)
	XLoadingView xLoadingView;
	@BindView(R.id.category_toolbar)
	Toolbar toolbar;

	@BindView(R.id.category_btn_msg)
	ImageView btn_msg;
	@BindView(R.id.category_btn_scan)
	ImageView btn_scan;
	private static boolean VIEW_INIT = true;

	public static CategoryFragment newInstance() {

		Bundle args = new Bundle();

		CategoryFragment fragment = new CategoryFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_category;
	}

	@Override
	public void initView() {
		XLog.v("加载分类页面Fragment");
		if (VIEW_INIT) {
			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(getActivity(), toolbar);
		}
		VIEW_INIT = false;
	}


	@Override
	public void initData(@Nullable Bundle savedInstanceState) {
		if (VIEW_INIT) {
			new CategoryPresenterImpl(this);
			presenter.initData();
		}


	}


	@Override
	public void setPresenter(CategoryContract.CategoryPresenter presenter) {
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

		}
	}

	@Override
	public void getData() {
		goodsCategoryList_one = new ArrayList<>();
		goodsCategoryList_two = new ArrayList<>();
		goodsCategoryList_three = new ArrayList<>();
		goodsCategoryList_one = CategoryPresenterImpl.getGoodsCategoryList_one();
		goodsCategoryList_two = CategoryPresenterImpl.getGoodsCategoryList_two();
		goodsCategoryList_three = CategoryPresenterImpl.getGoodsCategoryList_three();
	}

	@Override
	public void showFrist(CategoryData classItem, int m) {
		AddViewHolder holder = new AddViewHolder(getActivity(), R.layout.fragment_category_item_frist);
		holder.setText(R.id.tvGoodsClassId, String.valueOf(classItem.getCategoryId())).setText(R.id.tv_category_first, classItem.getCategoryName());
		if (m == 0) {
			setCurrentGoodsClass(holder, classItem.getCategoryImageUrl());
			this.currentGoodsClassView = holder;
			this.currentItem = m;
			String goodsClassId = holder.getText(R.id.tvGoodsClassId);
			CategoryFragment.this.showGoodsClass(goodsClassId);
			GlideImageLoader.setImage(getContext(), classItem.getCategoryImageUrl(), categroy_img);
		}
		this.llGoodsClassRoot.addView(holder.getCustomView());
		setItemClick(holder, classItem, m);
		showState(1);
	}


	private void setItemClick(final AddViewHolder holder, final CategoryData classItem, final int m) {
		holder.setOnClickListener(R.id.llView, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (m != CategoryFragment.this.currentItem) {
					CategoryFragment.this.setCurrentGoodsClass(holder, classItem.getCategoryImageUrl());
					CategoryFragment.this.resetCurrentGoodsClass(CategoryFragment.this.currentGoodsClassView, ((CategoryData) CategoryFragment.this.goodsCategoryList_one.get(CategoryFragment.this.currentItem)).getCategoryImageUrl());
					CategoryFragment.this.currentItem = m;
					CategoryFragment.this.currentGoodsClassView = holder;

					String goodsClassId = holder.getText(R.id.tvGoodsClassId);

					CategoryFragment.this.showGoodsClass(goodsClassId);
					GlideImageLoader.setImage(getContext(), classItem.getCategoryImageUrl(), categroy_img);
				}
			}
		});

	}

	private void showGoodsClass(String classId) {
		this.svGoodsClass.setVisibility(View.VISIBLE);
		this.svGoodsClass.scrollTo(0, 0);
		this.llGoodsClass.removeAllViews();
		for (int i = 0; i < this.goodsCategoryList_two.size(); i++) {
			if (((CategoryData) this.goodsCategoryList_two.get(i)).getParentId() == Integer.valueOf(classId).intValue()) {
				showRightView((CategoryData) this.goodsCategoryList_two.get(i), i);
			}
		}
	}


	private void showRightView(final CategoryData goodsCategory, int position) {
		AddViewHolder holder = new AddViewHolder(getActivity(), R.layout.fragment_category_item_class);

		GridView gvGoodsClass = (GridView) holder.getCustomView().findViewById(R.id.gvGoodsClass);
		if (position == 0) {
			holder.setVisible(R.id.tvLine, false);
		}
		switch (position % 10) {
			case 0:
				holder.setImageDrawable((int) R.id.tvGoodsClassDot, (int) R.drawable.nc_sharp_dot0);
				break;
			case 1:
				holder.setImageDrawable((int) R.id.tvGoodsClassDot, (int) R.drawable.nc_sharp_dot1);
				break;
			case 2:
				holder.setImageDrawable((int) R.id.tvGoodsClassDot, (int) R.drawable.nc_sharp_dot2);
				break;
			case 3:
				holder.setImageDrawable((int) R.id.tvGoodsClassDot, (int) R.drawable.nc_sharp_dot3);
				break;
			case 4:
				holder.setImageDrawable((int) R.id.tvGoodsClassDot, (int) R.drawable.nc_sharp_dot4);
				break;
			case 5:
				holder.setImageDrawable((int) R.id.tvGoodsClassDot, (int) R.drawable.nc_sharp_dot5);
				break;
			case 6:
				holder.setImageDrawable((int) R.id.tvGoodsClassDot, (int) R.drawable.nc_sharp_dot6);
				break;
			case 7:
				holder.setImageDrawable((int) R.id.tvGoodsClassDot, (int) R.drawable.nc_sharp_dot7);
				break;
			case 8:
				holder.setImageDrawable((int) R.id.tvGoodsClassDot, (int) R.drawable.nc_sharp_dot8);
				break;
			case 9:
				holder.setImageDrawable((int) R.id.tvGoodsClassDot, (int) R.drawable.nc_sharp_dot9);
				break;
		}
		holder.setText(R.id.tvGoodsClassName, goodsCategory.getCategoryName());
		holder.setOnClickListener(R.id.rlGoodClass, new View.OnClickListener() {
			public void onClick(View view) {
				CategoryPresenterImpl.jump(CategoryFragment.this.getActivity(), goodsCategory.getCategoryName(), false);
			}
		});
		String id = String.valueOf(goodsCategory.getCategoryId());
		List<CategoryData> categories = new ArrayList();
		List<List<CategoryData>> goodCatrList = new ArrayList();
		for (int j = 0; j < this.goodsCategoryList_three.size(); j++) {
			if (((CategoryData) this.goodsCategoryList_three.get(j)).getParentId() == Integer.valueOf(id).intValue()) {
				categories.add(this.goodsCategoryList_three.get(j));
			}
		}
		if (!categories.isEmpty()) {
			goodCatrList.add(categories);
		}
		for (int k = 0; k < goodCatrList.size(); k++) {
			GoodsCategoryAdapter adapter = new GoodsCategoryAdapter(getActivity());
			adapter.setmDatas((List) goodCatrList.get(k));
			gvGoodsClass.setAdapter(adapter);
		}
		this.llGoodsClass.addView(holder.getCustomView());
	}


	private void setCurrentGoodsClass(AddViewHolder holder, String url) {
		holder.setTextColor(R.id.tv_category_first, R.color.colorPrimary).setBackgroundColor(R.id.llView, R.color.bg_gray);
	}

	private void resetCurrentGoodsClass(AddViewHolder holder, String url) {
		holder.setTextColor(R.id.tv_category_first, R.color.tv_name).setBackgroundColor(R.id.llView, R.color.bg_white);
	}

	@Override
	public void OnImageViewClick(View view, String type, String data, boolean isAD) {

	}

}
