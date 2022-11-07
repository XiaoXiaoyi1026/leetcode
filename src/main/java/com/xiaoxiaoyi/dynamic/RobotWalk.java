package com.xiaoxiaoyi.dynamic;

/**
 * @author xiaoxiaoyi
 * 机器人移动方式种数
 * 给定格子数n, 起点m和终点p, 还有要求的步数k, 要求一定要走k步
 * 求从m走到p的方式种数, 到格子1时只能往右走, 到格子n时只能往左走
 */
public class RobotWalk {

    /**
     *
     * @param n 一共有n个格子可以走
     * @param p 终点
     * @param m 起点
     * @param k 总共要求走k步
     */
    public static int walkWays(int n, int p, int m, int k) {
        return f(n, p, k, m);
    }

    /**
     *
     * @param n 格子总数
     * @param p 终点
     * @param rest 剩余步数
     * @param cur 当前格子
     */
    public static int f(int n, int p, int rest, int cur) {
        if (rest == 0) {
            // 当走完时, 如果走到了终点则是1种走法, 否则走法无效
            return cur == p ? 1 : 0;
        }
        if (cur == 1) {
            // 走到格子1时, 只能往右边走
            f(n, p, rest - 1, 2);
        }
        if (cur == n) {
            // 走到格子n时, 只能往左走
            f(n, p, rest - 1, n - 1);
        }
        // 返回往左或者往右走的总方式数
        return f(n, p, rest - 1, cur - 1) + f(n, p, rest - 1, cur + 1);
    }

}
