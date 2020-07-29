package com.xinyuan.xyshop.mvp.contract;

import com.xinyuan.xyshop.bean.GoodListItemBean;

import java.util.List;

/**
 * Created by fx on 2017/8/31.
 */

public interface StoreGoodView {
	void showGoods(List<GoodListItemBean> list);
	void showState(int state);

}
