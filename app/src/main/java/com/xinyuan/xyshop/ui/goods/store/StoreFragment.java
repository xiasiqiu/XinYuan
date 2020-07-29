package com.xinyuan.xyshop.ui.goods.store;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.ItemTitlePagerAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.bean.StoreBean;
import com.xinyuan.xyshop.bean.StoreCouponBean;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.common.state.LoginContext;
import com.xinyuan.xyshop.even.StorePageEven;
import com.xinyuan.xyshop.mvp.contract.StoreView;
import com.xinyuan.xyshop.mvp.presenter.StorePresenter;
import com.xinyuan.xyshop.ui.goods.store.fragment.StoreActivityFragment;
import com.xinyuan.xyshop.ui.goods.store.fragment.StoreAllGoodFragment;
import com.xinyuan.xyshop.ui.goods.store.fragment.StoreHomeFragment;
import com.xinyuan.xyshop.ui.goods.store.fragment.StoreIntroduceFragment;
import com.xinyuan.xyshop.ui.goods.store.fragment.StoreNewGoodFragment;
import com.xinyuan.xyshop.ui.mine.login.LoginActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.dialog.StoreVoucherDialog;
import com.xinyuan.xyshop.widget.dialog.color.ColorDialog;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.badgeview.BGABadgeImageView;

import static com.xinyuan.xyshop.util.CommUtil.getInAnimationTest;
import static com.xinyuan.xyshop.util.CommUtil.getOutAnimationTest;

/**
 * Created by fx on 2017/10/9.
 * 店铺
 */

public class StoreFragment extends BaseFragment<StorePresenter> implements StoreView {

    @BindView(R.id.store__tabs)
    public SlidingTabLayout psts_tabs;
    @BindView(R.id.vp_content)
    public ViewPager vp_content;
    private List<Fragment> fragmentList = new ArrayList<>();
    @BindView(R.id.store_toolbar)
    Toolbar store_toolbar;

    @BindView(R.id.collapse_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.tv_store_introduce)
    TextView tv_store_introduce;
    @BindView(R.id.tv_store_voucher)
    TextView tv_store_voucher;
    @BindView(R.id.tv_store_service)
    TextView tv_store_service;


    @BindView(R.id.iv_store_bg)
    ImageView iv_store_bg;
    @BindView(R.id.iv_store_logo)
    ImageView iv_store_logo;
    @BindView(R.id.tv_store_name)
    TextView tv_store_name;
    @BindView(R.id.tv_store_fans)
    TextView tv_store_fans;
    @BindView(R.id.bt_store_fav)
    CheckBox bt_store_fav;
    @BindView(R.id.iv_store_credit)
    ImageView iv_store_credit;
    @BindView(R.id.tv_store_level)
    TextView tv_store_level;
    @BindView(R.id.goodshow_btn_back)
    ImageView goodshow_btn_back;
    @BindView(R.id.iv_header_right)
    BGABadgeImageView iv_header_right;
    @BindView(R.id.tv_store_name_title)
    TextView tv_store_name_title;
    private StoreHomeFragment homeFragment;
    private StoreAllGoodFragment allGoodFragment;
    private StoreNewGoodFragment newGoodFragment;
    private StoreActivityFragment activityFragment;
    public static List<StoreCouponBean> couponList;
    public static int state;
    public static long storeId;

    public static StoreFragment newInstance() {
        StoreFragment fragment = new StoreFragment();
        return fragment;
    }

