package com.xinyuan.xyshop.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/7/19.
 * 搜索热词数据
 */

public class KeyWordModel implements Serializable {
	private static final long serialVersionUID = -1474242651643293845L;
	private List<String> keywordList;
	private List<String> historySearchList;

	public List<String> getKeywordList() {
		return keywordList;
	}

	public List<String> getHistorySearchList() {
		return historySearchList;
	}

	@Override
	public String toString() {
		return "KeyWordModel{" +
				"keywordList=" + keywordList +
				", historySearchList=" + historySearchList +
				'}';
	}
}
