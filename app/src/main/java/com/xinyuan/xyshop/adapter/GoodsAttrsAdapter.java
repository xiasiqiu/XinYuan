package com.xinyuan.xyshop.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.AttributesBean;
import com.xinyuan.xyshop.callback.SKUInterface;
import com.xinyuan.xyshop.model.GoodsAttrsBean;
import com.xinyuan.xyshop.widget.SKUViewGroup;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fx on 2017/6/16.
 * 商品规格参数列表Adapter
 */

public class GoodsAttrsAdapter extends BaseRecyclerAdapter<AttributesBean> {

	private SKUInterface myInterface;

	private SimpleArrayMap<Integer, String> saveClick;

	private List<GoodsAttrsBean.StockGoodsBean> stockGoodsList;//商品数据集合
	private static String[] selectedValue;   //选中的属性
	private TextView[][] childrenViews;    //二维 装所有属性
	private GoodsAttrsBean.StockGoodsBean[][] childrenGoods;
	private final int SELECTED = 0x100;
	private final int CANCEL = 0x101;
	private static GoodsAttrsBean.StockGoodsBean selecGood;


	private List<AttributesBean> list;

	public GoodsAttrsAdapter(Context ctx, List<AttributesBean> list, List<GoodsAttrsBean.StockGoodsBean> stockGoodsList, GoodsAttrsBean.StockGoodsBean selecGood) {
		super(ctx, list);
		this.stockGoodsList = stockGoodsList;
		saveClick = new SimpleArrayMap<>();
		childrenViews = new TextView[list.size()][0];
		childrenGoods = new GoodsAttrsBean.StockGoodsBean[list.size()][0];
		selectedValue = new String[list.size()];
		for (int i = 0; i < selecGood.getGoodsInfo().size(); i++) {
			selectedValue[i] = selecGood.getGoodsInfo().get(i).getGspValue();
		}

	}




	public void setSKUInterface(SKUInterface myInterface) {
		this.myInterface = myInterface;
	}

	@Override
	public int getItemLayoutId(int viewType) {
		return R.layout.item_skuattrs;
	}

