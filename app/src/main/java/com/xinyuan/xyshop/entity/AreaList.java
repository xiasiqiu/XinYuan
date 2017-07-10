package com.xinyuan.xyshop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */

public class AreaList implements Serializable{
	private static final long serialVersionUID = -5005634970764353923L;
	private int code;
	private List<AreaBean>areaList;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public List<AreaBean> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<AreaBean> areaList) {
		this.areaList = areaList;
	}
}
