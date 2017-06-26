package com.xinyuan.xyshop.ui.mine.pro;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.CreditAdapter;
import com.xinyuan.xyshop.adapter.OrderAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.CreditBean;
import com.xinyuan.xyshop.ui.mine.info.UserInfoFragment;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/26.
 */

public class CreditFragment extends BaseFragment {
	@BindView(R.id.rv_credit)
	RecyclerView rv_credit;

	CreditAdapter adapter;
	@BindView(R.id.toolbar_iv)
	Toolbar msg_toolbar;

	public static CreditFragment newInstance() {
		CreditFragment fragment = new CreditFragment();
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_credit;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
		XLog.v("积分中心");
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(1);
		this.rv_credit.setLayoutManager(layoutManager);
		List<CreditBean> list = new ArrayList<>();
		list.add(new CreditBean("雅诗兰黛 红石榴泡沫洁面乳25ml清洁补水保湿", 255654784, "2017-05-14 12:06:12", 5, 0));
		list.add(new CreditBean("雅诗兰黛 红石榴泡沫洁面乳25ml清洁补水保湿", 255654784, "2017-05-14 12:06:12", 4, 1));
		list.add(new CreditBean("雅诗兰黛 红石榴泡沫洁面乳25ml清洁补水保湿", 255654784, "2017-05-14 12:06:12", 6, 0));
		list.add(new CreditBean("雅诗兰黛 红石榴泡沫洁面乳25ml清洁补水保湿", 255654784, "2017-05-14 12:06:12", 2, 1));
		list.add(new CreditBean("雅诗兰黛 红石榴泡沫洁面乳25ml清洁补水保湿", 255654784, "2017-05-14 12:06:12", 5, 1));
		list.add(new CreditBean("雅诗兰黛 红石榴泡沫洁面乳25ml清洁补水保湿", 255654784, "2017-05-14 12:06:12", 7, 0));
		list.add(new CreditBean("雅诗兰黛 红石榴泡沫洁面乳25ml清洁补水保湿", 255654784, "2017-05-14 12:06:12", 4, 1));
		this.adapter = new CreditAdapter(R.layout.fragment_credit_item, list);
		this.rv_credit.setAdapter(adapter);

		View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_credit_top, (ViewGroup) rv_credit.getParent(), false);
		adapter.addHeaderView(view);
	}
}
