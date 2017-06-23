package com.xinyuan.xyshop.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/6/23.
 */

public class GoodListItem implements Serializable {
	private static final long serialVersionUID = 6579021163019689196L;
	private String goodsId;
	private String goodsType;
	private String imageUrl;
	private String consumeNum;
	private String goodsActive;
	private BigDecimal goodsPrice;
	private String goodsName;

	public String getGoodsId() {
		return goodsId;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getConsumeNum() {
		return consumeNum;
	}

	public String getGoodsActive() {
		return goodsActive;
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public String getGoodsName() {
		return goodsName;
	}
}
