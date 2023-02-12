package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 猜数字的最小代价
 * @date 2/11/2023 4:22 PM
 */
public class GuessNumberMinCost {

    /**
     * 要猜的数字范围从1~n, 一共n个数字, 每猜一个数字, 就要那个数字那么多的代价
     * 例如我猜的是6, 那么需要支付的代价也为6, 如果猜对则不需要支付代价
     *
     * @param n 数字范围
     * @return 确保无论如何都能够猜中的情况下, 最小的代价
     */
    public static int mustWin(int n) {
        // 考虑最坏情况下所需的最小代价
        return guess(1, n);
    }

    /**
     * 要猜的目标数字范围为from~to, 递归过程中确保form <= to
     *
     * @param from 最小
     * @param to   最大
     * @return 最小代价
     */
    private static int guess(int from, int to) {
        // base case
        if (from == to) {
            // 已经锁定要猜的数字, 则必猜中, 代价为0
            return 0;
        }
        if (from == to - 1) {
            // 如果只有2个数, 则直接猜小的即可
            return from;
        }
        // 可以猜from或者猜to
        int minCost = Math.min(from + guess(from + 1, to), to + guess(from, to - 1));
        // 如果范围内还有其他数字, 每个数字都猜, 返回最小的代价
        for (int guessNumber = from + 1; guessNumber < to; guessNumber++) {
            // 如果猜了某个数字, 那么只会选择这个数字的左边或者右边继续猜, 而为了确保猜中, 所以最小代价一定是这两种情况中最大的那个
            minCost = Math.min(minCost, guessNumber + Math.max(guess(from, guessNumber - 1), guess(guessNumber + 1, to)));
        }
        return minCost;
    }

}
