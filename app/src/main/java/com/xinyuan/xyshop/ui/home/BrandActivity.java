package com.xinyuan.xyshop.ui.home;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.BrandAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.entity.BrandBean;
import com.xinyuan.xyshop.entity.BrandList;
import com.xinyuan.xyshop.entity.MySection;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.BrandModel;
import com.xinyuan.xyshop.util.JsonUtil;
import com.xinyuan.xyshop.widget.WaveSideBarView;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class BrandActivity extends BaseActivity {

	@BindView(R.id.rv_brand)
	RecyclerView rv_brand;
	@BindView(R.id.side_view)
	WaveSideBarView mSideBarView;
	private List<BrandBean> AdList;
	private List<BrandModel.Brand> AllBrandList;

	BrandAdapter brandAdapter;
	@BindView(R.id.brand_loadingView)
	XLoadingView xLoadingView;

	@Override
	public int getLayoutId() {
		return R.layout.activity_brand;
	}

	@Override
	public void initData(Bundle savedInstanceState) {


	}

	@Override
	public void initView() {
		ButterKnife.bind(this);
		xLoadingView.showLoading();
		rv_brand.setLayoutManager(new GridLayoutManager(this, 4));

		OkGo.get(Urls.URL_BRAND)
				.execute(new StringCallback() {
					@Override
					public void onSuccess(String s, Call call, Response response) {
						BrandList brands = JsonUtil.toBean(s, BrandList.class);
						inRV(brands);
					}

					@Override
					public void onError(Call call, Response response, Exception e) {
						xLoadingView.showError();
					}
				});

	}

	private void inRV(BrandList brandList) {


		if (brandList.equals("")) {
			xLoadingView.showEmpty();

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
			lists.add(0, new MySection(true, "品牌推荐#", false));

			XLog.list(lists);
			brandAdapter = new BrandAdapter(R.layout.activity_brand_item, R.layout.activity_brand_item_header, lists);
			rv_brand.setAdapter(brandAdapter);

			mSideBarView.setOnTouchLetterChangeListener(new WaveSideBarView.OnTouchLetterChangeListener() {
				@Override
				public void onLetterChange(String letter) {
					int pos = brandAdapter.getLetterPosition(letter);
					if (pos != -1) {
						rv_brand.scrollToPosition(pos);
						LinearLayoutManager mLayoutManager =
								(LinearLayoutManager) rv_brand.getLayoutManager();
						mLayoutManager.scrollToPositionWithOffset(pos, 0);
					}
				}
			});
			xLoadingView.showContent();

		}
	}


}