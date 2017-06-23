package com.xinyuan.xyshop.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getGoodsPrice() {
		return this.goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsQRCode() {
		return this.goodsQRCode;
	}

	public void setGoodsQRCode(String goodsQRCode) {
		this.goodsQRCode = goodsQRCode;
	}

	public int getStoreId() {
		return this.storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getBrandId() {
		return this.brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getGoodsBody() {
		return this.goodsBody;
	}

	public void setGoodsBody(String goodsBody) {
		this.goodsBody = goodsBody;
	}

	public String getMobileBody() {
		return this.mobileBody;
	}

	public void setMobileBody(String mobileBody) {
		this.mobileBody = mobileBody;
	}

	public String getGoodsSerial() {
		return this.goodsSerial;
	}

	public void setGoodsSerial(String goodsSerial) {
		this.goodsSerial = goodsSerial;
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

	public int getAreaId1() {
		return this.areaId1;
	}

	public void setAreaId1(int areaId1) {
		this.areaId1 = areaId1;
	}

	public int getAreaId2() {
		return this.areaId2;
	}

	public void setAreaId2(int areaId2) {
		this.areaId2 = areaId2;
	}

	public int getGoodsFavorite() {
		return this.goodsFavorite;
	}

	public void setGoodsFavorite(int goodsFavorite) {
		this.goodsFavorite = goodsFavorite;
	}

	public int getGoodsClick() {
		return this.goodsClick;
	}

	public void setGoodsClick(int goodsClick) {
		this.goodsClick = goodsClick;
	}

	public String getImageSrc() {
		return this.imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public String getGoodsSpecValues() {
		return this.goodsSpecValues;
	}

	public void setGoodsSpecValues(String goodsSpecValues) {
		this.goodsSpecValues = goodsSpecValues;
	}

	public List<GoodsImage> getGoodsImageList() {
		return this.goodsImageList;
	}

	public void setGoodsImageList(List<GoodsImage> goodsImageList) {
		this.goodsImageList = goodsImageList;
	}

	public int getFormatTop() {
		return this.formatTop;
	}

	public void setFormatTop(int formatTop) {
		this.formatTop = formatTop;
	}

	public int getFormatBottom() {
		return this.formatBottom;
	}

	public void setFormatBottom(int formatBottom) {
		this.formatBottom = formatBottom;
	}

	public List<Goods> getGoodsList() {
		return this.goodsList;
	}

	public void setGoodsVoList(List<Goods> list) {
		this.goodsList = this.goodsList;
	}

	public int getGoodsModal() {
		return this.goodsModal;
	}

	public void setGoodsModal(int goodsModal) {
		this.goodsModal = goodsModal;
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

	public int getBatchNum2() {
		return this.batchNum2;
	}

	public void setBatchNum2(int batchNum2) {
		this.batchNum2 = batchNum2;
	}

	public BigDecimal getBatchPrice0() {
		return this.batchPrice0;
	}

	public void setBatchPrice0(BigDecimal batchPrice0) {
		this.batchPrice0 = batchPrice0;
	}

	public BigDecimal getBatchPrice1() {
		return this.batchPrice1;
	}

	public void setBatchPrice1(BigDecimal batchPrice1) {
		this.batchPrice1 = batchPrice1;
	}

	public BigDecimal getBatchPrice2() {
		return this.batchPrice2;
	}

	public void setBatchPrice2(BigDecimal batchPrice2) {
		this.batchPrice2 = batchPrice2;
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

	public long getPromotionCountDownTime() {
		return this.promotionCountDownTime;
	}

	public void setPromotionCountDownTime(long promotionCountDownTime) {
		this.promotionCountDownTime = promotionCountDownTime;
	}

	public String getPromotionCountDownTimeType() {
		return this.promotionCountDownTimeType;
	}

	public void setPromotionCountDownTimeType(String promotionCountDownTimeType) {
		this.promotionCountDownTimeType = promotionCountDownTimeType;
	}

	public int getPromotionState() {
		return this.promotionState;
	}

	public void setPromotionState(int promotionState) {
		this.promotionState = promotionState;
	}

	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Discount getDiscount() {
		return this.discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public List<SpecJsonVo> getSpecJson() {
		return this.specJson;
	}

	public void setSpecJson(List<SpecJsonVo> specJson) {
		this.specJson = specJson;
	}

	public List<String> getGoodsSpecNameList() {
		return this.goodsSpecNameList;
	}

	public void setGoodsSpecNameList(List<String> goodsSpecNameList) {
		this.goodsSpecNameList = goodsSpecNameList;
	}



	public List<GoodsAttrVo> getGoodsAttrList() {
		return this.goodsAttrList;
	}

	public void setGoodsAttrList(List<GoodsAttrVo> goodsAttrList) {
		this.goodsAttrList = goodsAttrList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
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

	public BigDecimal getAppPriceMin() {
		return this.appPriceMin;
	}

	public void setAppPriceMin(BigDecimal appPriceMin) {
		this.appPriceMin = appPriceMin;
	}

	public int getIsGift() {
		return this.isGift;
	}

	public void setIsGift(int isGift) {
		this.isGift = isGift;
	}

	public List<GoodGift> getGiftVoList() {
		return this.giftVoList;
	}

	public void setGiftVoList(List<GoodGift> giftVoList) {
		this.giftVoList = giftVoList;
	}

	public BookBean getBook() {
		return this.book;
	}

	public void setBook(BookBean book) {
		this.book = book;
	}

	public List<BookBean> getBookList() {
		return this.bookList;
	}

	public void setBookList(List<BookBean> bookList) {
		this.bookList = bookList;
	}
}
