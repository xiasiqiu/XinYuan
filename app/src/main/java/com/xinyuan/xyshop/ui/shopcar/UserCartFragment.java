package com.xinyuan.xyshop.ui.shopcar;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xinyuan.xyshop.MainFragment;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.GoodsGridAdapter;
import com.xinyuan.xyshop.adapter.UserCartAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.bean.CartGoodBean;
import com.xinyuan.xyshop.bean.StoreInfoBean;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.common.ShopHelper;
import com.xinyuan.xyshop.common.state.LoginContext;
import com.xinyuan.xyshop.even.TabSelectedEvent;
import com.xinyuan.xyshop.model.RecomGoodModel;
import com.xinyuan.xyshop.mvp.contract.CartView;
import com.xinyuan.xyshop.mvp.presenter.CartPresenter;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;
import com.xinyuan.xyshop.ui.home.ScanActivity;
import com.xinyuan.xyshop.ui.mine.login.LoginActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/4.
 */

public class UserCartFragment extends BaseFragment<CartPresenter> implements CartView, UserCartAdapter.CheckInterface, UserCartAdapter.ModifyCountInterface, UserCartAdapter.GroupEdtorListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.cart_swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout; //刷新布局
    @BindView(R.id.rl_car_login_notice)
    RelativeLayout rl_car_login_notice; //登录提示
    @BindView(R.id.layout_cart_empty)
    LinearLayout cart_empty;            //空白布局
    @BindView(R.id.rl_bottom)
    RelativeLayout rl_bottom;           //底部按钮布局
    @BindView(R.id.ll_cart)
    RelativeLayout llCart;              //
    @BindView(R.id.exListView)
    ExpandableListView exListView;      //主list
    @BindView(R.id.all_chekbox)
    CheckBox allChekbox;                //全选按钮
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;              //总价格显示
    @BindView(R.id.tv_go_to_pay)
    TextView tvGoToPay;                 //去支付按钮
    @BindView(R.id.ll_shar)
    LinearLayout llShar;
    @BindView(R.id.ll_info)
    RelativeLayout llInfo;
    @BindView(R.id.toolbartitle)
    TextView tv_header_center;
    @BindView(R.id.toolbar_edit)
    TextView tv_header_edit;
    @BindView(R.id.cart_toolbar)
    Toolbar topBar;
    @BindView(R.id.loading_view)
    XLoadingView loading_view;

    private static int flag = 0;
    private UserCartAdapter cartAdapter;
    private List<StoreInfoBean> groups = new ArrayList<StoreInfoBean>();// 组元素数据列表
    private Map<Long, List<CartGoodBean>> children = new HashMap<Long, List<CartGoodBean>>();// 子元素数据列表
    private BigDecimal totalPrice = new BigDecimal(0.00);// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    private List<StoreInfoBean> chosesGoods = new ArrayList<>();    //已选择的商品列表

    public static UserCartFragment newInstance() {
        UserCartFragment fragment = new UserCartFragment();
        return fragment;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_cart;
    }

    @Override
    public void initView(View rootView) {
        SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
        SystemBarHelper.setHeightAndPadding(getActivity(), topBar);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(29, 160, 57));
        loading_view.setOnRetryClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showState(0);
                        mPresenter.getCart();
                    }
                });
    }


    @Override
    protected CartPresenter createPresenter() {
        return new CartPresenter(getActivity(), this);
    }

    private void checkLogin() {
        if (!LoginContext.getInstance().isLogin) {
            rl_car_login_notice.setVisibility(View.VISIBLE);
            cart_empty.setVisibility(View.VISIBLE);
            rl_bottom.setVisibility(View.GONE);
            exListView.setVisibility(View.GONE);
            tv_header_edit.setVisibility(View.GONE);
            loading_view.showContent();

        } else {
            rl_car_login_notice.setVisibility(View.GONE);
            tv_header_edit.setVisibility(View.VISIBLE);
            exListView.setVisibility(View.VISIBLE);
            rl_bottom.setVisibility(View.VISIBLE);
            llCart.setVisibility(View.VISIBLE);
            chosesGoods.clear();
            totalCount = 0;
            tvGoToPay.setText("去支付(" + totalCount + ")");
            mPresenter.getCart();
        }
    }


    @Override
    public void showState(int state) {
        switch (state) {
            case 0:
                loading_view.showLoading();
                break;
            case 1:
                loading_view.showContent();
                break;
            case 2:
                loading_view.showError();
                break;

        }
    }

    @Override
    public LifecycleTransformer<List<StoreInfoBean>> bindLife() {
        return this.<List<StoreInfoBean>>bindUntilEvent(FragmentEvent.DESTROY_VIEW);
    }

    @Override
    public void showCart(List<StoreInfoBean> storeList) {
        if (XEmptyUtils.isEmpty(storeList)) {
            cart_empty.setVisibility(View.VISIBLE);
            rl_bottom.setVisibility(View.GONE);
        } else {
            mPresenter.getGood();
            cart_empty.setVisibility(View.GONE);
            rl_bottom.setVisibility(View.VISIBLE);
            allChekbox.setChecked(false);
            this.groups = storeList;

            for (StoreInfoBean storebean : storeList) {
                children.put(storebean.getStoreId(), storebean.getGoodsCartList());
            }

            cartAdapter = new UserCartAdapter(storeList, children, getContext());
            cartAdapter.setCheckInterface(this);// 关键步骤1,设置复选框接口
            cartAdapter.setModifyCountInterface(this);// 关键步骤2,设置数量增减接口
            cartAdapter.setmListener(this);
            exListView.setAdapter(cartAdapter);
            for (int j = 0; j < cartAdapter.getGroupCount(); j++) {
                exListView.expandGroup(j);// 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
            }
            loading_view.showContent();

        }

    }

    private View footerView;
    private GoodsGridAdapter goodAdapter;


    @OnClick({R.id.all_chekbox, R.id.tv_delete, R.id.tv_go_to_pay, R.id.toolbar_edit})
    public void onClick(View view) {
        AlertDialog alert;
        switch (view.getId()) {
            case R.id.all_chekbox:
                doCheckAll();
                break;
            case R.id.tv_delete:
                if (totalCount == 0) {
                    Toast.makeText(mContext, "请选择要移除的商品", Toast.LENGTH_LONG).show();
                    return;
                }
                alert = new AlertDialog.Builder(getActivity()).create();
                alert.setTitle("操作提示");
                alert.setMessage("您确定要将这些商品从购物车中移除吗？");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                doDelete();
                            }
                        });
                alert.show();
                break;
            case R.id.tv_go_to_pay:


                if (totalCount == 0) {
                    Toast.makeText(mContext, "请选择要支付的商品", Toast.LENGTH_LONG).show();
                    return;
                }

                JsonArray jsonArr = new JsonArray();//json格式的数组 如果用户调整了商品数量，先向服务器发送购物车id和产品数量修改掉
                for (StoreInfoBean storeInfoBean : chosesGoods) {
                    for (CartGoodBean goodBean : storeInfoBean.getGoodsCartList()) {
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("goodsCartId", String.valueOf(goodBean.getGoodsCartId()));
                        jsonObject.addProperty("goodsNum", String.valueOf(goodBean.getGoodsCount()));
                        jsonArr.add(jsonObject);
                    }
                }
                mPresenter.putGoodNum(jsonArr.toString());


                break;
            case R.id.toolbar_edit:
                if (flag == 0) {
                    llInfo.setVisibility(View.GONE);
                    tvGoToPay.setVisibility(View.GONE);
                    llShar.setVisibility(View.VISIBLE);
                    tv_header_edit.setText("完成");
                } else if (flag == 1) {
                    llInfo.setVisibility(View.VISIBLE);
                    tvGoToPay.setVisibility(View.VISIBLE);
                    llShar.setVisibility(View.GONE);
                    tv_header_edit.setText("编辑");
                }
                flag = (flag + 1) % 2;//其余得到循环执行上面2个不同的功能
                break;
        }
    }

    @Override
    public void showGood(final RecomGoodModel goodModel) {
        if (XEmptyUtils.isEmpty(footerView)) {
            if (!XEmptyUtils.isEmpty(goodModel) && !XEmptyUtils.isEmpty(goodModel.getGoodlist())) {


                footerView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_cart_item_recome, null);
                FlexboxLayout fl_goods = (FlexboxLayout) footerView.findViewById(R.id.fl_goods);
                fl_goods.removeAllViews();

                for (int i = 0; i < goodModel.getGoodlist().size(); i++) {
                    AddViewHolder addViewHolder = new AddViewHolder(getContext(), R.layout.view_item_half_grid);
                    ImageView iv_good = addViewHolder.getView(R.id.ivGoodPic1);
                    TextView tv_goods_name = addViewHolder.getView(R.id.tv_goods_name1);
                    TextView tv_goods_price = addViewHolder.getView(R.id.tv_goods_price1);
                    TextView tv_goods_sellnum = addViewHolder.getView(R.id.tv_goods_sellnum1);
                    RelativeLayout rl_good1 = addViewHolder.getView(R.id.rl_good1);

                    final int position = i;
                    rl_good1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Map<String, String> params = new HashMap();
                            params.put(Constants.GOODID, String.valueOf(goodModel.getGoodlist().get(position).getGoodsId()));
                            params.put(Constants.GOODTYPE, goodModel.getGoodlist().get(position).getGoodsType());
                            CommUtil.gotoActivity(getContext(), GoodDetailActivity.class, false, params);
                        }
                    });
                    GlideImageLoader.setUrlImg(getContext(), goodModel.getGoodlist().get(i).getImageUrl(), iv_good);
                    tv_goods_name.setText(goodModel.getGoodlist().get(i).getGoodsName());
                    tv_goods_price.setText(getResources().getString(R.string.money_rmb) + ShopHelper.getPriceString(goodModel.getGoodlist().get(i).getGoodsPrice()));
                    tv_goods_sellnum.setText(getString(R.string.good_selle_num) + goodModel.getGoodlist().get(i).getConsumeNum() + "件");

                    /**第二个商品*/

                    ImageView iv_good2 = addViewHolder.getView(R.id.ivGoodPic2);
                    TextView tv_goods_name2 = addViewHolder.getView(R.id.tv_goods_name2);
                    TextView tv_goods_price2 = addViewHolder.getView(R.id.tv_goods_price2);
                    TextView tv_goods_sellnum2 = addViewHolder.getView(R.id.tv_goods_sellnum2);
                    RelativeLayout rl_good2 = addViewHolder.getView(R.id.rl_good2);
                    final int position2 = i + 1;
                    rl_good2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Map<String, String> params = new HashMap();
                            params.put(Constants.GOODID, String.valueOf(goodModel.getGoodlist().get(position2).getGoodsId()));
                            params.put(Constants.GOODTYPE, goodModel.getGoodlist().get(position2).getGoodsType());
                            CommUtil.gotoActivity(getContext(), GoodDetailActivity.class, false, params);
                        }
                    });

                    GlideImageLoader.setUrlImg(getContext(), goodModel.getGoodlist().get(i + 1).getImageUrl(), iv_good2);
                    tv_goods_name2.setText(goodModel.getGoodlist().get(i + 1).getGoodsName());
                    tv_goods_price2.setText(getResources().getString(R.string.money_rmb) + ShopHelper.getPriceString(goodModel.getGoodlist().get(i + 1).getGoodsPrice()));
                    tv_goods_sellnum2.setText(getString(R.string.good_selle_num) + goodModel.getGoodlist().get(i + 1).getConsumeNum() + "件");
                    fl_goods.addView(addViewHolder.getCustomView());
                    i++;
                }
                exListView.addFooterView(footerView);
                loading_view.showContent();

            }

        }
    }

    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {
        StoreInfoBean group = groups.get(groupPosition);
        List<CartGoodBean> childs = children.get(group.getStoreId());
        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).setChoosed(isChecked);
        }
        if (isAllCheck())
            allChekbox.setChecked(true);
        else
            allChekbox.setChecked(false);
        cartAdapter.notifyDataSetChanged();
        calculate();
    }

    /**
     * 全选
     *
     * @return
     */
    private boolean isAllCheck() {
        for (StoreInfoBean group : groups) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }


    /**
     * 统计操作
     */
    private void calculate() {
        totalCount = 0;
        totalPrice = new BigDecimal(0.00);
        chosesGoods.clear();
        for (int i = 0; i < groups.size(); i++) {
            StoreInfoBean group = groups.get(i);
            List<CartGoodBean> childs = children.get(group.getStoreId());
            List<CartGoodBean> chosesGood = new ArrayList<>();
            StoreInfoBean chosesStore = new StoreInfoBean();
            chosesStore.setStoreId(group.getStoreId());
            for (int j = 0; j < childs.size(); j++) {
                CartGoodBean product = childs.get(j);
                if (product.isChoosed()) {
                    totalCount++;
                    if (!XEmptyUtils.isEmpty(product.getGoodsPrice())) {
                        totalPrice = totalPrice.add(product.getGoodsPrice().multiply(BigDecimal.valueOf(product.getGoodsCount())));
                    }
                    chosesGood.add(product);
                }
            }
            if (chosesGood.size() > 0) {
                chosesStore.setGoodsCartList(chosesGood);
                chosesGoods.add(chosesStore);
            }
        }

        tvTotalPrice.setText(mContext.getString(R.string.money_rmb) + ShopHelper.getPriceString(totalPrice));
        tvGoToPay.setText("去支付(" + totalCount + ")");
        //计算购物车的金额为0时候清空购物车的视图
        if (totalCount == 0) {
            setCartNum();
        } else {
            tv_header_center.setText(mContext.getString(R.string.shop_car) + "(" + totalCount + ")");
        }
    }

    private List<StoreInfoBean> toBeDeleteGroups;

    //删除购物车
    protected void doDelete() {
        toBeDeleteGroups = new ArrayList<StoreInfoBean>();// 待删除的组元素列表
        List<Long> toBeDeleteCartIds = new ArrayList<>();//待删除的购物车ID列表
        for (int i = 0; i < groups.size(); i++) {
            StoreInfoBean group = groups.get(i);
            if (group.isChoosed()) {
                toBeDeleteGroups.add(group);
            }
            List<CartGoodBean> toBeDeleteProducts = new ArrayList<CartGoodBean>();// 待删除的子元素列表
            List<CartGoodBean> childs = children.get(group.getStoreId());
            for (int j = 0; j < childs.size(); j++) {
                if (childs.get(j).isChoosed()) {
                    toBeDeleteProducts.add(childs.get(j));
                    toBeDeleteCartIds.add(childs.get(j).getGoodsCartId());
                }
            }
            childs.removeAll(toBeDeleteProducts);
        }
        mPresenter.deteleGood(toBeDeleteCartIds.toString());

    }

    @Override
    public void deteleGood(boolean result) {
        if (result) {
            XToast.info(getString(R.string.good_detele_success));
            groups.removeAll(toBeDeleteGroups);
            //记得重新设置购物车
            setCartNum();
            cartAdapter.notifyDataSetChanged();
        } else {
            XToast.info(getString(R.string.good_detele_error));
        }


    }

    @Override
    public void postOrder() {
        Map<String, String> params = new HashMap();
        params.put(Constants.GOODTYPE, Constants.GOOD_NORMAL);
        LoginContext.getInstance().buy(false, params, getActivity());
        EventBus.getDefault().postSticky(chosesGoods);
    }


    /**
     * 设置购物车产品数量
     */
    private void setCartNum() {
        int count = 0;
        for (int i = 0; i < groups.size(); i++) {
            groups.get(i).setChoosed(allChekbox.isChecked());
            StoreInfoBean group = groups.get(i);
            List<CartGoodBean> childs = children.get(group.getStoreId());
            for (CartGoodBean goodsInfo : childs) {
                count += 1;
            }
        }

        //购物车已清空
        if (count == 0) {
            clearCart();
        } else {
            tv_header_center.setText(R.string.shop_car + "(" + count + ")");
        }
    }

    /**
     * 清空购物车
     */
    private void clearCart() {
        tv_header_center.setText(getString(R.string.shop_car) + "(" + 0 + ")");
        tv_header_edit.setVisibility(View.GONE);
        llCart.setVisibility(View.GONE);
        cart_empty.setVisibility(View.VISIBLE);
    }

    /**
     * 全选与反选
     */
    private void doCheckAll() {
        for (int i = 0; i < groups.size(); i++) {
            groups.get(i).setChoosed(allChekbox.isChecked());
            StoreInfoBean group = groups.get(i);
            List<CartGoodBean> childs = children.get(group.getStoreId());
            for (int j = 0; j < childs.size(); j++) {
                childs.get(j).setChoosed(allChekbox.isChecked());
            }
        }
        cartAdapter.notifyDataSetChanged();
        calculate();
    }

    /**
     * 单选
     *
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     * @param isChecked     子元素选中与否
     */
    @Override
    public void checkChild(int groupPosition, int childPosition, boolean isChecked) {
        boolean allChildSameState = true;// 判断改组下面的所有子元素是否是同一种状态
        StoreInfoBean group = groups.get(groupPosition);
        List<CartGoodBean> childs = children.get(group.getStoreId());
        for (int i = 0; i < childs.size(); i++) {
            // 不全选中
            if (childs.get(i).isChoosed() != isChecked) {
                allChildSameState = false;
                break;
            }
        }
        //获取店铺选中商品的总金额
        if (allChildSameState) {
            group.setChoosed(isChecked);// 如果所有子元素状态相同，那么对应的组元素被设为这种统一状态
        } else {
            group.setChoosed(false);// 否则，组元素一律设置为未选中状态
        }

        if (isAllCheck()) {
            allChekbox.setChecked(true);// 全选
        } else {
            allChekbox.setChecked(false);// 反选
        }
        cartAdapter.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        CartGoodBean product = (CartGoodBean) cartAdapter.getChild(groupPosition,
                childPosition);
        int currentCount = product.getGoodsCount();
        currentCount++;
        product.setGoodsCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        cartAdapter.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {

        CartGoodBean product = (CartGoodBean) cartAdapter.getChild(groupPosition,
                childPosition);
        int currentCount = product.getGoodsCount();
        if (currentCount == 1)
            return;
        currentCount--;
        product.setGoodsCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        cartAdapter.notifyDataSetChanged();
        calculate();
    }

    /**
     * 删除单个
     *
     * @param groupPosition
     * @param childPosition
     */
    @Override
    public void childDelete(int groupPosition, int childPosition) {
        children.get(groups.get(groupPosition).getStoreId()).remove(childPosition);
        StoreInfoBean group = groups.get(groupPosition);
        List<CartGoodBean> childs = children.get(group.getStoreId());
        if (childs.size() == 0) {
            groups.remove(groupPosition);
        }
        cartAdapter.notifyDataSetChanged();
        //     handler.sendEmptyMessage(0);
        calculate();
    }

    /**
     * 组元素编辑
     *
     * @param groupPosition
     */
    @Override
    public void groupEdit(int groupPosition) {
        groups.get(groupPosition).setEdtor(true);
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public void initData() {

    }

    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
        if (event.position != MainFragment.CAR) return;
        checkLogin();
    }

    @Override
    public void onResume() {
        checkLogin();
        super.onResume();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.getCart();
                mSwipeRefreshLayout.setRefreshing(false);

            }
        }, 500);
    }

    @OnClick({R.id.car_btn_scan, R.id.bt_cart_login})
    public void ScanClick(View view) {
        switch (view.getId()) {
            case R.id.bt_cart_login:
                CommUtil.gotoActivity(getActivity(), LoginActivity.class, false, null);
                break;
            case R.id.car_btn_scan:
                CommUtil.gotoActivity(getActivity(), ScanActivity.class, false, null);
                break;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cartAdapter = null;
        groups.clear();
        totalPrice = new BigDecimal(0.00);
        totalCount = 0;
        children.clear();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unregisterEventBus(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        registerEventBus(this);
    }
}
