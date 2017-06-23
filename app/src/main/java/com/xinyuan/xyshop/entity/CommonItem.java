package com.xinyuan.xyshop.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/23.
 */

public class CommonItem implements Serializable {
	private static final long serialVersionUID = 8907328286372007628L;
	private String type;
	private String data;
	private String text;
	private String imageUrl;

	public String getType() {
		return type;
	}

	public String getData() {
		return data;
	}

	public String getText() {
		return text;
	}

	public String getImageUrl() {
		return imageUrl;
	}
}
