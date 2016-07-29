package com.lihao.haogenews.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.lihao.haogenews.R;
import com.youth.banner.Banner;

/**
 * Created by Administrator on 2016/7/28.
 */
public class MainFragment extends BaseFragment{

    private ListView mListView;
    private Banner mBanner;



    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        mListView = (ListView) view.findViewById(R.id.lv_main);
        View headView = inflater.inflate(R.layout.main_banner,mListView,false);
        mBanner = (Banner) headView.findViewById(R.id.banner);
        setBanner();
        mListView.addHeaderView(headView);
        return view;
    }

    private void setBanner() {
    }
}
