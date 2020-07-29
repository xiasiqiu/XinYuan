package com.xinyuan.xyshop.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.GoodsAttrsAdapter;
import com.xinyuan.xyshop.bean.AttributesBean;
import com.xinyuan.xyshop.callback.SKUInterface;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.even.GoodBusEven;
import com.xinyuan.xyshop.model.GoodsAttrsBean;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;
import com.xinyuan.xyshop.ui.goods.detail.fragment.GoodHomeFragment;
import com.xinyuan.xyshop.widget.AddSubView;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static String defaultImg;
    @BindView(R.id.addSubView)
    AddSubView addSubView;

    public GoodDetailsSpecDialog(Context context, GoodsAttrsBean goodsAttrsBean, String img) {
        super(context, R.style.CommonDialog);
        this.context = context;
        this.goodsAttrsBean = goodsAttrsBean;
        this.defaultImg = img;
    }

    String attrs;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_dialog_gooddetail_spec);
        ButterKnife.bind((Dialog) this);
        if (!XEmptyUtils.isEmpty(goodsAttrsBean.getDefaultGood().getGoodsPrice())) {
            this.selecGood = goodsAttrsBean.getDefaultGood();
            XLog.v("不为空");
        } else {
            XLog.v("空");
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_sku.setLayoutManager(layoutManager);
        GlideImageLoader.setUrlImg(context, defaultImg, ivSelectedGoodsImg);
        tvGoodsName.setText(GoodDetailActivity.goodName);
        if (!XEmptyUtils.isEmpty(goodsAttrsBean.getStockGoods())) {
            sortAttrbutes(goodsAttrsBean.getAttributes(), goodsAttrsBean.getStockGoods());
        }
        tvSkuStorage.setText("库存:" + GoodHomeFragment.goodsInventory);
        tvSelectedPrice.setText(context.getString(R.string.money_rmb) + GoodDetailActivity.goodPrice);
        if (!XEmptyUtils.isEmpty(goodsAttrsBean.getDefaultGood().getStock())) {
            addSubView.setInventory(GoodHomeFragment.goodsInventory)
                    .setBuyMax(GoodHomeFragment.goodsInventory);
        } else {
            addSubView.setBuyMax((int) goodsAttrsBean.getDefaultGood().getStock())
                    .setInventory((int) goodsAttrsBean.getDefaultGood().getStock());
        }

        addSubView.setCurrentNumber(1)
                .setOnWarnListener(new AddSubView.OnWarnListener() {
                    @Override
                    public void onWarningForInventory(int inventory) {
                        XToast.info("当前库存" + inventory);

                    }

                    @Override
                    public void onWarningForBuyMax(int max) {
                        XToast.info("超过最大购买数" + max);
                    }

                    @Override
                    public void onWarningForBuyMin(int min) {

                        XToast.info("低于最低购买数" + min);
                    }

                    @Override
                    public void AddListenter(View num) {
                        addAndMinusCount = addSubView.getNumber();
                        addAndMinusCount++;
                        addSubView.setCurrentNumber(addAndMinusCount);
                    }

                    @Override
                    public void ReduceListenter(View num) {
                        addAndMinusCount = addSubView.getNumber();
                        addAndMinusCount--;
                        addSubView.setCurrentNumber(addAndMinusCount);
                    }
                });
    }


    List<AttributesBean> attributesBeans;
    List<GoodsAttrsBean.StockGoodsBean> goodsInfo;

    private void sortAttrbutes(List<AttributesBean> list, List<GoodsAttrsBean.StockGoodsBean> stockGoodsList) {
        attributesBeans = new ArrayList<>();
        goodsInfo = new ArrayList<>();
        if (!XEmptyUtils.isEmpty(stockGoodsList)) {
            GoodsAttrsBean.StockGoodsBean bean = stockGoodsList.get(0);
            List<GoodsAttrsBean.GoodsInfoBean> attr = bean.getGoodsInfo();
            for (int i = 0; i < attr.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    if (attr.get(i).getSpecName().equals(list.get(j).getTabName())) {
                        AttributesBean attributesBean = new AttributesBean();
                        attributesBean.setTabName(list.get(j).getTabName());
                        attributesBean.setAttributesItem(list.get(j).getAttributesItem());
                        attributesBeans.add(attributesBean);
                    }
                }
            }
            goodsAttrsBean.setAttributes(attributesBeans);
            mAdapter = new GoodsAttrsAdapter(context, goodsAttrsBean.getAttributes(), goodsAttrsBean.getStockGoods(), this.selecGood);
            rv_sku.setFocusable(false);
            mAdapter.setSKUInterface(this);
            rv_sku.setAdapter(mAdapter);
            GlideImageLoader.setUrlImg(context, defaultImg, ivSelectedGoodsImg);
            init();
        }
    }

    private void init() {
        tvGoodsName.setText(GoodDetailActivity.goodName);
        tvSkuStorage.setText("库存:" + goodsAttrsBean.getDefaultGood().getStock());
        tvSelectedPrice.setText("￥" + goodsAttrsBean.getDefaultGood().getPrice());
        addSubView.setBuyMax((int) goodsAttrsBean.getDefaultGood().getStock())
                .setInventory((int) goodsAttrsBean.getDefaultGood().getStock())
                .setCurrentNumber(1)
                .setOnWarnListener(new AddSubView.OnWarnListener() {
                    @Override
                    public void onWarningForInventory(int inventory) {
                        XToast.info("当前库存" + inventory);

                    }

                    @Override
                    public void onWarningForBuyMax(int max) {
                        XToast.info("超过最大购买数" + max);
                    }

                    @Override
                    public void onWarningForBuyMin(int min) {

                        XToast.info("低于最低购买数" + min);
                    }

                    @Override
                    public void AddListenter(View num) {
                        addAndMinusCount = addSubView.getNumber();
                        addAndMinusCount++;
                        addSubView.setCurrentNumber(addAndMinusCount);
                    }

                    @Override
                    public void ReduceListenter(View num) {
                        addAndMinusCount = addSubView.getNumber();
                        addAndMinusCount--;
                        addSubView.setCurrentNumber(addAndMinusCount);
                    }
                });

        for (
                int i = 0; i < selecGood.getGoodsInfo().
                size();
                i++) {
            goodsAttrsBean.getAttributes().get(i);

            for (int j = 0; j < goodsAttrsBean.getAttributes().get(i).getAttributesItem().size(); j++) {
                if (goodsAttrsBean.getAttributes().get(i).getAttributesItem().get(j).getValueName().equals(selecGood.getGoodsInfo().get(i).getGspValue())) {
                    if (!XEmptyUtils.isEmpty(goodsAttrsBean.getAttributes().get(i).getAttributesItem().get(j).getValueImage()) && !XEmptyUtils.isSpace(goodsAttrsBean.getAttributes().get(i).getAttributesItem().get(j).getValueImage())) {
                        GlideImageLoader.setUrlImg(context, goodsAttrsBean.getAttributes().get(i).getAttributesItem().get(j).getValueImage(), ivSelectedGoodsImg);
                    }
                }

            }
        }

        attrs = "";
        for (
                int i = 0; i < goodsAttrsBean.getAttributes().

                size();

                i++)

        {
            attrs += goodsAttrsBean.getAttributes().get(i).getTabName() + ";";

        }

        tvSkuName.setText("请选择:" + attrs);


    }

    public static GoodsAttrsBean.StockGoodsBean selecGood;


    @Override
    public void selectedAttribute(String[] attr) {
        String str = "";
        String ss = "";

        String Bs = "";
        boolean ok = false;
        for (int i = 0; i < attr.length; i++) {
            ss = TextUtils.isEmpty(attr[i]) ? "无" : attr[i];
            str += ss + "|";

            if (TextUtils.isEmpty(attr[i])) {
                Bs += goodsAttrsBean.getAttributes().get(i).getTabName() + ";";
            }
        }
        str = str.substring(0, str.length() - 1);

        for (AttributesBean attributesBean : goodsAttrsBean.getAttributes()) {

            List<AttributesBean.AttributeBean> list = attributesBean.getAttributesItem();
            for (AttributesBean.AttributeBean attributeBean : list) {

                if (str.contains(attributeBean.getValueName())) {
                    if (!XEmptyUtils.isEmpty(attributeBean.getValueImage()) && !XEmptyUtils.isSpace(attributeBean.getValueImage())) {
                        GlideImageLoader.setUrlImg(context, attributeBean.getValueImage(), ivSelectedGoodsImg);
                    }
                }


            }


        }

        if (Bs.equals("")) {
            ok = true;
        }

        for (GoodsAttrsBean.StockGoodsBean goodsBean : goodsAttrsBean.getStockGoods()) {
            String goods = goodsBean.toString();
            Log.v("--------------", goods + "===" + str);
            if (str.equals(goods)) {
                this.selecGood = goodsBean;
                tvSkuStorage.setText("库存:" + goodsBean.getStock());
                tvSelectedPrice.setText("￥" + goodsBean.getPrice());
                addSubView.setInventory((int) goodsBean.getStock());
                addSubView.setCurrentNumber(1);

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


    @Override
    public void dismiss() {
        if (isCheck) {
            isCheck = false;
        } else {
            XLog.v("是否空" + XEmptyUtils.isEmpty(selecGood));
            if (!XEmptyUtils.isEmpty(selecGood)) {
                XLog.v("关闭时发送-selecGood" + selecGood.toString());
                EventBus.getDefault().post(new GoodBusEven(GoodBusEven.SelectedGoods, this.selecGood));
            }
            XLog.v("关闭时不发送");
        }
        super.dismiss();
    }

    private boolean isCheck = false;

    @OnClick({R.id.btnBuy, R.id.btnAddCart, R.id.tvOut})
    public void onClick(View v) {
        Map<String, String> params = new HashMap();

        switch (v.getId()) {
            case R.id.btnBuy:
                isCheck = true;
                GoodDetailActivity.goodNum = addSubView.getNumber();
                EventBus.getDefault().post(new GoodBusEven(GoodBusEven.goToBuy, true));
                break;
            case R.id.btnAddCart:
                isCheck = true;
                GoodDetailActivity.goodNum = addSubView.getNumber();
                XLog.v("孤规格是否" + XEmptyUtils.isEmpty(selecGood));
                if (!XEmptyUtils.isEmpty(selecGood)) {
                    EventBus.getDefault().post(new GoodBusEven(GoodBusEven.SelectedGoods, this.selecGood));
                }
                EventBus.getDefault().post(new GoodBusEven(GoodBusEven.addShopCar, true));

                dismiss();
                break;
            case R.id.tvOut:
                dismiss();
                break;

        }
    }


}
