package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.ExpressItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fx on 2017/6/5.
 * 物流列表Adapter
 */

public class LogisticAdapter extends BaseQuickAdapter<ExpressItemBean, BaseViewHolder> {
    private static boolean isFrist = true;
    private List<ExpressItemBean> list = new ArrayList<>();

    public LogisticAdapter(@LayoutRes int layoutResId, @Nullable List<ExpressItemBean> expressInfos) {
        super(layoutResId, expressInfos);
        this.list = expressInfos;

    }

    @Override
    protected void convert(BaseViewHolder helper, final ExpressItemBean item) {
        if (helper.getLayoutPosition() == 1) {
            ImageView imageView = helper.getView(R.id.iv_express_index);
            imageView.setBackground(mContext.getResources().getDrawable(R.drawable.ic_logistic_frist));
            isFrist = false;
        } else if (helper.getLayoutPosition() == list.size()) {
            ImageView imageView = helper.getView(R.id.iv_express_index);
            imageView.setBackground(mContext.getResources().getDrawable(R.drawable.ic_logistic_last));
        }

        TextView tv_log_time = helper.getView(R.id.tv_log_time);
        tv_log_time.setText(item.getAcceptTime());
        TextView tv_content = helper.getView(R.id.tv_express_detail);
        tv_content.setText(item.getAcceptStation());

    }


}
