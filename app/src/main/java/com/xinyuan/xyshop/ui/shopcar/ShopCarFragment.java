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
	public int getLayoutId() {
		return R.layout.fragment_shopcar;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		ButterKnife.bind(this, getView());
	}


}
