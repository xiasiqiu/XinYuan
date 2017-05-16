package com.xinyuan.xyshop.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xinyuan.xyshop.adapter.ExpandableItemAdapter;

/**
 * Created by Administrator on 2017/5/16.
 */

public class Menu implements MultiItemEntity {


	private String imgUrl;
	private String title;

	public Menu(String imgUrl, String title) {
		this.imgUrl = imgUrl;
		this.title = title;

	}


	@Override
	public int getItemType() {
		return ExpandableItemAdapter.TYPE_MENU;
	}


	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
