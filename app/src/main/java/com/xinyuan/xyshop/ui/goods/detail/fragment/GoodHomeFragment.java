package com.xinyuan.xyshop.ui.goods.detail.fragment;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.SimpleEvaluateAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.bean.AddCartBean;
import com.xinyuan.xyshop.bean.BuyDataBean;
import com.xinyuan.xyshop.bean.CartGoodBean;
import com.xinyuan.xyshop.bean.ContanctBean;
import com.xinyuan.xyshop.bean.GoodEvaBean;
import com.xinyuan.xyshop.bean.OrderOnGoodBean;
import com.xinyuan.xyshop.bean.StoreInfoBean;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.common.ShopHelper;
import com.xinyuan.xyshop.entity.PreGoods;
import com.xinyuan.xyshop.even.GoodBusEven;
import com.xinyuan.xyshop.even.GoodServiceEvent;
import com.xinyuan.xyshop.model.GoodDetailModels;
import com.xinyuan.xyshop.model.GoodsAttrsBean;
import com.xinyuan.xyshop.mvp.contract.GoodHomeView;
import com.xinyuan.xyshop.mvp.presenter.GoodHomePresenter;
import com.xinyuan.xyshop.ui.buy.ConfirmOrderActivity;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;
import com.xinyuan.xyshop.ui.goods.store.StoreActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.widget.SlideDetailsLayout;
import com.xinyuan.xyshop.widget.dialog.GoodDetailsSpecDialog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fx on 2017/8/7.
 * 商品详情主页
 */

public class GoodHomeFragment extends BaseFragment<GoodHomePresenter> implements GoodHomeView, SlideDetailsLayout.OnSlideDetailsListener {

    @BindView(R.id.sv_switch)
    SlideDetailsLayout sv_switch; //详情滑动切换
    @BindView(R.id.sv_goods_info)
    ScrollView sv_goods_info; //长view
    @BindView(R.id.goodinfo_banner)
    Banner banner; //顶部banner
    @BindView(R.id.goodinfo_share)
    ImageView iv_share; //分享按钮
    @BindView(R.id.ll_store_sign)
    LinearLayout ll_store_sign;
    @BindView(R.id.tv_current_goods)
    TextView tv_current_goods; //请选择产品规格文字
    @BindView(R.id.tv_goods_location)
    TextView tv_goods_location;
    @BindView(R.id.tv_new_price)
    TextView tv_newprice; //商品最新价格
    @BindView(R.id.tv_old_price)
    TextView tv_oldprice; //商品旧价格
    @BindView(R.id.tv_goods_postage)
    TextView tv_goodspostage; //商品快递费用
    @BindView(R.id.tv_goods_sellnum)
    TextView tv_goodssellnum; //商品销量
    @BindView(R.id.tv_goods_talk)
    TextView tv_goodstalk; //商品评论
    @BindView(R.id.fl_stoer_sign)
    FlexboxLayout flexBoxLayout;
    @BindView(R.id.ll_current_goods)
    RelativeLayout ll_current; //规格型号布局
    @BindView(R.id.ll_comment)
    RelativeLayout ll_comment;//评论布局
    @BindView(R.id.rvEvaluate)
    RecyclerView rvEvaluate;
    @BindView(R.id.bt_good_store)
    Button bt_store; //店铺按钮
    @BindView(R.id.fab_up_slide)
    FloatingActionButton fab_up_slide;
    @BindView(R.id.iv_good_store_img)
    ImageView srote_img;
    @BindView(R.id.tv_good_store_name)
    TextView tv_good_storename;
    @BindView(R.id.tvStoreDescPoint)
    TextView tv_StoreDescPoint;
    @BindView(R.id.tvStoreServicePoint)
    TextView tv_StoreServicePoint;
    @BindView(R.id.tvStoreDeliveryPoint)
    TextView tv_StoreDeliveryPoint;
    @BindView(R.id.ll_pull_up)
    LinearLayout ll_pull_up;    //上拉加载详情
    @BindView(R.id.ll_goods_detail)
    LinearLayout ll_goods_detail;//商品详情tab-详情
    @BindView(R.id.ll_goods_config)
    LinearLayout ll_goods_config;    //商品详情tab-规格
    @BindView(R.id.ll_goods_service)
    LinearLayout ll_goods_service;    //商品详情tab-售后
    @BindView(R.id.tv_goods_detail)
    TextView tv_goods_detail;//商品详情tab-详情文字
    @BindView(R.id.tv_goods_config)
    TextView tv_goods_config;    //商品详情tab-规格文字
    @BindView(R.id.tv_goods_service)
    TextView tv_goods_service;    //商品详情tab-售后文字
    @BindView(R.id.tv_store_level)
    TextView tv_store_level;
    @BindView(R.id.iv_store_grade)
    ImageView iv_store_grade;
    @BindView(R.id.fl_content)
    FrameLayout fl_content;
    @BindView(R.id.tv_comment_count)
    TextView tv_comment_count;
    @BindView(R.id.rl_group_info)
    RelativeLayout rl_group_info;
    @BindView(R.id.tv_group_name)
    TextView tv_group_name;
    @BindView(R.id.lodingView)
    XLoadingView lodingView;
    @BindView(R.id.tv_good_active)
    TextView tv_good_active;
    @BindView(R.id.tv_good_second)
    TextView tv_good_second;
    @BindView(R.id.tv_good_min)
    TextView tv_good_min;
    @BindView(R.id.tv_good_hour)
    TextView tv_good_hour;
    @BindView(R.id.tv_good_day)
    TextView tv_good_day;
    Fragment nowFragment;
    GoodsDetailFragment goodsDetailFragment;
    GoodsConfigFragment goodsConfigFragment;
    GoodsServiceFragment goodsServiceFragment;

    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private List<Fragment> fragmentList = new ArrayList<>();

