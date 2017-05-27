package com.xinyuan.xyshop.adapter;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.HomeMultipleItem;
import com.xinyuan.xyshop.entity.ShopCarMultipleItem;
import com.xinyuan.xyshop.entity.TestStore;
import com.youth.xframe.utils.log.XLog;

import java.util.List;

/**
 * Created by Administrator on 2017/5/27.
 */

public class ShopCarMultipeAdapter extends BaseMultiItemQuickAdapter<ShopCarMultipleItem, BaseViewHolder> {
	private Context context;
	private List<TestStore> list;

	public ShopCarMultipeAdapter(Context context, List<ShopCarMultipleItem> data) {
		super(data);
		addItemType(ShopCarMultipleItem.STORE_TITLE, R.layout.shopcar_item_store_title);
		addItemType(ShopCarMultipleItem.GOODS_ITEM, R.layout.shopcar_item_good);
		addItemType(ShopCarMultipleItem.RECOME_TITLE, R.layout.shopcar_item_recome_title);
		addItemType(ShopCarMultipleItem.RECOME_GOOD, R.layout.home_item_goods);
		this.context = context;

	}

	@Override
	protected void convert(final BaseViewHolder helper, ShopCarMultipleItem item) {


		switch (item.getItemType()) {

			case ShopCarMultipleItem.STORE_TITLE:
				TextView tv_store_name = helper.getView(R.id.tv_store_name);
				tv_store_name.setText("鑫园共享");
				CheckBox checkBox = helper.getView(R.id.store_checkbox);

				checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
						XLog.v("店铺点击了?" + b + helper.getPosition());
					}
				});
				break;
			case ShopCarMultipleItem.GOODS_ITEM:
				CheckBox good_checkbox = helper.getView(R.id.good_checkbox);
				good_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
						XLog.v("商品点击了?" + b + helper.getPosition());
					}
				});
				break;
			case ShopCarMultipleItem.RECOME_TITLE:
				break;
			case ShopCarMultipleItem.RECOME_GOOD:
				break;

		}


	}
}
