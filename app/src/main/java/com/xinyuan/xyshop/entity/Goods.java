package com.xinyuan.xyshop.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Goods implements Serializable {
	private static final long serialVersionUID = 151410478088851639L;
	private BigDecimal appPrice0 = new BigDecimal(0);
	private BigDecimal appPrice1 = new BigDecimal(0);
	private BigDecimal appPrice2 = new BigDecimal(0);
	private int appUsable = 0;
	private int colorId;
	private int commonId;
	private String goodsFullSpecs;
	private int goodsId;
	private BigDecimal goodsPrice0;
	private BigDecimal goodsPrice1;
	private BigDecimal goodsPrice2;
	private String goodsSerial;
	private String goodsSpecString;
	private String goodsSpecs;
	private int goodsStorage;
	private String imageName;
	private String imageSrc;
	private String promotionEndTime;
	private int promotionId = 0;
	private String promotionStartTime;
	private int promotionState = 0;
	private String promotionTitle;
	private int promotionType = 0;
	private String promotionTypeText;
	private String specValueIds;
	private BigDecimal webPrice0 = new BigDecimal(0);
	private BigDecimal webPrice1 = new BigDecimal(0);
	private BigDecimal webPrice2 = new BigDecimal(0);
	private int webUsable = 0;
	private BigDecimal wechatPrice0 = new BigDecimal(0);
	private BigDecimal wechatPrice1 = new BigDecimal(0);
	private BigDecimal wechatPrice2 = new BigDecimal(0);
	private int wechatUsable = 0;

	public int getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public int getCommonId() {
		return this.commonId;
	}

	public void setCommonId(int commonId) {
		this.commonId = commonId;
	}

	public String getGoodsSpecs() {
		return this.goodsSpecs;
	}

	public void setGoodsSpecs(String goodsSpecs) {
		this.goodsSpecs = goodsSpecs;
	}

	public String getGoodsFullSpecs() {
		return this.goodsFullSpecs;
	}

	public void setGoodsFullSpecs(String goodsFullSpecs) {
		this.goodsFullSpecs = goodsFullSpecs;
	}

	public String getSpecValueIds() {
		return this.specValueIds;
	}

	public void setSpecValueIds(String specValueIds) {
		this.specValueIds = specValueIds;
	}

	public BigDecimal getGoodsPrice0() {
		return this.goodsPrice0;
	}

	public void setGoodsPrice0(BigDecimal goodsPrice0) {
		this.goodsPrice0 = goodsPrice0;
	}

	public BigDecimal getGoodsPrice1() {
		return this.goodsPrice1;
	}

	public void setGoodsPrice1(BigDecimal goodsPrice1) {
		this.goodsPrice1 = goodsPrice1;
	}

	public BigDecimal getGoodsPrice2() {
		return this.goodsPrice2;
	}

	public void setGoodsPrice2(BigDecimal goodsPrice2) {
		this.goodsPrice2 = goodsPrice2;
	}

	public BigDecimal getWebPrice0() {
		return this.webPrice0;
	}

	public void setWebPrice0(BigDecimal webPrice0) {
		this.webPrice0 = webPrice0;
	}

	public BigDecimal getWebPrice1() {
		return this.webPrice1;
	}

	public void setWebPrice1(BigDecimal webPrice1) {
		this.webPrice1 = webPrice1;
	}

	public BigDecimal getWebPrice2() {
		return this.webPrice2;
	}

	public void setWebPrice2(BigDecimal webPrice2) {
		this.webPrice2 = webPrice2;
	}

	public int getWebUsable() {
		return this.webUsable;
	}

	public void setWebUsable(int webUsable) {
		this.webUsable = webUsable;
	}

	public BigDecimal getAppPrice0() {
		return this.appPrice0;
	}

	public void setAppPrice0(BigDecimal appPrice0) {
		this.appPrice0 = appPrice0;
	}

	public BigDecimal getAppPrice1() {
		return this.appPrice1;
	}

	public void setAppPrice1(BigDecimal appPrice1) {
		this.appPrice1 = appPrice1;
	}

	public BigDecimal getAppPrice2() {
		return this.appPrice2;
	}

	public void setAppPrice2(BigDecimal appPrice2) {
		this.appPrice2 = appPrice2;
	}

	public int getAppUsable() {
		return this.appUsable;
	}

	public void setAppUsable(int appUsable) {
		this.appUsable = appUsable;
	}

	public BigDecimal getWechatPrice0() {
		return this.wechatPrice0;
	}

	public void setWechatPrice0(BigDecimal wechatPrice0) {
		this.wechatPrice0 = wechatPrice0;
	}

	public BigDecimal getWechatPrice1() {
		return this.wechatPrice1;
	}

	public void setWechatPrice1(BigDecimal wechatPrice1) {
		this.wechatPrice1 = wechatPrice1;
	}

	public BigDecimal getWechatPrice2() {
		return this.wechatPrice2;
	}

	public void setWechatPrice2(BigDecimal wechatPrice2) {
		this.wechatPrice2 = wechatPrice2;
	}

	public int getWechatUsable() {
		return this.wechatUsable;
	}

	public void setWechatUsable(int wechatUsable) {
		this.wechatUsable = wechatUsable;
	}

	public int getPromotionId() {
		return this.promotionId;
	}

	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}

	public String getPromotionStartTime() {
		return this.promotionStartTime;
	}

	public void setPromotionStartTime(String promotionStartTime) {
		this.promotionStartTime = promotionStartTime;
	}

	public String getPromotionEndTime() {
		return this.promotionEndTime;
	}

	public void setPromotionEndTime(String promotionEndTime) {
		this.promotionEndTime = promotionEndTime;
	}

	public int getPromotionState() {
		return this.promotionState;
	}

	public void setPromotionState(int promotionState) {
		this.promotionState = promotionState;
	}

	public int getPromotionType() {
		return this.promotionType;
	}

	public void setPromotionType(int promotionType) {
		this.promotionType = promotionType;
	}

	public String getPromotionTypeText() {
		return this.promotionTypeText;
	}

	public void setPromotionTypeText(String promotionTypeText) {
		this.promotionTypeText = promotionTypeText;
	}

	public String getPromotionTitle() {
		return this.promotionTitle;
	}

	public void setPromotionTitle(String promotionTitle) {
		this.promotionTitle = promotionTitle;
	}

	public String getGoodsSerial() {
		return this.goodsSerial;
	}

	public void setGoodsSerial(String goodsSerial) {
		this.goodsSerial = goodsSerial;
	}

	public int getColorId() {
		return this.colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}

	public int getGoodsStorage() {
		return this.goodsStorage;
	}

	public void setGoodsStorage(int goodsStorage) {
		this.goodsStorage = goodsStorage;
	}

	public String getImageName() {
		return this.imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageSrc() {
		return this.imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public String getGoodsSpecString() {
		return this.goodsSpecString;
	}

	public void setGoodsSpecString(String goodsSpecString) {
		this.goodsSpecString = goodsSpecString;
	}
}
