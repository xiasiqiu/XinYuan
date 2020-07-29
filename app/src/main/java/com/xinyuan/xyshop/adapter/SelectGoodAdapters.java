package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.GoodPropertyBean;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.ui.goods.search.SearchGoodSelectDialog;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by fx on 2017/7/17.
 * 商品搜索筛选框商品筛选列表Adapter
 */

public class SelectGoodAdapters extends BaseQuickAdapter<GoodPropertyBean, BaseViewHolder> {
    private LinearLayout ll_select;


    public static HashMap<String, List<String>> selectValue;

    public SelectGoodAdapters(@LayoutRes int layoutResId, @Nullable List<GoodPropertyBean> data) {
        super(layoutResId, data);
        selectValue = new HashMap<String, List<String>>();
        for (GoodPropertyBean specJsonVo : data) {
            List<String> list = new ArrayList<>();
            selectValue.put(specJsonVo.getGoodsPropertyName(), list);
        }

    }

    @Override
    protected void convert(BaseViewHolder helper, final GoodPropertyBean propertyBean) {
        ll_select = helper.getView(R.id.ll_select);
        if (propertyBean.getGoodsPropertyValue() != null && propertyBean.getGoodsPropertyValue().size() > 0) {
            boolean z;
            this.ll_select.removeAllViews();
            final AddViewHolder addViewHolder = new AddViewHolder(mContext, R.layout.view_select_dialog_sort_item);
            addViewHolder.setText(R.id.tvSortName, propertyBean.getGoodsPropertyName());
            RecyclerView rvSort = (RecyclerView) addViewHolder.getView(R.id.rvSort);
            rvSort.setLayoutManager(new GridLayoutManager(mContext, 3));
            final SelectValueAdapters selectValueAdapter = new SelectValueAdapters(R.layout.view_select_sort_recycler_item, propertyBean.getGoodsPropertyValue()) {
                @Override
                protected void convert(final BaseViewHolder helper, final String beanitem) {
                    final TextView btnSpec = helper.getView(R.id.btnSpec);
                    btnSpec.setText(beanitem);
                    btnSpec.setActivated(selectValue.get(propertyBean.getGoodsPropertyName()).contains(beanitem));
                    btnSpec.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            if (btnSpec.isActivated()) {
                                btnSpec.setActivated(false);
                                selectValue.get(propertyBean.getGoodsPropertyName()).remove(beanitem);
                            } else if (selectValue.get(propertyBean.getGoodsPropertyName()).size() < 3) {
                                btnSpec.setActivated(true);
                                selectValue.get(propertyBean.getGoodsPropertyName()).add(beanitem);
                            } else {
                                XToast.info(mContext.getString(R.string.text_notice_filter));
                            }
                        }
                    });
                }
            };
            selectValueAdapter.setNewData(propertyBean.getGoodsPropertyValue().subList(0, propertyBean.getGoodsPropertyValue().size() > 3 ? 3 : propertyBean.getGoodsPropertyValue().size()));
            rvSort.setAdapter(selectValueAdapter);
            selectValueAdapter.notifyDataSetChanged();
            if (propertyBean.getGoodsPropertyValue().size() > 3) {
                z = true;
            } else {
                z = false;
            }
            addViewHolder.setVisible(R.id.ivMoreFlag, z);
            addViewHolder.setSelected(R.id.ivMoreFlag, false);
            addViewHolder.setOnClickListener(R.id.ivMoreFlag, new View.OnClickListener() {
                public void onClick(View v) {
                    if (selectValueAdapter.getData().size() == 3) {
                        addViewHolder.setSelected(R.id.ivMoreFlag, true);
                        selectValueAdapter.setNewData(propertyBean.getGoodsPropertyValue());
                        selectValueAdapter.notifyDataSetChanged();
                        return;
                    }
                    addViewHolder.setSelected(R.id.ivMoreFlag, false);
                    selectValueAdapter.setNewData(propertyBean.getGoodsPropertyValue().subList(0, 3));
                    selectValueAdapter.notifyDataSetChanged();
                }
            });
            this.ll_select.addView(addViewHolder.getCustomView());
        }

    }


    public static HashMap<String, List<String>> getSelectValue() {
        return selectValue;
    }

    public static void setSelectValue(HashMap<String, List<String>> selectValue) {
        SelectGoodAdapters.selectValue = selectValue;
    }


}
