package com.xinyuan.xyshop.ui.home.news;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.NewsModel;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.XYWebView;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;

import butterknife.BindView;

/**
 * Created by fx on 2017/8/19.
 */

public class NewsDetailActivity extends BaseActivity<BasePresenter> {
    @BindView(R.id.tv_new_title)
    TextView tv_new_title;
    @BindView(R.id.tv_new_time)
    TextView tv_new_time;
    @BindView(R.id.fragmenlayout)
    FrameLayout fragmenlayout;
    @BindView(R.id.toolbar_iv)
    Toolbar toolbar_iv;
    @BindView(R.id.tv_header_center)
    TextView tv_header_center;
    @BindView(R.id.iv_header_left)
    ImageView iv_header_left;
    private String id;
    private String title;
    private XYWebView mWebView;
    private ProgressDialog mProgressDialog;


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_news_detail;
    }

    @Override
    public void initView() {
        SystemBarHelper.immersiveStatusBar(this, 0); //设置状态栏透明
        SystemBarHelper.setHeightAndPadding(this, toolbar_iv);
        CommUtil.initToolBar(NewsDetailActivity.this, iv_header_left, null);


    }

    @Override
    public void initData() {
        id = getIntent().getStringExtra(Constants.NEWID);
        title = getIntent().getStringExtra(Constants.NEWTITLE);
        XLog.v(title);
        tv_header_center.setText(title);
        mWebView = new XYWebView(this.getApplicationContext(), null);
        mProgressDialog = new ProgressDialog(this);
        fragmenlayout.addView(mWebView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT,
                FrameLayout.LayoutParams.FILL_PARENT));
        if (!XEmptyUtils.isEmpty(id)) {
            OkGo.<LzyResponse<NewsModel>>post(Urls.URL_ARTICLE_DETAIL)
                    .tag(this)
                    .params("id", id)
                    .execute(new JsonCallback<LzyResponse<NewsModel>>() {
                        @Override
                        public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<NewsModel>> response) {
                            if (HttpUtil.handleResponse(NewsDetailActivity.this, response.body())) {
                                loadContent(response.body().datas);
                            }
                        }

                        @Override
                        public void onError(com.lzy.okgo.model.Response<LzyResponse<NewsModel>> response) {
                            HttpUtil.handleError(NewsDetailActivity.this, response);
                        }
                    });
        }
    }

    private void loadContent(NewsModel newsModel) {
        tv_new_title.setText(newsModel.getTitle());
        tv_new_time.setText(newsModel.getTime());
        if (!XEmptyUtils.isEmpty(newsModel.getContent())) {
            mWebView.setFocusable(false);
            this.mWebView.loadDataWithBaseURL(null, "<!DOCTYPE HTML>\n" +
                    "<html>\n" +
                    "<head> \n" +
                    "<meta name=”viewport” content=”width=device-width, initial-scale=1″ />" +
                    "<style> \n" +
                    "img{width:100%}" +
                    "body {font: normal 200% Helvetica, Arial, sans-serif;}" +
                    "</style> \n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div>\t" +
                    newsModel.getContent() +
                    " </div>  \n" +
                    " </body>\n" +
                    "</html>", "text/html", "utf-8", null);
            mWebView.setWebViewClient(new WebViewClient() {

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    mWebView.loadUrl(url);
                    return true;
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    mProgressDialog.show();
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    mProgressDialog.hide();
                }
            });

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView.removeAllViews();
        }


    }
}
