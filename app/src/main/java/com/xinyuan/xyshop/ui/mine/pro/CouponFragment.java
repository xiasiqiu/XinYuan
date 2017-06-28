package com.xinyuan.xyshop.ui.mine.pro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.CouponAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.CouponBean;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/26.
 */

public class CouponFragment extends BaseFragment {
	@BindView(R.id.toolbar_iv)
	Toolbar toolbar_iv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.rv_coupon)
	RecyclerView rv_coupon;
	CouponAdapter adapter;

	public static CouponFragment newInstance() {
		CouponFragment fragment = new CouponFragment();
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_coupon;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		if (toolbar_iv != null) {
			XLog.v("红包加载Tooolbar");
			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_iv);
			tv_header_center.setText("平台红包");
		}

	}

	@Override
	public void onLazyInitView(@Nullable Bundle savedInstanceState) {

		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(1);
		this.rv_coupon.setLayoutManager(layoutManager);
		List<CouponBean> list = new ArrayList<>();
		list.add(new CouponBean());
		list.add(new CouponBean());
		list.add(new CouponBean());
		this.adapter = new CouponAdapter(R.layout.fragment_coupon_item, list);
		this.rv_coupon.setAdapter(adapter);


	}
}
