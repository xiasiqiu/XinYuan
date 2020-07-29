package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.bean.FavStoreBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/7/25.
 * 收藏店铺列表数据
 */

public class FavStoreModel implements Serializable {
	private static final long serialVersionUID = -5817700825209134425L;
	private List<FavStoreBean> storeList;

	public List<FavStoreBean> getStoreList() {
		return storeList;
	}
}
