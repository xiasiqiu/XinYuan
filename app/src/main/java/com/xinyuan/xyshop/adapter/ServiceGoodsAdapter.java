package com.xinyuan.xyshop.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.HomeMultipleItem;
import com.xinyuan.xyshop.entity.ServiceMultipleItem;
import com.xinyuan.xyshop.model.HomeModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/1.
 */

public class ServiceGoodsAdapter extends BaseMultiItemQuickAdapter<ServiceMultipleItem, BaseViewHolder> {


	/**
	 * Same as QuickAdapter#QuickAdapter(Context,int) but with
	 * some initialization data.
	 *
	 * @param data A new list is created out of this one to avoid mutable list
	 */
	public ServiceGoodsAdapter(List<ServiceMultipleItem> data) {
		super(data);
		addItemType(ServiceMultipleItem.User, R.layout.fragment_service_goods_item_user);
		addItemType(ServiceMultipleItem.Store, R.layout.fragment_service_goods_item_store);
	}

	@Override
	protected void convert(BaseViewHolder helper, ServiceMultipleItem item) {
		switch (item.getItemType()) {
			case ServiceMultipleItem.User:
				if (helper.getLayoutPosition() == 0) {

				} else if (helper.getLayoutPosition() == 2) {
					RelativeLayout rl_order_status = helper.getView(R.id.rl_order_status);
					rl_order_status.setVisibility(View.GONE);
					RelativeLayout rl_service_reason = helper.getView(R.id.rl_service_reason);
					rl_service_reason.setVisibility(View.GONE);
					RelativeLayout rl_service_price = helper.getView(R.id.rl_service_price);
					rl_service_price.setVisibility(View.GONE);
					TextView tv_status_title = helper.getView(R.id.tv_status_title);
					tv_status_title.setText("买家已退货");
					TextView tv_status_hint = helper.getView(R.id.tv_status_hint);
					tv_status_hint.setVisibility(View.GONE);
					TextView tv_goods_name_title = helper.getView(R.id.tv_goods_name_title);
					tv_goods_name_title.setText("物流公司");
					TextView tv_goods_name = helper.getView(R.id.tv_goods_name);
					tv_goods_name.setText("顺丰快递");
					TextView tv_good_orderId_title = helper.getView(R.id.tv_good_orderId_title);
					tv_good_orderId_title.setText("物流单号");
					TextView tv_good_orderId = helper.getView(R.id.tv_good_orderId);
					tv_good_orderId.setText("78787987878787");
				}
				break;
			case ServiceMultipleItem.Store:
				if (helper.getLayoutPosition() == 1) {

				} else if (helper.getLayoutPosition() == 3) {
					TextView tv_status_title = helper.getView(R.id.tv_status_title);
					tv_status_title.setText("卖家确认收货");
					TextView tv_status_hint = helper.getView(R.id.tv_status_hint);
					tv_status_hint.setVisibility(View.GONE);
					LinearLayout ll_service_content = helper.getView(R.id.ll_service_content);
					ll_service_content.setVisibility(View.GONE);
					View service_line=helper.getView(R.id.service_line);
					service_line.setVisibility(View.GONE);
				} else if (helper.getLayoutPosition() == 4) {

				}
				break;
		}
	}
}
