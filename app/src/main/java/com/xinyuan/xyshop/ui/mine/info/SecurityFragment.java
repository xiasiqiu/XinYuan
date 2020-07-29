package com.xinyuan.xyshop.ui.mine.info;

import android.app.ProgressDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.SocializeUtils;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.bean.UserInfoBean;
import com.xinyuan.xyshop.callback.DialogCallback;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.common.state.LoginContext;
import com.xinyuan.xyshop.common.state.LoginState;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.even.LoginPageEvent;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.LoginModel;
import com.xinyuan.xyshop.model.UserSecurityModel;
import com.xinyuan.xyshop.mvp.contract.SecurityView;
import com.xinyuan.xyshop.mvp.presenter.SecurityPresenter;
import com.xinyuan.xyshop.ui.mine.info.security.BindEmailFragment;
import com.xinyuan.xyshop.ui.mine.info.security.ChangePhoneFragment;
import com.xinyuan.xyshop.ui.mine.info.security.CheckPhoneFragment;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.dialog.color.ColorDialog;
import com.youth.xframe.cache.XCache;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.XRegexUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/27.
 * 账户安全与设置
 */

public class SecurityFragment extends BaseFragment<SecurityPresenter> implements SecurityView {
    @BindView(R.id.toolbar_iv)
    Toolbar msg_toolbar;
    @BindView(R.id.tv_header_center)
    TextView tv_header_center;
    @BindView(R.id.iv_header_left)
    ImageView iv_header_left;
    @BindView(R.id.iv_header_right)
    ImageView iv_header_right;
    @BindView(R.id.tv_user_name)
    TextView tv_user_name;
    @BindView(R.id.tv_user_phone)
    TextView tv_user_phone;
    @BindView(R.id.tv_bank_bind)
    TextView tv_bank_bind;
    @BindView(R.id.tv_qq_bind)
    TextView tv_qq_bind;
    @BindView(R.id.tv_wx_bind)
    TextView tv_wx_bind;
    @BindView(R.id.tv_email_bind)
    TextView tv_email_bind;
    @BindView(R.id.tv_user_phone_title)
    TextView tv_user_phone_title;
    private UserInfoBean userInfoBean;
    private int type;


    public static SecurityFragment newInstance(UserInfoBean UserInfoBean) {
        SecurityFragment fragment = new SecurityFragment();
        fragment.userInfoBean = UserInfoBean;
        return fragment;
    }


    @Override
    protected SecurityPresenter createPresenter() {
        return new SecurityPresenter(getActivity(), this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_security;
    }

    @OnClick({R.id.rl_user_band, R.id.rl_user_pass, R.id.rl_user_phone, R.id.rl_user_email, R.id.rl_user_qq, R.id.rl_user_wechat})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_user_band:
                start(BandFragment.newInstance());
                break;
            case R.id.rl_user_pass:
                if (XEmptyUtils.isEmpty(userModel.getUserPhone())) {

                    final ColorDialog colorDialog = new ColorDialog(mContext);
                    colorDialog.setContentText("您还未绑定手机号，请先绑定手机号后修改密码");
                    colorDialog.setPositiveListener("立即绑定", new ColorDialog.OnPositiveListener() {
                        @Override
                        public void onClick(ColorDialog dialog) {
                            colorDialog.dismiss();
                            start(ChangePhoneFragment.newInstance("", ""));
                        }
                    })
                            .setNegativeListener("取消", new ColorDialog.OnNegativeListener() {
                                @Override
                                public void onClick(ColorDialog dialog) {
                                    colorDialog.dismiss();
                                }
                            }).show();

                } else {

                    start(CheckPhoneFragment.newInstance(userModel.getUserPhone(), 2));


                }
                break;
            case R.id.rl_user_phone:
                if (XEmptyUtils.isEmpty(userModel.getUserPhone())) {
                    start(ChangePhoneFragment.newInstance("", ""));
                } else {
                    start(CheckPhoneFragment.newInstance(userModel.getUserPhone(), 1));
                }
                break;
            case R.id.rl_user_email:
                if (XEmptyUtils.isSpace(userModel.getUserEmail())) {
                    start(BindEmailFragment.newInstance(userModel.getUserEmail()));
                } else {
                    start(BindEmailFragment.newInstance(userModel.getUserEmail()));

                }
                break;
            case R.id.rl_user_qq:
                if (userModel.getUserQQ().equals("1")) {
                    mPresenter.unbindUser("qq");
                } else {
                    UMShareAPI.get(mContext).doOauthVerify(getActivity(), SHARE_MEDIA.QQ, authListener);
                }
                type = 1;
                break;
            case R.id.rl_user_wechat:
                if (userModel.getUserWX().equals("1")) {
                    mPresenter.unbindUser("wx");
                } else {
                    UMShareAPI.get(mContext).doOauthVerify(getActivity(), SHARE_MEDIA.WEIXIN, authListener);
                }
                type = 2;

                break;

        }

    }

    @Override
    public void initView(View rootView) {
        SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
        SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
        tv_header_center.setText("账号与安全设置");
        CommUtil.initToolBar(getActivity(), iv_header_left, iv_header_right);

    }

    @Override
    public void initData() {
        if (!XEmptyUtils.isEmpty(userInfoBean)) {
            tv_user_name.setText(userInfoBean.getUserName());
        } else {
            _mActivity.onBackPressed();
        }
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(getActivity()).setShareConfig(config);
    }

    private UserSecurityModel userModel;


    private ProgressDialog dialog;

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
            if (!XEmptyUtils.isEmpty(data)) {
                String openId = data.get("openid");
                String Bindtype = "";
                if (type == 1) {
                    Bindtype = "qq";
                } else {
                    Bindtype = "wx";

                }
                mPresenter.bindUser(openId, Bindtype);
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

    @BindView(R.id.tv_pass)
    TextView tv_pass;

    @Override
    public void onSupportVisible() { //当fragment可见时，检查是否有新消息
        mPresenter.getInfo();
        super.onSupportVisible();

    }

    @Override
    public void showUserInfoBack(UserSecurityModel userSecurityModel) {
        if (!XEmptyUtils.isEmpty(userSecurityModel)) {
            userModel = userSecurityModel;
        }
        if (!XEmptyUtils.isSpace(userSecurityModel.getUserPhone())) {
            tv_user_phone.setText(userSecurityModel.getUserPhone());
        } else {
            tv_user_phone_title.setText("绑定手机号");
        }
        if (userSecurityModel.getUserPass().equals("0")) {
            tv_pass.setText("设置登录密码");
        } else {
            tv_pass.setText("修改登录密码");

        }

        if (!XEmptyUtils.isEmpty(userSecurityModel.getBankList())) {
            tv_bank_bind.setText("已绑定");
        }
        if (userSecurityModel.getUserQQ().equals("1")) {
            tv_qq_bind.setText("已绑定");
        } else {
            tv_qq_bind.setText("未绑定");

        }
        if (userSecurityModel.getUserWX().equals("1")) {
            tv_wx_bind.setText("已绑定");
        } else {
            tv_wx_bind.setText("未绑定");

        }
        if (!XEmptyUtils.isSpace(userSecurityModel.getUserEmail())) {
            tv_email_bind.setText(userSecurityModel.getUserEmail());
        }
    }

    @Override
    public void showBindBack() {

    }

    @Override
    public void ShowUnBindBack() {

    }
}
