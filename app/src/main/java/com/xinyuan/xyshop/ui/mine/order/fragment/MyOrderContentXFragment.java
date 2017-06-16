package com.xinyuan.xyshop.ui.mine.order.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/16.
 */

public class MyOrderContentXFragment extends BaseFragment {
	private String mTitle;
	@BindView(R.id.tv_order)
	TextView tv_order;

	public static MyOrderContentXFragment getInstance(String title) {
		MyOrderContentXFragment sf = new MyOrderContentXFragment();
		sf.mTitle = title;
		return sf;
	}


	@Override
	public int getLayoutId() {
		return R.layout.fragment_mine_order_content;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		tv_order.setText(mTitle);
	}
}
