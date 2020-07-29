package com.xinyuan.xyshop.common.state;

import android.content.Context;

import java.util.Map;

/**
 * Created by fx on 2017/7/25.
 * 用户操作
 */

public interface UserState {
	void buy(Boolean isOnline, Map<String, String> param, Context context);

	void addCart(Context context);

	void showMsg(Context context);

	void signDay(Context context);

	Boolean showMine(Context context);

	void checkNewMsg(Context context);

	void contactService(Map<String, String> param, Context context);

	Boolean getCoupon(Context context);
}
