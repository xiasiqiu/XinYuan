//package com.xinyuan.xyshop.widget.dialog;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.os.Bundle;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.text.Html;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.entity.MultiItemEntity;
//import com.xinyuan.xyshop.R;
//import com.xinyuan.xyshop.adapter.SelectDialogAdapter;
//import com.xinyuan.xyshop.bean.ExpandItem;
//import com.xinyuan.xyshop.common.ShopHelper;
//import com.xinyuan.xyshop.common.interfac.Separators;
//import com.xinyuan.xyshop.entity.BuyData;
//import com.xinyuan.xyshop.entity.GoodDetailVo;
//import com.xinyuan.xyshop.entity.Goods;
//import com.xinyuan.xyshop.entity.Menu;
//import com.xinyuan.xyshop.entity.PreGoods;
//import com.xinyuan.xyshop.entity.SelectFilterTest;
//import com.xinyuan.xyshop.model.GoodsDetailModel;
//import com.xinyuan.xyshop.util.CommUtil;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
///**
// * Created by Administrator on 2017/5/25.
// */
//
//public class GoodDetailsSpecDialog extends Dialog {
//	private int addAndMinusCount = 1;
//
//	private Context context;
//	private GoodsDetailModel detailModel;
//	private int allGoodsNum;
//
//	@BindView(R.id.rv_spec)
//	RecyclerView rv_spec;
//
//	public GoodDetailsSpecDialog(Context context, GoodsDetailModel detailModel) {
//		super(context, R.style.CommonDialog);
//		this.context = context;
//		this.detailModel = detailModel;
//	}
//
//
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.fragment_good_dialog_spec);
//		ButterKnife.bind((Dialog) this);
//
//		ArrayList<MultiItemEntity> res = new ArrayList<>();
//		ArrayList<SelectFilterTest.FilterKey> filterKeyList = new ArrayList<>();
//
//		for (SelectFilterTest.FilterKey key : filterKeyList) {
//			ExpandItem expandItem = new ExpandItem("", key.getCategoryName());
//			for (SelectFilterTest.FilterKey.KeyItem item : key.getKeyitem()) {
//				expandItem.addSubItem(new Menu("", item.getCategoryName()));
//			}
//			res.add(expandItem);
//		}
//
//
//		final SelectDialogAdapter selectDialogAdapter = new SelectDialogAdapter(res, filterKeyList);
//		final GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
//		manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//			@Override
//			public int getSpanSize(int position) {
//				return selectDialogAdapter.getItemViewType(position) == SelectDialogAdapter.TYPE_ITEM ? 1 : manager.getSpanCount();
//			}
//		});
//		rv_spec.setAdapter(selectDialogAdapter);
//		selectDialogAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//		rv_spec.setLayoutManager(manager);
//		selectDialogAdapter.expandAll();
//	}
//
//	@BindView(R.id.btnAppCommonAdd)
//	Button btnAppCommonAdd;
//
//	@BindView(R.id.btnAppCommonMinus)
//	Button btnAppCommonMinus;
//	@BindView(R.id.tvAppCommonCount)
//	TextView tvAppCommonCount;
//	@BindView(R.id.tvAllNumPrice)
//	TextView tvAllNumPrice;
//	@BindView(R.id.tvSelectedPrice)
//	TextView tvSelectedPrice;
//	private String moneyRmb;
//
//	@OnClick(R.id.btnAppCommonMinus)
//	public void btnAppCommonMinusClick() {
//		this.addAndMinusCount = Integer.valueOf(CommUtil.getText(this.tvAppCommonCount)).intValue();
//		this.addAndMinusCount--;
//		switchNumMinByGoodsModal(this.goodDetail.getGoodsModal());
//		this.tvAppCommonCount.setText(this.addAndMinusCount + "");
//	}
//
//	@OnClick(R.id.btnAppCommonAdd)
//	public void btnAppCommonAddClick() {
//		this.addAndMinusCount = Integer.valueOf(CommUtil.getText(this.tvAppCommonCount).trim()).intValue();
//		if (this.addAndMinusCount < detailModel.getStock()) {
//			this.addAndMinusCount++;
//			switchNumAddByGoodsModal(this.goodDetail.getGoodsModal());
//			this.tvAppCommonCount.setText(this.addAndMinusCount + "");
//		}
//	}
//
//	private void switchNumMinByGoodsModal(int goodsModal) {
//		switch (goodsModal) {
//			case 1:
//				if (this.addAndMinusCount < 1) {
//					this.addAndMinusCount = 1;
//				}
//				this.allGoodsNum = this.addAndMinusCount;
//				return;
//			case 2:
//				if (this.addAndMinusCount < 0) {
//					this.addAndMinusCount = 0;
//					this.preGoodsMap.remove(Integer.valueOf(this.selectedGoods.getGoodsId()));
//				} else {
//					this.preGoodsMap.put(Integer.valueOf(this.selectedGoods.getGoodsId()), new PreGoods(this.selectedGoods.getGoodsId(), this.addAndMinusCount, this.selectedGoods.getGoodsSpecString()));
//				}
//				updatePriceStringShow();
//				return;
//			default:
//				return;
//		}
//	}
//
//	private void switchNumAddByGoodsModal(int goodsModal) {
//		switch (goodsModal) {
//			case 1:
//				this.allGoodsNum = this.addAndMinusCount;
//				return;
//			case 2:
//				this.preGoodsMap.put(Integer.valueOf(this.selectedGoods.getGoodsId()), new PreGoods(this.selectedGoods.getGoodsId(), this.addAndMinusCount, this.selectedGoods.getGoodsSpecString()));
//				updatePriceStringShow();
//				return;
//			default:
//				return;
//		}
//	}
//
//	private void updatePriceStringShow() {
//		this.allGoodsNum = 0;
//		for (PreGoods preGoods : this.preGoodsMap.values()) {
//			this.allGoodsNum += Integer.valueOf(preGoods.getCount()).intValue();
//		}
//		if (this.allGoodsNum == 0) {
//			this.tvAllNumPrice.setText("");
//			return;
//		}
//		String singlePrice = getSinglePrice(this.goodDetail, this.allGoodsNum);
//		this.tvSelectedPrice.setText(this.moneyRmb + singlePrice);
//		double total = Double.valueOf(singlePrice).doubleValue() * ((double) this.allGoodsNum);
//		String[] allNumPriceStrings = String.format(this.allNumPrice, new Object[]{Integer.valueOf(this.allGoodsNum), this.goodDetail.getUnitName(), Double.valueOf(total)}).split(Separators.COMMA);
//		this.tvAllNumPrice.setText(Html.fromHtml("<font color=\"#555555\">" + allNumPriceStrings[0] + "</font><font color=\"#DE5765\">" + allNumPriceStrings[1] + "</font>"));
//	}
//
//	public static String getSinglePrice(GoodDetailVo goodDetail, int numAll) {
//		BigDecimal price = goodDetail.getAppPrice0();
//		if (goodDetail.getGoodsModal() == 2) {
//			if (numAll < goodDetail.getBatchNum0()) {
//				price = goodDetail.getAppPrice0();
//			} else if (goodDetail.getBatchNum0End() == 0) {
//				price = goodDetail.getAppPrice0();
//			} else if (goodDetail.getBatchNum1End() == 0) {
//				if (numAll >= goodDetail.getBatchNum1()) {
//					price = goodDetail.getAppPrice1();
//				} else {
//					price = goodDetail.getAppPrice0();
//				}
//			} else if (numAll >= goodDetail.getBatchNum2()) {
//				price = goodDetail.getAppPrice2();
//			} else if (numAll >= goodDetail.getBatchNum1()) {
//				price = goodDetail.getAppPrice1();
//			} else {
//				price = goodDetail.getAppPrice0();
//			}
//		}
//		return ShopHelper.getPriceString(price);
//	}
//}
