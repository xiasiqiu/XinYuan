package com.xinyuan.xyshop.entity;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/5/25.
 */

public class BookBean {
	private int commonId;
	private String createTime;
	private BigDecimal downPayment;
	private int downPercent;
	private String downTime;
	private BigDecimal finalPayment;
	private int goodsId;
	private String goodsName;
	private int storeId;
	private int totalPayment;

	public int getCommonId() {
		return commonId;
	}

	public void setCommonId(int commonId) {
		this.commonId = commonId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getDownPayment() {
		return downPayment;
	}

	public void setDownPayment(BigDecimal downPayment) {
		this.downPayment = downPayment;
	}

	public int getDownPercent() {
		return downPercent;
	}

	public void setDownPercent(int downPercent) {
		this.downPercent = downPercent;
	}

	public String getDownTime() {
		return downTime;
	}

	public void setDownTime(String downTime) {
		this.downTime = downTime;
	}

	public BigDecimal getFinalPayment() {
		return finalPayment;
	}

	public void setFinalPayment(BigDecimal finalPayment) {
		this.finalPayment = finalPayment;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(int totalPayment) {
		this.totalPayment = totalPayment;
	}

	@Override
	public String toString() {
		return "BookBean{" +
				"commonId=" + commonId +
				", createTime='" + createTime + '\'' +
				", downPayment=" + downPayment +
				", downPercent=" + downPercent +
				", downTime='" + downTime + '\'' +
				", finalPayment=" + finalPayment +
				", goodsId=" + goodsId +
				", goodsName='" + goodsName + '\'' +
				", storeId=" + storeId +
				", totalPayment=" + totalPayment +
				'}';
	}
}
