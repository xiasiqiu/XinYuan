package com.xinyuan.xyshop.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xinyuan.xyshop.adapter.ShopCarAdapter;

/**
 * Created by Administrator on 2017/5/31.
 */

public class ShopCarStoreItem extends AbstractExpandableItem<ShopCarGoodsItem> implements MultiItemEntity {

	private int position;
	private String sotrename;
	private String isDiscount;


	@Override
	public int getLevel() {
		return ShopCarAdapter.TYPE_LEVEL_0;
	}

	@Override
	public int getItemType() {
		return ShopCarAdapter.TYPE_LEVEL_0;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getSotrename() {
		return sotrename;
	}

	public void setSotrename(String sotrename) {
		this.sotrename = sotrename;
	}

	public String getIsDiscount() {
		return isDiscount;
	}

	public void setIsDiscount(String isDiscount) {
		this.isDiscount = isDiscount;
	}
}
