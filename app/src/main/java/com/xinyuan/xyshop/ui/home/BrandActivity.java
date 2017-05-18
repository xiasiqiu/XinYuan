package com.xinyuan.xyshop.ui.home;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.BrandGridViewAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.entity.Brand;
import com.xinyuan.xyshop.entity.BrandList;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.util.PinnedHeaderDecoration;
import com.xinyuan.xyshop.widget.WaveSideBarView;
import com.youth.xframe.utils.log.XLog;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

public class BrandActivity extends BaseActivity {

	@BindView(R.id.rv_brand)
	RecyclerView rv_brand;
	@BindView(R.id.side_view)
	WaveSideBarView mSideBarView;
	BrandGridViewAdapter adapter;

	private List<Brand> adList = new ArrayList<>();
	private List<Brand> brandLists = new ArrayList<>();

	@Override
	public int getLayoutId() {
		return R.layout.activity_brand;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

		OkGo.get(Urls.URL_BRAND)
				.execute(new JsonCallback<BrandList>() {
					@Override
					public void onSuccess(BrandList brandList, Call call, Response response) {

						adList = brandList.getDatas().getAdList();
						brandLists = brandList.getDatas().getBrandList();
						XLog.list(brandLists);


					}
				});


	}

	@Override
	public void initView() {
		final PinnedHeaderDecoration decoration = new PinnedHeaderDecoration();
		decoration.registerTypePinnedHeader(1, new PinnedHeaderDecoration.PinnedHeaderCreator() {
			@Override
			public boolean create(RecyclerView parent, int adapterPosition) {
				return true;
			}
		});
		rv_brand.addItemDecoration(decoration);

	}
}
