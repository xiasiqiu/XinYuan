package com.xinyuan.xyshop.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.VoucherAdapter;
import com.xinyuan.xyshop.entity.GoodsEvaluate;
import com.xinyuan.xyshop.util.FullyLinearLayoutManager;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/6.
 */

public class StoreVoucherDialog extends Dialog {

	@BindView(R.id.rv_store_voucher)
	RecyclerView rv_store_voucher;
	@BindView(R.id.iv_close)
	ImageView iv_close;
	VoucherAdapter voucherAdapter;

	public StoreVoucherDialog(@NonNull Context context) {
		super(context, R.style.CommonDialog);

	}


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_shopcar_dialog_store_voucher);
		ButterKnife.bind((Dialog) this);

		List<GoodsEvaluate> data = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			data.add(new GoodsEvaluate());
		}
		XLog.list(data);

		voucherAdapter = new VoucherAdapter(R.layout.item_store_voucher, data);
		FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(getContext());
		rv_store_voucher.setNestedScrollingEnabled(false);
		//设置布局管理器
		rv_store_voucher.setLayoutManager(linearLayoutManager);
		this.rv_store_voucher.setAdapter(this.voucherAdapter);
		this.voucherAdapter.notifyDataSetChanged();
		iv_close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dismiss();
			}
		});
	}
}
