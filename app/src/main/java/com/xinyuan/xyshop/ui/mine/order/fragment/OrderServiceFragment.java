package com.xinyuan.xyshop.ui.mine.order.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.ServiceAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.util.SystemBarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/7/1.
 */

public class OrderServiceFragment extends BaseFragment {
	@BindView(R.id.rv_service)
	RecyclerView rv_service;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;


	@BindView(R.id.toolbar_iv)
	Toolbar msg_toolbar;
	private ServiceAdapter adapter;

	public static OrderServiceFragment newInstance() {
		OrderServiceFragment fragment = new OrderServiceFragment();
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_orderservice;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		if (msg_toolbar != null) {
			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
			tv_header_center.setText("退款/售后");
		}


	}

	@Override
	public void onLazyInitView(@Nullable Bundle savedInstanceState) {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(1);
		this.rv_service.setLayoutManager(layoutManager);
		List<GoodsVo> list = new ArrayList<>();
		list.add(new GoodsVo());
		list.add(new GoodsVo());
		list.add(new GoodsVo());
		list.add(new GoodsVo());
		list.add(new GoodsVo());
		list.add(new GoodsVo());
		this.adapter = new ServiceAdapter(R.layout.fragment_orderservice_item, list);
		this.adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
		this.rv_service.setAdapter(adapter);
		adapter.setEnableLoadMore(true);
	}
}