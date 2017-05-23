package com.xinyuan.xyshop.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2017/5/23.
 */

public class XScrollView extends ScrollView

{
	private int _calCount;
	private OnScrollBottomListener _listener;
	private XScrollViewListener xScrollViewListener;

	public interface OnScrollBottomListener {
		void srollToBottom();
	}

	public interface XScrollViewListener {
		void onScrollChanged(int i, int i2, int i3, int i4);
	}

	public void registerOnBottomListener(OnScrollBottomListener l) {
		this._listener = l;
	}

	public void unRegisterOnBottomListener() {
		this._listener = null;
	}

	public XScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		if (getHeight() + getScrollY() == getChildAt(0).getHeight()) {
			this._calCount++;
			if (this._calCount == 1 && this._listener != null) {
				this._listener.srollToBottom();
			}
		} else {
			this._calCount = 0;
		}
		if (this.xScrollViewListener != null) {
			this.xScrollViewListener.onScrollChanged(l, t, oldl, oldt);
		}
	}

	public void setXScrollViewListener(XScrollViewListener xlistener) {
		this.xScrollViewListener = xlistener;
	}
}
