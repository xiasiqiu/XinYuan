package com.xinyuan.xyshop.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.HomeMultipleItem;
import com.xinyuan.xyshop.model.HomeModel;
import com.xinyuan.xyshop.mvp.presenter.HomePresenterImpl;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.youth.xframe.utils.log.XLog;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;


/**
 * Created by fx on 2017/5/2 0002.
 */

public class HomeMultipleItemAdapter extends BaseMultiItemQuickAdapter<HomeMultipleItem, BaseViewHolder> {
	private Context context;

	public static List<HomeModel.HomeModule> dataList;

	public HomeMultipleItemAdapter(Context context, List data) {
		super(data);
		addItemType(HomeMultipleItem.AD, R.layout.home_item_ad);
		addItemType(HomeMultipleItem.TAB_TITLE, R.layout.home_item_tab_title);
		addItemType(HomeMultipleItem.TAB, R.layout.home_item_tab);
		addItemType(HomeMultipleItem.CATEGORY, R.layout.home_item_category);
		this.context = context;
		dataList = HomePresenterImpl.getModuleList();
		XLog.list(data);
	}

	@Override
	protected void convert(BaseViewHolder helper, HomeMultipleItem item) {

		AutoUtils.autoSize(helper.getConvertView());

		switch (item.getItemType()) {
			case HomeMultipleItem.AD:

				List<HomeModel.HomeModule.HomeModuleData> adlist = dataList.get(helper.getLayoutPosition() + 2).getDataList();


				ImageView ad_view = helper.getView(R.id.home_ad_img);
				GlideImageLoader.setImage(context, adlist.get(0).getImageUrl(), ad_view);
				OnImageViewClick(ad_view, adlist.get(0).getType(), adlist.get(0).getData(), false);

				break;

			case HomeMultipleItem.TAB_TITLE:
				List<HomeModel.HomeModule.HomeModuleData> title_list = dataList.get(helper.getLayoutPosition() + 2).getDataList();
				ImageView tab_title_view = helper.getView(R.id.home_tab_title);
				GlideImageLoader.setImage(context, title_list.get(0).getImageUrl(), tab_title_view);

				break;
			case HomeMultipleItem.TAB:

				ImageView tab_view1 = helper.getView(R.id.home_tab_img1);
				ImageView tab_view2 = helper.getView(R.id.home_tab_img2);
				ImageView tab_view3 = helper.getView(R.id.home_tab_img3);
				ImageView tab_view4 = helper.getView(R.id.home_tab_img4);
				ImageView tab_view5 = helper.getView(R.id.home_tab_img5);
				ImageView tab_view6 = helper.getView(R.id.home_tab_img6);

				List<HomeModel.HomeModule.HomeModuleData> tablist = dataList.get(helper.getLayoutPosition() + 2).getDataList();
				GlideImageLoader.setImage(context, tablist.get(0).getImageUrl(), tab_view1);
				GlideImageLoader.setImage(context, tablist.get(1).getImageUrl(), tab_view2);
				GlideImageLoader.setImage(context, tablist.get(2).getImageUrl(), tab_view3);
				GlideImageLoader.setImage(context, tablist.get(3).getImageUrl(), tab_view4);
				GlideImageLoader.setImage(context, tablist.get(4).getImageUrl(), tab_view5);
				GlideImageLoader.setImage(context, tablist.get(5).getImageUrl(), tab_view6);

				break;
			case HomeMultipleItem.CATEGORY:


				List<HomeModel.HomeModule.HomeModuleData> catrgorylist = dataList.get(helper.getLayoutPosition() + 2).getDataList();

				ImageView catrgory_view = helper.getView(R.id.home_catrgory_img);
				GlideImageLoader.setImage(context, catrgorylist.get(0).getImageUrl(), catrgory_view);
				OnImageViewClick(catrgory_view, catrgorylist.get(0).getType(), catrgorylist.get(0).getData(), false);


				break;
			default:
				break;

		}


	}


	private void OnImageViewClick(View view, final String type, final String data, boolean isAD) {


	}

}
