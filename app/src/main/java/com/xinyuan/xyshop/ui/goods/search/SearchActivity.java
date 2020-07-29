package com.xinyuan.xyshop.ui.goods.search;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.StringTagAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.SearchHistoryBean;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.model.KeyWordModel;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.widget.EditTextWithDel;
import com.xinyuan.xyshop.widget.OnFlexboxSubscribeListener;
import com.xinyuan.xyshop.widget.TagFlowLayout;
import com.xinyuan.xyshop.widget.dialog.SearchSortDialog;
import com.youth.xframe.cache.XCache;
import com.youth.xframe.utils.XEmptyUtils;

import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fx on 2017/8/1.
 * 搜索界面Activity
 */

public class SearchActivity extends BaseActivity<BasePresenter> {
	@BindView(R.id.tag_flow_keyword)
	TagFlowLayout tab_keyword;
	@BindView(R.id.tag_flow_history)
	TagFlowLayout tab_history;
	@BindView(R.id.search_et)
	EditTextWithDel search_et;
	@BindView(R.id.search_btn_back)
	ImageView search_btn_back;
	@BindView(R.id.search_btn_search)
	ImageView search_btn_search;
	@BindView(R.id.tvSortName)
	TextView tvSortName;

	private XCache xCache;  //缓存控件
	private StringTagAdapter adapter;
	private SearchSortDialog sortDialog;
	public static SearchHistoryBean searchBean;
	public static boolean searchGood = true;

	@Override
	protected int provideContentViewId() {
		return R.layout.activity_search;
	}

	@Override
	public void initView() {
		registerEventBus(this);
	}

	@Override
	public void initData() {
		getHistoryDatas();
		this.sortDialog = new SearchSortDialog(this, this.tvSortName, this.tvSortName);
	}

	/**
	 * 获取并显示热词
	 *
	 * @param keyWordModel
	 */
	@Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
	public void getSearchKey(KeyWordModel keyWordModel) {
		if (!XEmptyUtils.isEmpty(keyWordModel)) {
			search_et.setText(keyWordModel.getKeywordList().get(0));
			adapter = new StringTagAdapter(this, keyWordModel.getKeywordList());
			tab_keyword.setAdapter(adapter);
			adapter.setOnSubscribeListener(new OnFlexboxSubscribeListener<String>() {
				@Override
				public void onSubscribe(List<String> selectedItem) {

				}
			});
		}
	}

	/**
	 * 从缓存中获取历史记录
	 */
	private void getHistoryDatas() {
		xCache = XCache.get(this);
		searchBean = (SearchHistoryBean) xCache.getAsObject(Constants.SEARCH_HISTORY);
		if (!XEmptyUtils.isEmpty(searchBean)) {
			if (!XEmptyUtils.isEmpty(searchBean.getHistoryList())) {
				this.tab_history.removeAllViews();
				if (!XEmptyUtils.isEmpty(searchBean.getHistoryList())) {
					adapter = new StringTagAdapter(this, searchBean.getHistoryList());
					tab_history.setAdapter(adapter);
				}
			} else {
			}

		} else {
			searchBean = new SearchHistoryBean();
			searchBean.setHistoryList(new ArrayList<String>());
		}


	}

	@OnClick({R.id.search_btn_search, R.id.btnClearHistory, R.id.search_btn_back, R.id.ll_store_good})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btnClearHistory:  //清空历史记录
				this.tab_history.removeAllViews();
				xCache.remove("SEARCH_HISTORY");
				searchBean.getHistoryList().clear();
				return;
			case R.id.search_btn_search:    //点击搜索按钮
				if (!XEmptyUtils.isSpace(search_et.getText().toString().trim())) {
					String key = CommUtil.getText(this.search_et).trim();   //获取输入的关键词
					HashMap<String, String> params;
					params = new HashMap();
					params.put(Constants.KEYWORD, key);
					searchBean.getHistoryList().add(0, key);    //放入search数据
					searchBean.setHistoryList(CommUtil.distinctList(searchBean.getHistoryList()));//数据去重
					xCache.put(Constants.SEARCH_HISTORY, searchBean, xCache.TIME_DAY);  //存入缓存
					if (searchGood) {
						CommUtil.gotoActivity(this, SearchGoodShowActivity.class, false, params);
					} else {
						CommUtil.gotoActivity(this, SearchStoreShowActivity.class, false, params);

					}
				}else {
					XToast.info("请输入关键词进行搜索");
				}

				return;
			case R.id.search_btn_back:      //返回
				finish();
				return;
			case R.id.ll_store_good:    //选择搜索类型
				this.sortDialog.show();
				break;
		}

	}


	public void onResume() {
		super.onResume();
		getHistoryDatas();  //获取历史记录
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
        searchGood=true;
		unregisterEventBus(this);
	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}
}
