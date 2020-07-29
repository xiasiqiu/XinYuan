package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.bean.ServiceOrderBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/9/26.
 */

public class ServiceModel implements Serializable{
	private static final long serialVersionUID = -1646440269855630479L;
	private List<ServiceOrderBean>orderList;

	public List<ServiceOrderBean> getOrderList() {
		return orderList;
	}

	@Override
	public String toString() {
		return "ServiceModel{" +
				"orderList=" + orderList +
				'}';
	}
}
