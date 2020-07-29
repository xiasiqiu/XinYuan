package com.xinyuan.xyshop.ui.mine.login;

import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.CheckBean;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.callback.DialogCallback;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.LoginModel;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.EditTextWithDel;
import com.xinyuan.xyshop.widget.PaperButton;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.XRegexUtils;
import com.youth.xframe.widget.XToast;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fx on 2017/5/27.
 * 忘记密码fragment
 */
public class ForgetFragment extends BaseFragment {
	@BindView(R.id.bt_login)
	Button bt_login;
	private int resultCode = 0;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.ed_userphone)
	EditTextWithDel ed_userphone;
	@BindView(R.id.ed_code)
	EditTextWithDel ed_code;
	@BindView(R.id.ed_password_re)
	EditTextWithDel ed_password_re;
	@BindView(R.id.ed_password)
	EditTextWithDel ed_password;

	@BindView(R.id.iv_login_code)
	ImageView iv_login_code;
	@BindView(R.id.iv_login_phone)
	ImageView iv_login_phone;
	@BindView(R.id.ll_login_phone)
	LinearLayout ll_login_phone;


	@BindView(R.id.pb_code)
	PaperButton pb_code;
	MyCountTimer timer;

	@BindView(R.id.ll_login_re)
	LinearLayout ll_login_re;
	@BindView(R.id.ll_login_forget)
	LinearLayout ll_login_forget;
	@BindView(R.id.ll_login_pass)
	LinearLayout ll_login_pass;
	@BindView(R.id.toolbar_tv)
	Toolbar toolbar_tv;
	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	private static String user_phone = "";
	private static String user_code = "";
	public static String title;

	public static ForgetFragment newInstance(String title) {
		ForgetFragment fragment = new ForgetFragment();
		fragment.title = title;
		return fragment;
	}


	private boolean isCheck = false;
	private boolean CodeRight = true;

	@OnClick({R.id.pb_code, R.id.bt_login})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.pb_code:
				final View code = view;
				String phone = ed_userphone.getText().toString();
				if (!XEmptyUtils.isSpace(phone)) {
					if (XRegexUtils.checkMobile(phone)) {
						if (isCheck) {
						} else {
							checkPhone(phone);
						}
					} else {
						ll_login_phone.setBackground(getResources().getDrawable(R.drawable.button_error_bg));
						iv_login_phone.setAnimation(CommUtil.shakeAnimation(2));
						XToast.info("手机号格式不正确");

					}
				} else {
					ll_login_phone.setBackground(getResources().getDrawable(R.drawable.button_error_bg));
					iv_login_phone.setAnimation(CommUtil.shakeAnimation(2));
					XToast.info("请输入手机号");
				}


				break;
			case R.id.bt_login:
				if (CodeRight) {
					user_phone = ed_userphone.getText().toString();
					user_code = ed_code.getText().toString();
					if (XEmptyUtils.isSpace(user_phone)) {
						XToast.error("手机号未输入");
					} else if (XEmptyUtils.isSpace(user_code)) {
						XToast.error("验证码未输入");
					} else {
						checkSMS(user_phone, user_code);
					}
				} else {
					String pass = ed_password.getText().toString();
					String pass_re = ed_password_re.getText().toString();
					if (XEmptyUtils.isSpace(pass)) {
						ll_login_pass.setBackground(getResources().getDrawable(R.drawable.button_error_bg));
						ll_login_pass.setAnimation(CommUtil.shakeAnimation(2));
						XToast.info("请输入密码");
					} else if (XEmptyUtils.isSpace(pass_re)) {
						ed_password_re.setBackground(getResources().getDrawable(R.drawable.button_error_bg));
						ed_password_re.setAnimation(CommUtil.shakeAnimation(2));
						XToast.info("请重复密码");
					} else {
						if (pass.equals(pass_re)) {
							postPass(user_phone, pass);
						} else {
							ed_password_re.setBackground(getResources().getDrawable(R.drawable.button_error_bg));
							ed_password_re.setAnimation(CommUtil.shakeAnimation(2));
							XToast.info("密码不一致");
						}


					}
				}

				break;

		}

	}

	/**
	 * @param phone
	 */
	private void checkPhone(String phone) {
		OkGo.<LzyResponse<LoginModel>>post(Urls.URL_EXIST_PHONE)
				.tag(this)
				.params("phone", phone)
				.execute(new JsonCallback<LzyResponse<LoginModel>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<LoginModel>> response) {
						if (response.body().getCode() == Constants.HTTP_110) {
							timer = new MyCountTimer(60000, 1000);
							timer.start();
							isCheck = true;
							getCode(phone);
						} else if (response.body().getCode() == Constants.HTTP_105) {
							XToast.error("该手机号未绑定");
						} else if (response.body().getCode() == Constants.HTTP_111) {
							XToast.error("该手机号未注册");
						}


					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<LoginModel>> response) {
						HttpUtil.handleError(mContext, response);
					}
				});

	}

	/**
	 * 发送密码
	 *
	 * @param pass
	 */
	private void postPass(String phone, String pass) {
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_USER_FORGET)
				.tag(this)
				.params("phone", phone)
				.params("key", key)
				.params("password", pass)
				.execute(new JsonCallback<LzyResponse<TokenBean>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							XToast.info("修改成功");
							_mActivity.onBackPressed();
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						HttpUtil.handleError(mContext, response);
					}
				});

	}

	private String key;

	/**
	 * 登录获取用户ID修改密码
	 *
	 * @param phone
	 * @param code
	 */
	private void checkSMS(String phone, String code) {
		long phoneNum = Long.parseLong(phone);
		int codeNum = Integer.valueOf(code);
		OkGo.<LzyResponse<CheckBean>>post(Urls.URL_CHECK_SMS)
				.tag(this)
				.params("phone", phoneNum)
				.params("mobileSMS", codeNum)
				.execute(new JsonCallback<LzyResponse<CheckBean>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<CheckBean>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							key = response.body().getDatas().getKey();
							ll_login_forget.setVisibility(View.GONE);
							ll_login_re.setVisibility(View.VISIBLE);
							tv_header_center.setText("修改密码");
							bt_login.setText("提交修改");
							CodeRight = false;
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<CheckBean>> response) {
						HttpUtil.handleError(mContext, response);
					}
				});
	}

	/**
	 * 获取验证码
	 *
	 * @param phone
	 */
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
		if (title.equals("修改密码")) {
			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_tv);
			tv_header_center.setText(title);

		} else {
			tv_header_center.setText(title);
		}
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
		return R.layout.fragment_login_foegt;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (timer != null) {
			timer.cancel();
		}
	}
}

