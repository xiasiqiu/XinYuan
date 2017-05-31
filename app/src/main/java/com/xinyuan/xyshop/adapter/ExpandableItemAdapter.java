package com.xinyuan.xyshop.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.ExpandItem;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.entity.Menu;
import com.xinyuan.xyshop.ui.home.BrandActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luoxw on 2016/8/9.
 */
public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
	private static final String TAG = ExpandableItemAdapter.class.getSimpleName();

	public static final int TYPE_LEVEL_0 = 0;
	public static final int TYPE_MENU = 1;
	private List<ItemData> menuList = new ArrayList<>();


	public ExpandableItemAdapter(List<MultiItemEntity> data, List<ItemData> menuList) {
		super(data);
		addItemType(TYPE_LEVEL_0, R.layout.home_item_expandable);
		addItemType(TYPE_MENU, R.layout.home_item_expandable);
		this.menuList = menuList;
	}


	@Override
	protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
		switch (item.getItemType()) {

			case TYPE_LEVEL_0:

				final ExpandItem expandItem = (ExpandItem) item;
				holder.setText(R.id.home_menu_title, menuList.get(holder.getPosition()).getData());
				GlideImageLoader.setImage(mContext, menuList.get(holder.getPosition()).getImageUrl(), (ImageView) holder.getView(R.id.home_menu_img));


				holder.itemView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						int pos = holder.getAdapterPosition();
						Log.d(TAG, "Level 0 item pos: " + pos);
						if (expandItem.isExpanded()) {
							collapse(pos);


						} else {

							expand(pos);
							if(pos==0){
								Intent intent=new Intent(mContext,BrandActivity.class);
								mContext.startActivity(intent);
							}


						}


					}
				});
				break;
			case TYPE_MENU:
				final Menu menItem = (Menu) item;
				holder.setText(R.id.home_menu_title, menuList.get(holder.getPosition()).getData());
				GlideImageLoader.setImage(mContext, menuList.get(holder.getPosition()).getImageUrl(), (ImageView) holder.getView(R.id.home_menu_img));

				holder.itemView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						int pos = holder.getAdapterPosition();
						Log.d(TAG, "menu 0 item pos: " + pos);
						Intent intent=new Intent(mContext,BrandActivity.class);
						mContext.startActivity(intent);
					}
				});
				break;

		}


	}
}
