package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by fx on 2017/7/17.
 * 商品搜索列表筛选元素列表Adapter
 */

public class SelectValueAdapters extends BaseQuickAdapter<String, BaseViewHolder> {
	public SelectValueAdapters(@LayoutRes int layoutResId, @Nullable List<String> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, String item) {

	}


}
