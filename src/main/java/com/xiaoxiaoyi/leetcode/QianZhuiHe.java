package com.xiaoxiaoyi.leetcode;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 预处理技巧之前缀和
 * @date 3/19/2023 3:26 PM
 */
public class QianZhuiHe {

    public static void main(String[] args) {
        int[] nums = {5, 3, -3, 3, 2, 3};
        System.out.println(heTarget(nums, 5));
    }

    @Contract(pure = true)
    public static int heTarget(@NotNull int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        int tmp = 0;
        // dp[i]代表必须以i位置的数结尾的情况下有多少个子数组累加和等于target
//        int[] dp = new int[n];
        int ans = 0;
        // 辅助map, 记录每一个前缀和出现的次数
        Map<Integer, Integer> help = new HashMap<>();
        // 开始遍历前需要添加0这个前缀和的出现次数为1
        help.put(0, 1);
        for (int num : nums) {
            // tmp记录前缀和
            tmp += num;
            // 求必须以i位置的结尾的累加和为target的子数组数量, 即查前面tmp - target在前面的出现次数
            ans += help.getOrDefault(tmp - target, 0);
            // 然后将当前累加和更新进map
            help.put(tmp, help.getOrDefault(tmp, 0) + 1);
        }
        System.out.println(help);
//        System.out.println(Arrays.toString(dp));
//        tmp = 0;
//        // dp数组的累加和就是答案
//        for (int ans : dp) {
//            tmp += ans;
//        }
//        return tmp;
        return ans;
    }

}
