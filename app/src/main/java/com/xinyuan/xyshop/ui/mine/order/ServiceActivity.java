package com.xinyuan.xyshop.ui.mine.order;

import android.os.Bundle;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.ui.mine.login.LoginFragment;
import com.xinyuan.xyshop.ui.mine.order.fragment.ServiceFragment;

import org.greenrobot.eventbus.EventBus;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;

/**
 * Created by Administrator on 2017/7/1.
 */

public class ServiceActivity extends BaseActivity {
	@Override
	public int getLayoutId() {
		return R.layout.activity_service;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {


		if (findFragment(LoginFragment.class) == null) {
			loadRootFragment(R.id.fl_service, ServiceFragment.newInstance());
		}
		setFragmentAnimator(new DefaultHorizontalAnimator());
	}
}
