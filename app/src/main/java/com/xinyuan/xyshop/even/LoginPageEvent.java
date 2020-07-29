package com.xinyuan.xyshop.even;

/**
 * Created by fx on 2017/6/30.
 * 登录消息
 */

public class LoginPageEvent {
	public String type;
	public boolean loginStatus;
	public String Token;

	public LoginPageEvent(String type) {
		this.type = type;
	}

	public LoginPageEvent(String type, boolean loginStatus) {
		this.type = type;
		this.loginStatus = loginStatus;
	}

	public LoginPageEvent(String type, boolean loginStatus, String token) {
		this.type = type;
		this.loginStatus = loginStatus;
		this.Token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}


	public String getToken() {
		return Token;
	}

	@Override
	public String toString() {
		return "LoginPageEvent{" +
				"type='" + type + '\'' +
				", loginStatus=" + loginStatus +
				", Token='" + Token + '\'' +
				'}';
	}
}
