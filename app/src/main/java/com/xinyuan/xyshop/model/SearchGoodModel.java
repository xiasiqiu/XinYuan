package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.bean.GoodBradnBean;
import com.xinyuan.xyshop.bean.GoodPropertyBean;
import com.xinyuan.xyshop.bean.GoodListItemBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/8/1.
 * 商品搜索数据
 */

public class SearchGoodModel implements Serializable{
	private static final long serialVersionUID = 6166947983315790707L;
	private List<GoodBradnBean>goodsBrandsList;
	private List<GoodListItemBean>goodsList;
	private int count;
	private int currentPage;
	private int totalPage;
	private List<GoodPropertyBean>GoodsPropertyList;


	public List<GoodBradnBean> getGoodsBrandsList() {
		return goodsBrandsList;
	}

	public List<GoodListItemBean> getGoodsList() {
		return goodsList;
	}

	public int getCount() {
		return count;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public List<GoodPropertyBean> getGoodsPropertyList() {
		return GoodsPropertyList;
	}
}
