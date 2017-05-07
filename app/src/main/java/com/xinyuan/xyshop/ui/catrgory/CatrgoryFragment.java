package com.xinyuan.xyshop.ui.catrgory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by fx on 2017/5/2 0002.
 */

public class CatrgoryFragment extends BaseFragment {
	@Override
	protected void lazyLoad() {

	}

	@Override
	public View initLayout(LayoutInflater inflater, ViewGroup container, boolean b) {
		View rootView = inflater.inflate(R.layout.fragment_catrgory, null);
		ButterKnife.bind(this, rootView);
		return rootView;
	}

	@Override
	protected void initData(@Nullable Bundle savedInstanceState) {

	}
}
