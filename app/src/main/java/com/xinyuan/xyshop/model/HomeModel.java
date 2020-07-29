package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.bean.GoodListItemBean;
import com.xinyuan.xyshop.entity.ItemData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/6/5.
 * 首页数据
 */

public class HomeModel implements Serializable {

	private static final long serialVersionUID = -2595183988797509119L;

	private List<HomeModule> moduleList;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public List<HomeModule> getModuleList() {
		return moduleList;
	}


	public class HomeModule implements Serializable {
		private static final long serialVersionUID = -116615636885253895L;
		private int itemSort;
		private String itemType;
		private String itemTypeText;
		private List<ItemData> dataList;
		private String itemtitleImage;
		private String itemtitleCN;
		private String itemtitleEN;
		private String itemtitleColor;

		public String getItemtitleImage() {
			return itemtitleImage;
		}

		public String getItemtitleCN() {
			return itemtitleCN;
		}

		public String getItemtitleEN() {
			return itemtitleEN;
		}

		public String getItemtitleColor() {
			return itemtitleColor;
		}

		public int getItemSort() {
			return itemSort;
		}

		public void setItemSort(int itemSort) {
			this.itemSort = itemSort;
		}

		public String getItemType() {
			return itemType;
		}

		public void setItemType(String itemType) {
			this.itemType = itemType;
		}

		public String getItemTypeText() {
			return itemTypeText;
		}

		public void setItemTypeText(String itemTypeText) {
			this.itemTypeText = itemTypeText;
		}

		public List<ItemData> getDataList() {
			return dataList;
		}

		public void setDataList(List<ItemData> dataList) {
			this.dataList = dataList;
		}

		@Override
		public String toString() {
			return "HomeModule{" +
					", itemSort=" + itemSort +
					", itemType='" + itemType + '\'' +
					", itemTypeText='" + itemTypeText + '\'' +
					", dataList=" + dataList +
					", itemtitleImage='" + itemtitleImage + '\'' +
					", itemtitleCN='" + itemtitleCN + '\'' +
					", itemtitleEN='" + itemtitleEN + '\'' +
					", itemtitleColor='" + itemtitleColor + '\'' +
					'}';
		}
	}

	@Override
	public String toString() {
		return "HomeModel{" +
				"moduleList=" + moduleList +
				'}';
	}
}
