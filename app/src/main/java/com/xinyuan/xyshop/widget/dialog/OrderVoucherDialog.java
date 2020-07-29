package com.xinyuan.xyshop.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.InvoiceSpecAdapter;
import com.xinyuan.xyshop.adapter.OrderExpressAdapter;
import com.xinyuan.xyshop.adapter.OrderVoucherCouponAdapter;
import com.xinyuan.xyshop.adapter.OrderVoucherRedAdapter;
import com.xinyuan.xyshop.bean.BuyExpressBean;
import com.xinyuan.xyshop.bean.CouponOrderBean;
import com.xinyuan.xyshop.bean.RedPacketBean;
import com.xinyuan.xyshop.even.BuyBusEven;
import com.xinyuan.xyshop.model.BuyExpressModel;
import com.xinyuan.xyshop.ui.welcome.GuideActivity;
import com.xinyuan.xyshop.ui.welcome.WelcomeActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/14.
 */

public class OrderVoucherDialog extends Dialog {
	@BindView(R.id.tv_voucher_type)
	TextView tv_voucher_type;
	@BindView(R.id.rv_order_voucher)
	RecyclerView rv_order_voucher;
	@BindView(R.id.bt_close)
	Button bt_close;

	private List<CouponOrderBean> couponList;
	private List<RedPacketBean> redList;


	private OrderVoucherRedAdapter redAdapter;
	private OrderVoucherCouponAdapter couponAdapter;
	private BuyExpressModel expressModel;

	public OrderVoucherDialog(@NonNull Context context, List<RedPacketBean> redPacketBeans, List<CouponOrderBean> couponOrderBeans, BuyExpressModel expressModel) {
		super(context, R.style.CommonDialog);
		this.couponList = couponOrderBeans;
		this.redList = redPacketBeans;
		this.expressModel = expressModel;
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_order_voucher);
		ButterKnife.bind((Dialog) this);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
		linearLayoutManager.setOrientation(1);
		//设置布局管理器
		rv_order_voucher.setLayoutManager(linearLayoutManager);

		bt_close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dismiss();
			}
		});
		initData();
	}

	private List<BuyExpressBean> expressList = new ArrayList<>();
	private OrderExpressAdapter expressAdapter;

	private void initData() {

		if (!XEmptyUtils.isEmpty(expressModel)) {
			XLog.v(expressModel.toString());
			if (!XEmptyUtils.isSpace(expressModel.getEms())) {
				BuyExpressBean ems = new BuyExpressBean();
				ems.setType("ems");
				ems.setCheck(false);
				ems.setContent(expressModel.getEms());
				expressList.add(ems);
			}
			if (!XEmptyUtils.isSpace(expressModel.getSeller())) {
				BuyExpressBean seller = new BuyExpressBean();
				seller.setType("seller");
				seller.setCheck(false);

				seller.setContent(expressModel.getSeller());
				expressList.add(seller);
			}
			if (!XEmptyUtils.isSpace(expressModel.getMail())) {
				BuyExpressBean mail = new BuyExpressBean();
				mail.setType("mail");
				mail.setCheck(false);

				mail.setContent(expressModel.getMail());
				expressList.add(mail);
			}
			if (!XEmptyUtils.isSpace(expressModel.getExpress())) {
				BuyExpressBean express = new BuyExpressBean();
				express.setType("express");
				express.setCheck(false);

				express.setContent(expressModel.getExpress());
				expressList.add(express);
			}
			tv_voucher_type.setText("选择快递方式");
			expressList.get(0).setCheck(true);
			expressAdapter = new OrderExpressAdapter(R.layout.item_order_voucher, expressList);
			this.rv_order_voucher.setAdapter(this.expressAdapter);
			expressAdapter.setOnItemClickListener(new InvoiceSpecAdapter.OnItemClickListener() {
				@Override
				public void onItemClick(View view, int position) {
					for (int i = 0; i < expressAdapter.getData().size(); i++) {
						if (i == position) {
							expressAdapter.getData().get(i).setCheck(true);
							XLog.list(expressAdapter.getData());
							XLog.v("position:" + position+expressAdapter.getData().get(position).toString());
							BuyExpressBean buyExpressBean=expressAdapter.getData().get(position);
							//EventBus.getDefault().post(new BuyBusEven(BuyBusEven.choeseExpress, expressAdapter.getData().get(i)));
							EventBus.getDefault().post(new BuyBusEven(BuyBusEven.choeseExpress,buyExpressBean));


						} else {
							expressAdapter.getData().get(i).setCheck(false);
						}
					}
					expressAdapter.notifyDataSetChanged();
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							dismiss();
						}
					}, 300);

				}


			});


		}


		if (!XEmptyUtils.isEmpty(couponList)) {
			tv_voucher_type.setText("选择优惠券");
			//couponList.get(0).setCheck(true);
			couponAdapter = new OrderVoucherCouponAdapter(R.layout.item_order_voucher, couponList);
			this.rv_order_voucher.setAdapter(this.couponAdapter);
			couponAdapter.setOnItemClickListener(new InvoiceSpecAdapter.OnItemClickListener() {
				@Override
				public void onItemClick(View view, int position) {
					for (int i = 0; i < couponAdapter.getData().size(); i++) {
						if (i == position) {
							EventBus.getDefault().post(new BuyBusEven(BuyBusEven.choeseCouponVoucher, couponAdapter.getData().get(i)));
							couponAdapter.getData().get(i).setCheck(true);
						} else {
							couponAdapter.getData().get(i).setCheck(false);
						}
					}
					couponAdapter.notifyDataSetChanged();
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							dismiss();
						}
					}, 300);

				}


			});
		}
		if (!XEmptyUtils.isEmpty(redList)) {
			tv_voucher_type.setText("选择红包");
			//redList.get(0).setCheck(true);

			redAdapter = new OrderVoucherRedAdapter(R.layout.item_order_voucher, redList);
			this.rv_order_voucher.setAdapter(this.redAdapter);
			redAdapter.setOnItemClickListener(new InvoiceSpecAdapter.OnItemClickListener() {
				@Override
				public void onItemClick(View view, int position) {
					for (int i = 0; i < redAdapter.getData().size(); i++) {
						if (i == position) {
							redAdapter.getData().get(i).setCheck(true);
							EventBus.getDefault().post(new BuyBusEven(BuyBusEven.choeseRedVoucher, redAdapter.getData().get(i)));
						} else {
							redAdapter.getData().get(i).setCheck(false);
						}
					}
					redAdapter.notifyDataSetChanged();
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							dismiss();
						}
					}, 300);
				}


			});
		}
	}



	@Override
	public void dismiss() {
		super.dismiss();
	}

}
