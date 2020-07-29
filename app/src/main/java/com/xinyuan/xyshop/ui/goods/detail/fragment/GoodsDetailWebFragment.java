package com.xinyuan.xyshop.ui.goods.detail.fragment;


import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.widget.XYWebView;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.loadingview.XLoadingView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;


/**
 * Created by fx on 2017/5/18.
 * 商品详情web详情界面
 */

public class GoodsDetailWebFragment extends BaseFragment<BasePresenter> {
	@BindView(R.id.fragmenlayout)
	FrameLayout fragmenlayout;
	@BindView(R.id.loadingView)
	XLoadingView loadingView;
	private XYWebView webView;


	@Override
	public void onStart() {
		registerEventBus(this);
		super.onStart();
	}


	@Override
	public void initView(View rootView) {
		webView = new XYWebView(mContext.getApplicationContext(), null);
		fragmenlayout.addView(webView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT,
				FrameLayout.LayoutParams.FILL_PARENT));
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				loadingView.showContent();

			}
		});


	}

	@Override
	public void initData() {

	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void setWeb(String body) {
		if (!XEmptyUtils.isSpace(body)) {
			webView.setFocusable(true);
			webView.loadData(getHtmlData(body), "text/html; charset=utf-8", "utf-8");
			loadingView.showContent();
		} else {
			loadingView.showEmpty();
		}
	}

	private String getHtmlData(String bodyHTML) {
		String head = "<head>" +
				"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
				"<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
				"</head>";
		return "<html>" + head + "<body>" + "<div>" + bodyHTML + "</div>" + "</body></html>";
	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_good_detail_web;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unregisterEventBus(this);
		if (webView != null) {
			webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
			webView.clearHistory();
			((ViewGroup) webView.getParent()).removeView(webView);
			webView.destroy();
			webView = null;
		}


	}
}
