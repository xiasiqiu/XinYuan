package com.xinyuan.xyshop.ui.shopcar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.SearchGoodListAdapter;
import com.xinyuan.xyshop.adapter.ShopCarAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.GoodsInfo;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.entity.ShopCarGoodsItem;
import com.xinyuan.xyshop.entity.ShopCarStoreItem;
import com.xinyuan.xyshop.entity.StoreInfo;
import com.xinyuan.xyshop.ui.catrgory.CategoryFragment;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fx on 2017/5/2 0002.
 */

public class ShopCarFragment extends BaseFragment implements ShopCarAdapter.CheckInterface,
		ShopCarAdapter.ModifyCountInterface, ShopCarAdapter.GroupEdtorListener {
	@BindView(R.id.category_toolbar)
	Toolbar toolbar;

	@BindView(R.id.rv_shopcar)
	RecyclerView rv_Car;
	@BindView(R.id.rl_car_login_notice)
	RelativeLayout rl_car_login_notice;
	private static int num = 1;
	ShopCarAdapter adapter;
	@BindView(R.id.good_checkbox)
	CheckBox allChekbox; //全选按钮

	@BindView(R.id.toolbar_edit)
	TextView toolbar_edit; //编辑按钮

	@BindView(R.id.ll_shopcar_edite_layout)
	LinearLayout ll_edite_layout; //编辑布局
	@BindView(R.id.btn_shopcar_share)
	Button btn_shopcar_share; //分享按钮
	@BindView(R.id.btn_shopcar_delete)
	Button btn_shopcar_delete; //删除按钮

	@BindView(R.id.rl_shopcar_pay_layout)
	RelativeLayout rl_pay_layout; //支付布局
	@BindView(R.id.all_price)
	TextView all_price; //总价
	@BindView(R.id.bt_sell)
	Button bt_sell; //支付按钮

	private double totalPrice = 0.00;// 购买的商品总价
	private int totalCount = 0;// 购买的商品总数量
	private List<StoreInfo> groups = new ArrayList<StoreInfo>();// 组元素数据列表
	private Map<String, List<GoodsInfo>> children = new HashMap<String, List<GoodsInfo>>();// 子元素数据列表

	public static ShopCarFragment newInstance() {

		Bundle args = new Bundle();

		ShopCarFragment fragment = new ShopCarFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_shopcart;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	ArrayList<MultiItemEntity> res;


	private ArrayList<MultiItemEntity> initdatas() {

		res = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			groups.add(new StoreInfo(i + "", "天猫店铺" + (i + 1) + "号店"));
			ShopCarStoreItem more = new ShopCarStoreItem();
			more.setPosition(i);
			List<GoodsInfo> products = new ArrayList<GoodsInfo>();
			for (int j = 0; j <= i; j++) {
				int[] img = {R.drawable.test_good_img, R.drawable.test_good_img, R.drawable.test_good_img, R.drawable.test_good_img, R.drawable.test_good_img, R.drawable.test_good_img};
				products.add(new GoodsInfo(j + "", "商品", groups.get(i)
						.getName() + "的第" + (j + 1) + "个商品", 12.00 + new Random().nextInt(23), new Random().nextInt(5) + 1, "豪华", "1", img[i * j], 6.00 + new Random().nextInt(13)));
				ShopCarGoodsItem shopCarGoodsItem = new ShopCarGoodsItem();
				shopCarGoodsItem.setStorePosition(i);
				shopCarGoodsItem.setGoddsPostion(j);
				more.addSubItem(shopCarGoodsItem);
			}
			children.put(groups.get(i).getId(), products);// 将组元素的一个唯一值，这里取Id，作为子元素List的Key
			res.add(more);

		}

		return res;
	}

	private static boolean VIEW_INIT = true;

	@Override
	public void initView() {
		if (VIEW_INIT) {
			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(getActivity(), toolbar);
		}
		XLog.v("加载购物车页面Fragment");
		VIEW_INIT = false;
		getLogin();
		adapter = new ShopCarAdapter(groups, children, initdatas());
		adapter.setCheckInterface(this);
		adapter.setModifyCountInterface(this);
		adapter.setmListener(this);

		final LinearLayoutManager manager = new LinearLayoutManager(getContext());
		rv_Car.setAdapter(adapter);
		// important! setLayoutManager should be called after setAdapter
		rv_Car.setLayoutManager(manager);
		adapter.expandAll();
		getFooterView();
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
		adapter.notifyDataSetChanged();
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
		adapter.notifyDataSetChanged();
		calculate();
	}

	private int flag = 0;

	@OnClick({R.id.good_checkbox, R.id.toolbar_edit, R.id.bt_sell, R.id.btn_shopcar_delete})
	public void OnClick(View view) {
		AlertDialog alert;
		switch (view.getId()) {
			case R.id.good_checkbox:
				doCheckAll();
				break;
			case R.id.toolbar_edit:
				doCheckAll();
				rl_pay_layout.setVisibility(View.GONE);
				ll_edite_layout.setVisibility(View.VISIBLE);
				if (flag == 0) {
					rl_pay_layout.setVisibility(View.GONE);
					ll_edite_layout.setVisibility(View.VISIBLE);
					toolbar_edit.setText("完成");
				} else if (flag == 1) {
					rl_pay_layout.setVisibility(View.VISIBLE);
					ll_edite_layout.setVisibility(View.GONE);
					toolbar_edit.setText("编辑");
				}
				flag = (flag + 1) % 2;//其余得到循环执行上面2个不同的功能
				break;
			case R.id.bt_sell:
				if (totalCount == 0) {
					XToast.error("请选择要支付的商品", Toast.LENGTH_SHORT);
					return;
				}
				alert = new AlertDialog.Builder(context).create();
				alert.setTitle("操作提示");
				alert.setMessage("总计:\n" + totalCount + "种商品\n" + totalPrice + "元" + "\n" + "商品：" + choseGoods.toString());
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

			case R.id.btn_shopcar_delete:
				if (totalCount == 0) {
					Toast.makeText(context, "请选择要移除的商品", Toast.LENGTH_LONG).show();
					return;
				}
				alert = new AlertDialog.Builder(context).create();
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

		}

	}

	/**
	 * 删除操作<br>
	 * 1.不要边遍历边删除，容易出现数组越界的情况<br>
	 * 2.现将要删除的对象放进相应的列表容器中，待遍历完后，以removeAll的方式进行删除
	 */
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
		res.clear();
		setCartNum();
		adapter.notifyDataSetChanged();

	}

	@Override
	public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
		GoodsInfo product = (GoodsInfo) adapter.getChild(groupPosition,
				childPosition);
		int currentCount = product.getCount();
		currentCount++;
		product.setCount(currentCount);
		((TextView) showCountView).setText(currentCount + "");
		adapter.notifyDataSetChanged();
		calculate();
	}

	@Override
	public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {

		GoodsInfo product = (GoodsInfo) adapter.getChild(groupPosition,
				childPosition);
		int currentCount = product.getCount();
		if (currentCount == 1)
			return;
		currentCount--;
		product.setCount(currentCount);
		((TextView) showCountView).setText(currentCount + "");
		adapter.notifyDataSetChanged();
		calculate();
	}

	@Override
	public void childDelete(int groupPosition, int childPosition) {

	}

	@Override
	public void groupEdit(int groupPosition) {

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
		adapter.notifyDataSetChanged();
		calculate();
	}

	List<GoodsInfo> choseGoods;

	/**
	 * 统计操作<br>
	 * 1.先清空全局计数器<br>
	 * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
	 * 3.给底部的textView进行数据填充
	 */
	private void calculate() {
		choseGoods = new ArrayList<>();
		totalCount = 0;
		totalPrice = 0.00;
		for (int i = 0; i < groups.size(); i++) {
			StoreInfo group = groups.get(i);
			List<GoodsInfo> childs = children.get(group.getId());
			for (int j = 0; j < childs.size(); j++) {
				GoodsInfo product = childs.get(j);
				if (product.isChoosed()) {
					choseGoods.add(product);
					totalCount++;
					totalPrice += product.getPrice() * product.getCount();
				}
			}
		}

		all_price.setText("￥" + totalPrice);
		bt_sell.setText("去结算(" + totalCount + ")");
		//计算购物车的金额为0时候清空购物车的视图
		if (totalCount == 0) {
			setCartNum();
		} else {
			bt_sell.setText("去结算(" + totalCount + ")");

		}
	}

	private boolean isAllCheck() {

		for (StoreInfo group : groups) {
			if (!group.isChoosed())
				return false;

		}

		return true;
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

		if (count == 0) {
			clearCart();
		} else {
			XLog.v("购物车数量" + count);
		}
	}

	@BindView(R.id.rl_bottom)
	RelativeLayout rl_bottom;
	@BindView(R.id.layout_cart_empty)
	LinearLayout cart_empty;

	private void clearCart() {

		rl_bottom.setVisibility(View.GONE);
		rv_Car.setVisibility(View.GONE);
		cart_empty.setVisibility(View.VISIBLE);
	}

	@Override
	public void onResume() {
		super.onResume();
		getLogin();
		setCartNum();
	}


	private void getLogin() {
		if (MyShopApplication.isLogin) {
			rl_car_login_notice.setVisibility(View.GONE);
		}

	}

	/**
	 * 底部推荐产品
	 */
	private void getFooterView() {

		View view = getActivity().getLayoutInflater().inflate(R.layout.shopcar_item_recome_title, (ViewGroup) rv_Car.getParent(), false);

		RecyclerView rv_recomm = (RecyclerView) view.findViewById(R.id.rv_recomm);
		List<GoodsVo> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			GoodsVo goodsVo = new GoodsVo();
			goodsVo.setImageSrc("https://java.bizpower.com/upload/image/6b/79/6b795db037af1af0816a8f97e7fac88d.jpg");
			goodsVo.setGoodsName("HAHAH");
			goodsVo.setAppPriceMin(10.02);
			goodsVo.setGoodsSaleNum(122);
			list.add(goodsVo);
		}

		SearchGoodListAdapter adapters = new SearchGoodListAdapter(R.layout.activity_searchgood_item_grid, list, false);
		GridLayoutManager layoutManager2 = new GridLayoutManager(this.context, 2, 1, false);
		rv_recomm.setLayoutManager(layoutManager2);
		rv_recomm.setAdapter(adapters);
		XLog.v("RecyView Set FOTTER");
		adapter.setFooterView(view);

	}
}
