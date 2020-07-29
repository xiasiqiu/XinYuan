package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.callback.DialogCallback;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.mvp.contract.OrderEvaView;
import com.youth.xframe.utils.log.XLog;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by fx on 2017/9/18.
 */

public class OrderEvaPresenter extends BasePresenter<OrderEvaView> {
	private Activity mActivity;

	public OrderEvaPresenter(Activity activity, OrderEvaView view) {
		super(view);
		this.mActivity = activity;
	}

	/**
	 * 提交实物订单评价
	 *
	 * @param orderId
	 * @param goodsCartId
	 * @param evaluateinfo
	 * @param evaluate_buyer_val
	 * @param description_evaluate
	 * @param service_evaluate
	 * @param ship_evaluate
	 * @param imgs
	 * @param anonymous
	 */
	public void PostEva(long orderId, long goodsCartId, String evaluateinfo, int evaluate_buyer_val, int description_evaluate, int service_evaluate, int ship_evaluate, ArrayList<File> imgs, Boolean anonymous) {
		XLog.v(orderId+"--"+goodsCartId+"---"+evaluateinfo+"---"+evaluateinfo+"---"+evaluate_buyer_val+"--"+description_evaluate+"---"+service_evaluate+"---"+ship_evaluate+"anonymous");
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_ORDER_ADD_EVA)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("orderId", orderId)
				.params("goodsCartId", goodsCartId)
				.params("evaluateinfo", evaluateinfo)
				.params("evaluate_buyer_val", evaluate_buyer_val)
				.params("description_evaluate", description_evaluate)
				.params("service_evaluate", service_evaluate)
				.params("ship_evaluate", ship_evaluate)
				.params("anonymous", anonymous)
				.addFileParams("files", imgs)
				.execute(new DialogCallback<LzyResponse<TokenBean>>(mActivity, "提交评价中...") {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						if (HttpUtil.handleResponse(mActivity, response.body())) {
							mView.showEvaStatus(true);
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						HttpUtil.handleError(mActivity, response);
					}
				});


	}
	/**
	 * 提交虚拟订单评价
	 *
	 * @param orderId
	 * @param goodsId
	 * @param evaluateinfo
	 * @param evaluate_buyer_val
	 * @param description_evaluate
	 * @param service_evaluate
	 * @param ship_evaluate
	 * @param imgs
	 * @param anonymous
	 */
	public void PostOnLineEva(long orderId, long goodsId, String evaluateinfo, int evaluate_buyer_val, int description_evaluate, int service_evaluate, int ship_evaluate, ArrayList<File> imgs, Boolean anonymous) {
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_ORDER_ADD_ONLINE_EVA)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("virOrderId", orderId)
				.params("goodsId", goodsId)
				.params("evaluateinfo", evaluateinfo)
				.params("evaluate_buyer_val", evaluate_buyer_val)
				.params("description_evaluate", description_evaluate)
				.params("service_evaluate", service_evaluate)
				.params("ship_evaluate", ship_evaluate)
				.params("anonymous", anonymous)
				.addFileParams("files", imgs)
				.execute(new DialogCallback<LzyResponse<TokenBean>>(mActivity, "提交评价中...") {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						if (HttpUtil.handleResponse(mActivity, response.body())) {
							mView.showEvaStatus(true);
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						HttpUtil.handleError(mActivity, response);
					}
				});


	}

	/**
	 * 追评实物订单
	 *
	 * @param orderId
	 * @param goodsCartId
	 * @param evaluateinfo
	 * @param imgs
	 */
	public void AddEva(long orderId, long goodsCartId, String evaluateinfo, ArrayList<File> imgs) {
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_ORDER_RATING_EVA)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("orderId", orderId)
				.params("goodsCartId", goodsCartId)
				.params("chaseRatingsInfo", evaluateinfo)
				.addFileParams("files", imgs)
				.execute(new DialogCallback<LzyResponse<TokenBean>>(mActivity, "提交评价中...") {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						if (HttpUtil.handleResponse(mActivity, response.body())) {
							mView.showEvaStatus(true);
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						HttpUtil.handleError(mActivity, response);
					}
				});


	}
	/**
	 * 追评虚拟订单
	 *
	 * @param virOrderId
	 * @param goodsId
	 * @param evaluateinfo
	 * @param imgs
	 */
	public void AddOnlineEva(long virOrderId, long goodsId, String evaluateinfo, ArrayList<File> imgs) {
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_ORDER_RATING_ONLINE_EVA)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("virOrderId", virOrderId)
				.params("goodsId", goodsId)
				.params("chaseRatingsInfo", evaluateinfo)
				.addFileParams("files", imgs)
				.execute(new DialogCallback<LzyResponse<TokenBean>>(mActivity, "提交评价中...") {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						if (HttpUtil.handleResponse(mActivity, response.body())) {
							mView.showEvaStatus(true);
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						HttpUtil.handleError(mActivity, response);
					}
				});


	}
}
