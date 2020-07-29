package com.xinyuan.xyshop.common.state;

import android.content.Context;

import java.util.Map;

/**
 * Created by fx on 2017/7/25.
 * 登录状态操作
 */

public class LoginContext {
	private UserState currentState = new LogoutState();
	public static boolean isLogin = false;

	private LoginContext() {

	}

	//通过单例模式获得LoginContext的对象
	public static LoginContext getInstance() {
		return SingletonHolder.instance;
	}


	public static class SingletonHolder {
		private static final LoginContext instance = new LoginContext();
	}

	public void setState(UserState state) {
		currentState = state;
		if (state instanceof LoginState) {
			isLogin = true;
		} else {
			isLogin = false;

		}


	}

	/**
	 * 立即购买操作
	 *
	 * @param isOnline
	 * @param param
	 * @param context
	 */
	public void buy(Boolean isOnline, Map<String, String> param, Context context) {
		currentState.buy(isOnline, param, context);
	}

	/**
	 * 添加购物车
	 *
	 * @param context
	 */
	public void addCart(Context context) {
		currentState.addCart(context);
	}


	/**
	 * 显示消息
	 *
	 * @param context
	 */
	public void showMsg(Context context) {
		currentState.showMsg(context);

	}

	/**
	 * 每日签到
	 *
	 * @param context
	 */
	public void signDay(Context context) {
		currentState.signDay(context);
	}

	/**
	 * 显示个人中心
	 *
	 * @param context
	 * @return
	 */
	public boolean showMine(Context context) {
		return currentState.showMine(context);
	}

	/**
	 * 检查是否有新消息
	 *
	 * @param context
	 */
	public void checkNewMsg(Context context) {
		currentState.checkNewMsg(context);
	}

	/**
	 * 客服沟通
	 *
	 * @param param
	 * @param context
	 */
	public void contactService(Map<String, String> param, Context context) {
		currentState.contactService(param, context);

	}

	public boolean getCoupon(Context context) {
		return currentState.getCoupon(context);
	}
}
