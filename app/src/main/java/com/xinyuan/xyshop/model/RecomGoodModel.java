package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.bean.GoodListItemBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/8/24.
 */

public class RecomGoodModel implements Serializable {
	private static final long serialVersionUID = 4024834837034994031L;
	private List<GoodListItemBean> goodsList;

	public List<GoodListItemBean> getGoodlist() {
		return goodsList;
	}
}
