package com.xinyuan.xyshop.ui.goods.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.SearchGoodListAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.entity.SearchGoodsList;
import com.xinyuan.xyshop.entity.SearchGoodsListTest;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.JsonUtil;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/18.
 */

public class GoodsRecommFragment extends BaseFragment {
	@BindView(R.id.rv_recomm)
	RecyclerView rv_recomm;

	private SearchGoodListAdapter adapter;
	private LinearLayoutManager manager;
	private List<GoodsVo> goodses = new ArrayList();
	private SearchGoodsList searchGoodsList;

	@Override
	public int getLayoutId() {
		return R.layout.fragment_good_recomm;
	}

	@Override
	public void initData(@Nullable Bundle savedInstanceState) {

		String url = "https://java.bizpower.com/api/search?page=1&pageSize=10&keyword=手机";
		OkGo.get(url)
				.execute(new StringCallback() {
					@Override
					public void onSuccess(String s, Call call, Response response) {

						searchGoodsList = JsonUtil.toBean(s, SearchGoodsList.class);
						XLog.v("datas" + searchGoodsList);
						SearchGoodsList.SearchGoodsData data = searchGoodsList.getDatas();
						goodses = data.getGoodsList();
						initlIST(goodses);

					}
				});
	}

	@Override
	public void initView() {
		GridLayoutManager layoutManager2 = new GridLayoutManager(this.context, 2, 1, false);
		this.rv_recomm.setLayoutManager(layoutManager2);
		this.manager = layoutManager2;


	}

	private void initlIST(List<GoodsVo> goodses) {
		adapter = new SearchGoodListAdapter(R.layout.searchgood_item_grid, goodses, false);
		rv_recomm.setAdapter(adapter);
	}
}
