package com.xinyuan.xyshop.http;

import android.support.annotation.NonNull;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Progress;
import com.vector.update_app.HttpManager;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.model.UpdateModel;


import java.io.File;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/1.
 */

public class UpdateAppHttpUtil implements HttpManager {

	private static final long serialVersionUID = -746296911702013167L;

	@Override
	public void asyncGet(@NonNull String url, @NonNull Map<String, String> params, @NonNull final Callback callBack) {

	}

	@Override
	public void asyncPost(@NonNull String url, @NonNull Map<String, String> params, @NonNull final Callback callBack) {
		OkGo.<LzyResponse<UpdateModel>>post(url).params(params).execute(new JsonCallback<LzyResponse<UpdateModel>>() {
			@Override
			public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<UpdateModel>> response) {
				callBack.onResponse(response.body().datas.toString());
			}

			@Override
			public void onError(com.lzy.okgo.model.Response<LzyResponse<UpdateModel>> response) {
				super.onError(response);
				callBack.onError("异常");
			}
		});
	}

	@Override
	public void download(@NonNull String url, @NonNull String path, @NonNull String fileName, @NonNull final FileCallback callback) {
		OkGo.<File>get(url).execute(new com.lzy.okgo.callback.FileCallback(path, fileName) {
			@Override
			public void onSuccess(com.lzy.okgo.model.Response<File> response) {
				callback.onResponse(response.body());
			}

			@Override
			public void onStart(com.lzy.okgo.request.base.Request<File, ? extends com.lzy.okgo.request.base.Request> request) {
				super.onStart(request);
				callback.onBefore();
			}

			@Override
			public void onError(com.lzy.okgo.model.Response<File> response) {
				super.onError(response);
				callback.onError("异常");
			}

			@Override
			public void downloadProgress(Progress progress) {
				super.downloadProgress(progress);

				callback.onProgress(progress.fraction, progress.totalSize);
			}
		});
	}
}
