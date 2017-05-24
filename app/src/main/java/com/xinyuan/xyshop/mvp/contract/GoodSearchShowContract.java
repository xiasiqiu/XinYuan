package com.xinyuan.xyshop.mvp.contract;

import android.view.View;

import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.base.BaseView;
import com.xinyuan.xyshop.entity.GoodCategory;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.entity.SelectFilter;
import com.xinyuan.xyshop.entity.SelectFilterTest;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */

public class GoodSearchShowContract {

	public interface GoodSearchShowView extends BaseView<GoodSearchShowContract.GoodSearchShowPresenter> {


		void showGoodList(List<GoodsVo> goodses, SelectFilterTest selectFilterTest);




	}

	public interface GoodSearchShowPresenter extends BasePresenter<GoodSearchShowContract.GoodSearchShowView> {

		void initData(String keyword, int brandId, int cat, String sort, String selectValue, int page);
	}
}
