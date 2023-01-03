package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.matrix.Matrix;

/**
 * @author xiaoxiaoyi
 * 给定一个字符串, 问最少添加几个字符可以使整体字符串回文
 */
public class MakePalindrome {

    public static int get(String str) {
        if (str == null || str.length() < 2) {
            return 0;
        }
        int n = str.length();
        // dp[i]][j]代表str的i~j范围最少添几个字符就可以使得, base case为当i == j时, 答案为0
        int[][] dp = new int[n][n];
        /*
        base case为当i == j时, 答案为0
        dp[i][i+1]代表2个字符最少添几个字符, 如果str[i] == str[i+1], 答案为0
        如果str[i] != str[i+1], 答案为1
         */
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = str.charAt(i) == str.charAt(i + 1) ? 0 : 1;
        }
        /*
        普遍情况下(i != j), 范围尝试模型只讨论范围的开始和结束位置的情况:
        1. 当str[i] == str[j]时, 那么只需要搞定i+1~j-1范围上的回文即可
        即dp[i][j] = dp[i+1][j-1]
        2. 当str[i] != str[j]时, 如果认为此时str[i+1][j]范围为回文, 则只需要在j位置的后面添上str[i]即可
        即dp[i][j] = dp[i+1][j] + 1
        3. 当str[i] != str[j]时, 如果认为此时str[i][j-1]范围为回文, 则只需要在i位置的前面添上str[j]即可
        即dp[i][j] = dp[i][j-1] + 1
         */
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.min(
                        dp[i + 1][j],
                        dp[i][j - 1]
                ) + 1;
                if (str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = Math.min(
                            dp[i][j],
                            dp[i + 1][j - 1]
                    );
                }
            }
        }
        Matrix.print(dp);
        // 返回0~n-1范围上的结果即可
        return dp[0][n - 1];
    }

    public static int get2(String str) {
        if (str == null || str.length() < 2) {
            return 0;
        }
        int n = str.length();
        // 代表当前行的下一行已经计算好的答案
        int[] downLine = new int[n];
        for (int row = n - 2; row >= 0; row--) {
            // 当前要求答案的行
            int[] curLine = new int[n];
            for (int col = row + 1; col < n; col++) {
                curLine[col] = Math.min(
                        downLine[col],
                        curLine[col - 1]
                ) + 1;
                if (str.charAt(row) == str.charAt(col)) {
                    curLine[col] = Math.min(
                            curLine[col],
                            downLine[col - 1]
                    );
                }
            }
            downLine = curLine;
        }
        // 返回最后求到的一行的最后一个答案即可
        return downLine[n - 1];
    }

    public static String getPalindrome(String str) {
        if (str == null || str.length() < 2) {
            return "";
        }
        int n = str.length();
        // dp[i]][j]代表str的i~j范围最少添几个字符
        int[][] dp = new int[n][n];
        /*
        base case为当i == j时, 答案为0
        dp[i][i+1]代表2个字符最少添几个字符, 如果str[i] == str[i+1], 答案为0
        如果str[i] != str[i+1], 答案为1
         */
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = str.charAt(i) == str.charAt(i + 1) ? 0 : 1;
        }
        /*
        普遍情况下(i != j), 范围尝试模型只讨论范围的开始和结束位置的情况:
        1. 当str[i] == str[j]时, 那么只需要搞定i+1~j-1范围上的回文即可
        即dp[i][j] = dp[i+1][j-1]
        2. 当str[i] != str[j]时, 如果认为此时str[i+1][j]范围为回文, 则只需要在j位置的后面添上str[i]即可
        即dp[i][j] = dp[i+1][j] + 1
        3. 当str[i] != str[j]时, 如果认为此时str[i][j-1]范围为回文, 则只需要在i位置的前面添上str[j]即可
        即dp[i][j] = dp[i][j-1] + 1
         */
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.min(
                        dp[i + 1][j],
                        dp[i][j - 1]
                ) + 1;
                if (str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = Math.min(
                            dp[i][j],
                            dp[i + 1][j - 1]
                    );
                }
            }
        }
        // 得到最少需要多少个字符才能变成回文串
        int need = dp[0][n - 1];
        // 准备结果字符数组
        char[] res = new char[n + need];
        int left = 0, right = res.length - 1;
        int row = 0, col = n - 1;
        while (left <= right) {
            // 判断当前位置的dp值是从哪里来的
            if (str.charAt(row) == str.charAt(col)) {
                // 说明是从左下角的格子来的
                res[left++] = str.charAt(row++);
                res[right--] = str.charAt(col--);
            } else {
                // 判断是从左边来的还是从右边来的
                if (dp[row][col] == dp[row][col - 1] + 1) {
                    // 说明是从左边来的, 最后一次进行的操作为: 在字符串的row-1添加col位置上的字符
                    res[left++] = str.charAt(col);
                    res[right--] = str.charAt(col);
                    col--;
                } else {
                    // 说明是从下边来的, 最后一次进行的操作为: 在字符串的col+1位置添加row位置上的字符
                    res[left++] = str.charAt(row);
                    res[right--] = str.charAt(row);
                    row++;
                }
            }
        }
        // 返回0~n-1范围上的结果即可
        return String.valueOf(res);
    }

}
