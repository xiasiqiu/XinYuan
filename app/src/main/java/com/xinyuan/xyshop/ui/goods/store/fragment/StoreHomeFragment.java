package com.xinyuan.xyshop.ui.goods.store.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SlidingTabLayout;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.HomeGoodsAdapter;
import com.xinyuan.xyshop.adapter.ItemTitlePagerAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.bean.GoodListItemBean;
import com.xinyuan.xyshop.bean.StoreBean;
import com.xinyuan.xyshop.bean.StoreCouponBean;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.model.StoreHomeModel;
import com.xinyuan.xyshop.mvp.contract.StoreHomeView;
import com.xinyuan.xyshop.mvp.presenter.StoreHomePresenter;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;
import com.xinyuan.xyshop.ui.goods.store.StoreFragment;
import com.xinyuan.xyshop.ui.home.WebViewActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.widget.NoScrollViewPager;
import com.youth.xframe.widget.loadingview.XLoadingView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by fx on 2017/6/1.
 * 店铺首页fragment
 */

public class StoreHomeFragment extends BaseFragment<StoreHomePresenter> implements StoreHomeView {

    @BindView(R.id.rv_store_home)
    RecyclerView rv_store_home;
    @BindView(R.id.lodingView)
    XLoadingView lodingView;
    private List<Fragment> fragmentList = new ArrayList<>();
    private HomeGoodsAdapter adapter;
    private static boolean VIEW_INIT = true;
    private StoreBean storeInfoBean;
    private View topView;
    private ImageView storeTopAd;
    private NoScrollViewPager vp_content;
    private SlidingTabLayout homeTab;

    @Override
    public void initView(View rootView) {
    }

    @Override
    public void initData() {
    }

    @Override
    public void onEnterAnimationEnd(Bundle saveInstanceState) {
        mPresenter.getData(StoreFragment.storeId);
        topView = getActivity().getLayoutInflater().inflate(R.layout.fragment_store_top, (ViewGroup) rv_store_home.getParent(), false);
        storeTopAd = (ImageView) topView.findViewById(R.id.iv_store_ad);
        vp_content = (NoScrollViewPager) topView.findViewById(R.id.viewpage_store);
        homeTab = (SlidingTabLayout) topView.findViewById(R.id.store__home_tabs);
        storeTopAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void showStoreInfo(StoreBean storeInfoBean) {
        this.storeInfoBean = storeInfoBean;
        EventBus.getDefault().post(storeInfoBean);
    }

    @Override
    public void showColl(ItemData ad, List<StoreHomeModel.CollGood> collGoodList, List<StoreHomeModel.CollGood> favGoodList) {

        GlideImageLoader.setUrlImg(getContext(), ad.getImageUrl(), storeTopAd);
        storeTopAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> params = new HashMap();
                params.put(Constants.URL, ad.getData());
                CommUtil.gotoActivity(getActivity(), WebViewActivity.class, false, params);
            }
        });
        fragmentList.add(StoreSellFragment.newInstance("收藏", collGoodList, favGoodList));
        fragmentList.add(StoreSellFragment.newInstance("销量", collGoodList, favGoodList));
        vp_content.setAdapter(new ItemTitlePagerAdapter(getChildFragmentManager(),
                fragmentList, new String[]{"收藏排行", "销量排行"}));
        vp_content.setOffscreenPageLimit(2);
        vp_content.setCurrentItem(0);
        homeTab.setViewPager(vp_content);
        adapter.addHeaderView(topView);
    }

    @Override
    public void showRecom(final List<GoodListItemBean> recomList) {
        GridLayoutManager layoutManager2 = new GridLayoutManager(mContext, 2);
        this.rv_store_home.setLayoutManager(layoutManager2);
        this.adapter = new HomeGoodsAdapter(R.layout.item_good_grid, recomList);
        this.rv_store_home.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Map<String, String> params = new HashMap();
                params.put(Constants.GOODID, recomList.get(position).getGoodsId());
                params.put(Constants.GOODTYPE, recomList.get(position).getGoodsType());
                CommUtil.gotoActivity(getActivity(), GoodDetailActivity.class, false, params);
            }
        });
        showState(1);
    }

    @Override
    public void showCoupon(List<StoreCouponBean> couponBeanList) {
        StoreFragment.couponList = couponBeanList;
    }

    @Override
    public LifecycleTransformer<StoreHomeModel> bindLife() {
        return this.<StoreHomeModel>bindUntilEvent(FragmentEvent.DESTROY_VIEW);
    }


    @Override
    protected StoreHomePresenter createPresenter() {
        return new StoreHomePresenter(getActivity(), this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_store_home;

    }

    @Override
    public void showState(int Sate) {
        switch (Sate) {
            case 0:
                lodingView.showLoading();
                break;
            case 1:
                lodingView.showContent();
                break;
            case 2:
                lodingView.showError();
                break;
        }
    }


}
