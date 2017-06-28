package com.xinyuan.xyshop.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.BrandAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.BrandBean;
import com.xinyuan.xyshop.entity.BrandList;
import com.xinyuan.xyshop.entity.MySection;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.BrandModel;
import com.xinyuan.xyshop.util.JsonUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.WaveSideBarView;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/28.
 */

public class BrandFragment extends BaseFragment {
	@BindView(R.id.toolbar_iv)
	Toolbar toolbar_iv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.brand_loadingView)
	XLoadingView loadingView;
	@BindView(R.id.rv_brand)
	RecyclerView rv_brand;
	@BindView(R.id.side_view)
	WaveSideBarView mSideBarView;


	private List<BrandBean> AdList;
	private List<BrandModel.Brand> AllBrandList;
	BrandAdapter brandAdapter;


	public static BrandFragment newInstance() {
		BrandFragment fragment = new BrandFragment();
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_brand;
	}


	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		if (toolbar_iv != null) {
			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_iv);
			tv_header_center.setText(R.string.brandtitle);
		}
		loadingView.setOnRetryClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onLazyInitView(null);
			}
		});
	}


	@Override
	public void onLazyInitView(@Nullable Bundle savedInstanceState) {
		loadingView.showLoading();
		OkGo.get(Urls.URL_BRAND)
				.execute(new StringCallback() {
					@Override
					public void onSuccess(String s, Call call, Response response) {
						BrandList brands = JsonUtil.toBean(s, BrandList.class);
						inRV(brands);
					}

					@Override
					public void onError(Call call, Response response, Exception e) {
						loadingView.showError();
					}
				});

	}

	private void inRV(BrandList brandList) {
		if (brandList.equals("") || brandList == null) {
			loadingView.showEmpty();
		} else {
			AdList = brandList.getDatas().getAdList();
			AllBrandList = brandList.getDatas().getAllBrandList();
			List<MySection> lists = new ArrayList<>();
			for (int i = 0; i < AllBrandList.size(); i++) {
				lists.add(new MySection(true, AllBrandList.get(i).getBrandInitial(), false));
				for (int j = 0; j < AllBrandList.get(i).getBrandBeanList().size(); j++) {
					lists.add(new MySection(AllBrandList.get(i).getBrandBeanList().get(j)));
				}

			}
			for (BrandBean brandBean : AdList) {
				lists.add(0, new MySection(brandBean));
			}
			lists.add(0, new MySection(true, getString(R.string.brandrecomm), false));
			rv_brand.setLayoutManager(new GridLayoutManager(context, 4));
			brandAdapter = new BrandAdapter(R.layout.fragment_brand_item, R.layout.fragment_brand_item_header, lists);
			rv_brand.setAdapter(brandAdapter);
			mSideBarView.setOnTouchLetterChangeListener(new WaveSideBarView.OnTouchLetterChangeListener() {
				@Override
				public void onLetterChange(String letter) {
					int pos = brandAdapter.getLetterPosition(letter);
					if (pos != -1) {
						rv_brand.scrollToPosition(pos);
						LinearLayoutManager mLayoutManager = (LinearLayoutManager) rv_brand.getLayoutManager();
						mLayoutManager.scrollToPositionWithOffset(pos, 0);
					}
				}
			});
			loadingView.showContent();

		}
	}
}
