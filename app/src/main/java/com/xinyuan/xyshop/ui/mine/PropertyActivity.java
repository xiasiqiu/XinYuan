package com.xinyuan.xyshop.ui.mine;

import android.os.Bundle;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/26.
 */

public class PropertyActivity extends BaseActivity {
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;

	@Override
	public int getLayoutId() {
		return R.layout.activity_property;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		getToolBar();
	}

	private void getToolBar() {
		tv_header_center.setText("我的财产");
	}
}
