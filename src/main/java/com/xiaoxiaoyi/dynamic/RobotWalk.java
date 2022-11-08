package com.xiaoxiaoyi.dynamic;

import java.util.Arrays;

/**
 * @author xiaoxiaoyi
 * 机器人移动方式种数
 * 给定格子数n, 起点m和终点p, 还有要求的步数k, 要求一定要走k步
 * 求从m走到p的方式种数, 到格子1时只能往右走, 到格子n时只能往左走
 */
public class RobotWalk {

    /**
     * @param n 一共有n个格子可以走
     * @param p 终点
     * @param m 起点
     * @param k 总共要求走k步
     */
    public static int walkWays1(int n, int p, int m, int k) {
        return f1(n, p, k, m);
    }

    /**
     * @param n    格子总数
     * @param p    终点
     * @param rest 剩余步数
     * @param cur  当前格子
     */
    public static int f1(int n, int p, int rest, int cur) {
        if (rest == 0) {
            // 当走完时, 如果走到了终点则是1种走法, 否则走法无效
            return cur == p ? 1 : 0;
        }
        if (cur == 1) {
            // 走到格子1时, 只能往右边走
            return f1(n, p, rest - 1, 2);
        }
        if (cur == n) {
            // 走到格子n时, 只能往左走
            return f1(n, p, rest - 1, n - 1);
        }
        // 返回往左或者往右走的总方式数
        return f1(n, p, rest - 1, cur - 1) + f1(n, p, rest - 1, cur + 1);
    }

    public static int walkWays2(int n, int p, int m, int k) {
        // 申请一个绝对够用的二维数组记录状态, 每一个状态都由步数k和当前位置m共同确定, k 和 m的范围也可以确定
        int[][] dp = new int[k + 1][n + 1];
        for (int[] ints : dp) {
            // dp数组所有值都变为-1
            Arrays.fill(ints, -1);
        }
        return f2(n, p, k, m, dp);
    }

    /**
     * @param n    格子总数
     * @param p    终点
     * @param rest 剩余步数
     * @param cur  当前格子
     * @param dp   缓存结构, 记录每一个子问题的解
     */
    public static int f2(int n, int p, int rest, int cur, int[][] dp) {
        if (dp[rest][cur] != -1) {
            // 记忆化搜索
            return dp[rest][cur];
        }
        // 缓存未命中
        if (rest == 0) {
            // 判断到没到终点
            dp[rest][cur] = cur == p ? 1 : 0;
        } else if (cur == 1) {
            // 走到最左边时只能往右边走
            dp[rest][cur] = f2(n, p, rest - 1, 2, dp);
        } else if (cur == n) {
            // 走到最右边只能往左边走
            dp[rest][cur] = f2(n, p, rest - 1, n - 1, dp);
        } else {
            // 返回往左或者往右走的总方式数
            dp[rest][cur] = f2(n, p, rest - 1, cur - 1, dp) + f2(n, p, rest - 1, cur + 1, dp);
        }
        return dp[rest][cur];
    }

    /**
     * 使用动态规划求解
     */
    public static int dpWalk(int n, int p, int m, int k) {
        // dp[*][0] 无效, 因为不可能走到 0 格子
        int[][] dp = new int[k + 1][n + 1];
        // k = 0 时 且刚好走到p则为1
        dp[0][p] = 1;
        // rest为行坐标
        for (int rest = 1; rest <= k; rest++) {
            // 当前格子编号cur为列坐标
            for (int cur = 1; cur <= n; cur++) {
                if (cur == 1) {
                    // 当前走到的格子为1时, 只能右边格子走
                    dp[rest][cur] = dp[rest - 1][cur + 1];
                } else if (cur == n) {
                    // 当前走到的格子为n是, 只能往左边走
                    dp[rest][cur] = dp[rest - 1][cur - 1];
                } else {
                    // 当前走到的格子在中间时, 可以往左右两边走
                    dp[rest][cur] = dp[rest - 1][cur - 1] + dp[rest - 1][cur + 1];
                }
            }
        }
        return dp[k][m];
    }

}
