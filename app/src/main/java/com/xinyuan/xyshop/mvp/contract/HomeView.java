package com.xinyuan.xyshop.mvp.contract;

import android.view.View;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xinyuan.xyshop.bean.GoodListItemBean;
import com.xinyuan.xyshop.entity.HomeMultipleItem;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.model.HomeModel;
import com.xinyuan.xyshop.model.KeyWordModel;

import java.util.List;

/**
 * Created by fx on 2017/7/31.
 */

public interface HomeView {
	void addHead(List<HomeMultipleItem> list, List<HomeModel.HomeModule> moduleList);

	void showState(int state);

	void showBanner(List<ItemData> itemList);

	void showMenu(List<ItemData> itemList);

	void showNotice(List<ItemData> itemList);

	void showGoods(List<GoodListItemBean> list);


	void OnImageViewClick(View view, ItemData itemData);

	void setKeyWord(KeyWordModel keyWords);
	void showSign(boolean status, String num);
	LifecycleTransformer<HomeModel> bindLife();


}
