package com.xinyuan.xyshop.ui.home;

import android.view.View;
import android.widget.Button;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.base.BasePresenter;
import com.youth.xframe.common.XActivityStack;

import butterknife.BindView;

/**
 * Created by fx on 2017/9/7.
 * 错误界面
 */

public class ErrorActivity extends BaseActivity {
	@BindView(R.id.bt_error)
	Button tv_error;

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.activity_error;
	}

	@Override
	public void initView() {

		tv_error.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				XActivityStack.getInstance().appExit();
			}
		});

	}

	@Override
	public void initData() {

	}

}
