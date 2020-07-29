package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;
import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.callback.JsonConvert;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.ApiServer;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.ChatDetailModule;
import com.xinyuan.xyshop.model.OnOrderModel;
import com.xinyuan.xyshop.model.OrderModel;
import com.xinyuan.xyshop.mvp.contract.OrderContentView;
import com.youth.xframe.utils.XEmptyUtils;

import java.lang.reflect.Type;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by fx on 2017/7/31.
 */

public class OrderContentPresenter extends BasePresenter<OrderContentView> {
	private static String url = "";
	private Activity mActivity;

	public OrderContentPresenter(OrderContentView view, Activity context) {
		super(view);
		this.mActivity = context;
	}

	/**
	 * 获取实物订单列表
	 * @param ship
	 * @param limit
	 * @param order_status
	 */
	public void getOrderList(int ship, int limit, String order_status) {
		final OrderModel orderModel = new OrderModel();
		OkGo.<LzyResponse<OrderModel>>post(Urls.URL_USER_ORDER)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("ship", ship)
				.params("limit", limit)
				.params("order_status", order_status)
				.converter(new JsonConvert<LzyResponse<OrderModel>>() {
				})
				.adapt(new ObservableBody<LzyResponse<OrderModel>>())
				.subscribeOn(Schedulers.io())//
				.doOnSubscribe(new Consumer<Disposable>() {
					@Override
					public void accept(@NonNull Disposable disposable) throws Exception {
					}
				})
				.map(new Function<LzyResponse<OrderModel>, OrderModel>() {
					@Override
					public OrderModel apply(@NonNull LzyResponse<OrderModel> response) throws Exception {
						if (HttpUtil.handleResponse(mActivity, response)) {
							if (XEmptyUtils.isEmpty(response.datas)) {
								OrderModel module = new OrderModel();
								return module;
							} else {
								return response.datas;
							}
						} else {
							OrderModel module = new OrderModel();
							return module;
						}

					}
				})
				.observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
				.compose(mView.bindLife())
				.subscribe(new Observer<OrderModel>() {
					@Override
					public void onSubscribe(@NonNull Disposable d) {

					}

					@Override
					public void onNext(@NonNull OrderModel orderModel1) {
						mView.showList(orderModel1);
					}

					@Override
					public void onError(@NonNull Throwable e) {
						e.printStackTrace();
						mView.showState(2);
					}

					@Override
					public void onComplete() {


					}
				});

	}

	/**
	 * 获取虚拟订单列表
	 * @param ship
	 * @param limit
	 * @param order_status
	 */
	public void getonlineOrderList(int ship, int limit, String order_status) {
		final OnOrderModel orderModel = new OnOrderModel();
		OkGo.<LzyResponse<OnOrderModel>>post(Urls.URL_USER_ONORDER)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("page", ship)
				.params("limit", limit)
				.params("order_status", order_status)
				.converter(new JsonConvert<LzyResponse<OnOrderModel>>() {
				})
				.adapt(new ObservableBody<LzyResponse<OnOrderModel>>())
				.subscribeOn(Schedulers.io())//
				.doOnSubscribe(new Consumer<Disposable>() {
					@Override
					public void accept(@NonNull Disposable disposable) throws Exception {
					}
				})
				.map(new Function<LzyResponse<OnOrderModel>, OnOrderModel>() {
					@Override
					public OnOrderModel apply(@NonNull LzyResponse<OnOrderModel> response) throws Exception {
						if (HttpUtil.handleResponse(mActivity, response)) {
							if (XEmptyUtils.isEmpty(response.datas)) {
								OnOrderModel module = new OnOrderModel();
								return module;
							} else {
								return response.datas;
							}
						} else {
							OnOrderModel module = new OnOrderModel();
							return module;
						}

					}
				})
				.observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
				.compose(mView.bindtoLife())
				.subscribe(new Observer<OnOrderModel>() {
					@Override
					public void onSubscribe(@NonNull Disposable d) {

					}

					@Override
					public void onNext(@NonNull OnOrderModel orderModel1) {
						mView.showOnLineList(orderModel1);
					}

					@Override
					public void onError(@NonNull Throwable e) {
						e.printStackTrace();
						mView.showState(2);
					}

					@Override
					public void onComplete() {


					}
				});

	}
}
