package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.SignBean;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.bean.GoodListItemBean;
import com.xinyuan.xyshop.entity.HomeMultipleItem;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.http.ApiServer;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.HomeModel;
import com.xinyuan.xyshop.model.KeyWordModel;
import com.xinyuan.xyshop.model.RecomGoodModel;
import com.xinyuan.xyshop.mvp.contract.HomeView;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by fx on 2017/7/31.
 */

public class HomePresenter extends BasePresenter<HomeView> {

    private List<HomeMultipleItem> HomeMultipleList; //模块布局元素列表
    private List<HomeModel.HomeModule> moduleList;//模块数据列表

    private List<ItemData> bannerList; //banner数据列表
    private List<ItemData> menuList; //菜单数据列表
    private List<ItemData> noticeList; //公告数据列表
    private List<ItemData> adList; //广告数据列表
    private List<ItemData> tabList;//6图列表
    private List<ItemData> tab2List;//3图列表
    private List<ItemData> categoryList;//大图列表
    private Activity mActivity;

    public HomePresenter(Activity activity, HomeView view) {
        super(view);
        this.mActivity = activity;
    }

    /**
     * 获取首页数据
     */
    public void getHomeData() {
        Type type = new TypeToken<LzyResponse<HomeModel>>() {
        }.getType();
        ApiServer.<LzyResponse<HomeModel>>getData(type, Urls.URL_HOME_INDEX)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {

                    }
                })
                .map(new Function<LzyResponse<HomeModel>, HomeModel>() {
                    @Override
                    public HomeModel apply(@NonNull LzyResponse<HomeModel> response) throws Exception {
                        return response.getDatas();


                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mView.bindLife())
                .subscribe(new Observer<HomeModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HomeModel homeModel) {
                        moduleList = homeModel.getModuleList();
                        showModule(moduleList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.showState(2);
                    }

                    @Override
                    public void onComplete() {
                        mView.showState(1);
                    }
                });


    }

    /**
     * 首页布局数据清洗
     *
     * @param list
     */
    private void showModule(List<HomeModel.HomeModule> list) {
        HomeMultipleList = new ArrayList<>();
        bannerList = new ArrayList<>();
        menuList = new ArrayList<>();
        noticeList = new ArrayList<>();
        adList = new ArrayList<>();
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
            } else if (homeModule.getItemType().equals("news")) {
                noticeList.addAll(homeModule.getDataList());
            } else if (homeModule.getItemType().equals("ad")) {
                adList.addAll(homeModule.getDataList());
                HomeMultipleList.add(new HomeMultipleItem(HomeMultipleItem.AD, HomeMultipleItem.AD_SPAN_SIZE));
            } else if (homeModule.getItemType().equals("tab6")) {
                tabList.addAll(homeModule.getDataList());
                HomeMultipleList.add(new HomeMultipleItem(HomeMultipleItem.TAB, HomeMultipleItem.TAB_SPAN_SIZE));
            } else if (homeModule.getItemType().equals("tab3")) {
                tab2List.addAll(homeModule.getDataList());
                HomeMultipleList.add(new HomeMultipleItem(HomeMultipleItem.TAB2, HomeMultipleItem.TAB2_SPAN_SIZE));
            } else if (homeModule.getItemType().equals("category")) {
                categoryList.addAll(homeModule.getDataList());
                HomeMultipleList.add(new HomeMultipleItem(HomeMultipleItem.CATEGORY, HomeMultipleItem.CATEGORY_SPAN_SIZE));
            }
        }
        mView.addHead(HomeMultipleList, moduleList);
        mView.showBanner(bannerList);
        mView.showMenu(menuList);
        mView.showNotice(noticeList);
        getGoodList();
    }

    /**
     * 获取推荐商品列表
     */
    public void getGoodList() {
        OkGo.<LzyResponse<RecomGoodModel>>get(Urls.URL_GOODS_RECOME_GOODS)
                .execute(new JsonCallback<LzyResponse<RecomGoodModel>>() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<RecomGoodModel>> response) {
                        if (HttpUtil.handleResponse(mActivity, response.body())) {
                            mView.showGoods(response.body().getDatas().getGoodlist());
                        }
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<LzyResponse<RecomGoodModel>> response) {
                        HttpUtil.handleError(mActivity, response);
                    }
                });

    }


    /**
     * 获取热搜词
     */
    public void getKeyWords() {
        Type type = new TypeToken<LzyResponse<KeyWordModel>>() {
        }.getType();

        ApiServer.<LzyResponse<KeyWordModel>>getData(type, Urls.URL_SEARCH_KEYWORD)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {

                    }
                })
                .map(new Function<LzyResponse<KeyWordModel>, KeyWordModel>() {
                    @Override
                    public KeyWordModel apply(@NonNull LzyResponse<KeyWordModel> response) throws Exception {

                        return response.datas;

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KeyWordModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull KeyWordModel keyWord) {

                        mView.setKeyWord(keyWord);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 签到
     */
    public void SignDay() {
        OkGo.<LzyResponse<SignBean>>post(Urls.URL_SIGN)
                .tag(this)
                .isMultipart(true)
                .headers("token", MyShopApplication.Token)
                .params("id", MyShopApplication.userId)
                .execute(new JsonCallback<LzyResponse<SignBean>>() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<SignBean>> response) {
                        if (HttpUtil.handleResponse(mActivity, response.body())) {
                            mView.showSign(true, response.body().getDatas().getNum());
                        } else {
                            mView.showSign(false, "");
                        }


                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<LzyResponse<SignBean>> response) {
                        HttpUtil.handleError(mActivity, response);
                    }
                });
    }

}
