package com.xinyuan.xyshop.ui.goods.detail.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tamic.jswebview.browse.JsWeb.CustomWebViewClient;
import com.tamic.jswebview.view.ProgressBarWebView;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;

import java.util.Map;

import butterknife.BindView;


/**
 * 图文详情webview的Fragment
 */
public class GoodsInfoWebFragment extends BaseFragment {
	@BindView(R.id.web_detail)
	ProgressBarWebView webView;
	private String urls = "http://m.okhqb.com/item/description/1000334264.html?fromApp=true";

	@Override
	public int getLayoutId() {
		return R.layout.fragment_good_detail_web;
	}


	public GoodsInfoWebFragment getInstance(String url) {
		GoodsInfoWebFragment gf = new GoodsInfoWebFragment();
		gf.urls = url;
		return gf;

	}

	@Override
	public void initData(Bundle savedInstanceState) {
		initWeb(urls);
	}

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
