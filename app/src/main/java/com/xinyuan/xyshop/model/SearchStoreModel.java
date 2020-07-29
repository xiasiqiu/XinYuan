package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.bean.ClassItemBean;
import com.xinyuan.xyshop.bean.StoreItemBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/8/16.
 * 店铺搜索数据
 */

public class SearchStoreModel implements Serializable{
	private static final long serialVersionUID = -1046007549354564690L;
	private List<StoreItemBean>storeList;
	private List<ClassItemBean>storeClassList;

	public List<StoreItemBean> getStoreList() {
		return storeList;
	}

	public List<ClassItemBean> getStoreClassList() {
		return storeClassList;
	}

	@Override
	public String toString() {
		return "SearchStoreModel{" +
				"storeList=" + storeList +
				", storeClassList=" + storeClassList +
				'}';
	}
}
