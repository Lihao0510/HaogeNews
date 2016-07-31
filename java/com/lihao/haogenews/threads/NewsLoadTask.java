package com.lihao.haogenews.threads;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Administrator on 2016/7/30.
 */
public class NewsLoadTask extends AsyncTask<String,Void,String> {

    private Context mContext;
    private WebView mWebview;
    private String url;

    public NewsLoadTask(Context context, WebView webView,String url){
        mContext = context;
        mWebview = webView;
        this.url = url;
    }
    @Override
    protected String doInBackground(String... params) {
        StringBuffer sb = new StringBuffer();
        String url = params[0];
        try {
            Document doc = Jsoup.connect(url).get();
            /*Elements head = doc.getElementsByTag("head");
            Elements comment = doc.getElementsByAttributeValue("class", "ic-comment");
            Elements content = doc.getElementsByAttributeValue("id", "container");
            Elements scripts = doc.getElementsByTag("script");
            sb.append("<!doctype html><html>").append(head).append("<body>").append(content).append(comment).append(scripts).append("</body></html>");
            Log.d("Lihao",sb.toString());*/
            doc.getElementsByTag("header").remove();
            doc.getElementsByTag("footer").remove();
            doc.getElementsByAttributeValue("class","channel-col").remove();
            doc.getElementsByAttributeValue("id","lol-ad").remove();
            sb.append(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    @Override
    protected void onPostExecute(String data) {
        //mWebview.loadData(data, "text/html; charset=UTF-8", null);
        mWebview.loadDataWithBaseURL(url,data,"text/html; charset=UTF-8",null,url);
    }
}
