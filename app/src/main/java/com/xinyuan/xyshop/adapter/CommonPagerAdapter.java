package com.xinyuan.xyshop.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/16.
 */

public class CommonPagerAdapter extends FragmentPagerAdapter {
	ArrayList<Fragment> mFragments;
	private String[] mTitles;

	public CommonPagerAdapter(FragmentManager fm, ArrayList<Fragment> mFragments, String[] mTitles) {
		super(fm);
		this.mFragments = mFragments;
		this.mTitles = mTitles;
	}


	@Override
	public int getCount() {
		return mFragments.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return mTitles[position];
	}

	@Override
	public Fragment getItem(int position) {
		return mFragments.get(position);
	}


}
