package com.amqr.screenshotviewdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * User: LJM
 * Date&Time: 2017-04-21 & 20:02
 * Describe: Describe Text
 */
public class StyleRecyclerView extends BaseActivity {
    private RecyclerView mRecycler;
    private ImageView mIvRet;
    private List<DataBean> mDatas;
    private TestAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);

        initData();
        mRecycler = (RecyclerView) findViewById(R.id.mRecycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(mAdapter = new TestAdapter());

        mIvRet = (ImageView) findViewById(R.id.mIvRet);
        findViewById(R.id.mTvShot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = SimpleUtils.shotRecyclerView(mRecycler);
                mIvRet.setImageBitmap(bitmap);
            }
        });
    }

    private void initData()
    {
        mDatas = new ArrayList<DataBean>();
        DataBean dataBean = null;
        for (int i = 0; i < 6; i++)
        {
            dataBean = new DataBean();
            dataBean.title = "标题　　"+i;
            dataBean.desc = "描述一下　　"+i;
            mDatas.add(dataBean);
        }
    }

    class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder>{
        // 孩子数
        @Override
        public int getItemCount() {
            return mDatas.size();
        }


        // 创建视图
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(StyleRecyclerView.this)
                    .inflate(R.layout.item_rv,parent, false));
            return myViewHolder;
        }

        // 绑定视图视图  以前getView的事情  关键方法
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            DataBean dataBean = mDatas.get(position);
            holder.mTvTitle.setText(dataBean.title);
            holder.mTvDesc.setText(dataBean.desc);

        }

        // 必须实现的Holder
        class MyViewHolder extends RecyclerView.ViewHolder
        {
            TextView mTvTitle;
            TextView mTvDesc;

            public MyViewHolder(View itemView) {
                super(itemView);
                mTvTitle = (TextView) itemView.findViewById(R.id.mTvTitle);
                mTvDesc = (TextView) itemView.findViewById(R.id.mTvDesc);
            }
        }

    }
}
