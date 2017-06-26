package com.xinyuan.xyshop;

import android.content.Context;
import android.content.SharedPreferences;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;
import com.xinyuan.xyshop.common.Constants;
import com.youth.xframe.XFrame;
import com.youth.xframe.base.XApplication;
import com.youth.xframe.utils.log.XLog;


import java.util.ArrayList;
import java.util.logging.Level;

import me.yokeyword.fragmentation.Fragmentation;

/**
 * Created by Administrator on 2017/5/2 0002.
 */

public class MyShopApplication extends XApplication {
	private static MyShopApplication instance;
	private static String keyWord;
	private SharedPreferences sysInitSharedPreferences;
	//一个标记
	public static String TAG;
	public Context context;
	private ArrayList<String> searchKeyList = new ArrayList();
	private String avatar;
	private String memberID;
	private String memberName;
	private String token;

	@Override
	public void onCreate() {
		super.onCreate();
		XFrame.init(this);
		TAG = this.getClass().getSimpleName();
		instance = MyShopApplication.this;
		OkGo.init(this);
		OkGo.getInstance()
				.setCacheMode(CacheMode.NO_CACHE)
				.setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
				.setRetryCount(3);
		Fragmentation.builder()
				.debug(true)
				.install();
		this.sysInitSharedPreferences = getSharedPreferences(Constants.SYSTEM_INIT_FILE_NAME, 0);
		this.token = this.sysInitSharedPreferences.getString("token", "");//获取缓存中的token
		this.memberID = this.sysInitSharedPreferences.getString("memberID", "");//获取缓存中的会员ID
		this.memberName = this.sysInitSharedPreferences.getString("memberName", "");//获取缓存中的会员名称
		this.avatar = this.sysInitSharedPreferences.getString("avatar", "");
		initTbs();
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

	public String getToken() {
		return this.sysInitSharedPreferences.getString("token", "");
	}

	public static String getKeyWord() {
		return keyWord;
	}

	public static void setKeyWord(String keyWord) {
		MyShopApplication.keyWord = keyWord;
	}

	private void initTbs() {
		QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

			@Override
			public void onViewInitFinished(boolean arg0) {
				XLog.i("onViewInitFinished is " + arg0);
			}

			@Override
			public void onCoreInitFinished() {
			}
		};

		QbSdk.setTbsListener(new TbsListener() {
			@Override
			public void onDownloadFinish(int i) {
				XLog.i("onDownloadFinish");
			}

			@Override
			public void onInstallFinish(int i) {
				XLog.i("onInstallFinish");
			}

			@Override
			public void onDownloadProgress(int i) {
				XLog.i("onDownloadProgress:" + i);
			}
		});

		QbSdk.initX5Environment(getApplicationContext(), cb);


	}

}
