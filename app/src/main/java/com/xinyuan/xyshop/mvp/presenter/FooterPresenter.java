package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.FooterModel;
import com.xinyuan.xyshop.mvp.contract.FooterView;

/**
 * Created by fx on 2017/9/4.
 */

public class FooterPresenter extends BasePresenter<FooterView> {
	private Activity activity;

	public FooterPresenter(Activity activity, FooterView view) {
		super(view);
		this.activity = activity;
	}

	/**
	 * 获取足迹（历史浏览记录）
	 *
	 * @param ship
	 * @param limit
	 */
	public void getFooterGood(int ship, int limit) {
		OkGo.<LzyResponse<FooterModel>>post(Urls.URL_USER_FOOT)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("id", MyShopApplication.userId)
				.params("ship", ship)
				.params("limit", limit)
				.execute(new JsonCallback<LzyResponse<FooterModel>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<FooterModel>> response) {
						if (HttpUtil.handleResponse(activity, response.body())) {
							mView.showList(response.body().datas);
						}

					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<FooterModel>> response) {
						HttpUtil.handleError(activity, response);
					}
				});

	}

}
