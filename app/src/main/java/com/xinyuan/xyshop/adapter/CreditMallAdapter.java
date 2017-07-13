package com.xinyuan.xyshop.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.CreditMultipleItem;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.entity.HomeMultipleItem;
import com.xinyuan.xyshop.model.CreditModel;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailsActivity;
import com.xinyuan.xyshop.ui.home.CreditMallActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 */

public class CreditMallAdapter extends BaseMultiItemQuickAdapter<CreditMultipleItem, BaseViewHolder> {

	private List<CreditModel.CreditModule> moduleList;

	public CreditMallAdapter(List data, List<CreditModel.CreditModule> moduleList) {
		super(data);
		addItemType(CreditMultipleItem.AD, R.layout.activity_credit_mall_item_ad);
		addItemType(CreditMultipleItem.MODULE, R.layout.activity_credit_mall_item_module);
		this.moduleList = moduleList;
		XLog.list(moduleList);
	}

	@Override
	protected void convert(BaseViewHolder helper, CreditMultipleItem item) {
		switch (item.getItemType()) {
			case CreditMultipleItem.AD:
				ImageView iv_ad = helper.getView(R.id.iv_credit_ad);
				int index = helper.getLayoutPosition() / 2 - 1;
				XLog.v("AD-" + index);

				GlideImageLoader.setImage(mContext, moduleList.get(index).getAd().getImageUrl(), iv_ad);
				break;
			case CreditMultipleItem.MODULE:
				int Index = helper.getLayoutPosition() / 2;
				XLog.v("MODULE-" + Index);
				CreditModel.CreditModule module = moduleList.get(Index);
				TextView tv_title = helper.getView(R.id.tv_credit_module);
				tv_title.setText(module.getFloorTitle());
				RecyclerView rv_module = helper.getView(R.id.rv_credit_content);
				CreditGoodsAdapter adapter = new CreditGoodsAdapter(R.layout.item_good_grid, module.getGoodsList());
				adapter.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
						String Type = "1:2";
						HashMap<String, String> params;
						params = new HashMap();
						params.put("GoodType", Type);
						CommUtil.gotoActivity(mContext, GoodDetailsActivity.class, false, params);
					}
				});

				GridLayoutManager layoutManager2 = new GridLayoutManager(mContext, 2, 1, false);
				rv_module.setLayoutManager(layoutManager2);
				rv_module.setAdapter(adapter);

				break;
		}
	}
}
