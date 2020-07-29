package com.xinyuan.xyshop.widget;

import java.util.List;

/**
 *
 * 日期：2017/6/27.
 */
public interface OnFlexboxSubscribeListener<T> {

    /**
     * @param selectedItem 已选中的标签
     */
    void onSubscribe(List<T> selectedItem);
}
