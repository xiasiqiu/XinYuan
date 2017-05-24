package com.xinyuan.xyshop.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.ExpandItem;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.entity.Menu;
import com.xinyuan.xyshop.entity.SelectFilterTest;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */

public class SelectDialogAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

	public static final int TYPE_LEVEL_1 = 0;
	public static final int TYPE_ITEM = 1;
	private List<SelectFilterTest.FilterKey> list = new ArrayList<>();

	/**
	 * Same as QuickAdapter#QuickAdapter(Context,int) but with
	 * some initialization data.
	 *
	 * @param data A new list is created out of this one to avoid mutable list
	 */

	public SelectDialogAdapter(List<MultiItemEntity> data, List<SelectFilterTest.FilterKey> menuList) {
		super(data);
		addItemType(TYPE_LEVEL_1, R.layout.searchgoodfilter_item1);
		addItemType(TYPE_ITEM, R.layout.searchgoodfilter_item2);
		this.list = menuList;
	}

	@Override
	protected void convert(final BaseViewHolder helper, final MultiItemEntity item) {

		switch (helper.getItemViewType()) {

			case TYPE_LEVEL_1:

				final ExpandItem lv1 = (ExpandItem) item;
				helper.setText(R.id.title, lv1.title)
						.setImageResource(R.id.img_search_filter, lv1.isExpanded() ? R.drawable.arrow_b : R.drawable.arrow_r);
				helper.itemView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						int pos = helper.getAdapterPosition();

						if (lv1.isExpanded()) {
							collapse(pos, false);
						} else {
							expand(pos, false);
						}
					}
				});
				break;
			case TYPE_ITEM:

				final Menu menu = (Menu) item;
				helper.setText(R.id.tv_search_item, menu.getTitle());

				helper.itemView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						XLog.v(menu.getTitle());
						if (keyList.contains(((Menu) item).getTitle())) {
							keyList.remove(((Menu) item).getTitle());
							helper.itemView.setSelected(false);
							helper.setTextColor(R.id.tv_search_item, mContext.getResources().getColor(R.color.tv_hint));
						} else {
							keyList.add(((Menu) item).getTitle());
							helper.itemView.setSelected(true);
							helper.setTextColor(R.id.tv_search_item, mContext.getResources().getColor(R.color.bg_white));
						}


					}
				});

				break;

		}
	}

	private static List<String> keyList = new ArrayList<>();


	public static List<String> getKeyList() {

		return keyList;
	}
}
