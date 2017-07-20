package org.ulplatform.utils;

import java.util.UUID;

/**
 * 创建uuid
 * Created by Administrator on 2017-7-8.
 */
public class CodecUtil {
    public static String createUUID(){
        return UUID.randomUUID().toString();
    }
}
