package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.GoodsEvaluate;
import com.xinyuan.xyshop.entity.MsgBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */

public class MsgAdapter extends BaseQuickAdapter<MsgBean, BaseViewHolder> {

	private static boolean detele = false;

	public MsgAdapter(@LayoutRes int layoutResId, @Nullable List<MsgBean> data) {
		super(layoutResId, data);
	}


	@Override
	protected void convert(BaseViewHolder helper, MsgBean item) {
		TextView tv_name = helper.getView(R.id.tv_store_name);
		TextView tv_msg_content = helper.getView(R.id.tv_msg_content);
		tv_name.setText(item.getName());

		tv_msg_content.setText(item.getContent());

	}
}
