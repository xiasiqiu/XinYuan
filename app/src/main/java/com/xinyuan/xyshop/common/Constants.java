package com.xinyuan.xyshop.common;

import android.os.Environment;

import com.xinyuan.xyshop.R;

/**
 * Created by fx on 2017/5/25.
 */

public class Constants {
	public static final String ROOT_DIR;


	/**
	 * 跳转类型
	 */
	public static final String GOOD = "good";             //商品跳转
	public static final String STORE = "store";           //店铺跳转
	public static final String NATIVE = "native";         //本地跳转
	public static final String HTML = "html";             //网页跳转

	/**
	 * 跳转参数
	 */
	public static final String URL = "URL";               //跳转网址
	public static final String STOREID = "STOREID";       //店铺Id
	public static final String GOODID = "GOODID";         //商品ID
	public static final String GOODTYPE = "GOODTYPE";     //商品类型
	public static final String KEYWORD = "KEYWORD";       //搜索关键词


	public static final String USERNAME = "USERNAME";   //聊天对方的用户名
	public static final String USERHEAD = "USERHEAD";   //聊天对方的头像
	public static final String USERID = "USERID";       //聊天对方的ID


	public static final String NEWID = "NEWID";       //文章ID
	public static final String NEWTITLE = "NEWTITLE";       //文章标题


	/**
	 * 标识
	 */
	public static final String GOOD_NORMAL = "1";   //实物商品标识
	public static final String GOOD_ONLINE = "2";   //虚拟商品标识
	public static final String GOOD_CREDIT = "4";   //积分兑换商品标识

	public static final String CREDIT_RED = "5";   //积分兑换红包标识
	public static final String CREDIT_COUPON = "6";   //积分兑换优惠券标识

	public static final String GOODSPEC = "0";   //积分兑换优惠券标识
	/**
	 * 支付
	 */
	public static final int ALIPAY = 00001;
	public static final int WECHATPAY = 00002;
	public static final int YINLANPAY = 00003;

	/**
	 * 缓存标识
	 */
	public static final String SEARCH_HISTORY = "SEARCH_HISTORY"; //搜索历史记录

	static {
		if ("mounted".equals(Environment.getExternalStorageState())) {
			ROOT_DIR = Environment.getExternalStorageDirectory().getAbsolutePath();
		} else {
			ROOT_DIR = Environment.getRootDirectory().getAbsolutePath();
		}
	}

	public static final String SYSTEM_INIT_FILE_NAME = "sysini";
	public static final String APP_DIR = (ROOT_DIR + "/xinyuan");
	public static final String CACHE_DIR = (APP_DIR + "/cachePic");
	public static final String LOG_DIR = (APP_DIR + "/log");
	public static final String APK_DIR = (APP_DIR + "/apk");

	public static final String WECHAT_APPID = "wx05810d9b3436cc3b";       //微信APPID
	public static final String WECHAT_APPID_KEY = "421bf1054837cfb9a3f7cae8af7e6cc1";//微信APPKEY
	/**
	 * 网络状态码
	 */
	public static final int HTTP_112 = 123112;   //输入格式错误
	public static final int HTTP_100 = 123100;   //用户已存在
	public static final int HTTP_101 = 123101;   //用户不存在
	public static final int HTTP_102 = 123102;   //输入密码错误
	public static final int HTTP_103 = 123103;   //用户已被冻结
	public static final int HTTP_104 = 123104;   //TOKEN验证失败
	public static final int HTTP_105 = 123105;   //手机号未绑定
	public static final int HTTP_106 = 123106;   //验证码错误
	public static final int HTTP_107 = 123107;   //验证码超时
	public static final int HTTP_108 = 123108;   //发送验证码时间间隔不足一分钟
	public static final int HTTP_109 = 123109;   //图片上传失败
	public static final int HTTP_110 = 123110;   //手机号已注册
	public static final int HTTP_111 = 123111;   //手机号未注册
	public static final int HTTP_113 = 123113;   //操作失败
	public static final int HTTP_114 = 123114;   //格式错误
	public static final int HTTP_115 = 123115;   //没有新消息
	public static final int HTTP_116 = 123116;   //店铺不存在
	public static final int HTTP_117 = 123117;   //收藏失败
	public static final int HTTP_118 = 123118;   //商品不存在
	public static final int HTTP_119 = 123119;   //商品已加入购物车
	public static final int HTTP_199 = 123199;   //已签到
	public static final int HTTP_200 = 123200;   //返回数据成功
	public static final int HTTP_201 = 123201;   //库存不足
	public static final int HTTP_202 = 123202;   //商品购买上限
	public static final int HTTP_203 = 123203;   //红包不存在
	public static final int HTTP_204 = 123204;   //优惠券不存在
	public static final int HTTP_205 = 123205;   //已领取
	public static final int HTTP_206 = 123206;   //商品已下架
	public static final int HTTP_207 = 123207;   //账号已绑定快捷方式
	public static final int HTTP_208 = 123208;   //微信支付参数不全
	public static final int HTTP_209 = 123209;   //微信支付回调信息
	public static final int HTTP_210 = 123210;   //微信支付参数信息
	public static final int HTTP_211 = 123211;   //订单操作失败
	public static final int HTTP_212 = 123212;   //邮箱已被绑定
	public static final int HTTP_213 = 123213;   //登录方式不足
	public static final int HTTP_215 = 123215;   //商品暂不提供购买服务
	public static final int HTTP_216 = 123216;   //店铺暂不开放

	public static final int HTTP_301 = 123301;   //二维码超时
	public static final int HTTP_300 = 123300;   //参数为空
	public static final int HTTP_302 = 123302;   //二维码已失效


}
