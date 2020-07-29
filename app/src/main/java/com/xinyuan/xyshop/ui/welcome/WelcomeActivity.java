package com.xinyuan.xyshop.ui.welcome;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.umeng.analytics.MobclickAgent;
import com.xinyuan.xyshop.BuildConfig;
import com.xinyuan.xyshop.MainActivity;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.state.LoginContext;
import com.xinyuan.xyshop.common.state.LoginState;
import com.xinyuan.xyshop.common.state.LogoutState;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.LoginModel;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.dialog.color.ColorDialog;
import com.youth.xframe.cache.XCache;
import com.youth.xframe.common.XActivityStack;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.XNetworkUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import butterknife.BindView;

import static com.xinyuan.xyshop.util.CommUtil.getInAnimationTest;
import static com.xinyuan.xyshop.util.CommUtil.getOutAnimationTest;

public class WelcomeActivity extends BaseActivity {

    private String isInstall;
    private XCache xCache;

    @BindView(R.id.tv_welcome_version)
    TextView tv_welcome_version;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {
        SystemBarHelper.immersiveStatusBar(WelcomeActivity.this, 0); //设置状态栏透明
        tv_welcome_version.setText("V " + BuildConfig.VERSION_NAME);
    }

    @Override
    public void initData() {
        xCache = XCache.get(this);
        isInstall = xCache.getAsString("Install"); //判断是否是初次安装
        final LoginModel userModel = (LoginModel) xCache.getAsObject("UserBase"); //获取系统缓存的用户信息
        if (!XEmptyUtils.isSpace(isInstall)) { //如果不是初次安装
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!XEmptyUtils.isEmpty(userModel)) { //有缓存用户信息 去检查，没有，去首页
                        if (getNet()) {
                            checkUser(userModel);
                        }

                    } else {
                        toMain();
                    }

                }

            }, 1000);

        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() { //初次安装，跳转至引导界面，安装写入缓存
                    CommUtil.gotoActivity(WelcomeActivity.this, GuideActivity.class, true, null);
                    xCache.put("Install", "isInstall");
                }
            }, 1000);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            MobclickAgent.enableEncrypt(true);
        }
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
    }


    private boolean getNet() {
        if (!XNetworkUtils.isAvailable()) {
            ColorDialog dialog = new ColorDialog(this);
            dialog.setAnimationEnable(true);
            dialog.setContentText("没有网络连接，请检查是否连接至网络");
            dialog.setAnimationIn(getInAnimationTest(this));
            dialog.setAnimationOut(getOutAnimationTest(this));
            dialog.setPositiveListener("我知道了", new ColorDialog.OnPositiveListener() {
                @Override
                public void onClick(ColorDialog dialog) {
                    dialog.dismiss();
                    finish();
                    XActivityStack.getInstance().appExit();
                }
            })
                    .show();
            return false;
        } else {
            return true;
        }
    }


    public void checkUser(final LoginModel userModel) {
        OkGo.<LzyResponse<TokenBean>>post(Urls.URL_CHECK_TOKEN)
                .tag(this)
                .params("Token", userModel.getToken())
                .execute(new JsonCallback<LzyResponse<TokenBean>>() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
                        if (response.body().getCode() == Constants.HTTP_200) {
                            LoginContext.getInstance().setState(new LoginState());
                            MyShopApplication.Token = userModel.getToken();
                            MyShopApplication.userId = userModel.getId();
                        } else {
                            LoginContext.getInstance().setState(new LogoutState());
                            MyShopApplication.Token = "";
                            MyShopApplication.userId = 0;
                        }

                        toMain();
                    }


                    @Override
                    public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
                        HttpUtil.handleError(WelcomeActivity.this, response);
                    }
                });
    }

    public void toMain() {
        CommUtil.gotoActivity(WelcomeActivity.this, MainActivity.class, true, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
