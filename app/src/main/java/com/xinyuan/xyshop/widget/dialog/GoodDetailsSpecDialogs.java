package com.xinyuan.xyshop.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.common.GoodHelper;
import com.xinyuan.xyshop.common.ShopHelper;
import com.xinyuan.xyshop.common.interfac.Separators;
import com.xinyuan.xyshop.entity.BookBean;
import com.xinyuan.xyshop.entity.BuyData;
import com.xinyuan.xyshop.entity.PreGoods;
import com.xinyuan.xyshop.model.GoodDetail.SpecJson;
import com.xinyuan.xyshop.model.GoodDetail;
import com.xinyuan.xyshop.ui.goods.GoodBusBean;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.youth.xframe.utils.log.XLog;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Title:
 * Description:
 * Created by Administrator on 2017/6/13.
 * 作者：fx on 2017/6/13 22:46
 */

public class GoodDetailsSpecDialogs extends Dialog {
	@BindView(R.id.ivSelectedGoodsImg)
	ImageView ivSelectedGoodsImg; //商品小图片
	@BindView(R.id.tvGoodsName)
	TextView tvGoodsName; //商品名称
	@BindView(R.id.tvSkuStorage)
	TextView tvSkuStorage; //商品库存
	@BindView(R.id.tvSelectedPrice)
	TextView tvSelectedPrice;   //商品价格
	@BindView(R.id.llBuyCart)
	LinearLayout llBuyCart; //购物车购买

	@BindView(R.id.btnBuy)
	Button btnBuy;          //立即购买
	@BindView(R.id.btnAddCart)
	Button btnAddCart;  // 加入购物车

	@BindView(R.id.tvAppCommonCount)
	TextView tvAppCommonCount;

	@BindView(R.id.llSpec)
	LinearLayout llSpec;

	@BindView(R.id.llSpec0)
	LinearLayout llSpec0;
	@BindView(R.id.llSpec1)
	LinearLayout llSpec1;
	@BindView(R.id.llSpec2)
	LinearLayout llSpec2;
	@BindView(R.id.llSpec3)
	LinearLayout llSpec3;

	@BindView(R.id.tvSpecName0)
	TextView tvSpecName0;
	@BindView(R.id.tvSpecName1)
	TextView tvSpecName1;
	@BindView(R.id.tvSpecName2)
	TextView tvSpecName2;
	@BindView(R.id.tvSpecName3)
	TextView tvSpecName3;

	@BindView(R.id.rvSpecList0)
	FlexboxLayout rvSpecList0;
	@BindView(R.id.rvSpecList1)
	FlexboxLayout rvSpecList1;
	@BindView(R.id.rvSpecList2)
	FlexboxLayout rvSpecList2;
	@BindView(R.id.rvSpecList3)
	FlexboxLayout rvSpecList3;


	private int addAndMinusCount = 1;   //初始数量

	private HashMap<Integer, BuyData> buydatas;
	private Context context;
	private GoodDetail goodDetail;
	private BookBean selectedBook = null;
	private GoodDetail.Good selectedGoods;
	private HashMap<Integer, PreGoods> preGoodsMap;
	private int allGoodsNum;
	private String allNumPrice;
	private String moneyRmb;

	private int selectedSpec0;
	private int selectedSpec1;
	private int selectedSpec2;
	private int selectedSpec3;
	private TextView selectedSpecTv0;
	private TextView selectedSpecTv1;
	private TextView selectedSpecTv2;
	private TextView selectedSpecTv3;

	private MyShopApplication application;

