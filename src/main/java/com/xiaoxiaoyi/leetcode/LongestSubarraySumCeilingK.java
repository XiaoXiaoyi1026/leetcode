package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 给定一个数组, 里面有正数, 负数, 0
 * 求和小于等于K的最长子数组的长度
 */
public class LongestSubarraySumCeilingK {

    public static int get(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return k == 0 ? 0 : -1;
        }
        int n = arr.length;
        // minSum[i]代表以数组i位置开始, 其后所有子数组中的最小和
        int[] minSum = new int[n];
        // minSumEnd[i]代表以数组i位置开始, 其后最小和子数组的右边界
        int[] minSumEnd = new int[n];
        // base case, 数组最后一个数为开头是的minSum就是arr[n - 1]
        minSum[n - 1] = arr[n - 1];
        minSumEnd[n - 1] = n - 1;
        // 从后往前预处理出minSum和minSumEnd的信息
        for (int i = n - 2; i >= 0; i--) {
            minSum[i] = arr[i];
            minSumEnd[i] = i;
            // 由minSum[i + 1]是否<0决定是否往右扩
            if (minSum[i + 1] < 0) {
                // minSum[i + 1] < 0说明往右扩可以使minSum更小, 选择往右扩
                minSum[i] += minSum[i + 1];
                minSumEnd[i] = minSumEnd[i + 1];
            }
        }
        int sum = 0, right = 0, ans = 0;
        // 计算每个位置开始时候的可能
        for (int left = 0; left < n; left++) {
            // 尝试往右扩
            while (right < n && sum + minSum[right] <= k) {
                sum += minSum[right];
                right = minSumEnd[right] + 1;
            }
            ans = Math.max(ans, right - left);
            if (left < right) {
                // 如果窗口内还有数字, 则在下一轮循环之前减去左边界那个
                sum -= arr[left];
            } else {
                // 如果此时窗口内已经没有数字, 说明left开始的所有子数组和都不可能小于等于k, 重开一个窗口
                right = left + 1;
            }
        }
        return ans;
    }

}
