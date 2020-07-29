package com.xinyuan.xyshop.ui.mine.msg;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.ChatListAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.bean.ChatListItemBean;
import com.xinyuan.xyshop.model.ChatListModule;
import com.xinyuan.xyshop.mvp.contract.StoreMsgView;
import com.xinyuan.xyshop.mvp.presenter.StoreMsgPresenter;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.widget.RecycleViewDivider;
import com.xinyuan.xyshop.widget.dialog.color.ColorDialog;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.xinyuan.xyshop.util.CommUtil.getInAnimationTest;
import static com.xinyuan.xyshop.util.CommUtil.getOutAnimationTest;

/**
 * Created by Administrator on 2017/6/5.
 */

public class StoreMsgFragment extends BaseFragment<StoreMsgPresenter> implements StoreMsgView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_msg)
    RecyclerView rv_msg;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.lodingView)
    XLoadingView lodingView;


    ColorDialog colorDialog;
    private ChatListAdapter msgAdapter;
    private int page = 1;
    private int limit = 20;

    @Override
    public void initView(View rootView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(1);
        rv_msg.setLayoutManager(linearLayoutManager);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
    }

    @Override
    public void initData() {
        mPresenter.getMsgList(1, limit);
        lodingView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getMsgList(1, limit);

            }
        });
    }

    @Override
    public void showMsgList(ChatListModule chatList) {
        if (XEmptyUtils.isEmpty(msgAdapter)) {
            if (!XEmptyUtils.isEmpty(chatList) && !XEmptyUtils.isEmpty(chatList.getChattingList())) {
                initList(chatList.getChattingList());
                page++;
            } else {
                showState(3);
            }
        } else {
            if (XEmptyUtils.isEmpty(chatList.getChattingList())) {
                if (page == 1) {
                    showState(3);
                } else {
                    msgAdapter.loadMoreEnd();
                }

            } else {
                if (page == 1) {
                    msgAdapter.setNewData(chatList.getChattingList());
                    mSwipeRefreshLayout.setRefreshing(false);
                    msgAdapter.notifyDataSetChanged();
                    msgAdapter.setEnableLoadMore(true);
                    showState(1);

                } else {
                    msgAdapter.addData(chatList.getChattingList());
                    mSwipeRefreshLayout.setRefreshing(false);
                    msgAdapter.notifyDataSetChanged();
                    msgAdapter.loadMoreComplete();
                    showState(1);
                }
                page++;

            }


        }

    }


    private void initList(final List<ChatListItemBean> data) {
        if (XEmptyUtils.isEmpty(msgAdapter)) {
            if (XEmptyUtils.isEmpty(data)) {
                lodingView.showEmpty();
            } else {
                this.msgAdapter = new ChatListAdapter(R.layout.fragment_msg_item, data);
                rv_msg.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.VERTICAL));
                rv_msg.setAdapter(msgAdapter);
                msgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                        Map<String, String> params = new HashMap();
                        params.put("USERNAME", msgAdapter.getData().get(position).getUserName());
                        params.put("USERHEAD", msgAdapter.getData().get(position).getChatting_image());
                        params.put("CHATTINGID", String.valueOf(msgAdapter.getData().get(position).getChatting_id()));
                        params.put("USERID", String.valueOf(msgAdapter.getData().get(position).getChatting_touser_id()));
                        params.put("STOREID", String.valueOf(msgAdapter.getData().get(position).getChatting_storeId()));
                        CommUtil.gotoActivity(getActivity(), ChattingDetailActivity.class, false, params);
                    }
                });
                msgAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {
                        colorDialog = new ColorDialog(getContext());
                        colorDialog.setTitle("删除消息");
                        colorDialog.setContentText("确定删除此消息吗？");
                        colorDialog.setAnimationEnable(true);
                        colorDialog.setAnimationIn(getInAnimationTest(getActivity()));
                        colorDialog.setAnimationOut(getOutAnimationTest(getActivity()));
                        colorDialog.setPositiveListener("确定", new ColorDialog.OnPositiveListener() {
                            @Override
                            public void onClick(ColorDialog dialog) {
                                mPresenter.deteleMsg(MyShopApplication.userId, data.get(position).getChatting_id());
                                msgAdapter.remove(position);
                                msgAdapter.notifyDataSetChanged();


                            }
                        })
                                .setNegativeListener("取消", new ColorDialog.OnNegativeListener() {
                                    @Override
                                    public void onClick(ColorDialog dialog) {
                                        Toast.makeText(getActivity(), dialog.getNegativeText().toString(), Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                }).show();
                        return false;
                    }
                });
                msgAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
                msgAdapter.setOnLoadMoreListener(this, rv_msg);
                lodingView.showContent();
            }
        } else {
            if (XEmptyUtils.isEmpty(data)) {
                if (page == 1) {
                    lodingView.showEmpty();
                } else {
                    msgAdapter.loadMoreEnd();

                }
            } else {
                if (page == 1) {
                    msgAdapter.setNewData(data);
                    mSwipeRefreshLayout.setRefreshing(false);
                    msgAdapter.setEnableLoadMore(true);
                } else {
                    msgAdapter.addData(data);
                    mSwipeRefreshLayout.setRefreshing(false);
                    msgAdapter.loadMoreComplete();
                }
            }

        }


    }

    @Override
    public void showDeteleState() {
        colorDialog.dismiss();

    }

    @Override
    protected StoreMsgPresenter createPresenter() {
        return new StoreMsgPresenter(this, getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_msg;

    }


    @Override
    public LifecycleTransformer<ChatListModule> bindLife() {
        return this.<ChatListModule>bindUntilEvent(FragmentEvent.DESTROY_VIEW);
    }

    @Override
    public void onResume() {
        if (!XEmptyUtils.isEmpty(msgAdapter)) {
            page = 1;
            mPresenter.getMsgList(page, limit);

        }
        super.onResume();
    }

    @Override
    public void showState(int state) {
        switch (state) {
            case 0:
                lodingView.showLoading();
                break;
            case 1:
                lodingView.showContent();
                break;
            case 2:
                lodingView.showError();
                break;
            case 3:
                lodingView.showEmpty();
                break;

        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                mPresenter.getMsgList(page, limit);
            }
        }, 500);
    }

    @Override
    public void onLoadMoreRequested() {
        rv_msg.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.getMsgList(page, limit);
            }

        }, 500);
    }
}
