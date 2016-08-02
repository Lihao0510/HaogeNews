package com.lihao.haogenews.model;

/**
 * Created by Administrator on 2016/8/2.
 */
public class PicBean {

    private String url;
    private int width;
    private int height;
    private int id;

    public PicBean(String url, String width, String height, String id) {
        this.url = url;
        this.height = Integer.parseInt(height);
        this.width = Integer.parseInt(width);
        this.id = Integer.parseInt(id);
    }

    public String getUrl() {
        return url;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getId() {
        return id;
    }
}
