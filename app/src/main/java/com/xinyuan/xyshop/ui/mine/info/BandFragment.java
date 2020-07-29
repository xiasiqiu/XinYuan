package com.xinyuan.xyshop.ui.mine.info;

import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.PaperButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fx on 2017/6/27.
 * 绑定银行卡fragment
 */

public class BandFragment extends BaseFragment {
	@BindView(R.id.toolbar_iv)
	Toolbar msg_toolbar;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.iv_header_right)
	ImageView iv_header_right;
	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;

	@BindView(R.id.pb_code)
	PaperButton pb_code;
	BandFragment.MyCountTimer timer;
	private static boolean isCheck = false;

	public static BandFragment newInstance() {
		BandFragment fragment = new BandFragment();
		return fragment;
	}

	@OnClick({R.id.pb_code})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.pb_code:
				if (isCheck) {

				} else {
					timer = new MyCountTimer(6000, 1000);
					timer.start();
					isCheck = true;
				}


				break;
		}
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
	public void initView(View rootView) {
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
		tv_header_center.setText("绑定银行卡");
		CommUtil.initToolBar(getActivity(), iv_header_left, iv_header_right);

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
		return R.layout.fragment_band;

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (timer != null) {
			timer.cancel();
		}
	}
}
