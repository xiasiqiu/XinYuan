package com.xinyuan.xyshop.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2017/5/27.
 */

public class ShopCarMultipleItem implements MultiItemEntity {

	public static final int STORE_TITLE = 1;
	public static final int GOODS_ITEM = 2;
	public static final int RECOME_TITLE = 3;
	public static final int RECOME_GOOD = 4;

	public static final int STORE_TITLE_SPAN_SIZE = 4;
	public static final int GOODS_ITEM_SPAN_SIZE = 4;
	public static final int RECOME_TITLE_SPAN_SIZE = 4;
	public static final int RECOME_GOOD_SPAN_SIZE = 2;
	public int itemType;
	public int spanSize;

	public ShopCarMultipleItem(int itemType, int spanSize) {
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


}
