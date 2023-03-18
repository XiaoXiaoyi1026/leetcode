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

    public static int tou(int[] rooms) {
        if (rooms == null || rooms.length == 0) {
            return 0;
        }
        int n = rooms.length;
        // dp[i]代表0~i范围上偷的最优解
        int[] dp = new int[n];
        dp[0] = rooms[0];
        dp[1] = Math.max(rooms[0], rooms[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(Math.max(rooms[i], rooms[i] + dp[i - 2]), dp[i - 1]);
        }
        return dp[n - 1];
    }

}
