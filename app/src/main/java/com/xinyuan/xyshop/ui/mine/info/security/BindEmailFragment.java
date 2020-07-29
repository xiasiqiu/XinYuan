package com.xinyuan.xyshop.ui.mine.info.security;

import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.callback.DialogCallback;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
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
 * Created by fx on 2017/9/14.
 * 绑定或解绑邮箱fragment
 */

public class BindEmailFragment extends BaseFragment {
	@BindView(R.id.toolbar_tv)
	Toolbar toolbar_tv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;

	@BindView(R.id.ll_input_email)
	LinearLayout ll_input_email;    //绑定邮箱布局
	@BindView(R.id.ed_bind_email)
	EditTextWithDel ed_bind_email;   //绑定输入邮箱
	@BindView(R.id.ed_bind_code)
	EditTextWithDel ed_bind_code;        //绑定输入验证码
	@BindView(R.id.pb_bind_code)
	PaperButton pb_bind_code;                //绑定点击发送验证码
	@BindView(R.id.tv_unbind_notice)
	TextView tv_unbind_notice;          //解绑邮箱提示
	@BindView(R.id.ll_unbind_input)
	LinearLayout ll_unbind_input;    //解绑邮箱布局
	@BindView(R.id.ed_unbind_code)
	EditTextWithDel ed_unbind_code;        //解绑输入验证码
	@BindView(R.id.pb_unbind_code)
	PaperButton pb_unbind_code;                //解绑点击发送验证码


	private static boolean isBindCheck = false;
	private static boolean isUnBindCheck = false;
	MyCountTimer timer;
	private String email;//有则解绑，无则绑定
	private int type; //1：绑定 2：解绑

	public static BindEmailFragment newInstance(String email) {
		BindEmailFragment fragment = new BindEmailFragment();
		fragment.email = email;
		return fragment;
	}

	@Override
	public void initView(View rootView) {
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_tv);
		tv_header_center.setText("邮箱绑定");
		CommUtil.initToolBar(getActivity(), iv_header_left, null);
		if (!XEmptyUtils.isSpace(email) && XRegexUtils.checkEmail(email)) {
			ll_unbind_input.setVisibility(View.VISIBLE);
			tv_unbind_notice.setText("您当前绑定的邮箱地址：" + email + "请点击发送验证码，我们将发送一个验证码至当前邮箱，输入当前验证码以完成解绑\"");
			type = 2;
		} else {
			ll_input_email.setVisibility(View.VISIBLE);
			type = 1;

		}
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
		return R.layout.fragment_bind_email;
	}

	@OnClick({R.id.pb_bind_code, R.id.pb_unbind_code, R.id.bt_bind})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.pb_bind_code:
				if (XEmptyUtils.isSpace(ed_bind_email.getText().toString().trim())) {


					XToast.error("请输入邮箱地址");


				} else if (!XRegexUtils.checkEmail(ed_bind_email.getText().toString().trim())) {


					XToast.error("邮箱地址格式不正确");
				} else {
					if (isBindCheck) {
					} else {
						timer = new MyCountTimer(60000, 1000);
						timer.start();
						getEmailCode(ed_bind_email.getText().toString().trim());
						isBindCheck = true;
					}

				}

				break;
			case R.id.pb_unbind_code:

				if (isUnBindCheck) {
				} else {
					timer = new MyCountTimer(60000, 1000);
					timer.start();
					getEmailCode(email);
					isUnBindCheck = true;
				}


				break;
			case R.id.bt_bind:
				if (type == 2) {

					if (XEmptyUtils.isSpace(ed_unbind_code.getText().toString().trim())) {
						XToast.error("请输入验证码");
					} else {
						UnBindEmail(email, ed_unbind_code.getText().toString().trim());
					}
				} else {

					if (XEmptyUtils.isSpace(ed_bind_email.getText().toString().trim())) {
						XToast.error("请输入邮箱地址");

					} else if (!XRegexUtils.checkEmail(ed_bind_email.getText().toString().trim())) {
						XToast.error("邮箱地址格式不正确");


					} else if (XEmptyUtils.isSpace(ed_bind_code.getText().toString().trim())) {
						XToast.error("请输入验证码");

					} else {
						BindEmail(ed_bind_email.getText().toString().trim(), ed_bind_code.getText().toString().trim());
					}
				}


				break;
		}

	}

	/**
	 * 绑定邮箱
	 *
	 * @param email
	 * @param code
	 */
	private void BindEmail(String email, String code) {
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_EMAIL_BIND)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("email", email)
				.params("emailCode", code)
				.execute(new DialogCallback<LzyResponse<TokenBean>>(getActivity(), "绑定中...") {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							XToast.info("绑定成功");
							_mActivity.onBackPressed();
						} else {
							XToast.error("绑定失败，请稍后重试");
						}

					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						HttpUtil.handleError(mContext, response);

					}
				});
	}

	/**
	 * 解绑邮箱
	 *
	 * @param email
	 * @param code
	 */
	private void UnBindEmail(String email, String code) {
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_EMAIL_UNBIND)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("email", email)
				.params("emailCode", code)
				.execute(new DialogCallback<LzyResponse<TokenBean>>(getActivity(), "绑定中...") {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							XToast.info("绑定成功");
							_mActivity.onBackPressed();
						} else {
							XToast.error("绑定失败，请稍后重试");
						}

					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						HttpUtil.handleError(mContext, response);

					}
				});
	}

	/**
	 * 获取邮箱验证码
	 *
	 * @param email
	 */
	private void getEmailCode(String email) {
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_EMAIL_CODE)
				.tag(this)
				.params("email", email)
				.execute(new DialogCallback<LzyResponse<TokenBean>>(getActivity(), "发送中...") {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {

						if (HttpUtil.handleResponse(mContext, response.body())) {
							XToast.info("发送成功，请在邮箱查收");
						} else {
							XToast.error("发送失败，请稍后重试");
						}

					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
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
			if (type == 2) {

				pb_unbind_code.setText((millisUntilFinished / 1000) + "秒后重发");
				pb_unbind_code.setClickable(false);
			} else {
				pb_bind_code.setText((millisUntilFinished / 1000) + "秒后重发");
				pb_bind_code.setClickable(false);
			}

		}

		@Override
		public void onFinish() {
			if (type == 2) {
				pb_unbind_code.setText("重新发送");
				pb_unbind_code.setClickable(true);
				isUnBindCheck = false;

			} else {
				pb_bind_code.setText("重新发送");
				pb_bind_code.setClickable(true);
				isBindCheck = false;
			}

		}
	}
}
