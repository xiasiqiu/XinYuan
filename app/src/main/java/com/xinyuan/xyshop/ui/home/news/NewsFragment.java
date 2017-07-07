package com.xinyuan.xyshop.ui.home.news;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.NewsAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.bean.NewsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/7/5.
 */

public class NewsFragment extends BaseFragment {

	@BindView(R.id.rv_news)
	RecyclerView rv_news;

	private String mTitle;
	NewsAdapter adapter;

	public static NewsFragment getInstance(String title) {
		NewsFragment sf = new NewsFragment();
		sf.mTitle = title;

		return sf;

	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_news;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}



	@Override
	public void initView() {
		List<NewsBean> list = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			NewsBean bean = new NewsBean();
			bean.setId("123");
			bean.setType("华北因异常降雨天气配送延迟");
			bean.setContent("华北地区近期将普遍降雨，异常天气会对我们的配送产生影响，如您的订单出现延误，");
			bean.setTime("2017-06-21 15:40:23");
			list.add(bean);
		}
		adapter = new NewsAdapter(R.layout.fragment_news_item, list);
		LinearLayoutManager manager = new LinearLayoutManager(getContext());
		rv_news.setLayoutManager(manager);
		rv_news.setAdapter(adapter);
	}
}