	public GoodDetailsSpecDialogs(Context context, GoodDetail goodDetail, HashMap<Integer, PreGoods> preGoodsMap, GoodDetail.Good selectedGoods, int allGoodsNum) {
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
		//初始化显示
		this.moneyRmb = this.context.getResources().getString(R.string.money_rmb);
		this.application = MyShopApplication.getInstance();
		GlideImageLoader.setImage(context, this.selectedGoods.getImageSrc(), this.ivSelectedGoodsImg);
		this.tvGoodsName.setText(this.goodDetail.getGoodsName());
		this.tvSkuStorage.setText("库存:" + this.selectedGoods.getGoodsStorage() + this.goodDetail.getUnitName());
		this.tvSelectedPrice.setText(this.moneyRmb + GoodHelper.getPriceStringAllShow(this.goodDetail, this.allGoodsNum));


		if (this.goodDetail.getGoodsModal() == 2) {
			updatePriceStringShow();
		}

		XLog.v("规格的size" + goodDetail.getGoodsSpecNameList().size());
		switchSpecBygoodsSpecNameList(this.goodDetail.getGoodsSpecNameList().size());
		switchCreateNumByGoodsModal(this.goodDetail.getGoodsModal());
		this.buydatas = new HashMap();

	}


	private void switchCreateNumByGoodsModal(int goodsModal) {
		switch (goodsModal) {
			case 1:

				this.addAndMinusCount = this.allGoodsNum;
				break;
			case 2:

				if (!this.preGoodsMap.containsKey(Integer.valueOf(this.selectedGoods.getGoodsId()))) {
					this.addAndMinusCount = 0;
					break;
				} else {
					this.addAndMinusCount = ((PreGoods) this.preGoodsMap.get(Integer.valueOf(this.selectedGoods.getGoodsId()))).getCount();
					break;
				}
		}
		this.tvAppCommonCount.setText(this.addAndMinusCount + "");
	}

	private void switchSpecBygoodsSpecNameList(int size) {
		switch (size) {
			case 1:
				this.llSpec0.setVisibility(View.VISIBLE);
				this.llSpec1.setVisibility(View.GONE);
				this.llSpec2.setVisibility(View.GONE);
				this.selectedSpec0 = 0;

				this.selectedSpec1 = Integer.MAX_VALUE;
				this.selectedSpec2 = Integer.MAX_VALUE;
				bindSpecByLlSpec(this.llSpec0);
				return;
			case 2:
				this.llSpec0.setVisibility(View.VISIBLE);
				this.llSpec1.setVisibility(View.VISIBLE);
				this.llSpec2.setVisibility(View.GONE);
				this.selectedSpec0 = 0;
				this.selectedSpec1 = 0;
				this.selectedSpec2 = Integer.MAX_VALUE;
				bindSpecByLlSpec(this.llSpec0, this.llSpec1);
				return;
			case 3:
				this.llSpec0.setVisibility(View.VISIBLE);
				this.llSpec1.setVisibility(View.VISIBLE);
				this.llSpec2.setVisibility(View.VISIBLE);
				this.selectedSpec0 = 0;
				this.selectedSpec1 = 0;
				this.selectedSpec2 = 0;
				bindSpecByLlSpec(this.llSpec0, this.llSpec1, this.llSpec2);
				return;
			case 4:
				this.llSpec0.setVisibility(View.VISIBLE);
				this.llSpec1.setVisibility(View.VISIBLE);
				this.llSpec2.setVisibility(View.VISIBLE);
				this.llSpec3.setVisibility(View.VISIBLE);
				this.selectedSpec0 = 0;
				this.selectedSpec1 = 0;
				this.selectedSpec2 = 0;
				this.selectedSpec3 = 0;
				bindSpecByLlSpec(this.llSpec0, this.llSpec1, this.llSpec2, this.llSpec3);
				return;
			default:
				this.llSpec.setVisibility(View.GONE);
				return;
		}
	}


