package com.xinyuan.xyshop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */

public class SearchHot implements Serializable {

	private int code;
	private KeyHot datas;


	public class KeyHot{

		private List<String>keywordList;
		private List<String>historySearchList;

		public List<String> getKeywordList() {
			return keywordList;
		}

		public void setKeywordList(List<String> keywordList) {
			this.keywordList = keywordList;
		}

		public List<String> getHistorySearchList() {
			return historySearchList;
		}

		public void setHistorySearchList(List<String> historySearchList) {
			this.historySearchList = historySearchList;
		}

		@Override
		public String toString() {
			return "KeyHot{" +
					"keywordList=" + keywordList +
					", historySearchList=" + historySearchList +
					'}';
		}
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public KeyHot getDatas() {
		return datas;
	}

	public void setDatas(KeyHot datas) {
		this.datas = datas;
	}

	@Override
	public String toString() {
		return "SearchHot{" +
				"code=" + code +
				", datas=" + datas +
				'}';
	}
}