    private List<TextView> tabTextList;
    public GoodDetailActivity activity;
    public GoodsDetailWebFragment goodsInfoWebFragment;

    private HashMap<Integer, BuyDataBean> buydatas;
    private HashMap<Integer, PreGoods> preGoodsMap;
    private int allGoodsNum;
    private GoodDetailModels detailModel;
    SimpleEvaluateAdapter simpleEvaluateAdapter;

    private static SlideDetailsLayout.Status status;
    private static String unit;
    public static int goodsInventory;

    private static long mDay;
    private static long mHour;
    private static long mMin;
    private static long mSecond;
    private boolean isRun = true;
    private int goodsType; //商品类型
    private int goodsActive; //商品活动

    private GoodsAttrsBean.StockGoodsBean chosesGood;

    @Override
    public void initView(View rootView) {
        sv_switch.setOnSlideDetailsListener(this);
        fab_up_slide.hide();
        rootView.setFocusable(true);
        rootView.setFocusableInTouchMode(true);
    }

    @Override
    public boolean onBackPressedSupport() {
        if (status == SlideDetailsLayout.Status.OPEN) { //当处于详情页展开状态时，点击返回上滑至顶部
            sv_goods_info.smoothScrollTo(0, 0);
            sv_switch.smoothClose(true);
            status = SlideDetailsLayout.Status.CLOSE;
            return true;
        } else {
            return super.onBackPressedSupport();
        }
    }

