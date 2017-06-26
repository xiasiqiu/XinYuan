package com.xinyuan.xyshop.ui.mine.info;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.CreditAdapter;
import com.xinyuan.xyshop.adapter.FollowAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.CreditBean;
import com.xinyuan.xyshop.entity.FollowBean;
import com.xinyuan.xyshop.ui.mine.pro.CreditFragment;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/26.
 */

public class FollowFragment extends BaseFragment {
	@BindView(R.id.toolbar_tv)
	Toolbar toolbar_tv;
	@BindView(R.id.rv_follow)
	RecyclerView rv_follow;
	FollowAdapter adapter;
	@BindView(R.id.tv_header_right)
	TextView tv_header_right;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;

	public static FollowFragment newInstance() {
		FollowFragment fragment = new FollowFragment();
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_follow;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_tv);
		tv_header_center.setText("关注店铺");
		tv_header_right.setText("编辑");
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(1);
		this.rv_follow.setLayoutManager(layoutManager);
		List<String> one = new ArrayList<>();
		one.add("皇冠");
		one.add("金牌卖家");
		List<String> two = new ArrayList<>();
		two.add("皇冠");
		List<FollowBean> list = new ArrayList<>();

		list.add(new FollowBean("http://gw2.alicdn.com/bao/uploaded/1e/d2/TB1gmj.GpXXXXXKXVXXSutbFXXX.jpg_80x80.jpg_.webp", "可可丽小姐", one));
		list.add(new FollowBean("http://gw2.alicdn.com/bao/uploaded/d6/d3/TB1uvjXJFXXXXX6XFXXSutbFXXX.jpg_80x80.jpg_.webp", "ZARA官方旗舰店", two));
		list.add(new FollowBean("http://gw2.alicdn.com/bao/uploaded/1d/fa/TB1CR2MRVXXXXbZXFXXSutbFXXX.jpg_80x80.jpg_.webp", "试衣间", one));
		list.add(new FollowBean("http://gw2.alicdn.com/bao/uploaded/a1/fc/T1ZNdyFi4cXXb1upjX.jpg_80x80.jpg_.webp", "一号女装", two));
		list.add(new FollowBean("http://gw2.alicdn.com/bao/uploaded/1e/d2/TB1gmj.GpXXXXXKXVXXSutbFXXX.jpg_80x80.jpg_.webp", "可可丽小姐", one));
		list.add(new FollowBean("http://gw2.alicdn.com/bao/uploaded/d6/d3/TB1uvjXJFXXXXX6XFXXSutbFXXX.jpg_80x80.jpg_.webp", "ZARA官方旗舰店", two));
		list.add(new FollowBean("http://gw2.alicdn.com/bao/uploaded/1d/fa/TB1CR2MRVXXXXbZXFXXSutbFXXX.jpg_80x80.jpg_.webp", "试衣间", one));
		list.add(new FollowBean("http://gw2.alicdn.com/bao/uploaded/a1/fc/T1ZNdyFi4cXXb1upjX.jpg_80x80.jpg_.webp", "一号女装", two));
		list.add(new FollowBean("http://gw2.alicdn.com/bao/uploaded/1e/d2/TB1gmj.GpXXXXXKXVXXSutbFXXX.jpg_80x80.jpg_.webp", "可可丽小姐", one));
		list.add(new FollowBean("http://gw2.alicdn.com/bao/uploaded/d6/d3/TB1uvjXJFXXXXX6XFXXSutbFXXX.jpg_80x80.jpg_.webp", "ZARA官方旗舰店", two));
		list.add(new FollowBean("http://gw2.alicdn.com/bao/uploaded/1d/fa/TB1CR2MRVXXXXbZXFXXSutbFXXX.jpg_80x80.jpg_.webp", "试衣间", one));
		list.add(new FollowBean("http://gw2.alicdn.com/bao/uploaded/a1/fc/T1ZNdyFi4cXXb1upjX.jpg_80x80.jpg_.webp", "一号女装", two));

		this.adapter = new FollowAdapter(R.layout.fragment_follow_item, list);
		this.rv_follow.setAdapter(adapter);


	}
}
