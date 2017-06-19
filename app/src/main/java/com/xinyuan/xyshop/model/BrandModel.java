package com.xinyuan.xyshop.model;


import com.xinyuan.xyshop.entity.BrandBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */

public class BrandModel implements Serializable {
	private static final long serialVersionUID = 6605855787372291346L;
	List<BrandBean> AdList;
	private List<Brand> BrandList;

	public List<BrandBean> getAdList() {
		return AdList;
	}

	public List<Brand> getAllBrandList() {
		return BrandList;
	}

	@Override
	public String toString() {
		return "BrandModel{" +
				"AdList=" + AdList +
				", AllBrandList=" + BrandList +
				'}';
	}

	public class Brand implements Serializable {
		private static final long serialVersionUID = 5023762104615955276L;
		private int brandSord;
		private String brandInitial;

		private List<BrandBean> List;

		public int getBrandSord() {
			return brandSord;
		}

		public String getBrandInitial() {
			return brandInitial;
		}

		public List<BrandBean> getBrandBeanList() {
			return List;
		}

		@Override
		public String toString() {
			return "Brand{" +
					"brandSord=" + brandSord +
					", brandInitial='" + brandInitial + '\'' +
					", brandBeanList=" + List +
					'}';
		}


	}
}
