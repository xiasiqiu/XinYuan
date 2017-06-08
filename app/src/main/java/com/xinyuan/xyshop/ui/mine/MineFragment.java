package com.xinyuan.xyshop.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.log.XLog;

import butterknife.ButterKnife;

/**
 * Created by fx on 2017/5/2 0002.
 */

public class MineFragment extends BaseFragment {

	private Toolbar toolbar;

	private static int num=1;

	@Override
	public int getLayoutId() {
		return R.layout.fragment_mine;
	}

	@Override
	public void initData(@Nullable Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		ButterKnife.bind(this, getView());
//		toolbar= (Toolbar) getView().findViewById(R.id.mine_toolbar);
//		if(num==1){
//			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
//			SystemBarHelper.setHeightAndPadding(getActivity(), toolbar);
//
//		}
		XLog.v("我的页面切换"+num);
		num++;
	}
}
