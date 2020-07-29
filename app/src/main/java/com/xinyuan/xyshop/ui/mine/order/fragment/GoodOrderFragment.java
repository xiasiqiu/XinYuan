package com.xinyuan.xyshop.ui.mine.order.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.CommonPagerAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.OrderBean;
import com.xinyuan.xyshop.even.GoodOrderEven;
import com.xinyuan.xyshop.even.OrderBusEven;
import com.xinyuan.xyshop.ui.mine.order.OrderDetailFragment;
import com.xinyuan.xyshop.ui.mine.order.fragment.MyOrderContentFragment;
import com.youth.xframe.utils.log.XLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/9/5.
 * 实物订单fragment
 */

public class GoodOrderFragment extends BaseFragment {
	@BindView(R.id.order__tabs)
	SlidingTabLayout order__tabs;
	@BindView(R.id.vp_content)
	ViewPager vp_content;


	private ArrayList<Fragment> mFragments = new ArrayList<>();
	private final String[] mEntityTitles = {
			"全部", "待付款", "待发货"
			, "待收货", "待评价"
	};
	private MyOrderContentFragment contentFragment;
	private CommonPagerAdapter adapter;
	private int index;
	private String mTitle = "实物订单";

	public static GoodOrderFragment newInstance() {
		GoodOrderFragment fragment = new GoodOrderFragment();
		return fragment;
	}

	@Override
	public void initView(View rootView) {
		for (String title : mEntityTitles) {
			mFragments.add(contentFragment.getInstance(title, mTitle));
		}
		adapter = new CommonPagerAdapter(getChildFragmentManager(), mFragments, mEntityTitles);
		vp_content.setAdapter(adapter);
		order__tabs.setViewPager(vp_content);
		vp_content.setCurrentItem(index);
		vp_content.setOffscreenPageLimit(1);
	}

	@Override
	public void initData() {

	}

	@Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
	public void OrderBusEven(GoodOrderEven event) {
		order__tabs.setCurrentTab(event.getIndex());
	}

	@Override
	public void onStart() {
		super.onStart();
		EventBus.getDefault().register(this);

	}

	@Override
	public void onStop() {
		EventBus.getDefault().unregister(this);
		super.onStop();
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
