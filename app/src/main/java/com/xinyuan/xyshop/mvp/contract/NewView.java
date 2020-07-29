package com.xinyuan.xyshop.mvp.contract;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xinyuan.xyshop.model.NewsModel;

import java.util.List;

/**
 * Created by fx on 2017/8/29.
 */

public interface NewView {
	void showList(List<NewsModel> list);

	void showState(int state);

	LifecycleTransformer<List<NewsModel>> bindLife();


}
