package com.xinyuan.xyshop.ui.mine.order.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.OrderAdapter;
import com.xinyuan.xyshop.adapter.SearchGoodListAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/16.
 */

public class MyOrderContentFragment extends BaseFragment {
	private String mTitle;
	@BindView(R.id.rv_order)
	RecyclerView rv_order;
	private String mParent;
	private OrderAdapter adapter;

	public static MyOrderContentFragment getInstance(String title, String parent) {
		MyOrderContentFragment sf = new MyOrderContentFragment();
		sf.mTitle = title;
		sf.mParent = parent;
		return sf;
	}


	@Override
	public int getLayoutId() {
		return R.layout.fragment_mine_order_content;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		XLog.v("ContentFragment:" + mParent + ":" + mTitle);


		List<GoodsVo> list = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			list.add(new GoodsVo());
		}


		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(1);
		this.rv_order.setLayoutManager(layoutManager);
		this.adapter = new OrderAdapter(R.layout.fragment_order_item, list);
		this.adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
		this.rv_order.setAdapter(adapter);

//		if (mTitle.equals("实物订单")) {
//
//
//		} else {
//
//
//		}
	}
}
