package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.callback.DialogCallback;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.UserSecurityModel;
import com.xinyuan.xyshop.mvp.contract.SecurityView;
import com.youth.xframe.widget.XToast;

/**
 * Created by Administrator on 2017/9/23.
 */

public class SecurityPresenter extends BasePresenter<SecurityView> {
    private Activity mActivity;

    public SecurityPresenter(Activity activity, SecurityView view) {
        super(view);
        this.mActivity = activity;
    }


    /**
     * 获取用户信息
     */
    public void getInfo() {
        OkGo.<LzyResponse<UserSecurityModel>>post(Urls.URL_USER_SECURITY)
                .tag(this)
                .headers("token", MyShopApplication.Token)
                .params("userId", MyShopApplication.userId)
                .execute(new JsonCallback<LzyResponse<UserSecurityModel>>() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<UserSecurityModel>> response) {
                        if (HttpUtil.handleResponse(mActivity, response.body())) {
                            if (response.body().getCode() == Constants.HTTP_200) {
                                mView.showUserInfoBack(response.body().getDatas());
                            } else if (response.body().getCode() == Constants.HTTP_207) {
                                XToast.error(response.body().msg);
                            }
                        }
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<LzyResponse<UserSecurityModel>> response) {
                        HttpUtil.handleError(mActivity, response);
                    }
                });
    }

    /**
     * 绑定快捷方式
     *
     * @param openId
     * @param type
     */
    public void bindUser(String openId, String type) {
        OkGo.<LzyResponse<TokenBean>>post(Urls.URL_LOGIN_BIND)
                .tag(this)
                .headers("token", MyShopApplication.Token)
                .params("userId", MyShopApplication.userId)
                .params("openid", openId)
                .params("type", type)
                .execute(new DialogCallback<LzyResponse<TokenBean>>(mActivity, "绑定中...") {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
                        if (HttpUtil.handleResponse(mActivity, response.body())) {
                            getInfo();
                            XToast.info("绑定成功！");
                        }


                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
                        HttpUtil.handleError(mActivity, response);
                    }
                });
    }

    /**
     * 解除绑定快捷方式
     *
     * @param type
     */
    public void unbindUser(String type) {
        OkGo.<LzyResponse<TokenBean>>post(Urls.URL_LOGIN_UNBIND)
                .tag(this)
                .headers("token", MyShopApplication.Token)
                .params("userId", MyShopApplication.userId)
                .params("type", type)
                .execute(new DialogCallback<LzyResponse<TokenBean>>(mActivity, "解绑中...") {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
                        if (HttpUtil.handleResponse(mActivity, response.body())) {
                            getInfo();
                            XToast.info("已解除绑定！");
                        }


                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
                        HttpUtil.handleError(mActivity, response);
                    }
                });
    }


}
