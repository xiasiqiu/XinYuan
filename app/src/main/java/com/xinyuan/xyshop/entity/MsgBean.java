package com.xinyuan.xyshop.entity;

/**
 * Created by Administrator on 2017/7/11.
 */

public class MsgBean {
	private String heanImg;
	private String name;
	private String content;
	private String time;

	public MsgBean(String name, String content) {
		this.name = name;
		this.content = content;
	}

	public String getHeanImg() {
		return heanImg;
	}

	public String getName() {
		return name;
	}

	public String getContent() {
		return content;
	}

	public String getTime() {
		return time;
	}

	@Override
	public String toString() {
		return "MsgBean{" +
				"heanImg='" + heanImg + '\'' +
				", name='" + name + '\'' +
				", content='" + content + '\'' +
				", time='" + time + '\'' +
				'}';
	}
}
