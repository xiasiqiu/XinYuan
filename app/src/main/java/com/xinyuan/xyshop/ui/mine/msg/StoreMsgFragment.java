package com.xinyuan.xyshop.ui.mine.msg;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.MsgAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.GoodsEvaluate;
import com.xinyuan.xyshop.util.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/5.
 */

public class StoreMsgFragment extends BaseFragment {

	@BindView(R.id.rv_msg)
	RecyclerView rv_msg;

	MsgAdapter msgAdapter;

	@Override
	public int getLayoutId() {
		return R.layout.fragment_msg;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		List<GoodsEvaluate> data = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			data.add(new GoodsEvaluate());
		}
		this.msgAdapter = new MsgAdapter(R.layout.fragment_msg_item, data);
		FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(context);
		rv_msg.setNestedScrollingEnabled(false);
		//设置布局管理器
		rv_msg.setLayoutManager(linearLayoutManager);
		rv_msg.setAdapter(msgAdapter);
	}
}
