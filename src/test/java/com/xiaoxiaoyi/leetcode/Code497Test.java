package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

import java.util.Arrays;

public class Code497Test extends TestCase {

    public void testCode497() {
        int[][] rects = new int[][]{
                {-2, -2, 1, 1},
                {2, 2, 4, 6}
        };

        Code497.init(rects);
        System.out.println(Arrays.toString(Code497.pick()));
        System.out.println(Arrays.toString(Code497.pick()));
        System.out.println(Arrays.toString(Code497.pick()));
        System.out.println(Arrays.toString(Code497.pick()));
        System.out.println(Arrays.toString(Code497.pick()));
    }
}
