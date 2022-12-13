package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 最大子矩阵和问题, 通过压缩矩阵的方式求解
 * 本质还是最大子数组和
 */
public class MaximumSubMatrixSum extends MaximumSubarraySum{

    public static int maximumSubMatrixSum(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length, ans = 0;
        for (int i = 0; i < m; i++) {
            int[] array = new int[n];
            for (int j = i; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    // 压缩矩阵
                    array[k] += matrix[j][k];
                }
                ans = Math.max(ans, maximumSubarraySum(array));
            }
        }
        return ans;
    }

}
