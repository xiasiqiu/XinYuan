package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/8/16.
 * 分类数据实体类
 */

public class ClassItemBean implements Serializable {
	private static final long serialVersionUID = -1168839128570822750L;
	/**
	 * storeClassId : 1
	 * storeClassName : 男女服装
	 * storeClassLevel : 0
	 */

	private long storeClassId;
	private String storeClassName;
	private int storeClassLevel;

	public long getStoreClassId() {
		return storeClassId;
	}

	public void setStoreClassId(long storeClassId) {
		this.storeClassId = storeClassId;
	}

	public String getStoreClassName() {
		return storeClassName;
	}

	public void setStoreClassName(String storeClassName) {
		this.storeClassName = storeClassName;
	}

	public int getStoreClassLevel() {
		return storeClassLevel;
	}

	public void setStoreClassLevel(int storeClassLevel) {
		this.storeClassLevel = storeClassLevel;
	}
}
