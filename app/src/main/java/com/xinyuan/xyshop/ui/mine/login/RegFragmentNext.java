package com.xinyuan.xyshop.ui.mine.login;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.umeng.analytics.MobclickAgent;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.callback.DialogCallback;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.common.state.LoginContext;
import com.xinyuan.xyshop.common.state.LoginState;
import com.xinyuan.xyshop.even.LoginPageEvent;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.LoginModel;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.widget.EditTextWithDel;
import com.youth.xframe.cache.XCache;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/29.
 */

public class RegFragmentNext extends BaseFragment {
	private int loginType;
	@BindView(R.id.toolbar_tv)
	Toolbar toolbar_tv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	private static String phone;

	public static RegFragmentNext newInstance(String phone) {
		RegFragmentNext fragment = new RegFragmentNext();
		fragment.phone = phone;
		return fragment;
	}


	@BindView(R.id.ed_password)
	EditTextWithDel ed_password;
	@BindView(R.id.ed_password_re)
	EditTextWithDel ed_password_re;

	@BindView(R.id.ed_username)
	EditTextWithDel ed_username;
	@BindView(R.id.ed_invitation_num)
	EditTextWithDel ed_invitation_num;


	@OnClick({R.id.bt_login})
	public void onClick(View view) {


		switch (view.getId()) {
			case R.id.bt_login:
				String name = ed_username.getText().toString().trim();
				String pass = ed_password.getText().toString().trim();
				String pass_re = ed_password_re.getText().toString().trim();
				String invitation = ed_invitation_num.getText().toString().trim();
				if (!XEmptyUtils.isSpace(name)) {

					if (!XEmptyUtils.isSpace(pass)) {

						if (!XEmptyUtils.isSpace(pass_re)) {
							if (pass.equals(pass_re)) {

								getReg(name, pass, invitation);


							} else {
								XToast.error("两次输入的密码不一致");
							}


						} else {
							XToast.error("请重复输入密码");
						}


					} else {
						XToast.error("请输入密码");
					}
				} else {
					XToast.error("请输入用户名");
				}
				break;

		}
	}


	private void getReg(String name, String pass, String invitation) {
		OkGo.<LzyResponse<LoginModel>>post(Urls.URL_USER_REG)
				.tag(this)
				.params("userName", name)
				.params("password", pass)
				.params("phone", phone)
				.params("invite_code", invitation)
				.execute(new DialogCallback<LzyResponse<LoginModel>>(getActivity(), "登陆中...") {
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


	@Override
	public void initView(View rootView) {
		tv_header_center.setText("用户注册");
		CommUtil.initToolBar(_mActivity, mContext, iv_header_left, null);

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
		return R.layout.fragment_reg_1;
	}

}
