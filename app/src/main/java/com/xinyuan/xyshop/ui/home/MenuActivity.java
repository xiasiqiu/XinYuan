package com.xinyuan.xyshop.ui.home;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.MenuMoreAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.MenuModel;
import com.xinyuan.xyshop.util.JsonUtil;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/3.
 */

public class MenuActivity extends BaseActivity {

	@BindView(R.id.rv_menu)
	RecyclerView rv_menu;
	MenuMoreAdapter adapter;
	@BindView(R.id.toolbar_iv)
	Toolbar toolbar_iv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@Override
	public int getLayoutId() {
		return R.layout.activity_menu;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		OkGo.get(Urls.URL_MENU)
				.execute(new StringCallback() {
					@Override
					public void onSuccess(String s, Call call, Response response) {
						MenuModel brands = JsonUtil.toBean(s, MenuModel.class);
						XLog.v(brands.toString());
						inRV(brands);
					}
				});
	}

	@Override
	public void initView() {
		tv_header_center.setText("全部菜单");
	}


	private void inRV(MenuModel brands) {
		adapter = new MenuMoreAdapter(R.layout.fragment_home_item_expandable, brands.getData());
		final GridLayoutManager manager = new GridLayoutManager(this, 4);
		rv_menu.setLayoutManager(manager);
		rv_menu.setAdapter(adapter);

	}
}
