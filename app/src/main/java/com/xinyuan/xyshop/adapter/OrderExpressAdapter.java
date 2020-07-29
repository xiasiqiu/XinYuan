package com.xinyuan.xyshop.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.BuyExpressBean;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;

import java.util.List;

/**
 * Created by fx on 2017/9/12.
 * 订单邮费选择列表Adapter
 */

public class OrderExpressAdapter extends BaseQuickAdapter<BuyExpressBean, BaseViewHolder> {
	public OrderExpressAdapter(int layoutResId, @Nullable List<BuyExpressBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(final BaseViewHolder helper, BuyExpressBean item) {
		final CheckBox bt_store_fav = helper.getView(R.id.cb_order_chekbox);
		bt_store_fav.setText(item.getContent());
		if (item.getCheck()) {
			bt_store_fav.setChecked(true);
		} else {
			bt_store_fav.setChecked(false);
		}

		if (onItemClickListener != null) {
			bt_store_fav.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					onItemClickListener.onItemClick(view, helper.getLayoutPosition());
				}
			});
		}
	}

	public interface OnItemClickListener {
		void onItemClick(View view, int position);
	}

	public void setOnItemClickListener(InvoiceSpecAdapter.OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}

	private InvoiceSpecAdapter.OnItemClickListener onItemClickListener;
}
