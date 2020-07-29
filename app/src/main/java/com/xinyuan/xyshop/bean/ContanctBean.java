package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/8/18.
 * 联系客服数据实体类
 */

public class ContanctBean implements Serializable {
	private static final long serialVersionUID = -4720445487882927496L;
	private String userName;
	private String userId;
	private String userhead;

	public ContanctBean(String userName, String userId, String userhead) {
		this.userName = userName;
		this.userId = userId;
		this.userhead = userhead;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserhead() {
		return userhead;
	}
}
