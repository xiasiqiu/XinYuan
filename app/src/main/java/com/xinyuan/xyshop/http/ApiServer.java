package com.xinyuan.xyshop.http;

import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpMethod;
import com.lzy.okgo.model.HttpParams;

import java.lang.reflect.Type;

import io.reactivex.Observable;


/**
 * Created by fx on 2017/5/9 0009.
 */

public class ApiServer {

	/**
	 * 获取数据
	 *
	 * @param url
	 * @return
	 */
	public static <T> Observable<T> getData(Type type, String url) {
		return RxUtils.request(HttpMethod.GET, url, type);
	}

	public static <T> Observable<T> getData(Type type, String url, HttpParams param, HttpHeaders header) {
		return RxUtils.request(HttpMethod.POST, url, type, param, header);
	}

	public static<T>Observable<T>searchGood(Type type,String url,HttpParams keyword,HttpParams sorts,HttpParams currentPage,HttpParams pageSize,HttpParams price,HttpParams brandId,HttpParams brandName,HttpParams xyself,HttpParams property){
		return RxUtils.request(HttpMethod.POST,url,type,keyword,sorts,currentPage,pageSize,price,brandId,brandName,xyself,property);
	}


	/**
	 * 商品搜索数据
	 *
	 * @param url
	 * @return
	 */
	public static <T> Observable<T> getSearchList(Type type, String url, String header, int brandId, String priceLimit,
	                                              int storeType, String sort, String keyword, int page, int limit, String spec) {
		HttpHeaders headers = new HttpHeaders();
		headers.put("token", header);
		HttpParams params = new HttpParams();
		params.put("brandId", brandId);
		params.put("priceLimit", priceLimit);
		params.put("storeType", storeType);
		params.put("sort", "priec_desc");
		params.put("keyword", keyword);
		params.put("page", page);
		params.put("limit", limit);
		params.put("spec", spec);
		return RxUtils.request(HttpMethod.POST, url, type, params, headers);
	}

	/**
	 * 获取数据
	 *
	 * @param url   地址
	 * @param param 参数名
	 * @param id    参数ID
	 * @return
	 */
	public static <T> Observable<T> getData(Type type, String url, String header, String param, long id) {
		HttpHeaders headers = new HttpHeaders();
		headers.put("aaa", header);
		HttpParams params = new HttpParams();
		params.put(param, id);
		return RxUtils.request(HttpMethod.GET, url, type, params, headers);
	}


}

