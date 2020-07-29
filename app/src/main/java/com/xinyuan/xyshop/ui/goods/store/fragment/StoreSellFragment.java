package com.xinyuan.xyshop.ui.goods.store.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.model.StoreHomeModel;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by fx on 2017/6/1.
 * 店铺销量收藏界面
 */

public class StoreSellFragment extends BaseFragment {
	@BindView(R.id.iv_img_1)
	ImageView iv_img_1;
	@BindView(R.id.iv_img_2)
	ImageView iv_img_2;
	@BindView(R.id.iv_img_3)
	ImageView iv_img_3;
	@BindView(R.id.tv_sell_1)
	TextView tv_sell_1;
	@BindView(R.id.tv_sell_2)
	TextView tv_sell_2;
	@BindView(R.id.tv_sell_3)
	TextView tv_sell_3;


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
	public void initView(View rootView) {
		// TODO: 2017/9/8 预览代码，后期重写
		if (mTitle.equals("收藏")) {
			if (favgoodList.size() == 3) {
				GlideImageLoader.setUrlImg(getContext(), goodList.get(0).getGoodsImg(), iv_img_1);
				GlideImageLoader.setUrlImg(getContext(), goodList.get(1).getGoodsImg(), iv_img_2);
				GlideImageLoader.setUrlImg(getContext(), goodList.get(2).getGoodsImg(), iv_img_3);
				tv_sell_1.setText("已售:" + goodList.get(0).getGoodsSum() + "件");
				tv_sell_2.setText("已售:" + goodList.get(1).getGoodsSum() + "件");
				tv_sell_3.setText("已售:" + goodList.get(2).getGoodsSum() + "件");
				iv_img_1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Map<String, String> params = new HashMap();
						params.put(Constants.GOODID, String.valueOf(goodList.get(0).getGoodsId()));
						params.put(Constants.GOODTYPE, String.valueOf(goodList.get(0).getGoodsType()));
						CommUtil.gotoActivity(getActivity(), GoodDetailActivity.class, false, params);
					}
				});
				iv_img_2.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Map<String, String> params = new HashMap();
						params.put(Constants.GOODID, String.valueOf(goodList.get(1).getGoodsId()));
						params.put(Constants.GOODTYPE, String.valueOf(goodList.get(1).getGoodsType()));
						CommUtil.gotoActivity(getActivity(), GoodDetailActivity.class, false, params);
					}
				});
				iv_img_3.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Map<String, String> params = new HashMap();
						params.put(Constants.GOODID, String.valueOf(goodList.get(2).getGoodsId()));
						params.put(Constants.GOODTYPE, String.valueOf(goodList.get(2).getGoodsType()));
						CommUtil.gotoActivity(getActivity(), GoodDetailActivity.class, false, params);
					}
				});
			} else if (favgoodList.size() == 2) {
				GlideImageLoader.setUrlImg(getContext(), goodList.get(0).getGoodsImg(), iv_img_1);
				GlideImageLoader.setUrlImg(getContext(), goodList.get(1).getGoodsImg(), iv_img_2);
				tv_sell_1.setText("已售:" + goodList.get(0).getGoodsSum() + "件");
				tv_sell_2.setText("已售:" + goodList.get(1).getGoodsSum() + "件");
				iv_img_1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Map<String, String> params = new HashMap();
						params.put(Constants.GOODID, String.valueOf(goodList.get(0).getGoodsId()));
						params.put(Constants.GOODTYPE, String.valueOf(goodList.get(0).getGoodsType()));
						CommUtil.gotoActivity(getActivity(), GoodDetailActivity.class, false, params);
					}
				});
				iv_img_2.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Map<String, String> params = new HashMap();
						params.put(Constants.GOODID, String.valueOf(goodList.get(1).getGoodsId()));
						params.put(Constants.GOODTYPE, String.valueOf(goodList.get(1).getGoodsType()));
						CommUtil.gotoActivity(getActivity(), GoodDetailActivity.class, false, params);
					}
				});
			} else if (favgoodList.size() == 1) {
				GlideImageLoader.setUrlImg(getContext(), goodList.get(0).getGoodsImg(), iv_img_1);
				tv_sell_1.setText("已售:" + goodList.get(0).getGoodsSum() + "件");
				iv_img_1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Map<String, String> params = new HashMap();
						params.put(Constants.GOODID, String.valueOf(goodList.get(0).getGoodsId()));
						params.put(Constants.GOODTYPE, String.valueOf(goodList.get(0).getGoodsType()));
						CommUtil.gotoActivity(getActivity(), GoodDetailActivity.class, false, params);
					}
				});
			}


		} else if (mTitle.equals("销量")) {
			if (goodList.size() == 3) {
				GlideImageLoader.setUrlImg(getContext(), favgoodList.get(0).getGoodsImg(), iv_img_1);
				GlideImageLoader.setUrlImg(getContext(), favgoodList.get(1).getGoodsImg(), iv_img_2);
				GlideImageLoader.setUrlImg(getContext(), favgoodList.get(2).getGoodsImg(), iv_img_3);
				tv_sell_1.setText("已售:" + favgoodList.get(0).getGoodsSum() + "件");
				tv_sell_2.setText("已售:" + favgoodList.get(1).getGoodsSum() + "件");
				tv_sell_3.setText("已售:" + favgoodList.get(2).getGoodsSum() + "件");
				iv_img_1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Map<String, String> params = new HashMap();
						params.put(Constants.GOODID, String.valueOf(goodList.get(0).getGoodsId()));
						params.put(Constants.GOODTYPE, String.valueOf(goodList.get(0).getGoodsType()));
						CommUtil.gotoActivity(getActivity(), GoodDetailActivity.class, false, params);
					}
				});
				iv_img_2.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Map<String, String> params = new HashMap();
						params.put(Constants.GOODID, String.valueOf(goodList.get(1).getGoodsId()));
						params.put(Constants.GOODTYPE, String.valueOf(goodList.get(1).getGoodsType()));
						CommUtil.gotoActivity(getActivity(), GoodDetailActivity.class, false, params);
					}
				});
				iv_img_3.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Map<String, String> params = new HashMap();
						params.put(Constants.GOODID, String.valueOf(goodList.get(2).getGoodsId()));
						params.put(Constants.GOODTYPE, String.valueOf(goodList.get(2).getGoodsType()));
						CommUtil.gotoActivity(getActivity(), GoodDetailActivity.class, false, params);
					}
				});
			} else if (goodList.size() == 2) {
				GlideImageLoader.setUrlImg(getContext(), favgoodList.get(0).getGoodsImg(), iv_img_1);
				GlideImageLoader.setUrlImg(getContext(), favgoodList.get(1).getGoodsImg(), iv_img_2);
				tv_sell_1.setText("已售:" + favgoodList.get(0).getGoodsSum() + "件");
				tv_sell_2.setText("已售:" + favgoodList.get(1).getGoodsSum() + "件");
				iv_img_1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Map<String, String> params = new HashMap();
						params.put(Constants.GOODID, String.valueOf(goodList.get(0).getGoodsId()));
						params.put(Constants.GOODTYPE, String.valueOf(goodList.get(0).getGoodsType()));
						CommUtil.gotoActivity(getActivity(), GoodDetailActivity.class, false, params);
					}
				});
				iv_img_2.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Map<String, String> params = new HashMap();
						params.put(Constants.GOODID, String.valueOf(goodList.get(1).getGoodsId()));
						params.put(Constants.GOODTYPE, String.valueOf(goodList.get(1).getGoodsType()));
						CommUtil.gotoActivity(getActivity(), GoodDetailActivity.class, false, params);
					}
				});
			} else if (goodList.size() == 1) {
				GlideImageLoader.setUrlImg(getContext(), favgoodList.get(0).getGoodsImg(), iv_img_1);
				tv_sell_1.setText("已售:" + favgoodList.get(0).getGoodsSum() + "件");
				iv_img_1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Map<String, String> params = new HashMap();
						params.put(Constants.GOODID, String.valueOf(goodList.get(0).getGoodsId()));
						params.put(Constants.GOODTYPE, String.valueOf(goodList.get(0).getGoodsType()));
						CommUtil.gotoActivity(getActivity(), GoodDetailActivity.class, false, params);
					}
				});
			}
		}
	}

	@Override
	public void initData() {

	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_store_sell;

	}


}
