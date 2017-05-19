package com.xinyuan.xyshop.ui.goods.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.ui.goods.GoodDetailsActivity;
import com.xinyuan.xyshop.widget.SlideDetailsLayout;
import com.youth.banner.Banner;
import com.zhy.autolayout.AutoRelativeLayout;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * Created by Administrator on 2017/5/18.
 */

public class GoodsInfoFragment extends BaseFragment implements View.OnClickListener, SlideDetailsLayout.OnSlideDetailsListener {


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


	@BindView(R.id.tv_goods_detail)
	TextView tv_goods_detail;
	@BindView(R.id.tv_goods_config)
	TextView tv_goods_config;
	@BindView(R.id.tv_goods_service)
	TextView tv_goods_service;


	@BindView(R.id.ll_pull_up)
	LinearLayout ll_pull_up;

	private List<Fragment> fragmentList = new ArrayList<>();
	private List<TextView> tabTextList;

	public GoodDetailsActivity activity;




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
	}


	@Override
	public void initView() {
		sv_switch.setOnSlideDetailsListener(this);

	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		activity = (GoodDetailsActivity) context;
	}

	private Fragment nowFragment;

	GoodsDetailFragment goodsDetailFragment;

	GoodsConfigFragment goodsConfigFragment;
	GoodsServiceFragment goodsServiceFragment;

	@BindView(R.id.ll_goods_detail)
	LinearLayout ll_goods_detail;
	@BindView(R.id.ll_goods_config)
	LinearLayout ll_goods_config;
	@BindView(R.id.ll_goods_service)
	LinearLayout ll_goods_service;


	private FragmentTransaction fragmentTransaction;
	private FragmentManager fragmentManager;


	@Override
	public void onClick(View view) {
		switch (view.getId()) {

			case R.id.ll_pull_up:
				sv_switch.smoothOpen(true);
				break;
			case R.id.ll_goods_detail:
				tv_goods_detail.setTextColor(getResources().getColor(R.color.colorPrimary));
				tv_goods_config.setTextColor(getResources().getColor(R.color.tv_name));
				tv_goods_service.setTextColor(getResources().getColor(R.color.tv_name));
				switchFragment(nowFragment, goodsDetailFragment);
				nowFragment = goodsDetailFragment;
				break;
			case R.id.ll_goods_config:
				tv_goods_config.setTextColor(getResources().getColor(R.color.colorPrimary));
				tv_goods_detail.setTextColor(getResources().getColor(R.color.tv_name));
				tv_goods_service.setTextColor(getResources().getColor(R.color.tv_name));
				switchFragment(nowFragment, goodsConfigFragment);
				nowFragment = goodsConfigFragment;
				break;
			case R.id.ll_goods_service:
				tv_goods_service.setTextColor(getResources().getColor(R.color.colorPrimary));
				tv_goods_detail.setTextColor(getResources().getColor(R.color.tv_name));
				tv_goods_config.setTextColor(getResources().getColor(R.color.tv_name));
				switchFragment(nowFragment, goodsServiceFragment);
				nowFragment = goodsServiceFragment;
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
	public void onStatucChanged(SlideDetailsLayout.Status status) {
		if (status == SlideDetailsLayout.Status.OPEN) {
			//当前为图文详情页

			activity.vp_content.setNoScroll(true);
			activity.tv_title.setVisibility(View.VISIBLE);
			activity.psts_tabs.setVisibility(View.GONE);
		} else {
			//当前为商品详情页

			activity.vp_content.setNoScroll(false);
			activity.tv_title.setVisibility(View.GONE);
			activity.psts_tabs.setVisibility(View.VISIBLE);
		}
	}


}
