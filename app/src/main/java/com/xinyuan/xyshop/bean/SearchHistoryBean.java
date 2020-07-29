package com.xinyuan.xyshop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/8/1.
 * 搜索历史数据实体类
 */

public class SearchHistoryBean implements Serializable{
	private static final long serialVersionUID = -7779984084267472612L;
	private List<String>historyList;

	public List<String> getHistoryList() {
		return historyList;
	}

	public void setHistoryList(List<String> historyList) {
		this.historyList = historyList;
	}
}
