package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class EditDistanceTest extends TestCase {

    public void testEditDistance() {
        String strA = "abc", strB = "adc";
        int add = 5;
        int del = 3;
        int re = 100;
        System.out.println(EditDistance.replace(strA, strB, add, del, re));
    }

}
