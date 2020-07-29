package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/7/14.
 * 用户个人数据
 */

public class UserInfoBean implements Serializable {
	private static final long serialVersionUID = -8216022783476024475L;

	private String Birthday;
	private String Integral;
	private long userId;
	private String userFavs;
	private String storeCount;
	private int userSex;
	private String userPhoto;
	private String historyNum;
	private String userName;
	private String userPhone;
	private long logistics_no;	//待收货订单数量
	private long logistics_yes;	//待评价订单数量
	private long pay_no;		//代付款订单数量
	private long pay_yes;		//代发货订单数量
	private long returns_refund;//售后订单数量

	public long getLogistics_no() {
		return logistics_no;
	}

	public long getLogistics_yes() {
		return logistics_yes;
	}

	public long getPay_no() {
		return pay_no;
	}

	public long getPay_yes() {
		return pay_yes;
	}

	public long getReturns_refund() {
		return returns_refund;
	}

	public String getBirthday() {
		return Birthday;
	}

	public String getIntegral() {
		return Integral;
	}

	public long getUserId() {
		return userId;
	}

	public String getUserFavs() {
		return userFavs;
	}

	public String getStoreCount() {
		return storeCount;
	}

	public int getUserSex() {
		return userSex;
	}

	public String getUserPhoto() {
		return userPhoto;
	}

	public String getHistoryNum() {
		return historyNum;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	@Override
	public String toString() {
		return "UserInfoBean{" +
				"Birthday='" + Birthday + '\'' +
				", Integral='" + Integral + '\'' +
				", userId=" + userId +
				", userFavs='" + userFavs + '\'' +
				", storeCount='" + storeCount + '\'' +
				", userSex=" + userSex +
				", userPhoto='" + userPhoto + '\'' +
				", historyNum='" + historyNum + '\'' +
				", userName='" + userName + '\'' +
				", userPhone='" + userPhone + '\'' +
				'}';
	}
}
