package com.xinyuan.xyshop.ui.mine.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.CreditAdapter;
import com.xinyuan.xyshop.adapter.FooterAdapter;
import com.xinyuan.xyshop.adapter.GoodsGridAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.bean.LzyResponse;
import com.xinyuan.xyshop.callback.JsonConvert;
import com.xinyuan.xyshop.entity.BaseBean;
import com.xinyuan.xyshop.entity.BrandList;
import com.xinyuan.xyshop.entity.CreditBean;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.entity.TUserHistoryBean;
import com.xinyuan.xyshop.entity.TestUserInfo;
import com.xinyuan.xyshop.entity.UserHistoryBean;
import com.xinyuan.xyshop.entity.UserInfo;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.util.JsonUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/26.
 */

public class FooterFragment extends BaseFragment {
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.toolbar_tv)
	Toolbar toolbar_tv;
	@BindView(R.id.rv_footer)
	RecyclerView rv_footer;

	private FooterAdapter adapter;
	@BindView(R.id.lodingView)
	XLoadingView lodingView;
	public static FooterFragment newInstance() {
		FooterFragment fragment = new FooterFragment();
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_footer;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		OkGo.get(Urls.URL_USER_FOOT)
				.execute(new StringCallback() {
					@Override
					public void onSuccess(String s, Call call, Response response) {
						TUserHistoryBean brands = JsonUtil.toBean(s, TUserHistoryBean.class);
						List<UserHistoryBean> beans = brands.getHistoryBean();
						XLog.v("我的足迹"+beans.toString());

						initList(beans);

					}
				});
	}

	@Override
	public void initView() {

		if (toolbar_tv != null) {
			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_tv);
			tv_header_center.setText("我的足迹");
		}
		lodingView.showLoading();
	}

	private void initList(List<UserHistoryBean> beans) {

		GridLayoutManager layoutManager = new GridLayoutManager(this.context, 3, 1, false);
		this.rv_footer.setLayoutManager(layoutManager);
		this.adapter = new FooterAdapter(R.layout.fragment_footer_item, beans);
		this.rv_footer.setAdapter(adapter);
		lodingView.showContent();
	}

	@Override
	public void onLazyInitView(@Nullable Bundle savedInstanceState) {



	}


}
