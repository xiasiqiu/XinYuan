package com.xinyuan.xyshop.mvp.presenter;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.common.DataTrans;
import com.xinyuan.xyshop.entity.ApiResponse;
import com.xinyuan.xyshop.entity.ApiSpecialItem;
import com.xinyuan.xyshop.entity.HomeMultipleItem;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.entity.ItemGoods;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.ApiServer;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.mvp.contract.HomeContract;
import com.xinyuan.xyshop.util.JsonUtil;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by fx on 2017/5/9 0009.
 */

public class HomePresenterImpl implements HomeContract.HomePresenter {

	private HomeContract.HomeView view;


	private static List<HomeMultipleItem> HOMEMultipleItemlist;
	private static List<ApiSpecialItem> ADList;
	private static List<ApiSpecialItem> NOTICEList;
	private static List<ApiSpecialItem> TAB_TitleList;
	private static List<ApiSpecialItem> TABList;
	private static List<ApiSpecialItem> CATRGORYList;

	public static List<ItemData> banner;
	public static List<ItemData> menu;
	public static List<ItemData> noticeList;
	public static List<ItemGoods> goodsList;

	public static List<ApiSpecialItem> dataList;

	public HomePresenterImpl(HomeContract.HomeView view) {
		this.view = view;
		view.setPresenter(this);

	}

	@Override
	public void initData() {
		Subscription subscription = ApiServer.getApiData(Urls.API_INDEX)
				.doOnSubscribe(new Action0() {
					@Override
					public void call() {
						view.showState(0);
						XLog.v("首页请求中");
					}
				})
				.map(new Func1<ApiResponse, String>() {


					@Override
					public String call(ApiResponse apiResponse) {
						XLog.v("首页请求操作");
						return apiResponse.datas;
					}
				})
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<String>() {
					@Override
					public void call(String apidatas) {
						XLog.v("首页请求成功"+apidatas.toString());
						dataClean(apidatas);

					}


				}, new Action1<Throwable>() {
					@Override
					public void call(Throwable throwable) {
						throwable.printStackTrace();
						XLog.v("首页请求失败");
						view.showState(2);
					}
				});
	}


	private void dataClean(String datas) {


		List<ApiSpecialItem> lists = JsonUtil.toBean(datas, "itemList", new TypeToken<List<ApiSpecialItem>>() {

		}.getType());


		dataList = new ArrayList<>();
		HOMEMultipleItemlist = new ArrayList<>();
		ADList = new ArrayList<>();
		NOTICEList = new ArrayList<>();
		TAB_TitleList = new ArrayList<>();
		TABList = new ArrayList<>();
		CATRGORYList = new ArrayList<>();
		XLog.list(lists);

		for (ApiSpecialItem apiSpecialItem : lists) {
			if (apiSpecialItem.getItemType().equals("banner")) {
				if (apiSpecialItem.getItemType() != null && !apiSpecialItem.getItemType().equals("[]") && !apiSpecialItem.getItemType().equals("")) {
					banner = DataTrans.getItemDataList(apiSpecialItem.getItemData());
				}
			} else if (apiSpecialItem.getItemType().equals("homemenu")) {
				menu = DataTrans.getItemDataList(apiSpecialItem.getItemData());
			} else if (apiSpecialItem.getItemType().equals("ad")) {
				ADList.add(apiSpecialItem);
				dataList.add(apiSpecialItem);
				HOMEMultipleItemlist.add(new HomeMultipleItem(HomeMultipleItem.AD, HomeMultipleItem.AD_SPAN_SIZE));
			} else if (apiSpecialItem.getItemType().equals("tab_title")) {
				TAB_TitleList.add(apiSpecialItem);
				HOMEMultipleItemlist.add(new HomeMultipleItem(HomeMultipleItem.TAB_TITLE, HomeMultipleItem.TAB_TITLE_SPAN_SIZE));
				dataList.add(apiSpecialItem);
			} else if (apiSpecialItem.getItemType().equals("tab")) {
				TABList.add(apiSpecialItem);
				List<ItemData> list = DataTrans.getItemDataList(apiSpecialItem.getItemData());
				dataList.add(apiSpecialItem);
				HOMEMultipleItemlist.add(new HomeMultipleItem(HomeMultipleItem.TAB, HomeMultipleItem.TAB_SPAN_SIZE));
			} else if (apiSpecialItem.getItemType().equals("category")) {
				CATRGORYList.add(apiSpecialItem);
				dataList.add(apiSpecialItem);
				HOMEMultipleItemlist.add(new HomeMultipleItem(HomeMultipleItem.CATEGORY, HomeMultipleItem.CATEGORY_SPAN_SIZE));
			} else if (apiSpecialItem.getItemType().equals("goodslist")) {
				goodsList = DataTrans.getItemGoodsList(apiSpecialItem.getItemData());
				dataList.add(apiSpecialItem);
				for (ItemGoods goods : goodsList) {
					HOMEMultipleItemlist.add(new HomeMultipleItem(HomeMultipleItem.GOODS, HomeMultipleItem.GOODS_SPAN_SIZE));
				}
			} else if (apiSpecialItem.getItemType().equals("notice")) {
				noticeList = DataTrans.getItemDataList(apiSpecialItem.getItemData());

			}
		}


		view.addHead(getHomeMultipleItemlist());
		view.showBanner(banner);
		view.showMenu(menu);
		view.showNotice(noticeList);
		view.showList();

	}

	public static List<ItemGoods> moreGoodsList() {
		List<ItemGoods> list = new ArrayList<>();

		for (ItemGoods itemGoods : goodsList) {
			list.add(itemGoods);
		}
		for (ItemGoods itemGoods : goodsList) {
			list.add(itemGoods);
		}
		return list;

	}


	public static List<HomeMultipleItem> getHomeMultipleItemlist() {
		return HOMEMultipleItemlist;
	}


	public static List<ItemData> getItemADList() {
		return DataTrans.getItemData(ADList);
	}

	public static List<ApiSpecialItem> getApiDataList() {

		return dataList;
	}

	public static List<ItemData> getItemTabTitleList() {
		return DataTrans.getItemData(TAB_TitleList);

	}


	public static List<List<ItemData>> getTabList() {
		List<List<ItemData>> list = new ArrayList<List<ItemData>>();
		for (ApiSpecialItem apiSpecialItem : TABList) {
			list.add(DataTrans.getItemDataList(apiSpecialItem.getItemData()));
		}
		return list;
	}

	public static List<ItemGoods> getItemGoodsList() {
		return goodsList;
	}


	public static List<ItemData> getCategoryList() {
		return DataTrans.getItemData(CATRGORYList);
	}

	public static void clearList() {
		XLog.v("删除首页列表数据");
		banner.clear();
		menu.clear();
		noticeList.clear();
		goodsList.clear();

	}


}
