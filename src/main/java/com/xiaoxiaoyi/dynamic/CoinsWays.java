package com.xiaoxiaoyi.dynamic;

/**
 * @author 20609
 * 拿硬币问题, 给你一些面值的硬币, 每个面值的硬币你都可以拿很多个
 * 有几种办法可以拿到目标钱数
 */
public class CoinsWays {

    public static int[] randomCoins(int len, int max) {
        int[] res = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * max) + 1;
        }
        return res;
    }

    public static int takeCoins1(int[] coins, int target) {
        if (coins == null || coins.length == 0) {
            return 0;
        }
        return process(coins, 0, target);
    }

    /**
     * 暴力递归
     *
     * @param coins  硬币面值
     * @param index  当前拿到了第几个硬币面值
     * @param target 目标钱数
     * @return 拿到目标硬币数的方法数
     */
    private static int process(int[] coins, int index, int target) {
        if (index == coins.length) {
            // 如果最后一个面值也计算完毕且此时的目标钱数为0则是1种可行解
            return target == 0 ? 1 : 0;
        }
        int ans = 0;
        // 当前面值从0张开始拿, 要求拿到的总面值不超过目标钱数
        for (int takes = 0; coins[index] * takes <= target; takes++) {
            // 遍历后续的所有可能解, 继续选下一个面值的硬币, 目标钱数减去本次选到的钱数
            ans += process(coins, index + 1, target - coins[index] * takes);
        }
        return ans;
    }

    /**
     * 动态规划 可变参数为当前遍历到的面值下标index和剩余目标钱数target
     */
    public static int takeCoins2(int[] coins, int target) {
        if (coins == null || coins.length == 0) {
            return 0;
        }
        int n = coins.length;
        int[][] dp = new int[n + 1][target + 1];
        // base case 如果index == coins.length且target == 0, 则返回1, 其他默认是0
        dp[n][0] = 1;
        // 从第n - 1个位置开始选, 一直选到第0个
        for (int index = n - 1; index >= 0; index--) {
            // rest : 剩余的钱数, 枚举所有可能的钱数
            for (int rest = target; rest >= 0; rest--) {
                int ans = 0;
                // 当前面值从0张开始拿, 要求拿到的总面值不超过目标钱数
                for (int takes = 0; coins[index] * takes <= rest; takes++) {
                    // 遍历后续的所有可能解, 继续选下一个面值的硬币, 目标钱数减去本次选到的钱数
                    ans += dp[index + 1][rest - coins[index] * takes];
                }
                dp[index][rest] = ans;
            }
        }
        return dp[0][target];
    }

    /**
     * 斜率优化: 如果填表时存在 枚举 行为
     * 则可以根据 观察 得出当前位置的结果能否由自己邻近的计算结果加工得到
     * 只与 观察 有关, 和 原题意 无关
     * 优化枚举张数的行为
     */
    public static int takeCoins3(int[] coins, int target) {
        if (coins == null || coins.length == 0) {
            return 0;
        }
        int n = coins.length;
        int[][] dp = new int[n + 1][target + 1];
        // base case 如果index == coins.length且target == 0, 则返回1, 其他默认是0
        dp[n][0] = 1;
        // 从第n - 1个位置开始选, 一直选到第0个(从下往上)
        for (int index = n - 1; index >= 0; index--) {
            // rest : 剩余的钱数, 枚举所有可能的钱数(因为是斜率优化, 而且当前位置与左边的计算结果相关, 所以只能从左往右计算)
            for (int rest = 0; rest <= target; rest++) {
                // 当前情况总是需要加上自己下面的那个情况结果
                dp[index][rest] = dp[index + 1][rest];
                if (rest - coins[index] >= 0) {
                    // 根据它左边的对应位置的结果可以直接快速计算得到当前位置的结果(从左往右)
                    dp[index][rest] += dp[index][rest - coins[index]];
                }
            }
        }
        return dp[0][target];
    }
}
