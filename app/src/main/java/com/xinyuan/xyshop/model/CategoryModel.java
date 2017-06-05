package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.entity.GoodCategory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */

public class CategoryModel implements Serializable {
	private static final long serialVersionUID = 2935275813642114270L;

	private List<CategoryData> categoryList;


	public class CategoryData implements Serializable {
		private static final long serialVersionUID = 6718425111073342645L;
		private int categoryId;
		private String categoryName;
		private int categorySort;
		private String categoryImageUrl;
		private int parentId = 0;
		private List<CategoryData> categoryList = new ArrayList();

		public int getCategoryId() {
			return categoryId;
		}

		public String getCategoryName() {
			return categoryName;
		}

		public int getCategorySort() {
			return categorySort;
		}

		public String getCategoryImageUrl() {
			return categoryImageUrl;
		}

		public int getParentId() {
			return parentId;
		}

		public List<CategoryData> getCategoryList() {
			return categoryList;
		}

		@Override
		public String toString() {
			return "CategoryData{" +
					"categoryId=" + categoryId +
					", categoryName='" + categoryName + '\'' +
					", categorySort=" + categorySort +
					", categoryImageUrl='" + categoryImageUrl + '\'' +
					", parentId=" + parentId +
					", categoryList=" + categoryList +
					'}';
		}
	}

	public List<CategoryData> getDatas() {
		return categoryList;
	}
}
