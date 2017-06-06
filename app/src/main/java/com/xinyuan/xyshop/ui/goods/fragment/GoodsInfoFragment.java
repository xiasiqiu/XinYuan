package com.xinyuan.xyshop.ui.goods.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.SimpleEvaluateAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.entity.BuyData;
import com.xinyuan.xyshop.entity.GoodDetailVo;
import com.xinyuan.xyshop.entity.Goods;
import com.xinyuan.xyshop.entity.GoodsEvaluate;
import com.xinyuan.xyshop.entity.PreGoods;
import com.xinyuan.xyshop.ui.goods.GoodBusBean;
import com.xinyuan.xyshop.ui.goods.GoodDetailsActivity;
import com.xinyuan.xyshop.ui.goods.StoreActivity;
import com.xinyuan.xyshop.util.FullyLinearLayoutManager;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.xinyuan.xyshop.widget.SlideDetailsLayout;
import com.xinyuan.xyshop.widget.dialog.GoodDetailsPromotionDialog;
import com.xinyuan.xyshop.widget.dialog.GoodDetailsSpecDialog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.xframe.utils.log.XLog;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/18.
 */

public class GoodsInfoFragment extends BaseFragment implements SlideDetailsLayout.OnSlideDetailsListener {


	@BindView(R.id.sv_switch)

	SlideDetailsLayout sv_switch;
	@BindView(R.id.sv_goods_info)

	ScrollView sv_goods_info;


	@BindView(R.id.goodinfo_banner)
	Banner banner;
	@BindView(R.id.goodinfo_share)
	ImageView iv_share;


	@BindView(R.id.tv_new_price)
	TextView tv_newprice;
	@BindView(R.id.tv_old_price)
	TextView tv_oldprice;

	@BindView(R.id.tv_goods_postage)
	TextView tv_goodspostage;
	@BindView(R.id.tv_goods_sellnum)
	TextView tv_goodssellnum;
	@BindView(R.id.tv_goods_talk)
	TextView tv_goodstalk;


	@BindView(R.id.llGoodDiscount)
	AutoRelativeLayout ll_discount;
	@BindView(R.id.ll_current_goods)
	AutoRelativeLayout ll_current;
	@BindView(R.id.ll_comment)
	AutoRelativeLayout ll_comment;

	@BindView(R.id.rvEvaluate)
	RecyclerView rvEvaluate;

	@BindView(R.id.iv_good_store)
	Button bt_store;

	@BindView(R.id.fab_up_slide)
	FloatingActionButton fab_up_slide;
	@BindView(R.id.iv_good_store_img)
	ImageView srote_img;
	@BindView(R.id.tv_good_store_name)
	TextView tv_good_storename;
	@BindView(R.id.tvStoreDescPoint)
	TextView tv_StoreDescPoint;
	@BindView(R.id.tvStoreServicePoint)
	TextView tv_StoreServicePoint;
	@BindView(R.id.tvStoreDeliveryPoint)
	TextView tv_StoreDeliveryPoint;


	//上拉加载详情
	@BindView(R.id.ll_pull_up)
	LinearLayout ll_pull_up;


	//商品详情tab-详情
	@BindView(R.id.ll_goods_detail)
	LinearLayout ll_goods_detail;
	//商品详情tab-规格
	@BindView(R.id.ll_goods_config)
	LinearLayout ll_goods_config;
	//商品详情tab-售后
	@BindView(R.id.ll_goods_service)
	LinearLayout ll_goods_service;


	//商品详情tab-详情文字
	@BindView(R.id.tv_goods_detail)
	TextView tv_goods_detail;
	//商品详情tab-规格文字
	@BindView(R.id.tv_goods_config)
	TextView tv_goods_config;
	//商品详情tab-售后文字
	@BindView(R.id.tv_goods_service)
	TextView tv_goods_service;


	@BindView(R.id.fl_content)
	FrameLayout fl_content;


	private Fragment nowFragment;

	GoodsDetailFragment goodsDetailFragment;
	GoodsConfigFragment goodsConfigFragment;
	GoodsServiceFragment goodsServiceFragment;


	private FragmentTransaction fragmentTransaction;
	private FragmentManager fragmentManager;

