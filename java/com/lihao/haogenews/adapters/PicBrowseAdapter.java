package com.lihao.haogenews.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lihao.haogenews.R;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Administrator on 2016/8/4.
 */
public class PicBrowseAdapter extends PagerAdapter {

    private List<String> urlsList;
    private Context mContext;
    private List<View> mViews;

    public PicBrowseAdapter(Context context, List<String> list) {
        mContext = context;
        urlsList = list;
        Log.d("Lihao",urlsList.toString());
        mViews = new ArrayList<>();
        for (int i = 0; i < urlsList.size(); i++) {
            View view = View.inflate(mContext, R.layout.item_pic_layout, null);
            mViews.add(view);
        }

    }

    @Override
    public int getCount() {
        return urlsList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        View view = mViews.get(position);
        PhotoView imageView = (PhotoView) view.findViewById(R.id.picdetail_img);
        Glide.with(mContext).load(urlsList.get(position)).fitCenter().into(imageView);
        //PhotoViewAttacher mAttacher = new PhotoViewAttacher(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }
}
