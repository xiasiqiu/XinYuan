package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.model.NewsModel;

import java.util.List;

/**
 * Created by fx on 2017/7/5.
 * 文章列表Adapter
 */

public class NewsAdapter extends BaseQuickAdapter<NewsModel, BaseViewHolder> {


	public NewsAdapter(@LayoutRes int layoutResId, @Nullable List<NewsModel> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, NewsModel item) {

		helper.setText(R.id.tv_new_title, item.getContent());
		helper.setText(R.id.tv_new_time, item.getTime());
		helper.setText(R.id.tv_new_type, item.getType());

	}
}
