package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/9/4.
 * 店铺优惠券数据
 */

public class StoreCouponBean implements Serializable{
	private static final long serialVersionUID = 1960632099507561204L;
	private String couponName;
	private String status;
	private String type;
	private String couponId;
	private String couponSum;

	public String getCouponName() {
		return couponName;
	}

	public String getStatus() {
		return status;
	}

	public String getType() {
		return type;
	}

	public String getCouponId() {
		return couponId;
	}

	public String getCouponSum() {
		return couponSum;
	}

	@Override
	public String toString() {
		return "StoreCouponBean{" +
				"couponName='" + couponName + '\'' +
				", status='" + status + '\'' +
				", type='" + type + '\'' +
				", couponId='" + couponId + '\'' +
				", couponSum='" + couponSum + '\'' +
				'}';
	}
}
