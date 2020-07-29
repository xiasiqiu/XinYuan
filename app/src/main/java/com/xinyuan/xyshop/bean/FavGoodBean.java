package com.xinyuan.xyshop.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by fx on 2017/7/25.
 * 收藏商品数据实体类
 */

public class FavGoodBean implements Serializable {
	private static final long serialVersionUID = 4741100310521248106L;
	private long goodsId;
	private String goodsPhoto;
	private String goodsName;
	private BigDecimal goodsPrice;
	private long fId;
	private int goodType;


	public int getGoodType() {
		return goodType;
	}

	public long getfId() {
		return fId;
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public String getGoodsPhoto() {
		return goodsPhoto;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public BigDecimal getGoodPrice() {
		return goodsPrice;
	}

	@Override
	public String toString() {
		return "FavGoodBean{" +
				"goodsId=" + goodsId +
				", goodsPhoto='" + goodsPhoto + '\'' +
				", goodsName='" + goodsName + '\'' +
				", goodsPrice=" + goodsPrice +
				", fId=" + fId +
				'}';
	}
}
