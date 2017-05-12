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

import butterknife.ButterKnife;

/**
 * Created by fx on 2017/5/2 0002.
 */

public class MineFragment extends BaseFragment {

	private Toolbar toolbar;
	@Override
	protected void lazyLoad() {

	}

	@Override
	public View initLayout(LayoutInflater inflater, ViewGroup container, boolean b) {
		View rootView = inflater.inflate(R.layout.fragment_mine, null);
		toolbar= (Toolbar) rootView.findViewById(R.id.mine_toolbar);
		SystemBarHelper.immersiveStatusBar(getActivity(),0);
		SystemBarHelper.setHeightAndPadding(getActivity(), toolbar);
		ButterKnife.bind(this, rootView);
		return rootView;
	}

	@Override
	protected void initData(@Nullable Bundle savedInstanceState) {

	}
}
