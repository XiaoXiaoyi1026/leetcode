package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 尼姆博弈, 规定一个正数数组, 代表每一摞硬币的数量
 * 现在2个人分先后手拿硬币, 一次只能从一摞中拿, 且至少拿一枚硬币
 * 谁最后使对方面对无硬币可拿的状态谁就赢
 */
public class NimGame {

    public static String nimGame(int[] coins) {
        if (coins == null || coins.length == 0) {
            // 因为此时先手无硬币可拿
            return "后手";
        }
        int xor = 0;
        // 求所有硬币数量异或和
        for (int coin : coins) {
            xor ^= coin;
        }
        /*
        一开始如果所有硬币数量异或和为0, 那么后手必赢, 否则先手必赢
        因为只要让对手面对所有硬币数量异或和为0的状态, 那么就是必赢的
        如果开始硬币数量异或和就为0, 那么后手一定会让先手一直面对的是硬币数量异或和为0的状态
        后手必赢, 反之先手必赢
         */
        return xor == 0 ? "后手" : "先手";
    }

}
