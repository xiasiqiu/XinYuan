package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.callback.DialogCallback;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.state.LoginContext;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.mvp.contract.GoodDetailView;
import com.xinyuan.xyshop.mvp.contract.StoreView;
import com.xinyuan.xyshop.ui.mine.login.LoginActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.youth.xframe.widget.XToast;

/**
 * Created by fx on 2017/8/31.
 */

public class StorePresenter extends BasePresenter<StoreView> {
	private Activity activity;

	public StorePresenter(Activity activity, StoreView view) {
		super(view);
		this.activity = activity;
	}

	/**
	 * 收藏店铺
	 *
	 * @param id
	 */
	public void FavStore(Long id) {
		if (LoginContext.isLogin) {
			OkGo.<LzyResponse<TokenBean>>post(Urls.URL_USER_ADD_FAV)
					.tag(activity)
					.headers("token", MyShopApplication.Token)
					.params("userId", MyShopApplication.userId)
					.params("storeId", id)
					.params("type", 1)
					.execute(new DialogCallback<LzyResponse<TokenBean>>(activity, "收藏中...") {
						@Override
						public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
							if (response.body().getCode() == Constants.HTTP_200) {
								mView.setFavView(2);
							} else if (response.body().getCode() == Constants.HTTP_117) {
								mView.setFavView(3);

							}
						}

						@Override
						public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
							XToast.error("请求错误");
						}
					});
		} else {
			CommUtil.gotoActivity(activity, LoginActivity.class, false, null);
		}
	}

	/**
	 * 取消收藏
	 *
	 * @param id
	 */
	public void cancelFavGood(long id) {
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_USER_FAV_CANCEL)
				.tag(activity)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("storeId", id)
				.params("type", 1)
				.execute(new DialogCallback<LzyResponse<TokenBean>>(activity, "取消收藏...") {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {

						switch (response.body().getCode()) {
							case 200:
								mView.setFavView(1);
								break;
							case 117:
								mView.setFavView(3);
								break;
						}

					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						HttpUtil.handleError(activity, response);
					}
				});


	}
}
