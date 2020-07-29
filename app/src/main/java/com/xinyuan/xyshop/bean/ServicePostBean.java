package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/27.
 */

public class ServicePostBean implements Serializable {
	private static final long serialVersionUID = -5827307977652798416L;
	private Long goodsReturnId;
	private Long goodsRefundId;



	public Long getGoodsReturnId() {
		return goodsReturnId;
	}

	public Long getGoodsRefundId() {
		return goodsRefundId;
	}

	@Override
	public String toString() {
		return "ServicePostBean{" +
				"goodsReturnId='" + goodsReturnId + '\'' +
				", goodsRefundId='" + goodsRefundId + '\'' +
				'}';
	}
}
