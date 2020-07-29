package com.xinyuan.xyshop.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by fx on 2017/7/1.
 * 售后多布局实体类
 */

public class ServiceMultipleItem implements MultiItemEntity {
	public static final int User = 1;
	public static final int Store = 2;
	public static final int User_Span_Size = 4;
	public static final int Store_Span_Size = 4;

	public int itemType;
	public int spanSize;
	private Object obj;


	public ServiceMultipleItem(int itemType, int spanSize,Object obj) {
		this.itemType = itemType;
		this.spanSize = spanSize;
		this.obj=obj;

	}


	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
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
