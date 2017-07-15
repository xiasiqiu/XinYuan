package com.xinyuan.xyshop.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.ExpandItem;
import com.xinyuan.xyshop.entity.Menu;
import com.xinyuan.xyshop.even.MainFragmentStartEvent;
import com.xinyuan.xyshop.model.HomeModel;
import com.xinyuan.xyshop.ui.goods.groupbuy.GroupBuyActivity;
import com.xinyuan.xyshop.ui.home.BrandFragment;
import com.xinyuan.xyshop.ui.home.CreditMallActivity;
import com.xinyuan.xyshop.ui.home.MenuActivity;
import com.xinyuan.xyshop.ui.home.WebViewActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.xinyuan.xyshop.widget.dialog.SignDialog;
import com.youth.xframe.utils.log.XLog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
						menuOnclick(menuList.get(pos));

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
						menuOnclick(menuList.get(pos));
					}
				});
				break;

		}


	}


	private void menuOnclick(HomeModel.HomeModule.HomeModuleData data) {
		Map<String, String> params = new HashMap();
		if (data.getType().equals("html")) {
			params.put("url", data.getData());
			CommUtil.gotoActivity(mContext, WebViewActivity.class, false, params);

		} else if (data.getType().equals("native")) {
			if (data.getText().equals(mContext.getResources().getString(R.string.brandtitle))) {
				EventBus.getDefault().post(new MainFragmentStartEvent(BrandFragment.newInstance()));
			} else if (data.getText().equals("积分中心")) {
				CommUtil.gotoActivity(mContext, CreditMallActivity.class, false, null);
			} else if (data.getText().equals("团购商城")) {
				CommUtil.gotoActivity(mContext, GroupBuyActivity.class, false, null);
			} else if (data.getText().equals("签到")) {
				SignDialog.Builder builder = new SignDialog.Builder(mContext);
				builder.create().show();
				final SignDialog dialog = new SignDialog.Builder(mContext).create();
			}else  if(data.getText().equals("查看更多")){
				CommUtil.gotoActivity(mContext, MenuActivity.class,false,null);
			}


		}

	}


}
