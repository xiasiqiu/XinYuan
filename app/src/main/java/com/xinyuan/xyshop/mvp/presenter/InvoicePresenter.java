package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.InvoiceBean;
import com.xinyuan.xyshop.bean.InvoiceContentBean;
import com.xinyuan.xyshop.bean.InvoiceHeaderBean;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.FooterModel;
import com.xinyuan.xyshop.model.InvoiceModel;
import com.xinyuan.xyshop.mvp.contract.InvoiceView;

/**
 * Created by Administrator on 2017/9/6.
 */

public class InvoicePresenter extends BasePresenter<InvoiceView> {
	private Activity mActivity;

	public InvoicePresenter(Activity activity, InvoiceView view) {
		super(view);
		this.mActivity = activity;
	}

	/**
	 * 获取发票数据
	 */
	public void getInvoiceList() {
		OkGo.<LzyResponse<InvoiceModel>>post(Urls.URL_USER_INVOICE)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.execute(new JsonCallback<LzyResponse<InvoiceModel>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<InvoiceModel>> response) {
						if (HttpUtil.handleResponse(mActivity, response.body())) {
							mView.showInvoice(response.body().getDatas());
						}

					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<InvoiceModel>> response) {
						HttpUtil.handleError(mActivity, response);
					}
				});

	}

	/**
	 * 保存发票抬头
	 *
	 * @param header
	 */
	public void saveInvoiceHeader(String header) {
		OkGo.<LzyResponse<InvoiceHeaderBean>>post(Urls.URL_USER_INVOICE_ADDHEAD)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("headerName", header)
				.execute(new JsonCallback<LzyResponse<InvoiceHeaderBean>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<InvoiceHeaderBean>> response) {
						if (HttpUtil.handleResponse(mActivity, response.body())) {
							mView.saveHeader(response.body().getDatas());
						}

					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<InvoiceHeaderBean>> response) {
						HttpUtil.handleError(mActivity, response);
					}
				});
	}

	/**
	 * 保存发票
	 * @param headerId
	 * @param contentId
	 * @param invoice_type
	 * @param aptitudeId
	 * @param userName
	 * @param phone
	 * @param email
	 * @param address
	 */
	public void saveInvoice(long headerId, long contentId, int invoice_type, long aptitudeId, String userName, String phone, String email, String address) {
		OkGo.<LzyResponse<InvoiceBean>>post(Urls.URL_USER_INVOICE_SAVE)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("headerId", headerId)
				.params("contentId", contentId)
				.params("invoice_type", invoice_type)
				.params("aptitudeId", aptitudeId)
				.params("userName", userName)
				.params("phone", phone)
				.params("email", email)
				.params("address", address)
				.execute(new JsonCallback<LzyResponse<InvoiceBean>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<InvoiceBean>> response) {
						if (HttpUtil.handleResponse(mActivity, response.body())) {
							mView.saveInvoice(response.body().getDatas().getInvoiceInfoId());
						}

					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<InvoiceBean>> response) {
						HttpUtil.handleError(mActivity, response);
					}
				});

	}
}
