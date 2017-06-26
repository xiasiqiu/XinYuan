package com.xinyuan.xyshop.ui.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.GoodsGridAdapter;
import com.xinyuan.xyshop.adapter.SearchGoodListAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.entity.GoodsVo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FavoriteActivity extends BaseActivity {

	@BindView(R.id.rv_fav)
	RecyclerView rv_fav;
	GoodsGridAdapter adapter;
	private LinearLayoutManager manager;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;

	@BindView(R.id.tv_header_right)
	TextView tv_header_right;
	@Override
	public int getLayoutId() {
		return R.layout.activity_favorite;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {

		tv_header_center.setText("收藏夹");
		tv_header_right.setText("编辑");
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		layoutManager.setOrientation(1);

		List<GoodsVo> goodses = new ArrayList<>();
		goodses.add(new GoodsVo());
		goodses.add(new GoodsVo());
		goodses.add(new GoodsVo());
		goodses.add(new GoodsVo());
		goodses.add(new GoodsVo());
		goodses.add(new GoodsVo());
		goodses.add(new GoodsVo());
		this.rv_fav.setLayoutManager(layoutManager);
		this.manager = layoutManager;
		this.adapter = new GoodsGridAdapter(R.layout.activity_favorite_item, goodses);
		this.adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
		this.rv_fav.setAdapter(adapter);
	}
}
