package com.xinyuan.xyshop.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by fx on 2017/6/4 0004.
 * 积分商城模块数据实体类
 *
 */

public class CreditMultipleItem implements MultiItemEntity {
	public static final int C_AD = 1;
	public static final int C_MODULE = 2;

	public static final int R_AD = 3;
	public static final int R_MODULE = 4;

	public static final int G_AD = 5;
	public static final int G_MODULE = 6;


	public static final int C_AD_SPAN_SIZE = 2;
	public static final int C_MODULE_SPAN_SIZE = 2;

	public static final int R_AD_SPAN_SIZE = 2;
	public static final int R_MODULE_SPAN_SIZE = 2;

	public static final int G_AD_SPAN_SIZE = 2;
	public static final int G_MODULE_SPAN_SIZE = 2;


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
