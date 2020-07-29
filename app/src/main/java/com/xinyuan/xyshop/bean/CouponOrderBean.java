package com.xinyuan.xyshop.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by fx on 2017/9/6.
 */

public class CouponOrderBean implements Serializable {
	private static final long serialVersionUID = -2896194437779495015L;
	private String couponName;
	private String couponStoreId;
	private long couponInfoId;
	private boolean isCheck;
	private BigDecimal prices;

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public void setCouponStoreId(String couponStoreId) {
		this.couponStoreId = couponStoreId;
	}

	public void setCouponInfoId(long couponInfoId) {
		this.couponInfoId = couponInfoId;
	}

	public BigDecimal getPrice() {
		return prices;
	}

	public void setPrice(BigDecimal price) {
		this.prices = price;
	}

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean check) {
		isCheck = check;
	}

	public String getCouponName() {
		return couponName;
	}

	public String getCouponStoreId() {
		return couponStoreId;
	}

	public long getCouponInfoId() {
		return couponInfoId;
	}
}
