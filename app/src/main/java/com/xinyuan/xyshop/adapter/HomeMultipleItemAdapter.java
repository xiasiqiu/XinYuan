package com.xinyuan.xyshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.ApiSpecialItem;
import com.xinyuan.xyshop.entity.HomeMultipleItem;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.entity.ItemGoods;
import com.xinyuan.xyshop.mvp.presenter.HomePresenterImpl;
import com.xinyuan.xyshop.ui.goods.GoodDetailsActivity;
import com.xinyuan.xyshop.util.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by fx on 2017/5/4 0004.
 */

public class HomeMultipleItemAdapter extends BaseMultiItemQuickAdapter<HomeMultipleItem, BaseViewHolder> {
	private Context context;
	public static List<ApiSpecialItem> ADMultipleItemlist;
	public static List<ApiSpecialItem> TAB_TITLE_MultipleItemlist;
	public static List<ApiSpecialItem> TAB_MultipleItemlist;
	public static List<ApiSpecialItem> CATEGORY_MultipleItemlist;

	public static int tab_title_num = 0;
	public static int ad_num = 0;
	public static int tab_num = 0;
	public static int category_num = 0;
	public static int goods_num = 0;


	public static List<ItemGoods> goodsList;

	public static List<ItemData> tabTitleList;
	public static List<ItemData> categoryList;
	public static List<List<ItemData>> tablist;
	public static List<ItemData> adList;


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

		goodsList = HomePresenterImpl.getItemGoodsList();
		tabTitleList = HomePresenterImpl.getItemTabTitleList();
		categoryList = HomePresenterImpl.getCategoryList();
		tablist = HomePresenterImpl.getTabList();
		adList = HomePresenterImpl.getItemADList();


	}

	@Override
	protected void convert(BaseViewHolder helper, HomeMultipleItem item) {


		switch (item.getItemType()) {
			case HomeMultipleItem.AD:
				if (ad_num == adList.size()) {
					ad_num = 0;
				}

				ImageView ad_view = helper.getView(R.id.home_ad_img);
				GlideImageLoader.setImage(context, adList.get(ad_num).getImageUrl(), ad_view);
				OnImageViewClick(ad_view, adList.get(ad_num).getType(), adList.get(ad_num).getData(), true);
				ad_num++;
				break;

			case HomeMultipleItem.TAB_TITLE:
				if (tab_title_num == tabTitleList.size()) {
					tab_title_num = 0;
				}
				ImageView tab_title_view = helper.getView(R.id.home_tab_title);
				GlideImageLoader.setImage(context, tabTitleList.get(tab_title_num).getImageUrl(), tab_title_view);
				OnImageViewClick(tab_title_view, tabTitleList.get(tab_title_num).getType(), tabTitleList.get(tab_title_num).getData(), false);
				tab_title_num++;

				break;
			case HomeMultipleItem.TAB:
				if (tab_num == tablist.size()) {
					tab_num = 0;
				}
				ImageView tab_view1 = helper.getView(R.id.home_tab_img1);
				ImageView tab_view2 = helper.getView(R.id.home_tab_img2);
				ImageView tab_view3 = helper.getView(R.id.home_tab_img3);
				ImageView tab_view4 = helper.getView(R.id.home_tab_img4);
				ImageView tab_view5 = helper.getView(R.id.home_tab_img5);
				ImageView tab_view6 = helper.getView(R.id.home_tab_img6);
				List<ItemData> tabitemlist = tablist.get(tab_num);
				GlideImageLoader.setImage(context, tabitemlist.get(0).getImageUrl(), tab_view1);
				OnImageViewClick(tab_view1, tabitemlist.get(0).getType(), tabitemlist.get(0).getData(), false);

				GlideImageLoader.setImage(context, tabitemlist.get(1).getImageUrl(), tab_view2);
				OnImageViewClick(tab_view2, tabitemlist.get(1).getType(), tabitemlist.get(1).getData(), false);

				GlideImageLoader.setImage(context, tabitemlist.get(2).getImageUrl(), tab_view3);
				OnImageViewClick(tab_view3, tabitemlist.get(2).getType(), tabitemlist.get(2).getData(), false);

				GlideImageLoader.setImage(context, tabitemlist.get(3).getImageUrl(), tab_view4);
				OnImageViewClick(tab_view4, tabitemlist.get(3).getType(), tabitemlist.get(3).getData(), false);

				GlideImageLoader.setImage(context, tabitemlist.get(4).getImageUrl(), tab_view5);
				OnImageViewClick(tab_view5, tabitemlist.get(4).getType(), tabitemlist.get(4).getData(), false);

				GlideImageLoader.setImage(context, tabitemlist.get(5).getImageUrl(), tab_view6);
				OnImageViewClick(tab_view6, tabitemlist.get(5).getType(), tabitemlist.get(5).getData(), false);
				tab_num++;

				break;
			case HomeMultipleItem.CATEGORY:
				if (category_num == categoryList.size()) {
					category_num = 0;
				}
				ImageView catrgory_view = helper.getView(R.id.home_catrgory_img);
				GlideImageLoader.setImage(context, categoryList.get(category_num).getImageUrl(), catrgory_view);
				OnImageViewClick(catrgory_view, categoryList.get(category_num).getType(), categoryList.get(category_num).getData(), false);

				category_num++;

				break;
			case HomeMultipleItem.GOODS:
				if (goods_num == goodsList.size()) {
					goods_num = 0;
				}
				ImageView goods_view = helper.getView(R.id.home_goods_img);
				TextView goods_name = helper.getView(R.id.home_goods_name);
				GlideImageLoader.setImage(context, goodsList.get(goods_num).getImageUrl(), goods_view);
				goods_name.setText(goodsList.get(goods_num).getGoodsName());
				OnGoodsViewClick(goods_view, goodsList.get(goods_num));
				goods_num++;
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
