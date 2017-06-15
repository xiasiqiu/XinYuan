package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.youth.xframe.utils.log.XLog;

import java.util.List;

/**
 * Created by Administrator on 2017/6/12.
 */

public class TextLineAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


	public TextLineAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, String item) {
		TextView textView = helper.getView(R.id.good_promotion);
		textView.setText(item);
		XLog.v("促销sss" + item.toString());
	}
}
