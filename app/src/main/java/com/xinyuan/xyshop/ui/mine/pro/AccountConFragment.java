package com.xinyuan.xyshop.ui.mine.pro;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;

import butterknife.BindView;
import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * Created by Administrator on 2017/6/26.
 */

public class AccountConFragment extends BaseFragment {
	private String mTitle;
	@BindView(R.id.tv_account_re_hint)
	TextView tv_account_re_hint;
	@BindView(R.id.rl_account_re)
	RelativeLayout rl_account_re;

	@BindView(R.id.ll_account_withdrawals)
	LinearLayout ll_account_withdrawals;


	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.toolbar_iv)
	Toolbar msg_toolbar;

	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	@BindView(R.id.iv_header_right)
	BGABadgeImageView iv_header_right;

	public static AccountConFragment newInstance(String title) {
		AccountConFragment fragment = new AccountConFragment();
		fragment.mTitle = title;
		return fragment;
	}


	@Override
	public void initView(View rootView) {
		tv_header_center.setText(mTitle);
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
		CommUtil.initToolBar(getActivity(), iv_header_left, iv_header_right);
		if (mTitle.equals("余额充值")) {
			tv_account_re_hint.setVisibility(View.VISIBLE);
			rl_account_re.setVisibility(View.VISIBLE);
		} else {
			ll_account_withdrawals.setVisibility(View.VISIBLE);
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
		return R.layout.fragment_account_con;
	}


}
