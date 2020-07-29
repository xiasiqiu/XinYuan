package com.xinyuan.xyshop.common;

import android.content.Context;
import android.util.DisplayMetrics;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by fx on 2017/5/26.
 * 商城辅助类
 */

public class ShopHelper {
	private static boolean SignStatus = true;

	public static String getPriceString(double price) {
		return new DecimalFormat("0.00").format(price);
	}

	public static String getPriceString(BigDecimal price) {
		if (price != null) {
			return new DecimalFormat("0.00").format(price);
		}
		return "0.00";
	}


	public static boolean getSignStatus() {
		return SignStatus;
	}

	public static void setSignStatus(Boolean b) {
		SignStatus = b;
	}

	public static int getScreenWeight(Context context) {
		if (context == null) {
			return 0;
		}
		DisplayMetrics dm = new DisplayMetrics();
		return context.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
	}

}
