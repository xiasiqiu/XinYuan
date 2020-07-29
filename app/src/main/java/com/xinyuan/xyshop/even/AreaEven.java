package com.xinyuan.xyshop.even;

/**
 * Created by fx on 2017/8/30.
 * 地区选择消息
 */

public class AreaEven {
	private long area_id;
	private String area_name;

	public AreaEven(long area_id, String area_name) {
		this.area_id = area_id;
		this.area_name = area_name;
	}

	public long getArea_id() {
		return area_id;
	}

	public String getArea_name() {
		return area_name;
	}
}
