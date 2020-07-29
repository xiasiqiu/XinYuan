package com.xinyuan.xyshop.ui.buy;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;

import butterknife.BindView;

/**
 * Created by fx on 2017/8/12.
 * 兑换优惠券、红包
 */

public class ConfirmOrderExFragment extends BaseFragment<BasePresenter> {
	@BindView(R.id.toolbar_iv)
	Toolbar toolbar_iv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	@BindView(R.id.iv_header_right)
	ImageView iv_header_right;

	private String title;

	public static ConfirmOrderExFragment newInstance(String title) {
		ConfirmOrderExFragment fragment = new ConfirmOrderExFragment();
		fragment.title = title;
		return fragment;
	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_confirm_order_exchange;
	}


	@Override
	public void initView(View rootView) {
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_iv);
		tv_header_center.setText(title);
		CommUtil.initToolBar(getActivity(), iv_header_left, iv_header_right);
	}

	@Override
	public void initData() {

	}
}
