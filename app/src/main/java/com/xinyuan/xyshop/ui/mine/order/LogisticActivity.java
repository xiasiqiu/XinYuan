package com.xinyuan.xyshop.ui.mine.order;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.HomeMultipleItemAdapter;
import com.xinyuan.xyshop.adapter.LogisticAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.entity.BrandList;
import com.xinyuan.xyshop.entity.ExpressBean;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.util.JsonUtil;
import com.youth.xframe.utils.log.XLog;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/3.
 */

public class LogisticActivity extends BaseActivity {
	@BindView(R.id.rv_logis)
	RecyclerView rv_logis;
	private LogisticAdapter adapter;


	@BindView(R.id.toolbar_iv)
	Toolbar toolbar_iv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;

	@Override
	public int getLayoutId() {
		return R.layout.activity_logistic;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		OkGo.get(Urls.URL_EXPRESS)
				.execute(new StringCallback() {
					@Override
					public void onSuccess(String s, Call call, Response response) {
						ExpressBean expressBean = JsonUtil.toBean(s, ExpressBean.class);
						XLog.v(expressBean.toString());
						inRV(expressBean);
					}

					@Override
					public void onError(Call call, Response response, Exception e) {

					}
				});

	}

	private void inRV(ExpressBean expressBean) {
		rv_logis.setHasFixedSize(true);
		rv_logis.setLayoutManager(new LinearLayoutManager(this));
		adapter = new LogisticAdapter(R.layout.activity_logistic_item, expressBean.getData());
		rv_logis.setAdapter(adapter);


		if (true) {
			adapter.setEmptyView(R.layout.activity_logistic_empty, (ViewGroup) rv_logis.getParent());
		}

		View headView = getLayoutInflater().inflate(R.layout.activity_logistic_top, (ViewGroup) rv_logis.getParent(), false);
		TextView tv_express_status = (TextView) headView.findViewById(R.id.tv_express_status);
		TextView tv_express_name = (TextView) headView.findViewById(R.id.tv_express_name);
		TextView tv_order_id = (TextView) headView.findViewById(R.id.tv_order_id);

		TextView tv_express_phone = (TextView) headView.findViewById(R.id.tv_express_phone);
		tv_order_id.setText(expressBean.getNu());
		tv_express_name.setText(expressBean.getCom());
		tv_express_phone.setText(expressBean.getPhone());
		switch (Integer.parseInt(expressBean.getState())) {
			case 0:
				tv_express_status.setText("运输中");
				break;
			case 1:
				tv_express_status.setText("已揽件");
				break;
			case 2:
				tv_express_status.setText("疑难件");
				break;
			case 3:
				tv_express_status.setText("已签收");
				break;
			case 4:
				tv_express_status.setText("已退签");
				break;
			case 5:
				tv_express_status.setText("已派件");
				break;
			case 6:
				tv_express_status.setText("已退回");
				break;
		}
		adapter.addHeaderView(headView);


	}

	@Override
	public void initView() {
		tv_header_center.setText("物流查询");
	}
}
