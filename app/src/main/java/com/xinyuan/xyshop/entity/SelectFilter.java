package com.xinyuan.xyshop.entity;

import com.xinyuan.xyshop.model.BrandModel;

import java.io.Serializable;
import java.util.List;

public class SelectFilter implements Serializable {
	private List<Attribute> attributeList;
	private List<BrandModel.Brand> brandList;
	private List<GoodCategory> categoryList;
	private int express;
	private int gift;
	private int own;
	private int promotion;
	private List<Object> searchCheckedFilterList;

	public int getGift() {
		return this.gift;
	}

	public void setGift(int gift) {
		this.gift = gift;
	}

	public List<Object> getSearchCheckedFilterList() {
		return this.searchCheckedFilterList;
	}

	public void setSearchCheckedFilterList(List<Object> searchCheckedFilterList) {
		this.searchCheckedFilterList = searchCheckedFilterList;
	}

	public int getOwn() {
		return this.own;
	}

	public void setOwn(int own) {
		this.own = own;
	}

	public List<GoodCategory> getCategoryList() {
		return this.categoryList;
	}

	public void setCategoryList(List<GoodCategory> categoryList) {
		this.categoryList = categoryList;
	}

	public int getExpress() {
		return this.express;
	}

	public void setExpress(int express) {
		this.express = express;
	}

	public int getPromotion() {
		return this.promotion;
	}

	public void setPromotion(int promotion) {
		this.promotion = promotion;
	}

	public List<Attribute> getAttributeList() {
		return this.attributeList;
	}

	public void setAttributeList(List<Attribute> attributeList) {
		this.attributeList = attributeList;
	}

	public List<BrandModel.Brand> getBrandList() {
		return this.brandList;
	}

	public void setBrandList(List<BrandModel.Brand> brandList) {
		this.brandList = brandList;
	}
}
