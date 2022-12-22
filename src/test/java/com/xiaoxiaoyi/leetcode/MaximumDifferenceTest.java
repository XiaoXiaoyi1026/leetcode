package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

import java.util.Arrays;

public class MaximumDifferenceTest extends TestCase {

    public int[] generateRandomArray(int max, int length) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

    public void testMaximumDifference() {
        for (int i = 0; i < 20000; i++) {
            int[] arr = generateRandomArray(100, 3 + (int) (Math.random() * 30));
            int ans1 = MaximumDifference.get(arr);
            int ans2 = MaximumDifference.ordinary(arr);
            if (ans1 != ans2) {
                System.out.println(Arrays.toString(arr));
                System.out.println(ans1);
                System.out.println(ans2);
            }
        }
    }

}
