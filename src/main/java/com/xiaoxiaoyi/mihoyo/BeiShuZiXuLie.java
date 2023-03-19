package com.xiaoxiaoyi.mihoyo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 倍数子序列
 * @date 3/20/2023 7:02 AM
 */
public class BeiShuZiXuLie {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        int mod = (int) (Math.pow(10, 9) + 7);
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        Arrays.sort(nums);
        // map存储nums中每个元素及其对应的下标
        Map<Integer, Integer> map = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            map.put(nums[i], i);
        }
        // dp[i]代表以i位置结尾的倍数子序列数
        int[] dp = new int[n];
        int ans = 0, tmp;
        for (int i = 0; i < n; i++) {
            tmp = nums[i];
            // 遍历寻找tmp的因数
            for (int j = 1; j * j <= tmp; j++) {
                if (tmp % j == 0) {
                    // notJ * j == tmp
                    int notJ = tmp / j;
                    // 如果notJ存在于集nums中, 且notJ不等于j的话
                    if (map.containsKey(notJ) && j != notJ) {
                        // 加上notJ这个因数的结果
                        dp[i] += dp[map.get(notJ)];
                    }
                    // 如果j存在于nums中
                    if (map.containsKey(j)) {
                        // 加上j这个因数的结果
                        dp[i] += dp[map.get(j)];
                    }
                }
            }
            // 加到最终结果中去
            ans = (ans + dp[i]) % mod;
            // 包括自己在内的结果集多1个
            dp[i]++;
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(ans);
    }

}
