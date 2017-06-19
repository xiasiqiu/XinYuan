package com.xinyuan.xyshop.ui.mine.order;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.CommonPagerAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.ui.mine.order.fragment.MyOrderFragment;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/6/12.
 */

public class OrderActivity extends BaseActivity {

	@BindView(R.id.stab_order)
	SegmentTabLayout psts_tabs;


	@BindView(R.id.order_contents)
	FrameLayout order_content;

	@BindView(R.id.msg_toolbar)
	Toolbar msg_toolbar;

	private MyOrderFragment nOrderFragment;
	private MyOrderFragment xFragment;
	private CommonPagerAdapter adapter;
	private FragmentManager fragmentManager;
	private Fragment currentFragment = new Fragment();
	private int currentIndex = 0;
	private ArrayList<Fragment> fragmentList = new ArrayList<>();
	public static int DATA_TYPE = 0;
	private String[] mTitles = {"实物订单", "虚拟订单"};

	@Override
	public int getLayoutId() {
		return R.layout.activity_order;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		ButterKnife.bind(this);

		SystemBarHelper.immersiveStatusBar(this); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(this, msg_toolbar);
		for (String title : mTitles) {
			fragmentList.add(nOrderFragment.getInstance(title));
		}

		fragmentManager = getSupportFragmentManager();
		psts_tabs.setTabData(mTitles);
		psts_tabs.setOnTabSelectListener(new OnTabSelectListener() {
			@Override
			public void onTabSelect(int position) {
				currentIndex = position;
				showFragment();
			}

			@Override
			public void onTabReselect(int position) {
				currentIndex = position;
				showFragment();
			}
		});
		showFragment();

	}

	private void showFragment() {

		FragmentTransaction transaction = fragmentManager.beginTransaction();


		//如果之前没有添加过
		if (!fragmentList.get(currentIndex).isAdded()) {
			transaction
					.hide(currentFragment)
					.add(R.id.order_contents, fragmentList.get(currentIndex), "" + currentIndex);  //第三个参数为添加当前的fragment时绑定一个tag

		} else {
			XLog.v("当前显示的Fragment为" + currentIndex);
			transaction
					.hide(currentFragment)
					.show(fragmentList.get(currentIndex));
		}

		currentFragment = fragmentList.get(currentIndex);
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

		transaction.commitAllowingStateLoss();


	}

}
