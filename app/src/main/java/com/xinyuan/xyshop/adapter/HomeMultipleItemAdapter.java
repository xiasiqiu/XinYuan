package com.xinyuan.xyshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.common.DataTrans;
import com.xinyuan.xyshop.entity.ApiSpecialItem;
import com.xinyuan.xyshop.entity.HomeMultipleItem;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.entity.ItemGoods;
import com.xinyuan.xyshop.mvp.presenter.HomePresenterImpl;
import com.xinyuan.xyshop.ui.goods.GoodDetailsActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.youth.xframe.utils.log.XLog;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;


/**
 * Created by fx on 2017/5/4 0004.
 */

public class HomeMultipleItemAdapter extends BaseMultiItemQuickAdapter<HomeMultipleItem, BaseViewHolder> {
	private Context context;


	public static List<ItemGoods> goodsList;
	public static List<ItemData> tabTitleList;
	public static List<ItemData> categoryList;
	public static List<List<ItemData>> tablist;
	public static List<ItemData> adList;

	public static List<ApiSpecialItem> dataList;
	private List<ItemGoods> goodlist;

	/**
	 * Same as QuickAdapter#QuickAdapter(Context,int) but with
	 * some initialization data.
	 *
	 * @param data A new list is created out of this one to avoid mutable list
	 */
	public HomeMultipleItemAdapter(Context context, List data) {
		super(data);
		addItemType(HomeMultipleItem.AD, R.layout.home_item_ad);
		addItemType(HomeMultipleItem.TAB_TITLE, R.layout.home_item_tab_title);
		addItemType(HomeMultipleItem.TAB, R.layout.home_item_tab);
		addItemType(HomeMultipleItem.CATEGORY, R.layout.home_item_category);
		addItemType(HomeMultipleItem.GOODS, R.layout.home_item_goods);
		this.context = context;
		dataList = HomePresenterImpl.getApiDataList();
		goodlist = DataTrans.getItemGoodsList(dataList.get(13).getItemData());
		XLog.list(goodsList);

	}

	@Override
	protected void convert(BaseViewHolder helper, HomeMultipleItem item) {

		AutoUtils.autoSize(helper.getConvertView());

		switch (item.getItemType()) {
			case HomeMultipleItem.AD:

				List<ItemData> adlist = DataTrans.getItemData(dataList.get(helper.getPosition() - 1));
				ImageView ad_view = helper.getView(R.id.home_ad_img);
				GlideImageLoader.setImage(context, adlist.get(0).getImageUrl(), ad_view);
				OnImageViewClick(ad_view, adlist.get(0).getType(), adlist.get(0).getData(), false);
				break;

			case HomeMultipleItem.TAB_TITLE:

				List<ItemData> title_list = DataTrans.getItemData(dataList.get(helper.getPosition() - 1));
				ImageView tab_title_view = helper.getView(R.id.home_tab_title);
				XLog.v("title"+title_list.get(0).getImageUrl());
				GlideImageLoader.setImage(context, title_list.get(0).getImageUrl(), tab_title_view);
				break;
			case HomeMultipleItem.TAB:


				ImageView tab_view1 = helper.getView(R.id.home_tab_img1);
				ImageView tab_view2 = helper.getView(R.id.home_tab_img2);
				ImageView tab_view3 = helper.getView(R.id.home_tab_img3);
				ImageView tab_view4 = helper.getView(R.id.home_tab_img4);
				ImageView tab_view5 = helper.getView(R.id.home_tab_img5);
				ImageView tab_view6 = helper.getView(R.id.home_tab_img6);

				List<ItemData> tablist = DataTrans.getItemData(dataList.get(helper.getPosition() - 1));
				GlideImageLoader.setImage(context, tablist.get(0).getImageUrl(), tab_view1);
				GlideImageLoader.setImage(context, tablist.get(1).getImageUrl(), tab_view2);
				GlideImageLoader.setImage(context, tablist.get(2).getImageUrl(), tab_view3);
				GlideImageLoader.setImage(context, tablist.get(3).getImageUrl(), tab_view4);
				GlideImageLoader.setImage(context, tablist.get(4).getImageUrl(), tab_view5);
				GlideImageLoader.setImage(context, tablist.get(5).getImageUrl(), tab_view6);

				break;
			case HomeMultipleItem.CATEGORY:


				List<ItemData> catrgorylist = DataTrans.getItemData(dataList.get(helper.getPosition() - 1));

				ImageView catrgory_view = helper.getView(R.id.home_catrgory_img);

				GlideImageLoader.setImage(context, catrgorylist.get(0).getImageUrl(), catrgory_view);


				OnImageViewClick(catrgory_view, catrgorylist.get(0).getType(), catrgorylist.get(0).getData(), false);

				XLog.v("CATEGORYPosition" + helper.getLayoutPosition() + "-----" + helper.getPosition());
				break;
			case HomeMultipleItem.GOODS:
				XLog.v("Position" + "-----" + helper.getPosition());

				AutoLinearLayout autoRelativeLayout = helper.getView(R.id.home_rl_goods);
				ImageView goods_view = helper.getView(R.id.home_goods_img);
				TextView goods_name = helper.getView(R.id.home_goods_name);
				GlideImageLoader.setImage(context, goodlist.get(helper.getPosition() - 14).getImageUrl(), goods_view);
				goods_name.setText(goodlist.get(helper.getPosition() - 14).getGoodsName());
				autoRelativeLayout.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Intent intent = new Intent(context, GoodDetailsActivity.class);
						context.startActivity(intent);
					}
				});

				TextView goods_price = helper.getView(R.id.tv_goods_price);
				goods_price.setText("$" + String.valueOf((int) goodlist.get(helper.getPosition() - 14).getAppPriceMin()));
				TextView goods_buynum = helper.getView(R.id.tv_goods_buynum);
				goods_buynum.setText("月销量" + String.valueOf((int) goodlist.get(helper.getPosition() - 14).getCommonId()));
				break;

		}


	}


	private void OnImageViewClick(View view, final String type, final String data, boolean isAD) {


	}


	private void OnGoodsViewClick(View view, final ItemGoods goods) {

		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, GoodDetailsActivity.class);
				intent.putExtra("commonId", goods.getCommonId());
				context.startActivity(intent);
			}
		});

	}


}
