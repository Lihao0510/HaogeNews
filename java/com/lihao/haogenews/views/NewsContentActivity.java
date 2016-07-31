package com.lihao.haogenews.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lihao.haogenews.R;
import com.lihao.haogenews.threads.NewsLoadTask;

/**
 * Created by Administrator on 2016/7/30.
 */
public class NewsContentActivity extends AppCompatActivity {


    private WebView newsWebView;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news);
        initViews();
        initData();


    }

    private void initData() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        Log.d("Lihao", url);
        if (url.contains("http://lol.duowan.cn")) {
            NewsLoadTask loadTast = new NewsLoadTask(this, newsWebView, url);
            loadTast.execute(url);
        } else {
            newsWebView.loadUrl(url);
        }

    }

    private void initViews() {
        newsWebView = (WebView) findViewById(R.id.news_webview);
        WebSettings settings = newsWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        newsWebView.setWebViewClient(new WebViewClient());
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }
}
