package com.xinyuan.xyshop.ui.catrgory;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.GoodsCategoryAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.model.CategoryModel;
import com.xinyuan.xyshop.mvp.contract.CategoryView;
import com.xinyuan.xyshop.mvp.presenter.CategoryPresenter;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;
import com.xinyuan.xyshop.ui.goods.search.SearchActivity;
import com.xinyuan.xyshop.ui.goods.search.SearchGoodShowActivity;
import com.xinyuan.xyshop.ui.goods.store.StoreActivity;
import com.xinyuan.xyshop.ui.home.WebViewActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.EditTextWithDel;
import com.youth.xframe.cache.XCache;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * Created by fx on 2017/7/31.
 * 分类fragment
 */

public class CategoryFragment extends BaseFragment<CategoryPresenter> implements CategoryView {
    @BindView(R.id.llGoodsClassRoot)
    LinearLayout llGoodsClassRoot;
    @BindView(R.id.svGoodsClassRoot)
    ScrollView svGoodsClassRoot;
    @BindView(R.id.svGoodsClass)
    ScrollView svGoodsClass;
    @BindView(R.id.llGoodsClass)
    LinearLayout llGoodsClass;
    @BindView(R.id.cagetory_img)
    ImageView categroy_img;
    @BindView(R.id.category_loadingView)
    XLoadingView xLoadingView;
    @BindView(R.id.category_toolbar)
    Toolbar toolbar;
    @BindView(R.id.category_btn_msg)
    BGABadgeImageView btn_msg;
    @BindView(R.id.category_btn_scan)
    ImageView btn_scan;
    @BindView(R.id.rl_search)
    RelativeLayout rl_search;

