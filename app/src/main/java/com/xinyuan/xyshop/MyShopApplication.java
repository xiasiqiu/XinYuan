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
	private static String keyWord;

	//一个标记
	public static String TAG;
	public Context context;
	private ArrayList<String> searchKeyList = new ArrayList();


	@Override
	public void onCreate() {
		super.onCreate();
		AutoLayoutConifg.getInstance().useDeviceSize();
		XFrame.init(this);
		TAG = this.getClass().getSimpleName();
		instance = MyShopApplication.this;
		OkGo.init(this);
		OkGo.getInstance()
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

	public static String getKeyWord() {
		return keyWord;
	}

	public static void setKeyWord(String keyWord) {
		MyShopApplication.keyWord = keyWord;
	}
}
