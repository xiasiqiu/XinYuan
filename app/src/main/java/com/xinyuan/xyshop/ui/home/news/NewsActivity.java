package com.xinyuan.xyshop.ui.home.news;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.ItemTitlePagerAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;

import java.util.ArrayList;

import butterknife.BindView;
import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * Created by fx on 2017/7/5.
 * 首页新闻公告Activity
 */

public class NewsActivity extends BaseActivity {
	@BindView(R.id.stab_msg)
	SegmentTabLayout psts_tabs;
	@BindView(R.id.vp_content)
	ViewPager viewPager;
	@BindView(R.id.new_toolbar)
	Toolbar new_toolbar;
	@BindView(R.id.brand_btn_back)
	ImageView iv_header_left;
	@BindView(R.id.iv_header_right)
	BGABadgeImageView iv_header_right;

	private ArrayList<Fragment> fragmentList = new ArrayList<>();


	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.activity_news;
	}

	@Override
	public void initView() {
		SystemBarHelper.immersiveStatusBar(this); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(this, new_toolbar);
		CommUtil.initToolBar(this, iv_header_left, iv_header_right);
		fragmentList.add(new NewsFragment().getInstance("平台公告"));
		fragmentList.add(new CreditNewsFragment().getInstance("积分动态"));
		viewPager.setAdapter(new ItemTitlePagerAdapter(getSupportFragmentManager(),
				fragmentList, new String[]{"平台公告", "积分动态"}));
		psts_tabs.setTabData(new String[]{"平台公告", "积分动态"});
		viewPager.setCurrentItem(0);
		viewPager.setOffscreenPageLimit(2);

	}

	public void initListener() {
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
	}

	@Override
	public void initData() {

	}

	@Override
	public void onResume() {
		if (MyShopApplication.IsNewMsg) {
			iv_header_right.showCirclePointBadge();
		} else {
			iv_header_right.hiddenBadge();
		}
		super.onResume();
	}

}
