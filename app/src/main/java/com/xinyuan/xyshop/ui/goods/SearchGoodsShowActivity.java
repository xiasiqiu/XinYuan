package com.xinyuan.xyshop.ui.goods;

import android.os.Bundle;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.StringTagAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.entity.SearchHot;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.util.JsonUtil;
import com.xinyuan.xyshop.widget.TagFlowLayout;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class SearchGoodsShowActivity extends BaseActivity {

	@BindView(R.id.tag_flow_keyword)
	TagFlowLayout tab_keyword;
	@BindView(R.id.tag_flow_history)
	TagFlowLayout tab_history;


	private String showWord;
	private String keyWord;

	private List<String> hotSearchs;
	private List<String> hothistory;
	private StringTagAdapter adapter;

	@Override
	public int getLayoutId() {
		return R.layout.activity_search_goods_show;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		this.showWord = getIntent().getStringExtra("showWord");
		this.keyWord = getIntent().getStringExtra("keyword");
	}

	private void getKeyData() {
		hotSearchs = new ArrayList<>();
		OkGo.get(Urls.URL_SEARCH_DEFAULT)
				.execute(new StringCallback() {
					@Override
					public void onSuccess(String s, Call call, Response response) {

						SearchHot searchHot = JsonUtil.toBean(s, SearchHot.class);

						SearchHot.KeyHot keyHot = searchHot.getDatas();
						hotSearchs = keyHot.getKeywordList();
						hothistory = keyHot.getHistorySearchList();
						showHot(hotSearchs);
					}
				});


	}

	private void showHot(List<String> list) {

		adapter = new StringTagAdapter(this, hotSearchs);
		XLog.list(hotSearchs);
		tab_keyword.setAdapter(adapter);

	}

	@Override
	public void initView() {
		ButterKnife.bind(this);


		getKeyData();


	}
}
