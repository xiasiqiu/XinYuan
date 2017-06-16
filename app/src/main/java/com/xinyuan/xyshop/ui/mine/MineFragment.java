package com.xinyuan.xyshop.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.ui.mine.order.OrderActivity;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.log.XLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fx on 2017/5/2 0002.
 */

public class MineFragment extends BaseFragment {

	private Toolbar toolbar;

	private static int num = 1;
	@BindView(R.id.bt_more_order)
	Button bt_more_order;

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

		num++;
	}

	@OnClick({R.id.bt_more_order})
	public void onClick(View v) {
		switch (v.getId()) {

			case R.id.bt_more_order:

				Intent intent = new Intent(getActivity(), OrderActivity.class);
				startActivity(intent);

				break;
		}

	}

}
