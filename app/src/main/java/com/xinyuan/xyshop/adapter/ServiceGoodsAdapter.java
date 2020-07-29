package com.xinyuan.xyshop.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.ServiceGoodBean;
import com.xinyuan.xyshop.bean.ServiceGoodDetailBean;
import com.xinyuan.xyshop.entity.HomeMultipleItem;
import com.xinyuan.xyshop.entity.ServiceMultipleItem;
import com.xinyuan.xyshop.model.HomeModel;
import com.xinyuan.xyshop.model.ServiceGoodDetailModel;
import com.youth.xframe.utils.log.XLog;

import java.util.List;

/**
 * Created by fx on 2017/7/1.
 * 退货详情列表Adapter
 */

public class ServiceGoodsAdapter extends BaseMultiItemQuickAdapter<ServiceMultipleItem, BaseViewHolder> {


	/**
	 * Same as QuickAdapter#QuickAdapter(Context,int) but with
	 * some initialization data.
	 *
	 * @param data A new list is created out of this one to avoid mutable list
	 */
	public ServiceGoodsAdapter(List<ServiceMultipleItem> data) {
		super(data);
		addItemType(ServiceMultipleItem.User, R.layout.fragment_service_goods_item_user);
		addItemType(ServiceMultipleItem.Store, R.layout.fragment_service_goods_item_store);
	}

	@Override
	protected void convert(BaseViewHolder helper, ServiceMultipleItem item) {
		ServiceGoodDetailBean goodBean = (ServiceGoodDetailBean) item.getObj();

		switch (item.getItemType()) {
			case ServiceMultipleItem.User:
				RelativeLayout rl_goods_info = helper.getView(R.id.rl_goods_info);
				View user_line = helper.getView(R.id.service_line);
				switch (goodBean.getStep()) {
					case 1: //发起退货

						helper.setText(R.id.tv_info_time, goodBean.getHandlingTime());
						helper.setText(R.id.tv_status_title, goodBean.getLog());
						helper.setText(R.id.tv_status_hint, goodBean.getCountdown());

						rl_goods_info.setVisibility(View.VISIBLE);
						user_line.setVisibility(View.VISIBLE);

						String info = goodBean.getInfo();


						String goodName = info.substring(info.indexOf("商品名称:") + 5, info.indexOf("|订单编号"));
						String orderNum = info.substring(info.indexOf("订单编号:") + 5, info.indexOf("|订单状态"));
						String orderStatus = info.substring(info.indexOf("订单状态:") + 5, info.indexOf("|退货原因"));
						String reason = info.substring(info.indexOf("退货原因:") + 5, info.indexOf("|金额"));
						String money = info.substring(info.indexOf("金额") + 2, info.length());
						helper.setText(R.id.tv_goods_name, goodName);
						helper.setText(R.id.tv_good_orderId, orderNum);
						helper.setText(R.id.tv_order_status, orderStatus);
						helper.setText(R.id.tv_order_reason, reason);
						helper.setText(R.id.tv_service_price, mContext.getString(R.string.money_rmb) + money);

						break;
					case 0: //买家已撤销
						rl_goods_info.setVisibility(View.GONE);
						user_line.setVisibility(View.GONE);
						helper.setText(R.id.tv_status_title, goodBean.getLog());
						break;

					case 5: //买家已发货
						rl_goods_info.setVisibility(View.GONE);
						user_line.setVisibility(View.GONE);
						helper.setText(R.id.tv_info_time, goodBean.getHandlingTime());
						helper.setText(R.id.tv_status_title, goodBean.getLog());

						break;


				}
				break;
			case ServiceMultipleItem.Store:
				View store_line = helper.getView(R.id.service_line);
				switch (goodBean.getStep()) {
					case 2: //卖家同意退货
						store_line.setVisibility(View.VISIBLE);
						helper.setVisible(R.id.tv_store_content, true);
						helper.setText(R.id.tv_status_title, goodBean.getLog());
						helper.setText(R.id.tv_status_hint, goodBean.getCountdown());
						helper.setText(R.id.tv_service_time, goodBean.getHandlingTime());
						helper.setText(R.id.tv_store_content, goodBean.getInfo());
						break;
					case 3: //卖家拒绝退货
						helper.setText(R.id.tv_status_title, goodBean.getLog());
						helper.setText(R.id.tv_service_time, goodBean.getHandlingTime());
						break;
					case 6: //卖家已收到退货
						helper.setText(R.id.tv_status_title, goodBean.getLog());
						helper.setText(R.id.tv_service_time, goodBean.getHandlingTime());
						break;
					case 7: //卖家同意退款
						helper.setText(R.id.tv_status_title, goodBean.getLog());
						helper.setText(R.id.tv_service_time, goodBean.getHandlingTime());
						break;
					case 8: //卖家拒绝退款
						helper.setText(R.id.tv_status_title, goodBean.getLog());
						helper.setText(R.id.tv_service_time, goodBean.getHandlingTime());
						break;
				}

				break;
		}
	}
}
