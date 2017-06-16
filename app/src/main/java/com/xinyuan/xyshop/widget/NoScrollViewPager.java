package com.xinyuan.xyshop.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 提供禁止滑动功能的自定义ViewPager
 */
public class NoScrollViewPager extends ViewPager {
	private boolean noScroll = false;
	private int startX;
	private int startY;

	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}


	public NoScrollViewPager(Context context) {
		super(context);
	}

	public void setNoScroll(boolean noScroll) {
		this.noScroll = noScroll;
	}

	@Override
	public void scrollTo(int x, int y) {
		super.scrollTo(x, y);
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		if (noScroll)
			return false;
		else
			return super.onTouchEvent(arg0);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		if (noScroll)
			return false;
		else
			return super.onInterceptTouchEvent(arg0);
	}

	@Override
	public void setCurrentItem(int item, boolean smoothScroll) {
		super.setCurrentItem(item, smoothScroll);
	}

	@Override
	public void setCurrentItem(int item) {
		super.setCurrentItem(item);
	}

	@Override
	protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
		if (v != this && v instanceof ViewPager) {
			return true;
		}
		return super.canScroll(v, checkV, dx, x, y);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {

//      //用getParent去请求，希望父控件不拦截这个OnToch事件
//        getParent().requestDisallowInterceptTouchEvent(true);

		switch (ev.getAction()) {

			case MotionEvent.ACTION_DOWN:
				//用getParent去请求，希望父控件不拦截这个OnToch事件
				//这样为了保证ACTION_MOVE调用
				getParent().requestDisallowInterceptTouchEvent(true);

				startX = (int) ev.getRawX();
				startY = (int) ev.getRawY();

				break;

			case MotionEvent.ACTION_MOVE:

				int endX = (int) ev.getRawX();
				int endY = (int) ev.getRawY();

				if (Math.abs(endX - startX) > Math.abs(endY - startY)) {//左右滑动

					if (endX > startX) {//右滑

						if (getCurrentItem() == 0) {//第一个页面，需要父控件拦截

							getParent().requestDisallowInterceptTouchEvent(false);
						}

					} else {//左滑

						if (getCurrentItem() == getAdapter().getCount() - 1) {//最后一个页面，需要拦截

							getParent().requestDisallowInterceptTouchEvent(false);
						}

					}

				} else {//上下滑动，需要父控件拦截
					getParent().requestDisallowInterceptTouchEvent(false);
				}

				break;
		}


		return super.dispatchTouchEvent(ev);
	}

}