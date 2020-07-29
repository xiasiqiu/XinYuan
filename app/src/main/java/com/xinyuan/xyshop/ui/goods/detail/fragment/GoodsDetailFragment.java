package com.xinyuan.xyshop.ui.goods.detail.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fx on 2017/5/18.
 */

public class GoodsDetailFragment extends BaseFragment {

	@BindView(R.id.ll_goods_detail)
	LinearLayout ll_goods_detail;
	@BindView(R.id.ll_goods_config)
	LinearLayout ll_goods_config;
	@BindView(R.id.ll_goods_service)
	LinearLayout ll_goods_service;
	@BindView(R.id.fl_content)
	FrameLayout fl_content;

	@BindView(R.id.tv_goods_detail)
	TextView tv_goods_detail;
	@BindView(R.id.tv_goods_config)
	TextView tv_goods_config;
	@BindView(R.id.tv_goods_service)
	TextView tv_goods_service;


	private Fragment nowFragment;
	private int nowIndex;
	private float fromX;
	private List<TextView> tabTextList;
	private GoodDetailActivity activity;
	private GoodsDetailFragment goodsDetailFragment;
	private FragmentTransaction fragmentTransaction;
	private FragmentManager fragmentManager;
	private View rootView;
	private GoodsConfigFragment goodsConfigFragment;
	private GoodsDetailWebFragment goodsDetailWebFragment;
	private GoodsServiceFragment goodsServiceFragment;


	@OnClick({R.id.ll_goods_config, R.id.ll_goods_service, R.id.ll_goods_detail})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.ll_goods_detail:
				//商品详情tab
				switchFragment(nowFragment, goodsDetailWebFragment);
				nowIndex = 0;
				nowFragment = goodsDetailWebFragment;
				tv_goods_detail.setTextColor(getResources().getColor(R.color.colorPrimary));
				tv_goods_config.setTextColor(getResources().getColor(R.color.tv_hint));
				tv_goods_service.setTextColor(getResources().getColor(R.color.tv_hint));
				break;
			case R.id.ll_goods_config:
				//规格参数tab
				switchFragment(nowFragment, goodsConfigFragment);
				nowIndex = 1;
				nowFragment = goodsConfigFragment;
				tv_goods_detail.setTextColor(getResources().getColor(R.color.tv_hint));
				tv_goods_config.setTextColor(getResources().getColor(R.color.colorPrimary));
				tv_goods_service.setTextColor(getResources().getColor(R.color.tv_hint));
				break;
			case R.id.ll_goods_service:
				//规格参数tab
				switchFragment(nowFragment, goodsServiceFragment);
				nowIndex = 2;
				nowFragment = goodsServiceFragment;
				tv_goods_detail.setTextColor(getResources().getColor(R.color.tv_hint));
				tv_goods_config.setTextColor(getResources().getColor(R.color.tv_hint));
				tv_goods_service.setTextColor(getResources().getColor(R.color.colorPrimary));
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
	public void initView(View rootView) {
		tabTextList = new ArrayList<>();
		tabTextList.add(tv_goods_detail);
		tabTextList.add(tv_goods_config);
		tabTextList.add(tv_goods_service);


		goodsDetailWebFragment = new GoodsDetailWebFragment();
		goodsConfigFragment = new GoodsConfigFragment();
		goodsServiceFragment = new GoodsServiceFragment();

		nowFragment = goodsDetailWebFragment;

		fragmentManager = getChildFragmentManager();

		//默认显示商品详情tab
		fragmentManager.beginTransaction().replace(R.id.fl_content, nowFragment).commitAllowingStateLoss();
		tv_goods_detail.setTextColor(getResources().getColor(R.color.colorPrimary));
	}

	@Override
	public void initData() {

	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_good_detail;
	}


}
