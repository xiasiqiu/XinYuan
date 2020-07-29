package com.xinyuan.xyshop.model;

import java.io.Serializable;

/**
 * Created by fx on 2017/7/20.
 * 新闻详情数据
 */

public class NewsModel implements Serializable {
	private static final long serialVersionUID = 5619833011118900242L;
	private long id;
	private String type;
	private String content;
	private String time;
	private String title;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public long getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getContent() {
		return content;
	}

	public String getTime() {
		return time;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return "NewsModel{" +
				"id=" + id +
				", type='" + type + '\'' +
				", content='" + content + '\'' +
				", time='" + time + '\'' +
				", title='" + title + '\'' +
				'}';
	}
}
