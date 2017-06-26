package com.xinyuan.xyshop.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.GoodsInfo;
import com.xinyuan.xyshop.entity.ShopCarGoodsItem;
import com.xinyuan.xyshop.entity.ShopCarStoreItem;
import com.xinyuan.xyshop.entity.StoreInfo;
import com.youth.xframe.utils.log.XLog;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/31.
 */

public class ShopCarAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
	public static final int TYPE_LEVEL_0 = 0;
	public static final int TYPE_LEVEL_1 = 1;

	private List<StoreInfo> groups;
	private Map<String, List<GoodsInfo>> children;
	private CheckInterface checkInterface;
	private ModifyCountInterface modifyCountInterface;
	private GroupEdtorListener mListener;

	public ShopCarAdapter(List<StoreInfo> groups, Map<String, List<GoodsInfo>> children, List<MultiItemEntity> data) {
		super(data);
		this.groups = groups;
		this.children = children;
		addItemType(TYPE_LEVEL_0, R.layout.fragment_shopcar_item_store_title);
		addItemType(TYPE_LEVEL_1, R.layout.fragment_shopcar_item_good);
	}

	public void setmListener(GroupEdtorListener mListener) {
		this.mListener = mListener;
	}

	public void setCheckInterface(CheckInterface checkInterface) {
		this.checkInterface = checkInterface;
	}

	public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
		this.modifyCountInterface = modifyCountInterface;
	}


	@Override
	protected void convert(BaseViewHolder helper, final MultiItemEntity item) {
		switch (item.getItemType()) {
			case TYPE_LEVEL_0:


				final ShopCarStoreItem shopCarStoreItem = (ShopCarStoreItem) item;
				XLog.v("店铺位置" + shopCarStoreItem.getPosition());


				CheckBox checkBox = helper.getView(R.id.store_checkbox);
				final StoreInfo group = (StoreInfo) getGroup(shopCarStoreItem.getPosition());
				checkBox.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						group.setChoosed(((CheckBox) view).isChecked());
						checkInterface.checkGroup(shopCarStoreItem.getPosition(), ((CheckBox) view).isChecked());// 暴露组选接口
					}
				});
				checkBox.setChecked(group.isChoosed);
				// notifyDataSetChanged();


				break;
			case TYPE_LEVEL_1:

				final ShopCarGoodsItem shopCarGoodsItem = (ShopCarGoodsItem) item;


				final int groupPosition = shopCarGoodsItem.getStorePosition();
				final int childPosition = shopCarGoodsItem.getGoddsPostion();
				final GoodsInfo goodsInfo = (GoodsInfo) getChild(shopCarGoodsItem.getStorePosition(), shopCarGoodsItem.getGoddsPostion());
				if (goodsInfo != null) {

					final CheckBox goodCheckBox = helper.getView(R.id.good_checkbox);
					goodCheckBox.setChecked(goodsInfo.isChoosed());
					goodCheckBox.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							goodsInfo.setChoosed(((CheckBox) v).isChecked());
							goodCheckBox.setChecked(((CheckBox) v).isChecked());
							checkInterface.checkChild(shopCarGoodsItem.getStorePosition(), shopCarGoodsItem.getGoddsPostion(), ((CheckBox) v).isChecked());// 暴露子选接口
						}
					});
					final TextView etNum = helper.getView(R.id.tvAppCommonCount);
					Button btReduce = helper.getView(R.id.btnAppCommonMinus);
					btReduce.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							modifyCountInterface.doDecrease(groupPosition, childPosition, etNum, goodCheckBox.isChecked());// 暴露删减接口
						}
					});
					Button btAdd = helper.getView(R.id.btnAppCommonAdd);
					btAdd.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							modifyCountInterface.doIncrease(groupPosition, childPosition, etNum, goodCheckBox.isChecked());// 暴露增加接口
						}
					});
					etNum.setText(""+goodsInfo.getCount());
