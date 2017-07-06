package com.xinyuan.xyshop.ui.mine.order;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.util.SystemBarHelper;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/7/6.
 */

public class ServiceMoneyDetailActivity extends BaseActivity {
	@BindView(R.id.tv_service_status)
	TextView tv_service_status;
	@BindView(R.id.tv_status_time)
	TextView tv_status_time;

	@BindView(R.id.iv_service_statu1)
	ImageView iv_service_statu1;
	@BindView(R.id.iv_service_statu2)
	ImageView iv_service_statu2;
	@BindView(R.id.iv_service_statu3)
	ImageView iv_service_statu3;

	@BindView(R.id.v_service_statu2)
	View v_service_statu2;
	@BindView(R.id.v_service_statu3)
	View v_service_statu3;


	@BindView(R.id.tv_status_1)
	TextView tv_status_1;
	@BindView(R.id.tv_status_2)
	TextView tv_status_2;
	@BindView(R.id.tv_status_3)
	TextView tv_status_3;


	@BindView(R.id.tv_status_1_time)
	TextView tv_status_1_time;
	@BindView(R.id.tv_status_2_time)
	TextView tv_status_2_time;
	@BindView(R.id.tv_status_3_time)
	TextView tv_status_3_time;


	@BindView(R.id.toolbar_iv)
	Toolbar toolbar_iv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;


	@Override
	public int getLayoutId() {
		return R.layout.fragment_service_money_detail;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {

		if (toolbar_iv != null) {
			SystemBarHelper.immersiveStatusBar(this, 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(this, toolbar_iv);
			tv_header_center.setText("退款详情");
		}

		int i = 2;
		switch (i) {
			case 1:

				break;
			case 2:
				v_service_statu2.setVisibility(View.VISIBLE);
				iv_service_statu2.setVisibility(View.VISIBLE);
				tv_status_2.setVisibility(View.VISIBLE);
				tv_status_2_time.setVisibility(View.VISIBLE);
				break;
			case 3:

				v_service_statu2.setVisibility(View.VISIBLE);
				iv_service_statu2.setVisibility(View.VISIBLE);
				tv_status_2.setVisibility(View.VISIBLE);
				tv_status_2_time.setVisibility(View.VISIBLE);

				v_service_statu3.setVisibility(View.VISIBLE);
				iv_service_statu3.setVisibility(View.VISIBLE);
				tv_status_3.setVisibility(View.VISIBLE);
				tv_status_3_time.setVisibility(View.VISIBLE);

				break;
		}
	}
}
