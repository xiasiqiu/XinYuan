package com.xinyuan.xyshop.ui.goods.detail;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.ItemTitlePagerAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.entity.PreGoods;
import com.xinyuan.xyshop.entity.TabEntity;
import com.xinyuan.xyshop.ui.buy.ConfirmOrderActivity;
import com.xinyuan.xyshop.ui.goods.GoodBusBean;

import com.xinyuan.xyshop.ui.goods.detail.fragment.GoodsCommentFragment;
import com.xinyuan.xyshop.ui.goods.detail.fragment.GoodsDetailFragment;
import com.xinyuan.xyshop.ui.goods.detail.fragment.GoodsInfoFragment;
import com.xinyuan.xyshop.ui.goods.detail.fragment.GoodsRecommFragment;
import com.xinyuan.xyshop.ui.goods.store.StoreActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.widget.NoScrollViewPager;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class GoodDetailsActivity extends BaseActivity {


	@BindView(R.id.tl_main)
	public SlidingTabLayout mTlMain;
	@BindView(R.id.vp_content)
	public NoScrollViewPager vp_content;
	@BindView(R.id.tv_title)
	public TextView tv_title;

	@BindView(R.id.cb_store_fv)
	CheckBox cb_store_fv;
	@BindView(R.id.bt_store)
	Button bt_store;
	@BindView(R.id.bt_service)
	Button bt_service;

	private ArrayList<Fragment> fragmentList = new ArrayList<>();

	private GoodsInfoFragment goodsInfoFragment;
	private GoodsDetailFragment goodsDetailFragment;
	private GoodsCommentFragment goodsCommentFragment;


	private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
	private String[] mTitles = {"商品", "详情", "评价"};
	private static int commonId;

	private HashMap<Integer, PreGoods> preGoodsMap;
	public static String COMMONID = "commonId";
	public static String GOODID = "goodsId";
	public static String TRYSTYPE = "trysType";
	private static int trysType;


	public static int totalCount;
	public static int goodAssess;
	public static int normalAssess;
	public static int lowAssess;
	public static int blueprint;

	public static String goodType;
	public static int GOODTYPE;
	public static int GOODACTIVE;

	@Override
	public int getLayoutId() {
		return R.layout.activity_good_details;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		this.commonId = getIntent().getIntExtra(COMMONID, 0);
		this.commonId = getIntent().getIntExtra(GOODID, 0);
		this.trysType = getIntent().getIntExtra(TRYSTYPE, 0);
		this.preGoodsMap = new HashMap();
		Intent intent = getIntent();
		goodType = intent.getStringExtra("GoodType");
		if (!XEmptyUtils.isEmpty(goodType)) {
			String[] s = goodType.split(":");
			GOODTYPE = Integer.parseInt(s[0]);
			GOODACTIVE = Integer.parseInt(s[1]);
			XLog.v("商品类型===" + GOODTYPE + ":" + GOODACTIVE);
		}

	}

	@Override
	public void initView() {
		EventBus.getDefault().register(this);
		for (int i = 0; i < mTitles.length; i++) {
			mTabEntities.add(new TabEntity(mTitles[i]));
		}

		fragmentList.add(goodsInfoFragment = new GoodsInfoFragment());
		fragmentList.add(goodsDetailFragment = new GoodsDetailFragment());
		fragmentList.add(goodsCommentFragment = new GoodsCommentFragment());


		vp_content.setAdapter(new ItemTitlePagerAdapter(getSupportFragmentManager(),
				fragmentList, new String[]{"商品", "详情", "评价"}));
		vp_content.setOffscreenPageLimit(4);
		mTlMain.setViewPager(vp_content);


		cb_store_fv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
				if (b) {
					cb_store_fv.setText("已收藏");
					cb_store_fv.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
					XToast.info("已收藏！");
				} else {
					cb_store_fv.setText("收藏");
					cb_store_fv.setTextColor(getResources().getColor(R.color.tv_name));
					XToast.normal("取消收藏！");
				}
			}
		});

	}

	String storeId = "";

	@Subscribe(threadMode = ThreadMode.MAIN)
	//第2步:注册一个在后台线程执行的方法,用于接收事件
	public void onGoodEvent(GoodBusBean goodBusBean) {//参数必须是ClassEvent类型, 否则不会调用此方法
		if (goodBusBean.getFlag().equals(GoodBusBean.SelectedEvaluate)) {
			vp_content.setCurrentItem(2);
		} else if (goodBusBean.getFlag().equals(GoodBusBean.GoodsStoreId)) {
			storeId = (String) goodBusBean.getObj();
		}

	}

	@OnClick({R.id.bt_store, R.id.bt_service, R.id.bt_goos_buy, R.id.bt_add_car})
	public void OnClick(View v) {
		v.getId();
		switch (v.getId()) {
			case R.id.bt_store:
				Map<String, String> params = new HashMap();
				params.put("storeId", storeId);
				CommUtil.gotoActivity(this, StoreActivity.class, false, params);
				break;
			case R.id.bt_service:
				break;
			case R.id.bt_goos_buy:
				CommUtil.gotoActivity(this, ConfirmOrderActivity.class, false, null);
				break;
			case R.id.bt_add_car:
				EventBus.getDefault().post(new GoodBusBean(GoodBusBean.SelectedSpec));
		}

	}

	@Override
	@CallSuper
	protected void onStop() {
		EventBus.getDefault().unregister(this);
		super.onStop();
	}
}