	private List<Fragment> fragmentList = new ArrayList<>();
	private List<TextView> tabTextList;

	public GoodDetailsActivity activity;
	public GoodsInfoWebFragment goodsInfoWebFragment;

	@Override
	public int getLayoutId() {
		return R.layout.fragment_good_info;
	}

	@Override
	public void initData(@Nullable Bundle savedInstanceState) {
		fragmentList = new ArrayList<>();
		tabTextList = new ArrayList<>();
		tabTextList.add(tv_goods_detail);
		tabTextList.add(tv_goods_config);
		tabTextList.add(tv_goods_service);
		goodsServiceFragment = new GoodsServiceFragment();
		goodsConfigFragment = new GoodsConfigFragment();
		goodsDetailFragment = new GoodsDetailFragment();
		goodsInfoWebFragment = new GoodsInfoWebFragment();
		fragmentList.add(goodsConfigFragment);
		fragmentList.add(goodsServiceFragment);
		fragmentList.add(goodsDetailFragment);
		fragmentList.add(goodsInfoWebFragment);


		nowFragment = goodsInfoWebFragment;

		fragmentManager = getChildFragmentManager();
		//默认显示商品详情tab
		fragmentManager.beginTransaction().replace(R.id.fl_content, nowFragment).commitAllowingStateLoss();
		tv_goods_detail.setTextColor(getResources().getColor(R.color.colorPrimary));
	}


