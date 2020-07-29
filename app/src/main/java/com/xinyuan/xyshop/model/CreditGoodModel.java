package com.xinyuan.xyshop.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/8/21.
 * 积分商品列表数据
 */

public class CreditGoodModel implements Serializable {
	private static final long serialVersionUID = 911896191831267142L;
	private List<CreditMallModel.CreditGood> goodsList;

	public List<CreditMallModel.CreditGood> getGoodlist() {
		return goodsList;
	}
}
