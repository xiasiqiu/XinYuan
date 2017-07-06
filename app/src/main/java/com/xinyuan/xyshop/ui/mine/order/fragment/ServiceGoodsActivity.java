package com.xinyuan.xyshop.ui.mine.order.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.HomeMultipleItemAdapter;
import com.xinyuan.xyshop.adapter.ServiceGoodsAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.entity.ServiceMultipleItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/1.
 */

public class ServiceGoodsActivity extends BaseActivity {
	@BindView(R.id.rv_service_good)
	RecyclerView rv_service_good;

	ServiceGoodsAdapter adapter;


	@Override
	public int getLayoutId() {
		return R.layout.fragment_service_goods;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {

		final GridLayoutManager manager = new GridLayoutManager(this, 4);
		rv_service_good.setLayoutManager(manager);
		final List<ServiceMultipleItem> list = new ArrayList<>();
		list.add(new ServiceMultipleItem(ServiceMultipleItem.User, ServiceMultipleItem.User_Span_Size));
		list.add(new ServiceMultipleItem(ServiceMultipleItem.Store, ServiceMultipleItem.Store_Span_Size));
		list.add(new ServiceMultipleItem(ServiceMultipleItem.User, ServiceMultipleItem.User_Span_Size));
		list.add(new ServiceMultipleItem(ServiceMultipleItem.Store, ServiceMultipleItem.Store_Span_Size));
		list.add(new ServiceMultipleItem(ServiceMultipleItem.Store, ServiceMultipleItem.Store_Span_Size));
		adapter = new ServiceGoodsAdapter(list);
		adapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
			@Override
			public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {

				return list.get(position).getSpanSize();
			}
		});
		rv_service_good.setAdapter(adapter);
	}


}
