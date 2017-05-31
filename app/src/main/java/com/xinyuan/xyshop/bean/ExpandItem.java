package com.xinyuan.xyshop.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xinyuan.xyshop.adapter.ExpandableItemAdapter;
import com.xinyuan.xyshop.entity.Menu;

/**
 * Created by Administrator on 2017/5/16.
 */

public class ExpandItem extends AbstractExpandableItem<Menu> implements MultiItemEntity {
	public String imgUrl;
	public String title;
	public ExpandItem(String imgUrl, String title) {
		this.imgUrl = title;
		this.title = title;
	}

	@Override
	public int getItemType() {
		return ExpandableItemAdapter.TYPE_LEVEL_0;
	}

	@Override
	public int getLevel() {
		return 1;
	}
}
