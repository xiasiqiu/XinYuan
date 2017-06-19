package com.xinyuan.xyshop.ui.goods;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.ItemTitlePagerAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.entity.PreGoods;
import com.xinyuan.xyshop.entity.TabEntity;
import com.xinyuan.xyshop.model.GoodDetail;
import com.xinyuan.xyshop.ui.buy.ConfirmOrderActivity;
import com.xinyuan.xyshop.ui.goods.fragment.GoodsCommentFragment;
import com.xinyuan.xyshop.ui.goods.fragment.GoodsDetailFragment;
import com.xinyuan.xyshop.ui.goods.fragment.GoodsInfoFragment;
import com.xinyuan.xyshop.ui.goods.fragment.GoodsRecommFragment;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.widget.NoScrollViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;

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
	private GoodsRecommFragment goodsRecommFragment;

	private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
	private String[] mTitles = {"商品", "详情", "评价", "推荐"};
	private static int commonId;

	private HashMap<Integer, PreGoods> preGoodsMap;
	public static String COMMONID = "commonId";
	public static String GOODID = "goodsId";
	public static String TRYSTYPE = "trysType";

	private static int trysType;

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
		fragmentList.add(goodsRecommFragment = new GoodsRecommFragment());

		vp_content.setAdapter(new ItemTitlePagerAdapter(getSupportFragmentManager(),
				fragmentList, new String[]{"商品", "详情", "评价", "推荐"}));
		vp_content.setOffscreenPageLimit(1);
		mTlMain.setViewPager(vp_content);


		cb_store_fv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
				if (b) {
					Drawable drawable = getResources().getDrawable(R.drawable.ic_store_fav);
					drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
					cb_store_fv.setCompoundDrawables(null, drawable, null, null);
					cb_store_fv.setText("已收藏");

				} else {
					Drawable drawable = getResources().getDrawable(R.drawable.ic_store_fav_de);
					drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
					cb_store_fv.setBackground(getResources().getDrawable(R.drawable.ic_store_fav_de));
					cb_store_fv.setText("收藏");
				}
			}
		});

	}

	String storeId = "";

	@Subscribe(threadMode = ThreadMode.MAIN)
	//第2步:注册一个在后台线程执行的方法,用于接收事件
	public void onUserEvent(GoodBusBean goodBusBean) {//参数必须是ClassEvent类型, 否则不会调用此方法
		if (goodBusBean.getFlag().equals(GoodBusBean.GoodEvaluate)) {
			vp_content.setCurrentItem(2);
		} else if (goodBusBean.getFlag().equals(GoodBusBean.GoodsStoreId)) {
			storeId = (String) goodBusBean.getObj();
		}

	}

	@OnClick({R.id.bt_store, R.id.bt_service, R.id.bt_goos_buy})
	public void OnClick(View v) {
		v.getId();
		switch (v.getId()) {
			case R.id.bt_store:
				if (!storeId.equals("")) {
					Intent intent = new Intent(this, StoreActivity.class);
					intent.putExtra("storeId", storeId);
					startActivity(intent);
				}
				break;
			case R.id.bt_service:
				break;
			case R.id.bt_goos_buy:
				Intent intent = new Intent(this, ConfirmOrderActivity.class);
				startActivity(intent);
				break;
		}


	}
}
