package com.amqr.screenshotviewdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * User: LJM
 * Date&Time: 2017-04-21 & 19:39
 * Describe: Describe Text
 */
public class StyleLvActivity extends BaseActivity {
    private ListView mLv;
    private ImageView mIvRet;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lv);
        mLv = (ListView) findViewById(R.id.mLv);
        mLv.setAdapter(new MyAdapter());
        mIvRet = (ImageView) findViewById(R.id.mIvRet);
        findViewById(R.id.mTvShot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = SimpleUtils.shotListView(mLv);
                mIvRet.setImageBitmap(bitmap);
            }
        });
    }

    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }
        @Override
        public long getItemId(int i) {
            return 0;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            // 简答粗暴了一些，只是为演示
            View  vi = getLayoutInflater().inflate(R.layout.item_lv,null);
            TextView textView = (TextView) vi.findViewById(R.id.mTv);
            textView.setText(i+"");


            return vi;
        }
    }
}
