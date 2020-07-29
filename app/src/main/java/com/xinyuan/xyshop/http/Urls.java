package com.xinyuan.xyshop.http;

/**
 * Created by fx on 2017/5/9 0009.
 */

public class Urls {

    public static final String HOSTIP = "http://47.92.99.44:80/xymall/";
    public static final String HOSTNAME = "http://47.92.99.44:80/xymall/api/mobile/";
    public static final String HOSTNAME_COMM = "http://47.92.99.44:80/xymall/api/";

//	public static final String HOSTIP = "http://192.168.0.128:8080/";
//	public static final String HOSTNAME = "http://192.168.0.128:8080/api/mobile/";
//	public static final String HOSTNAME_COMM = "http://192.168.0.128:8080/api/";

///
//	public static final String HOSTIP = "http:///192.168.0.117:8080/";
//	public static final String HOSTNAME = "http://192.168.0.117:8080/api/mobile/";
//	public static final String HOSTNAME_COMM = "http://192.168.0.117:8080/api/";

//	public static final String HOSTIP = "http://www2.chinagjgx.com/xymall/";
//	public static final String HOSTNAME = "http://www2.chinagjgx.com/xymall/api/mobile/";
//	public static final String HOSTNAME_COMM = "http://www2.chinagjgx.com/xymall/api/";


    public static final String URL_SHARE_GOOD = "http://www.chinagjgx.com/product_buy?goods_id=";
    public static final String URL_APP_UPDATE = HOSTNAME + "version.htm";//APP更新

    public static final String URL_HOME_INDEX = HOSTNAME + "home.htm";//首页数据
    public static final String URL_MENU = HOSTNAME + "menushow.htm";//首页菜单数据
    public static final String URL_SEARCH_KEYWORD = HOSTNAME + "brandHotSearch.htm";//搜索热词数据
    public static final String URL_ARTICLE_LIST = HOSTNAME + "articleshow.htm";//平台公告列表
    public static final String URL_ARTICLE_DETAIL = HOSTNAME + "articleSee.htm";//平台公告详情
    public static final String URL_CREDIT_LIST = HOSTNAME + "integralshow.htm";//积分动态列表
    public static final String URL_BRAND = HOSTNAME + "brandStore.htm";//品牌数据
    public static final String URL_SIGN = HOSTNAME + "signIn.htm";//每日签到

    public static final String URL_GROUP_BUY = HOSTNAME + "groupGoods.htm";//团购商城


    public static final String URL_CREDIT_MALL = HOSTNAME + "integral/home.htm";//积分商城数据
    public static final String URL_CREDIT_GOODS = HOSTNAME + "integralGoodsRecommended.htm";//积分商城推荐商品数据
    public static final String URL_CREDIT_GOOD = HOSTNAME + "mobileIntegralGoods.htm";//积分商城-商品数据
    public static final String URL_CREDIT_RED = HOSTNAME + "mobileIntegralRedPackets.htm";//积分商城-红包数据
    public static final String URL_CREDIT_COUPON = HOSTNAME + "mobileIntegralCoupons.htm";//积分商城-优惠券数据


    public static final String URL_SEARCH_GOOD = HOSTNAME + "searchGoods.htm";//搜索商品数据
    public static final String URL_SEARCH_STORE = HOSTNAME + "searchStore.htm";//搜索店铺数据


    public static final String URL_GOODS_CATEGORY = HOSTNAME + "goodsClassList.htm";//分类数据


    public static final String URL_GOODS_CART = HOSTNAME + "userGoodsCartList.htm";//购物车数据
    public static final String URL_GOODS_CART_DETELE = HOSTNAME + "delGoodsCart.htm";//删除购物车数据

    public static final String URL_GOODS_CART_NUM = HOSTNAME + "userGoodcartCountSave.htm";//购物车商品数量确认

    public static final String URL_GOODS_RECOME_GOODS = HOSTNAME + "goodsRecommended.htm";//推荐商品数据


    public static final String URL_GOODS_DETAIL = HOSTNAME + "goods.htm";//商品详情数据
    public static final String URL_GOODS_ADD_DETAIL = HOSTNAME_COMM + "addBY.htm";//添加商品浏览数据
    public static final String URL_GOODS_ADD_CART = HOSTNAME + "addGoodsCart.htm";//添加商品进购物车


    public static final String URL_STORE_INDEX = HOSTNAME + "store.htm";//店铺首页数据
    public static final String URL_STORE_GOODS = HOSTNAME + "storegoods.htm";//店铺商品数据
    public static final String URL_STORE_ACTIVITY = HOSTNAME + "store_goods_group.htm";//店铺活动数据
    public static final String URL_STORE_GET_RED = HOSTNAME + "mobileUserDrawRedPacket.htm";//领取店铺红包
    public static final String URL_STORE_GET_COUPON = HOSTNAME + "mobileUserDrawCoupon.htm";//领取店铺优惠券


