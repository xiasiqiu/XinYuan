package com.xinyuan.xyshop.ui.mine.order.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/11.
 */

public class ServiceExpActivity extends BaseActivity {

	private String[] express = new String[7];
	@BindView(R.id.sp_chose)
	Spinner sp_chose;
	@BindView(R.id.toolbar_iv)
	Toolbar toolbar_iv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;

	@Override
	public int getLayoutId() {
		return R.layout.activity_service_express;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		tv_header_center.setText("填写单号");
		express[0] = "圆通";
		express[1] = "中通";
		express[2] = "申通";
		express[3] = "韵达";
		express[4] = "顺丰";
		express[5] = "天天";
		express[6] = "EMS";

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, express);
		adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		sp_chose.setAdapter(adapter);
		sp_chose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
			                           int pos, long id) {
				XToast.info("选择了" + express[pos]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// Another interface callback
			}
		});
	}

	@OnClick({R.id.button})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.button:
				finish();
				XLog.v("填写完了单号，结束");
		}
	}

}