	private void bindSpecByLlSpec(LinearLayout... linearLayouts) {
		for (LinearLayout ll : linearLayouts) {
			SpecJson specJson;
			AddViewHolder addViewHolder;


			if (ll.equals(this.llSpec0)) {
				specJson = (SpecJson) this.goodDetail.getSpecJson().get(0);
				this.tvSpecName0.setText(specJson.getSpecName() + "：");
				this.selectedSpec0 = Integer.valueOf(this.selectedGoods.getSpecValueIds().split(Separators.COMMA)[0]).intValue();
				updateSelectedGoodsId();

				for (final GoodDetail.SpecValueList specValueListVo : specJson.getSpecValueList()) {
					final TextView btnSpec;
					addViewHolder = new AddViewHolder(this.context, R.layout.recyclerview_item_one_button);
					btnSpec = (TextView) addViewHolder.getView(R.id.btnSpec);
					btnSpec.setText(specValueListVo.getSpecValueName());
					if (this.selectedSpec0 == specValueListVo.getSpecValueId()) {
						this.selectedSpecTv0 = btnSpec;
						btnSpec.setActivated(true);
					} else {
						btnSpec.setActivated(false);
					}
					btnSpec.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							if (!btnSpec.isActivated()) {
								GoodDetailsSpecDialogs.this.selectedSpecTv0.setActivated(false);
								GoodDetailsSpecDialogs.this.selectedSpecTv0 = btnSpec;
								GoodDetailsSpecDialogs.this.selectedSpecTv0.setActivated(true);
								GoodDetailsSpecDialogs.this.selectedSpec0 = specValueListVo.getSpecValueId();
								GoodDetailsSpecDialogs.this.updateSelectedGoodsId();
							}
						}
					});
					this.rvSpecList0.addView(addViewHolder.getCustomView());
				}


			} else if (ll.equals(this.llSpec1)) {
				specJson = (SpecJson) this.goodDetail.getSpecJson().get(1);
				this.tvSpecName1.setText(specJson.getSpecName() + "：");
				this.selectedSpec1 = Integer.valueOf(this.selectedGoods.getSpecValueIds().split(Separators.COMMA)[1]).intValue();
				updateSelectedGoodsId();
				for (final GoodDetail.SpecValueList specValueListVo2 : specJson.getSpecValueList()) {
					final TextView btnSpec;
					addViewHolder = new AddViewHolder(this.context, R.layout.recyclerview_item_one_button);
					btnSpec = (TextView) addViewHolder.getView(R.id.btnSpec);
					btnSpec.setText(specValueListVo2.getSpecValueName());
					if (this.selectedSpec1 == specValueListVo2.getSpecValueId()) {
						this.selectedSpecTv1 = btnSpec;
						btnSpec.setActivated(true);
					} else {
						btnSpec.setActivated(false);
					}
					btnSpec.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							if (!btnSpec.isActivated()) {
								GoodDetailsSpecDialogs.this.selectedSpecTv1.setActivated(false);
								GoodDetailsSpecDialogs.this.selectedSpecTv1 = btnSpec;
								GoodDetailsSpecDialogs.this.selectedSpecTv1.setActivated(true);
								GoodDetailsSpecDialogs.this.selectedSpec1 = specValueListVo2.getSpecValueId();
								GoodDetailsSpecDialogs.this.updateSelectedGoodsId();
							}
						}
					});
					this.rvSpecList1.addView(addViewHolder.getCustomView());
				}
			} else if (ll.equals(this.llSpec2)) {
				specJson = (SpecJson) this.goodDetail.getSpecJson().get(2);
				this.tvSpecName2.setText(specJson.getSpecName() + "：");
				this.selectedSpec2 = Integer.valueOf(this.selectedGoods.getSpecValueIds().split(Separators.COMMA)[2]).intValue();
				updateSelectedGoodsId();
				for (final GoodDetail.SpecValueList specValueListVo22 : specJson.getSpecValueList()) {
					final TextView btnSpec;
					addViewHolder = new AddViewHolder(this.context, R.layout.recyclerview_item_one_button);
					btnSpec = (TextView) addViewHolder.getView(R.id.btnSpec);
					btnSpec.setText(specValueListVo22.getSpecValueName());
					if (this.selectedSpec2 == specValueListVo22.getSpecValueId()) {
						this.selectedSpecTv2 = btnSpec;
						btnSpec.setActivated(true);
					} else {
						btnSpec.setActivated(false);
					}

					btnSpec.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							if (!btnSpec.isActivated()) {
								GoodDetailsSpecDialogs.this.selectedSpecTv2.setActivated(false);
								GoodDetailsSpecDialogs.this.selectedSpecTv2 = btnSpec;
								GoodDetailsSpecDialogs.this.selectedSpecTv2.setActivated(true);
								GoodDetailsSpecDialogs.this.selectedSpec2 = specValueListVo22.getSpecValueId();
								GoodDetailsSpecDialogs.this.updateSelectedGoodsId();
							}
						}
					});
					this.rvSpecList2.addView(addViewHolder.getCustomView());
				}
			} else if (ll.equals(this.llSpec3)) {
				specJson = (SpecJson) this.goodDetail.getSpecJson().get(3);
				this.tvSpecName3.setText(specJson.getSpecName() + "：");
				this.selectedSpec3 = Integer.valueOf(this.selectedGoods.getSpecValueIds().split(Separators.COMMA)[3]).intValue();
				updateSelectedGoodsId();
				for (final GoodDetail.SpecValueList specValueListVo221 : specJson.getSpecValueList()) {
					final TextView btnSpec;
					addViewHolder = new AddViewHolder(this.context, R.layout.recyclerview_item_one_button);
					btnSpec = (TextView) addViewHolder.getView(R.id.btnSpec);
					btnSpec.setText(specValueListVo221.getSpecValueName());
					if (this.selectedSpec3 == specValueListVo221.getSpecValueId()) {
						this.selectedSpecTv3 = btnSpec;
						btnSpec.setActivated(true);
					} else {
						btnSpec.setActivated(false);
					}

					btnSpec.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							if (!btnSpec.isActivated()) {
								GoodDetailsSpecDialogs.this.selectedSpecTv3.setActivated(false);
								GoodDetailsSpecDialogs.this.selectedSpecTv3 = btnSpec;
								GoodDetailsSpecDialogs.this.selectedSpecTv3.setActivated(true);
								GoodDetailsSpecDialogs.this.selectedSpec3 = specValueListVo221.getSpecValueId();
								GoodDetailsSpecDialogs.this.updateSelectedGoodsId();
							}
						}
					});
					this.rvSpecList3.addView(addViewHolder.getCustomView());
				}
			}
		}
	}


	private void updateSelectedGoodsId() {
		XLog.v("当前选择的商品" + selectedGoods.toString());
		String specValue = this.selectedSpec0 + "";


		if (this.selectedSpec1 != 0) {
			specValue = specValue + Separators.COMMA + this.selectedSpec1;
			if (this.selectedSpec2 != 0) {
				specValue = specValue + Separators.COMMA + this.selectedSpec2;
			}
			if (this.selectedSpec3 != 0) {
				specValue = specValue + Separators.COMMA + this.selectedSpec3;
			}
		}

		XLog.v("SpecValue" + specValue);
		for (GoodDetail.Good goods : this.goodDetail.getGoodsList()) {
			if (goods.getSpecValueIds().equals(specValue)) {
				this.selectedGoods = goods;
				XLog.v("选择的商品" + selectedGoods.toString());
				if (this.goodDetail.getAppUsable() == 1 && this.goodDetail.getPromotionType() == 3) {
					for (BookBean bookBean : this.goodDetail.getBookList()) {
						if (bookBean.getGoodsId() == this.selectedGoods.getGoodsId()) {
							this.selectedBook = bookBean;
						}
					}
				}
				GlideImageLoader.setImage(context, selectedGoods.getImageSrc(), ivSelectedGoodsImg);
				this.tvSkuStorage.setText("库存:" + this.selectedGoods.getGoodsStorage() + this.goodDetail.getUnitName());
				EventBus.getDefault().post(new GoodBusBean(GoodBusBean.SelectedGoods, this.selectedGoods));
				switchPriceChangeByGoodsModal(this.goodDetail.getGoodsModal());
				switchSpecChangeByGoodsModal(this.goodDetail.getGoodsModal());
				return;
			}
		}
	}
	@OnClick({R.id.tvOut})
	public void tvOutClick() {
		dismiss();
	}

	private void switchSpecChangeByGoodsModal(int goodsModal) {
		switch (goodsModal) {
			case 2:
				if (this.preGoodsMap.containsKey(Integer.valueOf(this.selectedGoods.getGoodsId()))) {
					this.addAndMinusCount = ((PreGoods) this.preGoodsMap.get(Integer.valueOf(this.selectedGoods.getGoodsId()))).getCount();
				} else {
					this.addAndMinusCount = 0;
				}
				this.tvAppCommonCount.setText(this.addAndMinusCount + "");
				return;
			default:
				return;
		}
	}

	private void switchPriceChangeByGoodsModal(int goodsModal) {
		switch (goodsModal) {
			case 1:
				if (this.selectedBook != null) {
					this.tvSelectedPrice.setText(this.moneyRmb + ShopHelper.getPriceString(this.selectedBook.getDownPayment()));
					return;
				} else {
					this.tvSelectedPrice.setText(this.moneyRmb + ShopHelper.getPriceString(this.selectedGoods.getAppPrice0()));
					return;
				}
			default:
				return;
		}
	}


	private void updatePriceStringShow() {
		this.allGoodsNum = 0;
		for (PreGoods preGoods : this.preGoodsMap.values()) {
			this.allGoodsNum += Integer.valueOf(preGoods.getCount()).intValue();
		}
		if (this.allGoodsNum == 0) {

			return;
		}
		String singlePrice = GoodHelper.getSinglePrice(this.goodDetail, this.allGoodsNum);
		this.tvSelectedPrice.setText(this.moneyRmb + singlePrice);
		double total = Double.valueOf(singlePrice).doubleValue() * ((double) this.allGoodsNum);
		String[] allNumPriceStrings = String.format(this.allNumPrice, new Object[]{Integer.valueOf(this.allGoodsNum), this.goodDetail.getUnitName(), Double.valueOf(total)}).split(Separators.COMMA);
	}

	@OnClick({R.id.btnAppCommonMinus})
	public void btnAppCommonMinusClick() {
		this.addAndMinusCount = Integer.valueOf(CommUtil.getText(this.tvAppCommonCount)).intValue();
		this.addAndMinusCount--;
		switchNumMinByGoodsModal(this.goodDetail.getGoodsModal());
		this.tvAppCommonCount.setText(this.addAndMinusCount + "");
	}

	@OnClick({R.id.btnAppCommonAdd})
	public void btnAppCommonAddClick() {
		this.addAndMinusCount = Integer.valueOf(CommUtil.getText(this.tvAppCommonCount).trim()).intValue();
		if (this.addAndMinusCount < this.selectedGoods.getGoodsStorage()) {
			this.addAndMinusCount++;
			switchNumAddByGoodsModal(this.goodDetail.getGoodsModal());
			this.tvAppCommonCount.setText(this.addAndMinusCount + "");
		}
	}

	private void switchNumMinByGoodsModal(int goodsModal) {
		switch (goodsModal) {
			case 1:
				if (this.addAndMinusCount < 1) {
					this.addAndMinusCount = 1;
				}
				this.allGoodsNum = this.addAndMinusCount;
				return;
			case 2:
				if (this.addAndMinusCount < 0) {
					this.addAndMinusCount = 0;
					this.preGoodsMap.remove(Integer.valueOf(this.selectedGoods.getGoodsId()));
				} else {
					this.preGoodsMap.put(Integer.valueOf(this.selectedGoods.getGoodsId()), new PreGoods(this.selectedGoods.getGoodsId(), this.addAndMinusCount, this.selectedGoods.getGoodsSpecString()));
				}
				updatePriceStringShow();
				return;
			default:
				return;
		}
	}

	private void switchNumAddByGoodsModal(int goodsModal) {
		switch (goodsModal) {
			case 1:
				this.allGoodsNum = this.addAndMinusCount;
				return;
			case 2:
				this.preGoodsMap.put(Integer.valueOf(this.selectedGoods.getGoodsId()), new PreGoods(this.selectedGoods.getGoodsId(), this.addAndMinusCount, this.selectedGoods.getGoodsSpecString()));
				updatePriceStringShow();
				return;
			default:
				return;
		}
	}
}
