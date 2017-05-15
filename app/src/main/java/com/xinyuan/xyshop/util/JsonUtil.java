package com.xinyuan.xyshop.util;

import com.google.gson.Gson;
import com.xinyuan.xyshop.bean.LzyResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by fx on 2017/5/4 0004.
 */

public class JsonUtil {

	public static final Gson gson = new Gson();

	public static <T> T toBean(String json, String urg1, String urg2, Type type) {
		try {
			return gson.fromJson(new JSONObject(new JSONObject(json).getString(urg1)).getString(urg2), type);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T toBean(String json, String urg1, Type type) {
		try {
			return gson.fromJson(new JSONObject(json).optString(urg1), type);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T toBean(String json, Type type) {
		return gson.fromJson(json, type);
	}

	public static Integer toInteger(String json, String urg1) {
		try {
			return Integer.valueOf(new JSONObject(json).optString(urg1));
		} catch (JSONException e) {
			e.printStackTrace();
			return Integer.valueOf(0);
		}
	}

	public static String toString(String json, String urg1) {
		try {
			return new JSONObject(json).optString(urg1);
		} catch (JSONException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String toString(String json, String urg1, String urg2) {
		try {
			return new JSONObject(new JSONObject(json).optString(urg1)).optString(urg2);
		} catch (JSONException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String getError(String json) {
		String error = null;
		try {
			error = new JSONObject(json).optString("error");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return error;
	}

	public static LzyResponse getBaseData(String json) {
		try {
			JSONObject object = new JSONObject(json);
			LzyResponse baseData = new LzyResponse();
			baseData.setCode(object.optInt("code"));
			baseData.setDatas(object.optString("datas"));
			return baseData;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getSuccess(String json) {
		String success = null;
		try {
			success = new JSONObject(json).optString("success");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return success;
	}
}
