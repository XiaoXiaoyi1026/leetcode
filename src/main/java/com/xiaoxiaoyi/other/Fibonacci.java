package com.xiaoxiaoyi.other;

/**
 * @author xiaoxiaoyi
 * 斐波拉契数列的优化方法(log(N)级别)
 */
public class Fibonacci {

    public static int fibonacci(int n) {
        if (n < 1) {
            return 0;
        }
        if (n < 3) {
            // n == 2 || n == 1
            return 1;
        }
        int[][] base = {
                {1, 1},
                {1, 0}
        };
        // 求base的n - 2次方
        int[][] res = matrixPower(base, n - 2);
        // 返回第n项 |f(n) f(n-1)| = |f(2) f(1)| * base ^(n - 2)
        return res[0][0] + res[1][0];
    }

    /**
     * 求matrix的power次方
     */
    private static int[][] matrixPower(int[][] matrix, int power) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            // res变成单位矩阵(矩阵中1的含义)
            res[i][i] = 1;
        }
        // 辅助矩阵
        int[][] tmp = matrix;
        // 当power的二进制中还包含有1时, 每一轮右移一位
        for (; power != 0; power >>= 1) {
            // 如果power最右边的那一位为1
            if ((power & 1) == 1) {
                // res = res * tmp
                res = multiplyMatrix(res, tmp);
            }
            // tmp = tmp ^ 2
            tmp = multiplyMatrix(tmp, tmp);
        }
        // res = matrix ^ power
        return res;
    }

    /**
     * 如果两个矩阵规模不大, 复杂度接近O(1)
     */
    private static int[][] multiplyMatrix(int[][] matrix1, int[][] matrix2) {
        int n = matrix1.length;
        int m = matrix2[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < matrix2.length; k++) {
                    res[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return res;
    }

}
