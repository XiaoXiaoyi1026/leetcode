package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

import java.util.Arrays;

public class PerfectShuffleTest extends TestCase {

    public void testExchange() {
        int[] arr = {1, 3, 2, 5, 4};
        PerfectShuffle.exchange(arr, 0, 2, 3, 4);
        System.out.println(Arrays.toString(arr));
    }

    public void testShuffle() {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        PerfectShuffle.shuffle(arr);
        System.out.println(Arrays.toString(arr));
    }

}
