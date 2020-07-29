package com.xinyuan.xyshop.entity;

import java.io.Serializable;

/**
 * Created by fx on 2017/5/4 0004.
 * 广告类数据
 */

public class ItemData implements Serializable {
	private static final long serialVersionUID = -8926264069824104056L;
	private String data;
	private String imageUrl;
	private String type;
	private String text;

	public void setData(String data) {
		this.data = data;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "ItemData{" +
				"data='" + data + '\'' +
				", imageUrl='" + imageUrl + '\'' +
				", type='" + type + '\'' +
				", text='" + text + '\'' +
				'}';
	}

	public String getText() {
		return text;
	}

	public String getData() {
		return data;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getType() {
		return type;
	}
}
