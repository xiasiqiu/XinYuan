package com.xinyuan.xyshop.ui.home;


import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.RxBus;
import com.xinyuan.xyshop.entity.ApiSpecialItem;
import com.xinyuan.xyshop.entity.HomeMultipleItem;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.entity.ItemGoods;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by fx on 2017/5/3 0003.
 */

public class HomeDataLoadHelper {
	public static List<HomeMultipleItem> homeMultipleItemlist;


	private static MyShopApplication application;
	private static List<ApiSpecialItem> dataList;
	public static List<ItemData> banner;
	public static List<ItemData> menu;
	public static List<ItemData> adList;
	public static List<ItemData> tab_title_List;
	public static List<ItemData> tabList;
	public static List<ItemData> categoryList;
	public static List<ItemGoods> goodsList;


	public static List<ApiSpecialItem> ADList;
	public static List<ApiSpecialItem> TAB_TitleList;
	public static List<ApiSpecialItem> TABList;
	public static List<ApiSpecialItem> CATRGORYList;

	private static RxBus rxBus = RxBus.getInstance();
	public List<List<ItemData>> tabLisst;

	public HomeDataLoadHelper(Context context) {
		//application = (MyShopApplication) context.getApplicationContext();
		ADList = new ArrayList<>();
		TAB_TitleList = new ArrayList<>();
		TABList = new ArrayList<>();
		CATRGORYList = new ArrayList<>();
	}


	public static void getHomeData(Context context, final HomeShowViewHelper homeShowViewHelper) {
		OkGo.get(Constants.API_INDEX)
				.tag(context)
				.execute(new JsonCallback<LzyResponse<List<ApiSpecialItem>>>() {
					@Override
					public void onSuccess(LzyResponse<List<ApiSpecialItem>> listLzyResponse, Call call, Response response) {
						if (listLzyResponse != null && !listLzyResponse.equals("") && !listLzyResponse.equals("[]")) {
							dataList = new ArrayList<>();
							dataList = listLzyResponse.getItemList();
							showHomeView(homeShowViewHelper, dataList);
						}
					}


				});
	}


	public static void showHomeView(HomeShowViewHelper helper, List<ApiSpecialItem> apiSpecialItemList) {

		homeMultipleItemlist = new ArrayList<>();
		for (ApiSpecialItem apiSpecialItem : apiSpecialItemList) {
			if (apiSpecialItem.getItemType().equals("banner")) {
				if (apiSpecialItem.getItemType() != null && !apiSpecialItem.getItemType().equals("[]") && !apiSpecialItem.getItemType().equals("")) {
					banner = getItemDataList(apiSpecialItem.getItemData());
				}
			} else if (apiSpecialItem.getItemType().equals("homemenu")) {
				menu = getItemDataList(apiSpecialItem.getItemData());

			} else if (apiSpecialItem.getItemType().equals("ad")) {
				ADList.add(apiSpecialItem);
				homeMultipleItemlist.add(new HomeMultipleItem(HomeMultipleItem.AD, HomeMultipleItem.AD_SPAN_SIZE));
			} else if (apiSpecialItem.getItemType().equals("tab_title")) {
				TAB_TitleList.add(apiSpecialItem);
				homeMultipleItemlist.add(new HomeMultipleItem(HomeMultipleItem.TAB_TITLE, HomeMultipleItem.TAB_TITLE_SPAN_SIZE));
			} else if (apiSpecialItem.getItemType().equals("tab")) {
				TABList.add(apiSpecialItem);
				List<ItemData> list = getItemDataList(apiSpecialItem.getItemData());
                homeMultipleItemlist.add(new HomeMultipleItem(HomeMultipleItem.TAB, HomeMultipleItem.TAB_SPAN_SIZE));
			} else if (apiSpecialItem.getItemType().equals("category")) {
				CATRGORYList.add(apiSpecialItem);
				homeMultipleItemlist.add(new HomeMultipleItem(HomeMultipleItem.CATEGORY, HomeMultipleItem.CATEGORY_SPAN_SIZE));

			} else if (apiSpecialItem.getItemType().equals("goodslist")) {

				goodsList = getItemGoodsList(apiSpecialItem.getItemData());
				for (ItemGoods goods : goodsList) {
					homeMultipleItemlist.add(new HomeMultipleItem(HomeMultipleItem.GOODS, HomeMultipleItem.GOODS_SPAN_SIZE));
				}
			}
		}
		rxBus.post("dataload","数据加载ok");
	}




	public static List<ItemData> getItemData(List<ApiSpecialItem> list) {
       List<String>jsons=new ArrayList<>();
		for(ApiSpecialItem item:list){
          jsons.add(item.getItemData());
        }
        List<List<ItemData>>datas=new ArrayList<>();
        for(String json:jsons){
           datas.add(getItemDataList(json));
        }
        List<ItemData>list1=new ArrayList<>();
        for(int i=0;i<datas.size();i++){
            for(int j=0;j<datas.get(i).size();j++){
                list1.add(datas.get(i).get(j));
            }
        }
        return list1;
	}

    public static List<ItemData> getItemDataList(String json) {
        return (List) JsonUtil.toBean(json, new TypeToken<List<ItemData>>() {
        }.getType());
    }


    public static List<ItemGoods> getItemGoodsList(String json) {
        return (List) JsonUtil.toBean(json, new TypeToken<List<ItemGoods>>() {
        }.getType());
    }
	public static List<ItemData> getItemADList() {
		return getItemData(ADList);
	}


    public static List<ItemData> getItemTabTitleList() {
        return getItemData(TAB_TitleList);

    }


    public static List<List<ItemData>> getTabList() {
        List<List<ItemData>> list = new ArrayList<List<ItemData>>();
        for (ApiSpecialItem apiSpecialItem : TABList) {
            list.add(getItemDataList(apiSpecialItem.getItemData()));
        }
        return list;
    }
	public static List<ItemGoods> getItemGoodsList() {
		return goodsList;
	}





	public static List<ItemData> getCategoryList() {
		return getItemData(CATRGORYList);
	}

	public static List<ItemData> getBanner() {
		return banner;
	}

	public static List<ItemData> getMenu() {
		return menu;
	}


	public static List<HomeMultipleItem> getHomeMultipleItemlist() {
		return homeMultipleItemlist;
	}


	public static ItemData getItemData(String json) {
		return JsonUtil.toBean(json, new TypeToken<ItemData>() {
		}.getType());

	}




}
