package com.xinyuan.xyshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */

public class GoodsSpecValueJsonVo {
	private int goodsId;
	private List<Integer> specValueIds;

	public int getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public List<Integer> getSpecValueIds() {
		return this.specValueIds;
	}

	public void setSpecValueIds(List<Integer> specValueIds) {
		this.specValueIds = specValueIds;
	}
}
