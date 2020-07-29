package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.ServicePostBean;
import com.xinyuan.xyshop.callback.DialogCallback;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.OrderServiceReasonModel;
import com.xinyuan.xyshop.mvp.contract.ServiceReasonView;
import com.youth.xframe.utils.log.XLog;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/23.
 */

public class ServiceReasonPresenter extends BasePresenter<ServiceReasonView> {
	private Activity mActivity;

	public ServiceReasonPresenter(Activity activity, ServiceReasonView view) {
		super(view);
		this.mActivity = activity;

	}

	public void getRefundReason(long orderId, String goodsCartId) {
		OkGo.<LzyResponse<OrderServiceReasonModel>>post(Urls.URL_ORDER_SERVICE_REASON)
				.tag(this)
				.params("orderId", orderId)
				.params("goodsCartId", goodsCartId)
				.execute(new JsonCallback<LzyResponse<OrderServiceReasonModel>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<OrderServiceReasonModel>> response) {
						if (HttpUtil.handleResponse(mActivity, response.body())) {
							mView.reasonBack(response.body().getDatas());
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<OrderServiceReasonModel>> response) {
						HttpUtil.handleError(mActivity, response);
					}
				});

	}


	public void postRefundMoney(long orderId, long goodsCartId, String refund_amount, String msg, long goodsReturnResonId, ArrayList<File> files) {
		OkGo.<LzyResponse<ServicePostBean>>post(Urls.URL_ORDER_SERVICE_MONEY)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("goodsCartId", goodsCartId)
				.params("orderId", orderId)
				.params("refund_amount", refund_amount)
				.params("msg", msg)
				.params("goodsReturnResonId", goodsReturnResonId)
				.addFileParams("files", files)
				.execute(new DialogCallback<LzyResponse<ServicePostBean>>(mActivity, "申请提交中...") {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<ServicePostBean>> response) {
						if (HttpUtil.handleResponse(mActivity, response.body())) {
							XLog.v(response.body().datas.toString());
							mView.postBack(1, response.body().getDatas().getGoodsRefundId());
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<ServicePostBean>> response) {
						HttpUtil.handleError(mActivity, response);
					}
				});

	}

	public void postRefundGood(long orderId, long goodsCartId, String refund_amount, String msg, long goodsReturnResonId, ArrayList<File> files) {
		OkGo.<LzyResponse<ServicePostBean>>post(Urls.URL_ORDER_SERVICE_GOOD)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("goodsCartId", goodsCartId)
				.params("orderId", orderId)
				.params("return_amount", refund_amount)
				.params("msg", msg)
				.params("goodsReturnResonId", goodsReturnResonId)
				.addFileParams("files", files)
				.execute(new DialogCallback<LzyResponse<ServicePostBean>>(mActivity, "申请提交中...") {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<ServicePostBean>> response) {
						if (HttpUtil.handleResponse(mActivity, response.body())) {
							mView.postBack(2, response.body().getDatas().getGoodsReturnId());

						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<ServicePostBean>> response) {
						HttpUtil.handleError(mActivity, response);
					}
				});

	}
}
