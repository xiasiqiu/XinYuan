package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.callback.JsonConvert;
import com.xinyuan.xyshop.model.ChatListModule;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.mvp.contract.StoreMsgView;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by fx on 2017/8/5.
 */

public class StoreMsgPresenter extends BasePresenter<StoreMsgView> {

	private static Activity activity;

	public StoreMsgPresenter(StoreMsgView view, Activity context) {
		super(view);
		this.activity = context;
	}

	/**
	 * 获取店铺消息列表
	 *
	 * @param page
	 * @param limit
	 */
	public void getMsgList(int page, int limit) {
		OkGo.<LzyResponse<ChatListModule>>post(Urls.URL_USER_CHAT_LIST)
				.headers("token", MyShopApplication.Token)
				.params("id", MyShopApplication.userId)
				.params("ship", page)
				.params("limit", limit)
				.converter(new JsonConvert<LzyResponse<ChatListModule>>() {
				})
				.adapt(new ObservableBody<LzyResponse<ChatListModule>>())
				.subscribeOn(Schedulers.io())//
				.doOnSubscribe(new Consumer<Disposable>() {
					@Override
					public void accept(@NonNull Disposable disposable) throws Exception {

					}
				})
				.map(new Function<LzyResponse<ChatListModule>, ChatListModule>() {
					@Override
					public ChatListModule apply(@NonNull LzyResponse<ChatListModule> response) throws Exception {
						if (HttpUtil.handleResponse(activity, response)) {
							if (XEmptyUtils.isEmpty(response.datas)) {
								ChatListModule ch = new ChatListModule();
								return ch;
							} else {
								return response.datas;
							}
						} else {
							ChatListModule ch = new ChatListModule();
							return ch;

						}
					}
				})
				.observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
				.compose(mView.bindLife())
				.subscribe(new Observer<ChatListModule>() {
					@Override
					public void onSubscribe(@NonNull Disposable d) {

					}

					@Override
					public void onNext(@NonNull ChatListModule chatListModule) {
						mView.showMsgList(chatListModule);
					}

					@Override
					public void onError(@NonNull Throwable e) {
						e.printStackTrace();
						mView.showState(2);
					}

					@Override
					public void onComplete() {


					}
				});


	}

	/**
	 * 删除消息
	 * @param userId
	 * @param chattingId
	 */
	public void deteleMsg(long userId, final long chattingId) {
		OkGo.<LzyResponse<ChatListModule>>post(Urls.URL_USER_MSG_DETELE)
				.headers("token", MyShopApplication.Token)
				.params("id", MyShopApplication.userId)
				.params("chattingId", chattingId)
				.converter(new JsonConvert<LzyResponse<ChatListModule>>() {
				})
				.adapt(new ObservableBody<LzyResponse<ChatListModule>>())
				.subscribeOn(Schedulers.io())//
				.doOnSubscribe(new Consumer<Disposable>() {
					@Override
					public void accept(@NonNull Disposable disposable) throws Exception {

					}
				})
				.map(new Function<LzyResponse<ChatListModule>, ChatListModule>() {
					@Override
					public ChatListModule apply(@NonNull LzyResponse<ChatListModule> response) throws Exception {
						if (HttpUtil.handleResponse(activity, response)) {
							if (XEmptyUtils.isEmpty(response.datas)) {
								ChatListModule ch = new ChatListModule();
								return ch;
							} else {
								return response.datas;
							}
						} else {
							ChatListModule ch = new ChatListModule();
							return ch;

						}
					}
				})
				.observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
				.compose(mView.bindLife())
				.subscribe(new Observer<ChatListModule>() {
					@Override
					public void onSubscribe(@NonNull Disposable d) {

					}

					@Override
					public void onNext(@NonNull ChatListModule chatListModule) {
						mView.showDeteleState();
					}

					@Override
					public void onError(@NonNull Throwable e) {
						e.printStackTrace();
						mView.showState(2);
					}

					@Override
					public void onComplete() {


					}
				});
	}
}
