package com.xinyuan.xyshop.base;

/**
 * Created by fx on 2017/5/2 0002.
 */
public interface BaseView <T extends BasePresenter>{


	void setPresenter(T presenter);
}

