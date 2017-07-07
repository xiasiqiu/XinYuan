package com.xinyuan.xyshop.ui.mine.info;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.util.SystemBarHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/4.
 */

public class AddAddressFragment extends BaseFragment {
	@BindView(R.id.toolbar_tv)
	Toolbar toolbar_tv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;

	public static AddAddressFragment newInstance() {
		AddAddressFragment fragment = new AddAddressFragment();
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_address_add;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		if (toolbar_tv != null) {
			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_tv);
			tv_header_center.setText("新增收货地址");
		}
	}

	@OnClick(R.id.rl_area)
	public void onClick() {

	}
}
