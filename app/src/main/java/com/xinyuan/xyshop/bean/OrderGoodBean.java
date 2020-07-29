package com.xinyuan.xyshop.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by fx on 2017/9/14.
 * 订单商品数据
 */

public class OrderGoodBean implements Serializable {
	private static final long serialVersionUID = 4987880235527303653L;
	private long goods_id;
	private String goods_name;
	private BigDecimal goods_price; //商品市场价格
	private BigDecimal store_price; //店铺价格
	private int count;
	private String goodsImg;
	private String spec_info;
	private long goodsCart_id;
	private String evaluateMark;    //是否已经评价（0没评价/1已经评价-追评/2不允许评价）
	private String returnMark; //是否可以退货 1：已经申请 0：可以申请
	private String refundMark;//是否可以退款 1：已经申请 0：可以申请
	private int goodType;
	private long refundId;  //退款ID
	private long returnId;  //退货ID


	public long getRefundId() {
		return refundId;
	}

	public long getReturnId() {
		return returnId;
	}

	public int getGoodType() {
		return goodType;
	}

	public String getReturnMark() {
		return returnMark;
	}

	public String getRefundMark() {
		return refundMark;
	}

	public String getEvaluateMark() {
		return evaluateMark;
	}

	public long getGoodsCart_id() {
		return goodsCart_id;
	}

	public long getGoods_id() {
		return goods_id;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public BigDecimal getGoods_price() {
		return goods_price;
	}

	public BigDecimal getStore_price() {
		return store_price;
	}

	public int getCount() {
		return count;
	}

	public String getGoodsImg() {
		return goodsImg;
	}

	public String getSpec_info() {
		return spec_info;
	}

	public void setGoods_id(long goods_id) {
		this.goods_id = goods_id;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public void setGoods_price(BigDecimal goods_price) {
		this.goods_price = goods_price;
	}

	public void setStore_price(BigDecimal store_price) {
		this.store_price = store_price;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}

	public void setSpec_info(String spec_info) {
		this.spec_info = spec_info;
	}

	public void setGoodsCart_id(long goodsCart_id) {
		this.goodsCart_id = goodsCart_id;
	}

	public void setEvaluateMark(String evaluateMark) {
		this.evaluateMark = evaluateMark;
	}

	public void setReturnMark(String returnMark) {
		this.returnMark = returnMark;
	}

	public void setRefundMark(String refundMark) {
		this.refundMark = refundMark;
	}

	public void setGoodType(int goodType) {
		this.goodType = goodType;
	}

	public void setRefundId(long refundId) {
		this.refundId = refundId;
	}

	public void setReturnId(long returnId) {
		this.returnId = returnId;
	}
}
