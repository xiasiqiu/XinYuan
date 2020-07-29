package com.xinyuan.xyshop.common.state;

import android.content.Context;

import com.xinyuan.xyshop.ui.mine.login.LoginActivity;
import com.xinyuan.xyshop.util.CommUtil;

import java.util.Map;

/**
 * Created by fx on 2017/7/25.
 * 未登录模式
 */

public class LogoutState implements UserState {
	@Override
	public void buy(Boolean isOnlin, Map<String, String> param, Context context) {
		CommUtil.gotoActivity(context, LoginActivity.class, false, null);
	}

	@Override
	public void addCart(Context context) {
		CommUtil.gotoActivity(context, LoginActivity.class, false, null);

	}


	@Override
	public void showMsg(Context context) {
		CommUtil.gotoActivity(context, LoginActivity.class, false, null);

	}

	@Override
	public void signDay(Context context) {
		CommUtil.gotoActivity(context, LoginActivity.class, false, null);

	}

	@Override
	public Boolean showMine(Context context) {
		CommUtil.gotoActivity(context, LoginActivity.class, false, null);
		return false;
	}

	@Override
	public void checkNewMsg(Context context) {

	}

	@Override
	public void contactService(Map<String, String> param, Context context) {
		CommUtil.gotoActivity(context, LoginActivity.class, false, null);

	}

	@Override
	public Boolean getCoupon(Context context) {
		CommUtil.gotoActivity(context, LoginActivity.class, false, null);
		return false;
	}
}
