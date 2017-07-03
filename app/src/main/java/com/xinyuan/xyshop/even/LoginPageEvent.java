package com.xinyuan.xyshop.even;

/**
 * Created by Administrator on 2017/6/30.
 */

public class LoginPageEvent {
	public String type;
	public static boolean loginStatus;

	public LoginPageEvent(String type) {
		this.type = type;
	}

	public LoginPageEvent(String type, boolean loginStatus) {
		this.type = type;
		this.loginStatus = loginStatus;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static boolean isLoginStatus() {
		return loginStatus;
	}

	public static void setLoginStatus(boolean loginStatus) {
		LoginPageEvent.loginStatus = loginStatus;
	}
}
