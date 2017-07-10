package com.xinyuan.xyshop.ui.mine.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.PluralsRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.AddressAdapter;
import com.xinyuan.xyshop.adapter.AreaAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.AddressBean;
import com.xinyuan.xyshop.entity.AreaBean;
import com.xinyuan.xyshop.entity.AreaList;
import com.xinyuan.xyshop.entity.BrandList;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.util.JsonUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/10.
 */

public class AddressContentFragment extends BaseFragment {

	@BindView(R.id.toolbar_tv)
	Toolbar toolbar_tv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.lodingView)
	XLoadingView lodingView;

	@BindView(R.id.rv_address_content)
	RecyclerView rv_address_content;
	AreaAdapter areaAdapter;
	private List<AreaBean> choseArea = new ArrayList<>();

	static int falg = 0;
	private static String url = "";
	List<AreaBean> areaBeen = new ArrayList<>();
	List<AreaBean> areaBeen1 = new ArrayList<>();
	List<AreaBean> areaBeen2 = new ArrayList<>();
	List<AreaBean> areaBeen3 = new ArrayList<>();
	List<AreaBean> nowArea = new ArrayList<>();

	private static final String ARG_TITLE = "arg_area";

	public static AddressContentFragment newInstance(int falg) {
		AddressContentFragment fragment = new AddressContentFragment();
		fragment.falg = falg;
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_address_content;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}


	@Override
	public void onLazyInitView(@Nullable Bundle savedInstanceState) {


	}


	private void getData(String url) {
		lodingView.showLoading();
		OkGo.get(url)
				.execute(new StringCallback() {
					@Override
					public void onSuccess(String s, Call call, Response response) {
						AreaList data = JsonUtil.toBean(s, AreaList.class);
						nowArea = data.getAreaList();
						XLog.list(nowArea);
						if (falg == 0 || falg == 3) {
							setView();
						} else {
							areaAdapter.setNewData(nowArea);
							lodingView.showContent();
						}


					}

					@Override
					public void onError(Call call, Response response, Exception e) {

					}
				});
	}

	private void setView() {

		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(1);
		this.rv_address_content.setLayoutManager(layoutManager);
		areaAdapter = new AreaAdapter(R.layout.fragment_address_content_item, nowArea);
		this.rv_address_content.setAdapter(areaAdapter);


		areaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
				switch (falg) {
					case 0:
						choseArea.add(nowArea.get(position));
						falg++;
						url = Urls.URL_AREA1;
						getData(url);
						break;
					case 1:
						choseArea.add(nowArea.get(position));
						falg++;
						url = Urls.URL_AREA2;
						getData(url);
						break;
					case 2:
						choseArea.add(nowArea.get(position));
						EventBus.getDefault().post(choseArea);
						_mActivity.onBackPressed();
						break;
					case 3:
						choseArea.add(nowArea.get(position));
						EventBus.getDefault().post(choseArea);
						_mActivity.onBackPressed();
						break;
				}


			}
		});
		lodingView.showContent();
	}

	@Override
	public void initView() {

		if (toolbar_tv != null) {
			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_tv);
			tv_header_center.setText("选择地址");
		}


		if (falg == 0) {
			getData(Urls.URL_AREA);
		} else if (falg == 3) {
			getData(Urls.URL_AREA3);
		}

	}


	@Override
	public void onStop() {
		falg = 0;
		super.onStop();
	}


}
