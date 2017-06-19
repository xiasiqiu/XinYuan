package com.xinyuan.xyshop.ui.goods.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.SimpleEvaluateAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.GoodsEvaluate;
import com.xinyuan.xyshop.model.GoodDetailModel;
import com.xinyuan.xyshop.model.GoodsDetailModel;
import com.xinyuan.xyshop.util.FullyLinearLayoutManager;
import com.youth.xframe.utils.log.XLog;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/18.
 */

public class GoodsCommentFragment extends BaseFragment {

//	@BindView(R.id.nvp_eva_content)
//	NotSlipViewPager nvp_eva_content;

	@BindView(R.id.ll_eva_all)
	LinearLayout ll_eva_all;
	@BindView(R.id.ll_eva_good)
	LinearLayout ll_eva_good;
	@BindView(R.id.ll_eva_med)
	LinearLayout ll_eva_med;
	@BindView(R.id.ll_eva_bad)
	LinearLayout ll_eva_bad;
	@BindView(R.id.ll_eva_pic)
	LinearLayout ll_eva_pic;

	@BindView(R.id.tv_eva_all_num)
	TextView tv_eva_all_num;
	@BindView(R.id.tv_eva_good_num)
	TextView tv_eva_good_num;
	@BindView(R.id.tv_eva_med_num)
	TextView tv_eva_med_num;
	@BindView(R.id.tv_eva_bad_num)
	TextView tv_eva_bad_num;
	@BindView(R.id.tv_eva_pic_num)
	TextView tv_eva_pic_num;

	@BindView(R.id.tv_eva_all)
	TextView tv_eva_all;
	@BindView(R.id.tv_eva_good)
	TextView tv_eva_good;
	@BindView(R.id.tv_eva_med)
	TextView tv_eva_med;
	@BindView(R.id.tv_eva_bad)
	TextView tv_eva_bad;
	@BindView(R.id.tv_eva_pic)
	TextView tv_eva_pic;

	@BindView(R.id.rv_comment)
	RecyclerView rv_comment;


	@Override
	public int getLayoutId() {
		return R.layout.fragment_good_comment;
	}

	@Override
	public void initData(@Nullable Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		XLog.v("开始加载视图");
		tv_eva_all_num.setEnabled(false);
		tv_eva_all.setEnabled(false);
		initList(20);


	}

	SimpleEvaluateAdapter simpleEvaluateAdapter;

	private void initList(int i) {
		List<GoodDetailModel.CommentList> data = new ArrayList<>();
		for ( int j = 0; j < i; j++) {
			data.add(new GoodDetailModel.CommentList());
		}
		this.simpleEvaluateAdapter = new SimpleEvaluateAdapter(R.layout.fragment_good_item_evaluate, data);
		FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(context);
		rv_comment.setNestedScrollingEnabled(false);
		//设置布局管理器
		rv_comment.setLayoutManager(linearLayoutManager);
		this.rv_comment.setAdapter(this.simpleEvaluateAdapter);
		this.simpleEvaluateAdapter.notifyDataSetChanged();
	}


	@OnClick({R.id.ll_eva_all, R.id.ll_eva_good, R.id.ll_eva_med, R.id.ll_eva_bad, R.id.ll_eva_pic})
	public void onClick(View view) {
		switch (view.getId()) {

			case R.id.ll_eva_all:
				XLog.v("点击了！！！！！！！！！！！！！！");
				tv_eva_all_num.setEnabled(false);
				tv_eva_all.setEnabled(false);

				tv_eva_good_num.setEnabled(true);
				tv_eva_good.setEnabled(true);
				tv_eva_med_num.setEnabled(true);
				tv_eva_med.setEnabled(true);
				tv_eva_bad_num.setEnabled(true);
				tv_eva_bad.setEnabled(true);
				tv_eva_pic_num.setEnabled(true);
				tv_eva_pic.setEnabled(true);
				initList(20);
				break;
			case R.id.ll_eva_good:

				tv_eva_good_num.setEnabled(false);
				tv_eva_good.setEnabled(false);

				tv_eva_all_num.setEnabled(true);
				tv_eva_all.setEnabled(true);
				tv_eva_med_num.setEnabled(true);
				tv_eva_med.setEnabled(true);
				tv_eva_bad_num.setEnabled(true);
				tv_eva_bad.setEnabled(true);
				tv_eva_pic_num.setEnabled(true);
				tv_eva_pic.setEnabled(true);
				initList(15);
				break;
			case R.id.ll_eva_med:

				tv_eva_med_num.setEnabled(false);
				tv_eva_med.setEnabled(false);

				tv_eva_all_num.setEnabled(true);
				tv_eva_all.setEnabled(true);
				tv_eva_good_num.setEnabled(true);
				tv_eva_good.setEnabled(true);
				tv_eva_bad_num.setEnabled(true);
				tv_eva_bad.setEnabled(true);
				tv_eva_pic_num.setEnabled(true);
				tv_eva_pic.setEnabled(true);
				initList(3);
				break;
			case R.id.ll_eva_bad:

				tv_eva_bad_num.setEnabled(false);
				tv_eva_bad.setEnabled(false);

				tv_eva_all_num.setEnabled(true);
				tv_eva_all.setEnabled(true);
				tv_eva_good_num.setEnabled(true);
				tv_eva_good.setEnabled(true);
				tv_eva_med_num.setEnabled(true);
				tv_eva_med.setEnabled(true);
				tv_eva_pic_num.setEnabled(true);
				tv_eva_pic.setEnabled(true);
				initList(2);
				break;
			case R.id.ll_eva_pic:
				tv_eva_pic_num.setEnabled(false);
				tv_eva_pic.setEnabled(false);

				tv_eva_all_num.setEnabled(true);
				tv_eva_all.setEnabled(true);
				tv_eva_good_num.setEnabled(true);
				tv_eva_good.setEnabled(true);
				tv_eva_med_num.setEnabled(true);
				tv_eva_med.setEnabled(true);
				tv_eva_bad_num.setEnabled(true);
				tv_eva_bad.setEnabled(true);
				initList(10);
				break;
			default:
				break;
		}

	}


}



