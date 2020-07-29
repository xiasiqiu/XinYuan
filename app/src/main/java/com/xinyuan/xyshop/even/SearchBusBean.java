package com.xinyuan.xyshop.even;

import com.xinyuan.xyshop.bean.GoodBradnBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/8/2.
 * 搜索消息
 */

public class SearchBusBean implements Serializable{
	private static final long serialVersionUID = -7127705022844064092L;
	private String price;
	private String xyself;
	private List<GoodBradnBean> selectedBrandList;
	private List<String> proList;
	private boolean isEmpty;

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean empty) {
		isEmpty = empty;
	}

	public String getPrice() {
		return price;
	}

	public String getXyself() {
		return xyself;
	}

	public List<GoodBradnBean> getSelectedBrandList() {
		return selectedBrandList;
	}

	public List<String> getProList() {
		return proList;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setXyself(String xyself) {
		this.xyself = xyself;
	}

	public void setSelectedBrandList(List<GoodBradnBean> selectedBrandList) {
		this.selectedBrandList = selectedBrandList;
	}

	public void setProList(List<String> proList) {
		this.proList = proList;
	}

	@Override
	public String toString() {
		return "SearchBusBean{" +
				"price='" + price + '\'' +
				", xyself='" + xyself + '\'' +
				", selectedBrandList=" + selectedBrandList +
				", proList=" + proList +
				", isEmpty=" + isEmpty +
				'}';
	}
}
