package com.xinyuan.xyshop;

import android.os.Bundle;

import com.xinyuan.xyshop.base.BaseActivity;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;

/**
 * 首页Activity
 */
public class MainActivity extends BaseActivity {

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
			//fragmentation框架里加载主Fragment
			loadRootFragment(R.id.main_content, MainFragment.newInstance());
		}
		setFragmentAnimator(new DefaultHorizontalAnimator());
	}
}
