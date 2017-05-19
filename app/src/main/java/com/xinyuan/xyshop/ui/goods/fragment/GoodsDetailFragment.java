package com.xinyuan.xyshop.ui.goods.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.ui.goods.GoodDetailsActivity;
import com.xinyuan.xyshop.util.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/5/18.
 */

public class GoodsDetailFragment extends BaseFragment implements View.OnClickListener {

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
	private GoodDetailsActivity activity;
	private GoodsConfigFragment goodsConfigFragment;
	private GoodsDetailFragment goodsDetailFragment;

	private FragmentTransaction fragmentTransaction;
	private FragmentManager fragmentManager;
	private View rootView;


	@BindView(R.id.good_detail_img)
	ImageView imageView;

	@Override
	public int getLayoutId() {
		return R.layout.fragment_good_detail;
	}

	@Override
	public void initData(@Nullable Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		int resourceId = R.drawable.test_goods;
		Glide.with(this).
				load(resourceId).
				asBitmap().
				into(imageView);
		tabTextList = new ArrayList<>();
		tabTextList.add(tv_goods_detail);
		tabTextList.add(tv_goods_config);
		tabTextList.add(tv_goods_service);
//		goodsConfigFragment = new GoodsConfigFragment();
//		nowFragment = goodsDetailFragment;
//		fragmentManager = getChildFragmentManager();
//		//默认显示商品详情tab
//		fragmentManager.beginTransaction().replace(R.id.fl_content, nowFragment).commitAllowingStateLoss();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.ll_goods_detail:
				//商品详情tab
				switchFragment(nowFragment, goodsDetailFragment);
				nowIndex = 0;
				nowFragment = goodsDetailFragment;

				break;

			case R.id.ll_goods_config:
				//规格参数tab
				switchFragment(nowFragment, goodsConfigFragment);
				nowIndex = 1;
				nowFragment = goodsConfigFragment;

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
}
