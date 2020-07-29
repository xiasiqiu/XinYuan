package com.xinyuan.xyshop.mvp.contract;


import com.xinyuan.xyshop.model.ServiceModel;

/**
 * Created by Administrator on 2017/9/26.
 */

public interface OrderServiceView {
	void showServiceList(ServiceModel model);

	void showState(int state);
}
