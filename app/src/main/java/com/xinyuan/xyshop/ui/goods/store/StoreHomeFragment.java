package com.xinyuan.xyshop.ui.goods.store;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

	@BindView(R.id.store__home_tabs)
	SlidingTabLayout homeTab;

	@BindView(R.id.vp_content)
	NoScrollViewPager vp_content;

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

		fragmentList.add(collectFragment = new StoreCollectFragment());
		fragmentList.add(sellFragment = new StoreSellFragment());
		vp_content.setAdapter(new ItemTitlePagerAdapter(getChildFragmentManager(),
				fragmentList, new String[]{"收藏排行", "销量排行"}));
		vp_content.setOffscreenPageLimit(2);
		homeTab.setViewPager(vp_content);
		XYGridLayoutManager layoutManager2 = new XYGridLayoutManager(this.context, 2);
		layoutManager2.setScrollEnabled(false);
		this.rv_store_home.setLayoutManager(layoutManager2);
		this.manager = layoutManager2;

	}

	private void initlIST(List<GoodsVo> goodses) {
		adapter = new SearchGoodListAdapter(R.layout.activity_searchgood_item_grid, goodses, false);
		rv_store_home.setAdapter(adapter);
	}
}
