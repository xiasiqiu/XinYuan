package com.xinyuan.xyshop.ui.goods.search;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.SelectBrandAdapter;
import com.xinyuan.xyshop.adapter.SelectGoodAdapters;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.common.ShopHelper;
import com.xinyuan.xyshop.bean.GoodBradnBean;
import com.xinyuan.xyshop.bean.GoodPropertyBean;
import com.xinyuan.xyshop.even.SearchBusBean;
import com.xinyuan.xyshop.even.SearchEven;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fx on 2017/7/15.
 * 搜索筛选
 */

public class SearchGoodSelectDialog extends Dialog {
	private Context context;
	@BindView(R.id.toolbar_tv)
	Toolbar toolbar_tv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;

	@BindView(R.id.rv_select)
	RecyclerView rv_select;

	LinearLayout llBrand;
	EditText et_price_l;
	EditText et_price_u;

	private SelectGoodAdapters adapter;


	public SearchGoodSelectDialog(Context context) {
		super(context, R.style.CommonDialog);
		this.context = context;
	}


	public static List<GoodBradnBean> brandList;
	private static List<GoodPropertyBean> propertyList;
	private static List<GoodBradnBean> selectedBrandList;
	private static String xyself = "no";

	public void getData() {
		propertyList = SearchGoodShowActivity.getProperty();
		brandList = SearchGoodShowActivity.getBrand();
		initView();
	}


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EventBus.getDefault().register(this);
		setContentView(R.layout.view_select_good_select);
		ButterKnife.bind((Dialog) this);
		getWindow().setLayout((ShopHelper.getScreenWeight(this.context) / 5) * 4, -1);
		getWindow().setWindowAnimations(R.style.AnimationFade);
		getWindow().setGravity(5);
		this.tv_header_center.setText("筛选");
		getData();
	}


	private void initView() {
		XLog.list(propertyList);
		XLog.list(brandList);
		if (!XEmptyUtils.isEmpty(propertyList)) {
			adapter = new SelectGoodAdapters(R.layout.view_select_good_item, propertyList);
			LinearLayoutManager manager = new LinearLayoutManager(context);
			rv_select.setLayoutManager(manager);
			rv_select.setAdapter(adapter);
		} else {
			propertyList = new ArrayList<>();
			adapter = new SelectGoodAdapters(R.layout.view_select_good_item, propertyList);
			LinearLayoutManager manager = new LinearLayoutManager(context);
			rv_select.setLayoutManager(manager);
			rv_select.setAdapter(adapter);
		}

		getTopView();
	}

	private void getTopView() {
		this.selectedBrandList = new ArrayList();
		View view = getLayoutInflater().inflate(R.layout.view_select_good_select_top, (ViewGroup) rv_select.getParent(), false);
		llBrand = (LinearLayout) view.findViewById(R.id.llBrand);
		et_price_l = (EditText) view.findViewById(R.id.etPriceFrom);
		et_price_u = (EditText) view.findViewById(R.id.etPriceTo);
		setBrandShow();
		RadioGroup rg_good_select = (RadioGroup) view.findViewById(R.id.rg_good_select);
		rg_good_select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup radioGroup, int i) {
				switch (i) {
					case R.id.rb_select_normal:
						xyself = "no";
						break;
					case R.id.rb_select_self:
						xyself = "yes";
						break;
				}
			}
		});
		adapter.addHeaderView(view);

	}


	private void setActivated(TextView view) {
		if (view.isActivated()) {
			view.setActivated(false);
		} else {
			view.setActivated(true);
		}

	}


	private SelectBrandAdapter categoryRRecyclerAdapter;
	RecyclerView rvSort;

	private void setBrandShow() {
		if (!XEmptyUtils.isEmpty(brandList)) {
			boolean z;
			this.llBrand.removeAllViews();
			final AddViewHolder addViewHolder = new AddViewHolder(this.context, R.layout.view_select_dialog_sort_item);
			addViewHolder.setText(R.id.tvSortName, "品牌");
			rvSort = (RecyclerView) addViewHolder.getView(R.id.rvSort);
			rvSort.setLayoutManager(new GridLayoutManager(this.context, 3));
			categoryRRecyclerAdapter = new SelectBrandAdapter(R.layout.view_select_sort_recycler_item, brandList) {
				@Override
				protected void convert(BaseViewHolder helper, final GoodBradnBean item) {
					final TextView btnSpec = helper.getView(R.id.btnSpec);
					btnSpec.setText(item.getGoodsBrandsName());
					btnSpec.setActivated(selectedBrandList.contains(item));
					btnSpec.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							if (btnSpec.isActivated()) {
								btnSpec.setActivated(false);
								selectedBrandList.remove(item);
							} else if (SearchGoodSelectDialog.this.selectedBrandList.size() < 3) {
								btnSpec.setActivated(true);
								selectedBrandList.add(item);
							} else {
								XToast.info("筛选个数不能超过3个！");
							}
						}
					});
				}
			};
			categoryRRecyclerAdapter.setNewData(brandList.subList(0, this.brandList.size() > 3 ? 3 : this.brandList.size()));
			rvSort.setAdapter(categoryRRecyclerAdapter);
			categoryRRecyclerAdapter.notifyDataSetChanged();
			if (this.brandList.size() > 3) {
				z = true;
			} else {
				z = false;
			}
			addViewHolder.setVisible(R.id.ivMoreFlag, z);
			addViewHolder.setSelected(R.id.ivMoreFlag, false);
			addViewHolder.setOnClickListener(R.id.ivMoreFlag, new View.OnClickListener() {
				public void onClick(View v) {
					if (categoryRRecyclerAdapter.getData().size() == 3) {
						addViewHolder.setSelected(R.id.ivMoreFlag, true);
						categoryRRecyclerAdapter.setNewData(SearchGoodSelectDialog.this.brandList);
						categoryRRecyclerAdapter.notifyDataSetChanged();
						return;
					}
					addViewHolder.setSelected(R.id.ivMoreFlag, false);
					categoryRRecyclerAdapter.setNewData(SearchGoodSelectDialog.this.brandList.subList(0, 3));
					categoryRRecyclerAdapter.notifyDataSetChanged();
				}
			});
			this.llBrand.addView(addViewHolder.getCustomView());
		}
	}


	protected void onStart() {
		super.onStart();
	}

	private SearchBusBean searchBusBean;

	@OnClick({R.id.btnSelectRevert, R.id.btnSelectSave})
	public void OnCLick(View view) {
		switch (view.getId()) {
			case R.id.btnSelectRevert:
				selectedBrandList.clear();
				if (!XEmptyUtils.isEmpty(selectedBrandList)) {
					categoryRRecyclerAdapter.notifyDataSetChanged();
					HashMap<String, List<String>> selectValue = SelectGoodAdapters.getSelectValue();

					for (GoodPropertyBean d : propertyList) {
						if (selectValue.containsKey(d.getGoodsPropertyName())) {
							selectValue.remove(selectValue);
						}
					}

					SelectGoodAdapters.setSelectValue(selectValue);
					getData();
					adapter.setNewData(propertyList);
					adapter.notifyDataSetChanged();
					xyself = "no";
					selectedBrandList.clear();
					selectValue.clear();
					proList.clear();
					searchBusBean = new SearchBusBean();
					searchBusBean.setPrice("");
					searchBusBean.setXyself("no");
					searchBusBean.setEmpty(false);
					EventBus.getDefault().post(new SearchEven(SearchEven.SelectValue, searchBusBean));
				} else {
					searchBusBean = new SearchBusBean();
					searchBusBean.setEmpty(true);
					EventBus.getDefault().post(new SearchEven(SearchEven.EmptSelect, searchBusBean));
				}
				this.dismiss();
				haveData = false;
				break;
			case R.id.btnSelectSave:
				getPrice();
				proList.clear();
				XLog.v(SelectGoodAdapters.getSelectValue().toString());
				if (!XEmptyUtils.isEmpty(SelectGoodAdapters.getSelectValue())) {
					Iterator iterator = SelectGoodAdapters.getSelectValue().entrySet().iterator();
					while (iterator.hasNext()) {
						Map.Entry entry = (Map.Entry) iterator.next();
						List<String> list = (List<String>) entry.getValue();
						for (String s : list) {
							proList.add(s);
						}
					}
					searchBusBean = new SearchBusBean();
					searchBusBean.setPrice(getPrice());
					searchBusBean.setXyself(xyself);
					searchBusBean.setProList(proList);
					searchBusBean.setSelectedBrandList(selectedBrandList);
					searchBusBean.setEmpty(false);
					EventBus.getDefault().post(new SearchEven(SearchEven.SelectValue, searchBusBean));
				} else {
					searchBusBean = new SearchBusBean();
					searchBusBean.setPrice(getPrice());
					searchBusBean.setXyself(xyself);
					searchBusBean.setProList(proList);
					searchBusBean.setSelectedBrandList(selectedBrandList);
					searchBusBean.setEmpty(false);
					EventBus.getDefault().post(new SearchEven(SearchEven.SelectValue, searchBusBean));
				}

				this.dismiss();
				haveData = true;
				break;
		}
	}

	private String getPrice() {
		if (!XEmptyUtils.isSpace(et_price_l.getText().toString().trim()) && XEmptyUtils.isSpace(et_price_u.getText().toString().trim())) {
			return et_price_l.getText().toString().trim() + "," + et_price_u.getText().toString().trim();
		} else {
			return "";
		}
	}

	private static List<String> proList = new ArrayList<>();

	@Subscribe
	public void page(SearchBusBean searchBusBean) {

	}

	private boolean haveData = false;

	@Override
	public void dismiss() {
		if (XEmptyUtils.isEmpty(searchBusBean)) {
			EventBus.getDefault().post(new SearchEven(SearchEven.EmptSelect, null));

		} else {
			if (searchBusBean.isEmpty()) {
				EventBus.getDefault().post(new SearchEven(SearchEven.EmptSelect, null));
			}
		}

		super.dismiss();
	}
}
