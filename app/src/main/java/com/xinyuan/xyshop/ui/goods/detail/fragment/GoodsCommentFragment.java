package com.xinyuan.xyshop.ui.goods.detail.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.SimpleEvaluateAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.BrandList;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.EvaluateModel;
import com.xinyuan.xyshop.model.GoodDetailModel;
import com.xinyuan.xyshop.model.TestEvaluateModel;
import com.xinyuan.xyshop.ui.goods.GoodBusBean;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailsActivity;
import com.xinyuan.xyshop.util.FullyLinearLayoutManager;
import com.xinyuan.xyshop.util.JsonUtil;
import com.youth.xframe.utils.log.XLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

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

	private static boolean VIEW_INIT = true;

	@Override
	public int getLayoutId() {
		return R.layout.fragment_good_comment;
	}

	@Override
	public void initData(@Nullable Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		if (VIEW_INIT) {
			XLog.v("开始加载视图");
			tv_eva_all_num.setEnabled(false);
			tv_eva_all.setEnabled(false);
			initList(20);

			tv_eva_all_num.setText("" + GoodDetailsActivity.totalCount);
			tv_eva_good_num.setText("" + GoodDetailsActivity.goodAssess);
			tv_eva_med_num.setText("" + GoodDetailsActivity.normalAssess);
			tv_eva_bad_num.setText("" + GoodDetailsActivity.lowAssess);
			tv_eva_pic_num.setText("" + GoodDetailsActivity.blueprint);
		}


		VIEW_INIT = false;


	}

	SimpleEvaluateAdapter simpleEvaluateAdapter;

	GoodDetailModel.GoodComment comment;


	private void initList(int i) {
		OkGo.get(Urls.URL_GOOD_EVA)
				.execute(new StringCallback() {
					@Override
					public void onSuccess(String s, Call call, Response response) {

						XLog.v("String:" + s);

						TestEvaluateModel model = JsonUtil.toBean(s, TestEvaluateModel.class);
						EvaluateModel evaluateModel = model.getDatas();
						XLog.v("EvaluateModel=" + evaluateModel.toString());

						getView(evaluateModel.getList());
					}
				});


	}

	private void getView(List<EvaluateModel.EvaluateBean> list) {

		this.simpleEvaluateAdapter = new SimpleEvaluateAdapter(R.layout.fragment_good_item_evaluate, list);
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



