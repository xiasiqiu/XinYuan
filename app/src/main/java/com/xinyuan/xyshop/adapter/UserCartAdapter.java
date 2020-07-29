package com.xinyuan.xyshop.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
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

import com.bumptech.glide.Glide;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.CartGoodBean;
import com.xinyuan.xyshop.bean.StoreInfoBean;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.common.ShopHelper;
import com.xinyuan.xyshop.entity.GoodsInfo;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;
import com.xinyuan.xyshop.ui.goods.store.StoreActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.widget.AddSubView;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.XToast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fx on 2017/7/4.
 * 购物车列表Adapter
 */

public class UserCartAdapter extends BaseExpandableListAdapter {

    private List<StoreInfoBean> groups;
    private Map<Long, List<CartGoodBean>> children;
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
    public UserCartAdapter(List<StoreInfoBean> groups, Map<Long, List<CartGoodBean>> children, Context context) {
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
        long groupId = groups.get(groupPosition).getStoreId();
        return children.get(groupId).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<CartGoodBean> childs = children.get(groups.get(groupPosition).getStoreId());
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
        final StoreInfoBean group = (StoreInfoBean) getGroup(groupPosition);

        gholder.bt_store_name.setText(group.getStoreName());
        gholder.bt_store_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> params = new HashMap();
                params.put(Constants.STOREID, String.valueOf(groups.get(groupPosition).getStoreId()));
                CommUtil.gotoActivity(mcontext, StoreActivity.class, false, params);
            }
        });
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
        final CartGoodBean goodsInfo = (CartGoodBean) getChild(groupPosition, childPosition);
        if (!XEmptyUtils.isEmpty(goodsInfo)) {
            cholder.tv_good_name.setText(goodsInfo.getGoodsName());  //商品名称
            cholder.tvPrice.setText(mcontext.getString(R.string.money_rmb) + ShopHelper.getPriceString(goodsInfo.getGoodsPrice()));//商品价格
            GlideImageLoader.setUrlImg(mcontext, goodsInfo.getGoodsImg(), cholder.iv_good_img);    ////商品图片
            //cholder.tvColorsize.setText("颜色：" + goodsInfo.getColor() + "," + "尺码：" + goodsInfo.getSize() + "瓶/斤");
//			String s = "[促销]满19：00得赠品，赠完为止";
//			SpannableString ss1 = new SpannableString(s);
//			ss1.setSpan(new ForegroundColorSpan(mcontext.getResources().getColor(R.color.tv_price)), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//			cholder.tv_good_active.setText(ss1);
            cholder.tv_good_spec.setText(goodsInfo.getGoodsSpecText());      //商品规格
            cholder.checkBox.setChecked(goodsInfo.isChoosed());      //商品选择
            cholder.checkBox.setOnClickListener(new View.OnClickListener() {    //选择监听
                @Override
                public void onClick(View v) {
                    goodsInfo.setChoosed(((CheckBox) v).isChecked());
                    cholder.checkBox.setChecked(((CheckBox) v).isChecked());
                    checkInterface.checkChild(groupPosition, childPosition, ((CheckBox) v).isChecked());// 暴露子选接口
                }
            });
            // TODO: 2017/9/2 购物车商品数据应加个库存数量
            //cholder.addSubView.setBuyMax(goodsInfo.get)
            cholder.addSubView.setStep(1)
                    .setPosition(childPosition)
                    .setInventory(50)
                    .setCurrentNumber(goodsInfo.getGoodsCount())
                    .setOnWarnListener(new AddSubView.OnWarnListener() {
                        @Override
                        public void onWarningForInventory(int inventory) {
                            XToast.info("当前库存" + inventory);

                        }

                        @Override
                        public void onWarningForBuyMax(int max) {
                            XToast.info("超过最大购买数" + max);

                        }

                        @Override
                        public void onWarningForBuyMin(int min) {
                            XToast.info("低于最低购买数" + min);
                        }

                        @Override
                        public void AddListenter(View inputValue) {
                            modifyCountInterface.doIncrease(groupPosition, childPosition, inputValue, cholder.checkBox.isChecked());// 暴露增加接口

                        }

                        @Override
                        public void ReduceListenter(View inputValue) {
                            modifyCountInterface.doDecrease(groupPosition, childPosition, inputValue, cholder.checkBox.isChecked());// 暴露删减接口


                        }
                    })
                    .setOnChangeValueListener(new AddSubView.OnChangeValueListener() {
                        @Override
                        public void onChangeValue(int value, int position) {

                        }
                    });


            cholder.rl_good_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Map<String, String> params = new HashMap();
                    params.put(Constants.GOODID, String.valueOf(goodsInfo.getGoodsId()));
                    params.put(Constants.GOODTYPE, "1");
                    CommUtil.gotoActivity(mcontext, GoodDetailActivity.class, false, params);
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
        private StoreInfoBean group;

        public GroupViewClick(int groupPosition, Button edtor, StoreInfoBean group) {
            this.groupPosition = groupPosition;
            this.edtor = edtor;
            this.group = group;
        }

        @Override
        public void onClick(View v) {
            int groupId = v.getId();
            if (groupId == edtor.getId()) {
                if (group.isEdtor()) {
                    group.setEdtor(false);
                } else {
                    group.setEdtor(true);

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
        @BindView(R.id.bt_store_name)
        TextView bt_store_name;

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
        @BindView(R.id.iv_good_img)
        ImageView iv_good_img;
        @BindView(R.id.tv_good_name)
        TextView tv_good_name;
        @BindView(R.id.tv_good_active)
        TextView tv_good_active;
        @BindView(R.id.tv_good_spec)
        TextView tv_good_spec;
        @BindView(R.id.rl_good_info)
        RelativeLayout rl_good_info;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.addSubView)
        AddSubView addSubView;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }


}

