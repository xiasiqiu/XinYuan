package com.xinyuan.xyshop.ui.mine.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.youth.xframe.utils.log.XLog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginFragment extends BaseFragment {


	@BindView(R.id.bt_login)
	Button bt_login;
	private int resultCode = 0;

	@BindView(R.id.toolbar_tv)
	Toolbar toolbar_tv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;

	private static int loginType = 0;

	public static LoginFragment newInstance() {
		LoginFragment fragment = new LoginFragment();
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_login;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		tv_header_center.setText("登录");

	}

	@Override
	public void onLazyInitView(@Nullable Bundle savedInstanceState) {


	}

	private static final int REQ_USER = 100;

	@OnClick({R.id.tv_fast_login, R.id.tv_forget_pass})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.tv_fast_login:
				startForResult(LoginFastFragment.newInstance(), REQ_USER);
				break;
			case R.id.tv_forget_pass:
				startForResult(ForgetFragment.newInstance(), REQ_USER);

				break;

		}

	}

	static final String KEY_RESULT_TITLE = "logininfo";

	@Override
	public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
		super.onFragmentResult(requestCode, resultCode, data);
		String s = data.getString(KEY_RESULT_TITLE);
		XLog.v("登录信息:" + s);
		if (requestCode == REQ_USER && resultCode == RESULT_OK && data != null) {


		}
	}

}

