package com.lihao.haogenews.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lihao.haogenews.R;
import com.lihao.haogenews.model.PicBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/2.
 */
public class PicAdapter extends RecyclerView.Adapter<PicAdapter.ViewHolder> {

    private Context mContext;
    private List<PicBean> mList;

    public PicAdapter(Context context, List<PicBean> list) {
        mContext = context;
        mList = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_pic, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.pic_recyclerview);
        }
    }
}
