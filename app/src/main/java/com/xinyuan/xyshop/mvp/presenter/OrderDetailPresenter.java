package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.OrderBean;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.even.OrderBusEven;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.mvp.contract.OrderDetailView;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2017/9/28.
 */

public class OrderDetailPresenter extends BasePresenter<OrderDetailView> {
	private Activity mActivity;

	public OrderDetailPresenter(Activity activity, OrderDetailView view) {
		super(view);
		this.mActivity = activity;
	}

	/**
	 * 请求实物订单详情数据
	 */
	public void getOrderDetail(long orderId) {
		OkGo.<LzyResponse<OrderBean>>post(Urls.URL_USER_ORDER_DETAIL)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("orderId", orderId)
				.execute(new JsonCallback<LzyResponse<OrderBean>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<OrderBean>> response) {
						if (HttpUtil.handleResponse(mActivity, response.body())) {
							mView.showOrderDetail(response.body().getDatas());
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<OrderBean>> response) {
						HttpUtil.handleError(mActivity, response);
					}
				});
	}

	/**
	 * 请求虚拟订单详情数据
	 */
	public void getOnOrderDetail(long orderId) {
		OkGo.<LzyResponse<OrderBean>>post(Urls.URL_USER_ONORDER_DETAIL)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("virOrderId", orderId)
				.execute(new JsonCallback<LzyResponse<OrderBean>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<OrderBean>> response) {
						if (HttpUtil.handleResponse(mActivity, response.body())) {
							mView.showOrderDetail(response.body().getDatas());
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<OrderBean>> response) {
						HttpUtil.handleError(mActivity, response);
					}
				});
	}
	/**
	 * 确认收货
	 *
	 * @param orderId
	 */
	public void ReceivingOrder(long orderId) {
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_ORDER_RECEIVE)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("orderFormId", orderId)
				.execute(new JsonCallback<LzyResponse<TokenBean>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						if (HttpUtil.handleResponse(mActivity, response.body())) {
							mView.receivingCallBack(true);
						} else {

						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						HttpUtil.handleError(mActivity, response);

					}
				});

	}

	/**
	 * 取消订单
	 *
	 * @param orderId
	 */
	public void OrderCancel(long orderId) {
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_ORDER_CANCEL)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("orderFormId", orderId)
				.execute(new JsonCallback<LzyResponse<TokenBean>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						if (HttpUtil.handleResponse(mActivity, response.body())) {
							mView.cancelCallBack(true);
						} else {
							mView.cancelCallBack(false);
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						HttpUtil.handleError(mActivity, response);

					}
				});

	}

	/**
	 * 删除订单
	 *
	 * @param orderId
	 */
	public void OrderDetele(long orderId) {
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_USER_ORDER_DETELE)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("orderId", orderId)
				.execute(new JsonCallback<LzyResponse<TokenBean>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						if (HttpUtil.handleResponse(mActivity, response.body())) {
							mView.deteleCallBack(true);
						} else {
							mView.deteleCallBack(false);
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						HttpUtil.handleError(mActivity, response);

					}
				});

	}
}
