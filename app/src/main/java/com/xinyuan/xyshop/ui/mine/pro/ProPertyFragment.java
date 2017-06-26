package com.xinyuan.xyshop.ui.mine.pro;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.even.StartBrotherEvent;
import com.xinyuan.xyshop.util.SystemBarHelper;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/26.
 */

public class ProPertyFragment extends BaseFragment {
	@BindView(R.id.toolbar_iv)
	Toolbar msg_toolbar;

	@Override
	public void onEnterAnimationEnd(Bundle saveInstanceState) {
		// 这里设置Listener、各种Adapter、请求数据等等
		initLazyView();
	}

	private void initLazyView() {

	}

	public static ProPertyFragment newInstance() {
		Bundle args = new Bundle();
		ProPertyFragment fragment = new ProPertyFragment();
		fragment.setArguments(args);
		return fragment;
	}


	@Override
	public int getLayoutId() {
		return R.layout.activity_property;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
		EventBus.getDefault().register(this);
	}

	@OnClick({R.id.rl_pro_account, R.id.rl_pro_coupon, R.id.rl_pro_redpacke, R.id.rl_pro_credit})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.rl_pro_account:
				EventBus.getDefault().post(new StartBrotherEvent(AccountFragment.newInstance()));
				break;
			case R.id.rl_pro_coupon:
				EventBus.getDefault().post(new StartBrotherEvent(CouponFragment.newInstance()));
				break;
			case R.id.rl_pro_redpacke:
				EventBus.getDefault().post(new StartBrotherEvent(CouponFragment.newInstance()));
				break;
			case R.id.rl_pro_credit:
				EventBus.getDefault().post(new StartBrotherEvent(CreditFragment.newInstance()));
				break;
		}
	}
}
