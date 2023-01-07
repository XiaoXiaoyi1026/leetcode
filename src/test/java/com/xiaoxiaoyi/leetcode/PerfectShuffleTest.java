package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

import java.util.Arrays;

public class PerfectShuffleTest extends TestCase {

    public void testExchange() {
        int[] arr = {1, 3, 2, 5, 4};
        PerfectShuffle.exchange(arr, 0, 2, 3, 4);
        System.out.println(Arrays.toString(arr));
    }

    public int[] generate(int halfLength, int max) {
        int[] arr = new int[halfLength << 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

    public void testShuffle() {
        int[] arr = generate(7, 50);
        System.out.println(Arrays.toString(arr));
        PerfectShuffle.shuffle(arr);
        System.out.println(Arrays.toString(arr));
    }
}
