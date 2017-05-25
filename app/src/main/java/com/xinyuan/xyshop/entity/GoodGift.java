package com.xinyuan.xyshop.entity;

/**
 * Created by Administrator on 2017/5/25.
 */

public class GoodGift {
	private int commonId;
	private int giftId;
	private int giftNum;
	private int giftType;
	private String goodsFullSpecs;
	private int goodsId;
	private String goodsName;
	private String imageSrc;
	private int itemCommonId;
	private int itemId;
	private String unitName;

	public int getCommonId() {
		return commonId;
	}

	public void setCommonId(int commonId) {
		this.commonId = commonId;
	}

	public int getGiftId() {
		return giftId;
	}

	public void setGiftId(int giftId) {
		this.giftId = giftId;
	}

	public int getGiftNum() {
		return giftNum;
	}

	public void setGiftNum(int giftNum) {
		this.giftNum = giftNum;
	}

	public int getGiftType() {
		return giftType;
	}

	public void setGiftType(int giftType) {
		this.giftType = giftType;
	}

	public String getGoodsFullSpecs() {
		return goodsFullSpecs;
	}

	public void setGoodsFullSpecs(String goodsFullSpecs) {
		this.goodsFullSpecs = goodsFullSpecs;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public int getItemCommonId() {
		return itemCommonId;
	}

	public void setItemCommonId(int itemCommonId) {
		this.itemCommonId = itemCommonId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	@Override
	public String toString() {
		return "GoodGift{" +
				"commonId=" + commonId +
				", giftId=" + giftId +
				", giftNum=" + giftNum +
				", giftType=" + giftType +
				", goodsFullSpecs='" + goodsFullSpecs + '\'' +
				", goodsId=" + goodsId +
				", goodsName='" + goodsName + '\'' +
				", imageSrc='" + imageSrc + '\'' +
				", itemCommonId=" + itemCommonId +
				", itemId=" + itemId +
				", unitName='" + unitName + '\'' +
				'}';
	}
}
