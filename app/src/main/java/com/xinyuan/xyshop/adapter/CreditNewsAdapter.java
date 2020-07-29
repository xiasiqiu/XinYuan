package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.CreditNewsBean;

import java.util.List;

/**
 * Created by fx on 2017/7/5
 * 积分动态列表Adapter
 */

public class CreditNewsAdapter extends BaseQuickAdapter<CreditNewsBean, BaseViewHolder> {


	public CreditNewsAdapter(@LayoutRes int layoutResId, @Nullable List<CreditNewsBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, CreditNewsBean item) {
		helper.setText(R.id.tv_new_title, item.getUserName());
		helper.setText(R.id.tv_new_time, item.getTime());
		helper.setText(R.id.tv_new_content, item.getContent());

	}
}
