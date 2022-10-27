package com.xiaoxiaoyi.graph;

/**
 * @author xiaoxiaoyi
 * 给你一个二维01矩阵，每一个位置可以和上下左右位置相连
 * 1代表陆地，0代表海洋，让你返回这个矩阵中岛屿的个数(有多少片连续的1)
 */
public class CountLands {

    public static int countLands(int[][] area) {
        int res = 0, m = area.length, n = area[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (area[i][j] == 1) {
                    // 找到了一个岛屿
                    res++;
                    // 感染函数
                    infect(area, i, j, m, n);
                }
            }
        }
        return res;
    }

    private static void infect(int[][] area, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || area[i][j] != 1) {
            // 越界直接返回
            return;
        }
        // 感染
        area[i][j] = 2;
        // 尝试感染上下左右
        infect(area, i + 1, j, m, n);
        infect(area, i, j + 1, m, n);
        infect(area, i - 1, j, m, n);
        infect(area, i, j - 1, m, n);
    }

}
