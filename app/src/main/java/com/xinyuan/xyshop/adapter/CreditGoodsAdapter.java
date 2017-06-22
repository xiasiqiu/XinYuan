package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.model.CreditModel;
import com.xinyuan.xyshop.util.GlideImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */

public class CreditGoodsAdapter extends BaseQuickAdapter<CreditModel.CreditGood, BaseViewHolder> {


	public CreditGoodsAdapter(@LayoutRes int layoutResId, @Nullable List<CreditModel.CreditGood> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, CreditModel.CreditGood item) {
		TextView goodname = helper.getView(R.id.tv_goods_name);
		TextView tv_goods_price = helper.getView(R.id.tv_goods_price);
		TextView tv_goods_sellnum = helper.getView(R.id.tv_goods_sellnum);
		ImageView imageView = helper.getView(R.id.ivGoodPic);
		goodname.setText(item.getGoodsName());
		tv_goods_price.setText(mContext.getString(R.string.money_rmb) + item.getGoodsPrice());
		tv_goods_sellnum.setText(item.getExNum() + "人兑换");

		GlideImageLoader.setImage(mContext, item.getImageUrl(), imageView);
	}
}
