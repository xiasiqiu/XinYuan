package com.xinyuan.xyshop.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sunfusheng.marqueeview.MarqueeView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.HomeGoodsAdapter;
import com.xinyuan.xyshop.adapter.HomeMultipleItemAdapter;
import com.xinyuan.xyshop.adapter.MenuMoreAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.bean.GoodListItemBean;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.common.state.LoginContext;
import com.xinyuan.xyshop.entity.HomeMultipleItem;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.even.MainFragmentStartEvent;
import com.xinyuan.xyshop.model.HomeModel;
import com.xinyuan.xyshop.model.KeyWordModel;
import com.xinyuan.xyshop.mvp.contract.HomeView;
import com.xinyuan.xyshop.mvp.presenter.HomePresenter;
import com.xinyuan.xyshop.ui.goods.credit.CreditMallFragment;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;
import com.xinyuan.xyshop.ui.goods.groupbuy.GroupBuyActivity;
import com.xinyuan.xyshop.ui.goods.search.SearchActivity;
import com.xinyuan.xyshop.ui.goods.store.StoreActivity;
import com.xinyuan.xyshop.ui.home.news.NewsActivity;
import com.xinyuan.xyshop.ui.mine.login.LoginActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.dialog.SignDialog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.loadingview.XLoadingView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * Created by fx on 2017/7/31.
 * 首页
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.home_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.home_swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.home_loadingView)
    XLoadingView xLoadingView;
    @BindView(R.id.home_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.home_header_rl)
    RelativeLayout darkView;
    @BindView(R.id.act_home_btn_scan)
    ImageView mScan;
    @BindView(R.id.act_home_btn_msg)
    BGABadgeImageView mMsg;
    @BindView(R.id.frag_home_et_search)
    Button et_search;


    private MarqueeView marqueeView; //公告滚动view

    private ArrayList<String> bannerImages = new ArrayList<>();
    private List<CharSequence> noticeList = new ArrayList<>();

    private HomeMultipleItemAdapter homeMultipleItemAdapter;
    private RecyclerView menuListView;//菜单列表
    private MenuMoreAdapter menuAdapter;
    private List<ItemData> menuList;
    private HomeGoodsAdapter goodsAdapter;
    private View footView;  //列表头部  banner，菜单，公告
    private View headView;//列表头部  推荐商品
    private int mDistanceY = 0; //列表滑动距离

    private Bundle saveState; //保存状态

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(getActivity(), this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View rootView) {
        SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
        SystemBarHelper.setHeightAndPadding(getActivity(), mToolbar);
    }

    @Override
    public void initData() {
        mPresenter.getKeyWords(); //获取搜索关键词数据
    }

    @Override
    public void onEnterAnimationEnd(Bundle saveInstanceState) {
        mDistanceY = 0; //列表滑动距离
        initListener();
        mPresenter.getHomeData();
    }


    @Override
    public void initListener() {
        restoreState();
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(29, 160, 57));
        xLoadingView.setOnRetryClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showState(0);
                        mPresenter.getHomeData();
                    }
                });
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //滑动的距离
                mDistanceY += dy;
                //toolbar的高度
                int toolbarHeight = mToolbar.getBottom();


                if (mDistanceY < 10) {
                    mToolbar.setBackgroundResource(R.color.colorTransparency);
                    darkView.setBackgroundResource(R.color.colorTransparency);
                    et_search.setTextColor(getResources().getColor(R.color.tv_hint));

                } else if (mDistanceY <= toolbarHeight) {

                    float scale = (float) mDistanceY / toolbarHeight;
                    float alpha = scale * 255;
                    mToolbar.setBackgroundColor(Color.argb((int) alpha, 0, 118, 42));
                    darkView.setBackgroundColor(Color.argb((int) alpha, 29, 160, 57));
                    et_search.setTextColor(getResources().getColor(R.color.bg_gray));


                } else {
                    mToolbar.setBackgroundResource(R.color.colorPrimaryDark);
                    darkView.setBackgroundResource(R.color.colorPrimary);
                    et_search.setTextColor(getResources().getColor(R.color.bg_white));
                }
            }
        });
        mMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginContext.getInstance().showMsg(mContext);
            }
        });
        mScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommUtil.gotoActivity(getActivity(), ScanActivity.class, false, null);
            }
        });
    }

    /**
     * 添加头部视图（banner,公告，菜单，各模块）
     *
     * @param list
     */
    @Override
    public void addHead(final List<HomeMultipleItem> list, List<HomeModel.HomeModule> moduleList) {
        if (XEmptyUtils.isEmpty(headView)) {
            headView = getActivity().getLayoutInflater().inflate(R.layout.fragment_home_top, (ViewGroup) mRecyclerView.getParent(), false);
            GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
            mRecyclerView.setLayoutManager(manager);
            homeMultipleItemAdapter = new HomeMultipleItemAdapter(list, moduleList);
            homeMultipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
                @Override
                public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {

                    return list.get(position).getSpanSize();
                }
            });

            homeMultipleItemAdapter.addHeaderView(headView);
            mRecyclerView.setAdapter(homeMultipleItemAdapter);
        } else {
            homeMultipleItemAdapter.setNewData(list);
        }


    }

    /**
     * 显示banner
     *
     * @param itemList
     */
    @Override
    public void showBanner(final List<ItemData> itemList) {

        for (ItemData itemData : itemList) {
            bannerImages.add(CommUtil.getUrl(itemData.getImageUrl()));
        }

        final Banner banner = (Banner) headView.findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setImageLoader(new GlideImageLoader())
                .setImages(bannerImages)
                .setBannerAnimation(Transformer.DepthPage)
                .isAutoPlay(true)
                .setDelayTime(3000)
                .setIndicatorGravity(BannerConfig.CENTER)
                .start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (itemList != null) {
                    ItemData itemData = itemList.get(position);
                    OnImageViewClick(banner, itemData);
                }
            }
        });


        bannerImages.clear();
    }


    /**
     * 显示菜单
     *
     * @param itemList
     */
    @Override
    public void showMenu(List<ItemData> itemList) {
        if (!XEmptyUtils.isEmpty(itemList)) {
            if (itemList.size() >= 9) {
                menuList = itemList.subList(0, 9);
                ItemData moreMenu = new ItemData();
                moreMenu.setImageUrl("");
                moreMenu.setText(getString(R.string.menu_more));
                moreMenu.setData(getString(R.string.menu_more));
                moreMenu.setType(Constants.NATIVE);
                menuList.add(menuList.size(), moreMenu);
            } else {
                menuList = itemList;
            }
            if (XEmptyUtils.isEmpty(menuAdapter)) {
                menuListView = (RecyclerView) headView.findViewById(R.id.menu_list);
                menuAdapter = new MenuMoreAdapter(0, R.layout.fragment_home_item_expandable, menuList);
                final GridLayoutManager manager = new GridLayoutManager(getActivity(), 5);
                menuListView.setAdapter(menuAdapter);

                menuListView.setLayoutManager(manager);
                menuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        OnImageViewClick(view, itemList.get(position));
                    }
                });

            } else {
                menuAdapter.setNewData(itemList);
            }
        }


    }

    /**
     * 显示公告
     *
     * @param itemList
     */
    @Override
    public void showNotice(List<ItemData> itemList) {
        if (!XEmptyUtils.isEmpty(itemList)) {

            marqueeView = (MarqueeView) headView.findViewById(R.id.marquee_name);
            for (ItemData data : itemList) {
                String s = "【" + data.getType() + "】" + data.getText();
                SpannableString ss1 = new SpannableString(s);
                ss1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimaryDark)), 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                noticeList.add(ss1);
            }

            marqueeView.startWithList(noticeList);

            RelativeLayout rl_home_news = (RelativeLayout) headView.findViewById(R.id.rl_home_news);
            rl_home_news.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CommUtil.gotoActivity(getActivity(), NewsActivity.class, false, null);
                }
            });
            noticeList.clear();
        }
    }


    /**
     * 显示推荐商品
     *
     * @param list
     */
    @Override
    public void showGoods(List<GoodListItemBean> list) {
        if (!XEmptyUtils.isEmpty(list)) {
            if (XEmptyUtils.isEmpty(footView)) {
                footView = getActivity().getLayoutInflater().inflate(R.layout.fragment_home_footer, (ViewGroup) mRecyclerView.getParent(), false);
                RecyclerView rv_goods = (RecyclerView) footView.findViewById(R.id.rv_home_goods);
                GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2, 1, false);
                rv_goods.setLayoutManager(layoutManager);
                goodsAdapter = new HomeGoodsAdapter(R.layout.item_home_good_grid, list);
                rv_goods.setAdapter(goodsAdapter);
                goodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        HashMap<String, String> params;
                        params = new HashMap();
                        params.put(Constants.GOODID, goodsAdapter.getData().get(position).getGoodsId());
                        params.put(Constants.GOODTYPE, goodsAdapter.getData().get(position).getGoodsType());
                        CommUtil.gotoActivity(mContext, GoodDetailActivity.class, false, params);


                    }
                });

                homeMultipleItemAdapter.addFooterView(footView);

            } else {
                goodsAdapter.setNewData(list);
            }
        }

    }

    /**
     * 监听点击
     *
     * @param view
     * @param itemData
     */
    @Override
    public void OnImageViewClick(View view, ItemData itemData) {
        Map<String, String> params = new HashMap();
        if (!XEmptyUtils.isEmpty(itemData.getType())) {
            if (itemData.getType().equals(Constants.GOOD)) {
                params.put(Constants.GOODID, itemData.getData());
                params.put(Constants.GOODTYPE, "1");
                CommUtil.gotoActivity(getActivity(), GoodDetailActivity.class, false, params);
            } else if (itemData.getType().equals(Constants.STOREID)) {
                params.put(Constants.STOREID, itemData.getData());
                CommUtil.gotoActivity(getActivity(), StoreActivity.class, false, params);
            } else if (itemData.getType().equals(Constants.HTML)) {
                params.put(Constants.URL, itemData.getData());
                CommUtil.gotoActivity(getActivity(), WebViewActivity.class, false, params);
            } else if (itemData.getType().equals(Constants.NATIVE)) {
                if (itemData.getData().equals(mContext.getResources().getString(R.string.brandtitle))) {
                    EventBus.getDefault().post(new MainFragmentStartEvent(BrandFragment.newInstance()));
                } else if (itemData.getData().equals(getString(R.string.credit_mall))) {
                    EventBus.getDefault().post(new MainFragmentStartEvent(CreditMallFragment.newInstance()));
                } else if (itemData.getData().equals(getString(R.string.group_mall))) {
                    CommUtil.gotoActivity(mContext, GroupBuyActivity.class, false, null);
                } else if (itemData.getData().equals(getString(R.string.sign_day))) {
                    if (LoginContext.isLogin) {
                        mPresenter.SignDay();
                    } else {
                        CommUtil.gotoActivity(getActivity(), LoginActivity.class, false, null);
                    }
                } else if (itemData.getData().equals(getString(R.string.menu_more))) {
                    EventBus.getDefault().post(new MainFragmentStartEvent(MenuFragment.newInstance()));
                }
            }

        }
    }


    /**
     * 设置搜索热词
     *
     * @param keyWords
     */
    @Override
    public void setKeyWord(final KeyWordModel keyWords) {
        if (!XEmptyUtils.isEmpty(keyWords)) {
            et_search.setHint(keyWords.getKeywordList().get(0));
            et_search.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        EventBus.getDefault().postSticky(keyWords);
                        CommUtil.gotoActivity(getActivity(), SearchActivity.class, false, null);

                    }
                    return false;
                }
            });
        }

    }

    @Override
    public void showSign(boolean status, String num) {
        SignDialog.Builder builder = new SignDialog.Builder(getActivity(), status, num);
        builder.create().show();
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        homeMultipleItemAdapter.setEnableLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.getHomeData();
                mSwipeRefreshLayout.setRefreshing(false);
                homeMultipleItemAdapter.setEnableLoadMore(true);

            }
        }, 500);
    }


    @Override
    public void showState(int state) {
        switch (state) {
            case 0:
                xLoadingView.showLoading();
                break;
            case 1:
                xLoadingView.showContent();
                break;
            case 2:
                xLoadingView.showError();
                break;
            case 3:
                xLoadingView.showEmpty();

        }

    }


    @Override
    public void onSupportVisible() { //当fragment可见时，检查是否有新消息
        if (MyShopApplication.IsNewMsg) {
            mMsg.showCirclePointBadge();
        } else {
            mMsg.hiddenBadge();
        }
        if (!XEmptyUtils.isEmpty(marqueeView)) {
            marqueeView.startFlipping();

        }
        super.onSupportVisible();
    }


    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        if (!XEmptyUtils.isEmpty(marqueeView)) {
            marqueeView.stopFlipping();
        }
    }


    private void saveStatetoArguments() {
        saveState = saveState();
        if (saveState != null) {
            Bundle b = getArguments();
        }
    }

    private Bundle saveState() {
        Bundle d = new Bundle();
        d.putInt("DistanceY", mDistanceY);
        return d;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveStatetoArguments();
    }

    private void restoreState() {
        if (saveState != null) {
            mDistanceY = saveState.getInt("DistanceY");
        }
    }


    @Override
    public LifecycleTransformer<HomeModel> bindLife() {
        return this.<HomeModel>bindUntilEvent(FragmentEvent.DESTROY_VIEW);
    }
}
