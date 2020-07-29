package com.xinyuan.xyshop.mvp.contract;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xinyuan.xyshop.model.EvaluateModel;

/**
 * Created by fx on 2017/8/11.
 */

public interface CommentContentView {
	LifecycleTransformer<EvaluateModel> bindLife();

	void showList(EvaluateModel evaModel);
}
