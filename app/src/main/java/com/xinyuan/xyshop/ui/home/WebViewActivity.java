package com.xinyuan.xyshop.ui.home;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.tamic.jswebview.browse.JsWeb.CustomWebViewClient;
import com.tamic.jswebview.view.ProgressBarWebView;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseActivity;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.loadingview.XLoadingView;


import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/23.
 */

public class WebViewActivity extends BaseActivity {

	private String urls = "";


	@BindView(R.id.login_progress_webview)
	ProgressBarWebView webView;

	@Override
	public int getLayoutId() {
		return R.layout.activity_web;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		this.urls = getIntent().getStringExtra("url");
		XLog.v("ULR" + urls);
		initWeb(urls);
	}

	public static final int MSG_OPEN_TEST_URL = 0;
	public static final int MSG_INIT_UI = 1;

	private void initWeb(String url) {

		webView.setWebViewClient(new CustomWebViewClient(webView.getWebView()) {
			@Override
			public String onPageError(String url) {
				//指定网络加载失败时的错误页面
				return "file:///android_asset/error.html";
			}

			@Override
			public Map<String, String> onPageHeaders(String url) {

				// 可以加入header

				return null;
			}


		});

		webView.loadUrl(url);
	}

	@Override
	public void initView() {

	}

}
