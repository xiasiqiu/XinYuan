package com.xinyuan.xyshop.ui.shopcar;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.CartAdapter;
import com.xinyuan.xyshop.adapter.SearchGoodListAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.entity.GoodsInfo;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.entity.StoreInfo;
import com.xinyuan.xyshop.even.LoginPageEvent;
import com.xinyuan.xyshop.mvp.presenter.HomePresenterImpl;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.XYGridLayoutManager;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/4.
 */

public class CartFragment extends BaseFragment implements CartAdapter.CheckInterface,
		CartAdapter.ModifyCountInterface, CartAdapter.GroupEdtorListener, SwipeRefreshLayout.OnRefreshListener {


	@BindView(R.id.toolbartitle)
	TextView title;
	@BindView(R.id.toolbar_edit)
	TextView subtitle;
	@BindView(R.id.cart_toolbar)
	Toolbar topBar;

	@BindView(R.id.exListView)
	ExpandableListView exListView;
	@BindView(R.id.tv_total_price)
	TextView tvTotalPrice;
	@BindView(R.id.all_chekbox)
	CheckBox allChekbox;
	@BindView(R.id.tv_delete)
	TextView tvDelete;
	@BindView(R.id.tv_go_to_pay)
	TextView tvGoToPay;

	@BindView(R.id.ll_shar)
	LinearLayout llShar;
	@BindView(R.id.ll_info)
	RelativeLayout llInfo;

	@BindView(R.id.tv_share)
	TextView tvShare;
	@BindView(R.id.ll_cart)
	RelativeLayout llCart;
	@BindView(R.id.layout_cart_empty)
	LinearLayout cart_empty;
	@BindView(R.id.loading_view)
	XLoadingView loading_view;

	@BindView(R.id.cart_swipeLayout)
	SwipeRefreshLayout mSwipeRefreshLayout;

	private double totalPrice = 0.00;// 购买的商品总价
	private int totalCount = 0;// 购买的商品总数量
	private CartAdapter selva;
	private List<StoreInfo> groups = new ArrayList<StoreInfo>();// 组元素数据列表
	private Map<String, List<GoodsInfo>> children = new HashMap<String, List<GoodsInfo>>();// 子元素数据列表
	private int flag = 0;

	public static CartFragment newInstance() {

		Bundle args = new Bundle();

		CartFragment fragment = new CartFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_cart;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	private static boolean VIEW_INIT = true;


	@BindView(R.id.rl_car_login_notice)
	RelativeLayout rl_car_login_notice;

	@Override
	public void initView() {
		EventBus.getDefault().register(this);
		if (VIEW_INIT) {
			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(getActivity(), topBar);
		}
		XLog.v("加载购物车页面Fragment");
		VIEW_INIT = false;
		getLogin();

	}

	private void getLogin() {
		if (MyShopApplication.isLogin) {
			rl_car_login_notice.setVisibility(View.GONE);
			initDatas();

		} else {
			rl_car_login_notice.setVisibility(View.VISIBLE);
			cart_empty.setVisibility(View.VISIBLE);
			loading_view.showContent();
		}
	}

	@Override
	public void onLazyInitView(@Nullable Bundle savedInstanceState) {


	}

	private void initDatas() {
		cart_empty.setVisibility(View.GONE);
		for (int i = 0; i < 3; i++) {
			groups.add(new StoreInfo(i + "", "天猫店铺" + (i + 1) + "号店"));
			List<GoodsInfo> products = new ArrayList<GoodsInfo>();
			for (int j = 0; j <= i; j++) {
				int[] img = {R.drawable.goods1, R.drawable.goods2, R.drawable.goods3, R.drawable.goods4, R.drawable.goods5, R.drawable.goods6};
				products.add(new GoodsInfo(j + "", "商品", groups.get(i)
						.getName() + "的第" + (j + 1) + "个商品", 12.00 + new Random().nextInt(23), new Random().nextInt(5) + 1, "豪华", "1", img[i * j], 6.00 + new Random().nextInt(13)));
			}
			children.put(groups.get(i).getId(), products);// 将组元素的一个唯一值，这里取Id，作为子元素List的Key
		}
		initEvents();
	}

	private void initEvents() {
		selva = new CartAdapter(groups, children, getContext());
		selva.setCheckInterface(this);// 关键步骤1,设置复选框接口
		selva.setModifyCountInterface(this);// 关键步骤2,设置数量增减接口
		selva.setmListener(this);


		exListView.setAdapter(selva);
		for (int i = 0; i < selva.getGroupCount(); i++) {
			exListView.expandGroup(i);// 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
		}


		View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_cart_item_recome, null);

		FlexboxLayout fl_goods = (FlexboxLayout) view.findViewById(R.id.fl_goods);
		for (int i = 0; i < 10; i++) {
			AddViewHolder addViewHolder = new AddViewHolder(getContext(), R.layout.view_item_half_grid);
			ImageView iv_good = addViewHolder.getView(R.id.ivGoodPic1);
			TextView tv_goods_name = addViewHolder.getView(R.id.tv_goods_name1);
			TextView tv_goods_price = addViewHolder.getView(R.id.tv_goods_price1);
			TextView tv_goods_sellnum = addViewHolder.getView(R.id.tv_goods_sellnum1);


			ImageView iv_good2 = addViewHolder.getView(R.id.ivGoodPic2);
			TextView tv_goods_name2 = addViewHolder.getView(R.id.tv_goods_name2);
			TextView tv_goods_price2 = addViewHolder.getView(R.id.tv_goods_price2);
			TextView tv_goods_sellnum2 = addViewHolder.getView(R.id.tv_goods_sellnum2);


			GlideImageLoader.setImage(getContext(), "https://java.bizpower.com/upload/image/6b/79/6b795db037af1af0816a8f97e7fac88d.jpg", iv_good);
			tv_goods_name.setText("测试商品" + i);
			tv_goods_price.setText("￥102.00");
			tv_goods_sellnum.setText("销量:" + 123);


			GlideImageLoader.setImage(getContext(), "https://img.alicdn.com/bao/uploaded/i3/TB1jp4zSXXXXXa5aXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg", iv_good2);
			tv_goods_name2.setText("【淘宝心选】创意懒人轻便叠衣板T恤衬衫折衣板叠衣服 10片装" + i);
			tv_goods_price2.setText("￥59.00");
			tv_goods_sellnum2.setText("销量:" + 371);
			fl_goods.addView(addViewHolder.getCustomView());
		}
		exListView.addFooterView(view);
		loading_view.showContent();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	/**
	 * 设置购物车产品数量
	 */
	private void setCartNum() {
		int count = 0;
		for (int i = 0; i < groups.size(); i++) {
			groups.get(i).setChoosed(allChekbox.isChecked());
			StoreInfo group = groups.get(i);
			List<GoodsInfo> childs = children.get(group.getId());
			for (GoodsInfo goodsInfo : childs) {
				count += 1;
			}
		}

		//购物车已清空
		if (count == 0) {
			clearCart();
		} else {
			title.setText("购物车" + "(" + count + ")");
		}
	}

	private void clearCart() {
		title.setText("购物车" + "(" + 0 + ")");
		subtitle.setVisibility(View.GONE);
		llCart.setVisibility(View.GONE);
		cart_empty.setVisibility(View.VISIBLE);
	}

	@OnClick({R.id.all_chekbox, R.id.tv_delete, R.id.tv_go_to_pay, R.id.toolbar_edit, R.id.tv_share})
	public void onClick(View view) {
		AlertDialog alert;
		switch (view.getId()) {
			case R.id.all_chekbox:
				doCheckAll();
				break;
			case R.id.tv_delete:
				if (totalCount == 0) {
					Toast.makeText(context, "请选择要移除的商品", Toast.LENGTH_LONG).show();
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
					Toast.makeText(context, "请选择要支付的商品", Toast.LENGTH_LONG).show();
					return;
				}
				alert = new AlertDialog.Builder(context).create();
				alert.setTitle("操作提示");
				alert.setMessage("总计:\n" + totalCount + "种商品\n" + totalPrice + "元");
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
								return;
							}
						});
				alert.show();
				break;
			case R.id.toolbar_edit:
				if (flag == 0) {
					llInfo.setVisibility(View.GONE);
					tvGoToPay.setVisibility(View.GONE);
					llShar.setVisibility(View.VISIBLE);
					subtitle.setText("完成");
				} else if (flag == 1) {
					llInfo.setVisibility(View.VISIBLE);
					tvGoToPay.setVisibility(View.VISIBLE);
					llShar.setVisibility(View.GONE);
					subtitle.setText("编辑");
				}
				flag = (flag + 1) % 2;//其余得到循环执行上面2个不同的功能
				break;
			case R.id.tv_share:
				if (totalCount == 0) {
					Toast.makeText(context, "请选择要分享的商品", Toast.LENGTH_LONG).show();
					return;
				}
				XToast.info("分享成功");
				break;
		}
	}

	protected void doDelete() {
		List<StoreInfo> toBeDeleteGroups = new ArrayList<StoreInfo>();// 待删除的组元素列表
		for (int i = 0; i < groups.size(); i++) {
			StoreInfo group = groups.get(i);
			if (group.isChoosed()) {
				toBeDeleteGroups.add(group);
			}
			List<GoodsInfo> toBeDeleteProducts = new ArrayList<GoodsInfo>();// 待删除的子元素列表
			List<GoodsInfo> childs = children.get(group.getId());
			for (int j = 0; j < childs.size(); j++) {
				if (childs.get(j).isChoosed()) {
					toBeDeleteProducts.add(childs.get(j));
				}
			}
			childs.removeAll(toBeDeleteProducts);
		}
		groups.removeAll(toBeDeleteGroups);
		//记得重新设置购物车
		setCartNum();
		selva.notifyDataSetChanged();

	}

	@Override
	public void checkGroup(int groupPosition, boolean isChecked) {
		StoreInfo group = groups.get(groupPosition);
		List<GoodsInfo> childs = children.get(group.getId());
		for (int i = 0; i < childs.size(); i++) {
			childs.get(i).setChoosed(isChecked);
		}
		if (isAllCheck())
			allChekbox.setChecked(true);
		else
			allChekbox.setChecked(false);
		selva.notifyDataSetChanged();
		calculate();
	}

	@Override
	public void checkChild(int groupPosition, int childPosition, boolean isChecked) {
		boolean allChildSameState = true;// 判断改组下面的所有子元素是否是同一种状态
		StoreInfo group = groups.get(groupPosition);
		List<GoodsInfo> childs = children.get(group.getId());
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
		selva.notifyDataSetChanged();
		calculate();
	}

	@Override
	public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
		GoodsInfo product = (GoodsInfo) selva.getChild(groupPosition,
				childPosition);
		int currentCount = product.getCount();
		currentCount++;
		product.setCount(currentCount);
		((TextView) showCountView).setText(currentCount + "");
		selva.notifyDataSetChanged();
		calculate();
	}

	@Override
	public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {

		GoodsInfo product = (GoodsInfo) selva.getChild(groupPosition,
				childPosition);
		int currentCount = product.getCount();
		if (currentCount == 1)
			return;
		currentCount--;
		product.setCount(currentCount);
		((TextView) showCountView).setText(currentCount + "");
		selva.notifyDataSetChanged();
		calculate();
	}

	@Override
	public void childDelete(int groupPosition, int childPosition) {
		children.get(groups.get(groupPosition).getId()).remove(childPosition);
		StoreInfo group = groups.get(groupPosition);
		List<GoodsInfo> childs = children.get(group.getId());
		if (childs.size() == 0) {
			groups.remove(groupPosition);
		}
		selva.notifyDataSetChanged();
		//     handler.sendEmptyMessage(0);
		calculate();
	}

	@Override
	public void groupEdit(int groupPosition) {
		groups.get(groupPosition).setIsEdtor(true);
		selva.notifyDataSetChanged();
	}

	private boolean isAllCheck() {

		for (StoreInfo group : groups) {
			if (!group.isChoosed())
				return false;

		}

		return true;
	}

	/**
	 * 全选与反选
	 */
	private void doCheckAll() {
		for (int i = 0; i < groups.size(); i++) {
			groups.get(i).setChoosed(allChekbox.isChecked());
			StoreInfo group = groups.get(i);
			List<GoodsInfo> childs = children.get(group.getId());
			for (int j = 0; j < childs.size(); j++) {
				childs.get(j).setChoosed(allChekbox.isChecked());
			}
		}
		selva.notifyDataSetChanged();
		calculate();
	}

	/**
	 * 统计操作<br>
	 * 1.先清空全局计数器<br>
	 * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
	 * 3.给底部的textView进行数据填充
	 */
	private void calculate() {
		totalCount = 0;
		totalPrice = 0.00;
		for (int i = 0; i < groups.size(); i++) {
			StoreInfo group = groups.get(i);
			List<GoodsInfo> childs = children.get(group.getId());
			for (int j = 0; j < childs.size(); j++) {
				GoodsInfo product = childs.get(j);
				if (product.isChoosed()) {
					totalCount++;
					totalPrice += product.getPrice() * product.getCount();
				}
			}
		}

		tvTotalPrice.setText("￥" + totalPrice);
		tvGoToPay.setText("去支付(" + totalCount + ")");
		//计算购物车的金额为0时候清空购物车的视图
		if (totalCount == 0) {
			setCartNum();
		} else {
			title.setText("购物车" + "(" + totalCount + ")");
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		selva = null;
		groups.clear();
		totalPrice = 0;
		totalCount = 0;
		children.clear();
	}

	@Override
	public void onRefresh() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				initDatas();
			}
		}, 2000);

	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onEvent(LoginPageEvent event) {
		XToast.info("登录了！" + event.toString());
		if (event.isLoginStatus()) {
			initDatas();
		}

	}

	@Override
	public void onStart() {
		super.onStart();


	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		EventBus.getDefault().unregister(this);
	}
}
