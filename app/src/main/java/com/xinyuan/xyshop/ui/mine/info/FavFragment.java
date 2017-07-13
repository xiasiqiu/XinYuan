package com.xinyuan.xyshop.ui.mine.info;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.FavAdapter;
import com.xinyuan.xyshop.adapter.GoodsGridAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.callback.OnItemClickListener;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.ui.mine.pro.AccountFragment;
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

public class FavFragment extends BaseFragment {
	@BindView(R.id.recycler_view)
	SwipeMenuRecyclerView rv_fav;

	FavAdapter adapter;
	private LinearLayoutManager manager;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;

	@BindView(R.id.tv_header_right)
	TextView tv_header_right;
	@BindView(R.id.toolbar_tv)
	Toolbar msg_toolbar;
	List<GoodsVo> goodses;

	public static FavFragment newInstance() {
		FavFragment fragment = new FavFragment();
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_favorite;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		if (msg_toolbar != null) {
			XLog.v("加载toolbar");
			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
			tv_header_center.setText("收藏夹");


		}

	}

	@Override
	public void onLazyInitView(@Nullable Bundle savedInstanceState) {
		LinearLayoutManager layoutManager = new LinearLayoutManager(context);
		layoutManager.setOrientation(1);
		goodses = new ArrayList<>();
		goodses.add(new GoodsVo());
		goodses.add(new GoodsVo());
		goodses.add(new GoodsVo());


		adapter = new FavAdapter(goodses);
		rv_fav.setLayoutManager(new LinearLayoutManager(context));// 布局管理器。

		rv_fav.setSwipeMenuCreator(swipeMenuCreator);
		rv_fav.setSwipeMenuItemClickListener(menuItemClickListener);
		adapter.setOnItemClickListener(onItemClickListener);
		rv_fav.setAdapter(adapter);
	}

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
						.setTextSize(12)
						.setWidth(width)
						.setHeight(height);
				swipeRightMenu.addMenuItem(shareItem);// 添加一个按钮到右侧侧菜单。
				SwipeMenuItem deleteItem = new SwipeMenuItem(context)
						.setBackgroundDrawable(R.color.colorPrimaryDark)
						.setText("取消收藏") // 文字，还可以设置文字颜色，大小等。。
						.setTextColor(Color.WHITE)
						.setWidth(width)
						.setTextSize(12)
						.setHeight(height);
				swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。
			}
		}
	};

	/**
	 * Item点击监听。
	 */
	private OnItemClickListener onItemClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(int position) {
			Toast.makeText(context, "我是第" + position + "条。", Toast.LENGTH_SHORT).show();
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
				XLog.v("list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT);
				if (menuPosition == 0) {
					XToast.info("成功分享！");
				} else {
					XToast.info("已移除收藏夹");
				}


			}


			if (menuPosition == 1) {// 删除按钮被点击。
				goodses.remove(adapterPosition);
				adapter.notifyItemRemoved(adapterPosition);
			}
		}
	};
}
