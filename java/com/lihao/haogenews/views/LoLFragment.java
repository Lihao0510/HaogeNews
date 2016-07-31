package com.lihao.haogenews.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lihao.haogenews.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Administrator on 2016/7/30.
 */
public class LoLFragment extends BaseFragment {

    private WebView mWebView;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmetn_lol, container, false);
        mWebView = (WebView) view.findViewById(R.id.lol_webview);
        ((MainActivity) mActivity).setSwipeEnable(false);
        setWebView();
        return view;
    }

    private void setWebView() {

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                Intent intent = new Intent(mActivity, NewsContentActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
                return true;
            }

        });
        mWebView.loadUrl("http://lolapp.duowan.com/index.php?r=news/index&menu=%E9%87%8D%E7%82%B9");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mWebView.destroy();
    }
}