    public static final String URL_USER_INVOICE = HOSTNAME + "userInvoice.htm";//订单发票数据
    public static final String URL_USER_INVOICE_ADDHEAD = HOSTNAME + "userAddInvoiceHeader.htm";//新增发票抬头
    public static final String URL_USER_INVOICE_SAVE = HOSTNAME + "userInvoiceInfo.htm";//保存发票数据

    public static final String URL_USER_BUY_COUPON = HOSTNAME + "userCouponToOrderForm.htm";//订单选择优惠券
    public static final String URL_USER_BUY_RED = HOSTNAME + "userRedPacketToOrderForm.htm";//订单选择红包
    public static final String URL_USER_BUY_EXPRESS = HOSTNAME + "mobileOrderFormFeePrice.htm";//运费

    public static final String URL_USER_PUT_ORDER = HOSTNAME + "mobileUserConfirmOrderForm.htm";//提交实物订单
    public static final String URL_USER_PUT_ONORDER = HOSTNAME + "mobile_add_virOrderForm.htm";//提交虚拟订单

    public static final String URL_USER_ORDER_ALIPAY = HOSTNAME_COMM + "pay/alipay/sign.htm";//支付宝订单签名
    public static final String URL_USER_ORDER_ALIPAY_NOTIFY = HOSTNAME_COMM + "pay/alipay/query.htm";//支付通知


    public static final String URL_USER_ORDER = HOSTNAME + "mobileUserOrderFormList.htm";//实物订单数据
    public static final String URL_USER_ORDER_DETAIL = HOSTNAME + "mobileOrderDetails.htm";//实物订单详情数据

    public static final String URL_USER_ONORDER = HOSTNAME + "mobile_virOrderForm_list.htm";  //虚拟物订单数据
    public static final String URL_USER_ONORDER_DETAIL = HOSTNAME + "mobile_virOrderForm_details.htm";//虚拟订单详情数据

    public static final String URL_ORDER_EXPRESS = HOSTNAME + "mobileUserOrderShip.htm";//快递物流数据
    public static final String URL_ORDER_EXPRESS_NAME = HOSTNAME + "checkExpressCompany.htm?keyword=";//快递公司名称数据
    public static final String URL_ORDER_EVA = HOSTNAME + "goodsEvaluateSee.htm";//商品评价数据


    public static final String URL_ORDER_RECEIVE = HOSTNAME + "mobileUserReceiving.htm";//确认收货订单

    public static final String URL_ORDER_CANCEL = HOSTNAME + "mobileUserOrderCancel.htm";//取消实物订单
    public static final String URL_ONORDER_CANCEL = HOSTNAME + "mobile_virOrderForm_cancel.htm";//取消虚拟订单

    public static final String URL_USER_ORDER_DETELE = HOSTNAME + "mobileOrderDel.htm";//删除订单

    public static final String URL_ORDER_ADD_EVA = HOSTNAME + "goodsEvaluateAdd.htm";//评价订单
    public static final String URL_ORDER_ADD_ONLINE_EVA = HOSTNAME + "virGoodsEvaluateAdd.htm";//评价虚拟订单
    public static final String URL_ORDER_RATING_EVA = HOSTNAME + "goodsRatingAdd.htm";//追评订单
    public static final String URL_ORDER_RATING_ONLINE_EVA = HOSTNAME + "virGoodsRatingAdd.htm";//追评虚拟订单


    public static final String URL_ORDER_SERVICE_REASON = HOSTNAME + "mobileGoodsRefundReson.htm";//请求退货退款原因
    public static final String URL_ORDER_SERVICE_GOOD = HOSTNAME + "mobileOrderGoodsRuturn.htm";//请求退货
    public static final String URL_ORDER_SERVICE_MONEY = HOSTNAME + "mobileOrderGoodsRefund.htm";//请求退款

    public static final String URL_ORDER_SERVICE_CANCEL = HOSTNAME + "mobileGoodsRefundCancel.htm";//撤销退款


    public static final String URL_ORDER_SERVICE = HOSTNAME + "mobileOrderReturnList.htm";//售后列表

    public static final String URL_ORDER_SERVICE_MONEY_DETAIL = HOSTNAME + "mobileOrderRefundDetail.htm";//退款详情
    public static final String URL_ORDER_SERVICE_GOOD_DETAIL = HOSTNAME + "mobileOrderReturnDetail.htm";//退货详情
    public static final String URL_ORDER_SERVICE_GOOD_RETURN = HOSTNAME + "mobileOrderFillNumber.htm";//买家退货

    public static final String URL_EXPRESS_COMPANY = HOSTNAME + "mobileExpressCompanyList.htm";//物流公司


    //用户相关
    public static final String URL_USER_INFO = HOSTNAME + "userInfo.htm";//个人中心首页数据
    public static final String URL_USER_FOOT = HOSTNAME + "userSeeShow.htm";//个人足迹数据
    public static final String URL_USER_FOOT_DETELE = HOSTNAME + "userSeeCancel.htm";//个人足迹删除
    public static final String URL_USER_FAV_STORE = HOSTNAME + "storeShow.htm";//收藏店铺数据
    public static final String URL_USER_ADD_FAV = HOSTNAME_COMM + "addFavorite.htm";//添加收藏
    public static final String URL_USER_FAV_GOODS = HOSTNAME + "goodsShow.htm";//收藏商品数据
    public static final String URL_USER_FAV_GOODS_DETELE = HOSTNAME + "favoriteCancel.htm";//批量取消收藏商品
    public static final String URL_USER_FAV_CANCEL = HOSTNAME + "gsFavoriteCancel.htm";//单个取消收藏


