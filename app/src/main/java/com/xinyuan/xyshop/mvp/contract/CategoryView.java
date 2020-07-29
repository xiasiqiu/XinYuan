package com.xinyuan.xyshop.mvp.contract;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.model.CategoryModel;
import com.xinyuan.xyshop.model.ChatDetailModule;

import java.util.List;

/**
 * Title:
 * Description:
 * Created by fx on 2017/5/10.
 * 作者：fx on 2017/5/10 22:02
 */

public interface CategoryView {

	LifecycleTransformer<CategoryModel> bindLife();

	void setItemClick(final AddViewHolder holder, final CategoryModel.CategoryData classItem, final int m);

	void showFrist(CategoryModel.CategoryData classItem, int m);

	void showState(int Sate);

	void cleanData(List<CategoryModel.CategoryData> goodsCategoryList);

}
