package com.xiaoxiaoyi.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoxiaoyi
 * 给定一个数组, 问如何划分子数组, 使得子数组中异或和为0的子数组数量最多
 */
public class OptimalPartitioningOfSubarrayXORSumToZero {

    public static int get(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Map<Integer, Integer> XORSumMap = new HashMap<>();
        // 不选择任何数的子数组的异或和为0, 此时出现异或和为0的位置是-1
        XORSumMap.put(0, -1);
        int n = arr.length;
        // dp[i]代表0~i范围上的最优解
        int[] dp = new int[n];
        // 记录每一步异或的结果
        int xor = 0;
        for (int cur = 0; cur < n; cur++) {
            xor ^= arr[cur];
            if (XORSumMap.containsKey(xor)) {
                /*
                如果前面的异或结果中包含有当前异或到的数,
                则说明当前位置到这个异或结果之前最后1次出现到的位置的异或和为0
                即lastIndex+1到cur位置的所有数字异或和为0
                 */
                int lastIndex = XORSumMap.get(xor);
                // 当前位置的结果可能是dp[lastIndex] + 1
                dp[cur] = lastIndex == -1 ? 1 : dp[lastIndex] + 1;
            }
            if (cur > 0) {
                // 当前位置的结果只可能是2种可能中最大的那个
                dp[cur] = Math.max(dp[cur], dp[cur - 1]);
            }
            // 更新当前异或结果出现的位置
            XORSumMap.put(xor, cur);
        }
        return dp[n - 1];
    }

}
