package com.xinyuan.xyshop.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.sunfusheng.marqueeview.MarqueeView;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fx on 2017/5/4 0004.
 */

public class HomeShowViewHelper {
	private MyShopApplication application;
	private Context context;


	RecyclerView mRecyclerView;

	public HomeShowViewHelper(Context context, RecyclerView recyclerView) {
		this.context = context;
		this.mRecyclerView = recyclerView;
		//this.application = (MyShopApplication) context.getApplicationContext();
	}


	public void showBanner(final List<ItemData> itemList, View heandView) {

		ArrayList<String> images = new ArrayList<>();
		for (ItemData itemData : itemList) {
			images.add(itemData.getImageUrl());
		}

		final Banner banner = (Banner) heandView.findViewById(R.id.banner);

		banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
				.setImageLoader(new GlideImageLoader())
				.setImages(images)
				.setBannerAnimation(Transformer.DepthPage)
				.isAutoPlay(true)
				.setDelayTime(3000)
				.setIndicatorGravity(BannerConfig.CENTER)
				.start();
		banner.setOnBannerListener(new OnBannerListener() {
			@Override
			public void OnBannerClick(int position) {
				if (itemList != null) {

					ItemData itemData = itemList.get(position);
					OnImageViewClick(banner, itemData.getType(), itemData.getData(), true);
				}
			}
		});

	}

	public void showNotice(List<ItemData> itemList, View heandView) {
		List<String> notices = new ArrayList<>();
		for (ItemData itemData : itemList) {
			notices.add(itemData.getImageUrl());
		}

		MarqueeView marqueeView = (MarqueeView) heandView.findViewById(R.id.marqueeView);

		marqueeView.startWithList(notices);

	}


	public void showHomeMenu(List<ItemData> itemList, View heandView) {
		AddViewHolder addViewHolder = new AddViewHolder(this.context, R.layout.home_top);
		ImageView im1 = (ImageView) heandView.findViewById(R.id.home_menu_1);
		ImageView im2 = (ImageView) heandView.findViewById(R.id.home_menu_2);
		ImageView im3 = (ImageView) heandView.findViewById(R.id.home_menu_3);
		ImageView im4 = (ImageView) heandView.findViewById(R.id.home_menu_4);
		ImageView im5 = (ImageView) heandView.findViewById(R.id.home_menu_5);

		addViewHolder.setImage(im1, itemList.get(0).getImageUrl());
		addViewHolder.setImage(im2, itemList.get(1).getImageUrl());
		addViewHolder.setImage(im3, itemList.get(2).getImageUrl());
		addViewHolder.setImage(im4, itemList.get(3).getImageUrl());
		addViewHolder.setImage(im5, itemList.get(4).getImageUrl());


		OnImageViewClick(addViewHolder.getView(R.id.home_menu_1), itemList.get(0).getType(), itemList.get(1).getData(), false);
		OnImageViewClick(addViewHolder.getView(R.id.home_menu_2), itemList.get(1).getType(), itemList.get(2).getData(), false);
		OnImageViewClick(addViewHolder.getView(R.id.home_menu_3), itemList.get(2).getType(), itemList.get(3).getData(), false);
		OnImageViewClick(addViewHolder.getView(R.id.home_menu_4), itemList.get(3).getType(), itemList.get(4).getData(), false);
	}


	public void OnImageViewClick(View view, final String type, final String data, boolean isAD) {

	}

}
