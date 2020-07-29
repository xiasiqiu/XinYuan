package com.xinyuan.xyshop.ui.mine.order.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.CommonPagerAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by fx on 2017/9/5.
 * 虚拟订单fragment
 */

public class OnLineOrderFragment extends BaseFragment {


	@BindView(R.id.order__tabs)
	SlidingTabLayout order__tabs;
	@BindView(R.id.vp_content)
	ViewPager vp_content;
	private final String[] mEntityTitles = {
			"全部", "待付款", "待使用"
			, "待评价"
	};
	private MyOrderContentFragment contentFragment;
	private ArrayList<Fragment> mFragments = new ArrayList<>();
	private String mTitle="虚拟订单";

	public static OnLineOrderFragment newInstance() {
		OnLineOrderFragment fragment = new OnLineOrderFragment();
		return fragment;
	}

	@Override
	public void initView(View rootView) {
		for (String title : mEntityTitles) {
			mFragments.add(new MyOrderContentFragment().getInstance(title, mTitle));
		}
		vp_content.setAdapter(new CommonPagerAdapter(getChildFragmentManager(), mFragments, mEntityTitles));
		order__tabs.setViewPager(vp_content);
		vp_content.setCurrentItem(0);
		vp_content.setOffscreenPageLimit(1);
	}

	@Override
	public void initData() {

	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_good_order;
	}
}
