package com.xinyuan.xyshop;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.even.StartBrotherEvent;
import com.xinyuan.xyshop.even.TabSelectedEvent;
import com.xinyuan.xyshop.ui.catrgory.CategoryFragment;
import com.xinyuan.xyshop.ui.home.HomeFragment;
import com.xinyuan.xyshop.ui.mine.MineFragment;
import com.xinyuan.xyshop.ui.shopcar.ShopCarFragment;
import com.xinyuan.xyshop.widget.BottomBar;
import com.xinyuan.xyshop.widget.BottomBarTab;
import com.youth.xframe.utils.log.XLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2017/6/26.
 */

public class MainFragment extends BaseFragment {
	@BindView(R.id.bottomBar)
	BottomBar bottomBar;
	private static final int REQ_USER = 100;
	public static final int HOME = 0;
	public static final int CLASS = 1;
	public static final int CAR = 2;
	public static final int MINE = 3;
	private SupportFragment[] mFragments = new SupportFragment[4];

	public static MainFragment newInstance() {
		Bundle args = new Bundle();
		MainFragment fragment = new MainFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_main;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		EventBus.getDefault().register(this);
		bottomBar.addItem(new BottomBarTab(_mActivity, R.drawable.act_home_home, "首页"))
				.addItem(new BottomBarTab(_mActivity, R.drawable.act_home_we, "分类"))
				.addItem(new BottomBarTab(_mActivity, R.drawable.act_home_shopcar, "购物车"))
				.addItem(new BottomBarTab(_mActivity, R.drawable.act_home_my, "我的"));
		bottomBar.setCurrentItem(0);
		bottomBar.getItem(HOME).setUnreadCount(0);

		bottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
			@Override
			public void onTabSelected(int position, int prePosition) {
				showHideFragment(mFragments[position], mFragments[prePosition]);
				XLog.v("Fragment" + mFragments.length);
				BottomBarTab tab = bottomBar.getItem(HOME);
				EventBus.getDefault().post(new TabSelectedEvent(position));
			}

			@Override
			public void onTabUnselected(int position) {

			}

			@Override
			public void onTabReselected(int position) {
				// 这里推荐使用EventBus来实现 -> 解耦
				// 在FirstPagerFragment,FirstHomeFragment中接收, 因为是嵌套的Fragment
				// 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
				EventBus.getDefault().post(new TabSelectedEvent(position));
			}
		});
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		HomeFragment homeFragment = findChildFragment(HomeFragment.class);
		if (homeFragment == null) {
			mFragments[HOME] = HomeFragment.newInstance();
			mFragments[CLASS] = CategoryFragment.newInstance();
			mFragments[CAR] = ShopCarFragment.newInstance();
			mFragments[MINE] = MineFragment.newInstance();
			loadMultipleRootFragment(R.id.fl_tab_container, HOME,
					mFragments[HOME],
					mFragments[CLASS],
					mFragments[CAR],
					mFragments[MINE]);
		} else {
			mFragments[HOME] = homeFragment;
			mFragments[CLASS] = findChildFragment(CategoryFragment.class);
			mFragments[CAR] = findChildFragment(ShopCarFragment.class);
			mFragments[MINE] = findChildFragment(MineFragment.class);
		}
	}


	@Subscribe
	public void startBrother(StartBrotherEvent event) {
		if (event.isResult) {
			startForResult(event.targetFragment, event.requestCode);
		} else {
			start(event.targetFragment);
		}


	}



	@Override
	public void onDestroyView() {
		EventBus.getDefault().unregister(this);
		super.onDestroyView();
	}

}
