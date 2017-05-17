package com.xinyuan.xyshop.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GoodsVo implements Serializable {
	private double appPriceMin;
	private int appUsable;
	private String areaInfo;
	private int batchNum0;
	private int batchNum0End;
	private int batchNum1;
	private int batchNum1End;
	private int batchNum2End;
	private double batchPrice0;
	private double batchPrice1;
	private double batchPrice2;
	private int categoryId;
	private int categoryId1;
	private int categoryId2;
	private int categoryId3;
	private String categoryName;
	private int colorId;
	private int commonId;
	private String createTime;
	private Integer evaluateNum = Integer.valueOf(0);
	private int goodsId;
	private List<GoodsImage> goodsImageList;
	private int goodsModal;
	private String goodsName;
	private double goodsPrice;
	private Integer goodsRate = Integer.valueOf(100);
	private int goodsSaleNum;
	private String goodsSpecValueJson;
	private int goodsState;
	private int goodsStatus;
	private int goodsStorage;
	private int goodsVerify;
	private List<Goods> goodsVoList;
	private String imageSrc;
	private int isCommend;
	private int isGift;
	private int isOwnShop;
	private String jingle;
	private String priceRange;
	private int promotionId;
	private int promotionType;
	private String promotionTypeText;
	private String specJson;
	private List<SpecJsonVo> specJsonVoList = new ArrayList();
	private String stateRemark;
	private int storeId;
	private String storeName;
	private String unitName;
	private String verifyRemark;

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

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getJingle() {
		return this.jingle;
	}

	public void setJingle(String jingle) {
		this.jingle = jingle;
	}

	public int getBatchNum0() {
		return this.batchNum0;
	}

	public void setBatchNum0(int batchNum0) {
		this.batchNum0 = batchNum0;
	}

	public int getBatchNum0End() {
		return this.batchNum0End;
	}

	public void setBatchNum0End(int batchNum0End) {
		this.batchNum0End = batchNum0End;
	}

	public int getBatchNum1() {
		return this.batchNum1;
	}

	public void setBatchNum1(int batchNum1) {
		this.batchNum1 = batchNum1;
	}

	public int getBatchNum1End() {
		return this.batchNum1End;
	}

	public void setBatchNum1End(int batchNum1End) {
		this.batchNum1End = batchNum1End;
	}

	public double getBatchNum2() {
		return this.batchPrice2;
	}

	public void setBatchNum2(int batchPrice2) {
		this.batchPrice2 = (double) batchPrice2;
	}

	public double getGoodsPrice() {
		return this.goodsPrice;
	}

	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public double getBatchPrice0() {
		return this.batchPrice0;
	}

	public void setBatchPrice0(double batchPrice0) {
		this.batchPrice0 = batchPrice0;
	}

	public double getBatchPrice1() {
		return this.batchPrice1;
	}

	public void setBatchPrice1(double batchPrice1) {
		this.batchPrice1 = batchPrice1;
	}

	public double getBatchPrice2() {
		return this.batchPrice2;
	}

	public void setBatchPrice2(double batchPrice2) {
		this.batchPrice2 = batchPrice2;
	}

	public int getGoodsModal() {
		return this.goodsModal;
	}

	public void setGoodsModal(int goodsModal) {
		this.goodsModal = goodsModal;
	}

	public Integer getEvaluateNum() {
		return this.evaluateNum;
	}

	public void setEvaluateNum(Integer evaluateNum) {
		this.evaluateNum = evaluateNum;
	}

	public Integer getGoodsRate() {
		return this.goodsRate;
	}

	public void setGoodsRate(Integer goodsRate) {
		this.goodsRate = goodsRate;
	}

	public int getGoodsSaleNum() {
		return this.goodsSaleNum;
	}

	public void setGoodsSaleNum(int goodsSaleNum) {
		this.goodsSaleNum = goodsSaleNum;
	}

	public int getGoodsStorage() {
		return this.goodsStorage;
	}

	public void setGoodsStorage(int goodsStorage) {
		this.goodsStorage = goodsStorage;
	}

	public int getGoodsStatus() {
		return this.goodsStatus;
	}

	public void setGoodsStatus(int goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public int getColorId() {
		return this.colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}

	public String getAreaInfo() {
		return this.areaInfo;
	}

	public void setAreaInfo(String areaInfo) {
		this.areaInfo = areaInfo;
	}

	public int getStoreId() {
		return this.storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getIsOwnShop() {
		return this.isOwnShop;
	}

	public void setIsOwnShop(int isOwnShop) {
		this.isOwnShop = isOwnShop;
	}

	public String getImageSrc() {
		return this.imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public List<GoodsImage> getGoodsImageList() {
		return this.goodsImageList;
	}

	public void setGoodsImageList(List<GoodsImage> goodsImageList) {
		this.goodsImageList = goodsImageList;
	}

	public String getSpecJson() {
		return this.specJson;
	}

	public void setSpecJson(String specJson) {
		this.specJson = specJson;
	}

	public String getGoodsSpecValueJson() {
		return this.goodsSpecValueJson;
	}

	public void setGoodsSpecValueJson(String goodsSpecValueJson) {
		this.goodsSpecValueJson = goodsSpecValueJson;
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getCategoryId1() {
		return this.categoryId1;
	}

	public void setCategoryId1(int categoryId1) {
		this.categoryId1 = categoryId1;
	}

	public int getCategoryId2() {
		return this.categoryId2;
	}

	public void setCategoryId2(int categoryId2) {
		this.categoryId2 = categoryId2;
	}

	public int getCategoryId3() {
		return this.categoryId3;
	}

	public void setCategoryId3(int categoryId3) {
		this.categoryId3 = categoryId3;
	}

	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public int getIsGift() {
		return this.isGift;
	}

	public void setIsGift(int isGift) {
		this.isGift = isGift;
	}

	public int getPromotionId() {
		return this.promotionId;
	}

	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
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

	public String getPriceRange() {
		return this.priceRange;
	}

	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}

	public List<SpecJsonVo> getSpecJsonVoList() {
		return this.specJsonVoList;
	}

	public void setSpecJsonVoList(List<SpecJsonVo> specJsonVoList) {
		this.specJsonVoList = specJsonVoList;
	}

	public int getIsCommend() {
		return this.isCommend;
	}

	public void setIsCommend(int isCommend) {
		this.isCommend = isCommend;
	}

	public int getGoodsState() {
		return this.goodsState;
	}

	public void setGoodsState(int goodsState) {
		this.goodsState = goodsState;
	}

	public String getStateRemark() {
		return this.stateRemark;
	}

	public void setStateRemark(String stateRemark) {
		this.stateRemark = stateRemark;
	}

	public int getGoodsVerify() {
		return this.goodsVerify;
	}

	public void setGoodsVerify(int goodsVerify) {
		this.goodsVerify = goodsVerify;
	}

	public String getVerifyRemark() {
		return this.verifyRemark;
	}

	public void setVerifyRemark(String verifyRemark) {
		this.verifyRemark = verifyRemark;
	}

	public List<Goods> getGoodsVoList() {
		return this.goodsVoList;
	}

	public void setGoodsVoList(List<Goods> goodsVoList) {
		this.goodsVoList = goodsVoList;
	}

	public double getAppPriceMin() {
		return this.appPriceMin;
	}

	public void setAppPriceMin(double appPriceMin) {
		this.appPriceMin = appPriceMin;
	}

	public int getAppUsable() {
		return this.appUsable;
	}

	public void setAppUsable(int appUsable) {
		this.appUsable = appUsable;
	}

	public String toString() {
		return "GoodsVo{goodsId=" + this.goodsId + ", commonId=" + this.commonId + ", goodsName='" + this.goodsName + '\'' + ", jingle='" + this.jingle + '\'' + ", batchNum0=" + this.batchNum0 + ", batchNum0End=" + this.batchNum0End + ", batchNum1=" + this.batchNum1 + ", batchNum1End=" + this.batchNum1End + ", goodsPrice=" + this.goodsPrice + ", batchNum2End=" + this.batchNum2End + ", batchPrice0=" + this.batchPrice0 + ", batchPrice1=" + this.batchPrice1 + ", batchPrice2=" + this.batchPrice2 + ", goodsModal=" + this.goodsModal + ", evaluateNum=" + this.evaluateNum + ", goodsRate=" + this.goodsRate + ", goodsSaleNum=" + this.goodsSaleNum + ", goodsStorage=" + this.goodsStorage + ", goodsStatus=" + this.goodsStatus + ", colorId=" + this.colorId + ", areaInfo='" + this.areaInfo + '\'' + ", storeId=" + this.storeId + ", storeName='" + this.storeName + '\'' + ", isOwnShop=" + this.isOwnShop + ", imageSrc='" + this.imageSrc + '\'' + ", goodsImageList=" + this.goodsImageList + ", unitName='" + this.unitName + '\'' + ", promotionId=" + this.promotionId + ", promotionType=" + this.promotionType + ", promotionTypeText='" + this.promotionTypeText + '\'' + ", priceRange='" + this.priceRange + '\'' + ", specJson='" + this.specJson + '\'' + ", specJsonVoList=" + this.specJsonVoList + ", goodsSpecValueJson='" + this.goodsSpecValueJson + '\'' + ", categoryId=" + this.categoryId + ", categoryName='" + this.categoryName + '\'' + ", createTime='" + this.createTime + '\'' + ", isCommend=" + this.isCommend + ", goodsState=" + this.goodsState + ", stateRemark='" + this.stateRemark + '\'' + ", goodsVerify=" + this.goodsVerify + ", verifyRemark='" + this.verifyRemark + '\'' + ", categoryId1=" + this.categoryId1 + ", categoryId2=" + this.categoryId2 + ", categoryId3=" + this.categoryId3 + ", goodsVoList=" + this.goodsVoList + ", appPriceMin=" + this.appPriceMin + ", appUsable=" + this.appUsable + '}';
	}
}
