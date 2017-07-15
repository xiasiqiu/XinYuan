package com.xinyuan.xyshop.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/7/14.
 */

public class UserHistoryBean implements Serializable {
	private static final long serialVersionUID = -3748653806866613197L;
	private String goodsImg;
	private BigDecimal goodsPrice;
	private long goodsId;

	public String getGoodsImg() {
		return goodsImg;
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public long getGoodsId() {
		return goodsId;
	}

	@Override
	public String toString() {
		return "UserHistoryBean{" +
				"goodsImg='" + goodsImg + '\'' +
				", goodsPrice=" + goodsPrice +
				", goodsId=" + goodsId +
				'}';
	}
}
