package com.xinyuan.xyshop.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.GoodsAttrsAdapter;
import com.xinyuan.xyshop.callback.SKUInterface;
import com.xinyuan.xyshop.model.GoodsAttrsBean;
import com.youth.xframe.utils.log.XLog;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/5/25.
 */

public class GoodDetailsSpecDialog extends Dialog implements SKUInterface {


	private Context context;
	private GoodsAttrsBean dataBean;

	private GoodsAttrsAdapter mAdapter;

	@BindView(R.id.rv_spec)
	RecyclerView rv_spec;
	@BindView(R.id.tvSelectedType)
	TextView tvSelectedType;
	@BindView(R.id.tvSkuStorage)

	TextView tvSkuStorage;

	public GoodDetailsSpecDialog(Context context) {
		super(context, R.style.CommonDialog);
		this.context = context;
		//this.goodsSpecModel = goodsSpecModel;
	}


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_good_dialog_spec);
		ButterKnife.bind((Dialog) this);
		Gson gson = new Gson();
		dataBean = gson.fromJson(context.getResources().getString(R.string.jsonData), GoodsAttrsBean.class);
		XLog.v("dataBean" + dataBean.toString());
		install();

	}

	private void install() {
		mAdapter = new GoodsAttrsAdapter(context, dataBean.getAttributes(), dataBean.getStockGoods());
		LinearLayoutManager layoutManager = new LinearLayoutManager(context);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		rv_spec.setLayoutManager(layoutManager);
		rv_spec.setFocusable(false);
		mAdapter.setSKUInterface(this);
		rv_spec.setAdapter(mAdapter);
	}

	@Override
	public void selectedAttribute(String[] attr) {
		String str = "";
		String ss = "";
		for (int i = 0; i < attr.length; i++) {
			str += " " + dataBean.getAttributes().get(i).getTabName() + "：";
			ss = TextUtils.isEmpty(attr[i]) ? "" : attr[i];
			str += ss + " ";
		}
		tvSelectedType.setText("已选:" + str);
	}

	@Override
	public void uncheckAttribute(String[] attr) {
		String str = "";
		String ss = "";
		for (int i = 0; i < attr.length; i++) {
			str += " " + dataBean.getAttributes().get(i).getTabName() + "：";
			ss = TextUtils.isEmpty(attr[i]) ? "" : attr[i];
			str += ss + " ";
		}
		tvSelectedType.setText("已选:" + str);

	}
}
