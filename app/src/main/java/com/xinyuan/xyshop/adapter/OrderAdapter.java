package com.xinyuan.xyshop.adapter;

import android.graphics.Paint;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayout;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.util.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */

public class OrderAdapter extends BaseQuickAdapter<GoodsVo, BaseViewHolder> {


	public OrderAdapter(@LayoutRes int layoutResId, @Nullable List<GoodsVo> data) {
		super(layoutResId, data);

	}

	@Override
	protected void convert(BaseViewHolder helper, GoodsVo item) {
		List<GoodsVo> list = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			list.add(new GoodsVo());
		}

		FlexboxLayout fl_order_goods = helper.getView(R.id.fl_order_goods);
		for (GoodsVo goodsVo : list) {
			AddViewHolder addViewHolder = new AddViewHolder(mContext, R.layout.fragment_order_item_good);
			TextView textView=addViewHolder.getView(R.id.tv_good_old_price);
			textView.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
			fl_order_goods.addView(addViewHolder.getCustomView());

		}
	}


}
