package com.xinyuan.xyshop.ui.mine.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.even.LoginPageEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/29.
 */

public class RegFragment extends BaseFragment {
	private int loginType;
	@BindView(R.id.toolbar_tv)
	Toolbar toolbar_tv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;

	public static RegFragment newInstance() {
		RegFragment fragment = new RegFragment();
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_reg;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {

	}

	@Override
	public void onLazyInitView(@Nullable Bundle savedInstanceState) {


	}

	@OnClick({R.id.bt_login})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.bt_login:
				getActivity().finish();
				break;
		}
	}
}
