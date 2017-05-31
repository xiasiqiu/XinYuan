package com.xinyuan.xyshop.ui.shopcar;

import android.os.Bundle;
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

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.SearchGoodListAdapter;
import com.xinyuan.xyshop.adapter.ShopCarAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.GoodsInfo;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.entity.ShopCarGoodsItem;
import com.xinyuan.xyshop.entity.ShopCarStoreItem;
import com.xinyuan.xyshop.entity.StoreInfo;
import com.youth.xframe.utils.log.XLog;
import com.zhy.autolayout.AutoRelativeLayout;

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
	AutoRelativeLayout rl_car_login_notice;
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

	@Override
	public int getLayoutId() {
		return R.layout.fragment_shopcart;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {

		ArrayList<MultiItemEntity> res = new ArrayList<>();
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


		adapter = new ShopCarAdapter(groups, children, res);
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

	@OnClick({R.id.good_checkbox, R.id.toolbar_edit})
	public void OnClick(View view) {
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
		}

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

		all_price.setText("￥" + totalPrice);

		//计算购物车的金额为0时候清空购物车的视图
		if (totalCount == 0) {
			setCartNum();
		} else {

		}
	}

	private boolean isAllCheck() {

		for (StoreInfo group : groups) {
			if (!group.isChoosed())
				return false;

		}

		return true;
	}

	@Override
	public void onResume() {
		super.onResume();
		setCartNum();
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
			XLog.v("购物车空");
		} else {
			XLog.v("购物车数量" + count);
		}
	}

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
		XLog.list(list);
		SearchGoodListAdapter adapters = new SearchGoodListAdapter(R.layout.searchgood_item_grid, list, false);
		GridLayoutManager layoutManager2 = new GridLayoutManager(this.context, 2, 1, false);
		rv_recomm.setLayoutManager(layoutManager2);
		rv_recomm.setAdapter(adapters);
		XLog.v("RecyView Set FOTTER");
		adapter.setFooterView(view);

	}
}
