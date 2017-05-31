package com.xinyuan.xyshop.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xinyuan.xyshop.adapter.ShopCarAdapter;

/**
 * Created by Administrator on 2017/5/31.
 */

public class ShopCarGoodsItem implements MultiItemEntity {

	private  int StorePosition;
	private  int goddsPostion;
	private String goodsName;

	public int getStorePosition() {
		return StorePosition;
	}

	public void setStorePosition(int storePosition) {
		StorePosition = storePosition;
	}

	public int getGoddsPostion() {
		return goddsPostion;
	}

	public void setGoddsPostion(int goddsPostion) {
		this.goddsPostion = goddsPostion;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Override
	public int getItemType() {
		return ShopCarAdapter.TYPE_LEVEL_1;
	}
}
