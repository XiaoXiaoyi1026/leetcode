package com.xiaoxiaoyi.leetcode;

import java.util.Scanner;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 打家劫舍2
 * @date 3/18/2023 2:38 PM
 */
public class DaJiaJieShe2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println(Math.max(
                Math.max(
                        tou(nums, 1, n - 1),
                        tou(nums, 0, n - 2)
                ),
                Math.max(
                        nums[0] + tou(nums, 2, n - 2),
                        nums[n - 1] + tou(nums, 1, n - 3)
                )
        ));
    }

    public static int tou(int[] nums, int left, int right) {
        int length = right - left + 1;
        if (length == 0) {
            return 0;
        }
        if (length < 2) {
            return nums[left];
        }
        // dp[i]代表0~i范围上偷的最优解
        int prePre = nums[left];
        int pre = Math.max(nums[left], nums[left + 1]);
        for (int i = left + 2, cur; i <= right; i++) {
            cur = Math.max(Math.max(prePre + nums[i], nums[i]), pre);
            prePre = pre;
            pre = cur;
        }
        return pre;
    }

}
