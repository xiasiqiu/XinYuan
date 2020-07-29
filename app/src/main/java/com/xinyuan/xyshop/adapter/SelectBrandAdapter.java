package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.bean.GoodBradnBean;

import java.util.List;

/**
 * Created by fx on 2017/7/17.
 * 搜索筛选框品牌列表Adapter
 *
 */

public class SelectBrandAdapter extends BaseQuickAdapter<GoodBradnBean, BaseViewHolder> {
	public SelectBrandAdapter(@LayoutRes int layoutResId, @Nullable List<GoodBradnBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, GoodBradnBean item) {

	}


}
