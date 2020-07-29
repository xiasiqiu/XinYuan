package com.xinyuan.xyshop.model;

import java.io.Serializable;

/**
 * Created by fx on 2017/7/20.
 * token验证数据
 */

public class LoginModel implements Serializable {
	private static final long serialVersionUID = -8059287340068616185L;
	private String token;
	private long id;
	public long getId() {
		return id;
	}

	public String getToken() {
		return token;
	}

	@Override
	public String toString() {
		return "LoginModel{" +
				"token='" + token + '\'' +
				", id='" + id + '\'' +
				'}';

	}
}
