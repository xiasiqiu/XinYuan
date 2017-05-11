package com.xinyuan.xyshop.common;

import com.google.gson.reflect.TypeToken;
import com.xinyuan.xyshop.entity.ApiSpecialItem;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.entity.ItemGoods;
import com.xinyuan.xyshop.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fx on 2017/5/9 0009.
 */

public class DataTrans {
	public static List<ItemData> getItemData(List<ApiSpecialItem> list) {
		List<String> jsons = new ArrayList<>();
		for (ApiSpecialItem item : list) {
			jsons.add(item.getItemData());
		}
		List<List<ItemData>> datas = new ArrayList<>();
		for (String json : jsons) {
			datas.add(getItemDataList(json));
		}
		List<ItemData> list1 = new ArrayList<>();
		for (int i = 0; i < datas.size(); i++) {
			for (int j = 0; j < datas.get(i).size(); j++) {
				list1.add(datas.get(i).get(j));
			}
		}
		return list1;
	}


	public static List<ItemData> getItemData(ApiSpecialItem item) {

		return getItemDataList(item.getItemData());


	}


	public static ItemData getItemData(String json) {
		return JsonUtil.toBean(json, new TypeToken<ItemData>() {
		}.getType());

	}

	public static List<ItemData> getItemDataList(String json) {
		return (List) JsonUtil.toBean(json, new TypeToken<List<ItemData>>() {
		}.getType());
	}


	public static List<ItemGoods> getItemGoodsList(String json) {
		return (List) JsonUtil.toBean(json, new TypeToken<List<ItemGoods>>() {
		}.getType());
	}

}
