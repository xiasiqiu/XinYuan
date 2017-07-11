package com.xinyuan.xyshop.ui.mine.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.common.ShopHelper;
import com.xinyuan.xyshop.even.LoginPageEvent;
import com.xinyuan.xyshop.ui.goods.GoodBusBean;
import com.youth.xframe.cache.XCache;
import com.youth.xframe.utils.log.XLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginFragment extends BaseFragment {


	@BindView(R.id.toolbar_tv)
	Toolbar toolbar_tv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.tv_header_right)
	TextView tv_header_right;
	@BindView(R.id.bt_login)
	Button bt_login;


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
		tv_header_right.setText("注册");
		tv_header_right.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				start(RegFragment.newInstance());
			}
		});
	}

	@Override
	public void onLazyInitView(@Nullable Bundle savedInstanceState) {


	}

	private static final int REQ_USER = 100;

	@OnClick({R.id.tv_fast_login, R.id.tv_forget_pass, R.id.bt_login, R.id.iv_login_wechat, R.id.iv_login_qq, R.id.iv_login_weibo})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.tv_fast_login:
				start(LoginFastFragment.newInstance());
				break;
			case R.id.tv_forget_pass:
				start(ForgetFragment.newInstance());
				break;
			case R.id.bt_login:
				getActivity().finish();
				MyShopApplication.isLogin = true;
				EventBus.getDefault().post(new LoginPageEvent("Login", true));
				break;
			case R.id.iv_login_qq:
				getActivity().finish();
				MyShopApplication.isLogin = true;
				EventBus.getDefault().post(new LoginPageEvent("QQ", true));
				break;
			case R.id.iv_login_wechat:
				getActivity().finish();
				MyShopApplication.isLogin = true;
				EventBus.getDefault().post(new LoginPageEvent("weChat", true));
				break;
			case R.id.iv_login_weibo:
				getActivity().finish();
				MyShopApplication.isLogin = true;
				EventBus.getDefault().post(new LoginPageEvent("weiBo", true));
				break;


		}
	}

	@Override
	public void onStart() {
		super.onStart();
		EventBus.getDefault().register(this);

	}

	@Override
	public void onStop() {
		EventBus.getDefault().unregister(this);
		super.onStop();
	}

	@Subscribe
	public void page(LoginPageEvent eventBus) {

	}
}

