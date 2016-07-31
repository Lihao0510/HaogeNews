package com.lihao.haogenews.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.lihao.haogenews.R;
import com.lihao.haogenews.threads.NewsLoadTask;

/**
 * Created by Administrator on 2016/7/30.
 */
public class NewsContentActivity extends AppCompatActivity {


    private WebView newsWebView;
    private String url;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initViews();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData();


    }

    private void initData() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        Log.d("Lihao", url);
        if (url.contains("http://lol.duowan.cn")||url.contains("http://bbs.duowan.com")) {
            NewsLoadTask loadTast = new NewsLoadTask(this, newsWebView, url);
            loadTast.execute(url);
            getSupportActionBar().setTitle("LOL最新资讯");
            Log.d("Lihao","Title" + newsWebView.getTitle());
        } else {
            newsWebView.loadUrl(url);
            Log.d("Lihao","Title" + newsWebView.getTitle());
            getSupportActionBar().setTitle("LOL画廊");

        }

    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.news_toolbar);
        newsWebView = (WebView) findViewById(R.id.news_webview);
        WebSettings settings = newsWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        newsWebView.setWebViewClient(new WebViewClient());
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }
}
