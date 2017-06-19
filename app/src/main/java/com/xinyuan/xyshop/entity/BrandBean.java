package com.xinyuan.xyshop.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/12.
 */

public class BrandBean implements Serializable {


	private static final long serialVersionUID = 5582995606145453808L;
	private int brandId;
	private String brandName;
	private int storeId;
	private String brandImage;


	public int getBrandId() {
		return brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public int getStoreId() {
		return storeId;
	}

	public String getBrandImage() {
		return brandImage;
	}


}
