package com.xinyuan.xyshop.http;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpMethod;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.base.Request;
import com.lzy.okrx2.adapter.ObservableBody;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.callback.JsonConvert;

import java.lang.reflect.Type;


import io.reactivex.Observable;

/**
 * Created by fx on 2017/7/19.
 */

public class RxUtils {

	public static <T> Observable<T> request(HttpMethod method, String url, Type type) {
		return request(method, url, type, null);
	}

	public static <T> Observable<T> request(HttpMethod method, String url, Type type, HttpParams params) {
		return request(method, url, type, params, null);
	}

	public static <T> Observable<T> request(HttpMethod method, String url, Type type, HttpParams params, HttpHeaders headers) {
		return request(method, url, type, null, params, headers);
	}

	public static <T> Observable<T> request(HttpMethod method, String url, Class<T> clazz) {
		return request(method, url, clazz, null);
	}

	public static <T> Observable<T> request(HttpMethod method, String url, Class<T> clazz, HttpParams params) {
		return request(method, url, clazz, params, null);
	}

	public static <T> Observable<T> request(HttpMethod method, String url, Class<T> clazz, HttpParams params, HttpHeaders headers) {
		return request(method, url, null, clazz, params, headers);
	}


	public static <T> Observable<T> request(HttpMethod method, String url, Type type, Class<T> clazz, HttpParams params, HttpHeaders headers) {
		Request<T, ? extends Request> request;
		if (method == HttpMethod.GET) request = OkGo.get(url);
		else if (method == HttpMethod.POST) request = OkGo.post(url);
		else if (method == HttpMethod.PUT) request = OkGo.put(url);
		else if (method == HttpMethod.DELETE) request = OkGo.delete(url);
		else if (method == HttpMethod.HEAD) request = OkGo.head(url);
		else if (method == HttpMethod.PATCH) request = OkGo.patch(url);
		else if (method == HttpMethod.OPTIONS) request = OkGo.options(url);
		else if (method == HttpMethod.TRACE) request = OkGo.trace(url);
		else request = OkGo.get(url);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.put("token", MyShopApplication.Token);
		request.headers(httpHeaders);
		request.params(params);
		if (type != null) {
			request.converter(new JsonConvert<T>(type));
		} else if (clazz != null) {
			request.converter(new JsonConvert<T>(clazz));
		} else {
			request.converter(new JsonConvert<T>());
		}
		return request.adapt(new ObservableBody<T>());
	}

	public static <T> Observable<T> request(HttpMethod post, String url, Type type, HttpParams keyword, HttpParams sorts, HttpParams currentPage, HttpParams pageSize, HttpParams price, HttpParams brandId, HttpParams brandName, HttpParams xyself, HttpParams property) {
		Request<T, ? extends Request> request;
		request = OkGo.post(url);
		request.params(keyword);
		request.params(sorts);
		request.params(currentPage);
		request.params(pageSize);
		request.params(price);
		request.params(brandId);
		request.params(brandName);
		request.params(xyself);
		request.params(property);
		if (type != null) {
			request.converter(new JsonConvert<T>(type));
		} else {
			request.converter(new JsonConvert<T>());
		}
		return request.adapt(new ObservableBody<T>());
	}
}
