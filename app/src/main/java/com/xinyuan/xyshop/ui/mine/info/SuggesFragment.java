package com.xinyuan.xyshop.ui.mine.info;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.dialog.color.ColorDialog;
import com.youth.xframe.widget.XToast;

import butterknife.BindView;
import butterknife.OnClick;

import static com.xinyuan.xyshop.util.CommUtil.getInAnimationTest;
import static com.xinyuan.xyshop.util.CommUtil.getOutAnimationTest;

/**
 * Created by Administrator on 2017/6/27.
 */

public class SuggesFragment extends BaseFragment {
	@BindView(R.id.toolbar_iv)
	Toolbar msg_toolbar;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;

	public static SuggesFragment newInstance() {
		SuggesFragment fragment = new SuggesFragment();
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_sugges;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		if (msg_toolbar != null) {
			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
			tv_header_center.setText("用户设置");
		}
	}

	@OnClick(R.id.bt_submit)
	public void onClick() {
		ColorDialog dialog = new ColorDialog(getContext());
		dialog.setTitle("提交建议");
		dialog.setAnimationEnable(true);
		dialog.setContentText("确定提交意见反馈？");
		dialog.setAnimationIn(getInAnimationTest(getActivity()));
		dialog.setAnimationOut(getOutAnimationTest(getActivity()));
		dialog.setPositiveListener("确定", new ColorDialog.OnPositiveListener() {
			@Override
			public void onClick(ColorDialog dialog) {

				dialog.dismiss();
				XToast.info("提交成功");
				_mActivity.onBackPressed();
			}
		})
				.setNegativeListener("取消", new ColorDialog.OnNegativeListener() {
					@Override
					public void onClick(ColorDialog dialog) {
						dialog.dismiss();
					}
				}).show();
	}


}
