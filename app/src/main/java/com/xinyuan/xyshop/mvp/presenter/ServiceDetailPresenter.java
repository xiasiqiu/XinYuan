package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.ReFundServiceDetailBean;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.callback.DialogCallback;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.ServiceGoodDetailModel;
import com.xinyuan.xyshop.mvp.contract.ServiceDetailView;

/**
 * Created by Administrator on 2017/9/23.
 */

public class ServiceDetailPresenter extends BasePresenter<ServiceDetailView> {

	private Activity mActivity;

	public ServiceDetailPresenter(Activity activity, ServiceDetailView view) {
		super(view);
		this.mActivity = activity;
	}


	public void getMoneyDetail(long RefundId) {
		OkGo.<LzyResponse<ReFundServiceDetailBean>>post(Urls.URL_ORDER_SERVICE_MONEY_DETAIL)
				.tag(this)
				.headers("token",MyShopApplication.Token)
				.params("refundId", RefundId)
				.execute(new JsonCallback<LzyResponse<ReFundServiceDetailBean>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<ReFundServiceDetailBean>> response) {
						if (HttpUtil.handleResponse(mActivity, response.body())) {
							mView.showMoneyInfo(response.body().getDatas());
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<ReFundServiceDetailBean>> response) {
						HttpUtil.handleError(mActivity, response);
					}
				});


	}

	public void getGoodDetail(long returnId) {
		OkGo.<LzyResponse<ServiceGoodDetailModel>>post(Urls.URL_ORDER_SERVICE_GOOD_DETAIL)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("returnId", returnId)
				.execute(new JsonCallback<LzyResponse<ServiceGoodDetailModel>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<ServiceGoodDetailModel>> response) {
						if (HttpUtil.handleResponse(mActivity, response.body())) {
							mView.showGoodInfo(response.body().datas);
						}

					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<ServiceGoodDetailModel>> response) {
						HttpUtil.handleError(mActivity, response);
					}
				});
	}

	/**
	 * 撤销售后
	 *
	 * @param type
	 * @param returnORrefundId
	 */
	public void cancelService(String type, long returnORrefundId) {
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_ORDER_SERVICE_CANCEL)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("type", type)
				.params("returnORrefundId", returnORrefundId)
				.execute(new DialogCallback<LzyResponse<TokenBean>>(mActivity, "撤销中...") {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						if (HttpUtil.handleResponse(mActivity, response.body())) {
							mView.cancelBack(true);
						} else {
							mView.cancelBack(false);
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						HttpUtil.handleError(mActivity, response);
					}
				});

	}
}
