package com.xinyuan.xyshop.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.VoucherAdapter;
import com.xinyuan.xyshop.bean.CouponBean;
import com.xinyuan.xyshop.bean.StoreCouponBean;
import com.xinyuan.xyshop.widget.RecycleViewDivider;
import com.youth.xframe.utils.log.XLog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/6.
 */

public class StoreVoucherDialog extends Dialog {

    @BindView(R.id.rv_store_voucher)
    RecyclerView rv_store_voucher;
    @BindView(R.id.bt_close)
    Button bt_close;

    VoucherAdapter voucherAdapter;
    private List<StoreCouponBean> couponList;

    public StoreVoucherDialog(@NonNull Context context, List<StoreCouponBean> couponList) {
        super(context, R.style.CommonDialog);
        this.couponList = couponList;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_shopcar_dialog_store_voucher);
        ButterKnife.bind((Dialog) this);
        XLog.list(couponList);
        voucherAdapter = new VoucherAdapter(R.layout.item_store_voucher, couponList);
        rv_store_voucher.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        //设置布局管理器
        rv_store_voucher.setLayoutManager(linearLayoutManager);
        this.rv_store_voucher.setAdapter(this.voucherAdapter);
        this.voucherAdapter.notifyDataSetChanged();
        bt_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
