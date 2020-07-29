package com.xinyuan.xyshop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/9/22.
 * 商品规格
 */

public class AttributesBean implements Serializable {
	private static final long serialVersionUID = 5351984957505524721L;
	/**
	 * tabID : 0
	 * tabName : 颜色
	 * attributesItem : ["白","蓝","黑"]
	 */

	private int specId;
	private String specName;
	private List<AttributeBean> specProperty;

	public int getTabID() {
		return specId;
	}

	public void setTabID(int tabID) {
		this.specId = tabID;
	}

	public String getTabName() {
		return specName;
	}

	public void setTabName(String tabName) {
		this.specName = tabName;
	}

	public List<AttributeBean> getAttributesItem() {
		return specProperty;
	}

	public void setAttributesItem(List<AttributeBean> attributesItem) {
		this.specProperty = attributesItem;
	}


	@Override
	public String toString() {
		return "AttributesBean{" +
				"specId=" + specId +
				", specName='" + specName + '\'' +
				", specProperty=" + specProperty +
				'}';
	}


	public class AttributeBean implements Serializable {
		private static final long serialVersionUID = 3002630256461811622L;
		private String valueName;
		private String valueImage;

		public String getValueName() {
			return valueName;
		}

		public String getValueImage() {
			return valueImage;
		}

		@Override
		public String toString() {
			return "AttributeBean{" +
					"valueName='" + valueName + '\'' +
					", valueImage='" + valueImage + '\'' +
					'}';
		}
	}
}
