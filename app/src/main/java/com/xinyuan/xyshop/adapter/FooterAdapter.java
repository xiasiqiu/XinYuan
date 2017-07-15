package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.entity.UserHistoryBean;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.xinyuan.xyshop.util.Image;
import com.xinyuan.xyshop.widget.dialog.SearchSortDialog;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */

public class FooterAdapter extends BaseQuickAdapter<UserHistoryBean, BaseViewHolder> {


	public FooterAdapter(@LayoutRes int layoutResId, @Nullable List<UserHistoryBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(final BaseViewHolder helper, UserHistoryBean item) {
		 final SearchSortDialog sortDialog;

		ImageView iv_good_img=helper.getView(R.id.iv_good_img);
		Button bt_footer=helper.getView(R.id.bt_footer);
		GlideImageLoader.setImage(mContext,item.getGoodsImg(),iv_good_img);
		bt_footer.setText("￥"+item.getGoodsPrice());
		bt_footer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});

	}
}
