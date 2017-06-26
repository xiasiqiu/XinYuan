package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayout;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.entity.CreditBean;
import com.xinyuan.xyshop.entity.FollowBean;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.util.GlideImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/6/26.
 */

public class FollowAdapter extends BaseQuickAdapter<FollowBean, BaseViewHolder> {
	public FollowAdapter(@LayoutRes int layoutResId, @Nullable List<FollowBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, FollowBean item) {
		ImageView iv_store = helper.getView(R.id.iv_follow_img);
		TextView tv_follow_name = helper.getView(R.id.tv_follow_name);
		FlexboxLayout flexboxLayout = helper.getView(R.id.fl_follow_level);
		GlideImageLoader.setImage(mContext, item.getStoreImage(), iv_store);
		tv_follow_name.setText(item.getStoreName());


		for (String level : item.getStoreLevel()) {
			AddViewHolder addViewHolder = new AddViewHolder(mContext, R.layout.fragment_follow_item_level);
			ImageView imageView = addViewHolder.getView(R.id.iv_store_level);
			if (level.equals("皇冠")) {
				imageView.setBackground(mContext.getResources().getDrawable(R.drawable.ic_store_leve1));
			} else if (level.equals("金牌卖家")) {
				imageView.setBackground(mContext.getResources().getDrawable(R.drawable.ic_store_glod));
			}
			flexboxLayout.addView(addViewHolder.getCustomView());

		}
	}
}
