package com.xinyuan.xyshop.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.AddressBean;

import java.util.List;

/**
 * Created by fx on 2017/8/21
 * 订单选择收货地址列表Adapter
 */

public class AddressListAdapter extends BaseQuickAdapter<AddressBean, BaseViewHolder> {
	public AddressListAdapter(int layoutResId, @Nullable List<AddressBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, final AddressBean item) {
		helper.setText(R.id.tv_address, item.getAddress() + " " + item.getAddressInfo());
		helper.setText(R.id.tv_user_phone, item.getMobile());
		helper.setText(R.id.tv_user_name, item.getUserName());
		if (item.getIsDefault() == 1) {
			helper.setVisible(R.id.tv_a, true);
		} else {
			helper.setVisible(R.id.tv_a, false);

		}

	}
}
