package com.xinyuan.xyshop.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.zhy.autolayout.utils.AutoLayoutHelper;

/**
 * Created by Administrator on 2017/5/18.
 */

public class MyScrollView extends ScrollView {

	private final AutoLayoutHelper mHelper = new AutoLayoutHelper(this);


	public MyScrollView(Context context) {
		super(context);
	}

	public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (!isInEditMode()) {
			mHelper.adjustChildren();
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
