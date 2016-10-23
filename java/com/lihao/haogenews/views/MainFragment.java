package com.lihao.haogenews.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.lihao.haogenews.MyApplication;
import com.lihao.haogenews.R;
import com.lihao.haogenews.adapters.MainListAdapter;
import com.lihao.haogenews.model.NewsBean;
import com.lihao.haogenews.utils.Constants;
import com.youth.banner.Banner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
public class MainFragment extends BaseFragment {

    private ListView mListView;
    private Banner mBanner;
    private int width;
    private int height;
    private MainListAdapter mAdapter;
    private List<NewsBean> mList;
    private List<NewsBean> bannerList;
    private static int count;


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        WindowManager windowManager = (WindowManager) mActivity.getSystemService(Context.WINDOW_SERVICE);
        width = windowManager.getDefaultDisplay().getWidth();
        height = width * 3 / 5;
        mListView = (ListView) view.findViewById(R.id.lv_main);
        View headView = inflater.inflate(R.layout.main_banner, mListView, false);
        mBanner = (Banner) headView.findViewById(R.id.banner);
        mList = new ArrayList<>();
        bannerList = new ArrayList<>();
        count = 1;
        getNewsData();
        mListView.addHeaderView(headView);
        return view;
    }

    private void getNewsData() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Constants.MAIN_NEWS_URL + count, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                count++;
                try {
                    JSONArray newsArray = response.getJSONArray("data");
                    for (int i = 0; i < newsArray.length(); i++) {
                        JSONObject newsObject = newsArray.getJSONObject(i);
                        mList.add(new NewsBean(newsObject.getString("title"), newsObject.getString("photo"), newsObject.getString("commentUrl"), newsObject.getString("content")));
                    }
                    Log.d(Constants.APP_NAME, mList.toString());
                    JSONArray bannerArray = response.getJSONArray("headerline");
                    for (int i = 0; i < bannerArray.length(); i++) {
                        JSONObject bannerObject = bannerArray.getJSONObject(i);
                        bannerList.add(new NewsBean("今日LOL头条", bannerObject.getString("photo"), bannerObject.getString("commentUrl"), ""));
                    }
                    Log.d(Constants.APP_NAME, bannerList.toString());
                    setListView();
                    setBanner();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(Constants.APP_NAME, "主页加载出错!");
            }
        });
        request.setTag(Constants.APP_NAME);
        MyApplication.getVolleyQueue().add(request);
    }

    private void setListView() {
        mAdapter = new MainListAdapter(mActivity, mList);
        mListView.setAdapter(mAdapter);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (mListView != null && mListView.getChildCount() != 0 && view.getLastVisiblePosition() > (mList.size() - 1)) {
                    Toast.makeText(mActivity, "正在刷新!", Toast.LENGTH_SHORT).show();
                    getMoreData();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (mListView != null && mListView.getChildCount() != 0) {
                    boolean enable = (firstVisibleItem == 0) && (view.getChildAt(0).getTop() == 0);
                    if(enable){
                        count = 1;
                    }
                    ((MainActivity) mActivity).setSwipeEnable(enable);
                }
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsBean content = (NewsBean) parent.getAdapter().getItem(position);
                Intent intent = new Intent(mActivity, NewsActivity.class);
                intent.putExtra("url", content.getUrl());
                intent.putExtra("title", content.getTitle());
                mActivity.startActivity(intent);
            }
        });
    }

    private void getMoreData() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Constants.MAIN_NEWS_URL + count, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                count++;
                try {
                    JSONArray newsArray = response.getJSONArray("data");
                    for (int i = 0; i < newsArray.length(); i++) {
                        JSONObject newsObject = newsArray.getJSONObject(i);
                        mList.add(new NewsBean(newsObject.getString("title"), newsObject.getString("photo"), newsObject.getString("commentUrl"), newsObject.getString("content")));
                    }
                    Log.d(Constants.APP_NAME, mList.toString());
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(Constants.APP_NAME, "主页加载出错!");
            }
        });
        request.setTag(Constants.APP_NAME);
        MyApplication.getVolleyQueue().add(request);
    }

    private void setBanner() {
        String[] images = new String[bannerList.size()];
        for (int i = 0; i < bannerList.size(); i++) {
            images[i] = bannerList.get(i).getPicUrl();
        }
        mBanner.setBannerStyle(Banner.CIRCLE_INDICATOR);
        mBanner.setDelayTime(3333);
        mBanner.setIndicatorGravity(Banner.CENTER);
        mBanner.setImages(images, new Banner.OnLoadImageListener() {
            @Override
            public void OnLoadImage(ImageView view, Object url) {
                Glide.with(mActivity).load(url).override(width, height).placeholder(R.mipmap.loading2).fitCenter().into(view);
            }
        });
        mBanner.setOnBannerClickListener(new Banner.OnBannerClickListener() {
            @Override
            public void OnBannerClick(View view, int position) {

                Intent intent = new Intent(mActivity, NewsActivity.class);
                intent.putExtra("url", bannerList.get(position).getUrl());
                intent.putExtra("title", "LOL头条");
                mActivity.startActivity(intent);//Toast.makeText(mActivity, "点击了第" + position + "张图片!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mBanner.isAutoPlay(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.isAutoPlay(false);
    }
}
