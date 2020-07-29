package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.bean.OrderBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by fx on 2017/6/20.
 * 订单列表数据
 */

public class OrderModel implements Serializable {

	private static final long serialVersionUID = 6369893594565596187L;
	private List<OrderBean> orderFormList;

	public List<OrderBean> getOrderList() {
		return orderFormList;
	}
}
