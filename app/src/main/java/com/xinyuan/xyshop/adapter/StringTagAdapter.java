package com.xinyuan.xyshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.xinyuan.xyshop.ui.goods.search.SearchActivity;
import com.xinyuan.xyshop.ui.goods.search.SearchGoodShowActivity;
import com.xinyuan.xyshop.ui.goods.search.SearchStoreShowActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.widget.StringTagView;
import com.youth.xframe.cache.XCache;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 作者：fx
 * 日期：2017/5/27
 */
public class StringTagAdapter extends SearchTagAdapter<StringTagView, String> {

	private Context context;
	private XCache xCache;

	public StringTagAdapter(Context context, List<String> data) {
		this(context, data, null);
		this.context = context;
		xCache = XCache.get(context);
	}

	public StringTagAdapter(Context context, List<String> data, List<String> selectItems) {
		super(context, data, selectItems);
		this.context = context;
	}

	/**
	 * 检查item和所选item是否一样
	 *
	 * @param view
	 * @param item
	 * @return
	 */
	@Override
	protected boolean checkIsItemSame(StringTagView view, String item) {
		return TextUtils.equals(view.getItem(), item);
	}

	/**
	 * 检查item是否是空指针
	 *
	 * @return
	 */
	@Override
	protected boolean checkIsItemNull(String item) {
		return TextUtils.isEmpty(item);
	}

	/**
	 * 添加标签
	 *
	 * @param item
	 * @return
	 */
	@Override
	protected StringTagView addTag(final String item) {
		StringTagView tagView = new StringTagView(getContext());
		tagView.setPadding(35, 20, 35, 20);
		final TextView textView = tagView.getTextView();
		textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
		textView.setGravity(Gravity.CENTER);
		tagView.setItemDefaultDrawable(itemDefaultDrawable);
		tagView.setItemSelectDrawable(itemSelectDrawable);
		tagView.setItemDefaultTextColor(itemDefaultTextColor);
		tagView.setItemSelectTextColor(itemSelectTextColor);
		tagView.setItem(item);
		tagView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				if (!XEmptyUtils.isSpace(item)) {
					SearchActivity.searchBean.getHistoryList().add(0, item);
					SearchActivity.searchBean.setHistoryList(CommUtil.distinctList(SearchActivity.searchBean.getHistoryList()));
					xCache.put("Search", SearchActivity.searchBean, xCache.TIME_DAY);
					HashMap<String, String> params;
					params = new HashMap();
					params.put("KEYWORD", item);
					if (SearchActivity.searchGood) {
						CommUtil.gotoActivity(context, SearchGoodShowActivity.class, false, params);
					} else {
						CommUtil.gotoActivity(context, SearchStoreShowActivity.class, false, params);

					}


				}


			}
		});


		return tagView;
	}
}
