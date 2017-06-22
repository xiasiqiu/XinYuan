package com.xinyuan.xyshop.ui.home;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.CreditAdapter;
import com.xinyuan.xyshop.adapter.CreditGoodsAdapter;
import com.xinyuan.xyshop.adapter.GoodsGridAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.entity.CreditMultipleItem;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.CreditModel;
import com.xinyuan.xyshop.model.TestCreditModel;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.xinyuan.xyshop.util.JsonUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/22.
 */

public class CreditMallActivity extends BaseActivity {


	@BindView(R.id.rv_credit)
	RecyclerView rv_credit;
	private CreditAdapter creditAdapter;


	List<CreditMultipleItem> list = new ArrayList<>();

	@Override
	public int getLayoutId() {
		return R.layout.activity_credit_mall;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		OkGo.get(Urls.URL_CREDIT)
				.execute(new StringCallback() {
					@Override
					public void onSuccess(String s, Call call, Response response) {
						TestCreditModel model = JsonUtil.toBean(s, TestCreditModel.class);
						creditModel = model.getMallData();
						getView();
					}
				});

	}

	private CreditModel creditModel;

	private void getView() {

		for (CreditModel.CreditModule module : creditModel.getMallFloors()) {
			list.add(new CreditMultipleItem(CreditMultipleItem.MODULE, CreditMultipleItem.MODULE_SPAN_SIZE));
			list.add(new CreditMultipleItem(CreditMultipleItem.AD, CreditMultipleItem.AD_SPAN_SIZE));
		}
		final GridLayoutManager manager = new GridLayoutManager(this, 2);
		rv_credit.setLayoutManager(manager);


		creditAdapter = new CreditAdapter(list, creditModel.getMallFloors());
		creditAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
			@Override
			public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {

				return list.get(position).getSpanSize();
			}
		});

		rv_credit.setAdapter(creditAdapter);
		creditAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
		addHeader();
		addFooter();

	}

	private void addHeader() {
		View headView = this.getLayoutInflater().inflate(R.layout.activity_credit_mall_top, (ViewGroup) rv_credit.getParent(), false);
		Banner banner = (Banner) headView.findViewById(R.id.banner_fx);
		ArrayList<String> images = new ArrayList<>();
		images.add("https://img.alicdn.com/imgextra/i1/46/TB2FV58xYBmpuFjSZFuXXaG_XXa_!!46-0-luban.jpg_q50.jpg");
		images.add("https://aecpm.alicdn.com/simba/img/TB14ab1KpXXXXclXFXXSutbFXXX.jpg_q50.jpg");
		images.add("https://gw.alicdn.com/imgextra/i1/99/TB2SO04hCB0XKJjSZFsXXaxfpXa_!!99-0-luban.jpg_q50.jpg");
		banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
				.setImageLoader(new GlideImageLoader())
				.setImages(images)
				.setBannerAnimation(Transformer.DepthPage)
				.isAutoPlay(true)
				.setDelayTime(3000)
				.setIndicatorGravity(BannerConfig.CENTER)
				.start();
		creditAdapter.addHeaderView(headView);
	}

	private void addFooter() {
		View footView = this.getLayoutInflater().inflate(R.layout.activity_credit_mall_item_module, (ViewGroup) rv_credit.getParent(), false);
		TextView textView = (TextView) footView.findViewById(R.id.tv_credit_module);
		textView.setText("为您推荐");
		RecyclerView rv_recom = (RecyclerView) footView.findViewById(R.id.rv_credit_content);
		CreditGoodsAdapter adapter = new CreditGoodsAdapter(R.layout.item_good_grid, creditModel.getRecommend());
		GridLayoutManager layoutManager2 = new GridLayoutManager(this, 2, 1, false);
		rv_recom.setLayoutManager(layoutManager2);
		rv_recom.setAdapter(adapter);
		creditAdapter.addFooterView(footView);
	}

	@Override
	public void initView() {


	}
}
