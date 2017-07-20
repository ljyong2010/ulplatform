package org.ulplatform.utils;

import org.junit.Test;

/**
 * Created by Administrator on 2017-7-14.
 */
public class TestUtil {

    @Test
    public void stringByte(){
        String ac = "abcdeafffff";
        byte[] bac = ac.getBytes();
        System.out.println("ac ="+ac+", bac = "+new String(bac));
    }
}
