package com.xiaoxiaoyi.leetcode;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 切割01字符串, 要求每一小部分的01等比例
 * @date 3/19/2023 4:07 PM
 */
public class Split01 {

    public static void main(String[] args) {
        int[] nums01 = {0, 1, 0, 1, 0, 0, 0, 0, 1};
        System.out.println(Arrays.toString(split(nums01)));
    }

    @NotNull
    @Contract(pure = true)
    public static int[] split(@NotNull int[] nums01) {
        if (nums01.length == 0) {
            return new int[]{0};
        }
        int n = nums01.length;
        // 存储0和1比例的出现次数
        Map<Float, Integer> help = new Hashtable<>();
        int[] dp = new int[n];
        int shuLiang0 = 0;
        int shuLiang1 = 0;
        int num;
        float biLi;
        for (int i = 0; i < n; i++) {
            num = nums01[i];
            if (num == 0) {
                shuLiang0++;
            } else {
                shuLiang1++;
            }
            // 包含当前位置在内, 0和1的比例
            biLi = shuLiang0 / (float) shuLiang1;
            // 从help中拿到之前这个比例的出现次数, 如果该比例出现过, 那么该位置+1, 否则该位置=1
            dp[i] = help.getOrDefault(biLi, 0) + 1;
            // 更新该比例的出现次数
            help.put(biLi, help.getOrDefault(biLi, 0) + 1);
        }
        System.out.println(help);
        return dp;
    }
}
