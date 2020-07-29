package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/8/1.
 * 商品品牌数据
 */

public class GoodBradnBean implements Serializable {
	private static final long serialVersionUID = 3312142980330016430L;
	private long goodsBrandsId;
	private String goodsBrandsName;

	public long getGoodsBrandsId() {
		return goodsBrandsId;
	}

	public String getGoodsBrandsName() {
		return goodsBrandsName;
	}

	@Override
	public String toString() {
		return "GoodBradnBean{" +
				"goodsBrandsId=" + goodsBrandsId +
				", goodsBrandsName='" + goodsBrandsName + '\'' +
				'}';
	}
}
