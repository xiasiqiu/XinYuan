package com.xinyuan.xyshop.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.model.CategoryModel.CategoryData;
import com.xinyuan.xyshop.ui.goods.search.SearchGoodShowActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.ViewHolder;
import com.youth.xframe.utils.XEmptyUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Title:
 * Description:
 * Created by Administrator on 2017/5/14.
 * 作者：fx on 2017/5/14 22:46
 * 商品分类列表Adapter
 */

public class GoodsCategoryAdapter extends CommonAdapter<CategoryData> {
    public GoodsCategoryAdapter(Context context) {
        super(context, R.layout.fragment_category_item_gridview);
    }

    public void convert(ViewHolder holder, final CategoryData goodsCategory) {
        holder.setText(R.id.tvGoodsClassName, goodsCategory.getCategoryName());
        Gson bean = new Gson();
        ItemData adBean = bean.fromJson(goodsCategory.getAd(), ItemData.class);
        ImageView imageView = holder.getView(R.id.tvGoodsClassImage);
        if (!XEmptyUtils.isEmpty(goodsCategory.getCategoryImageUrl())) {
            GlideImageLoader.setUrlImg(mContext, goodsCategory.getCategoryImageUrl(), imageView);
        } else {
            GlideImageLoader.setUrlImg(mContext, "", imageView);

        }
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Map<String, String> params = new HashMap();
                params.put(Constants.KEYWORD, goodsCategory.getCategoryName());
                CommUtil.gotoActivity(mContext, SearchGoodShowActivity.class, false, params);
            }
        });
    }
}
