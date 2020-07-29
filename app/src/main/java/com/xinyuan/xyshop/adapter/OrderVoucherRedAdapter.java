package com.xinyuan.xyshop.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.CouponBean;
import com.xinyuan.xyshop.bean.RedPacketBean;
import com.youth.xframe.utils.XEmptyUtils;

import java.util.List;

/**
 * Created by fx on 2017/8/14.
 * 红包列表Adapter
 */

public class OrderVoucherRedAdapter extends BaseQuickAdapter<RedPacketBean, BaseViewHolder> {
	public static int chosesId;
	public List<CouponBean> list;

	public OrderVoucherRedAdapter(int layoutResId, @Nullable List<RedPacketBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(final BaseViewHolder helper, final RedPacketBean item) {
		if (XEmptyUtils.isSpace(item.getRedpacketStoreId())) {
			helper.setText(R.id.tv_coupon_type, R.string.red_mall);

		} else {
			helper.setText(R.id.tv_coupon_type, R.string.red_store);

		}

		final CheckBox bt_store_fav = helper.getView(R.id.cb_order_chekbox);
		bt_store_fav.setText(item.getRedpacketName());

		if (item.isCheck()) {
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
