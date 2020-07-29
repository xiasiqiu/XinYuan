package com.xinyuan.xyshop.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.bean.BaseUserInfo;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.bean.MsgTokenBean;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.LoginModel;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by fx on 2017/8/11.
 */

public class MsgService extends IntentService {
	private static Boolean isStart = true;
	private MsgThread msgThread;
	public static Boolean isrunning = false;

	/*构造函数*/
	public MsgService() {
		//调用父类的构造函数
		//构造函数参数=工作线程的名字
		super("MsgService");

	}

	@Override
	public void onCreate() {
		EventBus.getDefault().register(this);
		super.onCreate();
	}

	@Override
	protected void onHandleIntent(@Nullable Intent intent) {
		String taskName = intent.getExtras().getString("taskName");
		switch (taskName) {
			case "msgCheck":
				msgThread = new MsgThread();
				msgThread.start();
				isrunning = true;
				break;
			case "stopService":
				isrunning = false;
				stopSelf();
				break;

			default:
				break;
		}

	}

	private class MsgThread extends Thread {
		@Override
		public void run() {
			while (isStart) {
				try {
					// 每个3秒向服务器发送一次请求
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				getMsg();

			}
		}


	}

	@Subscribe(threadMode = ThreadMode.BACKGROUND)
	public void getID(LoginModel baseUserInfo) {
		XLog.v("收到ID：" + baseUserInfo.toString());
		if (!XEmptyUtils.isEmpty(baseUserInfo)) {
			isrunning = true;
		}

	}

	/**
	 * 监测是否有新消息
	 */
	private void getMsg() {
		if (isrunning) {
			OkGo.<LzyResponse<TokenBean>>post(Urls.URL_USER_MSG_STATE)
					.tag(this)
					.headers("token", MyShopApplication.Token)
					.params("id", MyShopApplication.userId)
					.execute(new JsonCallback<LzyResponse<TokenBean>>() {
						@Override
						public void onSuccess(Response<LzyResponse<TokenBean>> response) {
							if (response.code() == 200) {
								if (response.body().code == Constants.HTTP_200) {
									MyShopApplication.IsNewMsg = true;
									isrunning = false;
								} else if (response.body().getCode() == Constants.HTTP_115) {
									MyShopApplication.IsNewMsg = false;
								} else if (response.body().getCode() == Constants.HTTP_114) {
									MyShopApplication.IsNewMsg = false;
									XLog.v("未登录");
									isrunning = false;
								}
							}
						}

						@Override
						public void onError(Response<LzyResponse<TokenBean>> response) {
							XLog.v("检查是否有新消息错误");
						}
					});
		}
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}


	@Override
	public void onDestroy() {
		EventBus.getDefault().unregister(this);
		super.onDestroy();
	}
}
