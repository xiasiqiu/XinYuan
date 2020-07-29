package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/9/7.
 * 支付类型
 */

public class PayTypeBean implements Serializable {
	private static final long serialVersionUID = 7116547314980215030L;
	private Object typeImg;
	private String typeName;
	private String typeHint;

	public Object getTypeImg() {
		return typeImg;
	}

	public void setTypeImg(Object typeImg) {
		this.typeImg = typeImg;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeHint() {
		return typeHint;
	}

	public void setTypeHint(String typeHint) {
		this.typeHint = typeHint;
	}
}
