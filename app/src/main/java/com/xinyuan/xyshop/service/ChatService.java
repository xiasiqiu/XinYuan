package com.xinyuan.xyshop.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.bean.MsgTokenBean;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.ChatDetailModule;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by fx on 2017/8/5.
 */

public class ChatService extends IntentService {
	private static Boolean isStart = true;
	private ChatThread chatThread;
	private static String chattingId;
	private static String chattinglogId;
	private static Boolean isrunning = false;

	/*构造函数*/
	public ChatService() {
		//调用父类的构造函数
		//构造函数参数=工作线程的名字
		super("ChatService");

	}


	@Override
	public void onCreate() {
		System.out.println("oncreate()");
		EventBus.getDefault().register(this);
		super.onCreate();
	}


	@Override
	protected void onHandleIntent(@Nullable Intent intent) {
		String taskName = intent.getExtras().getString("taskName");
		switch (taskName) {
			case "chatCheck":
				chatThread = new ChatThread();
				chatThread.start();
				isrunning = true;
				break;
			case "stopService":
				//this.isStart = false;
				isrunning = false;
				stopSelf();
				break;

			default:
				break;
		}

	}

	private class ChatThread extends Thread {
		@Override
		public void run() {
			while (isStart) {
				try {
					// 每个3秒向服务器发送一次请求
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				getChat();

			}
		}


	}


	@Subscribe(threadMode = ThreadMode.BACKGROUND)
	public void getID(MsgTokenBean msgTokenBean) {
		XLog.v("收到ID：" + msgTokenBean.toString());
		if (!XEmptyUtils.isEmpty(msgTokenBean)) {
			this.chattingId = msgTokenBean.getChattingId();
			this.chattinglogId = msgTokenBean.getChattinglogId();
		}

	}

	/**
	 * 获取新消息
	 */
	private void getChat() {
		if (isrunning) {
			OkGo.<LzyResponse<ChatDetailModule>>post(Urls.URL_USER_MSG_NEW)
					.tag(this)
					.headers("token", MyShopApplication.Token)
					.params("id", MyShopApplication.userId)
					.params("chattingId", MyShopApplication.chatting_ID)
					.params("chattinglogId", MyShopApplication.chatting_logID)
					.execute(new JsonCallback<LzyResponse<ChatDetailModule>>() {
						@Override
						public void onSuccess(Response<LzyResponse<ChatDetailModule>> response) {
							if (!XEmptyUtils.isEmpty(response.body())) {
								if (response.body().getCode() == Constants.HTTP_200) {
									if (!XEmptyUtils.isEmpty(response.body().datas)) {
										ChatDetailModule chatListModule = response.body().datas;
										EventBus.getDefault().post(chatListModule);
									}
								} else {
								}
							}
						}

						@Override
						public void onError(Response<LzyResponse<ChatDetailModule>> response) {
							//HttpUtil.handleError(response);
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
