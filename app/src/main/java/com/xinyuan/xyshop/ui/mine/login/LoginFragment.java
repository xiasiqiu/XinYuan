package com.xinyuan.xyshop.ui.mine.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.callback.DialogCallback;
import com.xinyuan.xyshop.common.state.LoginContext;
import com.xinyuan.xyshop.common.state.LoginState;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.even.LoginPageEvent;
import com.xinyuan.xyshop.even.MainFragmentStartEvent;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.LoginModel;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.widget.EditTextWithDel;
import com.youth.xframe.cache.XCache;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginFragment extends BaseFragment {


    @BindView(R.id.toolbar_tv)
    Toolbar toolbar_tv;
    @BindView(R.id.tv_header_center)
    TextView tv_header_center;
    @BindView(R.id.tv_header_right)
    TextView tv_header_right;
    @BindView(R.id.bt_login)
    Button bt_login;

    @BindView(R.id.ed_username)
    EditTextWithDel ed_username;
    @BindView(R.id.ed_password)
    EditTextWithDel ed_password;

    @BindView(R.id.ll_login_phone)
    LinearLayout ll_login_phone;
    @BindView(R.id.ll_login_pass)
    LinearLayout ll_login_pass;
    @BindView(R.id.iv_login_pass)
    ImageView iv_login_pass;
    @BindView(R.id.iv_login_phone)
    ImageView iv_login_phone;
    @BindView(R.id.iv_header_left)
    ImageView iv_header_left;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }


    private static final int REQ_USER = 100;

    @OnClick({R.id.tv_fast_login, R.id.tv_forget_pass, R.id.bt_login, R.id.iv_login_wechat, R.id.iv_login_qq})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_fast_login:
                start(LoginFastFragment.newInstance());
                break;
            case R.id.tv_forget_pass:
                start(ForgetFragment.newInstance("忘记密码"));
                break;
            case R.id.bt_login:

                String name = ed_username.getText().toString().trim();
                String password = ed_password.getText().toString().trim();
                if (XEmptyUtils.isSpace(name)) {
                    ll_login_phone.setBackground(getResources().getDrawable(R.drawable.button_error_bg));
                    iv_login_phone.setAnimation(CommUtil.shakeAnimation(2));
                    XToast.error("请输入用户名");
                } else if (XEmptyUtils.isSpace(password)) {
                    XToast.error("请输入用户名");
                    ll_login_pass.setBackground(getResources().getDrawable(R.drawable.button_error_bg));
                    iv_login_pass.setAnimation(CommUtil.shakeAnimation(2));
                } else {
                    Login(name, password);
                }

                break;
            case R.id.iv_login_qq:
                //  final boolean isauth = UMShareAPI.get(mContext).isAuthorize(getActivity(), SHARE_MEDIA.QQ);
                LoginType = "qq";
//                if (isauth) {
//                    UMShareAPI.get(mContext).getPlatformInfo(getActivity(), SHARE_MEDIA.QQ, infoListener);
//                } else {
//                    UMShareAPI.get(mContext).doOauthVerify(getActivity(), SHARE_MEDIA.QQ, authListener);
//                }
                UMShareAPI.get(mContext).doOauthVerify(getActivity(), SHARE_MEDIA.QQ, authListener);

                //UMShareAPI.get(mContext).getPlatformInfo(getActivity(), SHARE_MEDIA.QQ, authListener);
                break;
            case R.id.iv_login_wechat:
                final boolean WXauth = UMShareAPI.get(mContext).isAuthorize(getActivity(), SHARE_MEDIA.WEIXIN);
                LoginType = "wx";
