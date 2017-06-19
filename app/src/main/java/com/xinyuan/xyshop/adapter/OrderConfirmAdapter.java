package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayout;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.entity.GoodsVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */

public class OrderConfirmAdapter extends BaseQuickAdapter<GoodsVo, BaseViewHolder> {


	public OrderConfirmAdapter(@LayoutRes int layoutResId, @Nullable List<GoodsVo> data) {
		super(layoutResId, data);

	}

	@Override
	protected void convert(BaseViewHolder helper, GoodsVo item) {
		List<GoodsVo> list = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			list.add(new GoodsVo());
		}

		FlexboxLayout fl_order_goods = helper.getView(R.id.fl_goods);
		for (GoodsVo goodsVo : list) {
			AddViewHolder addViewHolder = new AddViewHolder(mContext, R.layout.activity_confirm_order_item_good);
			fl_order_goods.addView(addViewHolder.getCustomView());

		}
	}


}
