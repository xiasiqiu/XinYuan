package com.xinyuan.xyshop.ui.goods.detail.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.SimpleEvaluateAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.EvaluateModel;
import com.xinyuan.xyshop.model.GoodDetailModel;
import com.xinyuan.xyshop.model.TestEvaluateModel;
import com.xinyuan.xyshop.ui.mine.order.fragment.MyOrderFragment;
import com.xinyuan.xyshop.util.FullyLinearLayoutManager;
import com.xinyuan.xyshop.util.JsonUtil;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/22.
 */

public class CommentContentFragment extends BaseFragment {


	@BindView(R.id.rv_commont)
	RecyclerView rv_comment;
	@BindView(R.id.lodingView)
	XLoadingView lodingView;

	public int index;
	SimpleEvaluateAdapter simpleEvaluateAdapter;

	GoodDetailModel.GoodComment comment;

	public static CommentContentFragment getInstance(int index) {
		CommentContentFragment sf = new CommentContentFragment();
		sf.index = index;
		return sf;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_good_com_content;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		lodingView.showLoading();
		String url = Urls.URL_GOOD_EVA_ALL;
		switch (index) {
			case 0:
				break;
			case 1:
				url = Urls.URL_GOOD_EVA_GOOD;
				break;
			case 2:
				url = Urls.URL_GOOD_EVA_NORMAL;
				break;
			case 3:
				url = Urls.URL_GOOD_EVA_BAD;
				break;
			case 4:
				url = Urls.URL_GOOD_EVA_PIC;
				break;
		}
		OkGo.get(url)
				.execute(new StringCallback() {
					@Override
					public void onSuccess(String s, Call call, Response response) {
						TestEvaluateModel model = JsonUtil.toBean(s, TestEvaluateModel.class);
						EvaluateModel evaluateModel = model.getDatas();
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
		lodingView.showContent();
	}
}

