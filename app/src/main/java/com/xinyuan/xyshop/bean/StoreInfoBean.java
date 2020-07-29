package com.xinyuan.xyshop.bean;

import com.google.gson.JsonObject;
import com.xinyuan.xyshop.model.BuyExpressModel;
import com.youth.xframe.utils.XEmptyUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/5/31.
 * 购物车店铺操作
 */

public class StoreInfoBean implements Serializable {
	private static final long serialVersionUID = 529381259416091617L;
	private int Active;
	private long storeId;
	public String storeName;
	private List<CartGoodBean> goodsCartList;
	private BuyExpressModel express;
	private String addressId;
	public boolean isChoosed;
	public boolean isEdtor;
	private String invoiceInfoId;
	private RedPacketBean redPacketBean;
	private CouponOrderBean couponBean;
	private String msg = "";

	private String goodCartId;

	public String getGoodCartId() {
		return goodCartId;
	}

	public void setGoodCartId(String goodCartId) {
		this.goodCartId = goodCartId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getInvoiceInfoId() {
		return invoiceInfoId;
	}

	public void setInvoiceInfoId(String invoiceInfoId) {
		this.invoiceInfoId = invoiceInfoId;
	}

	public RedPacketBean getRedPacketBean() {
		return redPacketBean;
	}

	public void setRedPacketBean(RedPacketBean redPacketBean) {
		this.redPacketBean = redPacketBean;
	}

	public CouponOrderBean getCouponBean() {
		return couponBean;
	}

	public void setCouponBean(CouponOrderBean couponBean) {
		this.couponBean = couponBean;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public BuyExpressModel getExpress() {
		return express;
	}

	public void setExpress(BuyExpressModel express) {
		this.express = express;
	}

	public String getStoreName() {
		return storeName;
	}

	public boolean isChoosed() {
		return isChoosed;
	}

	public boolean isEdtor() {
		return isEdtor;
	}

	public int getActive() {
		return Active;
	}

	public long getStoreId() {
		return storeId;
	}

	public List<CartGoodBean> getGoodsCartList() {
		return goodsCartList;
	}

	public void setChoosed(boolean choosed) {
		isChoosed = choosed;
	}

	public void setEdtor(boolean edtor) {
		isEdtor = edtor;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setActive(int active) {
		Active = active;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public void setGoodsCartList(List<CartGoodBean> goodsCartList) {
		this.goodsCartList = goodsCartList;
	}

	public JsonObject getOrderJson() {

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("invoiceInfoId", invoiceInfoId);
		jsonObject.addProperty("feeType", express.getChoses());
		jsonObject.addProperty("msg", msg);
		jsonObject.addProperty("addressId", addressId);
		jsonObject.addProperty("couponInfoId", XEmptyUtils.isEmpty(couponBean) ? "" : String.valueOf(couponBean.getCouponInfoId()));
		jsonObject.addProperty("redpacketInfoId", XEmptyUtils.isEmpty(redPacketBean) ? "" : String.valueOf(redPacketBean.getRedpacketInfoId()));
		jsonObject.addProperty("goodsCartIds", goodCartId);
		jsonObject.addProperty("order_type", "android");
		return jsonObject;
	}

	@Override
	public String toString() {
		return "StoreInfoBean{" +
				"Active=" + Active +
				", storeId=" + storeId +
				", storeName='" + storeName + '\'' +
				", goodsCartList=" + goodsCartList +
				", express=" + express +
				", addressId='" + addressId + '\'' +
				", isChoosed=" + isChoosed +
				", isEdtor=" + isEdtor +
				", invoiceInfoId='" + invoiceInfoId + '\'' +
				", redPacketBean=" + redPacketBean +
				", couponBean=" + couponBean +
				", msg='" + msg + '\'' +
				", goodCartId='" + goodCartId + '\'' +
				'}';
	}
}
