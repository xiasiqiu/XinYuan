package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.CouponOrderBean;
import com.xinyuan.xyshop.bean.RedPacketBean;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.callback.DialogCallback;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.AddressModel;
import com.xinyuan.xyshop.model.BuyExpressModel;
import com.xinyuan.xyshop.model.ConfirmOrderModel;
import com.xinyuan.xyshop.mvp.contract.ConfirmOrderView;
import com.youth.xframe.utils.XEmptyUtils;

import java.util.List;

/**
 * Created by fx on 2017/8/30.
 */

public class ConfirmOrderPresenter extends BasePresenter<ConfirmOrderView> {
	private Activity mActivity;

	public ConfirmOrderPresenter(Activity activity, ConfirmOrderView view) {
		super(view);
		this.mActivity = activity;
	}

	/**
	 * 获取收货地址
	 */
	public void getAddress() {
		OkGo.<LzyResponse<AddressModel>>post(Urls.URL_USER_ADDRESS)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("id", MyShopApplication.userId)
				.params("ship", 1)
				.params("limit", 1)
				.execute(new JsonCallback<LzyResponse<AddressModel>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<AddressModel>> response) {
						if (HttpUtil.handleResponse(mActivity, response.body())) {
							if (!XEmptyUtils.isEmpty(response.body().getDatas())) {
								mView.showAddress(response.body().getDatas().getAddressList().get(0));

							} else {
								mView.showAddress(null);
							}
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<AddressModel>> response) {
						HttpUtil.handleError(mActivity, response);
					}
				});
	}

	/**
	 * 获取邮费
	 *
	 * @param storeId
	 * @param cartId
	 * @param addressId
	 */
	public void getExpressMoney(final String storeId, final String cartId, String addressId) {
		OkGo.<LzyResponse<BuyExpressModel>>post(Urls.URL_USER_BUY_EXPRESS)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("goodsCartIds", cartId)
				.params("addressId", addressId)
				.execute(new JsonCallback<LzyResponse<BuyExpressModel>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<BuyExpressModel>> response) {
						if (HttpUtil.handleResponse(mActivity, response.body())) {
							if (!XEmptyUtils.isEmpty(response.body().getDatas())) {
								mView.showExpress(storeId, response.body().getDatas(), cartId);
							} else {

							}
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<BuyExpressModel>> response) {
						HttpUtil.handleError(mActivity, response);
					}
				});
	}

	/**
	 * 获取可用的红包
	 *
	 * @param storeId
	 * @param cartId
	 * @param choeseid
	 */
	public void getRedPacketList(long storeId, String cartId, String choeseid) {
		OkGo.<LzyResponse<List<RedPacketBean>>>post(Urls.URL_USER_BUY_RED)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("storeId", storeId)
				.params("goodsCartIds", cartId)
				.params("redPacketIds", choeseid)
				.execute(new JsonCallback<LzyResponse<List<RedPacketBean>>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<List<RedPacketBean>>> response) {
						if (HttpUtil.handleResponse(mActivity, response.body())) {
							mView.showRedList(response.body().getDatas());

						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<List<RedPacketBean>>> response) {
						HttpUtil.handleError(mActivity, response);
					}
				});
	}

	/**
	 * 获取可用的优惠券
	 *
	 * @param storeId
	 * @param cartId
	 * @param choeseid
	 */
	public void getCouponList(long storeId, String cartId, String choeseid) {
		OkGo.<LzyResponse<List<CouponOrderBean>>>post(Urls.URL_USER_BUY_COUPON)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("storeId", storeId)
				.params("goodsCartIds", cartId)
				.params("couponIds", choeseid)
				.execute(new JsonCallback<LzyResponse<List<CouponOrderBean>>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<List<CouponOrderBean>>> response) {
						mView.showCouponList(response.body().getDatas());
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<List<CouponOrderBean>>> response) {
						HttpUtil.handleError(mActivity, response);
					}
				});
	}

	/**
	 * 提交订单
	 * @param jsonInfo
	 */
	public void putOrder(String jsonInfo) {
		OkGo.<LzyResponse<ConfirmOrderModel>>post(Urls.URL_USER_PUT_ORDER)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("orderFormJson", jsonInfo)
				.execute(new DialogCallback<LzyResponse<ConfirmOrderModel>>(mActivity, "订单提交中...") {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<ConfirmOrderModel>> response) {
						if (HttpUtil.handleResponse(mActivity, response.body())) {
							if (!XEmptyUtils.isEmpty(response.body().getDatas())) {
								mView.showOrderStatus(response.body().getDatas());
							}
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<ConfirmOrderModel>> response) {
						HttpUtil.handleError(mActivity, response);
					}
				});


	}
}
