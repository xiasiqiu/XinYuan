package com.xinyuan.xyshop.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.CouponBean;
import com.xinyuan.xyshop.bean.CouponOrderBean;
import com.xinyuan.xyshop.bean.RedPacketBean;
import com.xinyuan.xyshop.even.BuyBusEven;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by fx on 2017/8/14.
 * 订单优惠券选择列表Adapter
 */

public class OrderVoucherCouponAdapter extends BaseQuickAdapter<CouponOrderBean, BaseViewHolder> {
	public static int chosesId;
	public List<CouponBean> list;

	public OrderVoucherCouponAdapter(int layoutResId, @Nullable List<CouponOrderBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(final BaseViewHolder helper, final CouponOrderBean item) {
		final CheckBox bt_store_fav = helper.getView(R.id.cb_order_chekbox);
		bt_store_fav.setText(item.getCouponName());
		if (XEmptyUtils.isSpace(item.getCouponStoreId())) {
			helper.setText(R.id.tv_coupon_type, R.string.coupon_mall);

		} else {
			helper.setText(R.id.tv_coupon_type, R.string.coupon_store);

		}
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
