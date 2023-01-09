package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 给定一个数组arr, 长度为n代表有n个盘子
 * arr[i]代表i号盘子所在的柱子编号(1/2/3)
 * 问arr描述的是否是汉诺塔问题的中间状态, 如果是某一个中间状态, 则返回时第几个中间状态
 * 如果不是, 返回-1
 */
public class HanoiStep {

    public static int get(int[] arr) {
        if (arr == null) {
            return -1;
        }
        // 返回arr状态是将0~n-1盘子从1借助2移到3的中间第几步
        return process(arr, arr.length - 1, 1, 3, 2);
    }

    /**
     * 递归过程中i每次-1, 且只会走1个分支, 所以时间复杂度O(n)
     *
     * @param arr     状态描述数组
     * @param i       当前需要移动的盘子编号从0~i
     * @param from    从柱子from
     * @param another 借助柱子another
     * @param to      移到柱子to上去
     * @return arr是该子问题解中的第几步
     */
    private static int process(int[] arr, int i, int from, int to, int another) {
        if (i == -1) {
            // 如果没有盘子需要移动, 返回0
            return 0;
        }
        /*
        求解汉诺塔问题分3步:
        1. 将0~i-1位置的盘子从from借助to移到another, 总步数 = 2^(i-1) - 1
        2. 将i盘子从from移到to, 总步数 = 1
        3. 将0~i-1盘子从another借助from移到to, 总步数 = 2^(i-1) - 1
        可以得出: 当i位置的盘子在from时, 说明还在第1步的过程中
        当i盘子在another上时, 说明不是中间状态, 返回-1
        当i盘子在to上时, 说明在第3步中
         */
        int cur = arr[i];
        if (cur == another) {
            return -1;
        }
        if (cur == from) {
            return process(arr, i - 1, from, another, to);
        }
        int rest = process(arr, i - 1, another, to, from);
        return rest == -1 ? -1 : (1 << i) + rest;
    }

    public static int get2(int[] arr) {
        if (arr == null) {
            return -1;
        }
        int n = arr.length;
        int i = n - 1;
        int from = 1;
        int to = 3;
        int another = 2;
        int res = 0;
        int tmp;
        while (i >= 0) {
            int cur = arr[i];
            if (cur == another) {
                return -1;
            }
            if (cur == from) {
                tmp = to;
                to = another;
            } else {
                res += 1 << i;
                tmp = from;
                from = another;
            }
            another = tmp;
            i--;
        }
        return res;
    }

}
