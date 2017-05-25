package com.xinyuan.xyshop.entity;

/**
 * Created by Administrator on 2017/5/25.
 */

public class Discount {
	private String discountExplain;
	private int discountId;
	private String discountName;
	private double discountRate;
	private int discountState;
	private String discountStatus;
	private String discountTitle;
	private String discountTitleFinal;
	private String endTime;
	private long goodsCount;
	private long promotionCountDownTime;
	private String promotionCountDownTimeText;
	private String startTime;
	private int storeId;


	public String getDiscountExplain() {
		return discountExplain;
	}

	public void setDiscountExplain(String discountExplain) {
		this.discountExplain = discountExplain;
	}

	public int getDiscountId() {
		return discountId;
	}

	public void setDiscountId(int discountId) {
		this.discountId = discountId;
	}

	public String getDiscountName() {
		return discountName;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	public int getDiscountState() {
		return discountState;
	}

	public void setDiscountState(int discountState) {
		this.discountState = discountState;
	}

	public String getDiscountStatus() {
		return discountStatus;
	}

	public void setDiscountStatus(String discountStatus) {
		this.discountStatus = discountStatus;
	}

	public String getDiscountTitle() {
		return discountTitle;
	}

	public void setDiscountTitle(String discountTitle) {
		this.discountTitle = discountTitle;
	}

	public String getDiscountTitleFinal() {
		return discountTitleFinal;
	}

	public void setDiscountTitleFinal(String discountTitleFinal) {
		this.discountTitleFinal = discountTitleFinal;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public long getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(long goodsCount) {
		this.goodsCount = goodsCount;
	}

	public long getPromotionCountDownTime() {
		return promotionCountDownTime;
	}

	public void setPromotionCountDownTime(long promotionCountDownTime) {
		this.promotionCountDownTime = promotionCountDownTime;
	}

	public String getPromotionCountDownTimeText() {
		return promotionCountDownTimeText;
	}

	public void setPromotionCountDownTimeText(String promotionCountDownTimeText) {
		this.promotionCountDownTimeText = promotionCountDownTimeText;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	@Override
	public String toString() {
		return "Discount{" +
				"discountExplain='" + discountExplain + '\'' +
				", discountId=" + discountId +
				", discountName='" + discountName + '\'' +
				", discountRate=" + discountRate +
				", discountState=" + discountState +
				", discountStatus='" + discountStatus + '\'' +
				", discountTitle='" + discountTitle + '\'' +
				", discountTitleFinal='" + discountTitleFinal + '\'' +
				", endTime='" + endTime + '\'' +
				", goodsCount=" + goodsCount +
				", promotionCountDownTime=" + promotionCountDownTime +
				", promotionCountDownTimeText='" + promotionCountDownTimeText + '\'' +
				", startTime='" + startTime + '\'' +
				", storeId=" + storeId +
				'}';
	}
}
