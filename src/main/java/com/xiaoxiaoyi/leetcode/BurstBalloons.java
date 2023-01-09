package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 给定一个数组arr, 每个位置的数字代表该位置的气球对应的分数
 * 规则: 打爆i位置的气球得分 = 左边距离i位置最近的未爆气球分数 * i位置的气球分数 * 右边距离i位置最近的未爆气球分数
 * 如果左边或者右边不存在未爆气球, 则 * 1
 * 返回最高的可得分数
 */
public class BurstBalloons {

    public static int topScore(int[] balloons) {
        if (balloons == null || balloons.length == 0) {
            return 0;
        }
        return process(balloons, 0, balloons.length - 1);
    }

    /**
     * balloons[begin]~balloons[end]范围内气球打爆可得的最高分
     * 假设: balloons[begin-1]和arr[end+1]位置的气球一定未爆
     */
    private static int process(int[] balloons, int begin, int end) {
        // base case
        if (begin > end) {
            // 如果范围内没有气球, 返回0
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        // 范围内每个气球都尝试最后打爆
        for (int i = begin; i <= end; i++) {
            // i位置左边和右边的气球都打爆之后, 再打爆i位置的气球
            ans = process(balloons, begin, i - 1)
                    + process(balloons, i + 1, end)
                    + (begin > 0 ? balloons[begin - 1] : 1)
                    * balloons[i]
                    * (end < balloons.length - 1 ? balloons[end + 1] : 1
            );
        }
        return ans;
    }

    public static int dp(int[] balloons) {
        if (balloons == null || balloons.length == 0) {
            return 0;
        }
        int n = balloons.length;
        // dp[i][j]代表i~j范围上戳爆气球能得到的最高分数
        int[][] dp = new int[n][n];
        // 普遍情况
        for (int begin = n - 1; begin >= 0; begin--) {
            for (int end = begin; end < n; end++) {
                for (int i = begin; i <= end; i++) {
                    dp[begin][end] = (i == 0 ? 0 : dp[begin][i - 1])
                            + (i == n - 1 ? 0 : dp[i + 1][end])
                            + (begin > 0 ? balloons[begin - 1] : 1)
                            * balloons[i]
                            * (end < n - 1 ? balloons[end + 1] : 1);
                }
            }
        }
        return dp[0][n - 1];
    }

}
