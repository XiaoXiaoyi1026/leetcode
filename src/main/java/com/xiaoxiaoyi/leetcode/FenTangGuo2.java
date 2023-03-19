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
 * 问如何分糖果使得糖果数最少, 小朋友围成一圈坐
 * @date 3/18/2023 6:05 PM
 */
public class FenTangGuo2 {

    public static void main(String[] args) {
        int[] fenShu = {3, 4, 5, 6, 2, 7, 3};
        System.out.println(fenTangGuo(fenShu));
    }

    @Contract(pure = true)
    public static int findIndex(@NotNull int[] fenShu) {
        for (int i = 1; i < fenShu.length - 1; i++) {
            if (fenShu[i] <= fenShu[i - 1] && fenShu[i] <= fenShu[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    @Contract(pure = true)
    public static int fenTangGuo(@NotNull int[] fenShu) {
        int n = fenShu.length;
        if (n == 0) {
            return 0;
        }
        int index = findIndex(fenShu);
        int[] tmp = new int[n + 1];
        tmp[0] = tmp[n] = fenShu[index];
        int after = n - index - 1;
        System.arraycopy(fenShu, index + 1, tmp, 1, after);
        System.arraycopy(fenShu, 0, tmp, after + 1, index);
        System.out.println(Arrays.toString(tmp));
        // 记录每个数字左边有多少个连续比自己小的数
        int[] left = new int[n];
        left[0] = 1;
        int[] right = new int[n];
        right[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            if (tmp[i] > tmp[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (tmp[i] > tmp[i + 1]) {
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
