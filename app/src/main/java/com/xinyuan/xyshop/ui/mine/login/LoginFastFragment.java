package com.xinyuan.xyshop.ui.mine.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.even.LoginPageEvent;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.widget.EditTextWithDel;
import com.xinyuan.xyshop.widget.PaperButton;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.XRegexUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
	MyCountTimer timer;

	public static LoginFastFragment newInstance() {
		LoginFastFragment fragment = new LoginFastFragment();
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_login_fast;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		tv_header_center.setText("快捷登录");
	}

	@Override
	public void onLazyInitView(@Nullable Bundle savedInstanceState) {


	}

	private static boolean isCheck = false;

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
							timer = new MyCountTimer(30000, 1000);
							timer.start();
							isCheck = true;
						}

					} else {
						ll_login_phone.setBackground(getResources().getDrawable(R.drawable.button_error_bg));
						iv_login_phone.setAnimation(CommUtil.shakeAnimation(2));
						XToast.error("手机号格式不正确");

					}
				} else {
					ll_login_phone.setBackground(getResources().getDrawable(R.drawable.button_error_bg));
					iv_login_phone.setAnimation(CommUtil.shakeAnimation(2));
					XToast.error("请输入手机号");
				}


				break;
			case R.id.bt_login:
				user_phone = ed_userphone.getText().toString();
				user_code = ed_code.getText().toString();

				if (XEmptyUtils.isSpace(user_phone)) {
					ll_login_phone.setBackground(getResources().getDrawable(R.drawable.button_error_bg));
					iv_login_phone.setAnimation(CommUtil.shakeAnimation(2));
					XToast.error("手机号未输入");
				} else if (XEmptyUtils.isSpace(user_code)) {
					ll_login_code.setBackground(getResources().getDrawable(R.drawable.button_error_bg));
					iv_login_code.setAnimation(CommUtil.shakeAnimation(2));
					XToast.error("验证码未输入");
				} else {
					XLog.v("可以登录");
					MyShopApplication.isLogin = true;
					EventBus.getDefault().post(new LoginPageEvent("Fast", true));
					getActivity().finish();
				}


		}

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
	public void onDestroy() {
		super.onDestroy();
		if (timer != null) {
			timer.cancel();
		}
	}


}