    @Override
    protected StorePresenter createPresenter() {
        return new StorePresenter(getActivity(), this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_stores;
    }


    @Override
    public void initView(View rootView) {
        collapsingToolbar.setTitleEnabled(false);
        SystemBarHelper.immersiveStatusBar(getActivity()); //设置状态栏透明
        SystemBarHelper.setHeightAndPadding(getActivity(), store_toolbar);
        CommUtil.initToolBar(getActivity(), goodshow_btn_back, iv_header_right);
        this.storeId = Long.parseLong(getActivity().getIntent().getStringExtra(Constants.STOREID)); //店铺ID


    }

    @Override
    public void initData() {

    }

    @Override
    public void onEnterAnimationEnd(Bundle saveInstanceState) {
        fragmentList.add(homeFragment = new StoreHomeFragment());
        fragmentList.add(allGoodFragment = new StoreAllGoodFragment());
        fragmentList.add(newGoodFragment = new StoreNewGoodFragment());
        fragmentList.add(activityFragment = new StoreActivityFragment());

        vp_content.setAdapter(new ItemTitlePagerAdapter(getActivity().getSupportFragmentManager(),
                fragmentList, new String[]{"店铺首页", "全部商品", "商品上新", "店铺活动"}));
        vp_content.setOffscreenPageLimit(4);
        psts_tabs.setViewPager(vp_content);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                boolean showTitle = mCollapsingToolbar.getHeight() + verticalOffset <= mToolbar.getHeight();
                boolean showTitle = collapsingToolbar.getHeight() + verticalOffset <= store_toolbar.getHeight() * 2;
                tv_store_name_title.setVisibility(showTitle ? View.VISIBLE : View.GONE);
            }
        });

    }


    @OnClick({R.id.tv_store_introduce, R.id.tv_store_voucher, R.id.tv_store_service, R.id.bt_store_fav})
    public void onClick(View view) {
        Map<String, String> params = new HashMap();

        switch (view.getId()) {
            case R.id.tv_store_introduce:
                if (!XEmptyUtils.isEmpty(info)) {
                    start(StoreIntroduceFragment.newInstance(info));

                }
                break;
            case R.id.tv_store_voucher:
                if (LoginContext.getInstance().getCoupon(getActivity())) {
                    showSelectPromoDialog();
                }
                break;
            case R.id.tv_store_service:
                params.put(Constants.USERNAME, info.getStoreUserName());
                params.put(Constants.USERHEAD, info.getStoreUserImg());
                params.put(Constants.USERID, String.valueOf(info.getStoreUserId()));
                params.put(Constants.STOREID, String.valueOf(info.getStoreId()));
                LoginContext.getInstance().contactService(params, getActivity());
                break;
            case R.id.bt_store_fav:
                if (LoginContext.isLogin) {
                    if (info.getUserFavorites() == 0) {
                        mPresenter.FavStore(storeId);
                    } else {
                        ColorDialog colorDialog = new ColorDialog(getActivity());
                        colorDialog.setTitle("取消关注");
                        colorDialog.setContentText("确定要取消对" + info.getStoreName() + "的关注？");
                        colorDialog.setAnimationEnable(true);
                        colorDialog.setAnimationIn(getInAnimationTest(getActivity()));
                        colorDialog.setAnimationOut(getOutAnimationTest(getActivity()));
                        colorDialog.setPositiveListener("确定", new ColorDialog.OnPositiveListener() {
                            @Override
                            public void onClick(ColorDialog dialog) {
                                dialog.dismiss();
                                mPresenter.cancelFavGood(storeId);
                            }
                        })
                                .setNegativeListener("取消", new ColorDialog.OnNegativeListener() {
                                    @Override
                                    public void onClick(ColorDialog dialog) {
                                        dialog.dismiss();
                                    }
                                }).show();


                    }
                } else {
                    CommUtil.gotoActivity(getActivity(), LoginActivity.class, false, null);
                }

                break;
        }

    }


    private StoreVoucherDialog dialog;

    private void showSelectPromoDialog() {
        if (!XEmptyUtils.isEmpty(couponList)) {
            dialog = new StoreVoucherDialog(getActivity(), couponList);
            Window dialogWindow = dialog.getWindow();
            dialogWindow.setGravity(Gravity.BOTTOM);
            dialog.show();
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialogWindow.setLayout(dm.widthPixels, dialogWindow.getAttributes().height);


        } else {
            XToast.info("商家暂无优惠！");
        }

    }

    private StoreBean info = new StoreBean();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(StoreBean event) {
        if (!XEmptyUtils.isEmpty(event)) {
            this.info = event;
            GlideImageLoader.setUrlImg(getActivity(), event.getStoreBanner(), iv_store_bg);
            GlideImageLoader.setUrlImg(getActivity(), event.getStoreLogo(), iv_store_logo);
            tv_store_name.setText(event.getStoreName());
            tv_store_name_title.setText(event.getStoreName());
            tv_store_fans.setText(event.getStoreFansSum());
            if (event.getUserFavorites() == 1) {
                bt_store_fav.setText("已关注");
            }
            tv_store_level.setText(event.getStoreGradeName());
            GlideImageLoader.setImg(getActivity(), CommUtil.getStoreCredit(getActivity(), event.getStoreCredit()), iv_store_credit);

        }


    }

    @Override
    public void onResume() {
        if (MyShopApplication.IsNewMsg) {
            iv_header_right.showCirclePointBadge();
        } else {
            iv_header_right.hiddenBadge();
        }

        super.onResume();
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();

    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void setFavView(int type) {
        switch (type) {
            case 1:
                bt_store_fav.setText("关注");
                XToast.info("已取消关注");
                break;
            case 2:
                bt_store_fav.setText("已关注");
                XToast.info("已关注该店铺");
                break;
            case 3:
                XToast.error("关注失败");
                break;
        }
    }
}
