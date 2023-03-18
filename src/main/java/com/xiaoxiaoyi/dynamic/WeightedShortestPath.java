package com.xiaoxiaoyi.dynamic;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author xiaoxiaoyi
 * 给定一个matrix, 要求从左上角走到右下角, 求最短带权路径
 * 每次只能选择往右或者往下走
 */
public class WeightedShortestPath {

    public static int weightedShortestPath(int[][] matrix) {
        return dpProcess(matrix);
    }

    public static int dpProcess(@NotNull int[][] matrix) {
        // 普遍位置i j 依赖于i - 1, j 和 i, j - 1这两个位置的最小值
        int m = matrix.length, n = matrix[0].length;
        // 优化空间只用1个一维数组滚动更新
        int[] dp = new int[n];
        // dp滚动更新的次数
        for (int i = 0; i < m; i++) {
            // 求dp中每一个位置的值
            for (int j = 0; j < n; j++) {
                // 如果是第一行
                if (i == 0) {
                    // dp[j] = matrix[i][j] + j == 0 ? 0 : dp[j - 1];
                    if (j == 0) {
                        // 第一行第一个位置的值为baseCase, 即到达0, 0位置的最短路径就是它的权
                        dp[j] = matrix[i][j];
                    } else {
                        // 第一行其余位置只会依赖于它左边那一个格子的值
                        dp[j] = matrix[i][j] + dp[j - 1];
                    }
                } else {
                    // 非第一行
                    if (j == 0) {
                        // 如果是非第一行的第一列, 也只依赖于它上面的值
                        dp[j] += matrix[i][j];
                    } else {
                        // 如果是非第一行的非第一列, 则依赖于它上面和左边的
                        dp[j] = Math.min(dp[j], dp[j - 1]) + matrix[i][j];
                    }
                }
            }
            System.out.println(Arrays.toString(dp));
        }
        // 获取最后一行的最后1个值
        return dp[n - 1];
    }
}
