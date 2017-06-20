package com.xinyuan.xyshop.adapter;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayout;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.model.OrderModel;
import com.xinyuan.xyshop.ui.goods.StoreActivity;
import com.xinyuan.xyshop.ui.mine.order.OrderDetailActivity;
import com.xinyuan.xyshop.util.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */

public class OrderAdapter extends BaseQuickAdapter<OrderModel.OrderBean, BaseViewHolder> {


	public OrderAdapter(@LayoutRes int layoutResId, @Nullable List<OrderModel.OrderBean> data) {
		super(layoutResId, data);

	}

	@Override
	protected void convert(BaseViewHolder helper, final OrderModel.OrderBean item) {
		Button bt_store = helper.getView(R.id.bt_order_item_store);
		TextView tv_status = helper.getView(R.id.tv_order_item_status);
		TextView tv_price = helper.getView(R.id.tv_order_good_price);
		bt_store.setText(item.getStoreName());
		bt_store.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(mContext, StoreActivity.class);
				intent.putExtra("storeId", item.getStoreId());
				mContext.startActivity(intent);

			}
		});
		tv_price.setText("共" + item.getGoodsList().size() + "件商品 合计：￥" + item.getOrderPrice() + "(含运费￥" + item.getOrderExtra() + ")");

		switch (item.getOrderStatus()) {

			case 0:
				LinearLayout ll_order_bt_dfk = helper.getView(R.id.ll_order_bt_dfk);
				ll_order_bt_dfk.setVisibility(View.VISIBLE);
				tv_status.setText("等待买家付款");
				break;
			case 1:
				tv_status.setText("买家已付款");
				break;
			case 2:
				LinearLayout ll_order_bt_dsh = helper.getView(R.id.ll_order_bt_dsh);
				ll_order_bt_dsh.setVisibility(View.VISIBLE);
				tv_status.setText("卖家已发货");
				break;
			case 3:
				LinearLayout ll_order_bt_dpj = helper.getView(R.id.ll_order_bt_dpj);
				ll_order_bt_dpj.setVisibility(View.VISIBLE);
				tv_status.setText("等待评价");
				break;
			case 4:
				LinearLayout ll_order_bt_finish = helper.getView(R.id.ll_order_bt_finish);
				ll_order_bt_finish.setVisibility(View.VISIBLE);
				tv_status.setText("交易成功");
				break;
			case 5:
				LinearLayout ll_order_bt_close = helper.getView(R.id.ll_order_bt_close);
				ll_order_bt_close.setVisibility(View.VISIBLE);
				tv_status.setText("交易关闭");
				break;

		}


		FlexboxLayout fl_order_goods = helper.getView(R.id.fl_order_goods);
		for (OrderModel.OrderBean.OrderGood good : item.getGoodsList()) {

			AddViewHolder addViewHolder = new AddViewHolder(mContext, R.layout.fragment_order_item_good);
			ImageView iv_good_img = addViewHolder.getView(R.id.iv_good_img);
			GlideImageLoader.setImage(mContext, good.getGoodImg(), iv_good_img);
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
			fl_order_goods.addView(addViewHolder.getCustomView());
		}

		fl_order_goods.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(mContext, OrderDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("order", item);
				intent.putExtras(bundle);
				mContext.startActivity(intent);
			}
		});


	}


}
