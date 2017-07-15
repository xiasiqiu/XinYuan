package com.xinyuan.xyshop.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/14.
 */

public class TestUserInfo implements Serializable {
	private static final long serialVersionUID = 9033715337043240127L;
	private UserInfo datas;
	private int code;

	public UserInfo getDatas() {
		return datas;
	}

	public int getCode() {
		return code;
	}
}
