package com.xinyuan.xyshop.ui.shopcar;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.base.XFragment;
import com.youth.xframe.utils.log.XLog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fx on 2017/5/2 0002.
 */

public class ShopCarFragment extends BaseFragment {
	@BindView(R.id.category_toolbar)
	Toolbar toolbar;
	private static int num=1;
	@Override
	public int getLayoutId() {
		return R.layout.fragment_shopcar;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		ButterKnife.bind(this, getView());
		if(num==1){
			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(getActivity(), toolbar);


		}
		XLog.v("购物车页面切换"+num);
		num++;
	}





}
