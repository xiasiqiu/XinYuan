package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.CouponBean;
import com.xinyuan.xyshop.entity.CreditBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/26.
 */

public class CouponAdapter extends BaseQuickAdapter<CouponBean, BaseViewHolder> {

	public CouponAdapter(@LayoutRes int layoutResId, @Nullable List<CouponBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, CouponBean item) {

	}
}
