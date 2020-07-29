package com.xinyuan.xyshop.ui.mine.info.address;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.AreaAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.even.AddressEven;
import com.xinyuan.xyshop.even.AreaEven;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.bean.AreaBean;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.loadingview.XLoadingView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by fx on 2017/7/10.
 * 地区选择fragment
 */

public class AreaContentFragment extends BaseFragment {

	@BindView(R.id.toolbar_tv)
	Toolbar toolbar_tv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	@BindView(R.id.lodingView)
	XLoadingView lodingView;

	@BindView(R.id.rv_address_content)
	RecyclerView rv_address_content;
	AreaAdapter areaAdapter;


	static int falg = 0;
	private static String url = "";
	List<AreaBean> nowArea = new ArrayList<>();
	private List<AreaBean> choseArea = new ArrayList<>();
	private static final String ARG_TITLE = "arg_area";

	public static AreaContentFragment newInstance(int falg) {
		AreaContentFragment fragment = new AreaContentFragment();
		fragment.falg = falg;
		return fragment;
	}


	private void setView() {
		areaAdapter = new AreaAdapter(R.layout.fragment_address_content_item, nowArea);
		areaAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
		this.rv_address_content.setAdapter(areaAdapter);


		areaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

				choseArea.add(nowArea.get(position));
				getData(String.valueOf(nowArea.get(position).getArea_id()));
				XLog.list(choseArea);
			}
		});

		lodingView.showContent();
	}

	private static String areaId;


	@Override
	public void initView(View rootView) {
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_tv);
		CommUtil.initToolBar(getActivity(), iv_header_left, null);

		tv_header_center.setText("选择地址");
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(1);
		this.rv_address_content.setLayoutManager(layoutManager);
		getData(areaId);
	}

	private void getData(String areaId) {

		OkGo.<LzyResponse<List<AreaBean>>>post(Urls.URL_USER_AREA)
				.tag(this)
				.params("areaId", areaId)
				.execute(new JsonCallback<LzyResponse<List<AreaBean>>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<List<AreaBean>>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							nowArea = response.body().getDatas();
							if (XEmptyUtils.isEmpty(response.body().datas)) {
								String str = "";
								for (AreaBean s : choseArea) {
									str += s.getAreaName();
								}
								XLog.v("发送地址");
								_mActivity.onBackPressed();
								EventBus.getDefault().post(new AreaEven(choseArea.get(choseArea.size() - 1).getArea_id(), str)); //返回地址列表数据

							}
							if (XEmptyUtils.isEmpty(areaAdapter)) {
								setView();
							} else {
								rv_address_content.scrollToPosition(0);
								areaAdapter.setNewData(nowArea);
							}


						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<List<AreaBean>>> response) {
						HttpUtil.handleError(mContext, response);
					}
				});
	}

	@Override
	public void initData() {
	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_address_content;

	}

	@Override
	public void onStop() {
		falg = 0;
		super.onStop();
	}


}