//                if (WXauth) {
//                    UMShareAPI.get(mContext).getPlatformInfo(getActivity(), SHARE_MEDIA.WEIXIN, infoListener);
//                } else {
//                    UMShareAPI.get(mContext).doOauthVerify(getActivity(), SHARE_MEDIA.WEIXIN, authListener);
//                }
                UMShareAPI.get(mContext).doOauthVerify(getActivity(), SHARE_MEDIA.WEIXIN, authListener);

                //UMShareAPI.get(mContext).getPlatformInfo(getActivity(), SHARE_MEDIA.WEIXIN, authListener);
                break;


        }
    }

    private String LoginType = "";
    private ProgressDialog dialog;


    private void Login(String name, String password) {
        OkGo.<LzyResponse<LoginModel>>post(Urls.URL_USER_LOGIN)
                .tag(this)
                .params("UserName", name)
                .params("UserPassword", password)
                .execute(new DialogCallback<LzyResponse<LoginModel>>(getActivity(), "登录中...") {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<LoginModel>> response) {
                        if (HttpUtil.handleResponse(mContext, response.body())) {
                            LoginContext.getInstance().setState(new LoginState());
                            XCache cache = XCache.get(mContext);
                            cache.put("UserBase", response.body().getDatas());
                            MobclickAgent.onProfileSignIn(String.valueOf(response.body().getDatas().getId()));
                            MyShopApplication.userId = response.body().getDatas().getId();
                            MyShopApplication.Token = response.body().getDatas().getToken();
                            EventBus.getDefault().post(new LoginPageEvent("Login", true, response.body().getDatas().getToken()));
                            EventBus.getDefault().post(response.body().datas);
                            getActivity().finish();
                            XToast.info("登录成功！");

                        }


                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<LzyResponse<LoginModel>> response) {
                        HttpUtil.handleError(mContext, response);
                    }
                });
    }

    private void loginFast(String openId, String name, String gender, String iconurl, String type) {
        if (gender.equals("男")) {
            gender = "1";
        } else {
            gender = "0";
        }
        OkGo.<LzyResponse<LoginModel>>post(Urls.URL_LOGIN_FAST)
                .tag(this)
                .params("openid", openId)
                .params("userName", name)
                .params("sex", gender)
                .params("profile_image_url", iconurl)
                .params("type", type)
                .execute(new DialogCallback<LzyResponse<LoginModel>>(getActivity(), "登录中...") {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<LoginModel>> response) {
                        if (HttpUtil.handleResponse(mContext, response.body())) {
                            LoginContext.getInstance().setState(new LoginState());
                            XCache cache = XCache.get(mContext);
                            cache.put("UserBase", response.body().getDatas());
                            MobclickAgent.onProfileSignIn(String.valueOf(response.body().getDatas().getId()));
                            MyShopApplication.userId = response.body().getDatas().getId();
                            MyShopApplication.Token = response.body().getDatas().getToken();
                            EventBus.getDefault().post(new LoginPageEvent("Login", true, response.body().getDatas().getToken()));
                            EventBus.getDefault().post(response.body().datas);
                            getActivity().finish();
                            XToast.info("登录成功！");
                        }


                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<LzyResponse<LoginModel>> response) {
                        HttpUtil.handleError(mContext, response);
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        registerEventBus(this);
    }

    @Override
    public void initView(View rootView) {
        tv_header_center.setText("登录");
        tv_header_right.setText("注册");
        tv_header_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(RegFragment.newInstance());
            }
        });
        dialog = new ProgressDialog(getActivity());
        CommUtil.initToolBar(getActivity(), iv_header_left, null);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {


    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_login;

    }

    @Override
    public void onDestroyView() {
        unregisterEventBus(this);
        super.onDestroyView();
    }

    @Subscribe
    public void page(LoginPageEvent eventBus) {

    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            SocializeUtils.safeShowDialog(dialog);
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            SocializeUtils.safeCloseDialog(dialog);
            if (LoginType.equals("qq")) {
                UMShareAPI.get(mContext).getPlatformInfo(getActivity(), SHARE_MEDIA.QQ, infoListener);

            } else if (LoginType.equals("wx")) {
                UMShareAPI.get(mContext).getPlatformInfo(getActivity(), SHARE_MEDIA.WEIXIN, infoListener);

            }


        }


        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            SocializeUtils.safeCloseDialog(dialog);
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(mContext, "取消授权", Toast.LENGTH_LONG).show();

        }
    };
    UMAuthListener infoListener = new UMAuthListener() {
        /**
         * @desc 获取资料开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            SocializeUtils.safeShowDialog(dialog);
        }

        /**
         * @desc 获取资料成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            SocializeUtils.safeCloseDialog(dialog);
            String openId = data.get("openid");
            String name = data.get("name");
            String gender = data.get("gender");
            String iconurl = data.get("iconurl");
            loginFast(openId, name, gender, iconurl, LoginType);

        }


        /**
         * @desc 获取资料失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            SocializeUtils.safeCloseDialog(dialog);
            String s = t.getMessage();
            s = s.substring(s.indexOf("错误码"), s.indexOf("错误信息"));
            int code = Integer.decode(s);
            switch (code) {
                case 2008:
                    XToast.info("还未安装相关应用，无法快捷登录");
                    break;
            }
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(mContext, "取消了", Toast.LENGTH_LONG).show();

        }
    };

    @Override
    public boolean onBackPressedSupport() {
        EventBus.getDefault().postSticky(new MainFragmentStartEvent(null, true, 200));
        return super.onBackPressedSupport();
    }


}

