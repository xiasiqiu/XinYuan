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
import com.xinyuan.xyshop.ui.mine.login.LoginActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.youth.xframe.widget.XToast;

/**
 * Created by fx on 2017/8/24.
 */

public class GoodDetailPresenter extends BasePresenter<GoodDetailView> {
	private Activity activity;

	public GoodDetailPresenter(Activity activity, GoodDetailView view) {
		super(view);
		this.activity = activity;
	}

	/**
	 * 收藏商品
	 *
	 * @param id
	 * @param type
	 */
	public void FavGood(String id, int type) {
		if (LoginContext.isLogin) {
			OkGo.<LzyResponse<TokenBean>>post(Urls.URL_USER_ADD_FAV)
					.tag(activity)
					.headers("token", MyShopApplication.Token)
					.params("userId", MyShopApplication.userId)
					.params("goodsId", id)
					.params("type", type)
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
	 * 取消收藏商品
	 *
	 * @param id
	 */
	public void cancelFavGood(String id) {
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_USER_FAV_CANCEL)
				.tag(activity)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("goodsId", id)
				.params("type", 0)
				.execute(new DialogCallback<LzyResponse<TokenBean>>(activity, "取消收藏...") {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						if (response.body().getCode() == Constants.HTTP_200) {
							mView.setFavView(1);

						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						HttpUtil.handleError(activity, response);
					}
				});


	}


}
