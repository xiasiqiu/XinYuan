package com.xinyuan.xyshop.ui.goods.groupbuy.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.GoodsGridAdapter;
import com.xinyuan.xyshop.adapter.SearchGoodListAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.entity.SearchGoodsList;
import com.xinyuan.xyshop.model.GoodDetail;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailsActivity;
import com.xinyuan.xyshop.ui.mine.order.fragment.MyOrderContentFragment;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.JsonUtil;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/21.
 */

public class GroupGoodsFragment extends BaseFragment {

	private String mTitle;

	@BindView(R.id.rv_group)
	RecyclerView rv_group;
	@BindView(R.id.ll_group_select)
	LinearLayout ll_group_select;
	private GoodsGridAdapter adapter;
	private LinearLayoutManager manager;
	private List<GoodsVo> goodses = new ArrayList();
	private SearchGoodsList searchGoodsList;


	public static GroupGoodsFragment getInstance(String title) {
		GroupGoodsFragment sf = new GroupGoodsFragment();
		sf.mTitle = title;
		return sf;
	}


	@Override
	public int getLayoutId() {
		return R.layout.fragment_group_buy_goods;
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
		if (mTitle.equals("全部团购")) {
			ll_group_select.setVisibility(View.VISIBLE);
		}

		GridLayoutManager layoutManager2 = new GridLayoutManager(this.context, 2, 1, false);
		this.rv_group.setLayoutManager(layoutManager2);
		this.manager = layoutManager2;
	}


	private void initlIST(List<GoodsVo> goodses) {
		adapter = new GoodsGridAdapter(R.layout.activity_group_item_grid, goodses);
		rv_group.setAdapter(adapter);
		adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
				String Type = "1:1";
				HashMap<String, String> params;
				params = new HashMap();
				params.put("GoodType", Type);
				CommUtil.gotoActivity(getActivity(), GoodDetailsActivity.class, false, params);
			}
		});

	}

	public RecyclerView getRvList() {
		return rv_group;
	}
}
