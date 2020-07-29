package com.xinyuan.xyshop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fx on 2017/6/5.
 * 分类数据
 */

public class CategoryModel implements Serializable {
    private static final long serialVersionUID = 2935275813642114270L;

    private List<CategoryData> categoryList;


    public List<CategoryData> getDatas() {
        return categoryList;
    }

    public class CategoryData implements Serializable {
        private static final long serialVersionUID = 6718425111073342645L;
        private int categoryId;
        private String categoryName;
        private int categorySort;
        private int parentId = 0;
        private String categoryImageUrl;
        private String ad;

        public String getAd() {
            return ad;
        }

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
                    ", ad='" + ad + '\'' +
                    ", parentId=" + parentId +
                    ", categoryList=" + categoryList +
                    '}';
        }
    }
}
