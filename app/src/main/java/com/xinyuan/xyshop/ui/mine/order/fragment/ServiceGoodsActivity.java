package com.xinyuan.xyshop.ui.mine.order.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.HomeMultipleItemAdapter;
import com.xinyuan.xyshop.adapter.ServiceGoodsAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.entity.ServiceMultipleItem;
import com.xinyuan.xyshop.ui.mine.order.ServiceMoneyDetailActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.widget.dialog.color.ColorDialog;
import com.youth.xframe.widget.XToast;

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

	@BindView(R.id.tv_header_center)
	TextView tv_header_center;


	@BindView(R.id.toolbar_tv)
	Toolbar toolbar_tv;

	@Override
	public int getLayoutId() {
		return R.layout.fragment_service_goods;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		tv_header_center.setText("退货售后");
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

	@OnClick({R.id.bt_order_eva_later, R.id.bt_order_finish_logistical})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.bt_order_eva_later:

				break;
			case R.id.bt_order_finish_logistical:
				final ColorDialog colorDialog = new ColorDialog(this);
				colorDialog.setTitle("撤销退货");
				colorDialog.setContentText("确认撤销售后退货？");
				colorDialog.setPositiveListener("确认", new ColorDialog.OnPositiveListener() {
					@Override
					public void onClick(ColorDialog dialog) {

						XToast.info("已撤销");
						colorDialog.dismiss();
						CommUtil.gotoActivity(ServiceGoodsActivity.this, ServiceMoneyDetailActivity.class, false, null);
					}
				})
						.setNegativeListener("取消", new ColorDialog.OnNegativeListener() {
							@Override
							public void onClick(ColorDialog dialog) {
								colorDialog.dismiss();
							}
						}).show();

				break;

		}
	}
}
