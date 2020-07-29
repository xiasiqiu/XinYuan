package com.xinyuan.xyshop.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.youth.xframe.utils.log.XLog;

/**
 * Created by Administrator on 2017/8/19.
 */

public class XYLayout extends FrameLayout {
	public float oldY;
	private int t;
	private float oldX;

	public XYLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}


	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		XLog.v("-----------------------------C滑动-----------------------------");

		switch (ev.getAction()) {
			case MotionEvent.ACTION_MOVE:
				float Y = ev.getY();
				float Ys = Y - oldY;
				float X = ev.getX();
				if (Ys > 0 && t == 0) {
					XLog.v("-----------------------------A滑动-----------------------------");
					getParent().getParent().getParent().requestDisallowInterceptTouchEvent(false);
				} else {
					XLog.v("-----------------------------B滑动-----------------------------");

					getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);

				}
				break;

			case MotionEvent.ACTION_DOWN:
				getParent().getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
				oldY = ev.getY();
				oldX = ev.getX();
				break;

			case MotionEvent.ACTION_UP:
				getParent().getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
				break;

			default:
				break;
		}
		return super.onTouchEvent(ev);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		this.t = t;
		super.onScrollChanged(l, t, oldl, oldt);
	}
}
