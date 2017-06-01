package com.xinyuan.xyshop.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/6/1.
 */

public class XYGridLayoutManager extends GridLayoutManager {
	private boolean isScrollEnabled = true;

	public XYGridLayoutManager(Context context, int spanCount) {
		super(context, spanCount);
	}

	public void setScrollEnabled(boolean flag) {
		this.isScrollEnabled = flag;
	}

	@Override
	public boolean canScrollVertically() {
		//Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
		return isScrollEnabled && super.canScrollVertically();
	}


}
