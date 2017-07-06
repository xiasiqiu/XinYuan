package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayout;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.ui.mine.order.ServiceMoneyDetailActivity;
import com.xinyuan.xyshop.ui.mine.order.fragment.ServiceGoodsActivity;
import com.xinyuan.xyshop.util.CommUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */

public class ServiceAdapter extends BaseQuickAdapter<GoodsVo, BaseViewHolder> {

	private static boolean flag=true;
	public ServiceAdapter(@LayoutRes int layoutResId, @Nullable List<GoodsVo> data) {
		super(layoutResId, data);

	}

	@Override
	protected void convert(BaseViewHolder helper, final GoodsVo item) {

		final FlexboxLayout fl_order_goods = helper.getView(R.id.fl_service_goods);
		for (int i = 0; i < 2; i++) {

			AddViewHolder addViewHolder = new AddViewHolder(mContext, R.layout.fragment_orderservice_item_good);

			fl_order_goods.addView(addViewHolder.getCustomView());
		}

		Button bt_order_eva_later=helper.getView(R.id.bt_order_eva_later);
		bt_order_eva_later.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(flag){
					CommUtil.gotoActivity(mContext, ServiceMoneyDetailActivity.class,false,null);
					flag=false;
				}else {
					CommUtil.gotoActivity(mContext, ServiceGoodsActivity.class,false,null);
				}
			}
		});

	}


}
