package com.xinyuan.xyshop.entity;

import java.io.Serializable;

public class GoodsImage implements Serializable {
	private int colorId;
	private int commonId;
	private int imageId;
	private String imageName;
	private int imageSort;
	private String imageSrc;
	private int isDefault;

	public int getImageId() {
		return this.imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public int getCommonId() {
		return this.commonId;
	}

	public void setCommonId(int commonId) {
		this.commonId = commonId;
	}

	public int getColorId() {
		return this.colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}

	public String getImageName() {
		return this.imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public int getImageSort() {
		return this.imageSort;
	}

	public void setImageSort(int imageSort) {
		this.imageSort = imageSort;
	}

	public int getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	public String getImageSrc() {
		return this.imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}
}
