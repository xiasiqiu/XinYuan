package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/7/22.
 * 用户基础元素
 */

public class BaseUserInfo implements Serializable{
	private static final long serialVersionUID = 2990865135194927732L;
	private String token;
	private String userId;

	public void setToken(String token) {
		this.token = token;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public String getUserId() {
		return userId;
	}
}
