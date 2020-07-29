package com.xinyuan.xyshop.ui.mine.info.security;

import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.CheckBean;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.callback.DialogCallback;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.EditTextWithDel;
import com.xinyuan.xyshop.widget.PaperButton;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.XRegexUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fx on 2017/8/2.
 * 验证手机号fragment
 */

public class CheckPhoneFragment extends BaseFragment<BasePresenter> {
	@BindView(R.id.tv_phone)
	TextView tv_phone;
	@BindView(R.id.ed_code)
	EditTextWithDel ed_code;
	@BindView(R.id.pb_code)
	PaperButton pb_code;

	@BindView(R.id.toolbar_iv)
	Toolbar msg_toolbar;
	@BindView(R.id.iv_header_right)
	ImageView iv_header_right;
	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;


	private String phone;
	MyCountTimer timer;
	private boolean isCheck = false;
	private static int flag; //1:修改手机号 2：修改密码

	public static CheckPhoneFragment newInstance(String phone, int Flag) {
		CheckPhoneFragment checkPhoneFragment = new CheckPhoneFragment();
		checkPhoneFragment.phone = phone;
		checkPhoneFragment.flag = Flag;
		return checkPhoneFragment;
	}

	@Override
	public void initView(View rootView) {
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
		tv_header_center.setText("手机验证");
		CommUtil.initToolBar(getActivity(), iv_header_left, iv_header_right);
	}

	@Override
	public void initData() {
		if (!XEmptyUtils.isEmpty(phone)) {
			tv_phone.setText("现有手机号：" + XRegexUtils.phoneNoHide(phone));
			pb_code.performClick();
		} else {
			XToast.info("您还未绑定手机号，请绑定手机号");
			_mActivity.onBackPressed();
		}
	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_checkphone;
	}

	@OnClick({R.id.bt_login, R.id.pb_code})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.pb_code:
				if (isCheck) {

				} else {
					getCode(phone);

				}
				break;
			case R.id.bt_login:
				String code = ed_code.getText().toString().trim();
				if (XEmptyUtils.isSpace(code)) {
					XToast.error("验证码未输入");
				} else {
					checkPhone(phone, code);
				}
				break;
		}


	}


	private void login(String key) {
		_mActivity.onBackPressed();
		if (flag == 1) {
			start(ChangePhoneFragment.newInstance(key,phone));
		} else {
			start(ChangePassFragment.newInstance(key));

		}

	}

	private void checkPhone(String phone, String code) {
		OkGo.<LzyResponse<CheckBean>>post(Urls.URL_CHECK_SMS)
				.tag(this)
				.params("phone", phone)
				.params("mobileSMS", code)
				.execute(new DialogCallback<LzyResponse<CheckBean>>(getActivity(), "验证中...") {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<CheckBean>> response) {
						if (HttpUtil.handleResponse(getContext(), response.body())) {
							login(response.body().getDatas().getKey());
						}

					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<CheckBean>> response) {
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
						if (HttpUtil.handleResponse(getContext(), response.body())) {
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
