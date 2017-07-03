package com.xinyuan.xyshop.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2017/7/1.
 */

public class ServiceMultipleItem implements MultiItemEntity {
	public static final int User = 1;
	public static final int Store = 2;
	public static final int User_Span_Size = 4;
	public static final int Store_Span_Size = 4;

	public int itemType;
	public int spanSize;


	public ServiceMultipleItem(int itemType, int spanSize) {
		this.itemType = itemType;
		this.spanSize = spanSize;

	}


	@Override
	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public int getSpanSize() {
		return spanSize;
	}

	public void setSpanSize(int spanSize) {
		this.spanSize = spanSize;
	}
}
