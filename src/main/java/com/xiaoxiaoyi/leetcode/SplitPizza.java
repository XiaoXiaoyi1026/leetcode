package com.xiaoxiaoyi.leetcode;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 切披萨问题
 * @date 3/10/2023 10:17 AM
 */
public class SplitPizza {

    public static int ways(@NotNull String[] pizza, int k) {
        int m = pizza.length;
        int n = pizza[0].length();
        int mod = 1000000007;
        // apples[i][j]代表以pizza[i][j]为右下角的苹果数量
        int[][] apples = new int[m + 1][n + 1];
        for (int row = m - 1; row >= 0; row--) {
            for (int col = n - 1; col >= 0; col--) {
                if (pizza[row].charAt(col) == 'A') {
                    apples[row][col] = 1;
                }
                apples[row][col] += apples[row + 1][col] + apples[row][col + 1] - apples[row + 1][col + 1];
            }
        }
        System.out.println(Arrays.deepToString(apples));
        // process[i][j][l]表示以pizza左上角i, j位置为起点, 可以分给l个人的方案数
        int[][][] process = new int[m + 1][n + 1][k + 1];
        // 从最右下角开始向左上角枚举所有分法
        for (int row = m - 1; row >= 0; row--) {
            for (int col = n - 1; col >= 0; col--) {
                if (apples[row][col] > 0) {
                    // base case, 当以row和col作为pizza的左上角时且存在苹果, 可以分给一个人
                    process[row][col][1] = 1;
                }
                // 尝试分给多个人
                for (int humans = 2; humans <= k; humans++) {
                    // 尝试横着切, rowSplit代表切的横坐标
                    for (int rowSplit = m - row - 1; rowSplit >= 1; rowSplit--) {
                        // 在rowSplit切一刀, 如果剩下的pizza部分还有苹果, 则可以将剩下部分给human-1个人拿去分, 自己拿切下来的上面这块
                        if (apples[row][col] - apples[row + rowSplit][col] > 0) {
                            process[row][col][humans] = (process[row][col][humans] + process[row + rowSplit][col][humans - 1]) % mod;
                        }
                    }
                    for (int colSplit = n - col - 1; colSplit >= 1; colSplit--) {
                        if (apples[row][col] - apples[row][col + colSplit] > 0) {
                            process[row][col][humans] = (process[row][col][humans] + process[row][col + colSplit][humans - 1]) % mod;
                        }
                    }
                }
            }
        }
        return process[0][0][k];
    }

}
