package com.xinyuan.xyshop.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by fx on 2017/8/22.
 * 用户财产数据
 */

public class ProPertyBean implements Serializable {
	private static final long serialVersionUID = 7454391197150616023L;
	private BigDecimal balance; //账户余额
	private int redpacket;  //红包
	private int coupon; //优惠券
	private long integral;  //积分

	public BigDecimal getBalance() {
		return balance;
	}

	public int getRedpacket() {
		return redpacket;
	}

	public int getCoupon() {
		return coupon;
	}

	public long getIntegral() {
		return integral;
	}
}
