package com.xinyuan.xyshop.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by fx on 2017/9/14.
 * 订单数据
 */

public class OrderBean implements Serializable {
    private static final long serialVersionUID = 1186871779237403244L;
    private String outOrderFormNumber;   //总订单ID
    private long orderFormId;        //订单ID
    private String orderNumber;          //订单编号
    private int orderType; //订单类型
    private String createTime;      //创建时间
    private String orderStatus;     //订单状态
    private BigDecimal shipPrice;   //订单运费
    private BigDecimal orderPrice;  //商品实际价格
    private int orderGoodsSum;      //订单商品数量
    private String address;         //订单收货地址
    private String receiver;        //订单收货人
    private String receiverPhone;   //订单收货手机
    private long storeId;           //店铺ID
    private String storePhone;      //店铺联系手机
    private String storeUserName;   //店铺联系人
    private long storeUserId;       //店铺客服ID
    private String countdown;       //订单部分操作倒计时
    private String storeName;       //店铺名
    private String storeUserImg;
    private BigDecimal couponsPrice; //优惠券金额
    private String redemption_code;//兑换码
    private String valid_period_time;
    private String msg; //订单用户留言
    private  BigDecimal goodsPrice; //商品总价

    public String getMsg() {
        return msg;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public int getOrderType() {
        return orderType;
    }

    public String getRedemption_code() {
        return redemption_code;
    }

    public String getValid_period_time() {
        return valid_period_time;
    }

    public BigDecimal getCouponsPrice() {
        return couponsPrice;
    }

    public String getStoreUserImg() {
        return storeUserImg;
    }

    public String getOutOrderFormNumber() {
        return outOrderFormNumber;
    }

    public String getStoreName() {
        return storeName;
    }

    private List<OrderGoodBean> goodsList;


    public String getOutOrderFormId() {
        return outOrderFormNumber;
    }

    public long getOrderFormId() {
        return orderFormId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public BigDecimal getShipPrice() {
        return shipPrice;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public int getOrderGoodsSum() {
        return orderGoodsSum;
    }

    public String getAddress() {
        return address;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public long getStoreId() {
        return storeId;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public String getStoreUserName() {
        return storeUserName;
    }

    public long getStoreUserId() {
        return storeUserId;
    }

    public String getCountdown() {
        return countdown;
    }

    public List<OrderGoodBean> getGoodsList() {
        return goodsList;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "outOrderFormNumber='" + outOrderFormNumber + '\'' +
                ", orderFormId=" + orderFormId +
                ", orderNumber='" + orderNumber + '\'' +
                ", createTime='" + createTime + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", shipPrice=" + shipPrice +
                ", orderPrice=" + orderPrice +
                ", orderGoodsSum=" + orderGoodsSum +
                ", address='" + address + '\'' +
                ", receiver='" + receiver + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", storeId=" + storeId +
                ", storePhone='" + storePhone + '\'' +
                ", storeUserName='" + storeUserName + '\'' +
                ", storeUserId=" + storeUserId +
                ", countdown='" + countdown + '\'' +
                ", goodsList=" + goodsList +
                '}';
    }
}
