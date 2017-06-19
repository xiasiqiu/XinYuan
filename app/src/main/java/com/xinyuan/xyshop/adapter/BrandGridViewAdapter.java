package com.xinyuan.xyshop.adapter;

import android.content.Context;
import android.view.View;

import com.xinyuan.xyshop.R;

import com.xinyuan.xyshop.entity.BrandBean;
import com.xinyuan.xyshop.model.BrandModel;
import com.xinyuan.xyshop.mvp.presenter.CategoryPresenterImpl;
import com.xinyuan.xyshop.util.ViewHolder;

/**
 * Created by Administrator on 2017/5/15.
 */

public class BrandGridViewAdapter extends CommonAdapter<BrandBean> {
	public BrandGridViewAdapter(Context context) {
		super(context, R.layout.fragment_category_item_brand);
	}

	public void convert(ViewHolder holder, final BrandBean brand) {
		holder.setText(R.id.tvBrandName, brand.getBrandName());
		holder.setImage((int) R.id.ivBrandPic, brand.getBrandImage());
		holder.setOnClickListener(R.id.llBrandItem, new View.OnClickListener() {
			public void onClick(View view) {
				String brandId = brand.getBrandName();
				CategoryPresenterImpl.jump(BrandGridViewAdapter.this.mContext, brandId, true);
			}
		});
	}
}
