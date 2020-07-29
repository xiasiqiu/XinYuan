package com.xinyuan.xyshop.ui.mine;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.ItemTitlePagerAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.service.MsgService;
import com.xinyuan.xyshop.ui.mine.msg.MallMsgFragment;
import com.xinyuan.xyshop.ui.mine.msg.StoreMsgFragment;
import com.xinyuan.xyshop.util.SystemBarHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/5.
 */

public class MsgActivity extends BaseActivity<BasePresenter> {

	@BindView(R.id.stab_msg)
	SegmentTabLayout psts_tabs;
	@BindView(R.id.vp_content)
	ViewPager viewPager;
	private ArrayList<Fragment> fragmentList = new ArrayList<>();
	@BindView(R.id.msg_toolbar)
	Toolbar msg_toolbar;


	private StoreMsgFragment storeMsgFragment;
	private MallMsgFragment mallMsgFragment;


	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.activity_msg;
	}

	@Override
	public void initView() {
		SystemBarHelper.immersiveStatusBar(this); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(this, msg_toolbar);
	}

	@Override
	public void initData() {
		fragmentList.add(storeMsgFragment = new StoreMsgFragment());
		fragmentList.add(mallMsgFragment = new MallMsgFragment());
		viewPager.setAdapter(new ItemTitlePagerAdapter(getSupportFragmentManager(),
				fragmentList, new String[]{"客服消息", "系统通知"}));

		psts_tabs.setTabData(new String[]{"客服消息", "系统通知"});
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


	@Override
	protected void onDestroy() {
		super.onDestroy();
		MsgService.isrunning = true;
	}


	@OnClick(R.id.brand_btn_back)
	public void onBack() {
		finish();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
	}
}
