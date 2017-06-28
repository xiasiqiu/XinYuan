package com.xinyuan.xyshop.ui.mine.info;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.even.StartBrotherEvent;
import com.xinyuan.xyshop.ui.mine.pro.AccountFragment;
import com.xinyuan.xyshop.util.SystemBarHelper;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/27.
 */

public class SettingFragment extends BaseFragment {
	@BindView(R.id.toolbar_iv)
	Toolbar msg_toolbar;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;

	public static SettingFragment newInstance() {
		SettingFragment fragment = new SettingFragment();
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_setting;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		if (msg_toolbar != null) {
			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
			tv_header_center.setText("用户设置");
		}
	}

	@OnClick({R.id.bt_setting_address, R.id.bt_setting_cache, R.id.bt_setting_sugges, R.id.bt_setting_about, R.id.bt_setting_security})
	public void OnClick(View view) {
		switch (view.getId()) {
			case R.id.bt_setting_address:
				start(AddressFragment.newInstance());
				break;
			case R.id.bt_setting_security:
				start(SecurityFragment.newInstance());
				break;
			case R.id.bt_setting_cache:
				break;
			case R.id.bt_setting_sugges:
				break;
			case R.id.bt_setting_about:
				break;

		}

	}
}
