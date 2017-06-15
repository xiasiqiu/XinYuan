package com.xinyuan.xyshop.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.GoodsAttrsAdapter;
import com.xinyuan.xyshop.adapter.SearchGoodListAdapter;
import com.xinyuan.xyshop.adapter.SimpleEvaluateAdapter;
import com.xinyuan.xyshop.adapter.TextLineAdapter;
import com.xinyuan.xyshop.entity.BuyData;
import com.xinyuan.xyshop.entity.GoodDetailVo;
import com.xinyuan.xyshop.entity.Goods;
import com.xinyuan.xyshop.entity.PreGoods;
import com.xinyuan.xyshop.model.GoodsDetailModel;
import com.xinyuan.xyshop.util.FullyLinearLayoutManager;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/25.
 */

public class GoodDetailsPromotionDialog extends Dialog {

	private List<String> salesPromotion;

	private Context context;
	@BindView(R.id.iv_close)
	ImageView iv_close;

	@BindView(R.id.rl_promotion)
	RecyclerView rl_promotion;

	TextLineAdapter adapter;

	public GoodDetailsPromotionDialog(Context context, List<String> salesPromotion) {
		super(context, R.style.CommonDialog);
		this.context = context;
		this.salesPromotion = salesPromotion;
	}


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_good_dialog_promotion);
		ButterKnife.bind((Dialog) this);


		adapter = new TextLineAdapter(R.layout.item_textline, salesPromotion);
		rl_promotion.setLayoutManager(new LinearLayoutManager(getContext()));
		rl_promotion.setFocusable(false);
		rl_promotion.setAdapter(adapter);


	}

	@OnClick(R.id.iv_close)
	public void onClick() {
		dismiss();
	}


}
