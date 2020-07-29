package com.xinyuan.xyshop.ui.mine.info.security;

import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.CheckBean;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.common.Constants;
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
 * 修改绑定手机号Fragment
 */

public class ChangePhoneFragment extends BaseFragment<BasePresenter> {
	@BindView(R.id.toolbar_iv)
	Toolbar msg_toolbar;
	@BindView(R.id.iv_header_right)
	ImageView iv_header_right;
	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.ll_input_phone)
	LinearLayout ll_input_phone;
	@BindView(R.id.ll_phone_code)
	LinearLayout ll_phone_code;

	@BindView(R.id.ed_phone)
	EditTextWithDel ed_phone;
	@BindView(R.id.tv_phone)
	TextView tv_phone;
	@BindView(R.id.pb_code)
	PaperButton pb_code;
	@BindView(R.id.ed_code)
	EditTextWithDel ed_code;
	MyCountTimer timer;
	private static boolean isCheck = false;

	private String key;
	private String newKey;
	private String phone;
	private String newPhone;

	public static ChangePhoneFragment newInstance(String key, String phone) {
		ChangePhoneFragment changePhoneFragment = new ChangePhoneFragment();
		changePhoneFragment.key = key;
		changePhoneFragment.phone = phone;
		return changePhoneFragment;
	}


	@Override
	public void initView(View rootView) {
		tv_header_center.setText("手机验证");
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
		CommUtil.initToolBar(getActivity(), iv_header_left, iv_header_right);
	}

	@Override
	public void initData() {

	}


	@OnClick({R.id.bt_next, R.id.pb_code, R.id.bt_login})
	public void onClick(View view) {
		newPhone = ed_phone.getText().toString().trim();

		switch (view.getId()) {
			case R.id.bt_next:
				if (XEmptyUtils.isSpace(newPhone)) {
					XToast.error("请输入手机号");
				} else {
					if (!XRegexUtils.checkPhone(newPhone)) {
						XToast.error("手机号格式不对！");
					} else {
						checkPhone(newPhone);
					}
				}
				break;
			case R.id.pb_code:
				if (isCheck) {
				} else {
					getCode(newPhone);

				}
				break;

			case R.id.bt_login:
				String code = ed_code.getText().toString().trim();
				if (XEmptyUtils.isSpace(code)) {
					XToast.error("验证码未输入");
				} else {
					checkPhone(newPhone, code);
				}
				break;
		}

	}

	private void checkPhone(String phone) {

		OkGo.<LzyResponse<CheckBean>>post(Urls.URL_EXIST_PHONE)
				.tag(this)
				.params("phone", phone)
				.execute(new JsonCallback<LzyResponse<CheckBean>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<CheckBean>> response) {
						if (response.body().getCode() == Constants.HTTP_111||response.body().getCode()==Constants.HTTP_101) {
							ll_input_phone.setVisibility(View.GONE);
							ll_phone_code.setVisibility(View.VISIBLE);
							tv_phone.setText(phone);
							pb_code.performClick();
						} else if (response.body().getCode() == Constants.HTTP_110) {
							XToast.error("该手机号已注册！");
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

	/**
	 * 检查验证码
	 *
	 * @param phone
	 * @param code
	 */
	private void checkPhone(String phone, String code) {
		OkGo.<LzyResponse<CheckBean>>post(Urls.URL_CHECK_SMS)
				.tag(this)
				.params("phone", phone)
				.params("mobileSMS", code)
				.execute(new DialogCallback<LzyResponse<CheckBean>>(getActivity(), "验证中...") {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<CheckBean>> response) {
						if (HttpUtil.handleResponse(getContext(), response.body())) {
							newKey = response.body().getDatas().getKey();
							if (XEmptyUtils.isEmpty(key) || XEmptyUtils.isSpace(key)) {
								bindPhone();
							} else {
								changePhone();

							}
						}

					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<CheckBean>> response) {
						HttpUtil.handleError(mContext, response);
					}
				});
	}

	/**
	 * 绑定手机号
	 */
	private void bindPhone() {
		OkGo.<LzyResponse<CheckBean>>post(Urls.URL_UPDATE_USER)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("id", MyShopApplication.userId)
				.params("phone", newPhone)
				.params("key", newKey)
				.execute(new DialogCallback<LzyResponse<CheckBean>>(getActivity(), "绑定中...") {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<CheckBean>> response) {
						if (HttpUtil.handleResponse(getContext(), response.body())) {
							XToast.info("绑定成功");
							_mActivity.onBackPressed();
						}

					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<CheckBean>> response) {
						HttpUtil.handleError(mContext, response);
					}
				});
	}

	/**
	 * 更改手机号
	 */
	private void changePhone() {

		OkGo.<LzyResponse<CheckBean>>post(Urls.URL_UPDATE_USER)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("id", MyShopApplication.userId)
				.params("phone", phone)
				.params("newPhone", newPhone)
				.params("key", key)
				.params("newKey", newKey)
				.execute(new DialogCallback<LzyResponse<CheckBean>>(getActivity(), "修改中...") {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<CheckBean>> response) {
						if (HttpUtil.handleResponse(getContext(), response.body())) {
							XToast.info("修改成功");
							_mActivity.onBackPressed();
						}

					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<CheckBean>> response) {
						HttpUtil.handleError(mContext, response);
					}
				});
	}


	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_mine_change_phone;
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
