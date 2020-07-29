package com.xinyuan.xyshop.mvp.contract;

import com.xinyuan.xyshop.bean.AddressBean;
import com.xinyuan.xyshop.bean.CouponOrderBean;
import com.xinyuan.xyshop.bean.RedPacketBean;
import com.xinyuan.xyshop.model.AddressModel;
import com.xinyuan.xyshop.model.BuyExpressModel;
import com.xinyuan.xyshop.model.ConfirmOrderModel;

import java.util.List;

/**
 * Created by fx on 2017/8/30.
 */

public interface ConfirmOrderView {
	void showGoodList();

	void showRedList(List<RedPacketBean> list);

	void showCouponList(List<CouponOrderBean> list);

	void showAddress(AddressBean addressBean);

	void showExpress(String storeId, BuyExpressModel expressModel, String ids);

	void showOrderStatus(ConfirmOrderModel model);


}
