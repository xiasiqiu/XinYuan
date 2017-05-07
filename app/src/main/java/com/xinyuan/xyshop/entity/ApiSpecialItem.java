package com.xinyuan.xyshop.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fx on 2017/5/3 0003.
 */

public class ApiSpecialItem implements Serializable {
	private String itemData = "";
	private int itemId;
	private int itemSort = 999;
	private String itemType;
	private String itemTypeText;
	private int itemUsable = 0;
	private int specialId;

	public int getItemId() {
		return this.itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getSpecialId() {
		return this.specialId;
	}

	public void setSpecialId(int specialId) {
		this.specialId = specialId;
	}

	public String getItemType() {
		return this.itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemData() {
		return itemData;
	}

	public void setItemData(String itemData) {
		this.itemData = itemData;
	}

	public int getItemUsable() {
		return this.itemUsable;
	}

	public void setItemUsable(int itemUsable) {
		this.itemUsable = itemUsable;
	}

	public int getItemSort() {
		return this.itemSort;
	}

	public void setItemSort(int itemSort) {
		this.itemSort = itemSort;
	}

	public String getItemTypeText() {
		return this.itemTypeText;
	}

	public void setItemTypeText(String itemTypeText) {
		this.itemTypeText = itemTypeText;
	}

	public String toString() {
		return "ApiSpecialItem{itemId=" + this.itemId + ", specialId=" + this.specialId + ", itemType='" + this.itemType + '\'' + ", itemData='" + this.itemData + '\'' + ", itemUsable=" + this.itemUsable + ", itemSort=" + this.itemSort + ", itemTypeText='" + this.itemTypeText + '\'' + '}';
	}
}
