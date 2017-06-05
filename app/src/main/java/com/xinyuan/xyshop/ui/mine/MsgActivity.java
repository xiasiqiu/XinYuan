package com.xinyuan.xyshop.ui.mine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.gxz.PagerSlidingTabStrip;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.ItemTitlePagerAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.ui.goods.store.StoreAllGoodFragment;
import com.xinyuan.xyshop.ui.goods.store.StoreHomeFragment;
import com.xinyuan.xyshop.ui.mine.msg.MallMsgFragment;
import com.xinyuan.xyshop.ui.mine.msg.StoreMsgFragment;
import com.xinyuan.xyshop.util.SystemBarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/5.
 */

public class MsgActivity extends BaseActivity {

	@BindView(R.id.msg__tabs)
	PagerSlidingTabStrip psts_tabs;
	@BindView(R.id.vp_content)
	ViewPager viewPager;
	private List<Fragment> fragmentList = new ArrayList<>();
	@BindView(R.id.msg_toolbar)
	Toolbar msg_toolbar;

	private StoreMsgFragment storeMsgFragment;
	private MallMsgFragment mallMsgFragment;

	@Override
	public int getLayoutId() {
		return R.layout.activity_msg;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		ButterKnife.bind(this);

		SystemBarHelper.immersiveStatusBar(this); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(this, msg_toolbar);
		fragmentList.add(storeMsgFragment = new StoreMsgFragment());
		fragmentList.add(mallMsgFragment = new MallMsgFragment());

		viewPager.setAdapter(new ItemTitlePagerAdapter(getSupportFragmentManager(),
				fragmentList, new String[]{"客服消息", "系统通知"}));
		viewPager.setOffscreenPageLimit(2);
		psts_tabs.setViewPager(viewPager);
	}
}
