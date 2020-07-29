package com.xinyuan.xyshop.ui.mine.pro;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.AccountBean;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.XToast;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * Created by fx on 2017/6/26.
 */

public class AccountFragment extends BaseFragment {
	private static final String USER_ID = "USER_ID";
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.toolbar_iv)
	Toolbar msg_toolbar;
	@BindView(R.id.tv_money)
	TextView tv_money;

	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	@BindView(R.id.iv_header_right)
	BGABadgeImageView iv_header_right;

	public static AccountFragment newInstance() {
		AccountFragment fragment = new AccountFragment();
		return fragment;
	}

	@Override
	public void initView(View rootView) {
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
		tv_header_center.setText("账户余额");
		CommUtil.initToolBar(getActivity(), iv_header_left, iv_header_right);

	}

	@Override
	public void initData() {
		OkGo.<LzyResponse<AccountBean>>post(Urls.URL_USER_ACCOUNT)
				.headers("token", MyShopApplication.Token)
				.params("id", MyShopApplication.userId)
				.execute(new JsonCallback<LzyResponse<AccountBean>>() {
					@Override
					public void onSuccess(Response<LzyResponse<AccountBean>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							if (XEmptyUtils.isEmpty(response.body().datas.getBalanue())) {
								tv_money.setText("0.00");

							} else {
								tv_money.setText(response.body().datas.getBalanue());
							}
						}
					}

					@Override
					public void onError(Response<LzyResponse<AccountBean>> response) {
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
		return R.layout.fragment_account;
	}


	@OnClick({R.id.rl_acctount_withdrawals, R.id.rl_acctount_recharge})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.rl_acctount_recharge:
				XToast.info("功能暂未开通");
				//start(AccountConFragment.newInstance("余额充值"));
				break;
			case R.id.rl_acctount_withdrawals:
				XToast.info("功能暂未开通");
				//start(AccountConFragment.newInstance("余额提现"));
				break;
		}


	}

	@Override
	public void onSupportVisible() { //当fragment可见时，检查是否有新消息
		if (MyShopApplication.IsNewMsg) {
			iv_header_right.showCirclePointBadge();
		} else {
			iv_header_right.hiddenBadge();
		}
		super.onSupportVisible();
	}

}
