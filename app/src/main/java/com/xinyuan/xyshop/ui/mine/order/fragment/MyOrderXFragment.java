
package com.xinyuan.xyshop.ui.mine.order.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.CommonPagerAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.ui.mine.order.OrderActivity;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/16.
 */

public class MyOrderXFragment extends BaseFragment {
	@BindView(R.id.order__tabs)
	SlidingTabLayout order__tabs;
	@BindView(R.id.vp_content)
	public ViewPager vp_content;

	private final String[] mTitles = {
			"X全部", "X待付款", "X待发货"
			, "X待收货", "X待评价"
	};
	private String mTitle;
	private ArrayList<Fragment> mFragments = new ArrayList<>();
	private CommonPagerAdapter adapter;
	private MyOrderContentXFragment contentFragment;

	public static MyOrderXFragment getInstance(String title) {
		MyOrderXFragment sf = new MyOrderXFragment();
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
		XLog.v("虚拟Fragment" + mTitle);
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
