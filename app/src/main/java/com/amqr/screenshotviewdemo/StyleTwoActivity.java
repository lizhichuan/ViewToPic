package com.amqr.screenshotviewdemo;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;



/**
 * User: LJM
 * Date&Time: 2017-04-20 & 21:01
 * Describe: Describe Text
 */
@RuntimePermissions
public class StyleTwoActivity extends BaseActivity{

    private TextView mTvShot;
    private ImageView mIvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style_two);
        mTvShot = (TextView) findViewById(R.id.mTvShot);
        mIvResult = (ImageView) findViewById(R.id.mIvResult);

        mTvShot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StyleTwoActivityPermissionsDispatcher.storageNeedWithCheck(StyleTwoActivity.this);
            }
        });

    }

    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void storageNeed() {
        // 本View是inflate加载而来，不是Activity的xml本身的
        View view = getLayoutInflater().inflate(R.layout.item_group,null);
        ImageView mtv = (ImageView) view.findViewById(R.id.mIv);
        ViewGroup.LayoutParams upPartLayoutParams = mtv.getLayoutParams();
        int upPartMeasureHeight = View.MeasureSpec.makeMeasureSpec(upPartLayoutParams.height, View.MeasureSpec.EXACTLY);
        mtv.setImageDrawable(getResources().getDrawable(R.drawable.ccc));

        // 没有显示到界面上的view本身无大小可言，所以我们要手动指定一下
        SimpleUtils.layoutView(mtv,upPartMeasureHeight,upPartMeasureHeight);
        // View生成截图
        Bitmap cacheBitmapFromView = SimpleUtils.getCacheBitmapFromView(mtv);
        mIvResult.setImageBitmap(cacheBitmapFromView);
        // 保存bitmap到sd卡
        SimpleUtils.saveBitmapToSdCard(StyleTwoActivity.this,cacheBitmapFromView,"styleTwo");

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
