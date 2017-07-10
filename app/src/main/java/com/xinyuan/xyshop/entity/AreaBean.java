package com.xinyuan.xyshop.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/10.
 */

public class AreaBean implements Serializable{
	@Override
	public String toString() {
		return  areaName.toString();
	}

	private static final long serialVersionUID = 955395045555083459L;
	private int id;
	private String areaName;
	private int area_id;
	private int parent_id;

	public int getId() {
		return id;
	}

	public String getAreaName() {
		return areaName;
	}

	public int getArea_id() {
		return area_id;
	}

	public int getParent_id() {
		return parent_id;
	}
}