//					etNum.setOnFocusChangeListener(new android.view.View.
//							OnFocusChangeListener() {
//						@Override
//						public void onFocusChange(View v, boolean hasFocus) {//监听焦点的变化
//							if (hasFocus) {//获取到焦点也就是文本框被点击修改了
//								// 1，先强制键盘不弹出
//								InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
//								imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
//								// 2.显示弹出dialog进行修改
//								showDialog(goodsInfo, etNum);
//								// 3.清除焦点防止不断弹出dialog和软键盘
//								etNum.clearFocus();
//								//4. 数据刷新
//								notifyDataSetChanged();
//							}
//						}
//					});


				}

				break;

		}
	}

	public Object getChild(int groupPosition, int childPosition) {
		List<GoodsInfo> childs = children.get(groups.get(groupPosition).getId());
		return childs.get(childPosition);
	}

	public Object getGroup(int groupPosition) {
		return groups.get(groupPosition);
	}

	/**
	 * 复选框接口
	 */
	public interface CheckInterface {
		/**
		 * 组选框状态改变触发的事件
		 *
		 * @param groupPosition 组元素位置
		 * @param isChecked     组元素选中与否
		 */
		void checkGroup(int groupPosition, boolean isChecked);

		/**
		 * 子选框状态改变时触发的事件
		 *
		 * @param groupPosition 组元素位置
		 * @param childPosition 子元素位置
		 * @param isChecked     子元素选中与否
		 */
		void checkChild(int groupPosition, int childPosition, boolean isChecked);
	}

	/**
	 * 改变数量的接口
	 */
	public interface ModifyCountInterface {
		/**
		 * 增加操作
		 *
		 * @param groupPosition 组元素位置
		 * @param childPosition 子元素位置
		 * @param showCountView 用于展示变化后数量的View
		 * @param isChecked     子元素选中与否
		 */
		void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);

		/**
		 * 删减操作
		 *
		 * @param groupPosition 组元素位置
		 * @param childPosition 子元素位置
		 * @param showCountView 用于展示变化后数量的View
		 * @param isChecked     子元素选中与否
		 */
		void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);

		/**
		 * 删除子item
		 *
		 * @param groupPosition
		 * @param childPosition
		 */
		void childDelete(int groupPosition, int childPosition);
	}

	/**
	 * 监听编辑状态
	 */
	public interface GroupEdtorListener {
		void groupEdit(int groupPosition);
	}

	/**
	 * 使某个组处于编辑状态
	 * <p/>
	 * groupPosition组的位置
	 */
	class GroupViewClick implements View.OnClickListener {
		private int groupPosition;
		private Button edtor;
		private StoreInfo group;

		public GroupViewClick(int groupPosition, Button edtor, StoreInfo group) {
			this.groupPosition = groupPosition;
			this.edtor = edtor;
			this.group = group;
		}

		@Override
		public void onClick(View v) {
			int groupId = v.getId();
			if (groupId == edtor.getId()) {
				if (group.getIsEdtor()) {
					group.setIsEdtor(false);
				} else {
					group.setIsEdtor(true);

				}
				notifyDataSetChanged();
			}
		}
	}


	int count = 0;

	/**
	 * 显示修改购物车商品数量的dialog
	 *
	 * @param goodinfo item的商品信息实体
	 * @param edittext 购物车item的数量文本框
	 */
	private void showDialog(final GoodsInfo goodinfo, final EditText edittext) {
		final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
		View alertDialogView = LayoutInflater.from(mContext).inflate(R.layout.view_dialog_change_num, null, false);
		final AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.setView(alertDialogView);
		count = goodinfo.getCount();
		final EditText editText = (EditText) alertDialogView.findViewById(R.id.et_num);
		editText.setText("" + goodinfo.getCount());//设置dialog的数量初始值
		//自动弹出软键盘
		alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
			public void onShow(DialogInterface dialog) {
				InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
				imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
			}
		});
		final Button btadd = (Button) alertDialogView.findViewById(R.id.bt_add);
		final Button btreduce = (Button) alertDialogView.findViewById(R.id.bt_reduce);
		final TextView cancle = (TextView) alertDialogView.findViewById(R.id.tv_cancle);
		final TextView sure = (TextView) alertDialogView.findViewById(R.id.tv_sure);
		cancle.setOnClickListener(new View.OnClickListener() { //取消按钮
			@Override
			public void onClick(View v) {
				alertDialog.dismiss();
			}
		});
		sure.setOnClickListener(new View.OnClickListener() {//确定按钮
			@Override
			public void onClick(View v) {
				goodinfo.setCount(count);//重新设置数量
				edittext.setText(count + "");//购物车界面的文本框显示同步
				alertDialog.dismiss();
			}
		});
		btadd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				count++;   //点一下量加1
				editText.setText("" + count);//动态显示dialog的文本框的数据

			}
		});
		btreduce.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (count > 1) {//数量大雨1时操作
					count--; //点一下减1
					editText.setText("" + count);
				}
			}
		});
		alertDialog.show();
	}
}
