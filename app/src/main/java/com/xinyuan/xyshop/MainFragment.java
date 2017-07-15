package com.xinyuan.xyshop;

import android.os.Bundle;
import android.support.annotation.Nullable;


import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.even.MainFragmentStartEvent;
import com.xinyuan.xyshop.even.MainFragmentStartEvent;
import com.xinyuan.xyshop.even.TabSelectedEvent;
import com.xinyuan.xyshop.ui.catrgory.CategoryFragment;
import com.xinyuan.xyshop.ui.home.HomeFragment;
import com.xinyuan.xyshop.ui.mine.MineFragment;
import com.xinyuan.xyshop.ui.shopcar.CartFragment;
import com.xinyuan.xyshop.widget.BottomBar;
import com.xinyuan.xyshop.widget.BottomBarTab;
import com.youth.xframe.common.XActivityStack;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by fx on 2017/6/26.
 */

public class MainFragment extends BaseFragment {
	@BindView(R.id.bottomBar)
	BottomBar bottomBar; //底部菜单栏


	public static final int HOME = 0;   //首页位置
	public static final int CLASS = 1;  //分类位置
	public static final int CAR = 2;    //购物车位置
	public static final int MINE = 3;   //个人中心位置


	private SupportFragment[] mFragments = new SupportFragment[4];  //主页4页面

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
		bottomBar.addItem(new BottomBarTab(_mActivity, R.drawable.act_home_home, "首页"))
				.addItem(new BottomBarTab(_mActivity, R.drawable.act_home_we, "分类"))
				.addItem(new BottomBarTab(_mActivity, R.drawable.act_home_shopcar, "购物车"))
				.addItem(new BottomBarTab(_mActivity, R.drawable.act_home_my, "我的"));
		bottomBar.setCurrentItem(0);    //设置首页为默认加载
		bottomBar.getItem(HOME).setUnreadCount(0);
		bottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
			@Override
			public void onTabSelected(int position, int prePosition) {
				showHideFragment(mFragments[position], mFragments[prePosition]);
				BottomBarTab tab = bottomBar.getItem(HOME);
				EventBus.getDefault().post(new TabSelectedEvent(position));     //发送消息，当前选择的页面位置
			}

			@Override
			public void onTabUnselected(int position) {

			}

			@Override
			public void onTabReselected(int position) {
			}
		});
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		HomeFragment homeFragment = findChildFragment(HomeFragment.class);
		//如果没有创建，则新建Fragment并加载
		if (homeFragment == null) {
			mFragments[HOME] = HomeFragment.newInstance();
			mFragments[CLASS] = CategoryFragment.newInstance();
			mFragments[CAR] = CartFragment.newInstance();
			mFragments[MINE] = MineFragment.newInstance();
			loadMultipleRootFragment(R.id.fl_tab_container, HOME,
					mFragments[HOME],
					mFragments[CLASS],
					mFragments[CAR],
					mFragments[MINE]);
		} else {
			mFragments[HOME] = homeFragment;
			mFragments[CLASS] = findChildFragment(CategoryFragment.class);
			mFragments[CAR] = findChildFragment(CartFragment.class);
			mFragments[MINE] = findChildFragment(MineFragment.class);
		}
	}

	/**
	 * 收到启动通知，启动子Fragment
	 *
	 * @param event
	 */
	@Subscribe
	public void startBrother(MainFragmentStartEvent event) {
		if (event.isResult) {
			startForResult(event.targetFragment, event.requestCode);
		} else {
			start(event.targetFragment);
		}


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

	private long mExitTime;

	@Override
	public boolean onBackPressedSupport() {


		if ((System.currentTimeMillis() - mExitTime) > 2000) {
			Object mHelperUtils;
			XToast.info("要退出吗？请再按一次返回即可退出");
			mExitTime = System.currentTimeMillis();

		} else {
			XActivityStack.getInstance().appExit();
		}
		return true;
	}


}
