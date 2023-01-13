package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 */
public class DriverAssignment {

    public static int maxMoney1(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) != 0) {
            return 0;
        }
        return process(income, 0, income.length >> 1);
    }

    /**
     * @param income 每个司机去A或者B的收入
     * @param index  当前选到的司机下标
     * @param rest   A地剩余多少个司机名额
     * @return 从index开始到最后的所有司机, 分配给A, B两地, 使得AB两地司机数量一样能够获得的最大利润
     */
    private static int process(int[][] income, int index, int rest) {
        if (index == income.length) {
            // 没有司机可供分配了, 则后续无法获得任何利润
            return 0;
        }
        if (rest == 0) {
            // 如果A地已经没有名额了, 那么后续所有司机都要去B地
            return income[index][1] + process(income, index + 1, rest);
        }
        if (income.length - index == rest) {
            // 只剩下rest个人的时候, 说明B已经分配完, 剩下的人只能去A
            return income[index][0] + process(income, index + 1, rest - 1);
        }
        // 当前司机可选择去A或者去B
        int p1 = income[index][0] + process(income, index + 1, rest - 1);
        int p2 = income[index][1] + process(income, index + 1, rest);
        return Math.max(p1, p2);
    }

    public static int maxMoney2(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) != 0) {
            return 0;
        }
        return dp(income);
    }

    private static int dp(int[][] income) {
        int n = income.length;
        /*
        index变化范围0~n, rest变化范围0~n/2
        依赖关系: dp[index][rest] = Math.max(income[index][1] + dp[index + 1][rest], income[index][0] + dp[index + 1][rest - 1])
        base case: index == n时, dp[n][rest] = 0
        rest == 0时, dp[index][0] = income[index][1] + dp[index + 1][rest]
        rest == n - index时, dp[index][rest] = income[index][0] + dp[index + 1][rest - 1]
        由于index单调递增, 可以优化空间
         */
        int[] dp = new int[(n >> 1) + 1];
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = n >> 1; rest >= 0; rest--) {
                if (rest == 0) {
                    dp[0] = income[index][1] + dp[rest];
                } else if (rest == n - index) {
                    dp[rest] = income[index][0] + dp[rest - 1];
                } else {
                    dp[rest] = Math.max(income[index][1] + dp[rest], income[index][0] + dp[rest - 1]);
                }
            }
        }
        return dp[n >> 1];
    }

}