	@Override
	public void initView() {
		EventBus.getDefault().register(this);
		getView().setFocusable(true);
		getView().setFocusableInTouchMode(true);
		getView().setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					if (status == SlideDetailsLayout.Status.OPEN) {
						sv_goods_info.smoothScrollTo(0, 0);
						sv_switch.smoothClose(true);
						return true;
					} else {
						getActivity().onBackPressed();
					}

				}
				return false;
			}
		});

		sv_switch.setOnSlideDetailsListener(this);
		fab_up_slide.hide();
		ArrayList<String> titles = new ArrayList<>();
		titles.add("妃子笑荔枝新鲜上市！！！");
		titles.add("新人专享套餐");
		titles.add("小龙虾，我们走！");


		ArrayList<String> images = new ArrayList<>();
		images.add("https://imgqn2.fruitday.com/images/2017-05-08/3939a1d9231eb02dd507744befbb2823.jpg");
		images.add("https://imgqn2.fruitday.com/images/2017-03-03/6b24de7a7a42699abd07bb812c2d465e.jpg");
		images.add("https://imgqn2.fruitday.com/images/2017-05-07/6314f8e67de76b26bd528297db56f8e7.jpg");

		banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
				.setImageLoader(new GlideImageLoader())
				.setImages(images)
				.setBannerTitles(titles)
				.setBannerAnimation(Transformer.DepthPage)
				.isAutoPlay(true)
				.setDelayTime(3000)
				.setIndicatorGravity(BannerConfig.CENTER)
				.start();

		List<GoodsEvaluate> data = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			data.add(new GoodsEvaluate());
		}
		XLog.list(data);


		this.simpleEvaluateAdapter = new SimpleEvaluateAdapter(R.layout.evaluate_item_simple, data);


		FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(context);
		rvEvaluate.setNestedScrollingEnabled(false);
		//设置布局管理器
		rvEvaluate.setLayoutManager(linearLayoutManager);
		this.rvEvaluate.setAdapter(this.simpleEvaluateAdapter);
		this.simpleEvaluateAdapter.notifyDataSetChanged();
	}



	@Subscribe(threadMode = ThreadMode.MAIN) //第2步:注册一个在后台线程执行的方法,用于接收事件
	public void onUserEvent(GoodBusBean event) {//参数必须是ClassEvent类型, 否则不会调用此方法
	}



	SimpleEvaluateAdapter simpleEvaluateAdapter;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		activity = (GoodDetailsActivity) context;
	}


	@OnClick({R.id.ll_goods_config, R.id.ll_goods_service, R.id.ll_goods_detail, R.id.fab_up_slide, R.id.ll_comment, R.id.iv_good_store})
	public void onClick(View view) {
		switch (view.getId()) {

			case R.id.ll_pull_up:
				sv_switch.smoothOpen(true);
				break;
			case R.id.ll_goods_detail:
				switchFragment(nowFragment, goodsInfoWebFragment);
				nowFragment = goodsDetailFragment;
				tv_goods_detail.setTextColor(getResources().getColor(R.color.colorPrimary));
				tv_goods_config.setTextColor(getResources().getColor(R.color.tv_hint));
				tv_goods_service.setTextColor(getResources().getColor(R.color.tv_hint));
				break;
			case R.id.ll_goods_config:
				switchFragment(nowFragment, goodsConfigFragment);
				nowFragment = goodsConfigFragment;
				tv_goods_detail.setTextColor(getResources().getColor(R.color.tv_hint));
				tv_goods_config.setTextColor(getResources().getColor(R.color.colorPrimary));
				tv_goods_service.setTextColor(getResources().getColor(R.color.tv_hint));
				break;
			case R.id.ll_goods_service:
				switchFragment(nowFragment, goodsServiceFragment);
				nowFragment = goodsServiceFragment;
				tv_goods_detail.setTextColor(getResources().getColor(R.color.tv_hint));
				tv_goods_config.setTextColor(getResources().getColor(R.color.tv_hint));
				tv_goods_service.setTextColor(getResources().getColor(R.color.colorPrimary));
				break;
			case R.id.fab_up_slide:
				sv_goods_info.smoothScrollTo(0, 0);
				sv_switch.smoothClose(true);

				break;
			case R.id.ll_comment:
				EventBus.getDefault().post(new GoodBusBean(GoodBusBean.GoodEvaluate));
				break;
			case R.id.iv_good_store:
				Intent intent = new Intent(getActivity(), StoreActivity.class);
				startActivity(intent);
				break;
			default:
				break;
		}
	}

	private void switchFragment(Fragment fromFragment, Fragment toFragment) {
		if (nowFragment != toFragment) {
			fragmentTransaction = fragmentManager.beginTransaction();
			if (!toFragment.isAdded()) {    // 先判断是否被add过
				fragmentTransaction.hide(fromFragment).add(R.id.fl_content, toFragment).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到activity中
			} else {
				fragmentTransaction.hide(fromFragment).show(toFragment).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
			}
		}
	}

	private static SlideDetailsLayout.Status status;

	@Override
	public void onStatucChanged(SlideDetailsLayout.Status status) {
		if (status == SlideDetailsLayout.Status.OPEN) {
			this.status = status;
			//当前为图文详情页
			fab_up_slide.show();
			activity.vp_content.setNoScroll(true);
			activity.tv_title.setVisibility(View.VISIBLE);
			activity.mTlMain.setVisibility(View.GONE);
		} else {
			this.status = status;
			//当前为商品详情页
			fab_up_slide.hide();
			activity.vp_content.setNoScroll(false);
			activity.tv_title.setVisibility(View.GONE);
			activity.mTlMain.setVisibility(View.VISIBLE);
		}
	}

	@OnClick(R.id.ll_current_goods)
	public void onSpecChooseClick() {

		showSelectSpecDialog();

	}

	@OnClick(R.id.llGoodDiscount)
	public void onPromChooseClick() {

		showSelectPromoDialog();

	}

	private HashMap<Integer, BuyData> buydatas;
	private GoodDetailVo goodDetail;
	private Goods selectedGoods;
	private HashMap<Integer, PreGoods> preGoodsMap;
	private int allGoodsNum;

	private void showSelectSpecDialog() {
		GoodDetailsSpecDialog dialog = new GoodDetailsSpecDialog(this.context, goodDetail, preGoodsMap, selectedGoods, allGoodsNum);
		Window dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.BOTTOM);
		dialog.show();
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		dialogWindow.setLayout(dm.widthPixels, dialogWindow.getAttributes().height);
	}

	private void showSelectPromoDialog() {
		GoodDetailsPromotionDialog dialog = new GoodDetailsPromotionDialog(this.context, goodDetail, preGoodsMap, selectedGoods, allGoodsNum);
		Window dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.BOTTOM);
		dialog.show();
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		dialogWindow.setLayout(dm.widthPixels, dialogWindow.getAttributes().height);
	}

}
