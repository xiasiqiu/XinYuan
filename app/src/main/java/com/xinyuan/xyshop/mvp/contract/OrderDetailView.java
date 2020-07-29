package com.xinyuan.xyshop.mvp.contract;

import com.xinyuan.xyshop.bean.OrderBean;

/**
 * Created by Administrator on 2017/9/28.
 */

public interface OrderDetailView {
	void showOrderDetail(OrderBean datas);


	void receivingCallBack(boolean b);

	void cancelCallBack(boolean result);

	void deteleCallBack(boolean result);
}
