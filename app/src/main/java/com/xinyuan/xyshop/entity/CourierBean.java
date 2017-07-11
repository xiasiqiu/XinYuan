package com.xinyuan.xyshop.entity;

/**
 * Created by Administrator on 2017/7/11.
 */

public class CourierBean {
	private int courierId;
	private String courierName;

	public CourierBean(int courierId, String courierName) {
		this.courierId = courierId;
		this.courierName = courierName;
	}

	public void setCourierId(int courierId) {
		this.courierId = courierId;
	}

	public void setCourierName(String courierName) {
		this.courierName = courierName;
	}

	public int getCourierId() {
		return courierId;
	}

	public String getCourierName() {
		return courierName;
	}
}
