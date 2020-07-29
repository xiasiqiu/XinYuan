package com.xinyuan.xyshop.callback;

import com.google.gson.stream.JsonReader;
import com.lzy.okgo.convert.Converter;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.entity.SimpleResponse;
import com.xinyuan.xyshop.util.Convert;
import com.youth.xframe.utils.log.XLog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

public class JsonConvert<T> implements Converter<T> {

	private Type type;
	private Class<T> clazz;

	public JsonConvert() {
	}

	public JsonConvert(Type type) {
		this.type = type;
	}

	public JsonConvert(Class<T> clazz) {
		this.clazz = clazz;
	}


	@Override
	public T convertResponse(Response response) throws Exception {

		if (type == null) {
			if (clazz == null) {
				// 如果没有通过构造函数传进来，就自动解析父类泛型的真实类型（有局限性，继承后就无法解析到）
				Type genType = getClass().getGenericSuperclass();
				type = ((ParameterizedType) genType).getActualTypeArguments()[0];
			} else {
				return parseClass(response, clazz);
			}
		}

		if (type instanceof ParameterizedType) {
			return parseParameterizedType(response, (ParameterizedType) type);
		} else if (type instanceof Class) {
			return parseClass(response, (Class<?>) type);
		} else {
			return parseType(response, type);
		}
	}

	private T parseClass(Response response, Class<?> rawType) throws Exception {
		if (rawType == null) return null;
		ResponseBody body = response.body();
		if (body == null) return null;
		JsonReader jsonReader = new JsonReader(body.charStream());

		if (rawType == String.class) {
			//noinspection unchecked
			return (T) body.string();
		} else if (rawType == JSONObject.class) {
			//noinspection unchecked
			return (T) new JSONObject(body.string());
		} else if (rawType == JSONArray.class) {
			//noinspection unchecked
			return (T) new JSONArray(body.string());
		} else {
			T t = Convert.fromJson(jsonReader, rawType);
			response.close();
			return t;
		}
	}

	private T parseType(Response response, Type type) throws Exception {
		if (type == null) return null;
		ResponseBody body = response.body();
		if (body == null) return null;
		JsonReader jsonReader = new JsonReader(body.charStream());

		// 泛型格式如下： new JsonCallback<任意JavaBean>(this)
		T t = Convert.fromJson(jsonReader, type);
		response.close();
		return t;
	}

	private T parseParameterizedType(Response response, ParameterizedType type) throws Exception {
		if (type == null) return null;
		ResponseBody body = response.body();
		switch (response.code()) {
			case 404:
				XLog.v("404错误");
				return null;
			case 400:
				XLog.v("400错误");
				return null;
			case 500:
				XLog.v("500错误");
				return null;
			case 505:
				XLog.v("505错误");
				return null;
		}

		if (body == null) return null;
		JsonReader jsonReader = new JsonReader(body.charStream());

		Type rawType = type.getRawType();                     // 泛型的实际类型
		Type typeArgument = type.getActualTypeArguments()[0]; // 泛型的参数

		if (rawType != LzyResponse.class) {
			// 泛型格式如下： new JsonCallback<外层BaseBean<内层JavaBean>>(this)
			T t = Convert.fromJson(jsonReader, type);
			response.close();
			return t;
		} else {
			if (typeArgument == Void.class) {
				// 泛型格式如下： new JsonCallback<LzyResponse<Void>>(this)
				SimpleResponse simpleResponse = Convert.fromJson(jsonReader, SimpleResponse.class);
				response.close();
				return (T) simpleResponse.toLzyResponse();
			} else {
				// 泛型格式如下： new JsonCallback<LzyResponse<内层JavaBean>>(this)
				LzyResponse lzyResponse = Convert.fromJson(jsonReader, type);
				response.close();
				return (T) lzyResponse;
			}
		}
	}
}


