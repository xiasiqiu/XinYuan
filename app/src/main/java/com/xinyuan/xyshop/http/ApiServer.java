package com.xinyuan.xyshop.http;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.solver.Cache;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okrx.RxAdapter;
import com.xinyuan.xyshop.bean.CatrgoryResponse;
import com.xinyuan.xyshop.bean.LzyResponse;
import com.xinyuan.xyshop.callback.DialogCallback;
import com.xinyuan.xyshop.callback.JsonConvert;

import com.xinyuan.xyshop.entity.ApiSpecialItem;
import com.xinyuan.xyshop.entity.GoodCategory;
import com.xinyuan.xyshop.model.CategoryModel;
import com.xinyuan.xyshop.model.GoodDetailModel;
import com.xinyuan.xyshop.model.GoodsDetailModel;
import com.xinyuan.xyshop.model.HomeModel;
import com.xinyuan.xyshop.model.OrderModel;
import com.xinyuan.xyshop.model.StoreHomeModel;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import rx.Observable;

/**
 * Created by fx on 2017/5/9 0009.
 */

public class ApiServer {

	/**
	 * 首页数据
	 *
	 * @param url
	 * @return
	 */
	public static Observable<LzyResponse<HomeModel>> getHomeIndex(String url) {
		return OkGo.get(url)
				.getCall(new JsonConvert<LzyResponse<HomeModel>>() {
				}, RxAdapter.<LzyResponse<HomeModel>>create());

	}

	/**
	 * 分类数据
	 *
	 * @param url
	 * @return
	 */
	public static Observable<LzyResponse<CategoryModel>> getCategory(String url) {
		return OkGo.get(url)
				.getCall(new JsonConvert<LzyResponse<CategoryModel>>() {
				}, RxAdapter.<LzyResponse<CategoryModel>>create());


	}

	/**
	 * 商品详情数据
	 *
	 * @param url
	 * @return
	 */
	public static Observable<LzyResponse<GoodDetailModel>> getGoodsDetail(String url) {
		return OkGo.get(url)
				//.params("goodId", goodId)
				.getCall(new JsonConvert<LzyResponse<GoodDetailModel>>() {
				}, RxAdapter.<LzyResponse<GoodDetailModel>>create());


	}

	public static Observable<LzyResponse<StoreHomeModel>> getStoreHome(String url) {
		return OkGo.get(url)
				//.params("goodId", goodId)
				.getCall(new JsonConvert<LzyResponse<StoreHomeModel>>() {
				}, RxAdapter.<LzyResponse<StoreHomeModel>>create());


	}

	/**
	 * 订单数据
	 *
	 * @param url
	 * @return
	 */
	public static Observable<LzyResponse<OrderModel>> getOrderList(String url) {
		return OkGo.get(url)
				.getCall(new JsonConvert<LzyResponse<OrderModel>>() {
				}, RxAdapter.<LzyResponse<OrderModel>>create());
	}


}