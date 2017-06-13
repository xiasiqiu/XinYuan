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
import com.xinyuan.xyshop.entity.GoodDetailVo;
import com.xinyuan.xyshop.entity.Goods;
import com.xinyuan.xyshop.entity.PreGoods;
import com.xinyuan.xyshop.entity.SpecJsonVo;
import com.xinyuan.xyshop.entity.SpecValueListVo;
import com.xinyuan.xyshop.ui.goods.GoodBusBean;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.GlideImageLoader;

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
    ImageView ivSelectedGoodsImg;
    @BindView(R.id.tvGoodsName)
    TextView tvGoodsName;
    @BindView(R.id.tvSkuStorage)
    TextView tvSkuStorage;
    @BindView(R.id.tvSelectedPrice)
    TextView tvSelectedPrice;
    @BindView(R.id.llBuyCart)
    LinearLayout llBuyCart;
    @BindView(R.id.llPreBottom)
    LinearLayout llPreBottom;
    @BindView(R.id.tvAllNumPrice)
    TextView tvAllNumPrice;
    @BindView(R.id.btnBuy)
    Button btnBuy;
    @BindView(R.id.btnAddCart)
    Button btnAddCart;
    @BindView(R.id.btnArrivalNotice)
    Button btnArrivalNotice;
    private HashMap<Integer, BuyData> buydatas;
    private Context context;
    private GoodDetailVo goodDetail;
    private BookBean selectedBook = null;
    private Goods selectedGoods;
    private HashMap<Integer, PreGoods> preGoodsMap;
    private int allGoodsNum;
    private String allNumPrice;
    private String moneyRmb;

    private int selectedSpec0;
    private int selectedSpec1;
    private int selectedSpec2;
    private TextView selectedSpecTv0;
    private TextView selectedSpecTv1;
    private TextView selectedSpecTv2;

    private MyShopApplication application;

    public GoodDetailsSpecDialogs(Context context, GoodDetailVo goodDetail, HashMap<Integer, PreGoods> preGoodsMap, Goods selectedGoods, int allGoodsNum) {
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
        EventBus.getDefault().register(this);
        this.allNumPrice = this.context.getResources().getString(R.string.goods_details_spec_select_all);
        this.moneyRmb = this.context.getResources().getString(R.string.money_rmb);
        this.application = MyShopApplication.getInstance();
        GlideImageLoader.setImage(context, this.selectedGoods.getImageSrc(), this.ivSelectedGoodsImg);
        this.tvGoodsName.setText(this.goodDetail.getGoodsName());
        this.tvSkuStorage.setText("库存:" + this.selectedGoods.getGoodsStorage() + this.goodDetail.getUnitName());
        if (this.goodDetail.getAppUsable() == 1 && this.goodDetail.getPromotionType() == 3) {
            this.selectedBook = this.goodDetail.getBook();
            this.tvSelectedPrice.setText(this.moneyRmb + ShopHelper.getPriceString(this.selectedBook.getDownPayment()));
            this.llBuyCart.setVisibility(View.GONE);
            this.llPreBottom.setVisibility(View.VISIBLE);
        } else {
            this.llBuyCart.setVisibility(View.VISIBLE);
            this.llPreBottom.setVisibility(View.GONE);
            this.tvSelectedPrice.setText(this.moneyRmb + GoodHelper.getPriceStringAllShow(this.goodDetail, this.allGoodsNum));
        }

        if (this.goodDetail.getGoodsModal() == 2) {
            updatePriceStringShow();
        }
        setArrivalNoticeShow(this.goodDetail.getGoodsModal());
        switchSpecBygoodsSpecNameList(this.goodDetail.getGoodsSpecNameList().size());
        switchCreateNumByGoodsModal(this.goodDetail.getGoodsModal());
        discountController();
        this.buydatas = new HashMap();

    }

    @BindView(R.id.llSpec0)
    LinearLayout llSpec0;
    @BindView(R.id.llSpec1)
    LinearLayout llSpec1;
    @BindView(R.id.llSpec2)
    LinearLayout llSpec2;
    @BindView(R.id.llSpec)
    LinearLayout llSpec;
    @BindView(R.id.btnEditSelectedList)
    Button btnEditSelectedList;
    @BindView(R.id.tvPromotionType)
    TextView tvPromotionType;

    private void discountController() {
        if (this.goodDetail.getAppUsable() == 1 && this.goodDetail.getPromotionType() == 1 && this.goodDetail.getDiscount() != null) {
            this.tvPromotionType.setVisibility(View.VISIBLE);
            this.tvPromotionType.setText(this.goodDetail.getPromotionTypeText());
        } else if (this.goodDetail.getAppUsable() == 1 && this.goodDetail.getPromotionType() == 3) {
            this.tvPromotionType.setVisibility(View.VISIBLE);
            this.tvPromotionType.setText(this.goodDetail.getPromotionTypeText());
        }
    }

    private void switchCreateNumByGoodsModal(int goodsModal) {
        switch (goodsModal) {
            case 1:
                this.btnEditSelectedList.setVisibility(View.GONE);
                this.addAndMinusCount = this.allGoodsNum;
                break;
            case 2:
                this.btnEditSelectedList.setVisibility(View.VISIBLE);
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
            default:
                this.llSpec.setVisibility(View.GONE);
                return;
        }
    }

    @BindView(R.id.tvSpecName0)
    TextView tvSpecName0;
    @BindView(R.id.rvSpecList0)
    FlexboxLayout rvSpecList0;

    private void bindSpecByLlSpec(LinearLayout... linearLayouts) {
        for (LinearLayout ll : linearLayouts) {
            SpecJsonVo specJson;
            AddViewHolder addViewHolder;
            TextView btnSpec;
            if (ll.equals(this.llSpec0)) {
                specJson = (SpecJsonVo) this.goodDetail.getSpecJson().get(0);
                this.tvSpecName0.setText(specJson.getSpecName() + "：");
                this.selectedSpec0 = Integer.valueOf(this.selectedGoods.getSpecValueIds().split(Separators.COMMA)[0]).intValue();
                updateSelectedGoodsId();
                for (final SpecValueListVo specValueListVo : specJson.getSpecValueList()) {
                    addViewHolder = new AddViewHolder(this.context, R.layout.recyclerview_item_one_button);
                    btnSpec = (TextView) addViewHolder.getView(R.id.btnSpec);
                    btnSpec.setText(specValueListVo.getSpecValueName());
                    if (this.selectedSpec0 == specValueListVo.getSpecValueId()) {
                        this.selectedSpecTv0 = btnSpec;
                        btnSpec.setActivated(true);
                    } else {
                        btnSpec.setActivated(false);
                    }
                    final TextView finalBtnSpec = btnSpec;
                    btnSpec.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!finalBtnSpec.isActivated()) {
                                GoodDetailsSpecDialogs.this.selectedSpecTv0.setActivated(false);
                                GoodDetailsSpecDialogs.this.selectedSpecTv0 = finalBtnSpec;
                                GoodDetailsSpecDialogs.this.selectedSpecTv0.setActivated(true);
                                GoodDetailsSpecDialogs.this.selectedSpec0 = specValueListVo.getSpecValueId();
                                GoodDetailsSpecDialogs.this.updateSelectedGoodsId();
                            }
                        }
                    });
                    this.rvSpecList0.addView(addViewHolder.getCustomView());
                }
            } else if (ll.equals(this.llSpec1)) {
                specJson = (SpecJsonVo) this.goodDetail.getSpecJson().get(1);
                this.tvSpecName1.setText(specJson.getSpecName() + "：");
                this.selectedSpec1 = Integer.valueOf(this.selectedGoods.getSpecValueIds().split(Separators.COMMA)[1]).intValue();
                updateSelectedGoodsId();
                for (final SpecValueListVo specValueListVo2 : specJson.getSpecValueList()) {
                    addViewHolder = new AddViewHolder(this.context, R.layout.recyclerview_item_one_button);
                    btnSpec = (TextView) addViewHolder.getView(R.id.btnSpec);
                    btnSpec.setText(specValueListVo2.getSpecValueName());
                    if (this.selectedSpec1 == specValueListVo2.getSpecValueId()) {
                        this.selectedSpecTv1 = btnSpec;
                        btnSpec.setActivated(true);
                    } else {
                        btnSpec.setActivated(false);
                    }
                    final TextView finalBtnSpec2 = btnSpec;
                    btnSpec.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!finalBtnSpec2.isActivated()) {
                                GoodDetailsSpecDialogs.this.selectedSpecTv1.setActivated(false);
                                GoodDetailsSpecDialogs.this.selectedSpecTv1 = finalBtnSpec2;
                                GoodDetailsSpecDialogs.this.selectedSpecTv1.setActivated(true);
                                GoodDetailsSpecDialogs.this.selectedSpec1 = specValueListVo2.getSpecValueId();
                                GoodDetailsSpecDialogs.this.updateSelectedGoodsId();
                            }
                        }
                    });
                    this.rvSpecList1.addView(addViewHolder.getCustomView());
                }
            } else if (ll.equals(this.llSpec2)) {
                specJson = (SpecJsonVo) this.goodDetail.getSpecJson().get(2);
                this.tvSpecName2.setText(specJson.getSpecName() + "：");
                this.selectedSpec2 = Integer.valueOf(this.selectedGoods.getSpecValueIds().split(Separators.COMMA)[2]).intValue();
                updateSelectedGoodsId();
                for (final SpecValueListVo specValueListVo22 : specJson.getSpecValueList()) {
                    addViewHolder = new AddViewHolder(this.context, R.layout.recyclerview_item_one_button);
                    btnSpec = (TextView) addViewHolder.getView(R.id.btnSpec);
                    btnSpec.setText(specValueListVo22.getSpecValueName());
                    if (this.selectedSpec2 == specValueListVo22.getSpecValueId()) {
                        this.selectedSpecTv2 = btnSpec;
                        btnSpec.setActivated(true);
                    } else {
                        btnSpec.setActivated(false);
                    }
                    final TextView finalBtnSpec1 = btnSpec;
                    btnSpec.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!finalBtnSpec1.isActivated()) {
                                GoodDetailsSpecDialogs.this.selectedSpecTv2.setActivated(false);
                                GoodDetailsSpecDialogs.this.selectedSpecTv2 = finalBtnSpec1;
                                GoodDetailsSpecDialogs.this.selectedSpecTv2.setActivated(true);
                                GoodDetailsSpecDialogs.this.selectedSpec2 = specValueListVo22.getSpecValueId();
                                GoodDetailsSpecDialogs.this.updateSelectedGoodsId();
                            }
                        }
                    });
                    this.rvSpecList2.addView(addViewHolder.getCustomView());
                }
            }
        }
    }

    @BindView(R.id.tvSpecName1)
    TextView tvSpecName1;
    @BindView(R.id.tvSpecName2)
    TextView tvSpecName2;
    @BindView(R.id.rvSpecList1)
    FlexboxLayout rvSpecList1;
    @BindView(R.id.rvSpecList2)
    FlexboxLayout rvSpecList2;

    private void updateSelectedGoodsId() {
        String specValue = this.selectedSpec0 + "";
        if (this.selectedSpec1 != Integer.MAX_VALUE) {
            specValue = specValue + Separators.COMMA + this.selectedSpec1;
            if (this.selectedSpec2 != Integer.MAX_VALUE) {
                specValue = specValue + Separators.COMMA + this.selectedSpec2;
            }
        }
        for (Goods goods : this.goodDetail.getGoodsList()) {
            if (goods.getSpecValueIds().equals(specValue)) {
                this.selectedGoods = goods;
                if (this.goodDetail.getAppUsable() == 1 && this.goodDetail.getPromotionType() == 3) {
                    for (BookBean bookBean : this.goodDetail.getBookList()) {
                        if (bookBean.getGoodsId() == this.selectedGoods.getGoodsId()) {
                            this.selectedBook = bookBean;
                            this.tvPrePrice.setText("定金：" + this.moneyRmb + ShopHelper.getPriceString(this.selectedBook.getDownPayment()) + " + 尾款：" + this.moneyRmb + ShopHelper.getPriceString(this.selectedBook.getFinalPayment()));
                        }
                    }
                }
                setArrivalNoticeShow(this.goodDetail.getGoodsModal());
                GlideImageLoader.setImage(context, selectedGoods.getImageSrc(), ivSelectedGoodsImg);
                this.tvSkuStorage.setText("库存:" + this.selectedGoods.getGoodsStorage() + this.goodDetail.getUnitName());
                EventBus.getDefault().post(new GoodBusBean(GoodBusBean.SelectedGoods, this.selectedGoods));
                switchPriceChangeByGoodsModal(this.goodDetail.getGoodsModal());
                switchSpecChangeByGoodsModal(this.goodDetail.getGoodsModal());
                return;
            }
        }
    }

    @BindView(R.id.tvPrePrice)
    TextView tvPrePrice;
    @BindView(R.id.tvAppCommonCount)
    TextView tvAppCommonCount;
    private int addAndMinusCount = 1;

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

    private void setArrivalNoticeShow(int goodsModal) {
        switch (goodsModal) {
            case 1:
                if (this.selectedGoods.getGoodsStorage() == 0) {
                    this.btnBuy.setVisibility(View.GONE);
                    this.btnAddCart.setVisibility(View.GONE);
                    this.btnArrivalNotice.setVisibility(View.VISIBLE);
                    return;
                }
                this.btnBuy.setVisibility(View.VISIBLE);
                this.btnAddCart.setVisibility(View.VISIBLE);
                this.btnArrivalNotice.setVisibility(View.GONE);
                return;
            case 2:
                int num = 0;
                for (Goods goods : this.goodDetail.getGoodsList()) {
                    num += goods.getGoodsStorage();
                }
                if (num == 0) {
                    this.btnBuy.setVisibility(View.GONE);
                    this.btnAddCart.setVisibility(View.GONE);
                    this.btnArrivalNotice.setVisibility(View.VISIBLE);
                    return;
                }
                this.btnBuy.setVisibility(View.VISIBLE);
                this.btnAddCart.setVisibility(View.VISIBLE);
                this.btnArrivalNotice.setVisibility(View.GONE);
                return;
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
            this.tvAllNumPrice.setText("");
            return;
        }
        String singlePrice = GoodHelper.getSinglePrice(this.goodDetail, this.allGoodsNum);
        this.tvSelectedPrice.setText(this.moneyRmb + singlePrice);
        double total = Double.valueOf(singlePrice).doubleValue() * ((double) this.allGoodsNum);
        String[] allNumPriceStrings = String.format(this.allNumPrice, new Object[]{Integer.valueOf(this.allGoodsNum), this.goodDetail.getUnitName(), Double.valueOf(total)}).split(Separators.COMMA);
        this.tvAllNumPrice.setText(Html.fromHtml("<font color=\"#555555\">" + allNumPriceStrings[0] + "</font><font color=\"#DE5765\">" + allNumPriceStrings[1] + "</font>"));
    }

    @OnClick({R.id.btnAppCommonMinus})
    public void btnAppCommonMinusClick() {
        this.addAndMinusCount = Integer.valueOf(CommUtil.getText(this.tvAppCommonCount)).intValue();
        this.addAndMinusCount--;
        switchNumMinByGoodsModal(this.goodDetail.getGoodsModal());
        this.tvAppCommonCount.setText(this.addAndMinusCount + "");
    }

    @OnClick({R.id.tvAppCommonCount})
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
