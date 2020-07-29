package com.xinyuan.xyshop.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/10/8.
 */

public class ConfirmOrderModel implements Serializable{
	private static final long serialVersionUID = 67586370783172238L;
	private String orderNumber;
	private BigDecimal sumMoney;

	public String getOrderNumber() {
		return orderNumber;
	}

	public BigDecimal getSumMoney() {
		return sumMoney;
	}
}
