package com.xinyuan.xyshop.mvp.contract;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xinyuan.xyshop.bean.CouponBean;
import com.xinyuan.xyshop.bean.GoodListItemBean;
import com.xinyuan.xyshop.bean.StoreBean;
import com.xinyuan.xyshop.bean.StoreCouponBean;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.bean.StoreInfoBean;
import com.xinyuan.xyshop.model.StoreHomeModel;

import java.util.List;

/**
 * Created by fx on 2017/8/16.
 */

public interface StoreHomeView {
	void showState(int state);

	void showStoreInfo(StoreBean storeInfoBean);

	void showColl(ItemData ad, List<StoreHomeModel.CollGood> collGoodList, List<StoreHomeModel.CollGood> favGoodList);

	void showRecom(List<GoodListItemBean> recomList);

	void showCoupon(List<StoreCouponBean> couponBeanList);

	LifecycleTransformer<StoreHomeModel> bindLife();

}
