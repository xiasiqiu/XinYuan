package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.entity.GoodsEvaluate;

import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */

public class VoucherAdapter extends BaseQuickAdapter<GoodsEvaluate, BaseViewHolder> {


	public VoucherAdapter(@LayoutRes int layoutResId, @Nullable List<GoodsEvaluate> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, GoodsEvaluate item) {

	}
}
