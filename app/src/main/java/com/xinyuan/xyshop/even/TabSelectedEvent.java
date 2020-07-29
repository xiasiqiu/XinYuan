package com.xinyuan.xyshop.even;

/**
 * Created by fx on 2017/6/26.
 * 主页四页面切换消息
 */

public class TabSelectedEvent {
	public int position;

	public TabSelectedEvent(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "TabSelectedEvent{" +
				"position=" + position +
				'}';
	}
}
