package com.xinyuan.xyshop.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by fx on 2017/6/23.
 */

public class GoodListItemBean implements Serializable {
	private static final long serialVersionUID = 6579021163019689196L;
	private String goodsId;
	private String goodsType;
	private String imageUrl;
	private String consumeNum;
	private String goodsActive;
	private BigDecimal goodsPrice;
	private String goodsName;
	private String goodsEvanum;
	private String goodsEvaNum;

	public String getGoodsEvanum() {
		return goodsEvanum;
	}

	public String getGoodsEvaNum() {
		return goodsEvanum;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getConsumeNum() {
		return consumeNum;
	}

	public String getGoodsActive() {
		return goodsActive;
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setConsumeNum(String consumeNum) {
		this.consumeNum = consumeNum;
	}

	public void setGoodsActive(String goodsActive) {
		this.goodsActive = goodsActive;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public void setGoodsEvaNum(String goodsEvaNum) {
		this.goodsEvanum = goodsEvaNum;
	}

	@Override
	public String toString() {
		return "GoodListItem{" +
				"goodsId='" + goodsId + '\'' +
				", goodsType='" + goodsType + '\'' +
				", imageUrl='" + imageUrl + '\'' +
				", consumeNum='" + consumeNum + '\'' +
				", goodsActive='" + goodsActive + '\'' +
				", goodsPrice=" + goodsPrice +
				", goodsName='" + goodsName + '\'' +
				", goodsEvaNum='" + goodsEvanum + '\'' +
				'}';
	}
}
