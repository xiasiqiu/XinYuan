package com.xinyuan.xyshop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */

public class IndexData implements Serializable {
	private int itemId;
	private int itemSort;
	private String itemType;
	private String itemTypeText;
	private List<ItemData>itemData;
	private int specialId;

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getItemSort() {
		return itemSort;
	}

	public void setItemSort(int itemSort) {
		this.itemSort = itemSort;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemTypeText() {
		return itemTypeText;
	}

	public void setItemTypeText(String itemTypeText) {
		this.itemTypeText = itemTypeText;
	}

	public List<ItemData> getItemData() {
		return itemData;
	}

	public void setItemData(List<ItemData> itemData) {
		this.itemData = itemData;
	}

	public int getSpecialId() {
		return specialId;
	}

	public void setSpecialId(int specialId) {
		this.specialId = specialId;
	}

	@Override
	public String toString() {
		return "IndexData{" +
				"itemId=" + itemId +
				", itemSort=" + itemSort +
				", itemType='" + itemType + '\'' +
				", itemTypeText='" + itemTypeText + '\'' +
				", itemData=" + itemData +
				", specialId=" + specialId +
				'}';
	}
}
