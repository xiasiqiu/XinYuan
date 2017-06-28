package com.xinyuan.xyshop.ui.mine.info;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.ui.mine.pro.AccountConFragment;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.log.XLog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/27.
 */

public class SecurityFragment extends BaseFragment {
	@BindView(R.id.toolbar_iv)
	Toolbar msg_toolbar;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;

	public static SecurityFragment newInstance() {
		SecurityFragment fragment = new SecurityFragment();
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_security;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		if (msg_toolbar != null) {
			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
			tv_header_center.setText("账号与安全设置");
		}
	}

	@OnClick({R.id.rl_user_band})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.rl_user_band:
				start(BandFragment.newInstance());
				break;
		}

	}
}
