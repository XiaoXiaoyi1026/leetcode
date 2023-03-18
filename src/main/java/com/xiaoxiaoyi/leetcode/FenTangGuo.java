package com.xiaoxiaoyi.leetcode;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 给小朋友分糖果, 给定每个小朋友的分数
 * 规则: 如果相邻两位小朋友的分数一样, 那么怎么分都行
 * 如果相邻两位小朋友分数不一样, 那么分数高的那个小朋友必须分到比分数低的小朋友多的糖果
 * 问如何分糖果使得糖果数最少
 * @date 3/18/2023 6:05 PM
 */
public class FenTangGuo {

    public static void main(String[] args) {
        int[] fenShu = {1, 1, 2, 3, 4, 4, 5, 3, 2, 4, 2, 1};
        System.out.println(fenTangGuo(fenShu));
    }

    @Contract(pure = true)
    public static int fenTangGuo(@NotNull int[] fenShu) {
        int n = fenShu.length;
        if (n == 0) {
            return 0;
        }
        // 记录每个数字左边有多少个连续比自己小的数
        int[] left = new int[n];
        left[0] = 1;
        int[] right = new int[n];
        right[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            if (fenShu[i] > fenShu[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (fenShu[i] > fenShu[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }
        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));
        int cost = 0;
        for (int i = 0; i < n; i++) {
            cost += Math.max(left[i], right[i]);
        }
        return cost;
    }

}
