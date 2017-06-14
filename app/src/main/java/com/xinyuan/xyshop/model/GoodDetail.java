package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.entity.BookBean;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class GoodDetail {
	@Override
	public String toString() {
		return "GoodDetail{" +
				"goodsId=" + goodsId +
				", commonId=" + commonId +
				", goodsName='" + goodsName + '\'' +
				", jingle='" + jingle + '\'' +
				", categoryId=" + categoryId +
				", goodsQRCode='" + goodsQRCode + '\'' +
				", storeId=" + storeId +
				", brandId=" + brandId +
				", goodsBody='" + goodsBody + '\'' +
				", mobileBody='" + mobileBody + '\'' +
				", goodsSerial='" + goodsSerial + '\'' +
				", goodsStatus=" + goodsStatus +
				", colorId=" + colorId +
				", areaId1=" + areaId1 +
				", areaId2=" + areaId2 +
				", goodsFavorite=" + goodsFavorite +
				", goodsClick=" + goodsClick +
				", evaluateNum=" + evaluateNum +
				", goodsRate=" + goodsRate +
				", goodsSaleNum=" + goodsSaleNum +
				", imageSrc='" + imageSrc + '\'' +
				", specJson=" + specJson +
				", goodsSpecNameList=" + goodsSpecNameList +
				", goodsSpecValues='" + goodsSpecValues + '\'' +
				", goodsSpecValueJson=" + goodsSpecValueJson +
				", goodsImageList=" + goodsImageList +
				", goodsAttrList=" + goodsAttrList +
				", formatTop=" + formatTop +
				", formatBottom=" + formatBottom +
				", goodsList=" + goodsList +
				", goodsModal=" + goodsModal +
				", batchNum0=" + batchNum0 +
				", batchNum0End=" + batchNum0End +
				", batchNum1=" + batchNum1 +
				", batchNum1End=" + batchNum1End +
				", batchNum2=" + batchNum2 +
				", batchPrice0=" + batchPrice0 +
				", batchPrice1=" + batchPrice1 +
				", batchPrice2=" + batchPrice2 +
				", webPrice0=" + webPrice0 +
				", webPrice1=" + webPrice1 +
				", webPrice2=" + webPrice2 +
				", webPriceMin=" + webPriceMin +
				", webUsable=" + webUsable +
				", appPrice0=" + appPrice0 +
				", appPrice1=" + appPrice1 +
				", appPrice2=" + appPrice2 +
				", appPriceMin=" + appPriceMin +
				", appUsable=" + appUsable +
				", wechatPrice0=" + wechatPrice0 +
				", wechatPrice1=" + wechatPrice1 +
				", wechatPrice2=" + wechatPrice2 +
				", wechatPriceMin=" + wechatPriceMin +
				", wechatUsable=" + wechatUsable +
				", promotionId=" + promotionId +
				", promotionStartTime='" + promotionStartTime + '\'' +
				", promotionEndTime='" + promotionEndTime + '\'' +
				", promotionType=" + promotionType +
				", promotionTypeText='" + promotionTypeText + '\'' +
				", promotionCountDownTime=" + promotionCountDownTime +
				", promotionCountDownTimeType='" + promotionCountDownTimeType + '\'' +
				", promotionState=" + promotionState +
				", unitName='" + unitName + '\'' +
				", discount='" + discount + '\'' +
				", conformList=" + conformList +
				", voucherTemplate='" + voucherTemplate + '\'' +
				", voucherTemplateList=" + voucherTemplateList +
				", book='" + book + '\'' +
				", bookList=" + bookList +
				", buyNow=" + buyNow +
				", addCart=" + addCart +
				", isGift=" + isGift +
				", giftVoList=" + giftVoList +
				", groupId=" + groupId +
				", contractItem1=" + contractItem1 +
				", contractItem2=" + contractItem2 +
				", contractItem3=" + contractItem3 +
				", contractItem4=" + contractItem4 +
				", contractItem5=" + contractItem5 +
				", contractItem6=" + contractItem6 +
				", contractItem7=" + contractItem7 +
				", contractItem8=" + contractItem8 +
				", contractItem9=" + contractItem9 +
				", contractItem10=" + contractItem10 +
				", contractVoList=" + contractVoList +
				", web=" + web +
				", app=" + app +
				", wechat=" + wechat +
				'}';
	}

	private int goodsId;

	private int commonId;

	private String goodsName;

	private String jingle;

	private int categoryId;

	private String goodsQRCode;

	private int storeId;

	private int brandId;

	private String goodsBody;

	private String mobileBody;

	private String goodsSerial;

	private int goodsStatus;

	private int colorId;

	private int areaId1;

	private int areaId2;

	private int goodsFavorite;

	private int goodsClick;

	private int evaluateNum;

	private int goodsRate;

	private int goodsSaleNum;

	private String imageSrc;

	private List<SpecJson> specJson;

	private List<String> goodsSpecNameList;

	private String goodsSpecValues;

	private List<GoodsSpecValueJson> goodsSpecValueJson;

	private List<GoodsImageList> goodsImageList;

	private List<String> goodsAttrList;

	private int formatTop;

	private int formatBottom;

	private List<Good> goodsList;

	private int goodsModal;

	private int batchNum0;

	private int batchNum0End;

	private int batchNum1;

	private int batchNum1End;

	private int batchNum2;

	private BigDecimal batchPrice0;

	private BigDecimal batchPrice1;

	private BigDecimal batchPrice2;

	private BigDecimal webPrice0;

	private BigDecimal webPrice1;

	private BigDecimal webPrice2;

	private BigDecimal webPriceMin;

	private int webUsable;

	private BigDecimal appPrice0;

	private BigDecimal appPrice1;

	private BigDecimal appPrice2;

	private BigDecimal appPriceMin;

	private int appUsable;

	private BigDecimal wechatPrice0;

	private BigDecimal wechatPrice1;

	private BigDecimal wechatPrice2;

	private BigDecimal wechatPriceMin;

	private int wechatUsable;

	private int promotionId;

	private String promotionStartTime;

	private String promotionEndTime;

	private int promotionType;

	private String promotionTypeText;

	private int promotionCountDownTime;

	private String promotionCountDownTimeType;

	private int promotionState;

	private String unitName;

	private String discount;

	private List<String> conformList;

	private String voucherTemplate;

	private List<String> voucherTemplateList;

	private BookBean book;
	private List<BookBean> bookList;

	private int buyNow;

	private int addCart;

	private int isGift;

	private List<String> giftVoList;

	private int groupId;

	private int contractItem1;

	private int contractItem2;

	private int contractItem3;

	private int contractItem4;

	private int contractItem5;

	private int contractItem6;

	private int contractItem7;

	private int contractItem8;

	private int contractItem9;

	private int contractItem10;

	private List<String> contractVoList;

	private int web;

	private int app;

	private int wechat;

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public int getGoodsId() {
		return this.goodsId;
	}

	public void setCommonId(int commonId) {
		this.commonId = commonId;
	}

	public int getCommonId() {
		return this.commonId;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setJingle(String jingle) {
		this.jingle = jingle;
	}

	public String getJingle() {
		return this.jingle;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setGoodsQRCode(String goodsQRCode) {
		this.goodsQRCode = goodsQRCode;
	}

	public String getGoodsQRCode() {
		return this.goodsQRCode;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getStoreId() {
		return this.storeId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public int getBrandId() {
		return this.brandId;
	}

	public void setGoodsBody(String goodsBody) {
		this.goodsBody = goodsBody;
	}

	public String getGoodsBody() {
		return this.goodsBody;
	}

	public void setMobileBody(String mobileBody) {
		this.mobileBody = mobileBody;
	}

	public String getMobileBody() {
		return this.mobileBody;
	}

	public void setGoodsSerial(String goodsSerial) {
		this.goodsSerial = goodsSerial;
	}

	public String getGoodsSerial() {
		return this.goodsSerial;
	}

	public void setGoodsStatus(int goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public int getGoodsStatus() {
		return this.goodsStatus;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}

	public int getColorId() {
		return this.colorId;
	}

	public void setAreaId1(int areaId1) {
		this.areaId1 = areaId1;
	}

	public int getAreaId1() {
		return this.areaId1;
	}

	public void setAreaId2(int areaId2) {
		this.areaId2 = areaId2;
	}

	public int getAreaId2() {
		return this.areaId2;
	}

	public void setGoodsFavorite(int goodsFavorite) {
		this.goodsFavorite = goodsFavorite;
	}

	public int getGoodsFavorite() {
		return this.goodsFavorite;
	}

	public void setGoodsClick(int goodsClick) {
		this.goodsClick = goodsClick;
	}

	public int getGoodsClick() {
		return this.goodsClick;
	}

	public void setEvaluateNum(int evaluateNum) {
		this.evaluateNum = evaluateNum;
	}

	public int getEvaluateNum() {
		return this.evaluateNum;
	}

	public void setGoodsRate(int goodsRate) {
		this.goodsRate = goodsRate;
	}

	public int getGoodsRate() {
		return this.goodsRate;
	}

	public void setGoodsSaleNum(int goodsSaleNum) {
		this.goodsSaleNum = goodsSaleNum;
	}

	public int getGoodsSaleNum() {
		return this.goodsSaleNum;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public String getImageSrc() {
		return this.imageSrc;
	}

	public void setSpecJson(List<SpecJson> specJson) {
		this.specJson = specJson;
	}

	public List<SpecJson> getSpecJson() {
		return this.specJson;
	}

	public void setString(List<String> goodsSpecNameList) {
		this.goodsSpecNameList = goodsSpecNameList;
	}

	public List<String> getGoodsSpecNameList()

	{
		return this.goodsSpecNameList;
	}

	public void setGoodsSpecValues(String goodsSpecValues) {
		this.goodsSpecValues = goodsSpecValues;
	}

	public String getGoodsSpecValues() {
		return this.goodsSpecValues;
	}

	public void setGoodsSpecValueJson(List<GoodsSpecValueJson> goodsSpecValueJson) {
		this.goodsSpecValueJson = goodsSpecValueJson;
	}

	public List<GoodsSpecValueJson> getGoodsSpecValueJson() {
		return this.goodsSpecValueJson;
	}

	public void setGoodsImageList(List<GoodsImageList> goodsImageList) {
		this.goodsImageList = goodsImageList;
	}

	public List<GoodsImageList> getGoodsImageList() {
		return this.goodsImageList;
	}

	public void setGoodsAttrList(List<String> goodsAttrList) {
		this.goodsAttrList = goodsAttrList;
	}

	public List<String> getGoodsAttrList() {
		return this.goodsAttrList;
	}

	public void setFormatTop(int formatTop) {
		this.formatTop = formatTop;
	}

	public int getFormatTop() {
		return this.formatTop;
	}

	public void setFormatBottom(int formatBottom) {
		this.formatBottom = formatBottom;
	}

	public int getFormatBottom() {
		return this.formatBottom;
	}

	public void setGoodsList(List<Good> goodsList) {
		this.goodsList = goodsList;
	}

	public List<Good> getGoodsList() {
		return this.goodsList;
	}

	public void setGoodsModal(int goodsModal) {
		this.goodsModal = goodsModal;
	}

	public int getGoodsModal() {
		return this.goodsModal;
	}

	public void setBatchNum0(int batchNum0) {
		this.batchNum0 = batchNum0;
	}

	public int getBatchNum0() {
		return this.batchNum0;
	}

	public void setBatchNum0End(int batchNum0End) {
		this.batchNum0End = batchNum0End;
	}

	public int getBatchNum0End() {
		return this.batchNum0End;
	}

	public void setBatchNum1(int batchNum1) {
		this.batchNum1 = batchNum1;
	}

	public int getBatchNum1() {
		return this.batchNum1;
	}

	public void setBatchNum1End(int batchNum1End) {
		this.batchNum1End = batchNum1End;
	}

	public int getBatchNum1End() {
		return this.batchNum1End;
	}

	public void setBatchNum2(int batchNum2) {
		this.batchNum2 = batchNum2;
	}

	public int getBatchNum2() {
		return this.batchNum2;
	}

	public void setBatchPrice0(BigDecimal batchPrice0) {
		this.batchPrice0 = batchPrice0;
	}

	public BigDecimal getBatchPrice0() {
		return this.batchPrice0;
	}

	public void setBatchPrice1(BigDecimal batchPrice1) {
		this.batchPrice1 = batchPrice1;
	}

	public BigDecimal getBatchPrice1() {
		return this.batchPrice1;
	}

	public void setBatchPrice2(BigDecimal batchPrice2) {
		this.batchPrice2 = batchPrice2;
	}

	public BigDecimal getBatchPrice2() {
		return this.batchPrice2;
	}

	public void setWebPrice0(BigDecimal webPrice0) {
		this.webPrice0 = webPrice0;
	}

	public BigDecimal getWebPrice0() {
		return this.webPrice0;
	}

	public void setWebPrice1(BigDecimal webPrice1) {
		this.webPrice1 = webPrice1;
	}

	public BigDecimal getWebPrice1() {
		return this.webPrice1;
	}

	public void setWebPrice2(BigDecimal webPrice2) {
		this.webPrice2 = webPrice2;
	}

	public BigDecimal getWebPrice2() {
		return this.webPrice2;
	}

	public void setWebPriceMin(BigDecimal webPriceMin) {
		this.webPriceMin = webPriceMin;
	}

	public BigDecimal getWebPriceMin() {
		return this.webPriceMin;
	}

	public void setWebUsable(int webUsable) {
		this.webUsable = webUsable;
	}

	public int getWebUsable() {
		return this.webUsable;
	}

	public void setAppPrice0(BigDecimal appPrice0) {
		this.appPrice0 = appPrice0;
	}

	public BigDecimal getAppPrice0() {
		return this.appPrice0;
	}

	public void setAppPrice1(BigDecimal appPrice1) {
		this.appPrice1 = appPrice1;
	}

	public BigDecimal getAppPrice1() {
		return this.appPrice1;
	}

	public void setAppPrice2(BigDecimal appPrice2) {
		this.appPrice2 = appPrice2;
	}

	public BigDecimal getAppPrice2() {
		return this.appPrice2;
	}

	public void setAppPriceMin(BigDecimal appPriceMin) {
		this.appPriceMin = appPriceMin;
	}

	public BigDecimal getAppPriceMin() {
		return this.appPriceMin;
	}

	public void setAppUsable(int appUsable) {
		this.appUsable = appUsable;
	}

	public int getAppUsable() {
		return this.appUsable;
	}

	public void setWechatPrice0(BigDecimal wechatPrice0) {
		this.wechatPrice0 = wechatPrice0;
	}

	public BigDecimal getWechatPrice0() {
		return this.wechatPrice0;
	}

	public void setWechatPrice1(BigDecimal wechatPrice1) {
		this.wechatPrice1 = wechatPrice1;
	}

	public BigDecimal getWechatPrice1() {
		return this.wechatPrice1;
	}

	public void setWechatPrice2(BigDecimal wechatPrice2) {
		this.wechatPrice2 = wechatPrice2;
	}

	public BigDecimal getWechatPrice2() {
		return this.wechatPrice2;
	}

	public void setWechatPriceMin(BigDecimal wechatPriceMin) {
		this.wechatPriceMin = wechatPriceMin;
	}

	public BigDecimal getWechatPriceMin() {
		return this.wechatPriceMin;
	}

	public void setWechatUsable(int wechatUsable) {
		this.wechatUsable = wechatUsable;
	}

	public int getWechatUsable() {
		return this.wechatUsable;
	}

	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}

	public int getPromotionId() {
		return this.promotionId;
	}

	public void setPromotionStartTime(String promotionStartTime) {
		this.promotionStartTime = promotionStartTime;
	}

	public String getPromotionStartTime() {
		return this.promotionStartTime;
	}

	public void setPromotionEndTime(String promotionEndTime) {
		this.promotionEndTime = promotionEndTime;
	}

	public String getPromotionEndTime() {
		return this.promotionEndTime;
	}

	public void setPromotionType(int promotionType) {
		this.promotionType = promotionType;
	}

	public int getPromotionType() {
		return this.promotionType;
	}

	public void setPromotionTypeText(String promotionTypeText) {
		this.promotionTypeText = promotionTypeText;
	}

	public String getPromotionTypeText() {
		return this.promotionTypeText;
	}

	public void setPromotionCountDownTime(int promotionCountDownTime) {
		this.promotionCountDownTime = promotionCountDownTime;
	}

	public int getPromotionCountDownTime() {
		return this.promotionCountDownTime;
	}

	public void setPromotionCountDownTimeType(String promotionCountDownTimeType) {
		this.promotionCountDownTimeType = promotionCountDownTimeType;
	}

	public String getPromotionCountDownTimeType() {
		return this.promotionCountDownTimeType;
	}

	public void setPromotionState(int promotionState) {
		this.promotionState = promotionState;
	}

	public int getPromotionState() {
		return this.promotionState;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitName() {
		return this.unitName;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getDiscount() {
		return this.discount;
	}

	public void setConformList(List<String> conformList) {
		this.conformList = conformList;
	}

	public List<String> getConformList() {
		return this.conformList;
	}

	public void setVoucherTemplate(String voucherTemplate) {
		this.voucherTemplate = voucherTemplate;
	}

	public String getVoucherTemplate() {
		return this.voucherTemplate;
	}

	public void setVoucherTemplateList(List<String> voucherTemplateList) {
		this.voucherTemplateList = voucherTemplateList;
	}

	public List<String> getVoucherTemplateList() {
		return this.voucherTemplateList;
	}

	public void setBook(BookBean book) {
		this.book = book;
	}

	public BookBean getBook() {
		return this.book;
	}

	public void setBookList(List<BookBean> bookList) {
		this.bookList = bookList;
	}

	public List<BookBean> getBookList() {
		return this.bookList;
	}

	public void setBuyNow(int buyNow) {
		this.buyNow = buyNow;
	}

	public int getBuyNow() {
		return this.buyNow;
	}

	public void setAddCart(int addCart) {
		this.addCart = addCart;
	}

	public int getAddCart() {
		return this.addCart;
	}

	public void setIsGift(int isGift) {
		this.isGift = isGift;
	}

	public int getIsGift() {
		return this.isGift;
	}

	public void setGiftVoList(List<String> giftVoList) {
		this.giftVoList = giftVoList;
	}

	public List<String> getGiftVoList() {
		return this.giftVoList;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getGroupId() {
		return this.groupId;
	}

	public void setContractItem1(int contractItem1) {
		this.contractItem1 = contractItem1;
	}

	public int getContractItem1() {
		return this.contractItem1;
	}

	public void setContractItem2(int contractItem2) {
		this.contractItem2 = contractItem2;
	}

	public int getContractItem2() {
		return this.contractItem2;
	}

	public void setContractItem3(int contractItem3) {
		this.contractItem3 = contractItem3;
	}

	public int getContractItem3() {
		return this.contractItem3;
	}

	public void setContractItem4(int contractItem4) {
		this.contractItem4 = contractItem4;
	}

	public int getContractItem4() {
		return this.contractItem4;
	}

	public void setContractItem5(int contractItem5) {
		this.contractItem5 = contractItem5;
	}

	public int getContractItem5() {
		return this.contractItem5;
	}

	public void setContractItem6(int contractItem6) {
		this.contractItem6 = contractItem6;
	}

	public int getContractItem6() {
		return this.contractItem6;
	}

	public void setContractItem7(int contractItem7) {
		this.contractItem7 = contractItem7;
	}

	public int getContractItem7() {
		return this.contractItem7;
	}

	public void setContractItem8(int contractItem8) {
		this.contractItem8 = contractItem8;
	}

	public int getContractItem8() {
		return this.contractItem8;
	}

	public void setContractItem9(int contractItem9) {
		this.contractItem9 = contractItem9;
	}

	public int getContractItem9() {
		return this.contractItem9;
	}

	public void setContractItem10(int contractItem10) {
		this.contractItem10 = contractItem10;
	}

	public int getContractItem10() {
		return this.contractItem10;
	}

	public void setContractVoList(List<String> contractVoList) {
		this.contractVoList = contractVoList;
	}

	public List<String> getContractVoList() {
		return this.contractVoList;
	}

	public void setWeb(int web) {
		this.web = web;
	}

	public int getWeb() {
		return this.web;
	}

	public void setApp(int app) {
		this.app = app;
	}

	public int getApp() {
		return this.app;
	}

	public void setWechat(int wechat) {
		this.wechat = wechat;
	}

	public int getWechat() {
		return this.wechat;
	}


	public static class Good {
		@Override
		public String toString() {
			return "Good{" +
					"goodsId=" + goodsId +
					", commonId=" + commonId +
					", goodsSpecs='" + goodsSpecs + '\'' +
					", goodsSpecString='" + goodsSpecString + '\'' +
					", goodsFullSpecs='" + goodsFullSpecs + '\'' +
					", specValueIds='" + specValueIds + '\'' +
					", goodsPrice0=" + goodsPrice0 +
					", goodsPrice1=" + goodsPrice1 +
					", goodsPrice2=" + goodsPrice2 +
					", webPrice0=" + webPrice0 +
					", webPrice1=" + webPrice1 +
					", webPrice2=" + webPrice2 +
					", webUsable=" + webUsable +
					", appPrice0=" + appPrice0 +
					", appPrice1=" + appPrice1 +
					", appPrice2=" + appPrice2 +
					", appUsable=" + appUsable +
					", wechatPrice0=" + wechatPrice0 +
					", wechatPrice1=" + wechatPrice1 +
					", wechatPrice2=" + wechatPrice2 +
					", wechatUsable=" + wechatUsable +
					", promotionId=" + promotionId +
					", promotionStartTime='" + promotionStartTime + '\'' +
					", promotionEndTime='" + promotionEndTime + '\'' +
					", promotionState=" + promotionState +
					", promotionType=" + promotionType +
					", promotionTypeText='" + promotionTypeText + '\'' +
					", promotionTitle='" + promotionTitle + '\'' +
					", goodsSerial='" + goodsSerial + '\'' +
					", colorId=" + colorId +
					", goodsStorage=" + goodsStorage +
					", imageName='" + imageName + '\'' +
					", imageSrc='" + imageSrc + '\'' +
					", giftVoList=" + giftVoList +
					", isGift=" + isGift +
					", isGroup=" + isGroup +
					", groupPrice=" + groupPrice +
					", limitAmount=" + limitAmount +
					", wechat=" + wechat +
					", web=" + web +
					", app=" + app +
					'}';
		}

		private int goodsId;

		private int commonId;

		private String goodsSpecs;

		private String goodsSpecString;

		private String goodsFullSpecs;

		private String specValueIds;

		private int goodsPrice0;

		private int goodsPrice1;

		private int goodsPrice2;

		private int webPrice0;

		private int webPrice1;

		private int webPrice2;

		private int webUsable;

		private int appPrice0;

		private int appPrice1;

		private int appPrice2;

		private int appUsable;

		private int wechatPrice0;

		private int wechatPrice1;

		private int wechatPrice2;

		private int wechatUsable;

		private int promotionId;

		private String promotionStartTime;

		private String promotionEndTime;

		private int promotionState;

		private int promotionType;

		private String promotionTypeText;

		private String promotionTitle;

		private String goodsSerial;

		private int colorId;

		private int goodsStorage;

		private String imageName;

		private String imageSrc;

		private List<String> giftVoList;

		private int isGift;

		private int isGroup;

		private int groupPrice;

		private int limitAmount;

		private int wechat;

		private int web;

		private int app;

		public void setGoodsId(int goodsId) {
			this.goodsId = goodsId;
		}

		public int getGoodsId() {
			return this.goodsId;
		}

		public void setCommonId(int commonId) {
			this.commonId = commonId;
		}

		public int getCommonId() {
			return this.commonId;
		}

		public void setGoodsSpecs(String goodsSpecs) {
			this.goodsSpecs = goodsSpecs;
		}

		public String getGoodsSpecs() {
			return this.goodsSpecs;
		}

		public void setGoodsSpecString(String goodsSpecString) {
			this.goodsSpecString = goodsSpecString;
		}

		public String getGoodsSpecString() {
			return this.goodsSpecString;
		}

		public void setGoodsFullSpecs(String goodsFullSpecs) {
			this.goodsFullSpecs = goodsFullSpecs;
		}

		public String getGoodsFullSpecs() {
			return this.goodsFullSpecs;
		}

		public void setSpecValueIds(String specValueIds) {
			this.specValueIds = specValueIds;
		}

		public String getSpecValueIds() {
			return this.specValueIds;
		}

		public void setGoodsPrice0(int goodsPrice0) {
			this.goodsPrice0 = goodsPrice0;
		}

		public int getGoodsPrice0() {
			return this.goodsPrice0;
		}

		public void setGoodsPrice1(int goodsPrice1) {
			this.goodsPrice1 = goodsPrice1;
		}

		public int getGoodsPrice1() {
			return this.goodsPrice1;
		}

		public void setGoodsPrice2(int goodsPrice2) {
			this.goodsPrice2 = goodsPrice2;
		}

		public int getGoodsPrice2() {
			return this.goodsPrice2;
		}

		public void setWebPrice0(int webPrice0) {
			this.webPrice0 = webPrice0;
		}

		public int getWebPrice0() {
			return this.webPrice0;
		}

		public void setWebPrice1(int webPrice1) {
			this.webPrice1 = webPrice1;
		}

		public int getWebPrice1() {
			return this.webPrice1;
		}

		public void setWebPrice2(int webPrice2) {
			this.webPrice2 = webPrice2;
		}

		public int getWebPrice2() {
			return this.webPrice2;
		}

		public void setWebUsable(int webUsable) {
			this.webUsable = webUsable;
		}

		public int getWebUsable() {
			return this.webUsable;
		}

		public void setAppPrice0(int appPrice0) {
			this.appPrice0 = appPrice0;
		}

		public int getAppPrice0() {
			return this.appPrice0;
		}

		public void setAppPrice1(int appPrice1) {
			this.appPrice1 = appPrice1;
		}

		public int getAppPrice1() {
			return this.appPrice1;
		}

		public void setAppPrice2(int appPrice2) {
			this.appPrice2 = appPrice2;
		}

		public int getAppPrice2() {
			return this.appPrice2;
		}

		public void setAppUsable(int appUsable) {
			this.appUsable = appUsable;
		}

		public int getAppUsable() {
			return this.appUsable;
		}

		public void setWechatPrice0(int wechatPrice0) {
			this.wechatPrice0 = wechatPrice0;
		}

		public int getWechatPrice0() {
			return this.wechatPrice0;
		}

		public void setWechatPrice1(int wechatPrice1) {
			this.wechatPrice1 = wechatPrice1;
		}

		public int getWechatPrice1() {
			return this.wechatPrice1;
		}

		public void setWechatPrice2(int wechatPrice2) {
			this.wechatPrice2 = wechatPrice2;
		}

		public int getWechatPrice2() {
			return this.wechatPrice2;
		}

		public void setWechatUsable(int wechatUsable) {
			this.wechatUsable = wechatUsable;
		}

		public int getWechatUsable() {
			return this.wechatUsable;
		}

		public void setPromotionId(int promotionId) {
			this.promotionId = promotionId;
		}

		public int getPromotionId() {
			return this.promotionId;
		}

		public void setPromotionStartTime(String promotionStartTime) {
			this.promotionStartTime = promotionStartTime;
		}

		public String getPromotionStartTime() {
			return this.promotionStartTime;
		}

		public void setPromotionEndTime(String promotionEndTime) {
			this.promotionEndTime = promotionEndTime;
		}

		public String getPromotionEndTime() {
			return this.promotionEndTime;
		}

		public void setPromotionState(int promotionState) {
			this.promotionState = promotionState;
		}

		public int getPromotionState() {
			return this.promotionState;
		}

		public void setPromotionType(int promotionType) {
			this.promotionType = promotionType;
		}

		public int getPromotionType() {
			return this.promotionType;
		}

		public void setPromotionTypeText(String promotionTypeText) {
			this.promotionTypeText = promotionTypeText;
		}

		public String getPromotionTypeText() {
			return this.promotionTypeText;
		}

		public void setPromotionTitle(String promotionTitle) {
			this.promotionTitle = promotionTitle;
		}

		public String getPromotionTitle() {
			return this.promotionTitle;
		}

		public void setGoodsSerial(String goodsSerial) {
			this.goodsSerial = goodsSerial;
		}

		public String getGoodsSerial() {
			return this.goodsSerial;
		}

		public void setColorId(int colorId) {
			this.colorId = colorId;
		}

		public int getColorId() {
			return this.colorId;
		}

		public void setGoodsStorage(int goodsStorage) {
			this.goodsStorage = goodsStorage;
		}

		public int getGoodsStorage() {
			return this.goodsStorage;
		}

		public void setImageName(String imageName) {
			this.imageName = imageName;
		}

		public String getImageName() {
			return this.imageName;
		}

		public void setImageSrc(String imageSrc) {
			this.imageSrc = imageSrc;
		}

		public String getImageSrc() {
			return this.imageSrc;
		}

		public void setGiftVoList(List<String> giftVoList) {
			this.giftVoList = giftVoList;
		}

		public List<String> getGiftVoList() {
			return this.giftVoList;
		}

		public void setIsGift(int isGift) {
			this.isGift = isGift;
		}

		public int getIsGift() {
			return this.isGift;
		}

		public void setIsGroup(int isGroup) {
			this.isGroup = isGroup;
		}

		public int getIsGroup() {
			return this.isGroup;
		}

		public void setGroupPrice(int groupPrice) {
			this.groupPrice = groupPrice;
		}

		public int getGroupPrice() {
			return this.groupPrice;
		}

		public void setLimitAmount(int limitAmount) {
			this.limitAmount = limitAmount;
		}

		public int getLimitAmount() {
			return this.limitAmount;
		}

		public void setWechat(int wechat) {
			this.wechat = wechat;
		}

		public int getWechat() {
			return this.wechat;
		}

		public void setWeb(int web) {
			this.web = web;
		}

		public int getWeb() {
			return this.web;
		}

		public void setApp(int app) {
			this.app = app;
		}

		public int getApp() {
			return this.app;
		}

	}


	public class GoodsImageList {
		private int imageId;

		private int commonId;

		private int colorId;

		private String imageName;

		private int imageSort;

		private int isDefault;

		private String imageSrc;

		public void setImageId(int imageId) {
			this.imageId = imageId;
		}

		public int getImageId() {
			return this.imageId;
		}

		public void setCommonId(int commonId) {
			this.commonId = commonId;
		}

		public int getCommonId() {
			return this.commonId;
		}

		public void setColorId(int colorId) {
			this.colorId = colorId;
		}

		public int getColorId() {
			return this.colorId;
		}

		public void setImageName(String imageName) {
			this.imageName = imageName;
		}

		public String getImageName() {
			return this.imageName;
		}

		public void setImageSort(int imageSort) {
			this.imageSort = imageSort;
		}

		public int getImageSort() {
			return this.imageSort;
		}

		public void setIsDefault(int isDefault) {
			this.isDefault = isDefault;
		}

		public int getIsDefault() {
			return this.isDefault;
		}

		public void setImageSrc(String imageSrc) {
			this.imageSrc = imageSrc;
		}

		public String getImageSrc() {
			return this.imageSrc;
		}

	}

	public class GoodsSpecValueJson {
		private int goodsId;

		private List<Integer> specValueIds;

		public void setGoodsId(int goodsId) {
			this.goodsId = goodsId;
		}

		public int getGoodsId() {
			return this.goodsId;
		}

		public void setSpecValueIds(List<Integer> specValueIds) {
			this.specValueIds = specValueIds;
		}

		public List<Integer> getSpecValueIds() {
			return this.specValueIds;
		}

	}

	public class SpecJson {
		private int specId;

		private String specName;

		private List<SpecValueList> specValueList;

		public void setSpecId(int specId) {
			this.specId = specId;
		}

		public int getSpecId() {
			return this.specId;
		}

		public void setSpecName(String specName) {
			this.specName = specName;
		}

		public String getSpecName() {
			return this.specName;
		}

		public void setSpecValueList(List<SpecValueList> specValueList) {
			this.specValueList = specValueList;
		}

		public List<SpecValueList> getSpecValueList() {
			return this.specValueList;
		}

	}


	public class SpecValueList {
		private int specValueId;

		private String specValueName;

		private String imageSrc;

		public void setSpecValueId(int specValueId) {
			this.specValueId = specValueId;
		}

		public int getSpecValueId() {
			return this.specValueId;
		}

		public void setSpecValueName(String specValueName) {
			this.specValueName = specValueName;
		}

		public String getSpecValueName() {
			return this.specValueName;
		}

		public void setImageSrc(String imageSrc) {
			this.imageSrc = imageSrc;
		}

		public String getImageSrc() {
			return this.imageSrc;
		}

	}


}