package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.bean.StoreInfoBean;
import com.xinyuan.xyshop.bean.TokenBean;

import java.util.List;

/**
 * Created by fx on 2017/6/5.
 * 确认订单列表Adapter
 */

public class OrderConfirmAdapter extends BaseQuickAdapter<StoreInfoBean, BaseViewHolder> {


	public OrderConfirmAdapter(@LayoutRes int layoutResId, @Nullable List<StoreInfoBean> data) {
		super(layoutResId, data);

	}

	@Override
	protected void convert(BaseViewHolder helper, StoreInfoBean item) {


	}


}
