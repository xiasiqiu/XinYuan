package com.xinyuan.xyshop.ui.mine.pro;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.log.XLog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/26.
 */

public class AccountFragment extends BaseFragment {
	private static final String USER_ID = "USER_ID";
	private int mNumber;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.toolbar_iv)
	Toolbar msg_toolbar;

	public static AccountFragment newInstance() {
		AccountFragment fragment = new AccountFragment();
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_account;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		Bundle args = getArguments();
		if (args != null) {
			mNumber = args.getInt(USER_ID);
		}
	}

	@Override
	public void initView() {
		if (msg_toolbar != null) {
			XLog.v("账户加载Tooolbar");
			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
			tv_header_center.setText("账户余额");
		}



	}

	@OnClick({R.id.rl_acctount_withdrawals, R.id.rl_acctount_recharge})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.rl_acctount_recharge:
				start(AccountConFragment.newInstance("余额充值"));
				break;
			case R.id.rl_acctount_withdrawals:
				start(AccountConFragment.newInstance("余额提现"));
				break;
		}


	}
}
