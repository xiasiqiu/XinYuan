package com.xinyuan.xyshop.ui.shopcar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.youth.xframe.base.XFragment;

import butterknife.ButterKnife;

/**
 * Created by fx on 2017/5/2 0002.
 */

public class ShopCarFragment extends BaseFragment {


	@Override
	protected void lazyLoad() {

	}

	@Override
	public View initLayout(LayoutInflater inflater, ViewGroup container, boolean b) {
		View rootView = inflater.inflate(R.layout.fragment_shopcar, null);
		ButterKnife.bind(this, rootView);
		return rootView;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}


}
