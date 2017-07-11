package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.xinyuan.xyshop.widget.PaperButton;
import com.youth.xframe.utils.log.XLog;

import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/5/17.
 */

public class SearchGoodListAdapter extends BaseQuickAdapter<GoodsVo, BaseViewHolder> {
	private boolean isList;

	public SearchGoodListAdapter(@LayoutRes int layoutResId, @Nullable List<GoodsVo> data, Boolean isList) {
		super(layoutResId, data);
		this.isList = isList;
	}

	@Override
	protected void convert(BaseViewHolder helper, GoodsVo item) {

		if (isList) {
			ImageView goodsImg = helper.getView(R.id.ivGoodPic);
			TextView tv_goods_name = helper.getView(R.id.tv_goods_name);
			TextView tv_good_active = helper.getView(R.id.tv_good_active);
			TextView tv_goods_price = helper.getView(R.id.tv_goods_price);
			TextView tv_goods_sellnum = helper.getView(R.id.tv_goods_sellnum);
			TextView tv_goods_talk = helper.getView(R.id.tv_goods_talk);

			GlideImageLoader.setImage(mContext, item.getImageSrc(), goodsImg);
			tv_goods_name.setText(item.getGoodsName());
			tv_goods_price.setText("￥" + String.valueOf(item.getAppPriceMin()));
			tv_goods_sellnum.setText("月销量:" + item.getGoodsSaleNum() + "件");
			tv_goods_talk.setText("评论:" + item.getEvaluateNum());
			int num = (int) (Math.random() * 10 + 1);
			XLog.v("NUM" + num);
			if (num % 2 == 0) {
				tv_good_active.setText("包邮");
				tv_good_active.setBackground(mContext.getResources().getDrawable(R.color.bg_white));
				tv_good_active.setTextColor(mContext.getResources().getColor(R.color.tv_price));
			} else {
				tv_good_active.setVisibility(View.VISIBLE);
			}

		} else {
			TextView tv_good_active = helper.getView(R.id.tv_good_active);
			ImageView goodsImg = helper.getView(R.id.ivGoodPic);
			GlideImageLoader.setImage(mContext, item.getImageSrc(), goodsImg);
			TextView tv_goods_name = helper.getView(R.id.tv_goods_name);
			tv_goods_name.setText(item.getGoodsName());
			TextView tv_goods_price = helper.getView(R.id.tv_goods_price);
			tv_goods_price.setText("￥" + String.valueOf(item.getAppPriceMin()));
			TextView tv_goods_sellnum = helper.getView(R.id.tv_goods_sellnum);
			tv_goods_sellnum.setText("月销量:" + item.getGoodsSaleNum() + "件");
			int num = (int) (Math.random() * 10 + 1);
			if (num % 2 == 0) {
				tv_good_active.setText("包邮");
				tv_good_active.setBackground(mContext.getResources().getDrawable(R.color.bg_white));
				tv_good_active.setTextColor(mContext.getResources().getColor(R.color.tv_price));
			} else {
				tv_good_active.setVisibility(View.VISIBLE);
			}

		}

	}


}
