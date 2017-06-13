package com.xinyuan.xyshop.ui.goods;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.ItemTitlePagerAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.entity.PreGoods;
import com.xinyuan.xyshop.entity.TabEntity;
import com.xinyuan.xyshop.ui.goods.fragment.GoodsCommentFragment;
import com.xinyuan.xyshop.ui.goods.fragment.GoodsDetailFragment;
import com.xinyuan.xyshop.ui.goods.fragment.GoodsInfoFragment;
import com.xinyuan.xyshop.ui.goods.fragment.GoodsRecommFragment;
import com.xinyuan.xyshop.widget.NoScrollViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

public class GoodDetailsActivity extends BaseActivity {


    @BindView(R.id.tl_main)
    public SlidingTabLayout mTlMain;
    @BindView(R.id.vp_content)
    public NoScrollViewPager vp_content;
    @BindView(R.id.tv_title)
    public TextView tv_title;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    private GoodsInfoFragment goodsInfoFragment;
    private GoodsDetailFragment goodsDetailFragment;
    private GoodsCommentFragment goodsCommentFragment;
    private GoodsRecommFragment goodsRecommFragment;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"商品", "详情", "评价", "推荐"};
    private static int commonId;

    private HashMap<Integer, PreGoods> preGoodsMap;
    public static String COMMONID = "commonId";
    public static String GOODID = "goodsId";
    public static String TRYSTYPE = "trysType";

    private static int trysType;

    @Override
    public int getLayoutId() {
        return R.layout.activity_good_details;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        this.commonId = getIntent().getIntExtra(COMMONID, 0);
        this.commonId = getIntent().getIntExtra(GOODID, 0);
        this.trysType = getIntent().getIntExtra(TRYSTYPE, 0);
        this.preGoodsMap = new HashMap();

    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i]));
        }

        fragmentList.add(goodsInfoFragment = new GoodsInfoFragment());
        fragmentList.add(goodsDetailFragment = new GoodsDetailFragment());
        fragmentList.add(goodsCommentFragment = new GoodsCommentFragment());
        fragmentList.add(goodsRecommFragment = new GoodsRecommFragment());

        vp_content.setAdapter(new ItemTitlePagerAdapter(getSupportFragmentManager(),
                fragmentList, new String[]{"商品", "详情", "评价", "推荐"}));
        vp_content.setOffscreenPageLimit(1);
        mTlMain.setViewPager(vp_content);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    //第2步:注册一个在后台线程执行的方法,用于接收事件
    public void onUserEvent(GoodBusBean goodBusBean) {//参数必须是ClassEvent类型, 否则不会调用此方法
        if (goodBusBean.getFlag().equals(GoodBusBean.GoodEvaluate)) {
            vp_content.setCurrentItem(2);
        }

    }


}
