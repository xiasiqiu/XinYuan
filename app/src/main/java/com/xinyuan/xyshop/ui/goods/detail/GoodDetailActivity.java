package com.xinyuan.xyshop.ui.goods.detail;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.ItemTitlePagerAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.bean.ContanctBean;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.state.LoginContext;
import com.xinyuan.xyshop.entity.TabEntity;
import com.xinyuan.xyshop.even.GoodBusEven;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.mvp.contract.GoodDetailView;
import com.xinyuan.xyshop.mvp.presenter.GoodDetailPresenter;
import com.xinyuan.xyshop.ui.goods.detail.fragment.GoodHomeFragment;
import com.xinyuan.xyshop.ui.goods.detail.fragment.GoodsDetailFragment;
import com.xinyuan.xyshop.ui.goods.detail.fragment.GoodsEvaFragment;
import com.xinyuan.xyshop.ui.goods.store.StoreActivity;
import com.xinyuan.xyshop.ui.mine.login.LoginActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.widget.NoScrollViewPager;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * Created by fx on 2017/8/7.
 * 商品详情Activity
 */

public class GoodDetailActivity extends BaseActivity<GoodDetailPresenter> implements GoodDetailView {
    @BindView(R.id.tl_main)
    public SlidingTabLayout mTlMain;
    @BindView(R.id.vp_content)
    public NoScrollViewPager vp_content;
    @BindView(R.id.tv_title)
    public TextView tv_title;

