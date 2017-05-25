package com.xinyuan.xyshop.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.BuyData;
import com.xinyuan.xyshop.entity.GoodDetailVo;
import com.xinyuan.xyshop.entity.Goods;
import com.xinyuan.xyshop.entity.PreGoods;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/25.
 */

public class GoodDetailsSpecDialog extends Dialog {

	private HashMap<Integer, BuyData> buydatas;
	private Context context;
	private GoodDetailVo goodDetail;
	private Goods selectedGoods;
	private HashMap<Integer, PreGoods> preGoodsMap;
	private int allGoodsNum;

	@BindView(R.id.rv_spec)
	RecyclerView rv_spec;

	public GoodDetailsSpecDialog(Context context, GoodDetailVo goodDetail, HashMap<Integer, PreGoods> preGoodsMap, Goods selectedGoods, int allGoodsNum) {
		super(context, R.style.CommonDialog);
		this.context = context;
		this.goodDetail = goodDetail;
		this.selectedGoods = selectedGoods;
		this.preGoodsMap = preGoodsMap;
		this.allGoodsNum = allGoodsNum;
	}


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gooddetails_spec_dialog);
		ButterKnife.bind((Dialog) this);
	}


}
