package com.xinyuan.xyshop.ui.catrgory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.GoodsCategoryAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.entity.GoodCategory;
import com.xinyuan.xyshop.mvp.contract.CategoryContract;
import com.xinyuan.xyshop.mvp.presenter.CategoryPresenterImpl;
import com.xinyuan.xyshop.util.SystemBarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fx on 2017/5/2 0002.
 */

public class CategoryFragment extends BaseFragment implements CategoryContract.CategoryView {

	private Toolbar toolbar;

	private CategoryContract.CategoryPresenter presenter;
	private AddViewHolder currentGoodsClassView;
	private int currentItem = 0;
	public static List<GoodCategory> goodsCategoryList_one = new ArrayList();
	public static List<GoodCategory> goodsCategoryList_three = new ArrayList();
	public static List<GoodCategory> goodsCategoryList_two = new ArrayList();

	@BindView(R.id.llGoodsClassRoot)
	LinearLayout llGoodsClassRoot;
	@BindView(R.id.svGoodsClassRoot)
	ScrollView svGoodsClassRoot;

	@BindView(R.id.gvBrand)
	GridView gvBrand;
	@BindView(R.id.svGoodsClass)
	ScrollView svGoodsClass;
	@BindView(R.id.llGoodsClass)
	LinearLayout llGoodsClass;
	@Override
	protected void lazyLoad() {

	}

	@Override
	public View initLayout(LayoutInflater inflater, ViewGroup container, boolean b) {
		View rootView = inflater.inflate(R.layout.fragment_category, null);
		ButterKnife.bind(this, rootView);
		toolbar = (Toolbar) rootView.findViewById(R.id.category_toolbar);
		SystemBarHelper.immersiveStatusBar(getActivity(), 0);
		SystemBarHelper.setHeightAndPadding(getActivity(), toolbar);
		new CategoryPresenterImpl(this);
		return rootView;
	}

	@Override
	protected void initData(@Nullable Bundle savedInstanceState) {
		presenter.initData();
	}

	@Override
	public void setPresenter(CategoryContract.CategoryPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void showState(int Sate) {

	}


	@Override
	public void addGoodsClass(GoodCategory classItem, int m) {

	}

	@Override
	public void OnImageViewClick(View view, String type, String data, boolean isAD) {

	}

	@Override
	public void showFrist(GoodCategory classItem, int m) {
		AddViewHolder holder = new AddViewHolder(getActivity(), R.layout.category_item_frist);
		holder.setText(R.id.tvGoodsClassId, String.valueOf(classItem.getCategoryId())).setText(R.id.tv_category_first,classItem.getCategoryName());
		if (m == 0) {
			setCurrentGoodsClass(holder, classItem.getAppImageUrl());
			this.currentGoodsClassView = holder;
			this.currentItem = m;
		}
		this.llGoodsClassRoot.addView(holder.getCustomView());
		setItemClick(holder, classItem, m);
	}

	@Override
	public void getData() {
		goodsCategoryList_one=CategoryPresenterImpl.getGoodsCategoryList_one();
		goodsCategoryList_two=CategoryPresenterImpl.getGoodsCategoryList_two();
		goodsCategoryList_three=CategoryPresenterImpl.getGoodsCategoryList_three();
	}

	private void setItemClick(final AddViewHolder holder, final GoodCategory classItem, final int m){
		holder.setOnClickListener(R.id.llView, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (m != CategoryFragment.this.currentItem) {
					CategoryFragment.this.setCurrentGoodsClass(holder, classItem.getAppImageUrl());
					CategoryFragment.this.resetCurrentGoodsClass(CategoryFragment.this.currentGoodsClassView, ((GoodCategory) CategoryFragment.this.goodsCategoryList_one.get(CategoryFragment.this.currentItem)).getAppImageUrl());
					CategoryFragment.this.currentItem = m;
					CategoryFragment.this.currentGoodsClassView = holder;
					CategoryFragment.this.svGoodsClassRoot.smoothScrollTo(0, v.getTop());
					String goodsClassId = holder.getText(R.id.tvGoodsClassId);
					if (goodsClassId.equals("0")) {
						CategoryFragment.this.gvBrand.setVisibility(View.VISIBLE);
						CategoryFragment.this.svGoodsClass.setVisibility(View.GONE);
						return;
					}
					CategoryFragment.this.showGoodsClass(goodsClassId);
				}
			}
		});

	}

	private void showGoodsClass(String classId){
		this.gvBrand.setVisibility(View.GONE);
		this.svGoodsClass.setVisibility(View.VISIBLE);
		this.svGoodsClass.scrollTo(0, 0);
		this.llGoodsClass.removeAllViews();
		for (int i = 0; i < this.goodsCategoryList_two.size(); i++) {
			if (((GoodCategory) this.goodsCategoryList_two.get(i)).getParentId() == Integer.valueOf(classId).intValue()) {
				showRightView((GoodCategory) this.goodsCategoryList_two.get(i), i);
			}
		}
	}


	private void showRightView(final GoodCategory goodsCategory, int position) {
		AddViewHolder holder = new AddViewHolder(getActivity(), R.layout.category_item_class);
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
				CategoryPresenterImpl.jump(CategoryFragment.this.getActivity(), goodsCategory.getCategoryId(), false);
			}
		});
		String id = String.valueOf(goodsCategory.getCategoryId());
		List<GoodCategory> categories = new ArrayList();
		List<List<GoodCategory>> goodCatrList = new ArrayList();
		for (int j = 0; j < this.goodsCategoryList_three.size(); j++) {
			if (((GoodCategory) this.goodsCategoryList_three.get(j)).getParentId() == Integer.valueOf(id).intValue()) {
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
		holder.setTextColor(R.id.tv_category_first, R.color.colorPrimary).setBackgroundColor(R.id.tvLine, R.color.colorPrimary).setDimensionPixelSize(R.id.tvLine, R.dimen.tv_name_size);
	}
	private void resetCurrentGoodsClass(AddViewHolder holder, String url) {
		holder.setTextColor(R.id.tv_category_first, R.color.tv_hint).setBackgroundColor(R.id.tvLine, R.color.colorLine).setDimensionPixelSize(R.id.tvLine, R.dimen.tv_name_size);
	}

}
