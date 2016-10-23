package com.lihao.haogenews.utils;

/**
 * Created by Administrator on 2016/8/5.
 */
public class ArrayUtil {

    public static int finMaxCount(int[] positions) {
        int max = 0;
        for (int position : positions) {
            if (position > max) {
                max = position;
            }
        }
        return max;
    }

    public static String getSubUrl(String url) {
        String[] line = url.split("&");
        return line[0];
    }
}
