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
import com.xinyuan.xyshop.entity.Menu;
import com.xinyuan.xyshop.model.HomeModel;
import com.xinyuan.xyshop.ui.home.BrandActivity;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luoxw on 2016/8/9.
 */
public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
	private static final String TAG = ExpandableItemAdapter.class.getSimpleName();

	public static final int TYPE_LEVEL_0 = 0;
	public static final int TYPE_MENU = 1;
	private List<HomeModel.HomeModule.HomeModuleData> menuList = new ArrayList<>();


	public ExpandableItemAdapter(List<MultiItemEntity> data, List<HomeModel.HomeModule.HomeModuleData> menuList) {
		super(data);
		addItemType(TYPE_LEVEL_0, R.layout.fragment_home_item_expandable);
		addItemType(TYPE_MENU, R.layout.fragment_home_item_expandable);
		this.menuList = menuList;
	}


	@Override
	protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
		switch (item.getItemType()) {

			case TYPE_LEVEL_0:

				final ExpandItem expandItem = (ExpandItem) item;
				holder.setText(R.id.home_menu_title, menuList.get(holder.getPosition()).getText());
				GlideImageLoader.setImage(mContext, menuList.get(holder.getPosition()).getImageUrl(), (ImageView) holder.getView(R.id.home_menu_img));


				holder.itemView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						int pos = holder.getAdapterPosition();
						Log.d(TAG, "Level 1 item pos: " + pos);
						menuOnclick(menuList.get(pos).getType());

					}
				});


				break;
			case TYPE_MENU:
				final Menu menItem = (Menu) item;
				holder.setText(R.id.home_menu_title, menuList.get(holder.getPosition()).getText());
				GlideImageLoader.setImage(mContext, menuList.get(holder.getPosition()).getImageUrl(), (ImageView) holder.getView(R.id.home_menu_img));
				int pos = holder.getAdapterPosition();
				holder.itemView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						int pos = holder.getAdapterPosition();
						Log.d(TAG, "Level 2item pos: " + pos);
						menuOnclick(menuList.get(pos).getType());
						Intent intent=new Intent(mContext,BrandActivity.class);
						mContext.startActivity(intent);
					}
				});
				break;

		}


	}


	private void menuOnclick(String type) {

		if (type.equals("html")) {



		} else if (type.equals("native")) {




		}

	}


}
