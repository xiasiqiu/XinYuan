package com.xinyuan.xyshop.mvp.contract;

import com.xinyuan.xyshop.model.OrderServiceReasonModel;

/**
 * Created by Administrator on 2017/9/23.
 */

public interface ServiceReasonView {
	void postBack(int type, Long goodsRefundId);
	void reasonBack(OrderServiceReasonModel model);
}
