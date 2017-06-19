package com.xinyuan.xyshop.ui.buy;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.OrderConfirmAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.util.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/19.
 */

public class ConfirmOrderActivity extends BaseActivity {

	@BindView(R.id.rv_confirm_order)
	RecyclerView rv_order;
	private OrderConfirmAdapter adapter;

	@Override
	public int getLayoutId() {
		return R.layout.activity_confirm_order;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {

		List<GoodsVo> list = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			list.add(new GoodsVo());
		}


		FullyLinearLayoutManager layoutManager = new FullyLinearLayoutManager(this);
		layoutManager.setOrientation(1);
		this.rv_order.setLayoutManager(layoutManager);
		this.adapter = new OrderConfirmAdapter(R.layout.activity_confirm_order_item, list);
		this.adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
		this.rv_order.setAdapter(adapter);
		getTop();
		getBottom();
	}


	private void getTop() {
		View view = this.getLayoutInflater().inflate(R.layout.activity_confir_morder_top, (ViewGroup) rv_order.getParent(), false);
		adapter.setHeaderView(view);
	}

	private void getBottom() {
		View view = this.getLayoutInflater().inflate(R.layout.activity_confirm_order_bottom, (ViewGroup) rv_order.getParent(), false);
		adapter.setFooterView(view);
	}
}
