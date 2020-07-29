package com.xinyuan.xyshop.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by fx on 2017/8/24.
 * 购物车商品
 */

public class CartGoodBean implements Serializable {
	private static final long serialVersionUID = -273201743323110294L;

	private long goodsSpecInfo;
	private long goodsCartId;
	private long goodsId;
	private String goodsImg;
	private String goodsActive;
	private BigDecimal goodsPrice;
	private String goodsSpecText;
	private int goodsCount;
	private String goodsName;


	private boolean isChoosed;
	private int position;

	@Override
	public String toString() {
		return "CartGoodBean{" +
				"goodsSpecInfo=" + goodsSpecInfo +
				", goodsCartId=" + goodsCartId +
				", goodsId=" + goodsId +
				", goodsImg='" + goodsImg + '\'' +
				", goodsActive='" + goodsActive + '\'' +
				", goodsPrice=" + goodsPrice +
				", goodsSpecText='" + goodsSpecText + '\'' +
				", goodsCount=" + goodsCount +
				", goodsName='" + goodsName + '\'' +
				", isChoosed=" + isChoosed +
				", position=" + position +
				'}';
	}

	public long getGoodsSpecInfo() {
		return goodsSpecInfo;
	}

	public void setGoodsSpecInfo(long goodsSpecInfo) {
		this.goodsSpecInfo = goodsSpecInfo;
	}

	public long getGoodsCartId() {
		return goodsCartId;
	}

	public void setGoodsCartId(long goodsCartId) {
		this.goodsCartId = goodsCartId;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}

	public String getGoodsActive() {
		return goodsActive;
	}

	public void setGoodsActive(String goodsActive) {
		this.goodsActive = goodsActive;
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsSpecText() {
		return goodsSpecText;
	}

	public void setGoodsSpecText(String goodsSpecText) {
		this.goodsSpecText = goodsSpecText;
	}

	public int getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public boolean isChoosed() {
		return isChoosed;
	}

	public void setChoosed(boolean choosed) {
		isChoosed = choosed;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}
