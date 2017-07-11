package com.xinyuan.xyshop.ui.goods.store.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.ItemTitlePagerAdapter;
import com.xinyuan.xyshop.adapter.SearchGoodListAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.entity.SearchGoodsList;
import com.xinyuan.xyshop.util.JsonUtil;
import com.xinyuan.xyshop.widget.NoScrollViewPager;
import com.xinyuan.xyshop.widget.XYGridLayoutManager;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/1.
 */

public class StoreHomeFragment extends BaseFragment {

	@BindView(R.id.rv_store_home)
	RecyclerView rv_store_home;

	private List<Fragment> fragmentList = new ArrayList<>();

	private StoreSellFragment sellFragment;
	private StoreCollectFragment collectFragment;


	private SearchGoodListAdapter adapter;
	private LinearLayoutManager manager;
	private List<GoodsVo> goodses = new ArrayList();
	private SearchGoodsList searchGoodsList;
	private static boolean VIEW_INIT = true;

	@Override
	public int getLayoutId() {
		return R.layout.fragment_store_home;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
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


	}

	private void initlIST(List<GoodsVo> goodses) {

		GridLayoutManager layoutManager2 = new GridLayoutManager(this.context, 2);
		this.rv_store_home.setLayoutManager(layoutManager2);
		this.manager = layoutManager2;
		this.rv_store_home.setLayoutManager(layoutManager2);
		this.adapter = new SearchGoodListAdapter(R.layout.item_good_grid, goodses, false);
		this.rv_store_home.setAdapter(adapter);




		View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_store_top, (ViewGroup) rv_store_home.getParent(), false);
		NoScrollViewPager vp_content = (NoScrollViewPager) view.findViewById(R.id.vp_content);
		SlidingTabLayout homeTab = (SlidingTabLayout) view.findViewById(R.id.store__home_tabs);
		fragmentList.add(collectFragment = new StoreCollectFragment());
		fragmentList.add(sellFragment = new StoreSellFragment());
		vp_content.setAdapter(new ItemTitlePagerAdapter(getChildFragmentManager(),
				fragmentList, new String[]{"收藏排行", "销量排行"}));
		vp_content.setOffscreenPageLimit(2);
		homeTab.setViewPager(vp_content);
		adapter.addHeaderView(view);

	}
}
