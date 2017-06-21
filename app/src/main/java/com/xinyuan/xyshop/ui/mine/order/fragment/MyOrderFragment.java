package com.xinyuan.xyshop.ui.mine.order.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
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

	private final String[] mEntityTitles = {
			"全部", "待付款", "待发货"
			, "待收货", "待评价"
	};
	private final String[] mFicTitle = {
			"全部", "待付款", "待使用"
			, "待评价"
	};
	private String mTitle;

	private ArrayList<Fragment> mFragments = new ArrayList<>();
	private CommonPagerAdapter adapter;
	private MyOrderContentFragment contentFragment;
	private int index;
	public static MyOrderFragment getInstance(String title,int index) {
		MyOrderFragment sf = new MyOrderFragment();
		sf.mTitle = title;
		sf.index=index;
		return sf;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_order;
	}

	@Override
	public void initData(Bundle savedInstanceState) {


	}

	@Override
	public void initView() {
		XLog.v("Fragment" + ":" + mTitle);
		if (mTitle.equals("实物订单")) {
			for (String title : mEntityTitles) {
				mFragments.add(contentFragment.getInstance(title, mTitle));

			}
			adapter = new CommonPagerAdapter(getChildFragmentManager(), mFragments, mEntityTitles);
		} else if (mTitle.equals("虚拟订单")) {
			for (String title : mFicTitle) {
				mFragments.add(contentFragment.getInstance(title, mTitle));

			}
			adapter = new CommonPagerAdapter(getChildFragmentManager(), mFragments, mFicTitle);
		}


		vp_content.setAdapter(adapter);
		order__tabs.setViewPager(vp_content);
		vp_content.setCurrentItem(index);
		vp_content.setOffscreenPageLimit(4);


	}


}
