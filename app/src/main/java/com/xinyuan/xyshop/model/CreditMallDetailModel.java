package com.xinyuan.xyshop.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/6/22.
 * 积分商城详情列表数据
 */

public class CreditMallDetailModel implements Serializable {

	private static final long serialVersionUID = -3463569808883685543L;
	private List<CreditMallModel.CreditGood> goodsList;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public List<CreditMallModel.CreditGood> getGoodsList() {
		return goodsList;
	}
}
