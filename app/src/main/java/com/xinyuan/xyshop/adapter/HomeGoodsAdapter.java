package com.xinyuan.xyshop.adapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.GoodListItem;
import com.xinyuan.xyshop.model.HomeModel;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailsActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.youth.xframe.utils.log.XLog;

import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */

public class HomeGoodsAdapter extends BaseQuickAdapter<GoodListItem, BaseViewHolder> {

	public HomeGoodsAdapter(@LayoutRes int layoutResId, @Nullable List<GoodListItem> data) {
		super(layoutResId, data);

	}

	@Override
	protected void convert(BaseViewHolder helper, GoodListItem item) {

		ImageView goodsImg = helper.getView(R.id.ivGoodPic);
		TextView tv_goods_name = helper.getView(R.id.tv_goods_name);
		TextView tv_goods_price = helper.getView(R.id.tv_goods_price);
		TextView tv_goods_sellnum = helper.getView(R.id.tv_goods_sellnum);


		String name = CommUtil.getSubString(mContext, tv_goods_name, item.getGoodsName(), 1);

		GlideImageLoader.setImage(mContext, item.getImageUrl(), goodsImg);
		tv_goods_name.setText(name);
		tv_goods_price.setText(mContext.getString(R.string.money_rmb) + String.valueOf(item.getGoodsPrice()));
		tv_goods_sellnum.setText(item.getConsumeNum() + "人购买");

	}


}
