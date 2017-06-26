package com.xinyuan.xyshop.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/26.
 */

public class CreditBean implements Serializable {
	private static final long serialVersionUID = 5420960310710831028L;
	private String orderName;
	private long orderId;
	private String time;
	private int CreditNum;
	private int type;

	public CreditBean(String orderName, long orderId, String time, int creditNum, int type) {
		this.orderName = orderName;
		this.orderId = orderId;
		this.time = time;
		CreditNum = creditNum;
		this.type = type;
	}

	public String getOrderName() {
		return orderName;
	}

	public long getOrderId() {
		return orderId;
	}

	public String getTime() {
		return time;
	}

	public int getCreditNum() {
		return CreditNum;
	}

	public int getType() {
		return type;
	}
}
