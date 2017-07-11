package com.xinyuan.xyshop.ui.goods.store;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.util.SystemBarHelper;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/6.
 */

public class StoreIntroduceActivity extends BaseActivity {

	@BindView(R.id.toolbar_iv)
	Toolbar store_toolbar;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;


	@Override
	public int getLayoutId() {
		return R.layout.activity_sotre_introduce;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		tv_header_center.setText("店铺介绍");

	}
}
