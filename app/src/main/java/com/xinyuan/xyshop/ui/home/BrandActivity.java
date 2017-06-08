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
import com.xinyuan.xyshop.entity.Brand;
import com.xinyuan.xyshop.entity.BrandList;
import com.xinyuan.xyshop.entity.MySection;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.util.JsonUtil;
import com.xinyuan.xyshop.util.LetterComparator;
import com.xinyuan.xyshop.widget.WaveSideBarView;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.ArrayList;
import java.util.Collections;
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

	private List<Brand> listData;
	private List<Brand> adData;

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

	private void inRV(BrandList list) {
		if (list.equals("")) {
			xLoadingView.showEmpty();

		} else {
			listData = list.getDatas().getBrandList();
			adData = list.getDatas().getAdList();

			List<MySection> lists = new ArrayList<>();

			List<Brand> brands = new ArrayList<>();
			List<Brand> AllData = new ArrayList<>();

			String a = "";

			Collections.sort(listData, new LetterComparator());
			for (Brand b : listData) {
				if (!a.equals(b.getBrandInitial())) {
					brands.add(new Brand(b.getBrandInitial(), 0));
				}

				a = b.getBrandInitial();

			}

			AllData.addAll(brands);
			AllData.addAll(listData);
			Collections.sort(AllData, new LetterComparator());

			AllData.addAll(0, adData);
			for (Brand bran : AllData) {
				if (bran.getShowType() == 1) {

					lists.add(new MySection(bran));
				} else {
					lists.add(new MySection(true, bran.getBrandInitial(), false));
				}

			}


			lists.add(0, new MySection(true, "品牌推荐#", false));

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