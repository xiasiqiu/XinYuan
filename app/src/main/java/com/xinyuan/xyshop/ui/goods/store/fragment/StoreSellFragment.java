package com.xinyuan.xyshop.ui.goods.store.fragment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.model.StoreHomeModel;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/1.
 */

public class StoreSellFragment extends BaseFragment {


	@BindView(R.id.iv_img_1)
	ImageView iv_img_1;
	@BindView(R.id.iv_img_2)
	ImageView iv_img_2;
	@BindView(R.id.iv_img_3)
	ImageView iv_img_3;
	@BindView(R.id.iv_img_4)
	ImageView iv_img_4;

	@BindView(R.id.tv_sell_1)
	TextView tv_sell_1;
	@BindView(R.id.tv_sell_2)
	TextView tv_sell_2;
	@BindView(R.id.tv_sell_3)
	TextView tv_sell_3;
	@BindView(R.id.tv_sell_4)
	TextView tv_sell_4;


	private String mTitle;
	private static List<StoreHomeModel.CollGood> goodList = new ArrayList<>();
	private static List<StoreHomeModel.CollGood> favgoodList = new ArrayList<>();

	public static StoreSellFragment newInstance(String mTitle, List<StoreHomeModel.CollGood> favgoodList, List<StoreHomeModel.CollGood> goodList) {
		StoreSellFragment fragment = new StoreSellFragment();
		fragment.mTitle = mTitle;
		fragment.favgoodList = favgoodList;
		fragment.goodList = goodList;

		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_store_sell;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		if (mTitle.equals("收藏")) {
			GlideImageLoader.setImage(getContext(), goodList.get(0).getGoodImg(), iv_img_1);
			GlideImageLoader.setImage(getContext(), goodList.get(1).getGoodImg(), iv_img_2);
			GlideImageLoader.setImage(getContext(), goodList.get(2).getGoodImg(), iv_img_3);
			GlideImageLoader.setImage(getContext(), goodList.get(3).getGoodImg(), iv_img_4);

			tv_sell_1.setText("已售:" + goodList.get(0).getGoodSellNum());
			tv_sell_2.setText("已售:" + goodList.get(1).getGoodSellNum());
			tv_sell_3.setText("已售:" + goodList.get(2).getGoodSellNum());
			tv_sell_4.setText("已售:" + goodList.get(3).getGoodSellNum());
		} else if (mTitle.equals("销量")) {
			GlideImageLoader.setImage(getContext(), favgoodList.get(0).getGoodImg(), iv_img_1);
			GlideImageLoader.setImage(getContext(), favgoodList.get(1).getGoodImg(), iv_img_2);
			GlideImageLoader.setImage(getContext(), favgoodList.get(2).getGoodImg(), iv_img_3);
			GlideImageLoader.setImage(getContext(), favgoodList.get(3).getGoodImg(), iv_img_4);

			tv_sell_1.setText("已售:" + favgoodList.get(0).getGoodSellNum());
			tv_sell_2.setText("已售:" + favgoodList.get(1).getGoodSellNum());
			tv_sell_3.setText("已售:" + favgoodList.get(2).getGoodSellNum());
			tv_sell_4.setText("已售:" + favgoodList.get(3).getGoodSellNum());
		}


	}
}
