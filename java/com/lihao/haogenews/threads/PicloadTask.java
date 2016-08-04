package com.lihao.haogenews.threads;

import android.os.AsyncTask;

import com.lihao.haogenews.Presenter.PicActivityPresenter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/4.
 */
public class PicloadTask extends AsyncTask<String, Void, List<String>> {

    private List<String> mList;
    private PicActivityPresenter mPresenter;

    public PicloadTask(PicActivityPresenter presenter) {
        mList = new ArrayList<>();
        mPresenter = presenter;
    }

    @Override
    protected List<String> doInBackground(String... params) {
        String url = params[0];
        try {
            Document doc = Jsoup.connect(url).get();
            Elements picBox = doc.getElementsByAttributeValue("class", "pic-wrap");
            for (Element element : picBox) {
                Elements urls = element.getElementsByTag("img");
                for (Element src : urls) {
                    String img = src.attr("src");
                    mList.add(img);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mList;
    }

    @Override
    protected void onPostExecute(List<String> list) {
        mPresenter.initViewPager(list);
    }
}
