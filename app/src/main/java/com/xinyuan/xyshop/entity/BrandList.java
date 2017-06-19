package com.xinyuan.xyshop.entity;

import com.xinyuan.xyshop.model.BrandModel;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */

public class BrandList {
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
