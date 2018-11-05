package com.amqr.screenshotviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * User: LJM
 * Date&Time: 2017-04-20 & 16:10
 * Describe: Describe Text
 */
public class IndexActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        findViewById(R.id.mTvShotStyleOne).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IndexActivity.this,StyleOneActivity.class));
            }
        });

        findViewById(R.id.mTvShotStyleTwo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IndexActivity.this,StyleTwoActivity.class));
            }
        });

        findViewById(R.id.mTvWeb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IndexActivity.this,WebShotActivity.class));
            }
        });

        findViewById(R.id.mTvScroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IndexActivity.this,StyleScrollView.class));
            }
        });


        findViewById(R.id.mTvLv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IndexActivity.this,StyleLvActivity.class));
            }
        });

        findViewById(R.id.mTvRv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IndexActivity.this,StyleRecyclerView.class));
            }
        });

    }


}
