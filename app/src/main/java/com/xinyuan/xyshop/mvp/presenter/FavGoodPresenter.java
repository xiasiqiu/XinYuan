package com.xinyuan.xyshop.mvp.presenter;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.FavGoodModel;
import com.xinyuan.xyshop.mvp.contract.FavGoodView;
import com.youth.xframe.utils.log.XLog;

import java.util.List;

/**
 * Created by fx on 2017/7/31.
 */

public class FavGoodPresenter extends BasePresenter<FavGoodView> {
	private Context mContext;

	public FavGoodPresenter(Context context, FavGoodView view) {
		super(view);
		this.mContext = context;
	}

	/**
	 * 获取收藏商品数据
	 *
	 * @param ship
	 * @param limit
	 */
	public void getFavGoods(int ship, int limit) {
		OkGo.<LzyResponse<FavGoodModel>>post(Urls.URL_USER_FAV_GOODS)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("id", MyShopApplication.userId)
				.params("ship", ship)
				.params("limit", limit)
				.execute(new JsonCallback<LzyResponse<FavGoodModel>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<FavGoodModel>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							mView.showList(response.body().datas);
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<FavGoodModel>> response) {
						HttpUtil.handleError(mContext, response);
					}
				});
	}

	/**
	 * 取消收藏商品
	 *
	 * @param idList
	 */
	public void deteleFavGood(List<String> idList) {
		String str = "";
		for (String s : idList) {
			str += s.trim() + ",";
		}
		str.substring(0, str.length() - 1);
		str = "[" + str + "]";  //服务端格式[112,123]

		OkGo.<LzyResponse<FavGoodModel>>post(Urls.URL_USER_FAV_GOODS_DETELE)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("id", MyShopApplication.userId)
				.params("favorteId", str)
				.execute(new JsonCallback<LzyResponse<FavGoodModel>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<FavGoodModel>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							mView.detelteRes(true);
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<FavGoodModel>> response) {
						HttpUtil.handleError(mContext, response);
					}
				});
	}
}
