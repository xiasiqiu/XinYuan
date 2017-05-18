package com.xinyuan.xyshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */

public class BrandList {
	private int code;
	private BradnData datas;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public BradnData getDatas() {
		return datas;
	}

	public void setDatas(BradnData datas) {
		this.datas = datas;
	}

	public class BradnData {

		private List<Brand> AdList;
		private List<Brand> brandList;

		public List<Brand> getAdList() {
			return AdList;
		}

		public void setAdList(List<Brand> adList) {
			AdList = adList;
		}

		public List<Brand> getBrandList() {
			return brandList;
		}

		public void setBrandList(List<Brand> brandList) {
			this.brandList = brandList;
		}
	}
}
