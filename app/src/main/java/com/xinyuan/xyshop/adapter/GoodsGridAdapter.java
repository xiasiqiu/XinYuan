package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.common.ShopHelper;
import com.xinyuan.xyshop.bean.GoodListItemBean;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.youth.xframe.utils.XEmptyUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */

public class GoodsGridAdapter extends BaseQuickAdapter<GoodListItemBean, BaseViewHolder> {


	public GoodsGridAdapter(@LayoutRes int layoutResId, @Nullable List<GoodListItemBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(final BaseViewHolder helper, GoodListItemBean item) {
		if (item.getImageUrl() != null) {
			ImageView goodsImg = helper.getView(R.id.ivGoodPic);
			GlideImageLoader.setUrlImg(mContext, item.getImageUrl(), goodsImg);
			TextView tv_goods_name = helper.getView(R.id.tv_goods_name);
			tv_goods_name.setText(item.getGoodsName());
			TextView tv_goods_price = helper.getView(R.id.tv_goods_price);
			tv_goods_price.setText(mContext.getString(R.string.money_rmb) + ShopHelper.getPriceString(item.getGoodsPrice()));

			TextView tv_goods_sellnum = helper.getView(R.id.tv_goods_sellnum);
			tv_goods_sellnum.setText(mContext.getString(R.string.month_sell_num) + item.getConsumeNum() + "件");
			if (!XEmptyUtils.isEmpty(item.getGoodsEvanum())) {
				helper.setText(R.id.tv_goods_talk, mContext.getString(R.string.text_eva) + item.getGoodsEvanum());

			}
			if (!XEmptyUtils.isEmpty(item.getGoodsEvaNum())) {
				helper.setText(R.id.tv_goods_talk, mContext.getString(R.string.text_eva) + item.getGoodsEvaNum());

			}

		}
	}
}
