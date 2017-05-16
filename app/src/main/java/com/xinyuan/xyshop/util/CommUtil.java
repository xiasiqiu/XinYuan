package com.xinyuan.xyshop.util;

import android.content.Context;

/**
 * Created by Administrator on 2017/5/16.
 */

public class CommUtil {

	public static int dip2px(Context context, float dipValue) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

}
