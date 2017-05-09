package com.xinyuan.xyshop.mvp.contract;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.base.BaseView;

/**
 * Created by fx on 2017/5/2 0002.
 */

public class MainContract {
	public interface IMainView extends BaseView<IMainPresenter> {

		/**
		 * 获取显示内容的Viewpager
		 *
		 * @return viewpager 对象
		 */
		ViewPager getmActHomeVpContent();

		/**
		 * 获取当前activity的Fragment管理器
		 *
		 * @return Fragment管理器
		 */
		FragmentManager getManager();
	}

	public interface IMainPresenter extends BasePresenter<IMainView> {

	}
}
