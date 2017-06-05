package com.xinyuan.xyshop.mvp.contract;

import android.view.View;

import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.base.BaseView;
import com.xinyuan.xyshop.entity.Brand;
import com.xinyuan.xyshop.entity.GoodCategory;
import com.xinyuan.xyshop.model.CategoryModel;

import java.util.List;

/**
 * Title:
 * Description:
 * Created by Administrator on 2017/5/10.
 * 作者：fx on 2017/5/10 22:02
 */

public class CategoryContract {
    public interface CategoryView extends BaseView<CategoryPresenter> {



        void OnImageViewClick(View view, final String type, final String data, boolean isAD);

        void showFrist(CategoryModel.CategoryData classItem, int m);
        void getData();



    }

    public interface CategoryPresenter extends BasePresenter<CategoryView> {

    }
}
