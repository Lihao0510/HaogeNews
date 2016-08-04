package com.lihao.haogenews.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.lihao.haogenews.Presenter.PicActivityPresenter;
import com.lihao.haogenews.R;
import com.lihao.haogenews.adapters.PicBrowseAdapter;
import com.lihao.haogenews.threads.PicloadTask;
import com.lihao.haogenews.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/4.
 */
public class PicBrowseActivity extends AppCompatActivity implements PicActivityPresenter {

    private ViewPager mViewPager;
    private TextView mTitle;
    private int picId;
    private String url;
    private String title;
    private List<String> imgUrlsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pic_browse);
        initViews();
        getDatas();
    }

    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTitle = (TextView) findViewById(R.id.tv_imagetitle);
    }

    public void getDatas() {
        Intent intent = getIntent();
        picId = intent.getIntExtra("id",127141);
        title = intent.getStringExtra("title");
        url = Constants.PIC_BASE_URL + picId + ".html";
        Log.d("Lihao",url);
        Log.d("Lihao",title);
        mTitle.setText(title);
        imgUrlsList = new ArrayList<>();
        PicloadTask task = new PicloadTask(this);
        task.execute(url);
    }

    @Override
    public void initViewPager(List<String> list) {
        PicBrowseAdapter mAdapter = new PicBrowseAdapter(this, list);
        mViewPager.setAdapter(mAdapter);
        Log.d("Lihao","initViewPager完成!");
    }
}
