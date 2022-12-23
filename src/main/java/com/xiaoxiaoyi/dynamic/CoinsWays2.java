package com.xiaoxiaoyi.dynamic;

/**
 * @author xiaoxiaoyi
 * 给定一些货币, 一些是普通的, 每个面值可以拿多个
 * 一些是纪念币, 每个面值只能拿1个
 * 问凑出aim值的总值有多少种拿法
 */
public class CoinsWays2 {

    public static int get(int[] one, int[] ordinary, int aim) {
        if (aim == 0) {
            // 如果目标钱数是0, 则只有1中选法
            return 1;
        }
        int[] oneAns = takeOne(one, aim);
        int[] ordinaryAns = takeOrdinary(ordinary, aim);
        int ans = 0;
        for (int i = 0; i <= aim; i++) {
            ans += oneAns[i] * ordinaryAns[aim - i];
        }
        return ans;
    }

    public static int[] takeOne(int[] coins, int aim) {
        if (aim == 0) {
            // 如果目标钱数是0, 则只有1中选法
            return new int[]{1};
        }
        if (coins == null || coins.length == 0) {
            // 如果没有硬币可供选择, 则所有钱数的方法数都为0
            return new int[aim + 1];
        }
        int n = coins.length;
        int[][] dp = new int[n + 1][aim + 1];
        for (int i = 0; i <= n; i++) {
            // 当要凑的钱数为0时, 都只有1种拿法
            dp[i][0] = 1;
        }
        // 普遍情况, rest代表需要凑成的钱数
        for (int rest = 1; rest <= aim; rest++) {
            // cur代表当前遍历到的纪念币下标
            for (int cur = n - 1; cur >= 0; cur--) {
                dp[cur][rest] = dp[cur + 1][rest];
                if (rest - coins[cur] >= 0) {
                    dp[cur][rest] += dp[cur + 1][rest - coins[cur]];
                }
            }
        }
        return dp[0];
    }

    public static int[] takeOrdinary(int[] coins, int aim) {
        if (aim == 0) {
            // 如果目标钱数是0, 则只有1中选法
            return new int[]{1};
        }
        if (coins == null || coins.length == 0) {
            // 如果没有硬币可供选择, 则所有钱数的方法数都为0
            return new int[aim + 1];
        }
        int n = coins.length;
        // n+1代表可以使用0~n的硬币, aim+1代表目标为0~aim
        int[][] dp = new int[n + 1][aim + 1];
        for (int i = 0; i <= n; i++) {
            // 使用任意枚硬币凑出0块钱的方法数为1
            dp[i][0] = 1;
        }
        // cur代表当前遍历到的硬币下标(从下往上)
        for (int cur = n - 1; cur >= 0; cur--) {
            // rest代表aim - 之前0~cur-1个硬币已经凑出的钱数, 即后面还需要凑出多少钱(从左往右)
            for (int rest = 1; rest <= aim; rest++) {
                // 根据尝试的过程, 达成当前的目标总需要加上自己下方的情况
                dp[cur][rest] = dp[cur + 1][rest];
                if (rest - coins[cur] >= 0) {
                    // 当前位置还依赖左边的值
                    dp[cur][rest] += dp[cur][rest - coins[cur]];
                }
            }
        }
        return dp[0];
    }

}
