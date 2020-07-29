package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.bean.FavGoodBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/7/25.
 * 收藏商品列表数据
 */

public class FavGoodModel implements Serializable {

	private static final long serialVersionUID = -64735593420150512L;
	private List<FavGoodBean> goodsList;

	public List<FavGoodBean> getGoodsList() {
		return goodsList;
	}
}
