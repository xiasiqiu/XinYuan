package com.xinyuan.xyshop.ui.mine.info;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.CreditAdapter;
import com.xinyuan.xyshop.adapter.FollowAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.callback.OnItemClickListener;
import com.xinyuan.xyshop.entity.CreditBean;
import com.xinyuan.xyshop.entity.FollowBean;
import com.xinyuan.xyshop.ui.mine.pro.CreditFragment;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/26.
 */

public class FollowFragment extends BaseFragment {
	@BindView(R.id.toolbar_tv)
	Toolbar toolbar_tv;
	@BindView(R.id.rv_follow)
	SwipeMenuRecyclerView rv_follow;
	FollowAdapter adapter;
	@BindView(R.id.tv_header_right)
	TextView tv_header_right;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;

	public static FollowFragment newInstance() {
		FollowFragment fragment = new FollowFragment();
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_follow;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		if (toolbar_tv != null) {
			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_tv);
			tv_header_center.setText("关注店铺");
		}


	}

	List<FollowBean> list;

	@Override
	public void onLazyInitView(@Nullable Bundle savedInstanceState) {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(1);
		this.rv_follow.setLayoutManager(layoutManager);
		List<String> one = new ArrayList<>();
		one.add("皇冠");
		one.add("金牌卖家");
		List<String> two = new ArrayList<>();
		two.add("皇冠");
		list = new ArrayList<>();

		list.add(new FollowBean("http://gw2.alicdn.com/bao/uploaded/1e/d2/TB1gmj.GpXXXXXKXVXXSutbFXXX.jpg_80x80.jpg_.webp", "可可丽小姐", one));
		list.add(new FollowBean("http://gw2.alicdn.com/bao/uploaded/d6/d3/TB1uvjXJFXXXXX6XFXXSutbFXXX.jpg_80x80.jpg_.webp", "ZARA官方旗舰店", two));
		list.add(new FollowBean("http://gw2.alicdn.com/bao/uploaded/1d/fa/TB1CR2MRVXXXXbZXFXXSutbFXXX.jpg_80x80.jpg_.webp", "试衣间", one));
		list.add(new FollowBean("http://gw2.alicdn.com/bao/uploaded/a1/fc/T1ZNdyFi4cXXb1upjX.jpg_80x80.jpg_.webp", "一号女装", two));
		list.add(new FollowBean("http://gw2.alicdn.com/bao/uploaded/1e/d2/TB1gmj.GpXXXXXKXVXXSutbFXXX.jpg_80x80.jpg_.webp", "可可丽小姐", one));
		list.add(new FollowBean("http://gw2.alicdn.com/bao/uploaded/d6/d3/TB1uvjXJFXXXXX6XFXXSutbFXXX.jpg_80x80.jpg_.webp", "ZARA官方旗舰店", two));
		list.add(new FollowBean("http://gw2.alicdn.com/bao/uploaded/1d/fa/TB1CR2MRVXXXXbZXFXXSutbFXXX.jpg_80x80.jpg_.webp", "试衣间", one));
		list.add(new FollowBean("http://gw2.alicdn.com/bao/uploaded/a1/fc/T1ZNdyFi4cXXb1upjX.jpg_80x80.jpg_.webp", "一号女装", two));
		list.add(new FollowBean("http://gw2.alicdn.com/bao/uploaded/1e/d2/TB1gmj.GpXXXXXKXVXXSutbFXXX.jpg_80x80.jpg_.webp", "可可丽小姐", one));
		list.add(new FollowBean("http://gw2.alicdn.com/bao/uploaded/d6/d3/TB1uvjXJFXXXXX6XFXXSutbFXXX.jpg_80x80.jpg_.webp", "ZARA官方旗舰店", two));
		list.add(new FollowBean("http://gw2.alicdn.com/bao/uploaded/1d/fa/TB1CR2MRVXXXXbZXFXXSutbFXXX.jpg_80x80.jpg_.webp", "试衣间", one));
		list.add(new FollowBean("http://gw2.alicdn.com/bao/uploaded/a1/fc/T1ZNdyFi4cXXb1upjX.jpg_80x80.jpg_.webp", "一号女装", two));

		this.adapter = new FollowAdapter(list, getContext());
		rv_follow.setSwipeMenuCreator(swipeMenuCreator);
		rv_follow.setSwipeMenuItemClickListener(menuItemClickListener);
		adapter.setOnItemClickListener(onItemClickListener);
		this.rv_follow.setAdapter(adapter);


	}

	/**
	 * Item点击监听。
	 */
	private OnItemClickListener onItemClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(int position) {
			Toast.makeText(context, "我是第" + position + "条。", Toast.LENGTH_SHORT).show();
		}
	};
	private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {

		@Override
		public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
			int width = getResources().getDimensionPixelSize(R.dimen.item_height);

			// MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
			int height = ViewGroup.LayoutParams.MATCH_PARENT;

			// 添加右侧的，如果不添加，则右侧不会出现菜单。
			{
				SwipeMenuItem shareItem = new SwipeMenuItem(context)
						.setBackgroundDrawable(R.color.colorPrimary)
						.setText("分享") // 文字，还可以设置文字颜色，大小等。。
						.setTextColor(Color.WHITE)
						.setWidth(width)
						.setTextSize(12)
						.setHeight(height);

				swipeRightMenu.addMenuItem(shareItem);// 添加一个按钮到右侧侧菜单。
				SwipeMenuItem deleteItem = new SwipeMenuItem(context)
						.setBackgroundDrawable(R.color.colorPrimaryDark)
						.setText("取消关注") // 文字，还可以设置文字颜色，大小等。。
						.setTextColor(Color.WHITE)
						.setWidth(width)
						.setTextSize(12)
						.setHeight(height);
				swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。
			}
		}
	};
	/**
	 * 菜单点击监听。
	 */
	private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
		@Override
		public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
			closeable.smoothCloseMenu();// 关闭被点击的菜单。

			if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
				XLog.v( "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT);
				if(menuPosition==0){
					XToast.info("成功分享！");
				}else {
					XToast.info("已移除收藏夹");
				}


			}


			if (menuPosition == 1) {// 删除按钮被点击。
				list.remove(adapterPosition);
				adapter.notifyItemRemoved(adapterPosition);
			}
		}
	};
}
