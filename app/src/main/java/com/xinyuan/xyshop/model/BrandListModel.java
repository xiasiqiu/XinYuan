package com.xinyuan.xyshop.model;

/**
 * Created by fx on 2017/5/18.
 */

public class BrandListModel {
	private int code;
	private BrandModel datas;

	public int getCode() {
		return code;
	}

	public BrandModel getDatas() {
		return datas;
	}

	@Override
	public String toString() {
		return "BrandList{" +
				"code=" + code +
				", datas=" + datas +
				'}';
	}
}
