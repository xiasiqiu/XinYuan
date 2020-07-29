package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/9/21.
 * 二维码扫描数据
 */

public class ScanBean implements Serializable {
	private static final long serialVersionUID = -374210382115757735L;
	private String type;
	private String key;


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}


}