    @Override
    public void onEnterAnimationEnd(Bundle saveInstanceState) {
        mPresenter.getData(GoodDetailActivity.goodsId);
        fragmentList = new ArrayList<>();
        tabTextList = new ArrayList<>();
        tabTextList.add(tv_goods_detail);
        tabTextList.add(tv_goods_config);
        tabTextList.add(tv_goods_service);
        goodsServiceFragment = new GoodsServiceFragment();
        goodsConfigFragment = new GoodsConfigFragment();
        goodsDetailFragment = new GoodsDetailFragment();
        goodsInfoWebFragment = new GoodsDetailWebFragment();
        fragmentList.add(goodsConfigFragment);
        fragmentList.add(goodsServiceFragment);
        fragmentList.add(goodsDetailFragment);
        fragmentList.add(goodsInfoWebFragment);
        nowFragment = goodsInfoWebFragment;
        fragmentManager = getChildFragmentManager();

        //默认显示商品详情tab
        fragmentManager.beginTransaction().replace(R.id.fl_content, nowFragment).commitAllowingStateLoss();
        tv_goods_detail.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        lodingView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getData(GoodDetailActivity.goodsId);
            }
        });


    }

    @Override
    protected GoodHomePresenter createPresenter() {
        return new GoodHomePresenter(getActivity(), this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_good_info;
    }

    @Override
    public void showView(GoodDetailModels model) {
        this.detailModel = model;
        showBanner();
        showGoodsInfo();
        showEva();
        showStoreInfo();
        showWeb();
        //向评论列表fragment发送数量
        EventBus.getDefault().post(new GoodBusEven(GoodBusEven.GoodEvaluate, model.getEvaluate(), goodsType));
        showState(1);

    }

    private static ArrayList<String> titles; //banner名字
    private static ArrayList<String> images; //banner图片

    @Override
    public void showBanner() {
        titles = new ArrayList<>();
        images = new ArrayList<>();
        for (GoodDetailModels.GoodBanner banner : detailModel.getGoodsBanner()) {
            titles.add(banner.getGoodsTxt());
            images.add(CommUtil.getUrl(banner.getGoodsImage()));
        }
        GoodDetailActivity.goodImg = images.get(0);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                .setImageLoader(new GlideImageLoader())
                .setImages(images)
                .setBannerTitles(titles)
                .setBannerAnimation(Transformer.DepthPage)
                .isAutoPlay(true)
                .setDelayTime(3000)
                .setIndicatorGravity(BannerConfig.CENTER)
                .start();
        GoodDetailActivity.userName = detailModel.getStore().getStoreUserName();
        GoodDetailActivity.userId = String.valueOf(detailModel.getStore().getStoreUserId());
        GoodDetailActivity.userHeadImg = detailModel.getStore().getStoreUserImg();


    }


    @Override
    public void showGoodsInfo() {
        //type:1:实物商品 2:虚拟商品 /3/团购商品 4积分商品
        goodsInventory = detailModel.getGoodsInventory();
        GoodDetailActivity.goodName = detailModel.getGoodsName();
        goodsType = detailModel.getGoodsType();
        if (!XEmptyUtils.isEmpty(detailModel.getGoodsFavorites()) && detailModel.getGoodsFavorites() == 1) {
            GoodDetailActivity.isFav = true;
        } else {
            GoodDetailActivity.isFav = false;
        }
        EventBus.getDefault().post(new GoodBusEven(GoodBusEven.goodFav));
        if (XEmptyUtils.isEmpty(detailModel.getGoodSpec().getDefaultGood().getGoodsPrice())) {
            GoodDetailActivity.goodPrice = detailModel.getGoodsPrice();
            GoodDetailActivity.goodSpecText = "无规格";
        } else {
            GoodDetailActivity.goodPrice = detailModel.getGoodSpec().getDefaultGood().getGoodsPrice();
            GoodDetailActivity.goodSpecText = detailModel.getGoodSpec().getDefaultGood().getGoodSpecText();
        }


        if (XEmptyUtils.isEmpty(detailModel.getGoodSpec().getAttributes())) { //如果商品没有其他规格，则隐藏规格弹窗
            ll_current.setVisibility(View.GONE);
            GoodDetailActivity.haveSpec = false;
        } else {
            GoodDetailActivity.haveSpec = true;
            tv_current_goods.setText("已选择" + detailModel.getGoodSpec().getDefaultGood().getSpecText());
            chosesGood = detailModel.getGoodSpec().getDefaultGood();
            XLog.v("是否有默认的-" + XEmptyUtils.isEmpty(chosesGood));

        }


        switch (goodsType) {
            case 1:
                tv_newprice.setText("￥" + detailModel.getGoodsPrice());
                tv_oldprice.setText("￥" + detailModel.getGoodsOriginalPrice());
                tv_oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                if (detailModel.getGoodsTransfee() == 1) {
                    tv_goodspostage.setText("快递:免邮费");
                } else {
                    tv_goodspostage.setVisibility(View.GONE);
                }
                tv_goodssellnum.setText("月销量:" + detailModel.getConsumeNum() + "笔");
                tv_goodstalk.setText("评论:" + detailModel.getEvaluate().getGoodsEvaluates() + "条");
                tv_goods_location.setText(detailModel.getGoodsAddress());

                if (!XEmptyUtils.isEmpty(detailModel.getStoreSecurity())) {
                    for (String type : detailModel.getStoreSecurity()) {
                        AddViewHolder addViewHolder = new AddViewHolder(mContext, R.layout.view_good_sign);
                        addViewHolder.setText(R.id.tv_sign_type, type);
                        flexBoxLayout.addView(addViewHolder.getCustomView());
                    }
                } else {
                    ll_store_sign.setVisibility(View.GONE);
                }
                if (!XEmptyUtils.isEmpty(detailModel.getGoodsActivity().getGroupTime())) {
                    rl_group_info.setVisibility(View.VISIBLE);
                    tv_group_name.setText(detailModel.getGoodsActivity().getGroupName());
                    String[] time = detailModel.getGoodsActivity().getGroupTime().split("-");
                    mDay = Long.parseLong(time[0]);
                    mHour = Long.parseLong(time[1]);
                    mMin = Long.parseLong(time[2]);
                    mSecond = Long.parseLong(time[3]);
                    tv_good_day.setText(time[0]);
                    tv_good_hour.setText(time[1]);
                    tv_good_min.setText(time[2]);
                    tv_good_second.setText(time[3]);
                    startRun();
                }
                break;
            case 2:
                tv_goods_location.setVisibility(View.GONE);
                ll_current.setVisibility(View.GONE);
                tv_good_active.setVisibility(View.VISIBLE);
                tv_goodspostage.setVisibility(View.GONE);
                flexBoxLayout.setVisibility(View.GONE);
                tv_newprice.setText("￥" + detailModel.getGoodsPrice());
                tv_oldprice.setText("￥" + detailModel.getGoodsOriginalPrice());
                tv_oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                tv_goodssellnum.setText(mContext.getString(R.string.month_sell_num) + detailModel.getConsumeNum() + "笔");
                tv_goodstalk.setText(mContext.getString(R.string.text_eva) + detailModel.getEvaluate().getGoodsEvaluates());
                break;
            case 3:
                tv_group_name.setText(detailModel.getGoodsActivity().getGroupName());
                if (!XEmptyUtils.isEmpty(detailModel.getGoodsActivity().getGroupTime())) {
                    String[] time = detailModel.getGoodsActivity().getGroupTime().split("-");
                    mDay = Long.parseLong(time[0]);
                    mHour = Long.parseLong(time[1]);
                    mMin = Long.parseLong(time[2]);
                    mSecond = Long.parseLong(time[3]);
                    tv_good_day.setText(time[0]);
                    tv_good_hour.setText(time[1]);
                    tv_good_min.setText(time[2]);
                    tv_good_second.setText(time[3]);
                }

                rl_group_info.setVisibility(View.VISIBLE);
                startRun();
                tv_newprice.setText("￥" + detailModel.getGoodsPrice());
                tv_oldprice.setText("￥" + detailModel.getGoodsOriginalPrice());
                tv_oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                if (detailModel.getGoodsTransfee() == 1) {
                    tv_goodspostage.setText("快递:免邮费");
                } else {
                    tv_goodspostage.setVisibility(View.GONE);
                }
                tv_goodssellnum.setText("月销量:" + detailModel.getConsumeNum() + "笔");
                tv_goodstalk.setText("评论:" + detailModel.getEvaluate().getGoodsEvaluates() + "条");
                tv_goods_location.setText(detailModel.getGoodsAddress());

                if (!XEmptyUtils.isEmpty(detailModel.getStoreSecurity())) {
                    for (String type : detailModel.getStoreSecurity()) {
                        AddViewHolder addViewHolder = new AddViewHolder(mContext, R.layout.view_good_sign);
                        addViewHolder.setText(R.id.tv_sign_type, type);
                        flexBoxLayout.addView(addViewHolder.getCustomView());
                    }
                } else {
                    ll_store_sign.setVisibility(View.GONE);
                }
                break;
            case 4:
                tv_goods_location.setVisibility(View.GONE);
                ll_current.setVisibility(View.GONE);
                tv_good_active.setVisibility(View.VISIBLE);
                tv_good_active.setText("积分兑换");
                tv_goodspostage.setVisibility(View.GONE);
                flexBoxLayout.setVisibility(View.GONE);

                tv_newprice.setText(detailModel.getGoodsPrice() + "积分");
                tv_oldprice.setText(detailModel.getGoodsOriginalPrice() + "积分");
                tv_oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                tv_goodssellnum.setText(mContext.getString(R.string.month_sell_num) + detailModel.getConsumeNum() + "笔");
                tv_goodstalk.setText(mContext.getString(R.string.text_eva) + detailModel.getEvaluate().getGoodsEvaluates());
                break;

        }


    }


    @Override
    public void showEva() {
        tv_comment_count.setText("评价(" + detailModel.getEvaluate().getGoodsEvaluates() + ")");
        List<GoodEvaBean> data = new ArrayList<>();
        data = detailModel.getEvaluateList();
        this.simpleEvaluateAdapter = new SimpleEvaluateAdapter(R.layout.fragment_good_item_evaluate, data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rvEvaluate.setNestedScrollingEnabled(false);
        //设置布局管理器
        rvEvaluate.setLayoutManager(linearLayoutManager);
        this.rvEvaluate.setAdapter(this.simpleEvaluateAdapter);
        this.simpleEvaluateAdapter.notifyDataSetChanged();


    }

    @Override
    public void showStoreInfo() {
        EventBus.getDefault().post(new GoodBusEven(GoodBusEven.contanctSercvice, new ContanctBean(detailModel.getStore().getStoreUserName(), String.valueOf(detailModel.getStore().getStoreUserId()), detailModel.getStore().getStoreUserImg())));
        EventBus.getDefault().post(new GoodBusEven(GoodBusEven.GoodsStoreId, String.valueOf(detailModel.getStore().getStoreId())));
        GlideImageLoader.setUrlImg(mContext, detailModel.getStore().getStoreLogo(), srote_img);
        GoodDetailActivity.storeId = String.valueOf(detailModel.getStore().getStoreId());
        tv_good_storename.setText(detailModel.getStore().getStoreName());
        tv_StoreDescPoint.setText(String.valueOf(detailModel.getStore().getLogistics_score()));
        tv_StoreServicePoint.setText(String.valueOf(detailModel.getStore().getEvaluation_score()));
        tv_StoreDeliveryPoint.setText(String.valueOf(detailModel.getStore().getService_score()));
        tv_store_level.setText(detailModel.getStore().getStoreGradeName());
        GlideImageLoader.setImg(mContext, CommUtil.getStoreCredit(mContext, detailModel.getStore().getStoreCredit()), iv_store_grade);

    }

    @Override
    public void showWeb() {

        EventBus.getDefault().post(detailModel.getGoodsDetails());
        EventBus.getDefault().postSticky(new GoodServiceEvent(detailModel.getGoodsAfterSalesText(), detailModel.getGoodsAfterSalesImg()));
        EventBus.getDefault().postSticky(detailModel.getSpecParams());

    }

    @Override
    public void goToBuy(Long cartId) {
        List<StoreInfoBean> storeList = new ArrayList<>();
        StoreInfoBean store = new StoreInfoBean();
        store.setStoreId(detailModel.getStore().getStoreId());
        store.setStoreName(detailModel.getStore().getStoreName());
        List<CartGoodBean> goods = new ArrayList<>();
        CartGoodBean good = new CartGoodBean();
        good.setGoodsCount(GoodDetailActivity.goodNum);
        good.setGoodsImg(detailModel.getGoodsBanner().get(0).getGoodsImage());
        good.setGoodsName(detailModel.getGoodsName());
        good.setGoodsPrice(detailModel.getGoodsPrice());
        if (XEmptyUtils.isEmpty(chosesGood)) {
            good.setGoodsSpecText("无");

        } else {
            good.setGoodsSpecText(chosesGood.getSpecText());

        }
        good.setGoodsCartId(cartId);
        goods.add(good);
        store.setGoodsCartList(goods);
        storeList.add(store);
        EventBus.getDefault().postSticky(storeList);
        Map<String, String> params = new HashMap();
        params.put(Constants.GOODTYPE, String.valueOf(detailModel.getGoodsType()));
        CommUtil.gotoActivity(getActivity(), ConfirmOrderActivity.class, false, params);

    }


    @Override
    public void addCartBack(AddCartBean tokenBean) {
        if (!XEmptyUtils.isEmpty(tokenBean.getMsg())) {
            XToast.info(tokenBean.getMsg());
        } else {
            XLog.v("加入购物车失败");
        }
    }


    @OnClick({R.id.ll_goods_config, R.id.ll_goods_service, R.id.ll_goods_detail, R.id.fab_up_slide, R.id.ll_comment, R.id.bt_good_store, R.id.goodinfo_share})
    public void onClick(View view) {
        Map<String, String> params = new HashMap();

        switch (view.getId()) {

            case R.id.ll_pull_up:
                sv_switch.smoothOpen(true);
                break;
            case R.id.ll_goods_detail:
                switchFragment(nowFragment, goodsInfoWebFragment);
                nowFragment = goodsDetailFragment;
                tv_goods_detail.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv_goods_config.setTextColor(getResources().getColor(R.color.tv_hint));
                tv_goods_service.setTextColor(getResources().getColor(R.color.tv_hint));
                break;
            case R.id.ll_goods_config:
                switchFragment(nowFragment, goodsConfigFragment);
                nowFragment = goodsConfigFragment;
                tv_goods_detail.setTextColor(getResources().getColor(R.color.tv_hint));
                tv_goods_config.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv_goods_service.setTextColor(getResources().getColor(R.color.tv_hint));
                break;
            case R.id.ll_goods_service:
                switchFragment(nowFragment, goodsServiceFragment);
                nowFragment = goodsServiceFragment;
                tv_goods_detail.setTextColor(getResources().getColor(R.color.tv_hint));
                tv_goods_config.setTextColor(getResources().getColor(R.color.tv_hint));
                tv_goods_service.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case R.id.fab_up_slide:
                sv_goods_info.smoothScrollTo(0, 0);
                sv_switch.smoothClose(true);
                break;
            case R.id.ll_comment:
                EventBus.getDefault().post(new GoodBusEven(GoodBusEven.SelectedEvaluate));
                break;
            case R.id.bt_good_store:
                params.put(Constants.STOREID, GoodDetailActivity.storeId);
                CommUtil.gotoActivity(getActivity(), StoreActivity.class, false, params);
                break;
            case R.id.goodinfo_share:
                GoodDetailActivity.mShareAction.open();
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.ll_current_goods)
    public void onSpecChooseClick() {
        showSelectSpecDialog();
    }

    private void showSelectSpecDialog() {
        GoodDetailsSpecDialog dialog;
        if (!XEmptyUtils.isEmpty(detailModel.getGoodsBanner())) {
            dialog = new GoodDetailsSpecDialog(mContext, detailModel.getGoodSpec(), detailModel.getGoodsBanner().get(0).getGoodsImage());

        } else {
            dialog = new GoodDetailsSpecDialog(mContext, detailModel.getGoodSpec(), "");

        }
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialog.show();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        dialogWindow.setLayout(dm.widthPixels, dialogWindow.getAttributes().height);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    //第2步:注册一个在后台线程执行的方法,用于接收事件
    public void onGoodEvent(GoodBusEven goodBusEven) {//参数必须是ClassEvent类型, 否则不会调用此方法
        if (goodBusEven.getFlag().equals(GoodBusEven.SelectedGoods)) {
            XLog.v("收到了规格产品参数");
            chosesGood = (GoodsAttrsBean.StockGoodsBean) goodBusEven.getObj();
            tv_current_goods.setText("已选择" + chosesGood.getSpecText());
            detailModel.getGoodSpec().setDefaultGood(chosesGood);
            if (!XEmptyUtils.isEmpty(chosesGood)) {
                GoodDetailActivity.goodPrice = chosesGood.getGoodsPrice();
                GoodDetailActivity.goodSpecText = chosesGood.getGoodSpecText();
            }


        } else if (goodBusEven.getFlag().equals(GoodBusEven.addShopCar)) {
            if (!XEmptyUtils.isEmpty(chosesGood)) {
                XLog.v(chosesGood.toString());
                if (chosesGood.getGoodsInventory() <= 0) {
                    XToast.error("当前商品库存不足");
                    return;
                }
                mPresenter.addCart(String.valueOf(chosesGood.getGoodsSpecInfoId()), detailModel.getGoodsId(), GoodDetailActivity.goodNum);

            } else {
                XLog.v("" + this.goodsInventory);
                if (this.goodsInventory <= 0) {
                    XToast.error("当前商品库存不足");
                    return;
                }

                mPresenter.addCart("", detailModel.getGoodsId(), GoodDetailActivity.goodNum);
            }


        } else if (goodBusEven.getFlag().equals(GoodBusEven.goToBuy)) {
            switch (goodsType) {
                case 1:
                    if (XEmptyUtils.isEmpty(chosesGood)) {
                        if (this.goodsInventory <= 0) {
                            XToast.error("当前商品库存不足");
                            return;
                        }
                        mPresenter.goToBuy("", detailModel.getGoodsId(), GoodDetailActivity.goodNum);

                    } else {
                        if (chosesGood.getGoodsInventory() <= 0) {
                            XToast.error("当前商品库存不足");
                            return;
                        }
                        mPresenter.goToBuy(String.valueOf(chosesGood.getGoodsSpecInfoId()), detailModel.getGoodsId(), GoodDetailActivity.goodNum);
                    }
                    break;
                case 2:
                    if (this.goodsInventory <= 0) {
                        XToast.error("当前商品库存不足");
                        return;
                    }
                    OrderOnGoodBean goodBean = new OrderOnGoodBean();
                    goodBean.setGoodId(detailModel.getGoodsId());
                    goodBean.setImgUrl(detailModel.getGoodsBanner().get(0).getGoodsImage());
                    goodBean.setGoodName(detailModel.getGoodsName());
                    goodBean.setPrice(detailModel.getGoodsPrice());
                    if (XEmptyUtils.isEmpty(chosesGood)) {
                        goodBean.setGoodSpec("");
                        goodBean.setGoodSpecId("");
                    } else {
                        goodBean.setGoodSpec(chosesGood.getGoodSpecText());
                        goodBean.setGoodSpecId(String.valueOf(chosesGood.getGoodsSpecInfoId()));
                    }
                    goodBean.setGoodId(detailModel.getGoodsId());
                    goodBean.setGoodNum(GoodDetailActivity.goodNum);
                    goodBean.setStoreName(detailModel.getStore().getStoreName());
                    EventBus.getDefault().postSticky(goodBean);
                    Map<String, String> params = new HashMap();
                    params.put(Constants.GOODTYPE, String.valueOf(detailModel.getGoodsType()));
                    CommUtil.gotoActivity(getActivity(), ConfirmOrderActivity.class, false, params);
                    break;
            }


        } else if (goodBusEven.getFlag().equals(GoodBusEven.showSpecView)) {
            showSelectSpecDialog();
        }


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

    private void switchFragment(Fragment fromFragment, Fragment toFragment) {
        if (nowFragment != toFragment) {
            fragmentTransaction = fragmentManager.beginTransaction();
            if (!toFragment.isAdded()) {    // 先判断是否被add过
                fragmentTransaction.hide(fromFragment).add(R.id.fl_content, toFragment).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到activity中
            } else {
                fragmentTransaction.hide(fromFragment).show(toFragment).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
            }
        }
    }

    @Override
    public void onStatucChanged(SlideDetailsLayout.Status status) {
        if (status == SlideDetailsLayout.Status.OPEN) {
            this.status = status;
            //当前为图文详情页
            fab_up_slide.show();
            activity.vp_content.setNoScroll(true);
            activity.tv_title.setVisibility(View.VISIBLE);
            activity.mTlMain.setVisibility(View.GONE);
        } else {
            this.status = status;
            //当前为商品详情页
            fab_up_slide.hide();
            activity.vp_content.setNoScroll(false);
            activity.tv_title.setVisibility(View.GONE);
            activity.mTlMain.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 开启倒计时
     */
    private void startRun() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (isRun) {
                    try {
                        Thread.sleep(1000); // sleep 1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                computeTime();
                if (isRun) {
                    tv_good_day.setText(mDay + "");
                    tv_good_hour.setText(mHour + "");
                    tv_good_min.setText(mMin + "");
                    tv_good_second.setText(mSecond + "");
                }

            }
        }
    };

    private static void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
                if (mHour < 0) {
                    // 倒计时结束
                    mHour = 23;
                    mDay--;
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        isRun = false;
        status = SlideDetailsLayout.Status.CLOSE;
        super.onDestroyView();


    }

    @Override
    public LifecycleTransformer<GoodDetailModels> bindLife() {
        return this.<GoodDetailModels>bindUntilEvent(FragmentEvent.DESTROY_VIEW);
    }

    @Override
    public void shoEmpty() {
        CommUtil.gotoActivity(mContext, GoodEmptyActivity.class, true, null);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (GoodDetailActivity) context;
    }

    @Override
    public void onStart() {
        registerEventBus(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        unregisterEventBus(this);
        super.onStop();
    }
}
