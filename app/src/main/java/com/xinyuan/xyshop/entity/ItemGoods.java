package com.xinyuan.xyshop.entity;

import java.io.Serializable;

/**
 * Created by fx on 2017/5/5 0005.
 */

public class ItemGoods implements Serializable {
	private double appPriceMin;
	private int commonId;
	private String goodsName;
	private String imageUrl;
	private double wechatPriceMin;

	public int getCommonId() {
		return this.commonId;
	}

	public void setCommonId(int commonId) {
		this.commonId = commonId;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public double getAppPriceMin() {
		return this.appPriceMin;
	}

	public void setAppPriceMin(double appPriceMin) {
		this.appPriceMin = appPriceMin;
	}

	public double getWechatPriceMin() {
		return this.wechatPriceMin;
	}

	public void setWechatPriceMin(double wechatPriceMin) {
		this.wechatPriceMin = wechatPriceMin;
	}

	@Override
	public String toString() {
		return "ItemGoods{" +
				"appPriceMin=" + appPriceMin +
				", commonId=" + commonId +
				", goodsName='" + goodsName + '\'' +
				", imageUrl='" + imageUrl + '\'' +
				", wechatPriceMin=" + wechatPriceMin +
				'}';
	}
}
