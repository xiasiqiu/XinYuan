package com.xinyuan.xyshop.ui.goods.detail.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.even.GoodServiceEvent;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * Created by fx on 2017/5/19.
 * 商品详情售后界面
 */

public class GoodsServiceFragment extends BaseFragment {
	@BindView(R.id.tv_good_service)
	TextView tv_good_service;
	@BindView(R.id.iv_service_img)
	ImageView iv_service_img;

	@Override
	public void initView(View rootView) {

	}

	@Override
	public void initData() {

	}

	@Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
	public void setService(GoodServiceEvent goodServiceEvent) {
		XLog.v(goodServiceEvent.toString());
		if (!XEmptyUtils.isEmpty(goodServiceEvent)) {
			tv_good_service.setText(goodServiceEvent.getServiceText());
			GlideImageLoader.setUrlImg(getContext(), goodServiceEvent.getServiceImg(), iv_service_img);
		}
	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_good_service;
	}

	@Override
	public void onStart() {
		registerEventBus(this);
		super.onStart();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unregisterEventBus(this);
	}
}
