package com.xinyuan.xyshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17.
 */

public class SearchGoodsListTest {

	private int code;
	private SearchGoodsData datas;


	public class SearchGoodsData {

		private SelectFilterTest filter;

		private PageEntity pageEntity;
		private List<GoodsVo> goodsList;

		public SelectFilterTest getSelectFilter() {
			return filter;
		}

		public void setSelectFilter(SelectFilterTest selectFilter) {
			this.filter = selectFilter;
		}

		public PageEntity getPageEntity() {
			return pageEntity;
		}

		public void setPageEntity(PageEntity pageEntity) {
			this.pageEntity = pageEntity;
		}

		public List<GoodsVo> getGoodsList() {
			return goodsList;
		}

		public void setGoodsList(List<GoodsVo> goodsList) {
			this.goodsList = goodsList;
		}

		@Override
		public String toString() {
			return "SearchGoodsData{" +
					"selectFilter=" + filter +
					", pageEntity=" + pageEntity +
					", goodsList=" + goodsList +
					'}';
		}
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public SearchGoodsData getDatas() {
		return datas;
	}

	public void setDatas(SearchGoodsData datas) {
		this.datas = datas;
	}

	@Override
	public String toString() {
		return "SearchGoodsList{" +
				"code=" + code +
				", datas=" + datas +
				'}';
	}
}
