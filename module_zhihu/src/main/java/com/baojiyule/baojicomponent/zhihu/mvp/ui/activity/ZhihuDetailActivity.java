package com.baojiyule.baojicomponent.zhihu.mvp.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baojiyule.baojicomponent.zhihu.R;
import com.baojiyule.baojicomponent.zhihu.R2;
import com.baojiyule.baojicomponent.zhihu.app.ZhihuConstants;
import com.baojiyule.baojicomponent.zhihu.di.component.DaggerZhihuDetailComponent;
import com.baojiyule.baojicomponent.zhihu.mvp.contract.ZhihuDetailContract;
import com.baojiyule.baojicomponent.zhihu.mvp.model.entity.ZhihuDetailBean;
import com.baojiyule.baojicomponent.zhihu.mvp.presenter.ZhihuDetailPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import javax.inject.Inject;

import butterknife.BindView;
import me.jessyan.armscomponent.commonsdk.utils.HtmlUtil;
import me.jessyan.armscomponent.commonservice.RouterHub;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/30/2019 10:09
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Route(path = RouterHub.ZHIHU_DETAILACTIVITY)
public class ZhihuDetailActivity extends BaseActivity<ZhihuDetailPresenter> implements ZhihuDetailContract.View {
    @BindView(R2.id.webView)
    WebView mWebView;
    @Inject
    Dialog mDialog;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerZhihuDetailComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.zhihu_activity_detail; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initWebView();
        loadTitle();
        mPresenter.requestDetailInfo(getIntent().getIntExtra(ZhihuConstants.DETAIL_ID, 0));
    }

    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        mDialog.dismiss();
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void shonContent(ZhihuDetailBean bean) {
        String htmlData = HtmlUtil.createHtmlData(bean.getBody(), bean.getCss(), bean.getJs());
        mWebView.loadData(htmlData, HtmlUtil.MIME_TYPE, ZhihuConstants.ENCODING);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    private void initWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    private void loadTitle() {
        String title = getIntent().getStringExtra(ZhihuConstants.DETAIL_TITLE);
        if (title.length() > 10) {
            title = title.substring(0, 10) + " ...";
        }
        setTitle(title);
    }
}
