package com.xinyuan.xyshop.callback;

import com.google.gson.stream.JsonReader;
import com.lzy.okgo.convert.Converter;
import com.xinyuan.xyshop.bean.CatrgoryResponse;
import com.xinyuan.xyshop.bean.LzyResponse;
import com.xinyuan.xyshop.bean.SimpleResponse;
import com.xinyuan.xyshop.util.Convert;
import com.youth.xframe.utils.log.XLog;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

public class JsonConvert<T> implements Converter<T> {

	/**
	 * 该方法是子线程处理，不能做ui相关的工作
	 * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
	 * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
	 * <pre>
	 * OkGo.get(Urls.URL_METHOD)//
	 *     .tag(this)//
	 *     .execute(new DialogCallback<LzyResponse<ServerModel>>(this) {
	 *          @Override
	 *          public void onSuccess(LzyResponse<ServerModel> responseData, Call call, Response response) {
	 *              handleResponse(responseData.data, call, response);
	 *          }
	 *     });
	 * </pre>
	 */
	@Override
	public T convertSuccess(Response response) throws Exception {

		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		Type type = params[0];
		if (!(type instanceof ParameterizedType))
			throw new IllegalStateException("没有填写泛型参数");
		Type rawType = ((ParameterizedType) type).getRawType();
		Type typeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
		JsonReader jsonReader = new JsonReader(response.body().charStream());
		if (typeArgument == Void.class) {
			SimpleResponse simpleResponse = Convert.fromJson(jsonReader, SimpleResponse.class);
			response.close();
			return (T) simpleResponse.toLzyResponse();
		} else if (rawType == LzyResponse.class) {
			LzyResponse lzyResponse = Convert.fromJson(jsonReader, type);
			response.close();
			int code = lzyResponse.code;
			return codeResponse(code, (T) lzyResponse);
		} else if (rawType == CatrgoryResponse.class) {

			//有数据类型，表示有data
			CatrgoryResponse catrgoryResponse = Convert.fromJson(jsonReader, type);
			response.close();
			int code = catrgoryResponse.code;
			XLog.v("分類來了"+catrgoryResponse.toString());
			return codeResponse(code, (T) catrgoryResponse);

		} else {
			response.close();
			throw new IllegalStateException("基类错误无法解析!");
		}

	}

	private T codeResponse(int code,T bean) {
		if (code == 200) {
			//noinspection unchecked
			return (T) bean;
		} else if (code == 104) {
			//比如：用户授权信息无效，在此实现相应的逻辑，弹出对话或者跳转到其他页面等,该抛出错误，会在onError中回调。
			throw new IllegalStateException("用户授权信息无效");
		} else if (code == 105) {
			//比如：用户收取信息已过期，在此实现相应的逻辑，弹出对话或者跳转到其他页面等,该抛出错误，会在onError中回调。
			throw new IllegalStateException("用户收取信息已过期");
		} else if (code == 106) {
			//比如：用户账户被禁用，在此实现相应的逻辑，弹出对话或者跳转到其他页面等,该抛出错误，会在onError中回调。
			throw new IllegalStateException("用户账户被禁用");
		} else if (code == 300) {
			//比如：其他乱七八糟的等，在此实现相应的逻辑，弹出对话或者跳转到其他页面等,该抛出错误，会在onError中回调。
			throw new IllegalStateException("其他乱七八糟的等");
		} else {
			throw new IllegalStateException("错误代码：" + code);
		}
	}
}