package com.xinyuan.xyshop.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by fx on 2017/5/4 0004.
 */

public class CreditMultipleItem implements MultiItemEntity {
	public static final int AD = 1;
	public static final int MODULE = 2;


	public static final int AD_SPAN_SIZE = 2;
	public static final int MODULE_SPAN_SIZE = 2;


	public int itemType;
	public int spanSize;


	public CreditMultipleItem(int itemType, int spanSize) {
		this.itemType = itemType;
		this.spanSize = spanSize;

	}

	@Override
	public int getItemType() {
		return itemType;
	}

	public int getSpanSize() {
		return spanSize;
	}

	public void setSpanSize(int spanSize) {
		this.spanSize = spanSize;
	}

	@Override
	public String toString() {
		return "HomeMultipleItem{" +
				"itemType=" + itemType +
				", spanSize=" + spanSize +
				'}';
	}
}
