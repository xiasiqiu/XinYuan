package com.xinyuan.xyshop.mvp.presenter;

import com.xinyuan.xyshop.bean.LzyResponse;
import com.xinyuan.xyshop.http.ApiServer;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.HomeModel;
import com.xinyuan.xyshop.model.OrderModel;
import com.xinyuan.xyshop.mvp.contract.HomeContract;
import com.xinyuan.xyshop.mvp.contract.OrderListContract;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/6/20.
 */

public class OrderListPresenterImpl implements OrderListContract.OrderListPresenter {
	private OrderListContract.OrderListView view;

	public OrderListPresenterImpl(OrderListContract.OrderListView view) {
		this.view = view;
		view.setPresenter(this);
	}


	@Override
	public void initData(int orderType, int orderStatus, boolean isAll) {
		view.showState(0);
		String url = "";
		XLog.v("url="+orderType+":"+orderStatus+":"+isAll);
		switch (orderType) {
			case 0:
				if (isAll) {
					url = Urls.URL_ORDER_ALL;
				} else {
					switch (orderStatus) {
						case 0:
							url = Urls.URL_ORDER_DFK;
							break;
						case 1:
							url = Urls.URL_ORDER_DFH;
							break;
						case 2:
							url = Urls.URL_ORDER_DSH;
							break;
						case 3:
							url = Urls.URL_ORDER_DPJ;
							break;
					}
				}
				break;
			case 1:
				if (isAll) {

					url = Urls.URL_ORDER_ALL;
				} else {
					switch (orderStatus) {
						case 0:
							url = Urls.URL_ORDER_DFK;
							break;
						case 1:
							url = Urls.URL_ORDER_DFH;
							break;
						case 2:
							url = Urls.URL_ORDER_DSH;
							break;
					}
					break;
				}

				break;
		}


		XLog.v("加载数据url--"+url);

		Subscription subscription = ApiServer.getOrderList(url)
				.doOnSubscribe(new Action0() {
					@Override
					public void call() {

					}
				})
				.map(new Func1<LzyResponse<OrderModel>, OrderModel>() {

					@Override
					public OrderModel call(LzyResponse<OrderModel> orderModelLzyResponse) {

						return orderModelLzyResponse.getDatas();
					}
				})
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<OrderModel>() {
					@Override
					public void call(OrderModel orderModel) {
						view.showView(orderModel.getOrderList());
					}
				}, new Action1<Throwable>() {

					@Override
					public void call(Throwable throwable) {
						throwable.printStackTrace();

					}
				});


	}

	@Override
	public void initData() {

	}
}