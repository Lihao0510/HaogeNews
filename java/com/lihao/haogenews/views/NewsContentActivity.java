package com.lihao.haogenews.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.lihao.haogenews.R;
import com.lihao.haogenews.threads.NewsLoadTask;
import com.lihao.haogenews.utils.Constants;
import com.lihao.haogenews.utils.NetworkUtil;

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
        if (url.contains(Constants.LOL_NEWS_BEGIN) || url.contains(Constants.LOL_BBS_BEGIN)) {
            NewsLoadTask loadTast = new NewsLoadTask(this, newsWebView, url);
            loadTast.execute(url);
            Log.d("Lihao", "Title" + newsWebView.getTitle());
        } else {
            newsWebView.loadUrl(url);
            Log.d("Lihao", "Title" + newsWebView.getTitle());

        }

    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.news_toolbar);
        newsWebView = (WebView) findViewById(R.id.news_webview);
        WebSettings settings = newsWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        newsWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                getSupportActionBar().setTitle(title);
            }
        });
        newsWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                Intent intent = new Intent(NewsContentActivity.this, NewsContentActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
                return true;
            }
        });
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }
}
