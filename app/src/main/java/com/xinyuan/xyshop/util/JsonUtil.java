package com.xinyuan.xyshop.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by fx on 2017/5/4 0004.
 */

public class JsonUtil {

	public static final Gson gson = new Gson();


	public static <T> T toBean(String json, Type type) {
		return gson.fromJson(json, type);
	}
}
