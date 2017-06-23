package com.xinyuan.xyshop.ui.home;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseActivity;

/**
 * Created by Administrator on 2017/6/23.
 */

public class WebViewActivity extends BaseActivity {
	com.tencent.smtt.sdk.WebView webView;
	private String url = "http://192.168.0.112:3000/#/home";


	@Override
	public int getLayoutId() {
		return R.layout.activity_web;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	public static final int MSG_OPEN_TEST_URL = 0;
	public static final int MSG_INIT_UI = 1;

	@Override
	public void initView() {

		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

		webView = (com.tencent.smtt.sdk.WebView) findViewById(R.id.webView);
		webView.loadUrl(url);
		WebSettings webSetting = webView.getSettings();
		webSetting.setAllowFileAccess(true);
		webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
		webSetting.setSupportZoom(true);
		webSetting.setBuiltInZoomControls(true);
		webSetting.setUseWideViewPort(true);
		webSetting.setSupportMultipleWindows(false);
		// webSetting.setLoadWithOverviewMode(true);
		webSetting.setAppCacheEnabled(true);
		// webSetting.setDatabaseEnabled(true);
		webSetting.setDomStorageEnabled(true);
		webSetting.setJavaScriptEnabled(true);
		webSetting.setGeolocationEnabled(true);
		webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
		webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
		webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
		webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0)
				.getPath());
		// webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
		webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
		CookieSyncManager.createInstance(this);

		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);

				return true;
			}
		});
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (webView != null) webView.destroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (webView != null && webView.canGoBack()) {
				webView.goBack();
				return true;
			}
			return super.onKeyDown(keyCode, event);
		}
		return super.onKeyDown(keyCode, event);
	}


}
