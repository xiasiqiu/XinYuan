package com.xinyuan.xyshop.ui.mine.order;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.model.OrderModel;
import com.xinyuan.xyshop.util.GlideImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/20.
 */

public class OrderDetailActivity extends BaseActivity {
	private OrderModel.OrderBean orderBean;

	@BindView(R.id.tv_order_status)
	TextView tv_order_status;
	@BindView(R.id.tv_order_closetime)
	TextView tv_order_closetime;
	@BindView(R.id.tv_order_receiver)
	TextView tv_order_receiver;
	@BindView(R.id.tv_order_phone)
	TextView tv_order_phone;
	@BindView(R.id.tv_order_address)
	TextView tv_order_address;
	@BindView(R.id.fl_goods)
	FlexboxLayout fl_goods;
	@BindView(R.id.tv_order_good_price)
	TextView tv_order_good_price;
	@BindView(R.id.tv_order_good_send)
	TextView tv_order_good_send;
	@BindView(R.id.tv_order_price)
	TextView tv_order_price;
	@BindView(R.id.tv_order_orderId)
	TextView tv_order_orderId;
	@BindView(R.id.tv_order_createTime)
	TextView tv_order_createTime;
	@BindView(R.id.bt_order_item_store)
	Button bt_order_item_store;

	@BindView(R.id.bt_order_red)
	Button bt_order_red;

	@BindView(R.id.bt_order_2)
	Button bt_order_2;

	@BindView(R.id.bt_order_1)
	Button bt_order_1;
	@BindView(R.id.rl_order_detail_bottom)
	RelativeLayout rl_order_detail_bottom;

	@Override
	public int getLayoutId() {
		return R.layout.activity_order_detail;
	}

	@Override
	public void initData(Bundle savedInstanceState) {


	}

	@Override
	public void initView() {
		ButterKnife.bind(this);
		Intent intent = this.getIntent();
		orderBean = (OrderModel.OrderBean) intent.getSerializableExtra("order");
		switch (orderBean.getOrderStatus()) {
			case 0:
				tv_order_status.setText("等待买家付款");
				bt_order_1.setVisibility(View.GONE);
				bt_order_2.setText("取消订单");
				bt_order_red.setText("付款");
				ButtomOnClick(0, 2);
				ButtomOnClick(0, 3);
				break;
			case 1:
				tv_order_status.setText("买家已付款");
				rl_order_detail_bottom.setVisibility(View.GONE);
				break;
			case 2:
				tv_order_status.setText("卖家已发货");
				bt_order_1.setText("延长收货");
				ButtomOnClick(2, 1);
				bt_order_2.setText("查看物流");
				ButtomOnClick(2, 2);
				bt_order_red.setText("确认收货");
				ButtomOnClick(2, 3);
				break;
			case 3:
				tv_order_status.setText("等待评价");
				bt_order_1.setVisibility(View.GONE);
				bt_order_2.setText("查看物流");
				ButtomOnClick(3, 2);
				bt_order_red.setText("评价");
				ButtomOnClick(3, 3);
				break;
			case 4:
				tv_order_status.setText("交易成功");
				bt_order_1.setVisibility(View.GONE);
				bt_order_2.setText("删除订单");
				ButtomOnClick(4, 2);
				bt_order_red.setText("追评");
				ButtomOnClick(4, 3);
				break;
			case 5:
				tv_order_status.setText("交易关闭");
				bt_order_red.setText("删除订单");
				bt_order_1.setVisibility(View.GONE);
				bt_order_2.setVisibility(View.GONE);
				ButtomOnClick(5, 3);
				break;
		}

		if (!orderBean.getCloseTime().equals("")) {
			tv_order_closetime.setVisibility(View.VISIBLE);
			tv_order_closetime.setText("剩余" + orderBean.getCloseTime() + "自动关闭");
		}

		tv_order_receiver.setText("收货人" + orderBean.getReceiver());
		tv_order_address.setText(orderBean.getAddress());
		bt_order_item_store.setText(orderBean.getStoreName());
		for (OrderModel.OrderBean.OrderGood good : orderBean.getGoodsList()) {
			AddViewHolder addViewHolder = new AddViewHolder(this, R.layout.fragment_order_item_good);

			ImageView iv_good_img = addViewHolder.getView(R.id.iv_good_img);
			GlideImageLoader.setImage(this, good.getGoodImg(), iv_good_img);
			TextView tv_good_name = addViewHolder.getView(R.id.tv_good_name);
			tv_good_name.setText(good.getGoodName());
			TextView tv_good_spec = addViewHolder.getView(R.id.tv_good_spec);
			tv_good_spec.setText("已选:" + good.getGoodSpecText());
			TextView tv_good_price = addViewHolder.getView(R.id.tv_good_price);
			tv_good_price.setText("￥" + good.getGoodPrice());
			TextView tv_good_old_price = addViewHolder.getView(R.id.tv_good_old_price);
			tv_good_old_price.setText("￥" + good.getGoodOldPrice());
			tv_good_price.setText("￥" + good.getGoodPrice());
			tv_good_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
			TextView tv_good_num = addViewHolder.getView(R.id.tv_good_num);
			tv_good_num.setText("X" + good.getGoodNum());
			if (orderBean.getOrderStatus() == 1 || orderBean.getOrderStatus() == 2) {
				Button bt_order_good_refund = addViewHolder.getView(R.id.bt_order_good_refund);
				bt_order_good_refund.setVisibility(View.VISIBLE);
			} else if (orderBean.getOrderStatus() == 3) {
				Button bt_order_good_service = addViewHolder.getView(R.id.bt_order_good_service);
				bt_order_good_service.setVisibility(View.VISIBLE);
			}

			fl_goods.addView(addViewHolder.getCustomView());
		}


		tv_order_good_price.setText("￥" + orderBean.getOrderPrice());
		tv_order_good_send.setText("￥" + orderBean.getOrderExtra());

		tv_order_price.setText("￥" + String.valueOf(orderBean.getOrderExtra() + orderBean.getOrderPrice()));
		tv_order_orderId.setText("订单编号:" + orderBean.getOrderID());
		tv_order_createTime.setText("创建时间:" + orderBean.getCreateTime());

	}


	private void ButtomOnClick(int Status, int index) {

	}
}
