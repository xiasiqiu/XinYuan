package com.xinyuan.xyshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.ui.goods.SearchGoodsActivity;
import com.xinyuan.xyshop.ui.goods.SearchGoodsShowActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.widget.StringTagView;

import java.util.HashMap;
import java.util.List;


/**
 * 作者：ZhouYou
 * 日期：3017/3/27
 */
public class StringTagAdapter extends SearchTagAdapter<StringTagView, String> {

	private Context context;

	public StringTagAdapter(Context context, List<String> data) {
		this(context, data, null);
		this.context = context;
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
		tagView.setPadding(30, 20, 30, 20);
		final TextView textView = tagView.getTextView();
		textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
		textView.setGravity(Gravity.CENTER);
		tagView.setItemDefaultDrawable(itemDefaultDrawable);
		tagView.setItemSelectDrawable(itemSelectDrawable);
		tagView.setItemDefaultTextColor(itemDefaultTextColor);
		tagView.setItemSelectTextColor(itemSelectTextColor);
		tagView.setItem(item);
		tagView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				HashMap<String, String> params;
				if (item.equals("")) {
					params = new HashMap();
					params.put("keyword", SearchGoodsActivity.keyWord);

					SearchGoodsActivity.saveKeyword(SearchGoodsActivity.keyWord);
				} else {
					params = new HashMap();
					params.put("keyword", item);
					SearchGoodsActivity.saveKeyword(item);
				}


				Intent intent = new Intent(context, SearchGoodsShowActivity.class);
				intent.putExtra("keyword", (String) params.get("keyword"));
				context.startActivity(intent);
			}
		});



		return tagView;
	}
}
