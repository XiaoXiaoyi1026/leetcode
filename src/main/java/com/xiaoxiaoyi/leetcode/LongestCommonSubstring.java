package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 最长公共子串问题(动态规划空间复杂度压缩)
 */
public class LongestCommonSubstring {

    public static String dp1(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return "";
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int m = str1.length();
        int n = str2.length();
        /*
        dp[i][j]的值代表:
        str1必须以i位置的字符结尾的子串和str2必须以j位置结尾的子串
        能够得到的最长公共子串的长度, 且最长公共子串也必须以i和j结尾
        */
        int[][] dp = new int[m + 1][n + 1];
        int ans = 0, end = 0;
        for (int row = 1; row <= m; row++) {
            for (int col = 1; col <= n; col++) {
                if (chars1[row - 1] == chars2[col - 1]) {
                    dp[row][col] = dp[row - 1][col - 1] + 1;
                    if (dp[row][col] > ans) {
                        ans = dp[row][col];
                        end = row;
                    }
                }
            }
        }
        return str1.substring(end - ans, end);
    }

    public static String dp2(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return "";
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int m = chars1.length;
        int n = chars2.length;
        int ans = 0, end = 0, row = 0, col = n - 1;
        while (row < m) {
            int i = row, j = col, tmp = 0;
            while (i < m && j < n) {
                if (chars1[i++] == chars2[j++]) {
                    tmp++;
                } else {
                    tmp = 0;
                }
                if (tmp > ans) {
                    ans = tmp;
                    // 记录下重复字串的终止位置
                    end = i;
                }
            }
            if (col == 0) {
                row++;
            } else {
                col--;
            }
        }
        return str1.substring(end - ans, end);
    }

}
