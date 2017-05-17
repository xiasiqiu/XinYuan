//package com.xinyuan.xyshop.widget.dialog;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.content.DialogInterface.OnDismissListener;
//import android.os.Bundle;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.View.OnFocusChangeListener;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RadioButton;
//import android.widget.TextView;
//import butterknife.Bind;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import com.umeng.socialize.common.SocializeConstants;
//import com.ypy.eventbus.EventBus;
//import gov.nist.core.Separators;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import net.shopnc.b2b2c.R;
//import net.shopnc.b2b2c.android.bean.Attribute;
//import net.shopnc.b2b2c.android.bean.AttributeValue;
//import net.shopnc.b2b2c.android.bean.Brand;
//import net.shopnc.b2b2c.android.bean.GoodCategory;
//import net.shopnc.b2b2c.android.bean.SelectFilter;
//import net.shopnc.b2b2c.android.bean.SortString;
//import net.shopnc.b2b2c.android.common.AddViewHolder;
//import net.shopnc.b2b2c.android.common.Common;
//import net.shopnc.b2b2c.android.common.LinkedMultiValueMap;
//import net.shopnc.b2b2c.android.common.ShopHelper;
//import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
//import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
//import net.shopnc.b2b2c.android.custom.TToast;
//import net.shopnc.b2b2c.android.xrefresh.XScrollView;
//
//public class SearchGoodSelectDialog extends Dialog {
//    @Bind({2131558601})
//    ImageView btnBack;
//    @Bind({2131558604})
//    TextView btnClear;
//    @Bind({2131558877})
//    RadioButton btnGift;
//    @Bind({2131558876})
//    RadioButton btnGiftDefault;
//    @Bind({2131558603})
//    ImageButton btnMore;
//    @Bind({2131558875})
//    RadioButton btnOtherShop;
//    @Bind({2131558874})
//    RadioButton btnOwnShop;
//    @Bind({2131558872})
//    RadioButton btnPreShow;
//    @Bind({2131558866})
//    TextView btnRevert;
//    @Bind({2131558870})
//    RadioButton btnSaleDefault;
//    @Bind({2131558879})
//    Button btnSelectSave;
//    @Bind({2131558873})
//    RadioButton btnStoreDefault;
//    @Bind({2131558871})
//    RadioButton btnXianshi;
//    private String cat = "";
//    private Context context;
//    @Bind({2131558869})
//    EditText etNumBatch;
//    @Bind({2131558867})
//    EditText etPriceFrom;
//    @Bind({2131558868})
//    EditText etPriceTo;
//    private SelectFilter filter;
//    @Bind({2131558878})
//    LinearLayout llBrand;
//    @Bind({2131558825})
//    LinearLayout llCategory;
//    @Bind({2131558826})
//    LinearLayout llSort;
//    @Bind({2131558554})
//    XScrollView scrollView;
//    private LinkedMultiValueMap<Integer, Integer> selectAttributeList;
//    private List<Brand> selectedBrandList;
//    @Bind({2131558602})
//    TextView tvCommonTitle;
//
//    public SearchGoodSelectDialog(Context context) {
//        super(context, R.style.CommonDialog);
//        this.context = context;
//    }
//
//    public void setFilter(SelectFilter filter) {
//        this.filter = filter;
//    }
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search_good_select);
//        ButterKnife.bind((Dialog) this);
//        getWindow().setLayout((ShopHelper.getScreenWeight(this.context) / 5) * 4, -1);
//        getWindow().setWindowAnimations(R.style.AnimationFade);
//        getWindow().setGravity(5);
//        this.tvCommonTitle.setText("筛选");
//        setFormatListener();
//        this.selectedBrandList = new ArrayList();
//        this.selectAttributeList = new LinkedMultiValueMap();
//    }
//
//    protected void onStart() {
//        super.onStart();
//        setSortShow();
//        this.scrollView.smoothScrollTo(0, 0);
//    }
//
//    private void setSortShow() {
//        setCategoryShow();
//        setBrandShow();
//        setAttributeShow();
//    }
//
//    private void setCategoryShow() {
//        if (this.filter.getCategoryList() != null) {
//            boolean z;
//            this.llCategory.removeAllViews();
//            final AddViewHolder addViewHolder = new AddViewHolder(this.context, R.layout.select_dialog_sort_item);
//            addViewHolder.setText(R.id.tvSortName, "包含分类");
//            RecyclerView rvSort = (RecyclerView) addViewHolder.getView(R.id.rvSort);
//            rvSort.setLayoutManager(new GridLayoutManager(this.context, 3));
//            final RRecyclerAdapter<GoodCategory> categoryAdapter = new RRecyclerAdapter<GoodCategory>(this.context, R.layout.select_dialog_sort_recycler_item) {
//                public void convert(RecyclerHolder holder, final GoodCategory o) {
//                    final TextView btnSpec = (TextView) holder.getView(R.id.btnSpec);
//                    btnSpec.setText(o.getCategoryName());
//                    btnSpec.setOnClickListener(new OnClickListener() {
//                        public void onClick(View v) {
//                            if (!btnSpec.isActivated()) {
//                                btnSpec.setActivated(true);
//                                SearchGoodSelectDialog.this.cat = "&cat=" + o.getCategoryId();
//                                SearchGoodSelectDialog.this.btnSelectSaveClick();
//                            }
//                        }
//                    });
//                }
//            };
//            categoryAdapter.setDatas(this.filter.getCategoryList().subList(0, this.filter.getCategoryList().size() > 3 ? 3 : this.filter.getCategoryList().size()));
//            rvSort.setAdapter(categoryAdapter);
//            categoryAdapter.notifyDataSetChanged();
//            if (this.filter.getCategoryList().size() > 3) {
//                z = true;
//            } else {
//                z = false;
//            }
//            addViewHolder.setVisible(R.id.ivMoreFlag, z);
//            addViewHolder.setSelected(R.id.ivMoreFlag, false);
//            addViewHolder.setOnClickListener(R.id.ivMoreFlag, new OnClickListener() {
//                public void onClick(View v) {
//                    if (categoryAdapter.getDatas().size() == 3) {
//                        addViewHolder.setSelected(R.id.ivMoreFlag, true);
//                        categoryAdapter.setDatas(SearchGoodSelectDialog.this.filter.getCategoryList());
//                        categoryAdapter.notifyDataSetChanged();
//                        return;
//                    }
//                    addViewHolder.setSelected(R.id.ivMoreFlag, false);
//                    categoryAdapter.setDatas(SearchGoodSelectDialog.this.filter.getCategoryList().subList(0, 3));
//                    categoryAdapter.notifyDataSetChanged();
//                }
//            });
//            this.llCategory.addView(addViewHolder.getCustomView());
//            return;
//        }
//        this.llCategory.removeAllViews();
//    }
//
//    private void setBrandShow() {
//        if (this.filter.getBrandList() != null && this.filter.getBrandList().size() > 0) {
//            boolean z;
//            this.llBrand.removeAllViews();
//            final AddViewHolder addViewHolder = new AddViewHolder(this.context, R.layout.select_dialog_sort_item);
//            addViewHolder.setText(R.id.tvSortName, "品牌");
//            RecyclerView rvSort = (RecyclerView) addViewHolder.getView(R.id.rvSort);
//            rvSort.setLayoutManager(new GridLayoutManager(this.context, 3));
//            final RRecyclerAdapter<Brand> categoryRRecyclerAdapter = new RRecyclerAdapter<Brand>(this.context, R.layout.select_dialog_sort_recycler_item) {
//                public void convert(RecyclerHolder holder, final Brand o) {
//                    final TextView btnSpec = (TextView) holder.getView(R.id.btnSpec);
//                    btnSpec.setText(o.getBrandName());
//                    btnSpec.setActivated(SearchGoodSelectDialog.this.selectedBrandList.contains(o));
//                    btnSpec.setOnClickListener(new OnClickListener() {
//                        public void onClick(View v) {
//                            if (btnSpec.isActivated()) {
//                                btnSpec.setActivated(false);
//                                SearchGoodSelectDialog.this.selectedBrandList.remove(o);
//                            } else if (SearchGoodSelectDialog.this.selectedBrandList.size() < 5) {
//                                btnSpec.setActivated(true);
//                                SearchGoodSelectDialog.this.selectedBrandList.add(o);
//                            } else {
//                                TToast.showShort(AnonymousClass3.this.context, (CharSequence) "筛选个数不能超过5个！");
//                            }
//                        }
//                    });
//                }
//            };
//            categoryRRecyclerAdapter.setDatas(this.filter.getBrandList().subList(0, this.filter.getBrandList().size() > 3 ? 3 : this.filter.getBrandList().size()));
//            rvSort.setAdapter(categoryRRecyclerAdapter);
//            categoryRRecyclerAdapter.notifyDataSetChanged();
//            if (this.filter.getBrandList().size() > 3) {
//                z = true;
//            } else {
//                z = false;
//            }
//            addViewHolder.setVisible(R.id.ivMoreFlag, z);
//            addViewHolder.setSelected(R.id.ivMoreFlag, false);
//            addViewHolder.setOnClickListener(R.id.ivMoreFlag, new OnClickListener() {
//                public void onClick(View v) {
//                    if (categoryRRecyclerAdapter.getDatas().size() == 3) {
//                        addViewHolder.setSelected(R.id.ivMoreFlag, true);
//                        categoryRRecyclerAdapter.setDatas(SearchGoodSelectDialog.this.filter.getBrandList());
//                        categoryRRecyclerAdapter.notifyDataSetChanged();
//                        return;
//                    }
//                    addViewHolder.setSelected(R.id.ivMoreFlag, false);
//                    categoryRRecyclerAdapter.setDatas(SearchGoodSelectDialog.this.filter.getBrandList().subList(0, 3));
//                    categoryRRecyclerAdapter.notifyDataSetChanged();
//                }
//            });
//            this.llBrand.addView(addViewHolder.getCustomView());
//        }
//    }
//
//    private void setAttributeShow() {
//        if (this.filter.getAttributeList() != null) {
//            this.llSort.removeAllViews();
//            for (final Attribute attribute : this.filter.getAttributeList()) {
//                boolean z;
//                final AddViewHolder addViewHolder = new AddViewHolder(this.context, R.layout.select_dialog_sort_item);
//                addViewHolder.setText(R.id.tvSortName, attribute.getAttributeName());
//                RecyclerView rvSort = (RecyclerView) addViewHolder.getView(R.id.rvSort);
//                rvSort.setLayoutManager(new GridLayoutManager(this.context, 3));
//                final RRecyclerAdapter<AttributeValue> attrAdapter = new RRecyclerAdapter<AttributeValue>(this.context, R.layout.select_dialog_sort_recycler_item) {
//                    public void convert(RecyclerHolder holder, final AttributeValue o) {
//                        final TextView btnSpec = (TextView) holder.getView(R.id.btnSpec);
//                        btnSpec.setText(o.getAttributeValueName());
//                        btnSpec.setActivated(SearchGoodSelectDialog.this.selectAttributeList.containsValue(Integer.valueOf(attribute.getAttributeId()), Integer.valueOf(o.getAttributeValueId())));
//                        btnSpec.setOnClickListener(new OnClickListener() {
//                            public void onClick(View v) {
//                                if (btnSpec.isActivated()) {
//                                    btnSpec.setActivated(false);
//                                    SearchGoodSelectDialog.this.selectAttributeList.remove(Integer.valueOf(attribute.getAttributeId()), Integer.valueOf(o.getAttributeValueId()));
//                                } else if (SearchGoodSelectDialog.this.selectAttributeList.getValues(Integer.valueOf(attribute.getAttributeId())) == null || SearchGoodSelectDialog.this.selectAttributeList.getValues(Integer.valueOf(attribute.getAttributeId())).size() < 5) {
//                                    btnSpec.setActivated(true);
//                                    SearchGoodSelectDialog.this.selectAttributeList.put(Integer.valueOf(attribute.getAttributeId()), Integer.valueOf(o.getAttributeValueId()));
//                                } else {
//                                    TToast.showShort(AnonymousClass5.this.context, (CharSequence) "筛选个数不能超过5个！");
//                                }
//                            }
//                        });
//                    }
//                };
//                attrAdapter.setDatas(attribute.getAttributeValueList().subList(0, attribute.getAttributeValueList().size() > 3 ? 3 : attribute.getAttributeValueList().size()));
//                rvSort.setAdapter(attrAdapter);
//                attrAdapter.notifyDataSetChanged();
//                if (attribute.getAttributeValueList().size() > 3) {
//                    z = true;
//                } else {
//                    z = false;
//                }
//                addViewHolder.setVisible(R.id.ivMoreFlag, z);
//                addViewHolder.setSelected(R.id.ivMoreFlag, false);
//                addViewHolder.setOnClickListener(R.id.ivMoreFlag, new OnClickListener() {
//                    public void onClick(View v) {
//                        if (attrAdapter.getDatas().size() == 3) {
//                            addViewHolder.setSelected(R.id.ivMoreFlag, true);
//                            attrAdapter.setDatas(attribute.getAttributeValueList());
//                            attrAdapter.notifyDataSetChanged();
//                            return;
//                        }
//                        addViewHolder.setSelected(R.id.ivMoreFlag, false);
//                        attrAdapter.setDatas(attribute.getAttributeValueList().subList(0, 3));
//                        attrAdapter.notifyDataSetChanged();
//                    }
//                });
//                this.llSort.addView(addViewHolder.getCustomView());
//            }
//        }
//    }
//
//    private void setFormatListener() {
//        this.etPriceFrom.setOnFocusChangeListener(new OnFocusChangeListener() {
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!Common.isEmptyEditText(SearchGoodSelectDialog.this.etPriceFrom)) {
//                    SearchGoodSelectDialog.this.etPriceFrom.setText(ShopHelper.getPriceString(new BigDecimal(SearchGoodSelectDialog.this.etPriceFrom.getText().toString())));
//                }
//            }
//        });
//        this.etPriceTo.setOnFocusChangeListener(new OnFocusChangeListener() {
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!Common.isEmptyEditText(SearchGoodSelectDialog.this.etPriceTo)) {
//                    SearchGoodSelectDialog.this.etPriceTo.setText(ShopHelper.getPriceString(new BigDecimal(SearchGoodSelectDialog.this.etPriceTo.getText().toString())));
//                }
//            }
//        });
//    }
//
//    @OnClick({2131558866})
//    public void btnRevertClick() {
//        this.etPriceFrom.setText("");
//        this.etPriceTo.setText("");
//        this.etNumBatch.setText("");
//        this.btnSaleDefault.setChecked(true);
//        this.btnStoreDefault.setChecked(true);
//        this.btnGiftDefault.setChecked(true);
//        this.selectedBrandList.clear();
//        this.selectAttributeList.clear();
//        setSortShow();
//    }
//
//    @OnClick({2131558879})
//    public void btnSelectSaveClick() {
//        String selectValue = "";
//        if (Common.isNotEmpty(Common.getText(this.etPriceFrom), Common.getText(this.etPriceTo))) {
//            selectValue = selectValue + "&price=" + Common.getText(this.etPriceFrom) + SocializeConstants.OP_DIVIDER_MINUS + Common.getText(this.etPriceTo);
//        }
//        StringBuilder append = new StringBuilder().append(selectValue).append("&promotion=");
//        String str = this.btnXianshi.isChecked() ? "1" : this.btnPreShow.isChecked() ? "2" : "0";
//        EventBus.getDefault().post(new SortString(((((((append.append(str).toString() + (Common.isEmpty(Common.getText(this.etNumBatch)) ? "" : "&batch=" + Common.getText(this.etNumBatch))) + (this.btnOwnShop.isChecked() ? "&own=1" : "")) + (this.btnOtherShop.isChecked() ? "&own=0" : "")) + (this.btnGift.isChecked() ? "&gift=1" : "")) + this.cat) + getBrandString()) + getAttributeValue()));
//        dismiss();
//    }
//
//    private String getBrandString() {
//        String brandString = "&brand=";
//        String value = "";
//        if (this.selectedBrandList.size() > 0) {
//            for (Brand brand : this.selectedBrandList) {
//                value = value + brand.getBrandId() + Separators.COMMA;
//            }
//            value = value.substring(0, value.length() - 1);
//        }
//        return value.length() > 0 ? brandString + value : "";
//    }
//
//    private String getAttributeValue() {
//        String attributeString = "&attr=";
//        String value = "";
//        for (Integer i : this.selectAttributeList.keySet()) {
//            value = value + i + SocializeConstants.OP_DIVIDER_MINUS;
//            for (Integer v : this.selectAttributeList.getValues(i)) {
//                value = value + v + Separators.COLON;
//            }
//            if (this.selectAttributeList.getValues(i).size() > 0) {
//                value = value.substring(0, value.length() - 1);
//            }
//            value = value + Separators.COMMA;
//        }
//        if (value.length() <= 0) {
//            return value;
//        }
//        return attributeString + value.substring(0, value.length() - 1);
//    }
//
//    @OnClick({2131558871, 2131558872, 2131558874, 2131558870, 2131558873, 2131558875, 2131558876, 2131558877})
//    public void onSortClick(View view) {
//        switch (view.getId()) {
//            case R.id.btnSaleDefault:
//                this.btnSaleDefault.setChecked(true);
//                return;
//            case R.id.btnXianshi:
//                this.btnXianshi.setChecked(true);
//                return;
//            case R.id.btnPreShow:
//                this.btnPreShow.setChecked(true);
//                return;
//            case R.id.btnStoreDefault:
//                this.btnStoreDefault.setChecked(true);
//                return;
//            case R.id.btnOwnShop:
//                this.btnOwnShop.setChecked(true);
//                return;
//            case R.id.btnOtherShop:
//                this.btnOtherShop.setChecked(true);
//                return;
//            case R.id.btnGiftDefault:
//                this.btnGiftDefault.setChecked(true);
//                return;
//            case R.id.btnGift:
//                this.btnGift.setChecked(true);
//                return;
//            default:
//                return;
//        }
//    }
//
//    @OnClick({2131558601})
//    public void onBackClick(View view) {
//        switch (view.getId()) {
//            case R.id.btnBack:
//                dismiss();
//                return;
//            default:
//                return;
//        }
//    }
//
//    public void setOnDismissListener(OnDismissListener listener) {
//        super.setOnDismissListener(listener);
//        ButterKnife.unbind(this);
//    }
//}
