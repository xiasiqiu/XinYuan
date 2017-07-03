package com.xinyuan.xyshop.ui.mine.order.fragment;

import android.os.Bundle;
import android.view.View;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.ui.mine.login.LoginFragment;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/1.
 */

public class ServiceFragment extends BaseFragment {

	public static ServiceFragment newInstance() {
		ServiceFragment fragment = new ServiceFragment();
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_service;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {

	}

	@OnClick({R.id.rl_service_money, R.id.rl_service_goods})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.rl_service_money:
				start(ServiceMoneyFragment.newInstance());
				break;
			case R.id.rl_service_goods:
				start(ServiceGoodsFragment.newInstance());
				break;
		}
	}
}
