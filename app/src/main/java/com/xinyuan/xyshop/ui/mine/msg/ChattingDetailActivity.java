package com.xinyuan.xyshop.ui.mine.msg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.ChatDetailAdapter;
import com.xinyuan.xyshop.adapter.CommonFragmentPagerAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.bean.ChatItemBean;
import com.xinyuan.xyshop.bean.MsgTokenBean;
import com.xinyuan.xyshop.bean.UserInfoBean;
import com.xinyuan.xyshop.entity.MessageInfo;
import com.xinyuan.xyshop.entity.MsgMultipleItem;
import com.xinyuan.xyshop.model.ChatDetailModule;
import com.xinyuan.xyshop.mvp.contract.ChatDetailView;
import com.xinyuan.xyshop.mvp.presenter.ChatDetailPresenter;
import com.xinyuan.xyshop.ui.goods.store.StoreActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.EmotionInputDetector;
import com.xinyuan.xyshop.util.GlobalOnItemClickManagerUtils;
import com.xinyuan.xyshop.widget.NoScrollViewPager;
import com.xinyuan.xyshop.widget.StateButton;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by fx on 2017/8/9.
 * 聊天详情
 */

public class ChattingDetailActivity extends BaseActivity<ChatDetailPresenter> implements ChatDetailView, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(R.id.rv_msg_detail)
    RecyclerView rv_msg;

    @BindView(R.id.edit_text)
    EditText edit_text; //输入框
    @BindView(R.id.emotion_button)
    ImageView emotionButton; //表情button
    @BindView(R.id.emotion_add)
    ImageView emotionAdd; //添加图片
    @BindView(R.id.emotion_send)
    StateButton emotionSend;    //发送
    @BindView(R.id.viewpager)
    NoScrollViewPager viewpager; //选择表情或图片
    @BindView(R.id.emotion_layout)
    RelativeLayout emotionLayout;
    @BindView(R.id.refreshLayout)
    BGARefreshLayout mRefreshLayout;
    @BindView(R.id.tv_header_center)
    TextView tv_header_center;
    @BindView(R.id.iv_header_left)
    ImageView iv_header_left;
    @BindView(R.id.iv_header_right)
    ImageView iv_header_right;


    private ArrayList<Fragment> fragments; //frgaments集合
    private ChatEmotionFragment chatEmotionFragment;//表情选择fragment
    private ChatFunctionFragment chatFunctionFragment;//图片选择fragment
    private EmotionInputDetector mDetector;//表情输入控制
    private LinearLayoutManager layoutManager;


    private CommonFragmentPagerAdapter adapter;
    private ChatDetailAdapter chatAdapter;

    private static String chattingId; //当前对话ID
    private static String user_to_Id;//对话userID
    private static String user_to_name;//对话user名字
    private static String user_to_header;//对话user头像

    private static String user_header;//个人头像

    private static int page; //当前加载页数
    private static int limit = 10; //数据条数
    private static String unique; //消息标识符号
    private static boolean isNewMsg = false;
    private String storeId;

    private List<MsgMultipleItem> MultipleList;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_chatdetail;
    }

    @Override
    public void initView() {
        this.user_to_Id = getIntent().getStringExtra("USERID");
        this.user_to_name = getIntent().getStringExtra("USERNAME");
        this.user_to_header = getIntent().getStringExtra("USERHEAD");
        this.chattingId = getIntent().getStringExtra("CHATTINGID");
        this.storeId = getIntent().getStringExtra("STOREID");

        if (!XEmptyUtils.isEmpty(chattingId)) {
            MyShopApplication.chatting_ID = Long.parseLong(chattingId);
        }

        if (!XEmptyUtils.isSpace(storeId)) {
            iv_header_right.setVisibility(View.VISIBLE);
        } else {
            iv_header_right.setVisibility(View.GONE);
        }
        XLog.v("user_to_id:" + user_to_Id + ":chattingId：" + chattingId + "user_to_header:" + user_to_header);
        tv_header_center.setText("正在与 " + user_to_name + "沟通");
        CommUtil.initToolBar(this, iv_header_left, null);
        initRefreshLayout();
    }


    @Override
    public void initData() {
        mPresenter.getInfo();
        Long s = System.currentTimeMillis();
        unique = String.valueOf(s);
        MultipleList = new ArrayList<>();
        fragments = new ArrayList<>();
        chatEmotionFragment = new ChatEmotionFragment();
        fragments.add(chatEmotionFragment);
        chatFunctionFragment = new ChatFunctionFragment();
        fragments.add(chatFunctionFragment);
        adapter = new CommonFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);
        mDetector = EmotionInputDetector.with(this)
                .setEmotionView(emotionLayout)
                .setViewPager(viewpager)
                .bindToContent(mRefreshLayout)
                .bindToEditText(edit_text)
                .bindToEmotionButton(emotionButton)
                .bindToAddButton(emotionAdd)
                .bindToSendButton(emotionSend)
                .build();
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_msg.setLayoutManager(layoutManager);

    }

    private void initRefreshLayout() {
        mRefreshLayout.setDelegate(this);
        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(this, false);
        refreshViewHolder.setRefreshingText("");
        refreshViewHolder.setPullDownRefreshText("");
        refreshViewHolder.setReleaseRefreshText("");
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);
    }

    @Override
    public void initListener() {
        GlobalOnItemClickManagerUtils globalOnItemClickListener = GlobalOnItemClickManagerUtils.getInstance(this);
        globalOnItemClickListener.attachToEditText(edit_text);
        iv_header_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> params = new HashMap();
                params.put("STOREID", storeId);
                CommUtil.gotoActivity(ChattingDetailActivity.this, StoreActivity.class, false, params);
            }
        });
        rv_msg.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        chatAdapter.handler.removeCallbacksAndMessages(null);
                        chatAdapter.notifyDataSetChanged();
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        chatAdapter.handler.removeCallbacksAndMessages(null);
                        mDetector.hideEmotionLayout(false);
                        mDetector.hideSoftInput();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
    }

    private static String lastLogId;

    @Override
    public void showMsgList(ChatDetailModule chatDetailModule) {
        chatAdapter = new ChatDetailAdapter(this, MultipleList);
        rv_msg.setAdapter(chatAdapter);
        if (!XEmptyUtils.isEmpty(chatDetailModule)) {//若首次获取消息列表为空，则为新对话
            if (isNewMsg) {
                chattingId = String.valueOf(chatDetailModule.getChattingList().get(0).getChatting_id());
                lastLogId = String.valueOf(chatDetailModule.getChattingList().get(0).getChattinglog_id());
                MyShopApplication.chatting_ID = Long.parseLong(chattingId);
                MyShopApplication.chatting_logID = chatDetailModule.getLogId();
            } else {
                chattingId = String.valueOf(chatDetailModule.getChattingList().get(0).getChatting_id());
                MyShopApplication.chatting_ID = Long.parseLong(chattingId);
                MsgMultipleItem msgMultipleItem = new MsgMultipleItem();
                msgMultipleItem.setItemType(MsgMultipleItem.CHAT_TIME_CENTER);
                lastLogId = String.valueOf(chatDetailModule.getChattingList().get(0).getChattinglog_id());
                msgMultipleItem.setTime(chatDetailModule.getChattingList().get(0).getChatting_addTime());
                chatAdapter.addData(msgMultipleItem);
                for (ChatItemBean itemBean : chatDetailModule.getChattingList()) {
                    MsgMultipleItem message = new MsgMultipleItem();

                    if (!XEmptyUtils.isEmpty(itemBean.getChatting_content())) {
                        message.setContent(itemBean.getChatting_content());
                    }
                    if (!XEmptyUtils.isEmpty(itemBean.getChatting_image()) && !XEmptyUtils.isSpace(itemBean.getChatting_image())) {
                        message.setImageUrl(itemBean.getChatting_image());

                    }
                    if (itemBean.getChatting_user() != MyShopApplication.userId) {
                        message.setItemType(MsgMultipleItem.CHAT_ITEM_LEFT);
                        message.setHeader(user_to_header);
                    } else {
                        message.setItemType(MsgMultipleItem.CHAT_ITEM_RIGHT);
                        message.setHeader(user_header);
                    }
                    MultipleList.add(message);
                }
                rv_msg.setAdapter(chatAdapter);
                rv_msg.smoothScrollToPosition(chatAdapter.getItemCount() + 1);
                MyShopApplication.chatting_logID = chatDetailModule.getLogId();
                startService();
                MsgTokenBean msgTokenBean = new MsgTokenBean(chattingId, String.valueOf(lastLogId));
                EventBus.getDefault().post(msgTokenBean);
                isNewMsg = false;
            }
        } else {
            isNewMsg = true;
        }
    }

    private int MsgPosition = 1;

    @Override
    public void addHistory(ChatDetailModule chatListModule) {
        if (!XEmptyUtils.isEmpty(chatListModule)) {
            XLog.list(chatListModule.getChattingList());
            for (ChatItemBean itemBean : chatListModule.getChattingList()) {
                MsgMultipleItem message = new MsgMultipleItem();
                if (!XEmptyUtils.isEmpty(itemBean.getChatting_content())) {
                    message.setContent(itemBean.getChatting_content());
                }
                if (!XEmptyUtils.isEmpty(itemBean.getChatting_image()) && !XEmptyUtils.isSpace(itemBean.getChatting_image())) {
                    message.setImageUrl(itemBean.getChatting_image());

                }
                XLog.v(itemBean.toString());
                if (itemBean.getChatting_user() != MyShopApplication.userId) {
                    message.setItemType(MsgMultipleItem.CHAT_ITEM_LEFT);
                    message.setHeader(user_to_header);
                } else {
                    message.setItemType(MsgMultipleItem.CHAT_ITEM_RIGHT);
                    message.setHeader(user_header);
                }
                chatAdapter.addData(0, message);
            }
            MsgMultipleItem msgMultipleItem = new MsgMultipleItem();
            msgMultipleItem.setItemType(MsgMultipleItem.CHAT_TIME_CENTER);
            lastLogId = String.valueOf(chatListModule.getChattingList().get(chatListModule.getChattingList().size() - 1).getChattinglog_id());
            msgMultipleItem.setTime(chatListModule.getChattingList().get(chatListModule.getChattingList().size() - 1).getChatting_addTime());
            chatAdapter.addData(0, msgMultipleItem);
            //chatAdapter.notifyDataSetChanged();


            rv_msg.smoothScrollToPosition(chatAdapter.getData().size() - ((limit + 1) * MsgPosition) - 2);
            MsgPosition++;
        } else {
            XToast.info("没有更早的消息了");
        }
        mRefreshLayout.endRefreshing();

    }

    @Override
    public void setUserInfo(UserInfoBean userInfo) {
        user_header = userInfo.getUserPhoto();
        if (XEmptyUtils.isEmpty(chattingId)) {
            mPresenter.getChatting(null, user_to_Id, limit);//当没有chattingID传入,根据userid和对方userid获取对话
        } else {
            mPresenter.getChatting(chattingId, user_to_Id, limit);//正常获取
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getNewMsg(ChatDetailModule chatListModule) {
        if (chatListModule.getLogId() == MyShopApplication.chatting_logID) {
        } else {
            MyShopApplication.chatting_logID = chatListModule.getLogId();
            for (ChatItemBean itemBean : chatListModule.getChattingList()) {
                MsgMultipleItem message = new MsgMultipleItem(MsgMultipleItem.CHAT_ITEM_LEFT);
                if (!XEmptyUtils.isEmpty(itemBean.getChatting_content())) {
                    message.setContent(itemBean.getChatting_content());
                }
                if (!XEmptyUtils.isEmpty(itemBean.getChatting_image()) && !XEmptyUtils.isSpace(itemBean.getChatting_image())) {
                    message.setImageUrl(itemBean.getChatting_image());
                }
                message.setHeader(user_to_header);
                chatAdapter.addData(message);
            }
            rv_msg.smoothScrollToPosition(chatAdapter.getItemCount() + 1);
            chatAdapter.notifyDataSetChanged();
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void postMsg(MessageInfo message) {
        MsgMultipleItem Mes = new MsgMultipleItem(MsgMultipleItem.CHAT_ITEM_RIGHT);
        if (!XEmptyUtils.isEmpty(message.getContent())) {
            Mes.setContent(message.getContent());
        }
        if (!XEmptyUtils.isEmpty(message.getImageUrl())) {
            Mes.setImageUrl(message.getImageUrl());
        }
        Mes.setHeader(user_header);
        Mes.setItemType(MsgMultipleItem.CHAT_ITEM_RIGHT);
        Mes.setUnique(unique);
        Mes.setSendState(MsgMultipleItem.CHAT_ITEM_SENDING);
        chatAdapter.addData(Mes);
        rv_msg.scrollToPosition(chatAdapter.getItemCount() - 1);
        if (!XEmptyUtils.isEmpty(Mes.getImageUrl()) && !XEmptyUtils.isSpace(Mes.getImageUrl())) {
            File file = new File(Mes.getImageUrl());
            mPresenter.postChatFile(user_to_Id, Mes.getContent(), file, unique);
        } else {
            mPresenter.postChat(user_to_Id, Mes.getContent(), unique);
        }

    }

    @Override
    public void sendState(boolean state, String unique) {
        if (state) {
            for (MsgMultipleItem messageInfo : MultipleList) {
                if (!XEmptyUtils.isEmpty(messageInfo.getUnique())) {
                    if (messageInfo.getUnique().equals(unique)) {
                        messageInfo.setSendState(MsgMultipleItem.CHAT_ITEM_SEND_SUCCESS);
                        adapter.notifyDataSetChanged();
                    }
                }

            }
        } else {
            for (MsgMultipleItem messageInfo : MultipleList) {
                if (messageInfo.getUnique().equals(unique)) {
                    messageInfo.setSendState(MsgMultipleItem.CHAT_ITEM_SEND_ERROR);
                    adapter.notifyDataSetChanged();

                }
            }

        }

        if (isNewMsg) {
            mPresenter.getChatting(null, user_to_Id, 20);
        }
        chatAdapter.notifyDataSetChanged();

    }

    @Override
    public void showState(int state) {

    }


    @Override
    public void onBackPressedSupport() {
        if (!mDetector.interceptBackPress()) {
            super.onBackPressedSupport();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEventBus(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService();
        unregisterEventBus(this);
    }


    @Override
    public LifecycleTransformer<ChatDetailModule> bindLife() {
        return this.<ChatDetailModule>bindUntilEvent(ActivityEvent.DESTROY);
    }


    @Override
    protected ChatDetailPresenter createPresenter() {
        return new ChatDetailPresenter(this, this);
    }


    private void startService() {
        Intent i = new Intent();
        i.setAction("com.xinyuan.xyshop.service.ChatService");
        i.setPackage(getPackageName());
        Bundle bundle = new Bundle();
        bundle.putString("taskName", "chatCheck");
        i.putExtras(bundle);
        startService(i);
    }

    private void stopService() {
        Intent i = new Intent();
        i.setAction("com.xinyuan.xyshop.service.ChatService");
        i.setPackage(getPackageName());
        Bundle bundle = new Bundle();
        bundle.putString("taskName", "stopService");
        i.putExtras(bundle);
        startService(i);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.getHistory(chattingId, lastLogId, limit);
            }

        }, 500);


    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

}
