package com.xinyuan.xyshop.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.model.CreditMallModel;
import com.xinyuan.xyshop.ui.buy.ConfirmOrderActivity;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.youth.xframe.widget.XToast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fx on 2017/6/5.
 * 积分商品列表Adapter
 */

public class CreditGoodsAdapter extends BaseQuickAdapter<CreditMallModel.CreditGood, BaseViewHolder> {


	public CreditGoodsAdapter(int layoutResId, @Nullable List<CreditMallModel.CreditGood> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, final CreditMallModel.CreditGood item) {
		ImageView ivGoodPic = helper.getView(R.id.ivGoodPic);
		GlideImageLoader.setUrlImg(mContext, item.getGoodsImg(), ivGoodPic);

		helper.setText(R.id.tv_goods_name, item.getGoodsName());
		helper.setText(R.id.tv_goods_sellnum, item.getConsumeNum() + "人兑换");
		helper.setText(R.id.tv_goods_price, item.getGoodsPrice() + "积分");
//		setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//				HashMap<String, String> params;
//				params = new HashMap();
//				switch (item.getGoodsType()) {
//					case  Constants.GOOD_CREDIT:
//						params.put(Constants.GOODID, item.getGoodsId());
//						params.put(Constants.GOODTYPE, Constants.GOOD_CREDIT);
//						CommUtil.gotoActivity(mContext, GoodDetailActivity.class, false, params);
//						break;
//					case  Constants.CREDIT_RED:
//						params.put(Constants.GOODID, item.getGoodsId());
//						params.put(Constants.GOODTYPE,  Constants.CREDIT_RED);
//						CommUtil.gotoActivity(mContext, ConfirmOrderActivity.class, false, params);
//						break;
//					case Constants.CREDIT_COUPON:
//						params.put(Constants.GOODID, item.getGoodsId());
//						params.put(Constants.GOODTYPE,  Constants.CREDIT_COUPON);
//						CommUtil.gotoActivity(mContext, ConfirmOrderActivity.class, false, params);
//
//						break;
//
//				}
//
//
//			}
//		});
	}
}