	@Override
	public void bindData(RecyclerViewHolder holder, int position, AttributesBean item) {

		TextView tv_ItemName = holder.getTextView(R.id.tv_ItemName);
		SKUViewGroup vg_skuItem = (SKUViewGroup) holder.getView(R.id.vg_skuItem);
		tv_ItemName.setText(item.getTabName());


		List<String> childrens = new ArrayList<>();

		for (AttributesBean.AttributeBean attributeBean : item.getAttributesItem()) {

			childrens.add(attributeBean.getValueName());

		}


		int childrenSize = childrens.size();
		TextView[] textViews = new TextView[childrenSize];

		for (int i = 0; i < childrenSize; i++) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			params.setMargins(0, 20, 20, 0);
			TextView textView = new TextView(mContext);
			textView.setGravity(Gravity.CENTER);
			textView.setPadding(10, 0, 10, 0);
			textView.setTextSize(12);
			textView.setLayoutParams(params);
			textView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.spec_btn_select_false));
			textView.setText(childrens.get(i));
			textView.setTextColor(ContextCompat.getColor(mContext, R.color.tv_name));
			textViews[i] = textView;
			vg_skuItem.addView(textViews[i]);
		}
		childrenViews[position] = textViews;


		initOptions();
		canClickOptions();
		getSelected();
	}

	private int focusPositionG, focusPositionC;

	private class MyOnClickListener implements View.OnClickListener {
		//点击操作 选中SELECTED   取消CANCEL
		private int operation;

		private int positionG;

		private int positionC;

		public MyOnClickListener(int operation, int positionG, int positionC) {
			this.operation = operation;
			this.positionG = positionG;
			this.positionC = positionC;
		}

		@Override
		public void onClick(View v) {
			focusPositionG = positionG;
			focusPositionC = positionC;
			String value = childrenViews[positionG][positionC].getText().toString();
			switch (operation) {
				case SELECTED:
					saveClick.put(positionG, positionC + "");
					selectedValue[positionG] = value;

					myInterface.selectedAttribute(selectedValue);
					break;
				case CANCEL:
					saveClick.put(positionG, "");
					for (int l = 0; l < selectedValue.length; l++) {
						if (selectedValue[l].equals(value)) {
							selectedValue[l] = "";
							break;
						}
					}
					myInterface.uncheckAttribute(selectedValue);
					break;
			}
			initOptions();
			canClickOptions();
			getSelected();
		}
	}


	class MyOnFocusChangeListener implements View.OnFocusChangeListener {

		private int positionG;

		private int positionC;


		public MyOnFocusChangeListener(int positionG, int positionC) {
			this.positionG = positionG;
			this.positionC = positionC;
		}

		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			String clickpositionC = saveClick.get(positionG);
			if (hasFocus) {
				v.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorCenterBtn));
				if (TextUtils.isEmpty(clickpositionC)) {
					((TextView) v).setTextColor(ContextCompat.getColor(mContext, R.color.dodgerblue));
				} else if (clickpositionC.equals(positionC + "")) {

				} else {
					((TextView) v).setTextColor(ContextCompat.getColor(mContext, R.color.dodgerblue));
				}
			} else {
				v.setBackgroundColor(ContextCompat.getColor(mContext, R.color.saddlebrown));
				if (TextUtils.isEmpty(clickpositionC)) {
					((TextView) v).setTextColor(ContextCompat.getColor(mContext, R.color.bg_white));
				} else if (clickpositionC.equals(positionC + "")) {

				} else {
					((TextView) v).setTextColor(ContextCompat.getColor(mContext, R.color.bg_white));
				}
			}
		}

	}

	/**
	 * 初始化选项（不可点击，焦点消失）
	 */
	private void initOptions() {


		for (int y = 0; y < childrenViews.length; y++) {


			for (int z = 0; z < childrenViews[y].length; z++) {//循环所有属性

				TextView textView = childrenViews[y][z];
				textView.setEnabled(false);
				textView.setFocusable(false);
				textView.setTextColor(ContextCompat.getColor(mContext, R.color.flg_hint));//变灰
			}
		}
	}

	/**
	 * 找到符合条件的选项变为可选
	 */

	private void canClickOptions() {

		for (int i = 0; i < childrenViews.length; i++) {
			for (int j = 0; j < stockGoodsList.size(); j++) {
				boolean filter = false;
				List<GoodsAttrsBean.GoodsInfoBean> goodsInfo = stockGoodsList.get(j).getGoodsInfo();
				for (int k = 0; k < selectedValue.length; k++) {
					if (i == k || TextUtils.isEmpty(selectedValue[k])) {
						continue;
					}
					if (!selectedValue[k].equals(goodsInfo
							.get(k).getGspValue())) {
						filter = true;
						break;
					}
				}

				if (!filter) {
					for (int n = 0; n < childrenViews[i].length; n++) {
						TextView textView = childrenViews[i][n];//拿到所有属性TextView

						String name = textView.getText().toString();

						//拿到属性名称



						if (goodsInfo.get(i).getGspValue().equals(name)) {
							textView.setEnabled(true);//符合就变成可点击
							textView.setFocusable(true); //设置可以获取焦点
							//不要让焦点乱跑
							if (focusPositionG == i && focusPositionC == n) {
								textView.setTextColor(ContextCompat.getColor(mContext, R.color.tv_name));
								textView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.spec_btn_select_false));
								textView.requestFocus();
							} else {
								textView.setTextColor(ContextCompat.getColor(mContext, R.color.tv_name));
								textView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.spec_btn_select_false));
							}
							textView.setOnClickListener(new MyOnClickListener(SELECTED, i, n) {
							});
							textView.setOnFocusChangeListener(new MyOnFocusChangeListener(i, n) {
							});
						}
					}
				}
			}
		}
	}


	/**
	 * 找到已经选中的选项，让其变色
	 */
	private void getSelected() {
		String str = "";
		for (String s : selectedValue) {
			str += s + "-";
		}

		for (int i = 0; i < childrenViews.length; i++) {
			for (int j = 0; j < childrenViews[i].length; j++) {//拿到每行属性Item
				TextView textView = childrenViews[i][j];//拿到所有属性TextView
				String value = textView.getText().toString();
				for (int m = 0; m < selectedValue.length; m++) {
					if (selectedValue[m].equals(value)) {
						textView.setTextColor(ContextCompat.getColor(mContext, R.color.bg_white));
						textView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.spec_btn_select_true));
						textView.setOnClickListener(new MyOnClickListener(CANCEL, i, j) {
						});
					}
				}
			}
		}
	}
}
