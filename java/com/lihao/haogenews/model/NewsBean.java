package com.lihao.haogenews.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/7/29.
 */
public class NewsBean implements Parcelable {

    public String title;
    public String picUrl;
    public String author;

    public NewsBean() {

    }

    public NewsBean(String title, String picUrl, String author) {
        this.title = title;
        this.picUrl = picUrl;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Title:" + title + "; Author:" + author;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(picUrl);
    }

    public static final Parcelable.Creator<NewsBean> CREATOR = new Parcelable.Creator<NewsBean>() {

        @Override
        public NewsBean createFromParcel(Parcel source) {
            NewsBean newsBean = new NewsBean();
            newsBean.title = source.readString();
            newsBean.author = source.readString();
            newsBean.picUrl = source.readString();
            return newsBean;
        }

        @Override
        public NewsBean[] newArray(int size) {
            return new NewsBean[size];
        }
    };
}
