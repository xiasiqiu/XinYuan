package com.xinyuan.xyshop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */

public class History implements Serializable {
	private List<String>history;

	public List<String> getHistory() {
		return history;
	}

	public void setHistory(List<String> history) {
		this.history = history;
	}

	@Override
	public String toString() {
		return "History{" +
				"history=" + history +
				'}';
	}
}
