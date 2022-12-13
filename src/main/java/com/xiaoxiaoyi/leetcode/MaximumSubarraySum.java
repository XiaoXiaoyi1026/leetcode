package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 求一个数组的最大子数组和
 */
public class MaximumSubarraySum {

    public static int maximumSubarraySum(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int cur = 0, max = Integer.MIN_VALUE;
        for (int num : array) {
            cur += num;
            max = Math.max(cur, max);
            cur = Math.max(cur, 0);
        }
        return max;
    }
}