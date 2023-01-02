package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 给定一个字符串, 返回最少的回文子串个数
 */
public class SplitPalindromeSubstring {

    public static int get(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        if (str.length() == 1) {
            return 1;
        }
        // 从左往右的尝试模型, 返回0~str.length-1范围上的最少回文子串个数
        return process(str.toCharArray(), 0);
    }

    /**
     * @param chars 字符数组
     * @param cur   当前遍历到的位置
     * @return chars[cur]~chars[chars.length - 1]范围内的最少回文子串个数
     */
    public static int process(char[] chars, int cur) {
        // base case, 当cur == chars.length, 返回0
        if (cur == chars.length) {
            return 0;
        }
        // 预处理得到每个范围是否是回文
        boolean[][] isPalindrome = isPalindrome(chars);
        int ans = Integer.MAX_VALUE;
        // 普遍情况, 判断chars[cur]~chars[length-1]的每个子串, 如果子串是回文的, 则将其作为结果切割出的第一部分, 递归求解后面的结果
        for (int end = cur; end < chars.length; end++) {
            // 预处理将原本O(n)的回文串判断过程变成了O(1)的直接取结果的过程
            if (isPalindrome[cur][end]) {
                // 如果cur~end范围内是回文的, 则将其作为第一部分, 然后递归求解后面的end+1~chars.length-1范围内的解
                ans = Math.min(ans, 1 + process(chars, end + 1));
            }
        }
        return ans;
    }

    public static int dpGet(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        if (str.length() == 1) {
            return 1;
        }
        return dp(str.toCharArray());
    }

    public static int dp(char[] chars) {
        int n = chars.length;
        boolean[][] isPalindrome = isPalindrome(chars);
        // dp[i]的值代表范围i~n-1上的最少回文子串个数
        int[] dp = new int[n + 1];
        dp[n] = 0;
        dp[n - 1] = 1;
        for (int cur = n - 2; cur >= 0; cur--) {
            for (int end = cur; end < n; end++) {
                // O(1)
                if (isPalindrome[cur][end]) {
                    dp[cur] = Math.min(n - cur, 1 + dp[end + 1]);
                }
            }
        }
        return dp[0];
    }

    public static boolean[][] isPalindrome(char[] chars) {
        int n = chars.length;
        // isPalindrome[i][j]代表chars[i]~chars[j]范围上是不是回文串(范围尝试模型)
        boolean[][] isPalindrome = new boolean[n][n];
        isPalindrome[n - 1][n - 1] = true;
        for (int i = 0; i < n - 1; i++) {
            isPalindrome[i][i] = true;
            isPalindrome[i][i + 1] = chars[i] == chars[i + 1];
        }
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                isPalindrome[i][j] = chars[i] == chars[j] && isPalindrome[i + 1][j - 1];
            }
        }
        return isPalindrome;
    }
}
