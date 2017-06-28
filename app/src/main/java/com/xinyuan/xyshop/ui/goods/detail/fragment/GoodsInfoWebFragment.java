package com.xinyuan.xyshop.ui.goods.detail.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;

import butterknife.BindView;


/**
 * 图文详情webview的Fragment
 */
public class GoodsInfoWebFragment extends BaseFragment {
	@BindView(R.id.webView)
	WebView wv_detail;
	private WebSettings webSettings;
	private LayoutInflater inflater;


	@Override
	public int getLayoutId() {
		return R.layout.fragment_item_info_web;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		String url = "http://m.okhqb.com/item/description/1000334264.html?fromApp=true";
		wv_detail.setFocusable(false);
		wv_detail.loadUrl(url);
		webSettings = wv_detail.getSettings();
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setBuiltInZoomControls(true);
		webSettings.setLoadsImagesAutomatically(true);
		webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
		webSettings.setBlockNetworkImage(true);
		webSettings.setUseWideViewPort(true);
		webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		wv_detail.setWebViewClient(new GoodsDetailWebViewClient());
	}

	private class GoodsDetailWebViewClient extends WebViewClient {
		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			webSettings.setBlockNetworkImage(false);
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			return true;
		}
	}
}
