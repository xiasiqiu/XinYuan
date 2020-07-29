package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.bean.UserInfoBean;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.callback.JsonConvert;
import com.xinyuan.xyshop.bean.ChatSendBean;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.AddressModel;
import com.xinyuan.xyshop.model.ChatDetailModule;
import com.xinyuan.xyshop.mvp.contract.ChatDetailView;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by fx on 2017/8/5.
 */

public class ChatDetailPresenter extends BasePresenter<ChatDetailView> {
	private Activity activity;

	public ChatDetailPresenter(Activity activity, ChatDetailView view) {
		super(view);
		this.activity = activity;
	}

	/**
	 * 获取聊天详情列表
	 *
	 * @param chattingId
	 * @param userid
	 * @param limit
	 */
	public void getChatting(String chattingId, String userid, int limit) {
		OkGo.<LzyResponse<ChatDetailModule>>post(Urls.URL_USER_MSG_DETAIL)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("id", MyShopApplication.userId)
				.params("chattingId", chattingId)
				.params("user_id", userid)
				.params("limit", limit)
				.execute(new JsonCallback<LzyResponse<ChatDetailModule>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<ChatDetailModule>> response) {
						if (HttpUtil.handleResponse(activity, response.body())) {
							mView.showMsgList(response.body().getDatas());
						}

					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<ChatDetailModule>> response) {
						HttpUtil.handleError(activity, response);
					}
				});
	}

	/**
	 * 发送消息
	 *
	 * @param userId
	 * @param content
	 * @param unique
	 */
	public void postChat(String userId, String content, String unique) {
		OkGo.<LzyResponse<ChatSendBean>>post(Urls.URL_USER_MSG_SEND)
				.tag(this)
				.isMultipart(true)
				.headers("token", MyShopApplication.Token)
				.params("id", MyShopApplication.userId)
				.params("user_id", userId)
				.params("content", content)
				.params("unique", unique)
				.execute(new JsonCallback<LzyResponse<ChatSendBean>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<ChatSendBean>> response) {
						if (HttpUtil.handleResponse(activity, response.body())) {
							if (!XEmptyUtils.isEmpty(response)) {
								if (response.body().getCode() == 123200) {
									XLog.v(response.body().getDatas().toString());
									mView.sendState(true, String.valueOf(response.body().getDatas().getChattingInfo()));
								} else {
									mView.sendState(false, null);

								}
							}
						}


					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<ChatSendBean>> response) {
						HttpUtil.handleError(activity, response);
					}
				});
	}

	/**
	 * 发送图片消息
	 *
	 * @param userId
	 * @param content
	 * @param file
	 * @param unique
	 */
	public void postChatFile(String userId, String content, File file, String unique) {
		OkGo.<LzyResponse<ChatSendBean>>post(Urls.URL_USER_MSG_SEND)
				.tag(this)
				.isMultipart(true)
				.headers("token", MyShopApplication.Token)
				.params("id", MyShopApplication.userId)
				.params("user_id", userId)
				.params("file", file)
				.params("content", content)
				.params("unique", unique)
				.execute(new JsonCallback<LzyResponse<ChatSendBean>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<ChatSendBean>> response) {
						if (!XEmptyUtils.isEmpty(response)) {
							if (response.body().getCode() == Constants.HTTP_200) {
								mView.sendState(true, String.valueOf(response.body().getDatas().getChattingInfo()));
							} else {
								mView.sendState(false, null);

							}
						}

					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<ChatSendBean>> response) {
						HttpUtil.handleError(activity, response);
					}
				});
	}

	/**
	 * 拉取历史消息
	 *
	 * @param chattingId
	 * @param lastLogId
	 * @param limit
	 */
	public void getHistory(String chattingId, String lastLogId, int limit) {
		OkGo.<LzyResponse<ChatDetailModule>>post(Urls.URL_USER_MSG_HISTORY)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("id", MyShopApplication.userId)
				.params("chattingId", chattingId)
				.params("logId", lastLogId)
				.params("limit", limit)
				.execute(new JsonCallback<LzyResponse<ChatDetailModule>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<ChatDetailModule>> response) {
						if (HttpUtil.handleResponse(activity, response.body())) {
							mView.addHistory(response.body().getDatas());

						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<ChatDetailModule>> response) {
						HttpUtil.handleError(activity, response);
					}
				});

	}

	/**
	 * 获取个人信息
	 */
	public void getInfo() {
		OkGo.<LzyResponse<UserInfoBean>>post(Urls.URL_USER_INFO)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("token", MyShopApplication.Token)
				.execute(new JsonCallback<LzyResponse<UserInfoBean>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<UserInfoBean>> response) {
						if (HttpUtil.handleResponse(activity, response.body())) {
							mView.setUserInfo(response.body().datas);
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<UserInfoBean>> response) {
						HttpUtil.handleError(activity, response);
					}
				});


	}
}
