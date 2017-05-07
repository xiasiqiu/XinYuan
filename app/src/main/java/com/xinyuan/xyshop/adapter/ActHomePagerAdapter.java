package com.xinyuan.xyshop.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by fx on 2017/5/2 0002.
 */

public class ActHomePagerAdapter extends FragmentPagerAdapter {
	//定一个Fragment数据源
	private List<Fragment> fragments;

	public ActHomePagerAdapter(FragmentManager fm, List<Fragment> fragments) {
		super(fm);
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}
}
