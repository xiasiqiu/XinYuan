package com.xinyuan.xyshop.ui.catrgory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.GoodCategory;
import com.xinyuan.xyshop.mvp.contract.CategoryContract;
import com.xinyuan.xyshop.mvp.presenter.CategoryPresenterImpl;
import com.xinyuan.xyshop.util.SystemBarHelper;

import butterknife.ButterKnife;

/**
 * Created by fx on 2017/5/2 0002.
 */

public class CategoryFragment extends BaseFragment implements CategoryContract.CategoryView {

	private Toolbar toolbar;

	private CategoryContract.CategoryPresenter presenter;

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
}
