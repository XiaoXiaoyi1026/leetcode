package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 编辑距离问题(很重要！！！)
 * 给定2个字符串strA和strB, 给定3种操作的代价: add, del, re
 * 分别代表增加一个字符, 删除一个字符, 将一个字符替换为另一个字符的代价
 * 问用这3种操作将strA变为strB的最小代价是多少?
 */
public class EditDistance {

    /**
     * @param strA 初始字符串
     * @param strB 目标字符串
     * @param add  新增一个字符的代价
     * @param del  删除一个字符的代价
     * @param re   替换一个字符的代价
     * @return strA变为strB的最小代价
     */
    public static int replace(String strA, String strB, int add, int del, int re) {
        int m = strA.length();
        int n = strB.length();
        // dp[i][j]代表将字符串A的0~i-1个字符替换成字符串B的0~j-1个字符的最小代价
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            // 0代表空串
            dp[i][0] = del * i;
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = add * i;
        }
        // 普遍位置, 根据依赖关系从左往右从上往下求解
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.min(
                        Math.min(
                                // 方法1, 将A的0~i-2变为B的0~j-1, 然后删除A的i-1位置上的字符
                                dp[i - 1][j] + del,
                                // 方法2, 将A的0~i-1变为B的0~j-2, 然后A加上Bj-1位置上的字符
                                dp[i][j - 1] + add
                        ),
                        // 方法3, 将A的0~i-1变为B的0~i-1, 然后根据Ai-1和Bj-1位置上的字符相不相等判断需不需要替换
                        dp[i - 1][j - 1] + (strA.charAt(i - 1) == strB.charAt(j - 1) ? 0 : re)
                );
            }
        }
        return dp[m][n];
    }

}
