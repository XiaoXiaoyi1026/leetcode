package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class RotatingChangeStringTest extends TestCase {

    public void testGet() {
        String str1 = "abcd";
        String str2 = "bdca";
        System.out.println(RotatingChangeString.isRotatingChange(str1, str2));
        System.out.println(RotatingChangeString.dp(str1, str2));
    }

}
