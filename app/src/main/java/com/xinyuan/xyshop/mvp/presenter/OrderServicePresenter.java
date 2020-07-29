package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.AddressModel;
import com.xinyuan.xyshop.model.ServiceModel;
import com.xinyuan.xyshop.mvp.contract.OrderServiceView;
import com.youth.xframe.utils.XEmptyUtils;

/**
 * Created by Administrator on 2017/9/26.
 */

public class OrderServicePresenter extends BasePresenter<OrderServiceView> {
	private Activity mActivity;

	public OrderServicePresenter(Activity activity, OrderServiceView view) {
		super(view);
		this.mActivity = activity;
	}

	public void getServiceList(int page) {
		OkGo.<LzyResponse<ServiceModel>>post(Urls.URL_ORDER_SERVICE)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("ship", page)
				.params("limit", 10)
				.execute(new JsonCallback<LzyResponse<ServiceModel>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<ServiceModel>> response) {
						if (HttpUtil.handleResponse(mActivity, response.body())) {
							mView.showServiceList(response.body().getDatas());
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<ServiceModel>> response) {
						HttpUtil.handleError(mActivity, response);
					}
				});


	}
}
