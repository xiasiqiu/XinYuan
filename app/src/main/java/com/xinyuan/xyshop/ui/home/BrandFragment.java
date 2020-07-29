package com.xinyuan.xyshop.ui.home;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.BrandAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.BrandBean;
import com.xinyuan.xyshop.callback.JsonConvert;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.entity.MySection;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.BrandModel;
import com.xinyuan.xyshop.ui.goods.store.StoreActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.WaveSideBarView;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by fx on 2017/6/28.
 * 品牌列表Fragment
 */

public class BrandFragment extends BaseFragment {
	@BindView(R.id.toolbar_iv)
	Toolbar toolbar_iv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.brand_loadingView)
	XLoadingView loadingView;
	@BindView(R.id.rv_brand)
	RecyclerView rv_brand;
	@BindView(R.id.side_view)
	WaveSideBarView mSideBarView;
	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	@BindView(R.id.iv_header_right)
	ImageView iv_header_right;


	public static BrandFragment newInstance() {
		BrandFragment fragment = new BrandFragment();
		return fragment;
	}

	protected int provideContentViewId() {
		return R.layout.fragment_brand;
	}


	@Override
	public void initView(View rootView) {
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_iv);
		tv_header_center.setText(R.string.brandtitle);
		CommUtil.initToolBar(_mActivity, mContext, iv_header_left, iv_header_right);

	}

	public void initListener() {
		loadingView.setOnRetryClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				initData();
			}
		});
	}

	private void showBandList(BrandModel brandModel) {
		if (XEmptyUtils.isEmpty(brandModel)) {
			loadingView.showEmpty();
		} else {
			List<MySection> lists = new ArrayList<>();  //分组头部
			for (int i = 0; i < brandModel.getStoreSortList().size(); i++) {
				if (!XEmptyUtils.isEmpty(brandModel.getStoreSortList().get(i).getList())) {
					lists.add(new MySection(true, brandModel.getStoreSortList().get(i).getBrandInitial(), false));
					for (int j = 0; j < brandModel.getStoreSortList().get(i).getList().size(); j++) {
						lists.add(new MySection(brandModel.getStoreSortList().get(i).getList().get(j)));
					}
				}
			}
			for (BrandBean brandBean : brandModel.getStoreRecommendList()) {
				lists.add(0, new MySection(brandBean));
			}
			lists.add(0, new MySection(true, getString(R.string.brandrecomm), false));
			rv_brand.setLayoutManager(new GridLayoutManager(mContext, 2));
			final BrandAdapter brandAdapter = new BrandAdapter(R.layout.fragment_brand_item, R.layout.fragment_brand_item_header, lists);
			rv_brand.setAdapter(brandAdapter);

			mSideBarView.setOnTouchLetterChangeListener(new WaveSideBarView.OnTouchLetterChangeListener() {
				@Override
				public void onLetterChange(String letter) {
					int pos = brandAdapter.getLetterPosition(letter);
					if (pos != -1) {
						rv_brand.scrollToPosition(pos);
						LinearLayoutManager mLayoutManager = (LinearLayoutManager) rv_brand.getLayoutManager();
						mLayoutManager.scrollToPositionWithOffset(pos, 0);
					}
				}
			});
		}
		brandModel = null;

	}


	@Override
	public void initData() {
		OkGo.<LzyResponse<BrandModel>>get(Urls.URL_BRAND)
				.converter(new JsonConvert<LzyResponse<BrandModel>>() {
				})
				.adapt(new ObservableBody<LzyResponse<BrandModel>>())
				.subscribeOn(Schedulers.io())//
				.doOnSubscribe(new Consumer<Disposable>() {
					@Override
					public void accept(@NonNull Disposable disposable) throws Exception {
					}
				})
				.map(new Function<LzyResponse<BrandModel>, BrandModel>() {
					@Override
					public BrandModel apply(@NonNull LzyResponse<BrandModel> response) throws Exception {
						if (HttpUtil.handleResponse(mContext, response)) {
							if (XEmptyUtils.isEmpty(response.datas)) {
								BrandModel module = new BrandModel();
								return module;
							} else {
								return response.datas;
							}
						} else {
							BrandModel module = new BrandModel();
							return module;
						}

					}
				})
				.observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
				.compose(bindLife())
				.subscribe(new Observer<BrandModel>() {
					@Override
					public void onSubscribe(@NonNull Disposable d) {

					}

					@Override
					public void onNext(@NonNull BrandModel brandModel) {
						showBandList(brandModel);
					}

					@Override
					public void onError(@NonNull Throwable e) {
						e.printStackTrace();
						loadingView.showError();
					}

					@Override
					public void onComplete() {
						loadingView.showContent();

					}
				});
	}


	public LifecycleTransformer<BrandModel> bindLife() {
		return this.<BrandModel>bindUntilEvent(FragmentEvent.DESTROY_VIEW);
	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

}
