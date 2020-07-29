package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.MsgBean;

import java.util.List;

/**
 * Created by fx on 2017/6/5.
 * 系统通知消息列表Adapter
 */

public class MsgAdapter extends BaseQuickAdapter<MsgBean, BaseViewHolder> {

	private static boolean detele = false;

	public MsgAdapter(@LayoutRes int layoutResId, @Nullable List<MsgBean> data) {
		super(layoutResId, data);
	}


	@Override
	protected void convert(BaseViewHolder helper, MsgBean item) {
		helper.setText(R.id.tv_msg_time,item.getMessageTime());
		helper.setText(R.id.tv_store_name,item.getFromUser());
		helper.setText(R.id.tv_msg_content,item.getMessageContent());


	}
}
