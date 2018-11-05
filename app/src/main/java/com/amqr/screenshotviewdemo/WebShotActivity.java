package com.amqr.screenshotviewdemo;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * User: LJM
 * Date&Time: 2017-04-20 & 21:56
 * Describe: Describe Text
 */
@RuntimePermissions
public class WebShotActivity extends BaseActivity {
    private WebView mWeb;
    private ImageView mIvResult;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        mWeb = (WebView) findViewById(R.id.mWeb);
        mIvResult = (ImageView) findViewById(R.id.mIvResult);


        mWeb.setDrawingCacheEnabled(true);


        //支持javascript
        mWeb.getSettings().setJavaScriptEnabled(true);
        mWeb.getSettings().setUseWideViewPort(true);
        mWeb.getSettings().setLoadWithOverviewMode(true);
        //支持页面缩放
        //webView.getSettings().setBuiltInZoomControls(true);
        //提升渲染优先级
        //webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        //不加载网络中的图片资源
        //webView.getSettings().setBlockNetworkImage(true);
        //HTML5 Cache
        //*mWeb.getSettings().setDomStorageEnabled(true);
        mWeb.getSettings().setAllowFileAccess(true);
        mWeb.getSettings().setAppCacheEnabled(true);
        //优先从本地cache中载入，其次才是从网络中载入，即使内容已经过期*//*
        mWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                //Android TV中可以在这里返回true，按键交由onKeyDown方法处理
                return super.shouldOverrideKeyEvent(view, event);
            }
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });


        mWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

            }
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });

        mWeb.loadUrl("https://m.baidu.com/?from=844b&vit=fps");


        findViewById(R.id.mTvShot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebShotActivityPermissionsDispatcher.storageNeedWithCheck(WebShotActivity.this);
            }
        });


    }

    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void storageNeed() {
        int viewWidth = mWeb.getMeasuredWidth();
        int viewHeight = mWeb.getMeasuredHeight();
        if (viewWidth > 0 && viewHeight > 0) {
            Bitmap b = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.RGB_565);
            Canvas cvs = new Canvas(b);
            mWeb.draw(cvs);
            mIvResult.setImageBitmap(b);
            SimpleUtils.saveBitmapToSdCard(WebShotActivity.this,b,"styleWeb");
        }

    }


    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void storageRationale(final PermissionRequest request) {
        showRationaleDialog("存储权限是本程序必不可少的权限，请开启",request);
    }

    @OnPermissionDenied({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void storageDenied() {
        openAppSetting("您拒绝了存储权限，请授权");
    }

    @OnNeverAskAgain({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void storageAsk() {
        openAppSetting("您拒绝了存储权限，请授权");
    }
}
