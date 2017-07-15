package com.xinyuan.xyshop.mvp.presenter;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xinyuan.xyshop.bean.LzyResponse;
import com.xinyuan.xyshop.entity.HomeMultipleItem;
import com.xinyuan.xyshop.entity.KeyWord;
import com.xinyuan.xyshop.http.ApiServer;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.HomeModel;
import com.xinyuan.xyshop.mvp.contract.HomeContract;
import com.xinyuan.xyshop.util.JsonUtil;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
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

	private static List<HomeMultipleItem> HomeMultipleList;


	private static HomeModel.GoodModule goodModule;
	private static List<HomeModel.HomeModule> moduleList;

	private static List<HomeModel.HomeModule.HomeModuleData> bannerList;
	private static List<HomeModel.HomeModule.HomeModuleData> menuList;
	private static List<HomeModel.HomeModule.HomeModuleData> noticeList;
	private static List<HomeModel.HomeModule.HomeModuleData> adList;
	private static List<HomeModel.HomeModule.HomeModuleData> tabTitleList;
	private static List<HomeModel.HomeModule.HomeModuleData> tabList;
	private static List<HomeModel.HomeModule.HomeModuleData> tab2List;
	private static List<HomeModel.HomeModule.HomeModuleData> categoryList;


	private static List<HomeModel.HomeModule.HomeModuleData> ModuleDataList;

	public HomePresenterImpl(HomeContract.HomeView view) {
		this.view = view;
		view.setPresenter(this);

	}

	@Override
	public void initData() {
		view.showState(0);
		Subscription subscription = ApiServer.getHomeIndex(Urls.API_INDEX)
				.doOnSubscribe(new Action0() {
					@Override
					public void call() {
						view.showState(0);
					}
				})
				.map(new Func1<LzyResponse<HomeModel>, HomeModel>() {

					@Override
					public HomeModel call(LzyResponse<HomeModel> homeModelLzyResponse) {
						return homeModelLzyResponse.getDatas();
					}
				})
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<HomeModel>() {
					@Override
					public void call(HomeModel homeModel) {
						moduleList = new ArrayList<>();
						moduleList = homeModel.getModuleList();
						goodModule = homeModel.getGoodModule();
						showModule(moduleList);

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


	private void showModule(List<HomeModel.HomeModule> list) {

		HomeMultipleList = new ArrayList<>();
		bannerList = new ArrayList<>();
		menuList = new ArrayList<>();
		noticeList = new ArrayList<>();
		adList = new ArrayList<>();
		tabTitleList = new ArrayList<>();
		tabList = new ArrayList<>();
		tab2List = new ArrayList<>();
		categoryList = new ArrayList<>();


		for (HomeModel.HomeModule homeModule : list) {
			if (homeModule.getItemType() == null && homeModule.getItemType().equals("[]") && homeModule.getItemType().equals("")) {
				return;
			}
			if (homeModule.getItemType().equals("banner")) {
				bannerList = homeModule.getDataList();
			} else if (homeModule.getItemType().equals("homemenu")) {
				menuList = homeModule.getDataList();
			} else if (homeModule.getItemType().equals("notice")) {
				noticeList.addAll(homeModule.getDataList());
			} else if (homeModule.getItemType().equals("ad")) {
				adList.addAll(homeModule.getDataList());
				HomeMultipleList.add(new HomeMultipleItem(HomeMultipleItem.AD, HomeMultipleItem.AD_SPAN_SIZE));
			} else if (homeModule.getItemType().equals("tab_title")) {
				tabTitleList.addAll(homeModule.getDataList());
				HomeMultipleList.add(new HomeMultipleItem(HomeMultipleItem.TAB_TITLE, HomeMultipleItem.TAB_TITLE_SPAN_SIZE));
			} else if (homeModule.getItemType().equals("tab")) {
				tabList.addAll(homeModule.getDataList());
				HomeMultipleList.add(new HomeMultipleItem(HomeMultipleItem.TAB, HomeMultipleItem.TAB_SPAN_SIZE));
			} else if (homeModule.getItemType().equals("tab2")) {
				tab2List.addAll(homeModule.getDataList());
				HomeMultipleList.add(new HomeMultipleItem(HomeMultipleItem.TAB2, HomeMultipleItem.TAB2_SPAN_SIZE));
			} else if (homeModule.getItemType().equals("category")) {
				categoryList.addAll(homeModule.getDataList());
				HomeMultipleList.add(new HomeMultipleItem(HomeMultipleItem.CATEGORY, HomeMultipleItem.CATEGORY_SPAN_SIZE));
			}

		}

		showView();


	}


	public void showView() {

		view.addHead(HomeMultipleList);
		view.showBanner(bannerList);
		view.showMenu(menuList);
		view.showNotice(noticeList);
		view.showGoods(goodModule);
		view.showList();
		getkeyWord();

	}

	@Override
	public void getkeyWord() {
		OkGo.get(Urls.URL_SEARCH_HOT)
				.execute(new StringCallback() {
					@Override
					public void onSuccess(String s, Call call, Response response) {
						KeyWord keyWords = JsonUtil.toBean(s, KeyWord.class);
						KeyWord.Key key = keyWords.getKey();
						view.setKeyWord(key.getKeywordValue(), key.getKeywordName());

					}
				});

	}

	public static List<HomeMultipleItem> getHomeMultipleList() {
		return HomeMultipleList;
	}

	public static List<HomeModel.HomeModule> getModuleList() {
		return moduleList;
	}
}