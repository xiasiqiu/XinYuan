package com.xinyuan.xyshop.ui.mine.login;

import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.umeng.analytics.MobclickAgent;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.callback.DialogCallback;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.common.state.LoginContext;
import com.xinyuan.xyshop.common.state.LoginState;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.even.LoginPageEvent;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.LoginModel;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.widget.EditTextWithDel;
import com.xinyuan.xyshop.widget.PaperButton;
import com.youth.xframe.cache.XCache;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.XRegexUtils;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginFastFragment extends BaseFragment {
    @BindView(R.id.bt_login)
    Button bt_login;
    private int resultCode = 0;
    @BindView(R.id.toolbar_tv)
    Toolbar toolbar_tv;
    @BindView(R.id.tv_header_center)
    TextView tv_header_center;

    @BindView(R.id.ed_userphone)
    EditTextWithDel ed_userphone;
    @BindView(R.id.ed_code)
    EditTextWithDel ed_code;

    @BindView(R.id.iv_login_code)
    ImageView iv_login_code;
    @BindView(R.id.iv_login_phone)
    ImageView iv_login_phone;
    @BindView(R.id.ll_login_phone)
    LinearLayout ll_login_phone;
    @BindView(R.id.pb_code)
    PaperButton pb_code;
    @BindView(R.id.ll_login_code)
    LinearLayout ll_login_code;

    @BindView(R.id.iv_header_left)
    ImageView iv_header_left;
    MyCountTimer timer;

    public static LoginFastFragment newInstance() {
        LoginFastFragment fragment = new LoginFastFragment();
        return fragment;
    }


    private boolean isCheck = false;

    @OnClick({R.id.pb_code, R.id.bt_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pb_code:
                String phone = ed_userphone.getText().toString();
                if (!XEmptyUtils.isSpace(phone)) {
                    if (XRegexUtils.checkMobile(phone)) {
                        if (isCheck) {
                        } else {
                            timer = new MyCountTimer(60000, 1000);
                            timer.start();
                            getCode(phone);
                            isCheck = true;
                        }

                    } else {
                        XToast.error("手机号格式不正确");

                    }
                } else {

                    XToast.error("请输入手机号");
                }


                break;
            case R.id.bt_login:
                user_phone = ed_userphone.getText().toString();
                user_code = ed_code.getText().toString();
                if (XEmptyUtils.isSpace(user_phone)) {
                    XToast.error("手机号未输入");
                } else if (XEmptyUtils.isSpace(user_code)) {
                    XToast.error("验证码未输入");
                } else if (!XRegexUtils.checkMobile(user_phone)) {

                    XToast.error("手机号格式错误");
                } else {
                    login(user_phone, user_code);

                }


        }

    }

    private void login(String phone, String code) {
        long phoneNum = Long.parseLong(phone);
        int codeNum = Integer.valueOf(code);
        OkGo.<LzyResponse<LoginModel>>post(Urls.URL_PHONE_LOGIN)
                .tag(this)
                .params("phone", phoneNum)
                .params("mobileSMS", codeNum)
                .execute(new JsonCallback<LzyResponse<LoginModel>>() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<LoginModel>> response) {
                        if (HttpUtil.handleResponse(mContext, response.body())) {
                            LoginContext.getInstance().setState(new LoginState());
                            XCache cache = XCache.get(mContext);
                            cache.put("UserBase", response.body().getDatas());
                            MobclickAgent.onProfileSignIn(String.valueOf(response.body().getDatas().getId()));

                            MyShopApplication.Token = response.body().getDatas().getToken();
                            MyShopApplication.userId = response.body().getDatas().getId();
                            EventBus.getDefault().post(new LoginPageEvent("Login", true, response.body().getDatas().getToken()));

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

    private void getCode(String phone) {
        long phoneNum = Long.decode(phone);
        OkGo.<LzyResponse<String>>post(Urls.URL_SEND_SMS)
                .tag(this)
                .params("phone", phone)
                .execute(new DialogCallback<LzyResponse<String>>(getActivity(), "发送中...") {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<String>> response) {
                        if (HttpUtil.handleResponse(mContext, response.body())) {
                            XToast.info("短信发送成功，请注意查收！");
                        }

                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<LzyResponse<String>> response) {
                        HttpUtil.handleError(mContext, response);
                    }
                });
    }


    private static String user_phone = "";
    private static String user_code = "";


    class MyCountTimer extends CountDownTimer {

        public MyCountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            pb_code.setText((millisUntilFinished / 1000) + "秒后重发");
            pb_code.setClickable(false);
        }

        @Override
        public void onFinish() {
            pb_code.setText("重新发送");
            pb_code.setClickable(true);
            isCheck = false;
        }
    }

    @Override
    public void initView(View rootView) {
        tv_header_center.setText("快捷登录");
        CommUtil.initToolBar(getActivity(), iv_header_left, null);

    }

    @Override
    public void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_login_fast;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }


}

