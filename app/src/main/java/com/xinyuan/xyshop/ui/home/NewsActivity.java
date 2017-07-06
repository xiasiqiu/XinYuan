package com.xinyuan.xyshop.ui.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.CommonPagerAdapter;
import com.xinyuan.xyshop.adapter.ItemTitlePagerAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.ui.home.news.CreditNewsFragment;
import com.xinyuan.xyshop.ui.home.news.NewsFragment;
import com.xinyuan.xyshop.ui.mine.msg.MallMsgFragment;
import com.xinyuan.xyshop.ui.mine.msg.StoreMsgFragment;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/7/5.
 */

public class NewsActivity extends BaseActivity {


	@BindView(R.id.stab_msg)
	SegmentTabLayout psts_tabs;
	@BindView(R.id.vp_content)
	ViewPager viewPager;
	@BindView(R.id.new_toolbar)
	Toolbar new_toolbar;
	private NewsFragment newsFragment;
	private CreditNewsFragment creditNewsFragment;
	private CommonPagerAdapter adapter;
	private FragmentManager fragmentManager;
	private Fragment currentFragment = new Fragment();
	private int currentIndex = 0;
	private ArrayList<Fragment> fragmentList = new ArrayList<>();
	public static int DATA_TYPE = 0;
	private String[] mTitles = {"平台公告", "积分动态"};

	@Override
	public int getLayoutId() {
		return R.layout.activity_news;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		SystemBarHelper.immersiveStatusBar(this); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(this, new_toolbar);

		fragmentList.add(newsFragment.getInstance("平台公告"));
		fragmentList.add(creditNewsFragment.getInstance("积分动态"));

		viewPager.setAdapter(new ItemTitlePagerAdapter(getSupportFragmentManager(),
				fragmentList, new String[]{"平台公告", "积分动态"}));
		psts_tabs.setTabData(new String[]{"平台公告", "积分动态"});
		psts_tabs.setOnTabSelectListener(new OnTabSelectListener() {
			@Override
			public void onTabSelect(int position) {
				viewPager.setCurrentItem(position);
			}

			@Override
			public void onTabReselect(int position) {
				viewPager.setCurrentItem(position);
			}
		});
		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				psts_tabs.setCurrentTab(position);
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
		viewPager.setCurrentItem(0);

		viewPager.setOffscreenPageLimit(2);

	}

}
