package com.xinyuan.xyshop.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/4.
 */

public class CommonFragmentPagerAdapter extends FragmentPagerAdapter {
	ArrayList<Fragment> list;

	public CommonFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
		super(fm);
		this.list = list;
	}

	@Override
	public Fragment getItem(int position) {
		return list.get(position);
	}

	@Override
	public int getCount() {
		return list != null ? list.size() : 0;
	}
}
