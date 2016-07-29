package com.lihao.haogenews.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lihao.haogenews.R;
import com.lihao.haogenews.adapters.MainListAdapter;
import com.lihao.haogenews.model.NewsBean;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
public class MainFragment extends BaseFragment {

    private ListView mListView;
    private Banner mBanner;
    private MainListAdapter mAdapter;
    private List<NewsBean> mList;
    private static int count;


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mListView = (ListView) view.findViewById(R.id.lv_main);
        View headView = inflater.inflate(R.layout.main_banner, mListView, false);
        mBanner = (Banner) headView.findViewById(R.id.banner);
        setBanner();
        getNewsData();
        mListView.addHeaderView(headView);
        setListView();
        return view;
    }

    private void getNewsData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            mList.add(new NewsBean("测试Title" + count, "Hahahahah", "Lihao"));
            count++;
        }
    }

    private void setListView() {
        mAdapter = new MainListAdapter(mActivity, mList);
        mListView.setAdapter(mAdapter);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (mListView != null && mListView.getChildCount() != 0) {
                    boolean enable = (firstVisibleItem == 0) && (view.getChildAt(0).getTop() == 0);
                    ((MainActivity) mActivity).setSwipeEnable(enable);
                }
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsBean content = (NewsBean) parent.getAdapter().getItem(position);
                Toast.makeText(mActivity, content.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setBanner() {
        String[] images = getResources().getStringArray(R.array.url);
        String[] titles = getResources().getStringArray(R.array.title);
        mBanner.setBannerStyle(Banner.CIRCLE_INDICATOR_TITLE);
        mBanner.setDelayTime(3333);
        mBanner.setIndicatorGravity(Banner.CENTER);
        mBanner.setBannerTitle(titles);
        mBanner.setImages(images, new Banner.OnLoadImageListener() {
            @Override
            public void OnLoadImage(ImageView view, Object url) {
                Glide.with(mActivity).load(url).placeholder(R.mipmap.loading2).centerCrop().into(view);
            }
        });
        mBanner.setOnBannerClickListener(new Banner.OnBannerClickListener() {
            @Override
            public void OnBannerClick(View view, int position) {
                Toast.makeText(mActivity, "点击了第" + position + "张图片!", Toast.LENGTH_SHORT).show();
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
