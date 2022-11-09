package com.xiaoxiaoyi.dynamic;

import java.util.Arrays;

/**
 * @author xiaoxaioyi
 * 拿硬币问题, 给你一个硬币数组, 每个位置的值表示你有1枚该硬币, 问你最少拿多少枚硬币可以组成n元
 */
public class TakeCoins {

    /**
     * 获取随机硬币数组
     *
     * @param len 硬币数量
     * @param max 硬币最大面额
     * @return 随机硬币数组
     */
    public static int[] getRandomCoins(int len, int max) {
        int[] coins = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < coins.length; i++) {
            coins[i] = (int) (Math.random() * max) + 1;
        }
        return coins;
    }

    /**
     * 统计一共有多少种拿法
     *
     * @param coins    硬币数组
     * @param money    目标钱数
     * @param curMoney 当前拿到的钱数
     * @param cur      当前拿到的硬币位置
     * @return 种数
     */
    public static int f(int[] coins, int money, int curMoney, int cur) {
        if (cur == coins.length) {
            // 如果已经选完了最后1枚硬币, 则判断当前拿到的钱数是否等于目标钱数, 如果相等则是一种有效的拿法
            return curMoney == money ? 1 : 0;
        }
        // 返回拿或者不拿当前硬币的总方案数
        return f(coins, money, curMoney + coins[cur], cur + 1) +
                f(coins, money, curMoney, cur + 1);
    }

    /**
     * 暴力尝试
     *
     * @param coins 硬币数组
     * @param money 目标钱数
     * @return 最小硬币数
     */
    public static int takeCoins1(int[] coins, int money) {
        // f函数是求拿目标money的方案数
        f(coins, money, 0, 0);
        // minCoins是返回拿到目标money的最小硬币数
        return minCoins1(coins, 0,  money);
    }

    /**
     *
     * @param coins 硬币数组
     * @param cur 当前选到的硬币下标
     * @param curMoney 目标钱数
     * @return 能组成目标钱数的最小硬币数
     */
    public static int minCoins1(int[] coins, int cur, int curMoney) {
        if (curMoney < 0) {
            // 要组成负数的钱, 无法达成, 返回-1
            return -1;
        }
        if (curMoney == 0) {
            // 组成0的钱, 最小硬币数为0
            return 0;
        }
        if (cur == coins.length) {
            // 如果已经选完了所有的硬币, 还没有达成目标, 则返回-1
            return -1;
        }
        // 尝试拿当前硬币或者不拿当前硬币中的最小值
        int doNotTake = minCoins1(coins, cur + 1, curMoney);
        int take = minCoins1(coins, cur + 1, curMoney - coins[cur]);
        if (doNotTake == -1 && take == -1) {
            // 如果拿或者不拿当前硬币都无解, 则return -1
            return -1;
        } else {
            if (doNotTake == -1) {
                // 如果不拿当前硬币无解, 则返回拿当前硬币的解 + 1
                return take + 1;
            } else if (take == -1) {
                // 如果拿当前硬币无解, 则返回不拿当前硬币的解
                return doNotTake;
            } else {
                // 两种拿法都有解, 返回较小的那个
                return Math.min(doNotTake, take + 1);
            }
        }
    }


    /**
     * 记忆化搜索
     *
     * @param coins 硬币数组
     * @param money 目标钱数
     * @return 最小硬币数
     */
    public static int takeCoins2(int[] coins, int money) {
        // dp缓存
        int[][] dp = new int[coins.length + 1][money + 1];
        // 由于-1表示无效解, 0表示0个硬币, 所以一开始用-2填充dp
        for (int[] ints : dp) {
            Arrays.fill(ints, -2);
        }
        // minCoins是返回拿到目标money的最小硬币数
        return minCoins2(coins, 0,  money, dp);
    }

    /**
     *
     * @param coins 硬币数组
     * @param cur 当前选到的硬币下标
     * @param curMoney 目标钱数
     * @return 能组成目标钱数的最小硬币数
     */
    public static int minCoins2(int[] coins, int cur, int curMoney, int[][] dp) {
        if (curMoney < 0) {
            // 要组成负数的钱, 无法达成, 返回-1
            return -1;
        }
        if (dp[cur][curMoney] != -2) {
            // 已经算过, 直接返回
            return dp[cur][curMoney];
        }
        // 缓存未命中
        if (curMoney == 0) {
            // 组成0的钱, 最小硬币数为0
            dp[cur][curMoney] = 0;
            return dp[cur][curMoney];
        }
        if (cur == coins.length) {
            // 如果已经选完了所有的硬币, 还没有达成目标, 则返回-1
            dp[cur][curMoney] = -1;
            return dp[cur][curMoney];
        }
        // 尝试拿当前硬币或者不拿当前硬币中的最小值
        int doNotTake = minCoins2(coins, cur + 1, curMoney, dp);
        int take = minCoins2(coins, cur + 1, curMoney - coins[cur], dp);
        if (doNotTake == -1 && take == -1) {
            // 如果拿或者不拿当前硬币都无解, 则return -1
            dp[cur][curMoney] = -1;
        } else {
            if (doNotTake == -1) {
                // 如果不拿当前硬币无解, 则返回拿当前硬币的解 + 1
                dp[cur][curMoney] = take + 1;
            } else if (take == -1) {
                // 如果拿当前硬币无解, 则返回不拿当前硬币的解
                dp[cur][curMoney] = doNotTake;
            } else {
                // 两种拿法都有解, 返回较小的那个
                dp[cur][curMoney] = Math.min(doNotTake, take + 1);
            }
        }
        return dp[cur][curMoney];
    }

    /**
     * 动态规划
     *
     * @param coins 硬币数组
     * @param money 目标钱数
     * @return 最小硬币数
     */
    public static int takeCoins3(int[] coins, int money) {
        // minCoins是返回拿到目标money的最小硬币数
        return minCoins3(coins, money);
    }

    /**
     * 严格表结构
     *
     * @param coins 硬币数组
     * @param money 目标钱数
     * @return 能组成目标钱数的最小硬币数
     */
    public static int minCoins3(int[] coins, int money) {
        int n = coins.length;
        // 初始化严格表结构的表, 横坐标为硬币下标, 纵坐标为钱数
        int[][] dp = new int[n + 1][money + 1];
        for (int index = 0; index <= n; index ++) {
            // 代表money为0时, 需要0枚硬币
            dp[index][0] = 0;
        }

        for (int col = 1; col <= money ; col++) {
            // 代表当遍历完最后一枚硬币时, 返回-1
            dp[n][col] = -1;
        }

        // 普通情况
        for (int index = n - 1; index >= 0; index--) {
            // 从下往上填表, 跳过index = n, 因为index = n时为-1
            for (int col = 1; col <= money; col++) {
                // 从左往右填表, 跳过col = 0, 因为col = 0时为0
                // int doNotTake = minCoins1(coins, cur + 1, curMoney);
                int doNotTake = dp[index + 1][col];
                // int take = minCoins1(coins, cur + 1, curMoney - coins[cur]);
                int take = -1;
                if (col - coins[index] >= 0) {
                    // 防止越界
                    take = dp[index + 1][col - coins[index]];
                }
                if (doNotTake == -1 && take == -1) {
                    // 如果拿或者不拿当前硬币都无解, 则return -1
                    dp[index][col] = -1;
                } else {
                    if (doNotTake == -1) {
                        // 如果不拿当前硬币无解, 则返回拿当前硬币的解 + 1
                        dp[index][col] = take + 1;
                    } else if (take == -1) {
                        // 如果拿当前硬币无解, 则返回不拿当前硬币的解
                        dp[index][col] = doNotTake;
                    } else {
                        // 两种拿法都有解, 返回较小的那个
                        dp[index][col] = Math.min(doNotTake, take + 1);
                    }
                }
            }
        }
        // 返回要求的结果, 即从0开始拿硬币, 拿到目标硬币值的最小值
        return dp[0][money];
    }
}
