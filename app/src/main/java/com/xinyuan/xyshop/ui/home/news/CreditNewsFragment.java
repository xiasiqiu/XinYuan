package com.xinyuan.xyshop.ui.home.news;

import android.os.Bundle;
import android.print.PageRange;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.NewsAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.bean.NewsBean;
import com.xinyuan.xyshop.ui.mine.order.fragment.MyOrderFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/7/5.
 */

public class CreditNewsFragment extends BaseFragment {
	@BindView(R.id.rv_news_credit)
	RecyclerView rv_news_credit;


	private String mTitle;

	NewsAdapter adapter;

	public static CreditNewsFragment getInstance(String title) {
		CreditNewsFragment sf = new CreditNewsFragment();
		sf.mTitle = title;

		return sf;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_news_credit;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		List<NewsBean> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			NewsBean bean = new NewsBean();
			bean.setId("123");
			bean.setType("李***");
			bean.setContent("积分兑换了华为P10一台");
			bean.setTime("2017-06-21 15:40:23");
			list.add(bean);
		}
		adapter = new NewsAdapter(R.layout.fragment_news_credit_item, list);
		LinearLayoutManager manager = new LinearLayoutManager(getContext());
		rv_news_credit.setLayoutManager(manager);
		rv_news_credit.setAdapter(adapter);
	}
}
