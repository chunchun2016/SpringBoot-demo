package com.example.util;

import java.util.List;

/**
 * Created by admin on 17/5/3.
 */
public class StringUtil {

    private final static String SEPARATOR = ",";

    /**
     * list 转 string
     *
     * @param list
     * @param separator 分隔符
     * @return
     */
    public static String listToString(List list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }
    /**
     * list 转 string (逗号分隔)
     *
     * @param list
     * @return
     */
    public static String listToString(List list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(SEPARATOR);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }
}
