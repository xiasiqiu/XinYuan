package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.CreditBean;
import com.xinyuan.xyshop.entity.GoodsVo;

import java.util.List;

/**
 * Created by Administrator on 2017/6/26.
 */

public class CreditAdapter extends BaseQuickAdapter<CreditBean, BaseViewHolder> {
	public CreditAdapter(@LayoutRes int layoutResId, @Nullable List<CreditBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, CreditBean item) {
		TextView tv_credit_num = helper.getView(R.id.tb_credit_num);
		if (item.getType() == 0) {
			tv_credit_num.setText("-"+item.getCreditNum());
			tv_credit_num.setTextColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
		} else {
			tv_credit_num.setText("+"+item.getCreditNum());
			tv_credit_num.setTextColor(mContext.getResources().getColor(R.color.tv_price));
		}

	}
}
