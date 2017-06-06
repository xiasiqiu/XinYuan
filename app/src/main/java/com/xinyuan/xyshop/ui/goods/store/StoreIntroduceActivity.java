package com.xinyuan.xyshop.ui.goods.store;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.util.SystemBarHelper;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/6.
 */

public class StoreIntroduceActivity extends BaseActivity {

	@BindView(R.id.store_introduce_toolbar)
	Toolbar store_toolbar;


	@Override
	public int getLayoutId() {
		return R.layout.activity_sotre_introduce;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {

//		setSupportActionBar(store_toolbar);
//		SystemBarHelper.immersiveStatusBar(this); //设置状态栏透明
//		SystemBarHelper.setHeightAndPadding(this, store_toolbar);
	}
}
