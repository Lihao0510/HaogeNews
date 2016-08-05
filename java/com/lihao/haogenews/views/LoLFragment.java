package com.lihao.haogenews.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.lihao.haogenews.R;
import com.lihao.haogenews.utils.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Administrator on 2016/7/30.
 */
public class LoLFragment extends BaseFragment {

    private WebView mWebView;
    private ProgressBar mProgressbar;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmetn_lol, container, false);
        mWebView = (WebView) view.findViewById(R.id.lol_webview);
        mProgressbar = (ProgressBar) view.findViewById(R.id.lol_progressbar);
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

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProgressbar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressbar.setVisibility(View.GONE);
            }
        });
        mWebView.loadUrl(Constants.LOL_BASE_URL);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mWebView.destroy();
    }
}
