package com.xinyuan.xyshop.ui.goods;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.StringTagAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.entity.SearchHot;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.JsonUtil;
import com.xinyuan.xyshop.widget.OnFlexboxSubscribeListener;
import com.xinyuan.xyshop.widget.TagFlowLayout;
import com.xinyuan.xyshop.widget.dialog.SearchSortDialog;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class SearchGoodsActivity extends BaseActivity {

	@BindView(R.id.tag_flow_keyword)
	TagFlowLayout tab_keyword;
	@BindView(R.id.tag_flow_history)
	TagFlowLayout tab_history;
	@BindView(R.id.search_sort_name)
	TextView search_sort_name;
	@BindView(R.id.search_et)
	EditText search_et;
	@BindView(R.id.search_btn_back)
	ImageView search_btn_back;
	@BindView(R.id.seach_btn_delete)
	ImageView search_btn_delete;
	@BindView(R.id.search_btn_search)
	ImageView search_btn_search;


	private SearchSortDialog sortDialog;
	public static String showWord;
	public static String keyWord;
	private List<String> hotSearchs;

	private static List<String> hothistory = new ArrayList<>();
	private StringTagAdapter adapter;


	private MyShopApplication application;


	@Override
	public int getLayoutId() {
		return R.layout.activity_search;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		this.showWord = getIntent().getStringExtra("showWord");
		this.keyWord = getIntent().getStringExtra("keyword");
		XLog.v("showKeyWord" + keyWord);

	}


	@Override
	public void initView() {
		ButterKnife.bind(this);

		search_et.setHint(keyWord);
		this.application = MyShopApplication.getInstance();
		sortDialog = new SearchSortDialog(this, this.search_sort_name, this.search_sort_name);
		search_et.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void afterTextChanged(Editable editable) {
				if (CommUtil.isEmpty(editable)) {
					search_btn_delete.setVisibility(View.GONE);
					return;
				}
				if (CommUtil.getText(search_sort_name).equals("商品")) {

				}
				search_btn_delete.setVisibility(View.VISIBLE);
			}
		});
		getKeyData();
		getHistoryDatas();
	}


	private void showHot(List<String> list) {

		adapter = new StringTagAdapter(this, hotSearchs);
		XLog.list(hotSearchs);
		tab_keyword.setAdapter(adapter);
		adapter.setOnSubscribeListener(new OnFlexboxSubscribeListener<String>() {
			@Override
			public void onSubscribe(List<String> selectedItem) {

			}
		});

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

	private void getHistoryDatas() {
		try {
			hothistory = MyShopApplication.getInstance().getSearchKeyList();

			if (hothistory != null || hothistory.size() != 0) {
				Collections.reverse(this.hothistory);
				XLog.list(hothistory);
				showHistoty(this.hothistory);
				XLog.v("显示历史词" + hothistory.toString());
				showHistoty(hothistory);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

	private void showHistoty(List<String> historys) {
		this.tab_history.removeAllViews();
		if (historys != null && !historys.isEmpty()) {
			adapter = new StringTagAdapter(this, historys);
			XLog.list(historys);
			tab_history.setAdapter(adapter);
		}
	}


	@OnClick({R.id.search_sort_img, R.id.seach_btn_delete, R.id.search_btn_search, R.id.btnClearHistory,R.id.search_btn_back})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.search_sort_img:
				this.sortDialog.show();
				return;
			case R.id.seach_btn_delete:
				this.search_et.setText("");
				return;
			case R.id.btnClearHistory:
				this.application.getSearchKeyList().clear();
				this.tab_history.removeAllViews();
				return;
			case R.id.search_btn_search:
				String key = CommUtil.getText(this.search_et);
				HashMap<String, String> params;
				if (CommUtil.getText(this.search_sort_name).equals("商品")) {
					if (key.equals("")) {
						key = this.keyWord;
						saveKeyword(keyWord);
					} else {
						saveKeyword(key);
					}
					params = new HashMap();
					params.put("keyword", key);

					CommUtil.gotoActivity(this, SearchGoodsShowActivity.class, false, params);
					return;
				}
				params = new HashMap();
				params.put("keyword", key);
				//CommUtil.gotoActivity(this, SearchStoresShowActivity.class, false, params);
				return;
			case R.id.search_btn_back:
				finish();
				return;
		}

	}

	public static void saveKeyword(String text) {
		if (!text.trim().equals("")) {
			Collections.reverse(hothistory);
			if (hothistory.contains(text)) {
				MyShopApplication.getInstance().getSearchKeyList().remove(text);
			}
			MyShopApplication.getInstance().getSearchKeyList().add(text);

		}

	}

	protected void onResume() {
		super.onResume();
		getHistoryDatas();
	}

}
