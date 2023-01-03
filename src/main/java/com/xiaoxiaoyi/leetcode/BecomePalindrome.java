package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 给定一个字符串str, 你可以删除一些字符, 最终目的是让其变成一个回文串
 * 认定空串不是回文串, 最终保留的字符如果相同但是在原来字符串中的位置不同认为是2种不同的方式
 * 即ABA这个字符串, 可以保留0位置上的A(1种), 也可以保留2位置上的A(2种)
 * 问统共有几种删除字符的方案可以使得字符串str变成回文串
 */
public class BecomePalindrome {

    public static int get(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        if (str.length() == 1) {
            return 1;
        }
        return dpProcess(str.toCharArray());
    }

    private static int dpProcess(char[] chars) {
        int n = chars.length;
        // dp[i][j]代表str[i]~str[j]范围内的解
        int[][] dp = new int[n][n];
        // base case
        for (int i = 0; i < n - 1; i++) {
            // 当i == j时, 只有1个字符, 只有1种回文子串
            dp[i][i] = 1;
            // 当i == j - 1时, 有2个字符, 当i和j位置的字符相等时, 有3个解, 否则只有2个解
            dp[i][i + 1] = chars[i] == chars[i + 1] ? 3 : 2;
        }
        /*
        如果用dp[i][j]代表str[i]~str[j]范围上变成回文串的方案数
        那么这是一个在范围上尝试的模型, 讨论最终结果开头和结尾的4种情况:
        1. 既不以i位置的字符为开头, 也不以j位置的字符为结尾
        2. 以i位置的字符为开头, 但不以j位置的字符为结尾
        3. 不以i位置的字符为开头, 但以j位置的字符为结尾
        4. 既以i位置的字符为开头, 又以j位置的字符为结尾
        举例可说明4种分类全量且互斥, 所以最终结果就是这4种情况的累加
        根据dp的意义可知: dp[i][j-1]代表str[i]~str[j-1]范围上的解
        但是由于str[i]~str[j-1]范围上的解并不一定以i位置的字符作开头, 只是确定不以j位置的字符作结尾
        所以其实dp[i][j-1]包含了1和2两种情况, 同理dp[i+1][j]包含了1和3两种情况, 两者直接相加会多加1次
        情况1, 而情况1对应的位置为dp[i+1][j-1], 所以要求1&2&3情况的和可以表示为dp[i][j-1]+dp[i+1][j]-dp[i+1][j-1]
        至于情况4, 只在str[i]==str[j]时成立, 因为空串不算回文子串, 所以此时分2种情况:
        1. i和j中间的位置不包含任何字符(空串), 即只由str[i]和str[j]组成的回文子串
        2. i和j中间的i+1~j-1位置为回文子串(dp[i+1][j-1])
        综上, 第4种情况可以表示为: 在str[i] == str[j]的情况下, dp[i][j] = 1 + dp[i+1][j-1]
        */
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
                if (chars[i] == chars[j]) {
                    dp[i][j] += 1 + dp[i + 1][j - 1];
                }
            }
        }
        // 求的是0~n-1范围内的解
        return dp[0][n - 1];
    }

}
