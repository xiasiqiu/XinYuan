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
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.LoginModel;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.widget.EditTextWithDel;
import com.xinyuan.xyshop.widget.PaperButton;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.XRegexUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/29.
 */

public class RegFragment extends BaseFragment {
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
	private boolean isCheck = false;
	private static String user_phone = "";
	private static String user_code = "";

	public static RegFragment newInstance() {
		RegFragment fragment = new RegFragment();
		return fragment;
	}

	@OnClick({R.id.pb_code, R.id.bt_login})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.pb_code:
				final View code = view;
				String phone = ed_userphone.getText().toString().trim();
				if (!XEmptyUtils.isSpace(phone)) {
					if (XRegexUtils.checkMobile(phone)) {
						if (!isCheck) {
							CheckPhone(phone);//验证手机号是否存在
						}
					} else {
						XToast.error("手机输入格式不正确");
					}
				} else {
					ll_login_phone.setBackground(getResources().getDrawable(R.drawable.button_error_bg));
					iv_login_phone.setAnimation(CommUtil.shakeAnimation(2));
					XToast.error("请输入手机号");
				}


				break;
			case R.id.bt_login:
				user_phone = ed_userphone.getText().toString().trim();
				user_code = ed_code.getText().toString();

				if (XEmptyUtils.isSpace(user_phone)) {
					ll_login_phone.setBackground(getResources().getDrawable(R.drawable.button_error_bg));
					iv_login_phone.setAnimation(CommUtil.shakeAnimation(2));
					XToast.error("手机号码未输入");
				} else if (XEmptyUtils.isSpace(user_code)) {
					ll_login_code.setBackground(getResources().getDrawable(R.drawable.button_error_bg));
					iv_login_code.setAnimation(CommUtil.shakeAnimation(2));
					XToast.error("验证码未输入");
				} else if (!XRegexUtils.checkMobile(user_phone)) {
					XToast.error("手机号码格式不正确");
				} else {
					CheckSMS(user_phone, user_code);

				}


		}

	}


	private void CheckPhone(final String phone) {
		OkGo.<LzyResponse<LoginModel>>post(Urls.URL_EXIST_PHONE)
				.tag(this)
				.params("phone", phone)
				.execute(new JsonCallback<LzyResponse<LoginModel>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<LoginModel>> response) {
						if (!XEmptyUtils.isEmpty(response)) {
							XLog.v("CODE:" + response.body().getCode());
							if (response.body().getCode() == Constants.HTTP_111 || response.body().getCode() == Constants.HTTP_101) {
								getCode(phone);
							} else {
								XToast.error("该手机号已注册");
							}
						}

					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<LoginModel>> response) {
						HttpUtil.handleError(mContext, response);

					}
				});
	}


	private void getCode(String phone) {
		OkGo.<LzyResponse<String>>post(Urls.URL_SEND_SMS)
				.tag(this)
				.params("phone", phone)
				.execute(new JsonCallback<LzyResponse<String>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<String>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							XToast.info("短信发送成功，请注意查收！");
							timer = new MyCountTimer(60000, 1000);
							timer.start();
							isCheck = true;
						}

					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<String>> response) {
						HttpUtil.handleError(mContext, response);
					}
				});
	}

	private void CheckSMS(final String phone, String code) {
		long phoneNum = Long.parseLong(phone);
		int codeNum = Integer.valueOf(code);
		OkGo.<LzyResponse<LoginModel>>post(Urls.URL_CHECK_SMS)
				.tag(this)
				.params("phone", phoneNum)
				.params("mobileSMS", codeNum)
				.execute(new JsonCallback<LzyResponse<LoginModel>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<LoginModel>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							start(RegFragmentNext.newInstance(phone));
						}

					}

					@Override
					public void onError
							(com.lzy.okgo.model.Response<LzyResponse<LoginModel>> response) {
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
		tv_header_center.setText("用户注册");
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
		return R.layout.fragment_reg;

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (timer != null) {
			timer.cancel();
		}
	}

}
