package com.xiaoxiaoyi.leetcode;

import java.util.Arrays;

/**
 * @author xiaoxiaoyi
 * 获取一个数组排序后的相邻数之间的最大差值
 * 要求算法复杂度为O(N), 不能使用非比较的排序方法
 */
public class MaximumDifference {

    public static int get(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : arr) {
            max = Math.max(num, max);
            min = Math.min(num, min);
        }
        if (max == min) {
            // 如果数组最大值等于最小值, 则直接返回0
            return 0;
        }
        // 桶的数量是n+1个, 为了保证分配完数字之后一定存在空桶
        int n = arr.length;
        // 标记每一个桶中是否包含有数字
        boolean[] haveNum = new boolean[n + 1];
        // 记录每一个桶的最大值
        int[] maxes = new int[n + 1];
        // 记录每一个桶的最小值
        int[] minus = new int[n + 1];
        for (int num : arr) {
            // 计算数字应该在的桶的编号
            int bucketIndex = (num - min) * n / (max - min);
            maxes[bucketIndex] = haveNum[bucketIndex] ? Math.max(maxes[bucketIndex], num) : num;
            minus[bucketIndex] = haveNum[bucketIndex] ? Math.min(minus[bucketIndex], num) : num;
            haveNum[bucketIndex] = true;
        }
        int res = 0;
        // 上一个有值桶的最大值
        int preMax = maxes[0];
        // 从第1个桶开始, 一共n+1个桶
        for (int i = 1; i <= n; i++) {
            if (haveNum[i]) {
                // 当前桶的最小值减去前面桶的最大值有可能是结果
                res = Math.max(minus[i] - preMax, res);
                // 更新前面桶的最大值
                preMax = maxes[i];
            }
        }
        return res;
    }

    public static int ordinary(int[] arr) {
        Arrays.sort(arr);
        int ans = 0;
        int pre = arr[0];
        for (int num : arr) {
            ans = Math.max(ans, num - pre);
            pre = num;
        }
        return ans;
    }
}
