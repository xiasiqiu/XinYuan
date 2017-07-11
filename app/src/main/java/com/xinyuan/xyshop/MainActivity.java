package com.xinyuan.xyshop;

import android.os.Bundle;

import com.xinyuan.xyshop.base.BaseActivity;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;

public class MainActivity extends BaseActivity {

	private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";


	@Override
	public int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		if (findFragment(MainFragment.class) == null) {
			loadRootFragment(R.id.main_content, MainFragment.newInstance());
		}
		setFragmentAnimator(new DefaultHorizontalAnimator());
	}
}
