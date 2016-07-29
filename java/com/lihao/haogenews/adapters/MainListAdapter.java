package com.lihao.haogenews.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lihao.haogenews.R;
import com.lihao.haogenews.model.NewsBean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/29.
 */
public class MainListAdapter extends BaseAdapter {

    private Context mContext;
    private List<NewsBean> mList;
    private LayoutInflater mInflater;

    public MainListAdapter(Context context, List<NewsBean> list) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(mContext);
    }

    public List<NewsBean> getData() {
        return mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.mainlist_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.newsAuthor = (TextView) convertView.findViewById(R.id.mainlist_author);
            viewHolder.newsTitle = (TextView) convertView.findViewById(R.id.mainlist_title);
            viewHolder.newsImage = (ImageView) convertView.findViewById(R.id.mainlist_pic);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.newsTitle.setText(mList.get(position).getTitle());
        viewHolder.newsAuthor.setText(mList.get(position).getAuthor());

        return convertView;
    }

    public static class ViewHolder {
        TextView newsTitle;
        TextView newsAuthor;
        ImageView newsImage;
    }


}
