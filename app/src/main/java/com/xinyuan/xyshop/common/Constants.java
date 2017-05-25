package com.xinyuan.xyshop.common;

import android.os.Environment;

/**
 * Created by Administrator on 2017/5/25.
 */

public class Constants {
	public static final String ROOT_DIR;

	static {
		if ("mounted".equals(Environment.getExternalStorageState())) {
			ROOT_DIR = Environment.getExternalStorageDirectory().getAbsolutePath();
		} else {
			ROOT_DIR = Environment.getRootDirectory().getAbsolutePath();
		}
	}

	public static final String SYSTEM_INIT_FILE_NAME = "sysini";
	public static final String APP_DIR = (ROOT_DIR + "/trunk");
	public static final String CACHE_DIR = (APP_DIR + "/cachePic");
	public static final String LOG_DIR = (APP_DIR + "/log");
}
