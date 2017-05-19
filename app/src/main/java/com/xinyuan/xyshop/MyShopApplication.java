package com.xinyuan.xyshop;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.youth.xframe.XFrame;
import com.youth.xframe.base.XApplication;
import com.youth.xframe.cache.XCache;
import com.zhy.autolayout.config.AutoLayoutConifg;

import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Created by Administrator on 2017/5/2 0002.
 */

public class MyShopApplication extends XApplication {
	private static MyShopApplication instance;

	//一个标记
	public static String TAG;
	public Context context;
	private ArrayList<String> searchKeyList = new ArrayList();


	@Override
	public void onCreate() {
		super.onCreate();
		AutoLayoutConifg.getInstance().useDeviceSize();
		XFrame.init(this);
		XFrame.initXLog()
				.setTag("XYShop")//设置全局tag
				.setShowThreadInfo(true)//是否开启线程信息显示，默认true
				.setDebug(true);//是否显示日志，默认true，发布时最好关闭
		TAG = this.getClass().getSimpleName();
		instance = MyShopApplication.this;
		OkGo.init(this);
		OkGo.getInstance()
				.debug("OKGO", Level.INFO, true)
				.setCacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
				.setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
				.setRetryCount(3);
	}


	public ArrayList<String> getSearchKeyList() {

		return this.searchKeyList;
	}

	public void setSearchKeyList(ArrayList<String> searchKeyList) {
		this.searchKeyList = searchKeyList;

	}

	public static MyShopApplication getInstance() {
		return instance;
	}

}
