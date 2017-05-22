package com.xinyuan.xyshop.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by Administrator on 2017/5/22.
 */

public class MySection extends SectionEntity<Brand> {
	private boolean isMore;

	public MySection(boolean isHeader, String header, boolean isMroe) {
		super(isHeader, header);
		this.isMore = isMroe;
	}

	public MySection(Brand t) {
		super(t);
	}

	public boolean isMore() {
		return isMore;
	}

	public void setMore(boolean mroe) {
		isMore = mroe;
	}
}
