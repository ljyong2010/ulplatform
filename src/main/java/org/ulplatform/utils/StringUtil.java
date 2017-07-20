package org.ulplatform.utils;

/**
 * 字符串工具类
 * Created by Administrator on 2017-7-8.
 */
public class StringUtil {
    /**
     * 给定字符串是否为空或空串
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        if (str != null && str.length() != 0) {
            return true;
        }
        return false;
    }

    /**
     * 给定字符串是否为空或空串
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str != null && str.length() != 0) {
            return false;
        }
        return true;
    }
    /**
     * 给键添加前缀
     *
     * @param key 键
     * @return 添加前缀后的键
     */
    public static String addPrefix(String key) {
        return "USER-" + ":" + key;
    }
}
