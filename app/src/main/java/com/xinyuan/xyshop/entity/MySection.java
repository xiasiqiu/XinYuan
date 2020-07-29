package com.xinyuan.xyshop.entity;


import com.chad.library.adapter.base.entity.SectionEntity;
import com.xinyuan.xyshop.bean.BrandBean;

/**
 * Created by fx on 2017/5/22.
 * 品牌店铺头部
 */

public class MySection extends SectionEntity<BrandBean> {
	private boolean isMore;

	public MySection(boolean isHeader, String header, boolean isMroe) {
		super(isHeader, header);
		this.isMore = isMroe;
	}

	public MySection(BrandBean t) {
		super(t);
	}

	public boolean isMore() {
		return isMore;
	}

	public void setMore(boolean mroe) {
		isMore = mroe;
	}
}
