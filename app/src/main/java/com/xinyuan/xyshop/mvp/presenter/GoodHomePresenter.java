package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;
import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.AddCartBean;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.callback.JsonConvert;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.ChatDetailModule;
import com.xinyuan.xyshop.model.GoodDetailModels;
import com.xinyuan.xyshop.mvp.contract.GoodHomeView;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by fx on 2017/8/7.
 */

public class GoodHomePresenter extends BasePresenter<GoodHomeView> {
    private Activity mActivity;
    private Context context;

    public GoodHomePresenter(Activity activity, GoodHomeView view) {
        super(view);
        this.mActivity = activity;
    }

    /**
     * 获取商品详情数据
     *
     * @param goodId
     */
    public void getData(String goodId) {
        OkGo.<LzyResponse<GoodDetailModels>>post(Urls.URL_GOODS_DETAIL)
                .tag(this)
                .headers("token", MyShopApplication.Token)
                .headers("userId", String.valueOf(MyShopApplication.userId))
                .params("goodsId", goodId)
                .execute(new JsonCallback<LzyResponse<GoodDetailModels>>() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<GoodDetailModels>> response) {
                        if (HttpUtil.handleResponse(mActivity, response.body())) {
                            if (response.body().code == Constants.HTTP_215) {
                                mView.shoEmpty();
                            } else {
                                mView.showView(response.body().getDatas());

                            }
                        }

                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<LzyResponse<GoodDetailModels>> response) {
                        HttpUtil.handleError(mActivity, response);
                    }
                });


    }

//	/**
//	 * 加入商品浏览记录
//	 *
//	 * @param goodId
//	 */
//	public void addHistory(String goodId) {
//		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_GOODS_ADD_DETAIL)
//				.tag(this)
//				.headers("token", MyShopApplication.Token)
//				.params("userId", MyShopApplication.userId)
//				.params("goodsId", goodId)
//				.execute(new JsonCallback<LzyResponse<TokenBean>>() {
//					@Override
//					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
//						if (HttpUtil.handleResponse(mActivity, response.body())) {
//						}
//
//					}
//
//					@Override
//					public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
//						HttpUtil.handleError(mActivity, response);
//					}
//				});
//	}

    /**
     * 加入购物车
     *
     * @param specId
     * @param goodId
     * @param count
     */
    public void addCart(String specId, long goodId, int count) {
        OkGo.<LzyResponse<AddCartBean>>post(Urls.URL_GOODS_ADD_CART)
                .headers("token", MyShopApplication.Token)
                .params("userId", MyShopApplication.userId)
                .params("goodsId", goodId)
                .params("goodsInfoId", specId)
                .params("count", count)
                .converter(new JsonConvert<LzyResponse<AddCartBean>>() {
                })
                .adapt(new ObservableBody<LzyResponse<AddCartBean>>())
                .subscribeOn(Schedulers.io())
                .map(new Function<LzyResponse<AddCartBean>, AddCartBean>() {
                    @Override
                    public AddCartBean apply(@NonNull LzyResponse<AddCartBean> response) throws Exception {


                        if (response.getCode() == Constants.HTTP_200 || response.getCode() == Constants.HTTP_119 || response.getCode() == Constants.HTTP_201) {
                            AddCartBean addCartBean = new AddCartBean();
                            addCartBean.setMsg(response.msg);
                            addCartBean.setGoodsCartId(response.getDatas().getGoodsCartId());
                            return addCartBean;

                        } else {
                            AddCartBean addCartBean = new AddCartBean();
                            return addCartBean;
                        }


                    }
                })
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddCartBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull AddCartBean tokenBean) {
                        mView.addCartBack(tokenBean);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();

                    }

                    @Override
                    public void onComplete() {


                    }
                });
    }

    /**
     * 立即购买
     *
     * @param chosesGoodId
     * @param goodId
     * @param count
     */
    public void goToBuy(String chosesGoodId, long goodId, int count) {
        OkGo.<LzyResponse<AddCartBean>>post(Urls.URL_GOODS_ADD_CART)
                .headers("token", MyShopApplication.Token)
                .params("userId", MyShopApplication.userId)
                .params("goodsId", goodId)
                .params("goodsInfoId", chosesGoodId)
                .params("count", count)
                .execute(new JsonCallback<LzyResponse<AddCartBean>>() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<AddCartBean>> response) {
                        if (HttpUtil.handleResponse(mActivity, response.body())) {
                            mView.goToBuy(response.body().getDatas().getGoodsCartId());
                        }


                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<LzyResponse<AddCartBean>> response) {
                        HttpUtil.handleError(mActivity, response);
                    }
                });
    }
}
