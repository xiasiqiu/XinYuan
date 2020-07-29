package com.xinyuan.xyshop.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.ShopHelper;
import com.xinyuan.xyshop.bean.FavGoodBean;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;
import com.xinyuan.xyshop.ui.goods.search.SearchGoodShowActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.common.GlideImageLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/5.
 * 收藏商品列表Adapter
 */

public class FavAdapters extends BaseQuickAdapter<FavGoodBean, BaseViewHolder> {


	public static boolean isDetele = false;
	public static HashMap<Integer, String> choeseList = new HashMap<Integer, String>();

	public FavAdapters(int layoutResId, @Nullable List<FavGoodBean> data, boolean isDetele) {
		super(layoutResId, data);
		this.isDetele = isDetele;
	}

	@Override
	protected void convert(final BaseViewHolder helper, final FavGoodBean item) {
		CheckBox chekbox = helper.getView(R.id.chekbox);
		if (isDetele) { //是否编辑状态
			chekbox.setVisibility(View.VISIBLE);
			if (choeseList.containsValue(item.getGoodsId())) {
				chekbox.setChecked(true);
			} else {
				chekbox.setChecked(false);
			}
			chekbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
					if (b) {
						choeseList.put(helper.getPosition(), String.valueOf(item.getfId()));
					}
				}
			});
		} else {
			chekbox.setVisibility(View.GONE);
		}
		ImageView iv_fav_goodimage = helper.getView(R.id.iv_fav_goodimage);
		GlideImageLoader.setUrlImg(mContext, item.getGoodsPhoto(), iv_fav_goodimage);
		helper.setText(R.id.tv_fav_goodname, item.getGoodsName());
		helper.setText(R.id.tv_fav_price, mContext.getString(R.string.money_rmb) + ShopHelper.getPriceString(item.getGoodPrice()));
		RelativeLayout rl_good_info = helper.getView(R.id.rl_good_info);
		rl_good_info.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Map<String, String> params = new HashMap();
				params.put(Constants.GOODID, String.valueOf(item.getGoodsId()));
				params.put(Constants.GOODTYPE, String.valueOf(item.getGoodType()));
				CommUtil.gotoActivity(mContext, GoodDetailActivity.class, false, params);
			}
		});
		helper.setOnClickListener(R.id.bt_fav_sim, new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Map<String, String> params = new HashMap();
				params.put(Constants.KEYWORD, item.getGoodsName());
				CommUtil.gotoActivity(mContext, SearchGoodShowActivity.class, false, params);
			}
		});

	}
}

