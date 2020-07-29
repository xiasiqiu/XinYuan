package com.xinyuan.xyshop.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.PayTypeBean;
import com.xinyuan.xyshop.common.GlideImageLoader;

import java.util.List;

/**
 * Created by fx on 2017/9/7.
 * 支付方式列表Adapter
 *
 */

public class PayTypeAdapter extends BaseQuickAdapter<PayTypeBean, BaseViewHolder> {
	public PayTypeAdapter(int layoutResId, @Nullable List<PayTypeBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, PayTypeBean item) {
		ImageView iv_pay_img = helper.getView(R.id.iv_pay_img);
		GlideImageLoader.setImg(mContext, item.getTypeImg(), iv_pay_img);
		helper.setText(R.id.tv_Pay_type, item.getTypeName());
		helper.setText(R.id.tv_Pay_hint, item.getTypeHint());

	}
}
