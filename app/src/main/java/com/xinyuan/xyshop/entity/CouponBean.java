package com.xinyuan.xyshop.entity;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/6/26.
 */

public class CouponBean implements Serializable{
	private static final long serialVersionUID = -7488839802758573062L;
	private long couponId;
	private String storeImage;
	private String storeName;
	private DecimalFormat money;
	private String useTime;

	public long getCouponId() {
		return couponId;
	}

	public String getStoreImage() {
		return storeImage;
	}

	public String getStoreName() {
		return storeName;
	}

	public DecimalFormat getMoney() {
		return money;
	}

	public String getUseTime() {
		return useTime;
	}
}
