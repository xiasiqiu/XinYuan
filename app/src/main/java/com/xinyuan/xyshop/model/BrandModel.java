package com.xinyuan.xyshop.model;


import com.xinyuan.xyshop.bean.BrandBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/6/19.
 * 品牌店铺数据
 */

public class BrandModel implements Serializable {
	private static final long serialVersionUID = 6605855787372291346L;
	private List<BrandBean> storeRecommendList;
	private List<Brand> storeSortList;


	public List<BrandBean> getStoreRecommendList() {
		return storeRecommendList;
	}

	public List<Brand> getStoreSortList() {
		return storeSortList;
	}

	public class Brand implements Serializable {
		private static final long serialVersionUID = 5023762104615955276L;
		private int brandSord;
		private String brandInitial;
		private List<BrandBean> list;


		public int getBrandSord() {
			return brandSord;
		}

		public String getBrandInitial() {
			return brandInitial;
		}

		public List<BrandBean> getList() {
			return list;
		}
	}
}
