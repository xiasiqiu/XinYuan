package com.xinyuan.xyshop.mvp.contract;

import com.xinyuan.xyshop.bean.ReFundServiceDetailBean;
import com.xinyuan.xyshop.model.ServiceGoodDetailModel;

/**
 * Created by Administrator on 2017/9/23.
 */

public interface ServiceDetailView {
	void showMoneyInfo(ReFundServiceDetailBean bean);

	void showGoodInfo(ServiceGoodDetailModel model);

	void cancelBack(boolean status);
}
