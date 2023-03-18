package com.xiaoxiaoyi.leetcode;

import java.util.Scanner;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 偷盗问题
 * @date 3/18/2023 2:38 PM
 */
public class DaJiaJieShe1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] rooms = new int[n];
        for (int i = 0; i < n; i++) {
            rooms[i] = sc.nextInt();
        }
        System.out.println(tou(rooms));
    }

    public static int tou(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length < 2) {
            return nums[0];
        }
        int n = nums.length;
        // dp[i]代表0~i范围上偷的最优解
        int prePre = nums[0];
        int pre = Math.max(nums[0], nums[1]);
        for (int i = 2, cur; i < n; i++) {
            cur = Math.max(Math.max(prePre + nums[i], nums[i]), pre);
            prePre = pre;
            pre = cur;
        }
        return pre;
    }

}
