package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

import java.util.Arrays;

public class LongestSubarraySumCeilingKTest extends TestCase {

    /**
     * border必须大于0
     * -border <= arr[i] < border
     */
    public int[] generateRandomArray(int border, int length) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = -1 * border + (int) (Math.random() * (border << 1));
        }
        return arr;
    }

    public void testGet() {
        int k = -10 + (int) (Math.random() * 20), length = 5 + (int) (Math.random() * Math.abs(k));
        int[] arr = generateRandomArray(Math.abs(k), length);
        System.out.println(k);
        System.out.println(Arrays.toString(arr));
        System.out.println(LongestSubarraySumCeilingK.get(arr, k));
    }

}
