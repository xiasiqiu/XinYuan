package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/9/29.
 * 售后列表传入提供详情页进行：1、发起对话，2、拨打电话、3重新申请售后
 */

public class OrderServiceStoreInfoBean implements Serializable {

	private static final long serialVersionUID = -8883284708603506L;
	private String storeUserName;
	private String storeUserImg;
	private long storeUserId;
	private long serviceId;
	private long storeId;
	private long orderId;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getStoreUserName() {
		return storeUserName;
	}

	public void setStoreUserName(String storeUserName) {
		this.storeUserName = storeUserName;
	}

	public String getStoreUserImg() {
		return storeUserImg;
	}

	public void setStoreUserImg(String storeUserImg) {
		this.storeUserImg = storeUserImg;
	}

	public long getStoreUserId() {
		return storeUserId;
	}

	public void setStoreUserId(long storeUserId) {
		this.storeUserId = storeUserId;
	}

	public long getServiceId() {
		return serviceId;
	}


	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}

	@Override
	public String toString() {
		return "OrderServiceStoreInfoBean{" +
				"storeUserName='" + storeUserName + '\'' +
				", storeUserImg='" + storeUserImg + '\'' +
				", storeUserId=" + storeUserId +
				", serviceId=" + serviceId +
				", storeId=" + storeId +
				'}';
	}
}
