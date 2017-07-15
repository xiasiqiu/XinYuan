package com.xinyuan.xyshop.ui.goods.detail.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.SimpleEvaluateAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.entity.BuyData;
import com.xinyuan.xyshop.entity.PreGoods;
import com.xinyuan.xyshop.model.EvaluateModel;
import com.xinyuan.xyshop.model.GoodDetailModel;
import com.xinyuan.xyshop.model.GoodsAttrsBean;
import com.xinyuan.xyshop.model.TestModel;
import com.xinyuan.xyshop.mvp.contract.GoodsDetailContract;
import com.xinyuan.xyshop.ui.goods.GoodBusBean;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailsActivity;
import com.xinyuan.xyshop.ui.goods.store.StoreActivity;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.xinyuan.xyshop.widget.SlideDetailsLayout;
import com.xinyuan.xyshop.widget.dialog.GoodDetailsPromotionDialog;
import com.xinyuan.xyshop.widget.dialog.GoodDetailsSpecDialog;
import com.xinyuan.xyshop.widget.dialog.color.PromptDialog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;

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

public class GoodsInfoFragment extends BaseFragment implements SlideDetailsLayout.OnSlideDetailsListener, GoodsDetailContract.GoodsDetailView {


	@BindView(R.id.sv_switch)

	SlideDetailsLayout sv_switch;
	@BindView(R.id.sv_goods_info)

	ScrollView sv_goods_info;


	@BindView(R.id.goodinfo_banner)
	Banner banner;
	@BindView(R.id.goodinfo_share)
	ImageView iv_share; //分享按钮


	@BindView(R.id.tv_current_goods)
	TextView tv_current_goods;
	@BindView(R.id.tv_goods_location)
	TextView tv_goods_location;

	@BindView(R.id.tv_new_price)
	TextView tv_newprice; //商品最新价格
	@BindView(R.id.tv_old_price)
	TextView tv_oldprice; //商品旧价格

	@BindView(R.id.tv_goods_postage)
	TextView tv_goodspostage; //商品快递费用
	@BindView(R.id.tv_goods_sellnum)
	TextView tv_goodssellnum; //商品销量
	@BindView(R.id.tv_goods_talk)
	TextView tv_goodstalk; //商品评论


	@BindView(R.id.fl_stoer_sign)
	FlexboxLayout flexBoxLayout;


	@BindView(R.id.ll_current_goods)
	RelativeLayout ll_current; //规格型号布局
	@BindView(R.id.ll_comment)
	RelativeLayout ll_comment;//评论布局

	@BindView(R.id.rvEvaluate)
	RecyclerView rvEvaluate;

	@BindView(R.id.bt_good_store)
	Button bt_store; //店铺按钮

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

	@BindView(R.id.tv_comment_count)
	TextView tv_comment_count;

	@BindView(R.id.rl_group_info)
	RelativeLayout rl_group_info;
	@BindView(R.id.lodingView)
	XLoadingView lodingView;
	@BindView(R.id.tv_good_active)
	TextView tv_good_active;
	private Fragment nowFragment;

	GoodsDetailFragment goodsDetailFragment;
	GoodsConfigFragment goodsConfigFragment;
	GoodsServiceFragment goodsServiceFragment;

	private GoodsDetailContract.GoodsDetailPresenter presenter;


	private FragmentTransaction fragmentTransaction;
	private FragmentManager fragmentManager;

	private List<Fragment> fragmentList = new ArrayList<>();
	private List<TextView> tabTextList;

	public GoodDetailsActivity activity;
	public GoodsInfoWebFragment goodsInfoWebFragment;

	private HashMap<Integer, BuyData> buydatas;

	private HashMap<Integer, PreGoods> preGoodsMap;
	private int allGoodsNum;
	private GoodDetailModel detailModel;
	private static boolean VIEW_INIT = true;
	SimpleEvaluateAdapter simpleEvaluateAdapter;

	private static SlideDetailsLayout.Status status;

	private static String unit;


	private long mDay = 10;
	private long mHour = 12;
	private long mMin = 43;
	private long mSecond = 00;
	private boolean isRun = true;


	@Override
	public int getLayoutId() {
		return R.layout.fragment_good_info;
	}

	@Override
	public void initView() {
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


	}

