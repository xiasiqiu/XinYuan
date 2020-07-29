package com.xinyuan.xyshop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/9/26.
 * 售后订单实体类
 */

public class ServiceOrderBean implements Serializable {
	private static final long serialVersionUID = 3345578178471213774L;
	private String storeName;
	private long storeId;
	private long orderId;
	private List<ServiceGoodBean> goodsCartList;
	private long storeUserId;
	private String storeUserImg;
	private String storeUserName;


	public String getStoreName() {
		return storeName;
	}

	public long getStoreId() {
		return storeId;
	}

	public long getOrderId() {
		return orderId;
	}

	public List<ServiceGoodBean> getGoodsCartList() {
		return goodsCartList;
	}

	public long getStoreUserId() {
		return storeUserId;
	}

	public String getStoreUserImg() {
		return storeUserImg;
	}

	public String getStoreUserName() {
		return storeUserName;
	}
}
