package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.entity.ItemData;

import java.util.List;

/**
 * Created by Administrator on 2017/7/3.
 */

public class MenuModel {
	@Override
	public String toString() {
		return "MenuModel{" +
				"code=" + code +
				", data=" + data +
				'}';
	}

	private int code;
	private List<ItemData> data;

	public int getCode() {
		return code;
	}

	public List<ItemData> getData() {
		return data;
	}
}
