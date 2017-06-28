package com.xinyuan.xyshop.ui.goods;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.SelectDialogAdapter;
import com.xinyuan.xyshop.bean.ExpandItem;
import com.xinyuan.xyshop.entity.Menu;
import com.xinyuan.xyshop.entity.SelectFilterTest;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/23.
 */

public class RightSearchPly extends RelativeLayout {

	@BindView(R.id.search_goods_filter)
	RecyclerView RV_filter;

	@BindView(R.id.btn_ok)
	Button btn_ok;
	@BindView(R.id.btn_cancel)
	Button btn_cancel;

	private static SelectFilterTest selectFilterTests;
	private List<SelectFilterTest.FilterKey> filterKeyList;

	private Context mCtx;

	public RightSearchPly(Context context, SelectFilterTest selectFilterTest) {
		super(context);
		mCtx = context;
		selectFilterTests = selectFilterTest;
		inflateView();
	}


	private void inflateView() {
		View.inflate(getContext(), R.layout.activity_search_good_select, this);
		ButterKnife.bind(this);
		if (selectFilterTests != null) {
			filterKeyList = selectFilterTests.getKeyList();
		}
		if (filterKeyList!= null) {
			int menucount = filterKeyList.size();
			ArrayList<MultiItemEntity> res = new ArrayList<>();
			for (SelectFilterTest.FilterKey key : filterKeyList) {
				ExpandItem expandItem = new ExpandItem("", key.getCategoryName());
				for (SelectFilterTest.FilterKey.KeyItem item : key.getKeyitem()) {
					expandItem.addSubItem(new Menu("", item.getCategoryName()));
				}
				res.add(expandItem);
			}

			final SelectDialogAdapter selectDialogAdapter = new SelectDialogAdapter(res, filterKeyList);
			final GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
			manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
				@Override
				public int getSpanSize(int position) {
					return selectDialogAdapter.getItemViewType(position) == SelectDialogAdapter.TYPE_ITEM ? 1 : manager.getSpanCount();
				}
			});
			RV_filter.setAdapter(selectDialogAdapter);
			selectDialogAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
			RV_filter.setLayoutManager(manager);
			selectDialogAdapter.expandAll();
		}


	}

	private PopupWindow mMenuPop;

	private void dismissMenuPop() {
		if (mMenuPop != null) {
			mMenuPop.dismiss();
			mMenuPop = null;
		}

	}

	private CloseMenuCallBack menuCallBack;

	public interface CloseMenuCallBack {
		void setupCloseMean();
	}

	public void setCloseMenuCallBack(CloseMenuCallBack menuCallBack) {
		this.menuCallBack = menuCallBack;
	}

	@OnClick({R.id.btn_cancel, R.id.btn_ok})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btn_ok:
				XLog.list(SelectDialogAdapter.getKeyList());
				break;
			case R.id.btn_cancel:
				inflateView();
				break;
		}
	}
}
