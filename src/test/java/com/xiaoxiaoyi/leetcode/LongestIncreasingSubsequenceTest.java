package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

import java.util.Arrays;

public class LongestIncreasingSubsequenceTest extends TestCase {

    public int[] generateRandomArray(int length, int max) {
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            // 1 ~ max
            arr[i] = 1 + (int) (Math.random() * max);
        }
        return arr;
    }

    public void testLongestIncreasingSubsequence() {
        int[] arr;
        int ans1, ans2, ans3;
        for (int i = 0; i < 20; i++) {
            arr = generateRandomArray(1 + (int) (Math.random() * 20), 50);
            ans1 = LongestIncreasingSubsequence
                    .longestIncreasingSubsequence1(arr);
            ans2 = LongestIncreasingSubsequence
                    .longestIncreasingSubsequence2(arr);
            ans3 = LongestIncreasingSubsequence
                    .longestIncreasingSubsequence3(arr);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println(Arrays.toString(arr));
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
            }
        }
    }

    public void testLongestIncreasingSubsequence1() {
        int[] arr = {3, 1, 2, 6, 3, 4};
        System.out.println(LongestIncreasingSubsequence
                .longestIncreasingSubsequence1(arr));
    }

    public void testLongestIncreasingSubsequence2() {
        int[] arr = {3, 1, 2, 6, 3, 4};
        System.out.println(LongestIncreasingSubsequence
                .longestIncreasingSubsequence2(arr));
    }

    public void testLongestIncreasingSubsequence3() {
        int[] arr = {3, 1, 2, 6, 3, 4};
        System.out.println(LongestIncreasingSubsequence
                .longestIncreasingSubsequence3(arr));
    }

}
