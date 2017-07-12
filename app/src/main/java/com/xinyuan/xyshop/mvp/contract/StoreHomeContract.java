package com.xinyuan.xyshop.mvp.contract;

import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.base.BaseView;
import com.xinyuan.xyshop.entity.CouponBean;
import com.xinyuan.xyshop.entity.GoodListItem;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.entity.StoreInfoBean;
import com.xinyuan.xyshop.model.StoreHomeModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */

public class StoreHomeContract {
	public interface StoreHomeView extends BaseView<StoreHomeContract.StoreHomePresenter> {

		void showStoreInfo(StoreInfoBean storeInfoBean);

		void showColl(ItemData ad, List<StoreHomeModel.CollGood> collGoodList, List<StoreHomeModel.CollGood> favGoodList);

		void showRecom(List<GoodListItem> recomList);

		void showCoupon(List<CouponBean> couponBeanList);
	}

	public interface StoreHomePresenter extends BasePresenter<StoreHomeContract.StoreHomeView> {
		void initData(int storeId);
	}
}
