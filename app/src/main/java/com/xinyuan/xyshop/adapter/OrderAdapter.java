package com.xinyuan.xyshop.adapter;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayout;
import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.OrderBean;
import com.xinyuan.xyshop.bean.OrderGoodBean;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.common.ShopHelper;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.even.OrderBusEven;
import com.xinyuan.xyshop.even.OrderPageRefresh;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.ui.goods.store.StoreActivity;
import com.xinyuan.xyshop.ui.mine.msg.ChattingDetailActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.widget.dialog.color.ColorDialog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fx on 2017/6/5.
 * 订单列表Adapter
 */

public class OrderAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {
	private int type;

	public OrderAdapter(@LayoutRes int layoutResId, @Nullable List<OrderBean> data, int orderType) {
		super(layoutResId, data);
		this.type = orderType;

	}

	@Override
	protected void convert(final BaseViewHolder helper, final OrderBean item) {


		LinearLayout ll_order_bt_dfk = helper.getView(R.id.ll_order_bt_dfk);    //待付款功能布局
		LinearLayout ll_order_bt_dsh = helper.getView(R.id.ll_order_bt_dsh);    //待收货功能布局
		LinearLayout ll_order_bt_dpj = helper.getView(R.id.ll_order_bt_dpj);    //待评价功能布局
		LinearLayout ll_order_bt_finish = helper.getView(R.id.ll_order_bt_finish); //订单完成布局
		LinearLayout ll_order_bt_close = helper.getView(R.id.ll_order_bt_close);    //关闭订单功能布局
		View view_bottom = helper.getView(R.id.view_bottom);  //底部横线
		view_bottom.setVisibility(View.GONE);
		ll_order_bt_dfk.setVisibility(View.GONE);
		ll_order_bt_dsh.setVisibility(View.GONE);
		ll_order_bt_dpj.setVisibility(View.GONE);
		ll_order_bt_finish.setVisibility(View.GONE);
		ll_order_bt_close.setVisibility(View.GONE);

		Button bt_store = helper.getView(R.id.bt_order_item_store);
		TextView tv_status = helper.getView(R.id.tv_order_item_status);
		TextView tv_price = helper.getView(R.id.tv_order_good_price);
		bt_store.setText(item.getStoreName());
		bt_store.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(mContext, StoreActivity.class);
				intent.putExtra(Constants.STOREID, String.valueOf(item.getStoreId()));
				mContext.startActivity(intent);

			}
		});
		tv_price.setText("共" + item.getGoodsList().size() + "件商品 合计：" + mContext.getString(R.string.money_rmb) + ShopHelper.getPriceString(item.getOrderPrice()) + "(含运费" + mContext.getResources().getString(R.string.money_rmb) + ShopHelper.getPriceString(item.getShipPrice()) + ")");

		switch (item.getOrderStatus()) {
			case "1":                                                                   //待付款状态
				ll_order_bt_dfk.setVisibility(View.VISIBLE);
				view_bottom.setVisibility(View.VISIBLE);
				tv_status.setText(R.string.order_dfk);
				Button bt_order_cancel = helper.getView(R.id.bt_order_cancel);          //取消订单按钮
				Button bt_order_contatct = helper.getView(R.id.bt_order_contatct);      //联系卖家按钮
				Button bt_order_pay = helper.getView(R.id.bt_order_pay);                //付款按钮
				bt_order_cancel.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						ToOrderCancel(item.getOrderType(), item.getOrderFormId(), helper.getLayoutPosition());
					}
				});
				bt_order_contatct.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						ToOrderContact(item.getStoreUserName(), String.valueOf(item.getStoreUserId()), item.getStoreUserImg());
					}
				});
				bt_order_pay.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						EventBus.getDefault().post(new OrderBusEven(OrderBusEven.ToOrderPay, item));
					}
				});

				break;
			case "2":  //待发货状态 已付款
				if (type == 1) {
					tv_status.setText(R.string.order_dfh);
				} else {
					tv_status.setText(R.string.order_dsy);
				}

				view_bottom.setVisibility(View.GONE);
				break;
			case "3":    //待收货状态  已使用
				if (type == 1) {
					Button bt_order_logistical = helper.getView(R.id.bt_order_logistical);                   //查看物流按钮
					Button bt_order_suertake = helper.getView(R.id.bt_order_suertake);                       //确认收货按钮
					view_bottom.setVisibility(View.VISIBLE);
					bt_order_logistical.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							ToLogistic(item);
						}
					});

					bt_order_suertake.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							ToOrderFinish(item.getOrderFormId(), helper.getLayoutPosition());
						}


					});
					ll_order_bt_dsh.setVisibility(View.VISIBLE);
					tv_status.setText(R.string.order_yfh);
				} else {
					ll_order_bt_dpj.setVisibility(View.VISIBLE);
					view_bottom.setVisibility(View.VISIBLE);
					Button bt_order_dpj = helper.getView(R.id.bt_order_dpj_logistical);                      //查看物流按钮
					bt_order_dpj.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							ToLogistic(item);
						}
					});
					tv_status.setText(R.string.order_dpj);
				}

				break;
			case "4":                                                                                   //待评价 已过期
				if (type == 1) {
					ll_order_bt_dpj.setVisibility(View.VISIBLE);
					view_bottom.setVisibility(View.VISIBLE);
					Button bt_order_dpj = helper.getView(R.id.bt_order_dpj_logistical);                      //查看物流按钮
					bt_order_dpj.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							ToLogistic(item);
						}
					});
					tv_status.setText(R.string.order_dpj);
				} else {
					tv_status.setText(R.string.order_ygq);

				}

				break;
			case "5":                                                                                    //售后中状态
				if (type == 1) {
					tv_status.setText("售后处理中");

				} else {
					tv_status.setText("交易成功");

				}
				break;
			case "9":                                                                                   //交易成功状态
				ll_order_bt_finish.setVisibility(View.VISIBLE);
				Button bt_order_sucess_logistical = helper.getView(R.id.bt_order_finish_logistical);   //查看物流
				bt_order_sucess_logistical.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						ToLogistic(item);
					}
				});
				Button bt_order_sucess_detele = helper.getView(R.id.bt_order_detele);
				bt_order_sucess_detele.setVisibility(View.GONE);
				tv_status.setText(R.string.order_success);
				break;
			case "10":                                                                                  //订单完成状态

				if (type == 1) {
					ll_order_bt_finish.setVisibility(View.VISIBLE);
					view_bottom.setVisibility(View.VISIBLE);
					Button bt_order_finish_logistical = helper.getView(R.id.bt_order_finish_logistical);   //查看物流
					bt_order_finish_logistical.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							ToLogistic(item);
						}
					});
					Button bt_order_finish_detele = helper.getView(R.id.bt_order_detele);
					bt_order_finish_detele.setVisibility(View.VISIBLE);
					bt_order_finish_detele.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							ToDetele(item.getOrderFormId(), helper.getLayoutPosition());
						}
					});
					tv_status.setText(R.string.order_finish);
				} else {
					ll_order_bt_finish.setVisibility(View.VISIBLE);
					view_bottom.setVisibility(View.VISIBLE);
					Button bt_order_finish_logistical = helper.getView(R.id.bt_order_finish_logistical);   //查看物流
					bt_order_finish_logistical.setVisibility(View.GONE);
					Button bt_order_finish_detele = helper.getView(R.id.bt_order_detele);
					bt_order_finish_detele.setVisibility(View.VISIBLE);
					bt_order_finish_detele.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							ToDetele(item.getOrderFormId(), helper.getLayoutPosition());
						}
					});
					tv_status.setText(R.string.order_finish);
				}

				break;
			case "0":                                                                                   //交易关闭状态
				ll_order_bt_close.setVisibility(View.VISIBLE);
				tv_status.setText(R.string.order_close);
				Button bt_order_close_detele = helper.getView(R.id.bt_order_close_detele);
				bt_order_close_detele.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						ToDetele(item.getOrderFormId(), helper.getLayoutPosition());
					}
				});
				break;

		}
		FlexboxLayout fl_order_goods = helper.getView(R.id.fl_order_goods);
		fl_order_goods.removeAllViews();
		for (OrderGoodBean good : item.getGoodsList()) {

			AddViewHolder addViewHolder = new AddViewHolder(mContext, R.layout.fragment_order_item_good);
			ImageView iv_good_img = addViewHolder.getView(R.id.iv_good_img);
			GlideImageLoader.setUrlImg(mContext, good.getGoodsImg(), iv_good_img);
			TextView tv_good_name = addViewHolder.getView(R.id.tv_good_name);
			tv_good_name.setText(good.getGoods_name());
			TextView tv_good_spec = addViewHolder.getView(R.id.tv_good_spec);
			tv_good_spec.setText(mContext.getString(R.string.text_chosed) + good.getSpec_info());
			TextView tv_good_price = addViewHolder.getView(R.id.tv_good_price);
			tv_good_price.setText(mContext.getString(R.string.money_rmb) + ShopHelper.getPriceString(good.getStore_price()));
			TextView tv_good_old_price = addViewHolder.getView(R.id.tv_good_old_price);
			tv_good_old_price.setText(mContext.getString(R.string.money_rmb) + ShopHelper.getPriceString(good.getGoods_price()));
			tv_good_price.setText(mContext.getString(R.string.money_rmb) + ShopHelper.getPriceString(good.getStore_price()));
			tv_good_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
			TextView tv_good_num = addViewHolder.getView(R.id.tv_good_num);
			tv_good_num.setText("X" + good.getCount());
			fl_order_goods.addView(addViewHolder.getCustomView());
		}


		fl_order_goods.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ToOrderDetail(item);
			}
		});

	}

	/**
	 * 联系卖家
	 *
	 * @param userName
	 * @param userId
	 * @param userHeadImg
	 */
	private void ToOrderContact(String userName, String userId, String userHeadImg) {
		Map<String, String> params = new HashMap();
		params.put(Constants.USERNAME, userName);
		params.put(Constants.USERHEAD, userHeadImg);
		params.put(Constants.USERID, userId);
		CommUtil.gotoActivity(mContext, ChattingDetailActivity.class, false, params);

	}

	/**
	 * 付款
	 */
	private void ToOrderPay() {

	}

	/**
	 * 确认收货
	 *
	 * @param orderId
	 */
	private void ToOrderFinish(final Long orderId, final int position) {
		final ColorDialog colorDialog = new ColorDialog(mContext);
		colorDialog.setContentText(mContext.getString(R.string.order_notice_recvie));
		colorDialog.setPositiveListener(mContext.getString(R.string.bt_ok), new ColorDialog.OnPositiveListener() {
			@Override
			public void onClick(ColorDialog dialog) {
				ReceivingOrder(orderId, position);
				colorDialog.dismiss();


			}
		})
				.setNegativeListener(mContext.getString(R.string.bt_cancel), new ColorDialog.OnNegativeListener() {
					@Override
					public void onClick(ColorDialog dialog) {
						colorDialog.dismiss();
					}
				}).show();
	}


	/**
	 * 退款操作
	 *
	 * @param bean
	 */
	private void ToServiceMoney(OrderBean bean) {
		EventBus.getDefault().post(new OrderBusEven(OrderBusEven.ToServiceMoneyDetail, bean));
	}


	/**
	 * 查看订单详情操作
	 *
	 * @param bean
	 */
	private void ToOrderDetail(OrderBean bean) {
		EventBus.getDefault().post(new OrderBusEven(OrderBusEven.ToOrderDetail, bean));
	}

	/**
	 * 查看物流操作
	 *
	 * @param bean
	 */
	private void ToLogistic(OrderBean bean) {
		EventBus.getDefault().post(new OrderBusEven(OrderBusEven.ToOrderLogistic, bean));

	}

	/**
	 * 删除订单
	 *
	 * @param
	 */
	private void ToDetele(final Long orderId, final int position) {
		final ColorDialog colorDialog = new ColorDialog(mContext);
		//colorDialog.setTitle("确认收货？");
		colorDialog.setContentText(mContext.getString(R.string.order_notice_detele));
		colorDialog.setPositiveListener(mContext.getString(R.string.bt_ok), new ColorDialog.OnPositiveListener() {
			@Override
			public void onClick(ColorDialog dialog) {
				OrderDetele(orderId, position);
				colorDialog.dismiss();


			}
		})
				.setNegativeListener(mContext.getString(R.string.bt_cancel), new ColorDialog.OnNegativeListener() {
					@Override
					public void onClick(ColorDialog dialog) {
						colorDialog.dismiss();
					}
				}).show();
	}

	/**
	 * 取消订单
	 *
	 * @param orderType
	 * @param orderId
	 */
	private void ToOrderCancel(int orderType, final Long orderId, final int position) {
		final ColorDialog colorDialog = new ColorDialog(mContext);
		//colorDialog.setTitle("确认收货？");
		colorDialog.setContentText(mContext.getString(R.string.order_notice_close));
		colorDialog.setPositiveListener(mContext.getString(R.string.bt_ok), new ColorDialog.OnPositiveListener() {
			@Override
			public void onClick(ColorDialog dialog) {
				if (orderType == 1) {
					OrderCancel(orderId, position);
				} else {
					OnOrderCancel(orderId, position);

				}
				colorDialog.dismiss();


			}
		})
				.setNegativeListener(mContext.getString(R.string.bt_cancel), new ColorDialog.OnNegativeListener() {
					@Override
					public void onClick(ColorDialog dialog) {
						colorDialog.dismiss();
					}
				}).show();
	}

	/**
	 * 确认收货
	 *
	 * @param orderId
	 */
	private void ReceivingOrder(Long orderId, final int position) {
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_ORDER_RECEIVE)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("orderFormId", orderId)
				.execute(new JsonCallback<LzyResponse<TokenBean>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							XToast.info("已确认收货");
							EventBus.getDefault().post(new OrderBusEven(OrderBusEven.ToOrderRefresh));
							EventBus.getDefault().postSticky(new OrderPageRefresh(true));
						} else {
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						HttpUtil.handleError(mContext, response);
						XToast.error("确认收货出错，请稍后再试");

					}
				});

	}


	/**
	 * 取消订单
	 *
	 * @param orderId
	 */
	private void OrderCancel(Long orderId, final int position) {
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_ORDER_CANCEL)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("orderFormId", orderId)
				.execute(new JsonCallback<LzyResponse<TokenBean>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							XToast.info("已取消订单");
							EventBus.getDefault().postSticky(new OrderPageRefresh(true));
						} else {

						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						HttpUtil.handleError(mContext, response);
						XToast.error("取消订单出错，请稍后再试");

					}
				});

	}

	/**
	 * 取消虚拟订单
	 *
	 * @param orderId
	 */
	private void OnOrderCancel(Long orderId, final int position) {
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_ONORDER_CANCEL)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("virOrderId", orderId)
				.execute(new JsonCallback<LzyResponse<TokenBean>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							XToast.info("已取消订单");
							EventBus.getDefault().postSticky(new OrderPageRefresh(true));
						} else {

						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						HttpUtil.handleError(mContext, response);
						XToast.error("取消订单出错，请稍后再试");

					}
				});

	}

	/**
	 * 删除订单
	 *
	 * @param orderId
	 * @param position
	 */
	private void OrderDetele(Long orderId, final int position) {
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_USER_ORDER_DETELE)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("orderId", orderId)
				.execute(new JsonCallback<LzyResponse<TokenBean>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							XToast.info("已删除订单");
							EventBus.getDefault().postSticky(new OrderPageRefresh(true));
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						HttpUtil.handleError(mContext, response);
						XToast.error("删除出错，请稍后再试");

					}
				});

	}

}
