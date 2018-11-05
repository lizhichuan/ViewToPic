package com.amqr.screenshotviewdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

/**
 * User: LJM
 * Date&Time: 2017-04-21 & 19:36
 * Describe: Describe Text
 */
public class StyleScrollView extends BaseActivity {

    private ImageView mIvRet;
    private ScrollView mScrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        mIvRet = (ImageView) findViewById(R.id.mIvRet);
        mScrollView = (ScrollView) findViewById(R.id.mScrollView);

        findViewById(R.id.mTvShot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = SimpleUtils.shotScrollView(mScrollView);
                mIvRet.setImageBitmap(bitmap);
            }
        });
    }
}
