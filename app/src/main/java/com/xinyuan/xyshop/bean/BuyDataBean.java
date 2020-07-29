package com.xinyuan.xyshop.bean;

/**
 * Created by fx on 2017/5/25.
 * 购物数据
 */

public class BuyDataBean {
	private int cartId;
	private int goodsId;
	private int buyNum;

	public BuyDataBean(int goodsId, int buyNum) {
		this.goodsId = goodsId;
		this.buyNum = buyNum;
	}

	public BuyDataBean(int goodsId, int cartId, int buyNum) {
		this.goodsId = goodsId;
		this.buyNum = buyNum;
		this.cartId = cartId;
	}

	public int getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public int getBuyNum() {
		return this.buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	public int getCartId() {
		return this.cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
}

