package com.xiaoxiaoyi.leetcode;

/**
 * @author 20609
 * 给定一个整数n, 返回0~n之间的数每个数中1的个数数组
 */
public class CountOne {

    public static int[] countOne(int n) {
        int[] res = new int[n + 1];
        int highBit = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                // i为2的整数倍, high记录最高有位1
                highBit = i;
            }
            res[i] = res[i - highBit] + 1;
        }
        return res;
    }

}
