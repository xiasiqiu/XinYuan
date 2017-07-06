package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.bean.NewsBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/5.
 */

public class NewsAdapter extends BaseQuickAdapter<NewsBean, BaseViewHolder> {


	public NewsAdapter(@LayoutRes int layoutResId, @Nullable List<NewsBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, NewsBean item) {

	}
}
