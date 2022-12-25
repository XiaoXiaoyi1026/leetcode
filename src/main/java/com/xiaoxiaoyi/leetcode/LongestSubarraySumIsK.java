package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 和为k的最长子数组长度
 */
public class LongestSubarraySumIsK {

    public static int get(int[] arr, int k) {
        int left = -1, sum = 0, ans = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            while (sum > k) {
                // 当累加和大于k时, 累加和减去范围内最左边的那个数字, 然后左指针右移
                sum -= arr[++left];
            }
            if (sum == k) {
                // 如果累加和等于k, 则尝试更新结果
                ans = Math.max(ans, i - left);
            }
        }
        return ans;
    }

}
