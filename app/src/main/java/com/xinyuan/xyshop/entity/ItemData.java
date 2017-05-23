package com.xinyuan.xyshop.entity;

import java.io.Serializable;

/**
 * Created by fx on 2017/5/4 0004.
 */

public class ItemData implements Serializable {
	private String data;
	private String imageUrl;
	private String type;
	private String image;

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ItemData{" +
				"data='" + data + '\'' +
				", imageUrl='" + imageUrl + '\'' +
				", type='" + type + '\'' +
				'}';
	}
}
