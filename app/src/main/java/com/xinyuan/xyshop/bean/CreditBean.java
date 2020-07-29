package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/6/26.
 * 积分数据实体类
 */

public class CreditBean implements Serializable {
	private static final long serialVersionUID = 5420960310710831028L;
	private long logDig;
	private String logContent;
	private String logTime;
	private long orderId;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CreditBean{" +
				"logDig=" + logDig +
				", logContent='" + logContent + '\'' +
				", logTime='" + logTime + '\'' +
				", orderId=" + orderId +
				'}';
	}

	public long getLogDig() {
		return logDig;
	}

	public String getLogContent() {
		return logContent;
	}

	public String getLogTime() {
		return logTime;
	}

	public long getOrderId() {
		return orderId;
	}
}
