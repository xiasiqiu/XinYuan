package com.xinyuan.xyshop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/8/1.
 * 产品属性数据
 */

public class GoodPropertyBean implements Serializable {
	private static final long serialVersionUID = 2498312269459051434L;
	private int goodsPropertyId;
	private String goodsPropertyName;
	private List<String> goodsPropertyValue;

	public int getGoodsPropertyId() {
		return goodsPropertyId;
	}

	public String getGoodsPropertyName() {
		return goodsPropertyName;
	}

	public List<String> getGoodsPropertyValue() {
		return goodsPropertyValue;
	}

	@Override
	public String toString() {
		return "PropertyBean{" +
				"goodsPropertyId=" + goodsPropertyId +
				", goodsPropertyName='" + goodsPropertyName + '\'' +
				", goodsPropertyValue=" + goodsPropertyValue +
				'}';
	}
}
