package com.xinyuan.xyshop.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.GoodListItemBean;
import com.xinyuan.xyshop.bean.GroupGoodBean;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.common.ShopHelper;
import com.xinyuan.xyshop.model.CreditMallModel;
import com.xinyuan.xyshop.ui.buy.ConfirmOrderActivity;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;
import com.xinyuan.xyshop.util.CommUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by fx on 2017/6/5.
 * 团购商品列表Adapter
 */

public class GroupGoodsAdapter extends BaseQuickAdapter<GoodListItemBean, BaseViewHolder> {


	public GroupGoodsAdapter(int layoutResId, @Nullable List<GoodListItemBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, final GoodListItemBean item) {

		ImageView ivGoodPic = helper.getView(R.id.ivGoodPic);
		helper.setText(R.id.tv_goods_time, item.getGoodsActive() + "后结束");

		helper.setText(R.id.tv_goods_name, item.getGoodsName());
		helper.setText(R.id.tv_goods_sellnum, item.getConsumeNum() + "人已团");
		helper.setText(R.id.tv_goods_price, "团购价" + mContext.getString(R.string.money_rmb) + ShopHelper.getPriceString(item.getGoodsPrice()));

	}
}
