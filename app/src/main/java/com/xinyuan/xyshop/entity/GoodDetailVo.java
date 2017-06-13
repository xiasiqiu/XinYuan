package com.xinyuan.xyshop.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */

public class GoodDetailVo {
	private BigDecimal appPrice0;
	private BigDecimal appPrice1;
	private BigDecimal appPrice2;
	private BigDecimal appPriceMin;
	private int appUsable;
	private int areaId1;
	private int areaId2;
	private int batchNum0;
	private int batchNum0End;
	private int batchNum1;
	private int batchNum1End;
	private int batchNum2;
	private BigDecimal batchPrice0;
	private BigDecimal batchPrice1;
	private BigDecimal batchPrice2;
	private BookBean book;
	private List<BookBean> bookList;
	private int brandId;
	private int categoryId;
	private int colorId;
	private int commonId;
	private Discount discount;
	private Integer evaluateNum;
	private int formatBottom;
	private int formatTop;
	private List<GoodGift> giftVoList;
	private List<GoodsAttrVo> goodsAttrList;
	private String goodsBody;
	private int goodsClick;
	private int goodsFavorite;
	private int goodsId;
	private List<GoodsImage> goodsImageList;
	private List<Goods> goodsList;
	private int goodsModal;
	private String goodsName;
	private String goodsPrice;
	private String goodsQRCode;
	private Integer goodsRate;
	private int goodsSaleNum;
	private String goodsSerial;
	private List<String> goodsSpecNameList = new ArrayList();
	private List<GoodsSpecValueJsonVo> goodsSpecValueJson;
	private String goodsSpecValues;
	private int goodsStatus;
	private String imageSrc;
	private int isGift;
	private String jingle;
	private String mobileBody;
	private long promotionCountDownTime;
	private String promotionCountDownTimeType;
	private String promotionEndTime;
	private int promotionId;
	private String promotionStartTime;
	private int promotionState;
	private int promotionType;
	private String promotionTypeText;
	private List<SpecJsonVo> specJson = new ArrayList();
	private int storeId;
	private String unitName;
	private BigDecimal webPrice0;
	private BigDecimal webPrice1;
	private BigDecimal webPrice2;
	private int webUsable;
	private BigDecimal wechatPrice0;
	private BigDecimal wechatPrice1;
	private BigDecimal wechatPrice2;
	private int wechatUsable;

	public BigDecimal getAppPrice0() {
		return appPrice0;
	}

	public void setAppPrice0(BigDecimal appPrice0) {
		this.appPrice0 = appPrice0;
	}

	public BigDecimal getAppPrice1() {
		return appPrice1;
	}

	public void setAppPrice1(BigDecimal appPrice1) {
		this.appPrice1 = appPrice1;
	}

	public BigDecimal getAppPrice2() {
		return appPrice2;
	}

	public void setAppPrice2(BigDecimal appPrice2) {
		this.appPrice2 = appPrice2;
	}

	public BigDecimal getAppPriceMin() {
		return appPriceMin;
	}

	public void setAppPriceMin(BigDecimal appPriceMin) {
		this.appPriceMin = appPriceMin;
	}

	public int getAppUsable() {
		return appUsable;
	}

	public void setAppUsable(int appUsable) {
		this.appUsable = appUsable;
	}

	public int getAreaId1() {
		return areaId1;
	}

	public void setAreaId1(int areaId1) {
		this.areaId1 = areaId1;
	}

	public int getAreaId2() {
		return areaId2;
	}

	public void setAreaId2(int areaId2) {
		this.areaId2 = areaId2;
	}

	public int getBatchNum0() {
		return batchNum0;
	}

	public void setBatchNum0(int batchNum0) {
		this.batchNum0 = batchNum0;
	}

	public int getBatchNum0End() {
		return batchNum0End;
	}

	public void setBatchNum0End(int batchNum0End) {
		this.batchNum0End = batchNum0End;
	}

	public int getBatchNum1() {
		return batchNum1;
	}

	public void setBatchNum1(int batchNum1) {
		this.batchNum1 = batchNum1;
	}

	public int getBatchNum1End() {
		return batchNum1End;
	}

	public void setBatchNum1End(int batchNum1End) {
		this.batchNum1End = batchNum1End;
	}

	public int getBatchNum2() {
		return batchNum2;
	}

	public void setBatchNum2(int batchNum2) {
		this.batchNum2 = batchNum2;
	}

	public BigDecimal getBatchPrice0() {
		return batchPrice0;
	}

	public void setBatchPrice0(BigDecimal batchPrice0) {
		this.batchPrice0 = batchPrice0;
	}

	public BigDecimal getBatchPrice1() {
		return batchPrice1;
	}

	public void setBatchPrice1(BigDecimal batchPrice1) {
		this.batchPrice1 = batchPrice1;
	}

	public BigDecimal getBatchPrice2() {
		return batchPrice2;
	}

	public void setBatchPrice2(BigDecimal batchPrice2) {
		this.batchPrice2 = batchPrice2;
	}

	public BookBean getBook() {
		return book;
	}

	public void setBook(BookBean book) {
		this.book = book;
	}

