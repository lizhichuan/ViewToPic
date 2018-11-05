package com.amqr.screenshotviewdemo;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class StyleOneActivity extends BaseActivity {


    private RelativeLayout mRlViewTest;
    private ImageView mIvResult;
    private TextView mTvShot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style_one);
        mRlViewTest = (RelativeLayout) findViewById(R.id.mRlViewTest);
        mIvResult = (ImageView) findViewById(R.id.mIvResult);
        mTvShot = (TextView) findViewById(R.id.mTvShot);

        mTvShot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // storageNeedPer 原本涉及到纯粹权限的代码，我们放到storageNeedPer这个方法里面
                StyleOneActivityPermissionsDispatcher.storageNeedPerWithCheck(StyleOneActivity.this);
            }
        });
    }

    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void storageNeedPer() {
        // 那些权限涉及到存储权限的，写在这里
        Bitmap cacheBitmapFromView = SimpleUtils.getCacheBitmapFromView(mRlViewTest);
        mIvResult.setImageBitmap(cacheBitmapFromView);
        SimpleUtils.saveBitmapToSdCard(StyleOneActivity.this,cacheBitmapFromView,"styleOne");
    }



    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void storageNeedShowRat(final PermissionRequest request) {
        // 解释为什么需要这个权限
        showRationaleDialog("存储权限是本程序必不可少的权限，请开启",request);
    }

    @OnPermissionDenied({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void storageDenied() {
        // 如果用户不授予某权限时调用的方法
        openAppSetting("您拒绝了存储权限，请授权");

    }

    @OnNeverAskAgain({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void storageAsk() {
        //如果用户选择了让设备“不再询问”，而调用的方法
        // 如果用户不授予某权限时调用的方法
        openAppSetting("您拒绝了存储权限，请授权");
    }






}
