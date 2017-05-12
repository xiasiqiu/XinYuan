package com.xinyuan.xyshop.mvp.contract;

import android.view.View;

import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.base.BaseView;
import com.xinyuan.xyshop.entity.HomeMultipleItem;
import com.xinyuan.xyshop.entity.ItemData;

import java.util.List;

/**
 * Created by fx on 2017/5/9 0009.
 */

public class HomeContract {

	public interface HomeView extends BaseView<HomePresenter> {

		void addHead(List<HomeMultipleItem> list);

		void showBanner(List<ItemData> itemList);

		void showMenu(List<ItemData> itemList);

		void showNotice(List<ItemData> itemList);

		void showList();

		void OnImageViewClick(View view, final String type, final String data, boolean isAD);

	}

	public interface HomePresenter extends BasePresenter<HomeView> {



	}
}
