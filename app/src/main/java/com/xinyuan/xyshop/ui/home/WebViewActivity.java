package com.xinyuan.xyshop.ui.home;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.XYWebView;
import com.youth.xframe.utils.log.XLog;

import butterknife.BindView;

/**
 * Created by fx on 2017/6/23.
 * web界面Activity
 */

public class WebViewActivity extends BaseActivity {
	@BindView(R.id.fragmenlayout)
	FrameLayout fragmenlayout;
	XYWebView mWebView;
	@BindView(R.id.toolbar_iv)
	Toolbar toolbar_iv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	private ProgressDialog mProgressDialog;

	private String urls;
	public static final int MSG_OPEN_TEST_URL = 0;
	public static final int MSG_INIT_UI = 1;


	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.activity_web;
	}

	@Override
	public void initView() {
		SystemBarHelper.immersiveStatusBar(this, 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(this, toolbar_iv);
		CommUtil.initToolBar(WebViewActivity.this, iv_header_left, null);
		tv_header_center.setText("鑫园共享");

	}

	@Override
	public void initData() {
		mProgressDialog = new ProgressDialog(this);
		this.urls = getIntent().getStringExtra(Constants.URL);
		this.urls = getIntent().getStringExtra(Constants.URL);
		mWebView = new XYWebView(this, null);
		fragmenlayout.addView(mWebView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT,
				FrameLayout.LayoutParams.FILL_PARENT));
		mWebView.setFocusable(false);
		mWebView.loadUrl(urls);
		mWebView.setWebViewClient(new WebViewClient());
		mWebView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				XLog.v(url);
				return false;
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				mProgressDialog.show();
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				mProgressDialog.hide();
			}
		});
	}

	//销毁Webview
	@Override
	public void onDestroy() {
		if (mWebView != null) {
			mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
			mWebView.clearHistory();
			((ViewGroup) mWebView.getParent()).removeView(mWebView);
			mWebView.destroy();
			mWebView = null;
		}
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
			// 返回上一页面
			mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

			mWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}




