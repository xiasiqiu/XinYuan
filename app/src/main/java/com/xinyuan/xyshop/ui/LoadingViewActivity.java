package com.xinyuan.xyshop.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseActivity;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadingViewActivity extends BaseActivity {

	private static final String ERROR="ERROR";
	private static final String EMPTY="EMPTY";


	@BindView(R.id.xloading_view)
	XLoadingView xLoadingView;

	@Override
	public int getLayoutId() {
		return R.layout.activity_loading_view;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		ButterKnife.bind(this);
		xLoadingView.setOnRetryClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				XToast.success("重新加载");
				xLoadingView.showLoading();
			}
		});
	}
}
