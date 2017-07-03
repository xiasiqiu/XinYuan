package com.xinyuan.xyshop.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.GoodsAttrsAdapter;
import com.xinyuan.xyshop.callback.SKUInterface;
import com.xinyuan.xyshop.model.GoodsAttrsBean;
import com.xinyuan.xyshop.ui.buy.ConfirmOrderActivity;
import com.xinyuan.xyshop.ui.goods.GoodBusBean;
import com.xinyuan.xyshop.ui.mine.login.LoginActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/5/25.
 */

public class GoodDetailsSpecDialog extends Dialog implements SKUInterface {

	private Context context;
	private GoodsAttrsBean goodsAttrsBean;

	private GoodsAttrsAdapter mAdapter;

	@BindView(R.id.rv_sku)
	RecyclerView rv_sku;

	@BindView(R.id.tvGoodsName)
	TextView tvGoodsName;
	@BindView(R.id.tvSkuName)
	TextView tvSkuName;
	@BindView(R.id.tvSelectedPrice)
	TextView tvSelectedPrice;
	@BindView(R.id.tvSkuStorage)
	TextView tvSkuStorage;
	@BindView(R.id.ivSelectedGoodsImg)
	ImageView ivSelectedGoodsImg;


	public GoodDetailsSpecDialog(Context context, GoodsAttrsBean goodsAttrsBean) {
		super(context, R.style.CommonDialog);
		this.context = context;
		this.goodsAttrsBean = goodsAttrsBean;
	}

