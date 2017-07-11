package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseView;
import com.xinyuan.xyshop.entity.CourierBean;
import com.xinyuan.xyshop.entity.ExpressBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */

public class SpinExAdapter extends BaseQuickAdapter<CourierBean, BaseViewHolder> {
	public SpinExAdapter(@LayoutRes int layoutResId, @Nullable List<CourierBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, CourierBean item) {
		TextView textView = helper.getView(R.id.tv_courname);
		textView.setText(item.getCourierName());
	}
}
