package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.common.ShopHelper;
import com.xinyuan.xyshop.bean.GoodListItemBean;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.youth.xframe.utils.log.XLog;

import java.util.HashMap;
import java.util.List;

/**
 * Created by fx on 2017/6/5.
 * 首页推荐商品列表Adapter
 */

public class HomeGoodsAdapter extends BaseQuickAdapter<GoodListItemBean, BaseViewHolder> {

	public HomeGoodsAdapter(@LayoutRes int layoutResId, @Nullable List<GoodListItemBean> data) {
		super(layoutResId, data);

	}

	@Override
	protected void convert(BaseViewHolder helper, final GoodListItemBean item) {
		ImageView goodsImg = helper.getView(R.id.ivGoodPic);
		GlideImageLoader.setUrlImg(mContext, item.getImageUrl(), goodsImg);
		//if(item.getGoodsType())
		helper.setText(R.id.tv_goods_name, item.getGoodsName());
		helper.setText(R.id.tv_goods_sellnum, item.getConsumeNum() + "人购买");
		helper.setText(R.id.tv_goods_price, mContext.getString(R.string.money_rmb) + ShopHelper.getPriceString(item.getGoodsPrice()));
	}


}
