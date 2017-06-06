package com.xinyuan.xyshop.ui.goods;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.gxz.PagerSlidingTabStrip;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.ItemTitlePagerAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.ui.goods.store.StoreActivityFragment;
import com.xinyuan.xyshop.ui.goods.store.StoreAllGoodFragment;
import com.xinyuan.xyshop.ui.goods.store.StoreHomeFragment;
import com.xinyuan.xyshop.ui.goods.store.StoreIntroduceActivity;
import com.xinyuan.xyshop.ui.goods.store.StoreNewGoodFragment;
import com.xinyuan.xyshop.ui.goods.store.StoreVoucherDialog;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.NoScrollViewPager;
import com.xinyuan.xyshop.widget.dialog.GoodDetailsPromotionDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/31.
 */

public class StoreActivity extends BaseActivity {

	@BindView(R.id.store__tabs)
	public SlidingTabLayout psts_tabs;
	@BindView(R.id.vp_content)
	public ViewPager vp_content;
	private List<Fragment> fragmentList = new ArrayList<>();
	@BindView(R.id.store_toolbar)
	Toolbar store_toolbar;

	@BindView(R.id.collapse_toolbar)
	CollapsingToolbarLayout collapsingToolbar;

	@BindView(R.id.tv_store_introduce)
	TextView tv_store_introduce;
	@BindView(R.id.tv_store_voucher)
	TextView tv_store_voucher;
	@BindView(R.id.tv_store_service)
	TextView tv_store_service;

	private StoreHomeFragment homeFragment;
	private StoreAllGoodFragment allGoodFragment;
	private StoreNewGoodFragment newGoodFragment;
	private StoreActivityFragment activityFragment;


	@Override
	public int getLayoutId() {
		return R.layout.activity_stores;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}


	@Override
	public void initView() {

		setSupportActionBar(store_toolbar);
		collapsingToolbar.setTitleEnabled(false);

		SystemBarHelper.immersiveStatusBar(this); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(this, store_toolbar);

		fragmentList.add(homeFragment = new StoreHomeFragment());
		fragmentList.add(allGoodFragment = new StoreAllGoodFragment());
		fragmentList.add(newGoodFragment = new StoreNewGoodFragment());
		fragmentList.add(activityFragment = new StoreActivityFragment());

		vp_content.setAdapter(new ItemTitlePagerAdapter(getSupportFragmentManager(),
				fragmentList, new String[]{"店铺首页", "全部商品", "商品上新", "店铺活动"}));
		vp_content.setOffscreenPageLimit(4);
		psts_tabs.setViewPager(vp_content);
	}


	@OnClick({R.id.tv_store_introduce, R.id.tv_store_voucher, R.id.tv_store_service})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.tv_store_introduce:
				CommUtil.gotoActivity(this, StoreIntroduceActivity.class, false, null);
				break;
			case R.id.tv_store_voucher:
				showSelectPromoDialog();
				break;
			case R.id.tv_store_service:
				CommUtil.gotoActivity(this, StoreIntroduceActivity.class, false, null);
				break;

		}

	}

	private void showSelectPromoDialog() {
		StoreVoucherDialog dialog = new StoreVoucherDialog(this);
		Window dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.BOTTOM);
		dialog.show();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		dialogWindow.setLayout(dm.widthPixels, dialogWindow.getAttributes().height);
	}
}
