package com.xinyuan.xyshop.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created by fx on 2017/5/2 0002.
 */

public class SourcePanel extends GridView {

	public SourcePanel(Context context) {

		super(context);

	}

	public SourcePanel(Context context, AttributeSet attrs) {

		super(context, attrs);

	}

	public SourcePanel(Context context, AttributeSet attrs, int defStyle) {

		super(context, attrs, defStyle);

	}

	@Override

	public boolean dispatchTouchEvent(MotionEvent ev) {

		if (ev.getAction() == MotionEvent.ACTION_MOVE) {

			return true;  //禁止GridView滑动

		}


		return super.dispatchTouchEvent(ev);

	}
}
