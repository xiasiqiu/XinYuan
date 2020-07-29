package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/7/10.
 * 地区实体类
 */

public class AreaBean implements Serializable{
	@Override
	public String toString() {
		return  areaName.toString();
	}

	private static final long serialVersionUID = 955395045555083459L;
	private int level;
	private String areaName;
	private int area_id;
	private int parent_id;

	public int getLevel() {
		return level;
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
