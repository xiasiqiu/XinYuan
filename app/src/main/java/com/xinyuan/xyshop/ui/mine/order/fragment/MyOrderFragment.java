package com.xinyuan.xyshop.ui.mine.order.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.CommonPagerAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.youth.xframe.utils.log.XLog;


import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/16.
 */

public class MyOrderFragment extends BaseFragment {

	@BindView(R.id.order__tabs)
	SlidingTabLayout order__tabs;
	@BindView(R.id.vp_content)
	public ViewPager vp_content;

	private final String[] mTitles = {
			"全部", "待付款", "待发货"
			, "待收货", "待评价"
	};
	private String mTitle;
	private ArrayList<Fragment> mFragments = new ArrayList<>();
	private CommonPagerAdapter adapter;
	private MyOrderContentFragment contentFragment;

	public static MyOrderFragment getInstance(String title) {
		MyOrderFragment sf = new MyOrderFragment();
		sf.mTitle = title;
		return sf;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_mine_order;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		XLog.v("实物Fragment" + mTitle);
		for (String title : mTitles) {
			mFragments.add(contentFragment.getInstance(title));
		}
		adapter = new CommonPagerAdapter(getChildFragmentManager(), mFragments, mTitles);
		vp_content.setAdapter(adapter);
		order__tabs.setViewPager(vp_content);
		vp_content.setCurrentItem(0);
		vp_content.setOffscreenPageLimit(4);
	}


}
