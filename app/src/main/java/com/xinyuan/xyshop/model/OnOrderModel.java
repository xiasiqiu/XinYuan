package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.bean.OrderBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/6/20.
 * 虚拟订单列表数据
 */

public class OnOrderModel implements Serializable {

	private static final long serialVersionUID = -9168859284985859238L;
	private List<OrderBean> vofList;

	public List<OrderBean> getOrderList() {
		return vofList;
	}
}
