package com.xinyuan.xyshop.mvp.presenter;

import com.xinyuan.xyshop.common.DataTrans;
import com.xinyuan.xyshop.entity.ApiSpecialItem;
import com.xinyuan.xyshop.entity.HomeMultipleItem;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.entity.ItemGoods;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.ApiServer;
import com.xinyuan.xyshop.mvp.contract.HomeContract;
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


	public HomePresenterImpl(HomeContract.HomeView view) {
		this.view = view;
		view.setPresenter(this);

	}

	@Override
	public void initData() {
		Subscription subscription = ApiServer.getApiSpecialList("aaa", "bbb")
				.doOnSubscribe(new Action0() {
					@Override
					public void call() {
						view.showState(0);

					}
				})
				.map(new Func1<LzyResponse<List<ApiSpecialItem>>, List<ApiSpecialItem>>() {

					@Override
					public List<ApiSpecialItem> call(LzyResponse<List<ApiSpecialItem>> listLzyResponse) {

						return listLzyResponse.itemList;
					}
				})
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<List<ApiSpecialItem>>() {
					@Override
					public void call(List<ApiSpecialItem> apiSpecialItemList) {
						XLog.v("首页请求成功");
						dataClean(apiSpecialItemList);

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


	private void dataClean(List<ApiSpecialItem> lists) {
		HOMEMultipleItemlist = new ArrayList<>();
		ADList = new ArrayList<>();
		NOTICEList = new ArrayList<>();
		TAB_TitleList = new ArrayList<>();
		TABList = new ArrayList<>();
		CATRGORYList = new ArrayList<>();


		for (ApiSpecialItem apiSpecialItem : lists) {
			if (apiSpecialItem.getItemType().equals("banner")) {
				if (apiSpecialItem.getItemType() != null && !apiSpecialItem.getItemType().equals("[]") && !apiSpecialItem.getItemType().equals("")) {
					banner = DataTrans.getItemDataList(apiSpecialItem.getItemData());
				}
			} else if (apiSpecialItem.getItemType().equals("homemenu")) {
				menu = DataTrans.getItemDataList(apiSpecialItem.getItemData());
			} else if (apiSpecialItem.getItemType().equals("ad")) {
				ADList.add(apiSpecialItem);
				HOMEMultipleItemlist.add(new HomeMultipleItem(HomeMultipleItem.AD, HomeMultipleItem.AD_SPAN_SIZE));
			} else if (apiSpecialItem.getItemType().equals("tab_title")) {
				TAB_TitleList.add(apiSpecialItem);
				HOMEMultipleItemlist.add(new HomeMultipleItem(HomeMultipleItem.TAB_TITLE, HomeMultipleItem.TAB_TITLE_SPAN_SIZE));
			} else if (apiSpecialItem.getItemType().equals("tab")) {
				TABList.add(apiSpecialItem);
				List<ItemData> list = DataTrans.getItemDataList(apiSpecialItem.getItemData());
				HOMEMultipleItemlist.add(new HomeMultipleItem(HomeMultipleItem.TAB, HomeMultipleItem.TAB_SPAN_SIZE));
			} else if (apiSpecialItem.getItemType().equals("category")) {
				CATRGORYList.add(apiSpecialItem);
				HOMEMultipleItemlist.add(new HomeMultipleItem(HomeMultipleItem.CATEGORY, HomeMultipleItem.CATEGORY_SPAN_SIZE));
			} else if (apiSpecialItem.getItemType().equals("goodslist")) {
				goodsList = DataTrans.getItemGoodsList(apiSpecialItem.getItemData());
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

		banner.clear();
		menu.clear();
		noticeList.clear();
		goodsList.clear();

	}

}
