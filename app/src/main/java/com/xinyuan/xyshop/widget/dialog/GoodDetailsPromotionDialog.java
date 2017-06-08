package com.xinyuan.xyshop.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.BuyData;
import com.xinyuan.xyshop.entity.GoodDetailVo;
import com.xinyuan.xyshop.entity.Goods;
import com.xinyuan.xyshop.entity.PreGoods;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/25.
 */

public class GoodDetailsPromotionDialog extends Dialog {

	private HashMap<Integer, BuyData> buydatas;
	private Context context;
	private GoodDetailVo goodDetail;
	private Goods selectedGoods;
	private HashMap<Integer, PreGoods> preGoodsMap;
	private int allGoodsNum;


	@BindView(R.id.iv_close)
	ImageView iv_close;
	public GoodDetailsPromotionDialog(Context context, GoodDetailVo goodDetail, HashMap<Integer, PreGoods> preGoodsMap, Goods selectedGoods, int allGoodsNum) {
		super(context, R.style.CommonDialog);
		this.context = context;
		this.goodDetail = goodDetail;
		this.selectedGoods = selectedGoods;
		this.preGoodsMap = preGoodsMap;
		this.allGoodsNum = allGoodsNum;
	}


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_good_dialog_promotion);
		ButterKnife.bind((Dialog) this);
	}

	@OnClick(R.id.iv_close)
	public void onClick(){
		dismiss();
	}


}
