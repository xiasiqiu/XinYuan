package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.ChatListItemBean;
import com.xinyuan.xyshop.common.GlideImageLoader;

import java.util.List;

import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * Created by Administrator on 2017/6/5.
 * 消息列表Adapter
 */

public class ChatListAdapter extends BaseQuickAdapter<ChatListItemBean, BaseViewHolder> {

	private static boolean detele = false;

	public ChatListAdapter(@LayoutRes int layoutResId, @Nullable List<ChatListItemBean> data) {
		super(layoutResId, data);
	}


	@Override
	protected void convert(BaseViewHolder helper, ChatListItemBean item) {
		helper.setText(R.id.tv_msg_time, item.getChatting_addTime());
		helper.setText(R.id.tv_store_name, item.getUserName());
		helper.setText(R.id.tv_msg_content, item.getChatting_content());
		BGABadgeImageView iv_head_img = helper.getView(R.id.iv_head_img);
		GlideImageLoader.setUrlImg(mContext, item.getChatting_image(), iv_head_img);
		if (item.getChatting_mark() == 1) {
			iv_head_img.showCirclePointBadge();
		} else {
			iv_head_img.hiddenBadge();
		}

	}
}
