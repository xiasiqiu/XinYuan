package com.xinyuan.xyshop.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/12.
 */

public class CouponBean implements Serializable{


	private static final long serialVersionUID = 3732356561071474547L;
	private int couponId;
	private String couponName;
	private int couponNum;

	public int getCouponId() {
		return couponId;
	}

	public String getCouponName() {
		return couponName;
	}

	public int getCouponNum() {
		return couponNum;
	}

	@Override
	public String toString() {
		return "CouponBean{" +
				"couponId=" + couponId +
				", couponName='" + couponName + '\'' +
				", couponNum=" + couponNum +
				'}';
	}
}
