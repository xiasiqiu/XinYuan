package com.xinyuan.xyshop.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/12.
 */

public class Brand implements Serializable {
	private int applyState = 1;
	private String brandEnglish;
	private int brandId;
	private String brandImage;
	private String brandImageSrc;
	private String brandInitial;
	private String brandName;
	private int brandSort = 0;
	private int isRecommend = 0;
	private int showType;
	private int storeId = 0;

	public int getApplyState() {
		return applyState;
	}

	public void setApplyState(int applyState) {
		this.applyState = applyState;
	}

	public String getBrandEnglish() {
		return brandEnglish;
	}

	public void setBrandEnglish(String brandEnglish) {
		this.brandEnglish = brandEnglish;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getBrandImage() {
		return brandImage;
	}

	public void setBrandImage(String brandImage) {
		this.brandImage = brandImage;
	}

	public String getBrandImageSrc() {
		return brandImageSrc;
	}

	public void setBrandImageSrc(String brandImageSrc) {
		this.brandImageSrc = brandImageSrc;
	}

	public String getBrandInitial() {
		return brandInitial;
	}

	public void setBrandInitial(String brandInitial) {
		this.brandInitial = brandInitial;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public int getBrandSort() {
		return brandSort;
	}

	public void setBrandSort(int brandSort) {
		this.brandSort = brandSort;
	}

	public int getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(int isRecommend) {
		this.isRecommend = isRecommend;
	}

	public int getShowType() {
		return showType;
	}

	public void setShowType(int showType) {
		this.showType = showType;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	@Override
	public String toString() {
		return "Brand{" +
				"applyState=" + applyState +
				", brandEnglish='" + brandEnglish + '\'' +
				", brandId=" + brandId +
				", brandImage='" + brandImage + '\'' +
				", brandImageSrc='" + brandImageSrc + '\'' +
				", brandInitial='" + brandInitial + '\'' +
				", brandName='" + brandName + '\'' +
				", brandSort=" + brandSort +
				", isRecommend=" + isRecommend +
				", showType=" + showType +
				", storeId=" + storeId +
				'}';
	}
}
