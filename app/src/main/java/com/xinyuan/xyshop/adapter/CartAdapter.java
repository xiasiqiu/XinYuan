package com.xinyuan.xyshop.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.GoodsInfo;
import com.xinyuan.xyshop.entity.StoreInfo;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/4.
 */

public class CartAdapter extends BaseExpandableListAdapter {

	private List<StoreInfo> groups;
	private Map<String, List<GoodsInfo>> children;
	private Context mcontext;
	private CheckInterface checkInterface;
	private ModifyCountInterface modifyCountInterface;
	public int flag = 0;
	private GroupEdtorListener mListener;

	public GroupEdtorListener getmListener() {
		return mListener;
	}

	public void setmListener(GroupEdtorListener mListener) {
		this.mListener = mListener;
	}

	int count = 0;

	/**
	 * 构造函数
	 *
	 * @param groups   组元素列表
	 * @param children 子元素列表
	 * @param context
	 */
	public CartAdapter(List<StoreInfo> groups, Map<String, List<GoodsInfo>> children, Context context) {
		this.groups = groups;
		this.children = children;
		this.mcontext = context;
	}

	public void setCheckInterface(CheckInterface checkInterface) {
		this.checkInterface = checkInterface;
	}

	public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
		this.modifyCountInterface = modifyCountInterface;
	}

	@Override
	public int getGroupCount() {
		return groups.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		String groupId = groups.get(groupPosition).getId();
		return children.get(groupId).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groups.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		List<GoodsInfo> childs = children.get(groups.get(groupPosition).getId());
		return childs.get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

		final GroupViewHolder gholder;
		if (convertView == null) {
			convertView = View.inflate(mcontext, R.layout.fragment_cart_item_group, null);
			gholder = new GroupViewHolder(convertView);
			convertView.setTag(gholder);
		} else {
			gholder = (GroupViewHolder) convertView.getTag();
		}
		final StoreInfo group = (StoreInfo) getGroup(groupPosition);

		gholder.tvSourceName.setText(group.getName());
		gholder.determineChekbox.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)

			{
				group.setChoosed(((CheckBox) v).isChecked());
				checkInterface.checkGroup(groupPosition, ((CheckBox) v).isChecked());// 暴露组选接口
			}
		});
		gholder.determineChekbox.setChecked(group.isChoosed());
		notifyDataSetChanged();
		return convertView;
	}

	@Override
	public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild, View convertView, final ViewGroup parent) {

		final ChildViewHolder cholder;
		if (convertView == null) {
			convertView = View.inflate(mcontext, R.layout.fragment_cart_item_product, null);
//            if(isLastChild&&getChild(groupPosition,childPosition)!=null)
//            {
//                View    v = View.inflate(context, R.layout.child_footer,null);
//                TextView txtFooter = (TextView)v.findViewById(R.id.txtFooter);
//                txtFooter.setText("店铺满99元包邮");
//                if(convertView instanceof ViewGroup){
//                    ((ViewGroup) convertView).addView(v);
//                }
//            }

			cholder = new ChildViewHolder(convertView);
			convertView.setTag(cholder);
		} else {
			cholder = (ChildViewHolder) convertView.getTag();
		}
		final GoodsInfo goodsInfo = (GoodsInfo) getChild(groupPosition, childPosition);
		if (goodsInfo != null) {


			cholder.tvIntro.setText(goodsInfo.getDesc());
			cholder.tvPrice.setText("￥" + goodsInfo.getPrice() + "");
			cholder.etNum.setText(goodsInfo.getCount() + "");
			cholder.ivAdapterListPic.setImageResource(goodsInfo.getGoodsImg());
			//cholder.tvColorsize.setText("颜色：" + goodsInfo.getColor() + "," + "尺码：" + goodsInfo.getSize() + "瓶/斤");
			SpannableString spanString = new SpannableString("￥" + String.valueOf(goodsInfo.getDiscountPrice()));
			StrikethroughSpan span = new StrikethroughSpan();
			spanString.setSpan(span, 0, String.valueOf(goodsInfo.getDiscountPrice()).length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			cholder.checkBox.setChecked(goodsInfo.isChoosed());
			cholder.checkBox.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					goodsInfo.setChoosed(((CheckBox) v).isChecked());
					cholder.checkBox.setChecked(((CheckBox) v).isChecked());
					checkInterface.checkChild(groupPosition, childPosition, ((CheckBox) v).isChecked());// 暴露子选接口
				}
			});
			cholder.btAdd.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					modifyCountInterface.doIncrease(groupPosition, childPosition, cholder.etNum, cholder.checkBox.isChecked());// 暴露增加接口
				}
			});
			cholder.btReduce.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					modifyCountInterface.doDecrease(groupPosition, childPosition, cholder.etNum, cholder.checkBox.isChecked());// 暴露删减接口
				}
			});
			notifyDataSetChanged();

		}

		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;

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
				if (group.isEdtor()) {
					group.setIsEdtor(false);
				} else {
					group.setIsEdtor(true);

				}
				notifyDataSetChanged();
			}
		}
	}

	/**
	 * 组元素绑定器
	 */
	static class GroupViewHolder {
		@BindView(R.id.determine_chekbox)
		CheckBox determineChekbox;
		@BindView(R.id.tv_source_name)
		TextView tvSourceName;

		GroupViewHolder(View view) {
			ButterKnife.bind(this, view);
		}
	}

	/**
	 * 子元素绑定器
	 */
	static class ChildViewHolder {
		@BindView(R.id.check_box)
		CheckBox checkBox;
		@BindView(R.id.iv_adapter_list_pic)
		ImageView ivAdapterListPic;
		@BindView(R.id.tv_intro)
		TextView tvIntro;
		@BindView(R.id.tv_color_size)
		TextView tvColorSize;
		@BindView(R.id.tv_price)
		TextView tvPrice;
		@BindView(R.id.rl_no_edtor)
		RelativeLayout rlNoEdtor;
		@BindView(R.id.bt_reduce)
		Button btReduce;
		@BindView(R.id.et_num)
		TextView etNum;
		@BindView(R.id.bt_add)
		Button btAdd;
		@BindView(R.id.ll_change_num)
		RelativeLayout llChangeNum;


		ChildViewHolder(View view) {
			ButterKnife.bind(this, view);
		}

	}

	/**
	 * 购物车的数量修改编辑框的内容监听
	 */
	class GoodsNumWatcher implements TextWatcher {
		GoodsInfo goodsInfo;

		public GoodsNumWatcher(GoodsInfo goodsInfo) {
			this.goodsInfo = goodsInfo;
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {

		}

		@Override
		public void afterTextChanged(Editable s) {
			if (!TextUtils.isEmpty(s.toString())) {//当输入的数字不为空时，更新数字
				goodsInfo.setCount(Integer.valueOf(s.toString().trim()));
			}
		}

	}

	/**
	 * 显示修改购物车商品数量的dialog
	 *
	 * @param goodinfo item的商品信息实体
	 * @param edittext 购物车item的数量文本框
	 */
	private void showDialog(final GoodsInfo goodinfo, final EditText edittext) {
		final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mcontext);
		View alertDialogView = LayoutInflater.from(mcontext).inflate(R.layout.view_dialog_change_num, null, false);
		final AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.setView(alertDialogView);
		count = goodinfo.getCount();
		final EditText editText = (EditText) alertDialogView.findViewById(R.id.et_num);
		editText.setText("" + goodinfo.getCount());//设置dialog的数量初始值
		//自动弹出软键盘
		alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
			public void onShow(DialogInterface dialog) {
				InputMethodManager imm = (InputMethodManager) mcontext.getSystemService(Context.INPUT_METHOD_SERVICE);
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

