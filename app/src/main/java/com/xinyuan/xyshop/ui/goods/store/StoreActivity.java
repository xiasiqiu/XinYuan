package com.xinyuan.xyshop.ui.goods.store;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.google.android.flexbox.FlexboxLayout;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.ItemTitlePagerAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.entity.StoreInfoBean;
import com.xinyuan.xyshop.even.LoginPageEvent;
import com.xinyuan.xyshop.ui.goods.store.fragment.StoreActivityFragment;
import com.xinyuan.xyshop.ui.goods.store.fragment.StoreAllGoodFragment;
import com.xinyuan.xyshop.ui.goods.store.fragment.StoreHomeFragment;
import com.xinyuan.xyshop.ui.goods.store.fragment.StoreNewGoodFragment;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.dialog.StoreVoucherDialog;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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


	@BindView(R.id.iv_store_bg)
	ImageView iv_store_bg;
	@BindView(R.id.iv_store_logo)
	ImageView iv_store_logo;
	@BindView(R.id.tv_store_name)
	TextView tv_store_namel;
	@BindView(R.id.tv_store_fans)
	TextView tv_store_fans;
	@BindView(R.id.bt_store_fav)
	CheckBox bt_store_fav;
	@BindView(R.id.fl_store_sign)
	FlexboxLayout flexBoxLayout;


	private StoreHomeFragment homeFragment;
	private StoreAllGoodFragment allGoodFragment;
	private StoreNewGoodFragment newGoodFragment;
	private StoreActivityFragment activityFragment;

	public static int state;

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

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onEvent(StoreInfoBean event) {
		if (!XEmptyUtils.isEmpty(event)) {

			GlideImageLoader.setImage(StoreActivity.this, event.getBannerBg(), iv_store_bg);
			GlideImageLoader.setImage(StoreActivity.this, event.getStoreLogo(), iv_store_logo);
			tv_store_namel.setText(event.getStorename());
			tv_store_fans.setText(event.getFansNum());
			if (event.isFollowStatus() == 1) {
				bt_store_fav.setText("已关注");
			}

			List<String> list = new ArrayList<>();
			list.addAll(event.getStoresign());

			AddViewHolder addViewHolder = new AddViewHolder(this, R.layout.view_store_sign);
			ImageView ivImg = addViewHolder.getView(R.id.ivImg);
			GlideImageLoader.setImage(this, CommUtil.getStoreSign(this, event.getStoreLevel()), ivImg);
			flexBoxLayout.addView(addViewHolder.getCustomView());
			if (!XEmptyUtils.isEmpty(event.getStoresign())) {
				for (String type : event.getStoresign()) {

					AddViewHolder addViewHolder1 = new AddViewHolder(this, R.layout.view_store_sign);
					ImageView ivImg1 = addViewHolder1.getView(R.id.ivImg);
					GlideImageLoader.setImage(this, type, ivImg1);
					flexBoxLayout.addView(addViewHolder1.getCustomView());
				}
			}

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
}
