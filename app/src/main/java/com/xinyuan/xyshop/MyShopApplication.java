package com.xinyuan.xyshop;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.common.QueuedWork;
import com.xinyuan.xyshop.bean.UserInfoBean;
import com.xinyuan.xyshop.ui.home.ErrorActivity;
import com.xinyuan.xyshop.ui.welcome.WelcomeActivity;
import com.xinyuan.xyshop.util.ImagePickerLoader;
import com.xinyuan.xyshop.util.ScreenAdaptation;
import com.youth.xframe.XFrame;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import me.yokeyword.fragmentation.Fragmentation;
import okhttp3.OkHttpClient;

/**
 * Created by fx on 2017/5/2 0002.
 * 主Application
 */

public class MyShopApplication extends Application {
	private static MyShopApplication instance;
	public Context context;
	public static UserInfoBean userInfo;
	public static String Token = "";
	public static long userId = 0;
	public static boolean IsNewMsg = false;
	public static long chatting_logID = 0;
	public static long chatting_ID;
	public static ImagePicker imagePicker;

	public static MyShopApplication getInstance() {
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		new ScreenAdaptation(this, 720, 1280).register();
		instance = this;
		XFrame.init(this);  //XFrame 底层框架注入
		Fragmentation.builder()     //Fragmentation框架注入
				.debug(BuildConfig.DEBUG)
				.install();
		initCrash();//替换Crash
		Config.DEBUG = true;        //开发模式
		QueuedWork.isUseThreadPool = false;     //友盟
		UMShareAPI.get(this);
		getImageSelect();               //设置图片选择
		getOKHttp();                    //设置网络框架

	}

	/**
	 * 自定义崩溃界面
	 */
	private void initCrash() {
		//Install CustomActivityOnCrash
		CustomActivityOnCrash.install(this);
		CustomActivityOnCrash.setLaunchErrorActivityWhenInBackground(true);
		CustomActivityOnCrash.setShowErrorDetails(true);
		CustomActivityOnCrash.setDefaultErrorActivityDrawable(R.drawable.act_home_msg_de);
		CustomActivityOnCrash.setEnableAppRestart(true);
		CustomActivityOnCrash.setErrorActivityClass(ErrorActivity.class);
		//The EventListener you provide can not be an anonymous or non-static inner class
		CustomActivityOnCrash.setEventListener(new MyEventListener());
		CustomActivityOnCrash.setRestartActivityClass(WelcomeActivity.class);
		//CustomActivityOnCrash.setErrorActivityClass(Activity2.class);


	}

	/**
	 * 设置图片选择器
	 */
	private void getImageSelect() {
		imagePicker = ImagePicker.getInstance();
		imagePicker.setImageLoader(new ImagePickerLoader());   //设置图片加载器
		imagePicker.setMultiMode(true);
		imagePicker.setShowCamera(true);  //显示拍照按钮
		imagePicker.setCrop(true);        //允许裁剪（单选才有效）
		imagePicker.setSaveRectangle(true); //是否按矩形区域保存
		imagePicker.setSelectLimit(1);    //选中数量限制
		imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
		imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
		imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
		imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
		imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
	}

	/**
	 * 网络框架加载
	 */
	private void getOKHttp() {

		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
		loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
		loggingInterceptor.setColorLevel(Level.INFO);
		builder.addInterceptor(loggingInterceptor);
		builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);      //全局的读取超时时间
		builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);     //全局的写入超时时间
		builder.connectTimeout(30000, TimeUnit.MILLISECONDS);   //全局的连接超时时间
		builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));
		HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
		OkGo.getInstance().init(this)                           //必须调用初始化
				.setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置会使用默认的
				.setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
				.setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
				.setRetryCount(3);    //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0


	}


	/**
	 * 友盟QQ、微信设置
	 */ {
		PlatformConfig.setWeixin("wxd9ca088409799579", "3c2e691f3f0905ee821e12cf95641f19");
		PlatformConfig.setQQZone("101433770", "c3c98015e0d1524c7b60aa09b3b654b0");


	}


	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}

	/**
	 * 崩溃监听回调
	 */
	private static class MyEventListener implements CustomActivityOnCrash.EventListener {
		private static final long serialVersionUID = 365515429841960368L;

		@Override
		public void onLaunchErrorActivity() {
			Log.i("bqt", "onLaunchErrorActivity");
		}

		@Override
		public void onRestartAppFromErrorActivity() {
			Log.i("bqt", "onRestartAppFromErrorActivity");
		}

		@Override
		public void onCloseAppFromErrorActivity() {
			Log.i("bqt", "onCloseAppFromErrorActivity");
		}
	}
}
