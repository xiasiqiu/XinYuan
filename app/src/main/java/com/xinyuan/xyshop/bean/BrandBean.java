package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/5/12.
 * 品牌店铺实体类
 */

public class BrandBean implements Serializable {

	private static final long serialVersionUID = 5582995606145453808L;
	private long storeId;
	private String storeName;
	private String storeImg;



	public long getStoreId() {
		return storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getStoreImg() {
		return storeImg;
	}
}
