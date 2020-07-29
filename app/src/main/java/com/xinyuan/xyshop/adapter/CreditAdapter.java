package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.CreditBean;

import java.util.List;

/**
 * Created by fx on 2017/6/26.
 * 积分列表Adapter
 */

public class CreditAdapter extends BaseQuickAdapter<CreditBean, BaseViewHolder> {
	public CreditAdapter(@LayoutRes int layoutResId, @Nullable List<CreditBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, CreditBean item) {
		TextView tv_credit_num = helper.getView(R.id.tb_credit_num);
		if (item.getLogDig() < 0) { //消费积分
			tv_credit_num.setText(String.valueOf(item.getLogDig()));
			tv_credit_num.setTextColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
		} else {                    //增加积分
			tv_credit_num.setText("+" + String.valueOf(item.getLogDig()));
			tv_credit_num.setTextColor(mContext.getResources().getColor(R.color.tv_price));

		}
		helper.setText(R.id.tv_credit_name, item.getLogContent());
		helper.setText(R.id.tv_time, item.getLogTime());
	}
}
