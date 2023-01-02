package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 最长回文子序列(范围尝试)
 */
public class LongestPalindromeSubsequence {

    public static int get(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int n = str.length();
        // dp[i][j]代表i~j范围内的最长回文子序列的长度
        int[][] dp = new int[n][n];
        // base case, 当i == j时, 即对角线上代表范围内只有1个字符时, 答案就是1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        /*
        普遍位置上的范围尝试可能只有4种:
        1. i~j范围内的最长回文子序列既不以str[i]开头, 也不以str[j]结尾
        即 dp[i][j] = dp[i+1][j-1]
        2. i~j范围内的最长回文子序列以str[i]开头, 不以str[j]结尾
        即 dp[i][j] = dp[i][j-1]
        3. i~j范围内的最长回文子序列不以str[i]开头, 以str[j]结尾
        即 dp[i][j] = dp[i+1][j]
        4. i~j范围内的最长回文子序列既以str[i]开头, 又以str[j]结尾
        即在str[i] == str[j]的情况下, dp[i][j] = dp[i+1][j-1] + 2
         */
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(
                        str.charAt(i) == str.charAt(j) ?
                                dp[i + 1][j - 1] + 2 :
                                dp[i + 1][j - 1],
                        Math.max(
                                dp[i][j - 1],
                                dp[i + 1][j]
                        )
                );
            }
        }
        // 最后返回范围0~n-1上的最长回文子序列的长度即可
        return dp[0][n - 1];
    }

    public static int get2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int n = str.length();
        // 代表当前行的下一行
        int[] downLine = new int[n];
        // dp的最后一行只有最后1个数有效, 且为1
        downLine[n - 1] = 1;
        for (int row = n - 2; row >= 0; row--) {
            // 代表当前行
            int[] curLine = new int[n];
            // 当col == row时, base case = 1
            curLine[row] = 1;
            for (int col = row + 1; col < n; col++) {
                curLine[col] = Math.max(
                        str.charAt(row) == str.charAt(col) ?
                                downLine[col - 1] + 2 :
                                downLine[col - 1],
                        Math.max(
                                curLine[col - 1],
                                downLine[col]
                        )
                );
            }
            downLine = curLine;
        }
        // 最后返回范围0~n-1上的最长回文子序列的长度即可
        return downLine[n - 1];
    }

}
