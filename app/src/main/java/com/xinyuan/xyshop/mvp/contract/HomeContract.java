package com.xinyuan.xyshop.mvp.contract;

import android.view.View;

import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.base.BaseView;
import com.xinyuan.xyshop.entity.HomeMultipleItem;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.model.HomeModel;

import java.util.List;

/**
 * Created by fx on 2017/5/9 0009.
 */

public class HomeContract {

	public interface HomeView extends BaseView<HomePresenter> {

		void addHead(List<HomeMultipleItem> list);

		void showBanner(List<HomeModel.HomeModule.HomeModuleData> itemList);

		void showMenu(List<HomeModel.HomeModule.HomeModuleData> itemList);

		void showNotice(List<HomeModel.HomeModule.HomeModuleData> itemList);

		void showGoods(List<HomeModel.HomeGood> goodList);

		void showList();

		void OnImageViewClick(View view, final String type, final String data, boolean isAD);

		void setKeyWord(String keyWord, String showWord);

		void setSearchListener();
	}

	public interface HomePresenter extends BasePresenter<HomeView> {

		void getkeyWord();
	}
}
