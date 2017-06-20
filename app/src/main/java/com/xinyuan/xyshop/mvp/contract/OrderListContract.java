package com.xinyuan.xyshop.mvp.contract;

import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.base.BaseView;
import com.xinyuan.xyshop.model.OrderModel;

import java.util.List;

/**
 * Created by Administrator on 2017/6/20.
 */

public class OrderListContract {

	public interface OrderListView extends BaseView<OrderListPresenter> {
		void showView(List<OrderModel.OrderBean> orderList);
	}

	public interface OrderListPresenter extends BasePresenter<OrderListView> {

		void initData(int orderType, int OrderStatus, boolean isAll);
	}
}
