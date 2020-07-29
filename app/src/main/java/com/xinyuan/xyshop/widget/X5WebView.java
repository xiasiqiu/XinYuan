//package com.xinyuan.xyshop.widget;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.graphics.Canvas;
//import android.util.AttributeSet;
//import android.view.GestureDetector;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.TextView;
//
//import com.tencent.smtt.sdk.WebSettings;
//import com.tencent.smtt.sdk.WebView;
//import com.tencent.smtt.sdk.WebViewClient;
//import com.xinyuan.xyshop.ui.goods.detail.fragment.GoodsDetailWebFragment;
//import com.youth.xframe.utils.log.XLog;
//
///**
// * Created by Administrator on 2017/8/18.
// */
//
//public class X5WebView extends WebView {
//	TextView title;
//	private float oldY;
//	private int t;
//	private float oldX;
//	private GestureDetector gestureDetector;
//
//	public X5WebView(Context context, AttributeSet attrs, int defStyleAttr) {
//		super(context, attrs, defStyleAttr);
//	}
//
//	private WebViewClient client = new WebViewClient() {
//		/**
//		 * 防止加载网页时调起系统浏览器
//		 */
//		public boolean shouldOverrideUrlLoading(WebView view, String url) {
//			view.loadUrl(url);
//			return true;
//		}
//	};
//
//	@SuppressLint("SetJavaScriptEnabled")
//	public X5WebView(Context arg0, AttributeSet arg1) {
//		super(arg0, arg1);
//		gestureDetector = new GestureDetector(this.getContext(),
//				onGestureListener);
//		this.setWebViewClient(client);
//		// this.setWebChromeClient(chromeClient);
//		// WebStorage webStorage = WebStorage.getInstance();
//		initWebViewSettings();
//		this.getView().setClickable(true);
//		this.getView().setOnTouchListener(new OnTouchListener() {
//			@Override
//			public boolean onTouch(View view, MotionEvent ev) {
//				gestureDetector.onTouchEvent(ev);
//				switch (ev.getAction()) {
//					case MotionEvent.ACTION_MOVE:
//						float Y = ev.getY();
//						float Ys = Y - oldY;
//						float X = ev.getX();
//						if (Ys > 0 && t == 0) { //滑动到顶部了
//							//XLog.v("-----------------------------上滑到顶部了-----------------------------");
//							getParent().getParent().requestDisallowInterceptTouchEvent(false);
//						} else {
//							//	XLog.v("-----------------------------不在顶部-----------------------------");
//							if (x > 100) {
//								// 右滑 事件
//								getParent().getParent().requestDisallowInterceptTouchEvent(false);
//								XLog.v("右滑动2");
//							} else if (x < -100) {
//								// 左滑事件
//								getParent().getParent().requestDisallowInterceptTouchEvent(false);
//								XLog.v("左滑动2");
//							}
//						}
//						x = 0;
//						y = 0;
//						break;
//
//					case MotionEvent.ACTION_DOWN:
//
//						getParent().getParent().requestDisallowInterceptTouchEvent(true);
//						oldY = ev.getY();
//						oldX = ev.getX();
//
//						break;
//
//					case MotionEvent.ACTION_UP:
//						getParent().getParent().requestDisallowInterceptTouchEvent(true);
//
//						break;
//					default:
//						break;
//				}
//				return false;
//			}
//		});
//	}
//
//	private float x;
//	private float y;
//	private GestureDetector.OnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener() {
//		@Override
//		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
//		                       float velocityY) {
//
//			x = e2.getX() - e1.getX();
//			y = e2.getY() - e1.getY();
//
//			return false;
//		}
//	};
//
//	private void initWebViewSettings() {
//
//		WebSettings webSetting = this.getSettings();
//		webSetting.setJavaScriptEnabled(true);
//		webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
//		webSetting.setAllowFileAccess(true);
//		webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
//		webSetting.setSupportZoom(true);
//		webSetting.setBuiltInZoomControls(true);
//		webSetting.setJavaScriptEnabled(true);// 支持js
//		webSetting.setUseWideViewPort(true); //自适应屏幕
//		webSetting.setDisplayZoomControls(false);
//		webSetting.setSupportMultipleWindows(true);
//		webSetting.setLoadWithOverviewMode(true);
//		webSetting.setAppCacheEnabled(true);
//		// webSetting.setDatabaseEnabled(true);
//		webSetting.setDomStorageEnabled(true);
//		webSetting.setGeolocationEnabled(true);
//		webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
//		// webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
//		webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
//		// webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
//		webSetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//
//		// this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
//		// settings 的设计
//	}
//
//	@Override
//	protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
//		return super.drawChild(canvas, child, drawingTime);
//	}
//
//	public X5WebView(Context arg0) {
//		super(arg0);
//		setBackgroundColor(85621);
//	}
//
//
//	@Override
//	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//		this.t = t;
//		super.onScrollChanged(l, t, oldl, oldt);
//	}
//}
