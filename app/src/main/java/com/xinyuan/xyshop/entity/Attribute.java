package com.xinyuan.xyshop.entity;

import java.util.List;
/**
 * Created by fx on 2017/6/10.
 * 分类元素
 */
public class Attribute {
	private int attributeId;
	private String attributeName;
	private int attributeSort;
	private List<AttributeValue> attributeValueList;
	private String attributeValueNames;
	private int categoryId;
	private int isShow;

	public int getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}

	public String getAttributeName() {
		return this.attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public int getAttributeSort() {
		return this.attributeSort;
	}

	public void setAttributeSort(int attributeSort) {
		this.attributeSort = attributeSort;
	}

	public List<AttributeValue> getAttributeValueList() {
		return this.attributeValueList;
	}

	public void setAttributeValueList(List<AttributeValue> attributeValueList) {
		this.attributeValueList = attributeValueList;
	}

	public String getAttributeValueNames() {
		return this.attributeValueNames;
	}

	public void setAttributeValueNames(String attributeValueNames) {
		this.attributeValueNames = attributeValueNames;
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getIsShow() {
		return this.isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}
}