    public List<CategoryModel.CategoryData> goodsCategoryList_one;
    //一级分类目录
    public List<CategoryModel.CategoryData> goodsCategoryList_three;
    //二级分类目录
    public List<CategoryModel.CategoryData> goodsCategoryList_two;
    //三级分类目录
    private AddViewHolder currentGoodsClassView;
    private int currentItem = 0;

    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
        return fragment;
    }


    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_category;
    }


    @Override
    public void initView(View rootView) {
        SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
        SystemBarHelper.setHeightAndPadding(getActivity(), toolbar);
        CommUtil.initScanTool(getActivity(), btn_scan, btn_msg);
    }

    @Override
    public void initListener() {
        xLoadingView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getCategory();
            }
        });
        rl_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommUtil.gotoActivity(getActivity(), SearchActivity.class, false, null);

            }
        });

    }

    @Override
    public void initData() {
        XCache xCache = XCache.get(getActivity());
        CategoryModel categoryModel = (CategoryModel) xCache.getAsObject("categoryModel");
        if (XEmptyUtils.isEmpty(categoryModel)) {
            mPresenter.getCategory();
        } else {
            cleanData(categoryModel.getDatas());
        }

    }


    @Override
    protected CategoryPresenter createPresenter() {
        return new CategoryPresenter(getActivity(), this);
    }


    @Override
    public void cleanData(List<CategoryModel.CategoryData> goodsCategoryList) {
        goodsCategoryList_one = new ArrayList<>();
        goodsCategoryList_two = new ArrayList<>();
        goodsCategoryList_three = new ArrayList<>();
        for (int i = 0; i < goodsCategoryList.size(); i++) {
            goodsCategoryList_one.add(goodsCategoryList.get(i));
        }
        for (int k = 0; k < goodsCategoryList_one.size(); k++) {
            int m;
            List<CategoryModel.CategoryData> goodsCategories = goodsCategoryList_one.get(k).getCategoryList();
            for (m = 0; m < goodsCategories.size(); m++) {
                goodsCategoryList_two.add(goodsCategories.get(m));
            }
        }

        for (int j = 0; j < goodsCategoryList_two.size(); j++) {
            List<CategoryModel.CategoryData> goodsCategoryList1 = goodsCategoryList_two.get(j).getCategoryList();
            for (int g = 0; g < goodsCategoryList1.size(); g++) {
                goodsCategoryList_three.add(goodsCategoryList1.get(g));
            }
        }

        for (int m = 0; m < goodsCategoryList_one.size(); m++) {
            showFrist(goodsCategoryList_one.get(m), m);
        }


    }

    @Override
    public void showFrist(CategoryModel.CategoryData classItem, int m) {
        AddViewHolder holder = new AddViewHolder(getActivity(), R.layout.fragment_category_item_frist);
        holder.setText(R.id.tvGoodsClassId, String.valueOf(classItem.getCategoryId())).setText(R.id.tv_category_first, classItem.getCategoryName());
        if (m == 0) {
            Gson bean = new Gson();
            ItemData adBean = bean.fromJson(classItem.getAd(), ItemData.class);
            setCurrentGoodsClass(holder);
            this.currentGoodsClassView = holder;
            this.currentItem = m;
            String goodsClassId = holder.getText(R.id.tvGoodsClassId);
            this.showGoodsClass(goodsClassId);
            if (!XEmptyUtils.isEmpty(adBean.getImageUrl())) {
                GlideImageLoader.setUrlImg(getContext(), adBean.getImageUrl(), categroy_img);
                onAdClick(categroy_img, adBean);
            } else {
                categroy_img.setVisibility(View.GONE);
            }

        }
        this.llGoodsClassRoot.addView(holder.getCustomView());
        setItemClick(holder, classItem, m);
    }


    @Override
    public void setItemClick(final AddViewHolder holder, final CategoryModel.CategoryData classItem, final int m) {
        holder.setOnClickListener(R.id.llView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (m != currentItem) {
                    Gson bean = new Gson();
                    ItemData adBean = bean.fromJson(classItem.getAd(), ItemData.class);
                    setCurrentGoodsClass(holder);
                    resetCurrentGoodsClass(currentGoodsClassView);
                    currentItem = m;
                    currentGoodsClassView = holder;
                    String goodsClassId = holder.getText(R.id.tvGoodsClassId);
                    showGoodsClass(goodsClassId);
                    GlideImageLoader.setUrlImg(getContext(), adBean.getImageUrl(), categroy_img);
                    onAdClick(categroy_img, adBean);

                }
            }
        });
    }

    private void onAdClick(ImageView imageView, ItemData itemData) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> params = new HashMap();
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
                }
            }
        });
    }

    @Override
    public LifecycleTransformer<CategoryModel> bindLife() {
        return this.<CategoryModel>bindUntilEvent(FragmentEvent.DESTROY_VIEW);
    }

    private void showGoodsClass(String classId) {
        this.svGoodsClass.setVisibility(View.VISIBLE);
        this.svGoodsClass.scrollTo(0, 0);
        this.llGoodsClass.removeAllViews();
        for (int i = 0; i < this.goodsCategoryList_two.size(); i++) {
            if (((CategoryModel.CategoryData) this.goodsCategoryList_two.get(i)).getParentId() == Integer.valueOf(classId).intValue()) {
                showRightView((CategoryModel.CategoryData) this.goodsCategoryList_two.get(i), i);
            }
        }
    }

    private void showRightView(final CategoryModel.CategoryData goodsCategory, int position) {
        AddViewHolder holder = new AddViewHolder(getActivity(), R.layout.fragment_category_item_class);

        GridView gvGoodsClass = (GridView) holder.getCustomView().findViewById(R.id.gvGoodsClass);
        if (position == 0) {
            holder.setVisible(R.id.tvLine, false);
        }
        switch (position % 10) {
            case 0:
                holder.setImageDrawable((int) R.id.tvGoodsClassDot, (int) R.drawable.nc_sharp_dot0);
                break;
            case 1:
                holder.setImageDrawable((int) R.id.tvGoodsClassDot, (int) R.drawable.nc_sharp_dot1);
                break;
            case 2:
                holder.setImageDrawable((int) R.id.tvGoodsClassDot, (int) R.drawable.nc_sharp_dot2);
                break;
            case 3:
                holder.setImageDrawable((int) R.id.tvGoodsClassDot, (int) R.drawable.nc_sharp_dot3);
                break;
            case 4:
                holder.setImageDrawable((int) R.id.tvGoodsClassDot, (int) R.drawable.nc_sharp_dot4);
                break;
            case 5:
                holder.setImageDrawable((int) R.id.tvGoodsClassDot, (int) R.drawable.nc_sharp_dot5);
                break;
            case 6:
                holder.setImageDrawable((int) R.id.tvGoodsClassDot, (int) R.drawable.nc_sharp_dot6);
                break;
            case 7:
                holder.setImageDrawable((int) R.id.tvGoodsClassDot, (int) R.drawable.nc_sharp_dot7);
                break;
            case 8:
                holder.setImageDrawable((int) R.id.tvGoodsClassDot, (int) R.drawable.nc_sharp_dot8);
                break;
            case 9:
                holder.setImageDrawable((int) R.id.tvGoodsClassDot, (int) R.drawable.nc_sharp_dot9);
                break;
        }
        holder.setText(R.id.tvGoodsClassName, goodsCategory.getCategoryName());
        String id = String.valueOf(goodsCategory.getCategoryId());


        List<CategoryModel.CategoryData> categories = new ArrayList();
        List<List<CategoryModel.CategoryData>> goodCatrList = new ArrayList();


        for (int j = 0; j < this.goodsCategoryList_three.size(); j++) {
            if (((CategoryModel.CategoryData) this.goodsCategoryList_three.get(j)).getParentId() == Integer.valueOf(id).intValue()) {
                categories.add(this.goodsCategoryList_three.get(j));
            }
        }
        if (!categories.isEmpty()) {
            goodCatrList.add(categories);
        }
        for (int k = 0; k < goodCatrList.size(); k++) {
            GoodsCategoryAdapter adapter = new GoodsCategoryAdapter(getActivity());
            adapter.setmDatas((List) goodCatrList.get(k));
            gvGoodsClass.setAdapter(adapter);
        }
        this.llGoodsClass.addView(holder.getCustomView());
        showState(1);
    }

    private void setCurrentGoodsClass(AddViewHolder holder) {
        holder.setTextColor(R.id.tv_category_first, R.color.colorPrimary).setBackgroundColor(R.id.llView, R.color.bg_gray);
    }

    private void resetCurrentGoodsClass(AddViewHolder holder) {
        holder.setTextColor(R.id.tv_category_first, R.color.tv_name).setBackgroundColor(R.id.llView, R.color.bg_white);
    }

    public void jump(String keyWords) {
        Map<String, String> params = new HashMap();
        params.put(Constants.KEYWORD, keyWords);
        CommUtil.gotoActivity(getActivity(), SearchGoodShowActivity.class, false, params);
    }

    @Override
    public void onSupportVisible() { //当fragment可见时，检查是否有新消息
        if (MyShopApplication.IsNewMsg) {
            btn_msg.showCirclePointBadge();
        } else {
            btn_msg.hiddenBadge();
        }
        super.onSupportVisible();
    }

    @Override
    public void showState(int Sate) {
        switch (Sate) {
            case 0:
                xLoadingView.showLoading();
                break;
            case 1:
                xLoadingView.showContent();
                break;
            case 2:
                xLoadingView.showError();
                break;
        }
    }
}