	public List<BookBean> getBookList() {
		return bookList;
	}

	public void setBookList(List<BookBean> bookList) {
		this.bookList = bookList;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getColorId() {
		return colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}

	public int getCommonId() {
		return commonId;
	}

	public void setCommonId(int commonId) {
		this.commonId = commonId;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public Integer getEvaluateNum() {
		return evaluateNum;
	}

	public void setEvaluateNum(Integer evaluateNum) {
		this.evaluateNum = evaluateNum;
	}

	public int getFormatBottom() {
		return formatBottom;
	}

	public void setFormatBottom(int formatBottom) {
		this.formatBottom = formatBottom;
	}

	public int getFormatTop() {
		return formatTop;
	}

	public void setFormatTop(int formatTop) {
		this.formatTop = formatTop;
	}

	public List<GoodGift> getGiftVoList() {
		return giftVoList;
	}

	public void setGiftVoList(List<GoodGift> giftVoList) {
		this.giftVoList = giftVoList;
	}

	public List<GoodsAttrVo> getGoodsAttrList() {
		return goodsAttrList;
	}

	public void setGoodsAttrList(List<GoodsAttrVo> goodsAttrList) {
		this.goodsAttrList = goodsAttrList;
	}

	public String getGoodsBody() {
		return goodsBody;
	}

	public void setGoodsBody(String goodsBody) {
		this.goodsBody = goodsBody;
	}

	public int getGoodsClick() {
		return goodsClick;
	}

	public void setGoodsClick(int goodsClick) {
		this.goodsClick = goodsClick;
	}

	public int getGoodsFavorite() {
		return goodsFavorite;
	}

	public void setGoodsFavorite(int goodsFavorite) {
		this.goodsFavorite = goodsFavorite;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public List<GoodsImage> getGoodsImageList() {
		return goodsImageList;
	}

	public void setGoodsImageList(List<GoodsImage> goodsImageList) {
		this.goodsImageList = goodsImageList;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public int getGoodsModal() {
		return goodsModal;
	}

	public void setGoodsModal(int goodsModal) {
		this.goodsModal = goodsModal;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsQRCode() {
		return goodsQRCode;
	}

	public void setGoodsQRCode(String goodsQRCode) {
		this.goodsQRCode = goodsQRCode;
	}

	public Integer getGoodsRate() {
		return goodsRate;
	}

	public void setGoodsRate(Integer goodsRate) {
		this.goodsRate = goodsRate;
	}

	public int getGoodsSaleNum() {
		return goodsSaleNum;
	}

	public void setGoodsSaleNum(int goodsSaleNum) {
		this.goodsSaleNum = goodsSaleNum;
	}

	public String getGoodsSerial() {
		return goodsSerial;
	}

	public void setGoodsSerial(String goodsSerial) {
		this.goodsSerial = goodsSerial;
	}

	public List<String> getGoodsSpecNameList() {
		return goodsSpecNameList;
	}

	public void setGoodsSpecNameList(List<String> goodsSpecNameList) {
		this.goodsSpecNameList = goodsSpecNameList;
	}

	public List<GoodsSpecValueJsonVo> getGoodsSpecValueJson() {
		return goodsSpecValueJson;
	}

	public void setGoodsSpecValueJson(List<GoodsSpecValueJsonVo> goodsSpecValueJson) {
		this.goodsSpecValueJson = goodsSpecValueJson;
	}

	public String getGoodsSpecValues() {
		return goodsSpecValues;
	}

	public void setGoodsSpecValues(String goodsSpecValues) {
		this.goodsSpecValues = goodsSpecValues;
	}

	public int getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(int goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public int getIsGift() {
		return isGift;
	}

	public void setIsGift(int isGift) {
		this.isGift = isGift;
	}

	public String getJingle() {
		return jingle;
	}

	public void setJingle(String jingle) {
		this.jingle = jingle;
	}

	public String getMobileBody() {
		return mobileBody;
	}

	public void setMobileBody(String mobileBody) {
		this.mobileBody = mobileBody;
	}

	public long getPromotionCountDownTime() {
		return promotionCountDownTime;
	}

	public void setPromotionCountDownTime(long promotionCountDownTime) {
		this.promotionCountDownTime = promotionCountDownTime;
	}

	public String getPromotionCountDownTimeType() {
		return promotionCountDownTimeType;
	}

	public void setPromotionCountDownTimeType(String promotionCountDownTimeType) {
		this.promotionCountDownTimeType = promotionCountDownTimeType;
	}

	public String getPromotionEndTime() {
		return promotionEndTime;
	}

	public void setPromotionEndTime(String promotionEndTime) {
		this.promotionEndTime = promotionEndTime;
	}

	public int getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}

	public String getPromotionStartTime() {
		return promotionStartTime;
	}

	public void setPromotionStartTime(String promotionStartTime) {
		this.promotionStartTime = promotionStartTime;
	}

	public int getPromotionState() {
		return promotionState;
	}

	public void setPromotionState(int promotionState) {
		this.promotionState = promotionState;
	}

	public int getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(int promotionType) {
		this.promotionType = promotionType;
	}

	public String getPromotionTypeText() {
		return promotionTypeText;
	}

	public void setPromotionTypeText(String promotionTypeText) {
		this.promotionTypeText = promotionTypeText;
	}

	public List<SpecJsonVo> getSpecJson() {
		return specJson;
	}

	public void setSpecJson(List<SpecJsonVo> specJson) {
		this.specJson = specJson;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public BigDecimal getWebPrice0() {
		return webPrice0;
	}

	public void setWebPrice0(BigDecimal webPrice0) {
		this.webPrice0 = webPrice0;
	}

	public BigDecimal getWebPrice1() {
		return webPrice1;
	}

	public void setWebPrice1(BigDecimal webPrice1) {
		this.webPrice1 = webPrice1;
	}

	public BigDecimal getWebPrice2() {
		return webPrice2;
	}

	public void setWebPrice2(BigDecimal webPrice2) {
		this.webPrice2 = webPrice2;
	}

	public int getWebUsable() {
		return webUsable;
	}

	public void setWebUsable(int webUsable) {
		this.webUsable = webUsable;
	}

	public BigDecimal getWechatPrice0() {
		return wechatPrice0;
	}

	public void setWechatPrice0(BigDecimal wechatPrice0) {
		this.wechatPrice0 = wechatPrice0;
	}

	public BigDecimal getWechatPrice1() {
		return wechatPrice1;
	}

	public void setWechatPrice1(BigDecimal wechatPrice1) {
		this.wechatPrice1 = wechatPrice1;
	}

	public BigDecimal getWechatPrice2() {
		return wechatPrice2;
	}

	public void setWechatPrice2(BigDecimal wechatPrice2) {
		this.wechatPrice2 = wechatPrice2;
	}

	public int getWechatUsable() {
		return wechatUsable;
	}

	public void setWechatUsable(int wechatUsable) {
		this.wechatUsable = wechatUsable;
	}

	@Override
	public String toString() {
		return "GoodDetailVo{" +
				"appPrice0=" + appPrice0 +
				", appPrice1=" + appPrice1 +
				", appPrice2=" + appPrice2 +
				", appPriceMin=" + appPriceMin +
				", appUsable=" + appUsable +
				", areaId1=" + areaId1 +
				", areaId2=" + areaId2 +
				", batchNum0=" + batchNum0 +
				", batchNum0End=" + batchNum0End +
				", batchNum1=" + batchNum1 +
				", batchNum1End=" + batchNum1End +
				", batchNum2=" + batchNum2 +
				", batchPrice0=" + batchPrice0 +
				", batchPrice1=" + batchPrice1 +
				", batchPrice2=" + batchPrice2 +
				", book=" + book +
				", bookList=" + bookList +
				", brandId=" + brandId +
				", categoryId=" + categoryId +
				", colorId=" + colorId +
				", commonId=" + commonId +
				", discount=" + discount +
				", evaluateNum=" + evaluateNum +
				", formatBottom=" + formatBottom +
				", formatTop=" + formatTop +
				", giftVoList=" + giftVoList +
				", goodsAttrList=" + goodsAttrList +
				", goodsBody='" + goodsBody + '\'' +
				", goodsClick=" + goodsClick +
				", goodsFavorite=" + goodsFavorite +
				", goodsId=" + goodsId +
				", goodsImageList=" + goodsImageList +
				", goodsList=" + goodsList +
				", goodsModal=" + goodsModal +
				", goodsName='" + goodsName + '\'' +
				", goodsPrice='" + goodsPrice + '\'' +
				", goodsQRCode='" + goodsQRCode + '\'' +
				", goodsRate=" + goodsRate +
				", goodsSaleNum=" + goodsSaleNum +
				", goodsSerial='" + goodsSerial + '\'' +
				", goodsSpecNameList=" + goodsSpecNameList +
				", goodsSpecValueJson=" + goodsSpecValueJson +
				", goodsSpecValues='" + goodsSpecValues + '\'' +
				", goodsStatus=" + goodsStatus +
				", imageSrc='" + imageSrc + '\'' +
				", isGift=" + isGift +
				", jingle='" + jingle + '\'' +
				", mobileBody='" + mobileBody + '\'' +
				", promotionCountDownTime=" + promotionCountDownTime +
				", promotionCountDownTimeType='" + promotionCountDownTimeType + '\'' +
				", promotionEndTime='" + promotionEndTime + '\'' +
				", promotionId=" + promotionId +
				", promotionStartTime='" + promotionStartTime + '\'' +
				", promotionState=" + promotionState +
				", promotionType=" + promotionType +
				", promotionTypeText='" + promotionTypeText + '\'' +
				", specJson=" + specJson +
				", storeId=" + storeId +
				", unitName='" + unitName + '\'' +
				", webPrice0=" + webPrice0 +
				", webPrice1=" + webPrice1 +
				", webPrice2=" + webPrice2 +
				", webUsable=" + webUsable +
				", wechatPrice0=" + wechatPrice0 +
				", wechatPrice1=" + wechatPrice1 +
				", wechatPrice2=" + wechatPrice2 +
				", wechatUsable=" + wechatUsable +
				'}';
	}
}