    @BindView(R.id.cb_good_fv)
    CheckBox cb_good_fv;
    @BindView(R.id.bt_store)
    Button bt_store;
    @BindView(R.id.bt_service)
    Button bt_service;
    @BindView(R.id.bt_add_car)
    Button bt_add_car;
    @BindView(R.id.bt_goos_buy)
    Button bt_goos_buy;
    @BindView(R.id.iv_back)
    ImageView iv_header_left;
    @BindView(R.id.detail_btn_msg)
    BGABadgeImageView iv_header_right;
    private GoodHomeFragment goodHomeFragment;
    private GoodsDetailFragment goodsDetailFragment;
    private GoodsEvaFragment goodsCommentFragment;

    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"商品", "详情", "评价"};

    public static int totalCount; //评论总数
    public static int goodAssess; //好评数
    public static int normalAssess; //中评数
    public static int lowAssess; //差评数
    public static int blueprint;  //晒图数

    public static BigDecimal goodPrice;  //价格
    public static String goodSpecText;  //晒图数

    public static String goodsId; //商品ID
    public static String storeId; //店铺ID
    public static int goodType; //商品类型 1：实物，2：虚拟 3：团购
    public static boolean isOnline = false; //是否是虚拟
    public static boolean isGroup = false; //是否是团购
    public static String goodName;
    public static String goodImg;
    public static boolean isFav = false; //是否被收藏
    public static int goodNum = 1;   //商品选择数量

    public static String userName;
    public static String userHeadImg;
    public static String userId;
    private static ContanctBean contanctBean;
    public static boolean haveSpec = false;
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();

    @Override
    protected GoodDetailPresenter createPresenter() {
        return new GoodDetailPresenter(this, this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_good_details;
    }

    @Override
    public void initView() {
        CommUtil.initToolBar(this, iv_header_left, iv_header_right);
        this.goodsId = getIntent().getStringExtra(Constants.GOODID); //商品ID
        this.goodType = Integer.parseInt(getIntent().getStringExtra(Constants.GOODTYPE));//商品类型

    }


    @Override
    public void initData() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i]));
        }
        fragmentList.add(goodHomeFragment = new GoodHomeFragment());
        fragmentList.add(goodsDetailFragment = new GoodsDetailFragment());
        fragmentList.add(goodsCommentFragment = new GoodsEvaFragment());
        vp_content.setAdapter(new ItemTitlePagerAdapter(getSupportFragmentManager(),
                fragmentList, new String[]{"商品", "详情", "评价"}));
        vp_content.setOffscreenPageLimit(3);
        mTlMain.setViewPager(vp_content);
        switch (goodType) {
            case 1:
                bt_add_car.setVisibility(View.VISIBLE);
                isOnline = false;
                break;
            case 2:
                bt_add_car.setVisibility(View.GONE);
                isOnline = true;
                break;
            case 4:
                bt_add_car.setVisibility(View.GONE);
                isOnline = true;
                bt_goos_buy.setText("立即兑换");
                break;
        }

    }


    @OnClick({R.id.bt_store, R.id.bt_service, R.id.bt_goos_buy, R.id.bt_add_car})
    public void OnClick(View v) {
        Map<String, String> params = new HashMap();
        v.getId();
        switch (v.getId()) {
            case R.id.bt_store:
                params.put(Constants.STOREID, storeId);
                CommUtil.gotoActivity(this, StoreActivity.class, false, params);
                break;
            case R.id.bt_service:
                //if (!XEmptyUtils.isEmpty(contanctBean)) {
                params.put(Constants.USERNAME, userName);
                params.put(Constants.USERHEAD, userHeadImg);
                params.put(Constants.USERID, userId);
                params.put(Constants.STOREID, storeId);
                LoginContext.getInstance().contactService(params, GoodDetailActivity.this);
                //}

                break;
            case R.id.bt_goos_buy:
                if (LoginContext.isLogin) {
                    EventBus.getDefault().post(new GoodBusEven(GoodBusEven.showSpecView, true));
                } else {
                    CommUtil.gotoActivity(this, LoginActivity.class, false, null);
                }

                break;
            case R.id.bt_add_car:
                if (LoginContext.isLogin) {
                    EventBus.getDefault().post(new GoodBusEven(GoodBusEven.showSpecView, true));
                } else {
                    CommUtil.gotoActivity(this, LoginActivity.class, false, null);

                }

        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGoodEvent(GoodBusEven goodBusEven) {
        if (goodBusEven.getFlag().equals(GoodBusEven.SelectedEvaluate)) {//当在首页点击评论时跳转到评论列表页面
            vp_content.setCurrentItem(2);
        } else if (goodBusEven.getFlag().equals(GoodBusEven.GoodsStoreId)) { //获取店铺ID
            storeId = (String) goodBusEven.getObj();
        } else if (goodBusEven.getFlag().equals(GoodBusEven.contanctSercvice)) {
            contanctBean = (ContanctBean) goodBusEven.getObj();
        } else if (goodBusEven.getFlag().equals(GoodBusEven.goodFav)) {
            if (isFav) {
                cb_good_fv.setText("已收藏");
                cb_good_fv.setChecked(true);
            } else {
                flag = true;

            }
        } else if (goodBusEven.getFlag().equals(GoodBusEven.goToShare)) {
        }


    }

    private boolean flag = false;
    private UMShareListener mShareListener;
    public static ShareAction mShareAction;

    @Override
    public void initListener() {
        cb_good_fv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (flag) {
                    if (isFav) { //若该商品已被收藏
                        mPresenter.cancelFavGood(goodsId); //取消收藏
                    } else {
                        mPresenter.FavGood(goodsId, 0); //添加收藏
                    }

                } else {
                    flag = true;
                }
            }
        });
        mShareListener = new CustomShareListener(this);
  /*增加自定义按钮的分享面板*/
        mShareAction = new ShareAction(GoodDetailActivity.this).setDisplayList(
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        UMWeb web = new UMWeb(Urls.URL_SHARE_GOOD + goodsId);
                        web.setTitle(goodName);
                        web.setDescription(goodName);
                        web.setThumb(new UMImage(GoodDetailActivity.this, R.mipmap.ic_launcher));
                        new ShareAction(GoodDetailActivity.this).withMedia(web)
                                .setPlatform(share_media)
                                .setCallback(mShareListener)
                                .share();

                    }
                });

    }


    public void setFavView(int type) {
        switch (type) {
            case 1:
                cb_good_fv.setText("收藏");
                cb_good_fv.setChecked(false);
                isFav = false;
                XToast.normal("已取消收藏该商品！");
                break;
            case 2:
                cb_good_fv.setText("已收藏");
                cb_good_fv.setChecked(true);
                isFav = true;
                XToast.info("已收藏该商品！");
                break;
            case 3:
                cb_good_fv.setText("已收藏");
                isFav = true;
                cb_good_fv.setChecked(true);
                break;
        }
    }

    private static class CustomShareListener implements UMShareListener {

        private WeakReference<GoodDetailActivity> mActivity;

        private CustomShareListener(GoodDetailActivity activity) {
            mActivity = new WeakReference(activity);
        }

        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {

            if (platform.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(mActivity.get(), platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else {
                if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                        && platform != SHARE_MEDIA.EMAIL
                        && platform != SHARE_MEDIA.FLICKR
                        && platform != SHARE_MEDIA.FOURSQUARE
                        && platform != SHARE_MEDIA.TUMBLR
                        && platform != SHARE_MEDIA.POCKET
                        && platform != SHARE_MEDIA.PINTEREST

                        && platform != SHARE_MEDIA.INSTAGRAM
                        && platform != SHARE_MEDIA.GOOGLEPLUS
                        && platform != SHARE_MEDIA.YNOTE
                        && platform != SHARE_MEDIA.EVERNOTE) {
                    Toast.makeText(mActivity.get(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
                }

            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                    && platform != SHARE_MEDIA.EMAIL
                    && platform != SHARE_MEDIA.FLICKR
                    && platform != SHARE_MEDIA.FOURSQUARE
                    && platform != SHARE_MEDIA.TUMBLR
                    && platform != SHARE_MEDIA.POCKET
                    && platform != SHARE_MEDIA.PINTEREST

                    && platform != SHARE_MEDIA.INSTAGRAM
                    && platform != SHARE_MEDIA.GOOGLEPLUS
                    && platform != SHARE_MEDIA.YNOTE
                    && platform != SHARE_MEDIA.EVERNOTE) {
                Toast.makeText(mActivity.get(), platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
                if (t != null) {
                    Log.d("throw", "throw:" + t.getMessage());
                }
            }

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {

            Toast.makeText(mActivity.get(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mShareAction.close();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterEventBus(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEventBus(this);
    }

    @Override
    protected void onStop() {
        isFav = false;
        flag = false;
        haveSpec = false;
        goodNum = 1;
        super.onStop();
    }
}
