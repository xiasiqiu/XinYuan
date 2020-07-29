package com.xinyuan.xyshop.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.model.GoodDetailModels;

import java.util.List;

/**
 * Created by fx on 2017/8/15.
 * 商品详情页参数Adapter
 */

public class GoodConfigAdapter extends BaseQuickAdapter<GoodDetailModels.GoodParam, BaseViewHolder> {
	public GoodConfigAdapter(int layoutResId, @Nullable List<GoodDetailModels.GoodParam> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, GoodDetailModels.GoodParam item) {
		helper.setText(R.id.tv_name, item.getApecName());
		helper.setText(R.id.tv_value, item.getApecValue());
	}
}