	@Override
	public void initData(@Nullable Bundle savedInstanceState) {

		new TestModel.GoodsDetailPresenterImpl(this);
		presenter.initData(123);

		lodingView.setOnRetryClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				presenter.initData();
				lodingView.showLoading();
			}
		});
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


	@Override
	public void setPresenter(GoodsDetailContract.GoodsDetailPresenter presenter) {
		this.presenter = presenter;

	}

	@Override
	public void showState(int Sate) {
		switch (Sate) {
			case 0:
				lodingView.showLoading();
				break;
			case 1:
				lodingView.showContent();
				break;
			case 2:
				lodingView.showError();
				break;

		}

	}


	@Override
	public void showView(GoodDetailModel model) {

		this.detailModel = model;
		EventBus.getDefault().post(new GoodBusBean(GoodBusBean.GoodEvaluate, model.getGoodComment()));
		showBanner();
		showGoodsInfo();
		showEva();
		showStoreInfo();
		showState(1);

	}

	@Override
	public void showBanner() {

		ArrayList<String> titles = new ArrayList<>();
		ArrayList<String> images = new ArrayList<>();

		for (GoodDetailModel.GoodBanner banner : detailModel.getGoodBanner()) {
			titles.add(banner.getImgTxt());
			images.add(banner.getImgUrl());
		}
		banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
				.setImageLoader(new GlideImageLoader())
				.setImages(images)
				.setBannerTitles(titles)
				.setBannerAnimation(Transformer.DepthPage)
				.isAutoPlay(true)
				.setDelayTime(3000)
				.setIndicatorGravity(BannerConfig.CENTER)
				.start();


	}

	private int goodsType;
	private int goodsActive;

	@Override
	public void showGoodsInfo() {
		//如果商品为虚拟商品
		XLog.v("type:" + detailModel.getGoodsType() + "Active:" + detailModel.getGoodsActive());

		//如果商品为团购活动

		if (!XEmptyUtils.isEmpty(GoodDetailsActivity.goodType)) {
			goodsType = GoodDetailsActivity.GOODTYPE;
			goodsActive = GoodDetailsActivity.GOODACTIVE;
		} else {
			goodsType = detailModel.getGoodsType();
			goodsActive = detailModel.getGoodsActive();
		}


		switch (goodsType) {
			case 1:
				tv_newprice.setText("￥" + detailModel.getActualPrice());
				tv_oldprice.setText("￥" + detailModel.getOldPrice());
				tv_oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
				if (detailModel.getExpressCost() == 0) {
					tv_goodspostage.setText("快递:免邮费");
				} else {
					tv_goodspostage.setText("快递:" + detailModel.getExpressCost());
				}
				tv_goodssellnum.setText("月销量:" + detailModel.getSalesVolume() + "笔");
				tv_goodstalk.setText("评论:" + detailModel.getGoodComment().getTotalCount());
				this.unit = detailModel.getUnit();
				List<String> storeSign = new ArrayList<>();
				storeSign.addAll(detailModel.getShopServer());
				tv_goods_location.setText(detailModel.getDeliveryAddress());
				String dis = detailModel.getSalesPromotion().toString();
				for (String type : storeSign) {
					AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.view_good_sign);
					addViewHolder.setText(R.id.tv_sign_type, type);
					flexBoxLayout.addView(addViewHolder.getCustomView());
				}
				storeSign.clear();
				break;
			case 2:
				tv_goods_location.setVisibility(View.GONE);
				ll_current.setVisibility(View.GONE);
				tv_good_active.setVisibility(View.VISIBLE);
				tv_goodspostage.setVisibility(View.GONE);
				flexBoxLayout.setVisibility(View.GONE);
				tv_newprice.setText("￥" + detailModel.getActualPrice());
				tv_oldprice.setText("￥" + detailModel.getOldPrice());
				tv_oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
				tv_goodssellnum.setText("月销量:" + detailModel.getSalesVolume() + "笔");
				tv_goodstalk.setText("评论:" + detailModel.getGoodComment().getTotalCount());
				break;
		}

		if (goodsActive == 1) {
			rl_group_info.setVisibility(View.VISIBLE);
			startRun();
		} else if (goodsActive == 2) {
			tv_goods_location.setVisibility(View.GONE);
			ll_current.setVisibility(View.GONE);
			tv_good_active.setVisibility(View.VISIBLE);
			tv_good_active.setText("积分兑换");
			tv_goodspostage.setVisibility(View.GONE);
			flexBoxLayout.setVisibility(View.GONE);

			tv_newprice.setText(detailModel.getActualPrice() + "积分");
			tv_oldprice.setText(detailModel.getOldPrice() + "积分");
			tv_oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
			tv_goodssellnum.setText("月销量:" + detailModel.getSalesVolume() + "笔");
			tv_goodstalk.setText("评论:" + detailModel.getGoodComment().getTotalCount());
		}


	}


	@Override
	public void showEva() {


		tv_comment_count.setText("评价(" + detailModel.getGoodComment().getTotalCount() + ")");


		List<EvaluateModel.EvaluateBean> data = new ArrayList<>();

		data.addAll(detailModel.getGoodComment().getList());

		this.simpleEvaluateAdapter = new SimpleEvaluateAdapter(R.layout.fragment_good_item_evaluate, data);


		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
		rvEvaluate.setNestedScrollingEnabled(false);
		//设置布局管理器
		rvEvaluate.setLayoutManager(linearLayoutManager);
		this.rvEvaluate.setAdapter(this.simpleEvaluateAdapter);
		this.simpleEvaluateAdapter.notifyDataSetChanged();


	}

	@Override
	public void showStoreInfo() {
		EventBus.getDefault().post(new GoodBusBean(GoodBusBean.GoodsStoreId, String.valueOf(detailModel.getShopInfo().getShopId())));
		GlideImageLoader.setImage(context, detailModel.getShopInfo().getShopLogo(), srote_img);
		tv_good_storename.setText(detailModel.getShopInfo().getName());
		tv_StoreDescPoint.setText(String.valueOf(detailModel.getShopInfo().getGoodsScore()));
		tv_StoreServicePoint.setText(String.valueOf(detailModel.getShopInfo().getServerScore()));
		tv_StoreDeliveryPoint.setText(String.valueOf(detailModel.getShopInfo().getServerScore()));
	}


	@Override
	public void showWeb() {

	}


	private void showSelectPromoDialog() {

		GoodDetailsPromotionDialog dialog = new GoodDetailsPromotionDialog(context, detailModel.getSalesPromotion());
		Window dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.BOTTOM);
		dialog.show();
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		dialogWindow.setLayout(dm.widthPixels, dialogWindow.getAttributes().height);
	}


	private void showSelectSpecDialog() {
		GoodDetailsSpecDialog dialog = new GoodDetailsSpecDialog(context, detailModel.getGoodSpec());
		Window dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.BOTTOM);
		dialog.show();
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		dialogWindow.setLayout(dm.widthPixels, dialogWindow.getAttributes().height);
	}


	@OnClick({R.id.ll_goods_config, R.id.ll_goods_service, R.id.ll_goods_detail, R.id.fab_up_slide, R.id.ll_comment, R.id.bt_good_store})
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


				EventBus.getDefault().post(new GoodBusBean(GoodBusBean.SelectedEvaluate));
				break;
			case R.id.bt_good_store:
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


	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		activity = (GoodDetailsActivity) context;
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	//第2步:注册一个在后台线程执行的方法,用于接收事件
	public void onGoodEvent(GoodBusBean goodBusBean) {//参数必须是ClassEvent类型, 否则不会调用此方法
		if (goodBusBean.getFlag().equals(GoodBusBean.addShopCar)) {
			boolean isAdd = false;
			isAdd = (boolean) goodBusBean.getObj();
			if (isAdd) {
				new PromptDialog(getContext())
						.setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS)
						.setAnimationEnable(true)
						.setContentText("已加入购物车")
						.setPositiveListener("确认", new PromptDialog.OnPositiveListener() {
							@Override
							public void onClick(PromptDialog dialog) {
								dialog.dismiss();
							}
						}).show();

			}
		} else if (goodBusBean.getFlag().equals(GoodBusBean.SelectedSpec)) {
			showSelectSpecDialog();

		} else if (goodBusBean.getFlag().equals(GoodBusBean.SelectedGoods)) {
			GoodsAttrsBean.StockGoodsBean bean = (GoodsAttrsBean.StockGoodsBean) goodBusBean.getObj();
			tv_newprice.setText("￥" + bean.getPrice());
			tv_current_goods.setText("已选择" + bean.getSpecText());
		}

	}

	@BindView(R.id.tv_good_second)
	TextView tv_good_second;
	@BindView(R.id.tv_good_min)
	TextView tv_good_min;
	@BindView(R.id.tv_good_hour)
	TextView tv_good_hour;
	@BindView(R.id.tv_good_day)
	TextView tv_good_day;

	/**
	 * 开启倒计时
	 */
	private void startRun() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (isRun) {
					try {
						Thread.sleep(1000); // sleep 1000ms
						Message message = Message.obtain();
						message.what = 1;
						timeHandler.sendMessage(message);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}


	private Handler timeHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 1) {
				computeTime();
				if (isRun) {
					tv_good_day.setText(mDay + "");
					tv_good_hour.setText(mHour + "");
					tv_good_min.setText(mMin + "");
					tv_good_second.setText(mSecond + "");
				}

			}
		}
	};

	private void computeTime() {
		mSecond--;
		if (mSecond < 0) {
			mMin--;
			mSecond = 59;
			if (mMin < 0) {
				mMin = 59;
				mHour--;
				if (mHour < 0) {
					// 倒计时结束
					mHour = 23;
					mDay--;
				}
			}
		}
	}

	@Override
	public void onDestroyView() {
		XLog.v("商品详情销毁了");
		isRun = false;
		super.onDestroyView();


	}

}
