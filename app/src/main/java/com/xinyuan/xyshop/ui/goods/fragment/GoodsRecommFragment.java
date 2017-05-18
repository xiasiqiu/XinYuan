package com.xinyuan.xyshop.ui.goods.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;

/**
 * Created by Administrator on 2017/5/18.
 */

public class GoodsRecommFragment extends BaseFragment {
	@Override
	protected void lazyLoad() {

	}

	@Override
	public View initLayout(LayoutInflater inflater, ViewGroup container, boolean b) {
		View rootView = inflater.inflate(R.layout.fragment_good_recomm, null);
		return rootView;
	}

	@Override
	protected void initData(@Nullable Bundle savedInstanceState) {

	}
}
