package com.xinyuan.xyshop.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/14.
 */

public class UserInfo implements Serializable {
	private static final long serialVersionUID = -8216022783476024475L;

	@Override
	public String toString() {
		return "UserInfo{" +
				"userId=" + userId +
				", headImg='" + headImg + '\'' +
				", userName='" + userName + '\'' +
				", userCredit='" + userCredit + '\'' +
				", favNum='" + favNum + '\'' +
				", followNum='" + followNum + '\'' +
				", historyNum='" + historyNum + '\'' +
				", UserGender=" + UserGender +
				", UserBirthday='" + UserBirthday + '\'' +
				'}';
	}

	private long userId;


	private String headImg;


	private String userName;


	private String userCredit;

	private String favNum;


	private String followNum;


	private String historyNum;


	private int UserGender;


	private String UserBirthday;


	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCredit() {
		return userCredit;
	}

	public void setUserCredit(String userCredit) {
		this.userCredit = userCredit;
	}

	public String getFavNum() {
		return favNum;
	}

	public void setFavNum(String favNum) {
		this.favNum = favNum;
	}

	public String getFollowNum() {
		return followNum;
	}

	public void setFollowNum(String followNum) {
		this.followNum = followNum;
	}

	public String getHistoryNum() {
		return historyNum;
	}

	public void setHistoryNum(String historyNum) {
		this.historyNum = historyNum;
	}

	public int getUserGender() {
		return UserGender;
	}

	public void setUserGender(int userGender) {
		UserGender = userGender;
	}

	public String getUserBirthday() {
		return UserBirthday;
	}

	public void setUserBirthday(String userBirthday) {
		UserBirthday = userBirthday;
	}
}
