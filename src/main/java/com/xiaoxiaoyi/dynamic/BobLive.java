package com.xiaoxiaoyi.dynamic;

import org.jetbrains.annotations.NotNull;

/**
 * @author xiaoxiaoyi
 * bob存活概率问题, bob一开始在x, y 位置, 每一步随机往上下左右走, 如果越界则die
 * 返回bob走k步后的存活概率
 */
public class BobLive {

    /**
     * 最大公约数
     */
    public static long gcd(long num1, long num2) {
        long tmp;
        if (num1 < num2) {
            tmp = num1;
            num1 = num2;
            num2 = tmp;
        }
        while (num2 != 0) {
            tmp = num1;
            num1 = num2;
            num2 = tmp % num1;
        }
        return num1;
    }

    @NotNull
    public static String bob1(int m, int n, int x, int y, int step) {
        // 总的方法数为4^k
        long all = (long) Math.pow(4, step);
        // 存活方法数
        long live = live(m, n, x, y, step);
        // 求两个数的最大公约数(约分)
        long gcd = gcd(all, live);
        return (live / gcd) + "/" + (all / gcd);
    }

    /**
     * 可存活的位置为(0~m-1, 0~n-1)
     * 返回可存活的方法数
     */
    public static long live(int m, int n, int x, int y, int step) {
        if (x < 0 || x >= m || y < 0 || y >= n) {
            // 越界了, 没有存活方法
            return 0;
        }
        // 暴力递归尝试
        return process(m, n, x, y, step);
    }

    /**
     * 暴力递归
     */
    private static long process(int m, int n, int x, int y, int step) {
        if (x < 0 || x >= m || y < 0 || y >= n) {
            // 越界了, 没有存活方法
            return 0;
        }
        // base case 如果剩余步数为0且没越界, 则是1种存活方法
        if (step == 0) {
            return 1;
        }
        // 尝试上下左右4种走法
        long ans = live(m, n, x + 1, y, step - 1);
        ans += live(m, n, x - 1, y, step - 1);
        ans += live(m, n, x, y + 1, step - 1);
        ans += live(m, n, x, y - 1, step - 1);
        // 返回4种走法的存活方法之和
        return ans;
    }

    /**
     * dp 动态规划
     */
    @NotNull
    public static String bob2(int m, int n, int x, int y, int step) {
        long[][][] dp = new long[m + 2][n + 2][step + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // base case 在边界内且步数为0时, 则存活
                dp[i][j][0] = 1;
            }
        }
        // 从第1层开始
        for (int h = 1; h <= step; h++) {
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    dp[i][j][h] = dp[i - 1][j][h - 1] +
                            dp[i + 1][j][h - 1] +
                            dp[i][j - 1][h - 1] +
                            dp[i][j + 1][h - 1];
                }
            }
        }
        long all = (long) Math.pow(4, step);
        long live = dp[x + 1][y + 1][step];
        long gcd = gcd(all, live);
        return (live / gcd) + "/" + (all / gcd);
    }

}
