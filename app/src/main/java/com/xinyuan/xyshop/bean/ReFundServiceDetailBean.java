package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/27.
 */

public class ReFundServiceDetailBean implements Serializable {
	private static final long serialVersionUID = -1930019218515401363L;
	private String goodsRefundNumber;
	private String goodsRefundCreate;
	private String handlingTime;
	private String goodsRefundResons;
	private String goodsRefundMoney;
	private String goodsRefundStatus;
	private String goodsImg;
	private String spec_info;
	private long goodsId;
	private String goodsName;
	private long goodsCartId;
	private String storeUserPhone;
	private long orderId;


	public long getOrderId() {
		return orderId;
	}

	public String getGoodsRefundNumber() {
		return goodsRefundNumber;
	}

	public void setGoodsRefundNumber(String goodsRefundNumber) {
		this.goodsRefundNumber = goodsRefundNumber;
	}

	public String getGoodsRefundCreate() {
		return goodsRefundCreate;
	}

	public void setGoodsRefundCreate(String goodsRefundCreate) {
		this.goodsRefundCreate = goodsRefundCreate;
	}

	public String getHandlingTime() {
		return handlingTime;
	}

	public void setHandlingTime(String handlingTime) {
		this.handlingTime = handlingTime;
	}

	public String getGoodsRefundResons() {
		return goodsRefundResons;
	}

	public void setGoodsRefundResons(String goodsRefundResons) {
		this.goodsRefundResons = goodsRefundResons;
	}

	public String getGoodsRefundMoney() {
		return goodsRefundMoney;
	}

	public void setGoodsRefundMoney(String goodsRefundMoney) {
		this.goodsRefundMoney = goodsRefundMoney;
	}

	public String getGoodsRefundStatus() {
		return goodsRefundStatus;
	}

	public void setGoodsRefundStatus(String goodsRefundStatus) {
		this.goodsRefundStatus = goodsRefundStatus;
	}

	public String getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}

	public String getSpec_info() {
		return spec_info;
	}

	public void setSpec_info(String spec_info) {
		this.spec_info = spec_info;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public long getGoodsCartId() {
		return goodsCartId;
	}

	public void setGoodsCartId(long goodsCartId) {
		this.goodsCartId = goodsCartId;
	}

	public String getStoreUserPhone() {
		return storeUserPhone;
	}

	public void setStoreUserPhone(String storeUserPhone) {
		this.storeUserPhone = storeUserPhone;
	}
}