	String attrs;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_dialog_gooddetail_spec);
		EventBus.getDefault().register(this);
		ButterKnife.bind((Dialog) this);
		this.selecGood = goodsAttrsBean.getDefaultGood();
		mAdapter = new GoodsAttrsAdapter(context, goodsAttrsBean.getAttributes(), goodsAttrsBean.getStockGoods(), this.selecGood);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		rv_sku.setLayoutManager(layoutManager);
		rv_sku.setFocusable(false);
		mAdapter.setSKUInterface(this);
		rv_sku.setAdapter(mAdapter);
		init();

	}


	private void init() {
		tvAppCommonCount.setText("1");

		tvGoodsName.setText(goodsAttrsBean.getDefaultGood().getGoodsName());
		tvSkuStorage.setText("库存:" + goodsAttrsBean.getDefaultGood().getStock());
		tvSelectedPrice.setText("￥" + goodsAttrsBean.getDefaultGood().getPrice());
		XLog.v("默认产品" + selecGood.toString());
		for (int i = 0; i < selecGood.getGoodsInfo().size(); i++) {
			goodsAttrsBean.getAttributes().get(i);

			for (int j = 0; j < goodsAttrsBean.getAttributes().get(i).getAttributesItem().size(); j++) {
				if (goodsAttrsBean.getAttributes().get(i).getAttributesItem().get(j).getValueName().equals(selecGood.getGoodsInfo().get(i).getTabValue())) {
					if (!goodsAttrsBean.getAttributes().get(i).getAttributesItem().get(j).getValueImage().equals("")) {
						XLog.v("选中的是" + goodsAttrsBean.getAttributes().get(i).getAttributesItem().get(j).getValueImage());
						GlideImageLoader.setImage(context, goodsAttrsBean.getAttributes().get(i).getAttributesItem().get(j).getValueImage(), ivSelectedGoodsImg);
					}
				}
			}
		}

		attrs = "";
		for (int i = 0; i < goodsAttrsBean.getAttributes().size(); i++) {
			attrs += goodsAttrsBean.getAttributes().get(i).getTabName() + ";";

		}

		tvSkuName.setText("请选择:" + attrs);


	}

	private static GoodsAttrsBean.StockGoodsBean selecGood;

	@Subscribe
	public void onEventMainThread(GoodBusBean goodBusBean) {


	}

	@Override
	public void selectedAttribute(String[] attr) {
		String str = "";
		String ss = "";

		String Bs = "";
		boolean ok = false;
		for (int i = 0; i < attr.length; i++) {
			str += "" + goodsAttrsBean.getAttributes().get(i).getTabName() + ":";
			ss = TextUtils.isEmpty(attr[i]) ? "无" : attr[i];
			str += ss + "";

			if (TextUtils.isEmpty(attr[i])) {
				Bs += goodsAttrsBean.getAttributes().get(i).getTabName() + ";";
			}
		}
		for (GoodsAttrsBean.AttributesBean attributesBean : goodsAttrsBean.getAttributes()) {

			List<GoodsAttrsBean.AttributesBean.AttributeBean> list = attributesBean.getAttributesItem();
			for (GoodsAttrsBean.AttributesBean.AttributeBean attributeBean : list) {

				if (str.contains(attributeBean.getValueName())) {
					if (!attributeBean.getValueImage().equals("")) {
						GlideImageLoader.setImage(context, attributeBean.getValueImage(), ivSelectedGoodsImg);
					}
				}


			}


		}

		if (Bs.equals("")) {
			ok = true;
		}

		for (GoodsAttrsBean.StockGoodsBean goodsBean : goodsAttrsBean.getStockGoods()) {
			String goods = goodsBean.toString();
			goods = goods.substring(1, goods.length());
			goods = goods.substring(0, goods.length() - 1);
			goods = goods.replace(",", "");
			goods = goods.replace(" ", "");
			if (str.equals(goods)) {
				this.selecGood = goodsBean;
				tvSkuStorage.setText("库存:" + goodsBean.getStock());
				tvSelectedPrice.setText("￥" + goodsBean.getPrice());

			}
		}


		if (Bs.equals("")) {
			tvSkuName.setText("已选择:" + selecGood.getSpecText());
		} else {
			tvSkuName.setText("请选择:" + Bs);


		}


	}

	@Override
	public void uncheckAttribute(String[] attr) {

		XLog.v("attr" + attr.toString());

		String str = "";
		String ss = "";
		String Bs = "";
		for (int i = 0; i < attr.length; i++) {
			str += " " + goodsAttrsBean.getAttributes().get(i).getTabName() + "：";
			ss = TextUtils.isEmpty(attr[i]) ? "无" : attr[i];
			str += ss + " ";
			if (TextUtils.isEmpty(attr[i])) {
				Bs += goodsAttrsBean.getAttributes().get(i).getTabName() + ";";
			}
		}

		if (Bs.equals(attrs)) {
			init();
		}


	}

	private int addAndMinusCount = 1;

	@BindView(R.id.tvAppCommonCount)
	TextView tvAppCommonCount;

	@OnClick(R.id.btnAppCommonMinus)
	public void btnAppCommonMinusClick() {
		this.addAndMinusCount = Integer.valueOf(CommUtil.getText(this.tvAppCommonCount)).intValue();
		this.addAndMinusCount--;
		this.tvAppCommonCount.setText(this.addAndMinusCount + "");


	}

	@OnClick(R.id.btnAppCommonAdd)
	public void btnAppCommonAddClick() {
		this.addAndMinusCount = Integer.valueOf(CommUtil.getText(this.tvAppCommonCount).trim()).intValue();
		if (this.addAndMinusCount < this.selecGood.getStock()) {
			this.addAndMinusCount++;
			this.tvAppCommonCount.setText(this.addAndMinusCount + "");
		}

	}


	@Override
	public void dismiss() {
		XLog.v("退出啦");
		EventBus.getDefault().post(new GoodBusBean(GoodBusBean.SelectedGoods, this.selecGood));
		super.dismiss();
	}

	@OnClick({R.id.btnBuy, R.id.btnAddCart, R.id.tvOut})
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnBuy:
				Intent intent = new Intent(context, ConfirmOrderActivity.class);
				context.startActivity(intent);
				break;
			case R.id.btnAddCart:
				if (MyShopApplication.isLogin) {
					dismiss();
					EventBus.getDefault().post(new GoodBusBean(GoodBusBean.addShopCar, true));
				} else {
					CommUtil.gotoActivity(context, LoginActivity.class, false, null);
				}

				break;
			case R.id.tvOut:
				dismiss();
				break;

		}
	}


}
