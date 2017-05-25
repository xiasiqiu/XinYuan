package com.xinyuan.xyshop.entity;

/**
 * Created by Administrator on 2017/5/25.
 */

public class PreGoods {
	private int buyNum;
	private int goodsId;
	private String specName;

	public PreGoods(int goodsId, int buyNum, String specName) {
		this.goodsId = goodsId;
		this.buyNum = buyNum;
		this.specName = specName;
	}

	public int getGoodsId() {
		return this.goodsId;
	}

	public String getSpecName() {
		return this.specName;
	}

	public int getCount() {
		return this.buyNum;
	}
}