    public static final String URL_USER_ACCOUNT = HOSTNAME + "userBalanue.htm";//查询用户余额
    public static final String URL_USER_RED_PACKT = HOSTNAME + "userRedpacket.htm";//用户红包
    public static final String URL_USER_COUPON = HOSTNAME + "userCoupon.htm";//用户红包
    public static final String URL_USER_CREDIT = HOSTNAME + "userIntegralShow.htm";//用户积分
    public static final String URL_USER_PROPERTY = HOSTNAME + "userAssets.htm";//用户财产

    public static final String URL_USER_SECURITY = HOSTNAME + "userSecurityAndAccount.htm";//用户安全与设置数据


    public static final String URL_USER_CHAT_LIST = HOSTNAME + "mobile_chatting_list.htm";//消息列表
    public static final String URL_USER_MSG_STATE = HOSTNAME + "chatting_check.htm";//检查是否有新消息
    public static final String URL_USER_MSG_SEND = HOSTNAME + "mobile_chatting_send.htm";//发送消息
    public static final String URL_USER_MSG_NEW = HOSTNAME + "chatting_new_list.htm";//接收消息
    public static final String URL_USER_MSG_DETAIL = HOSTNAME + "chatting_read_list.htm";//接收消息详情
    public static final String URL_USER_MSG_HISTORY = HOSTNAME + "chatting_old_list.htm";//接收历史消息
    public static final String URL_USER_MSG_DETELE = HOSTNAME + "chatting_del.htm";//删除消息

    public static final String URL_MSG = HOSTNAME + "userMessage.htm";//系统通知列表
    public static final String URL_MSG_DE = HOSTNAME + "userMessageDel.htm";//删除系统通知

    //用户设置
    public static final String URL_USER_ADDRESS = HOSTNAME + "addressShow.htm";//用户收货地址
    public static final String URL_USER_ADDRESS_UPDATE = HOSTNAME + "addressUpdate.htm";//修改收货地址
    public static final String URL_USER_AREA = HOSTNAME + "areaSee.htm";//地区列表
    public static final String URL_USER_AREA_DETELE = HOSTNAME + "addressDel.htm";//删除地区列表
    public static final String URL_USER_AREA_DEFAULT = HOSTNAME + "addressSet.htm";//地区列表
    public static final String URL_UPDATE_USERHEAD = HOSTNAME + "uploadUserPhoto.htm";//更新用户头像
    public static final String URL_UPDATE_USER = HOSTNAME + "updateUserInfo.htm";//更新用户资料
    public static final String URL_USER_SUGGES = HOSTNAME + "feedbackAdd.htm";//反馈建议


    // 登录注册
    public static final String URL_CHECK_TOKEN = HOSTNAME + "checkToken.htm";//Token验证
    public static final String URL_LOGIN_SCAN = HOSTIP + "userScodeLoginAction/appSure.htm";//PC扫码登录
    public static final String URL_SEND_SMS = HOSTNAME + "sendSMS.htm";//发送短信验证码
    public static final String URL_PHONE_LOGIN = HOSTNAME + "loginSMS.htm";//快捷登陆

    public static final String URL_USER_LOGIN = HOSTNAME + "login.htm";//普通登陆
    public static final String URL_USER_REG = HOSTNAME + "userRegister.htm";//用户注册
    public static final String URL_EXIST_PHONE = HOSTNAME + "exist.htm";//检查用户名或手机号是否存在
    public static final String URL_CHECK_SMS = HOSTNAME + "checkSMS.htm";//短信验证
    public static final String URL_USER_FORGET = HOSTNAME + "forgetPass.htm";//忘记密码

    public static final String URL_LOGIN_FAST = HOSTNAME + "mobile_login_bind.htm";//第三方快捷登录
    public static final String URL_LOGIN_BIND = HOSTNAME + "mobile_user_info.htm";//第三方账号绑定
    public static final String URL_LOGIN_UNBIND = HOSTNAME + "mobile_login_unbind.htm";//第三方账号解绑
    public static final String URL_EMAIL_CODE = HOSTNAME + "sendEmail.htm";//发送邮箱验证码
    public static final String URL_EMAIL_BIND = HOSTNAME + "bindingEmail.htm";//绑定邮箱
    public static final String URL_EMAIL_UNBIND = HOSTNAME + "unbindingEmail.htm";//邮箱解绑


    public static final String URL_ORDER_STATUS = HOSTNAME + "mobileOrderStatus.htm";//更改订单状态(开发用)
    public static final String URL_ONORDER_STATUS = HOSTNAME + "mobileVofPay.htm";//更改订单状态(开发用)


}
