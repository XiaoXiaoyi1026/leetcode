package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 最长公共子序列, 区分清楚子串和子序列的定义, 两者完全不同
 */
public class LongestCommonSubsequence {

    /**
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 两个字符串的最长公共子序列
     */
    public static int get(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int m = chars1.length;
        int n = chars2.length;
        // dp[i][j]代表chars1[0~i-1]和chars2[0~j-1]部分的最长公共子序列的长度, 不要求以chars1[i-1]和chars2[j-1]做结尾
        int[][] dp = new int[m + 1][n + 1];
        /*
        dp[i][j]的可能性分析:
        1. 既不以chars1[i]结尾, 也不以chars2[j]结尾, dp[i][j] = dp[i-1][j-1]
        2. 以chars1[i]结尾, 不以chars2[j]结尾, dp[i][j] = dp[i][j-1]
        3. 不以chars1[i]结尾, 以chars2[j]结尾, dp[i][j] = dp[i-1][j]
        4. 在chars1[i] == chars2[j]的情况下, 既以chars1[i]结尾又以chars2[j]结尾, dp[i][j] = dp[i-1][j-1] + 1
        最终dp[i][j]取这4种可能性中的最大值
         */
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.max(
                        dp[i - 1][j - 1],
                        Math.max(
                                dp[i][j - 1],
                                dp[i - 1][j]
                        )
                );
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[i][j] = Math.max(
                            dp[i][j],
                            dp[i - 1][j - 1] + 1
                    );
                }
            }
        }
        return dp[m][n];
    }

    public static int get2(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int m = chars1.length;
        int n = chars2.length;
        // upperLine记录上一行的结果
        int[] upperLine = new int[n + 1];
        // left记录当前位置左边的结果
        int left = 0;
        for (int i = 1; i <= m; i++) {
            int[] curLine = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                // upperLine[j - 1]是左上, left是左边, upperLine[j]是上面
                int cur = Math.max(upperLine[j - 1], Math.max(left, upperLine[j]));
                if (chars1[i - 1] == chars2[j - 1]) {
                    cur = Math.max(cur, upperLine[j - 1] + 1);
                }
                left = cur;
                curLine[j] = cur;
            }
            upperLine = curLine;
        }
        return upperLine[n];
    }
}
