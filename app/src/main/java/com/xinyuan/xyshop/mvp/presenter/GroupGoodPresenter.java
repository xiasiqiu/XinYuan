package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.GoodListModel;
import com.xinyuan.xyshop.mvp.contract.GroupGoodView;

/**
 * Created by fx on 2017/9/2.
 */

public class GroupGoodPresenter extends BasePresenter<GroupGoodView> {
	private Activity mActivity;

	public GroupGoodPresenter(Activity activity, GroupGoodView view) {
		super(view);
		this.mActivity = activity;
	}

	public void getGoodList(String type, int page, int limit) {

		OkGo.<LzyResponse<GoodListModel>>post(Urls.URL_GROUP_BUY)
				.tag(this)
				.params("currentPage", page)
				.params("pageSize", limit)
				.params("sorts", type)
				.execute(new JsonCallback<LzyResponse<GoodListModel>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<GoodListModel>> response) {
						if (HttpUtil.handleResponse(mActivity, response.body())) {
							mView.showGoodList(response.body().getDatas());
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<GoodListModel>> response) {
						HttpUtil.handleError(mActivity, response);
					}
				});
	}
}
