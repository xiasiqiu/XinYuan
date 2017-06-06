package com.xinyuan.xyshop.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.GoodCategory;
import com.xinyuan.xyshop.model.HomeModel;
import com.xinyuan.xyshop.mvp.presenter.CategoryPresenterImpl;
import com.xinyuan.xyshop.ui.goods.GoodDetailsActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.xinyuan.xyshop.util.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */

public class HomeGoodsAdapter extends BaseQuickAdapter<HomeModel.HomeGood, BaseViewHolder> {

	public HomeGoodsAdapter(@LayoutRes int layoutResId, @Nullable List<HomeModel.HomeGood> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, HomeModel.HomeGood item) {

		ImageView goodsImg = helper.getView(R.id.ivGoodPic);
		GlideImageLoader.setImage(mContext, item.getImageUrl(), goodsImg);
		TextView tv_goods_name = helper.getView(R.id.tv_goods_name);
		tv_goods_name.setText(item.getGoodsName());
		TextView tv_goods_price = helper.getView(R.id.tv_goods_price);
		tv_goods_price.setText("￥" + String.valueOf(item.getAppPriceMin()));
		TextView tv_goods_sellnum = helper.getView(R.id.tv_goods_sellnum);
		tv_goods_sellnum.setText("月销量:" + item.getSellnum() + "件");
		goodsImg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				CommUtil.gotoActivity((Activity) mContext, GoodDetailsActivity.class, false, null);
			}
		});
	}


}
