package com.xiaoxiaoyi.other;

/**
 * @author xiaoxiaoyi
 * 最长括号子串问题: 给你一个由左、右括号组成的字符串
 * 问：最长有效的括号连续子串长度为多少？
 * 技巧：凡是涉及到连续子串问题的，直接求以每个位置结尾的结果，最终答案必在其中
 */
public class LongestParenthesizedSubstring {

    public static int longestParenthesizedSubstring(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int ans = 0;
        // dp数组, 用来记录每个位置结尾的最长子串长度
        int[] dp = new int[str.length()];
        char[] chars = str.toCharArray();
        // 不用看0位置, 无论0位置是左括号还是右括号结果dp[i]都是0
        for (int i = 1; i < chars.length; i++) {
            char cur = chars[i];
            if (cur == ')') {
                // 当前位置先继承它前一个位置的信息
                dp[i] = dp[i - 1];
                // 拿到它的前1个位置的信息
                int pre = dp[i - 1];
                // 拿到原字符串中匹配这个右括号的位置
                int match = i - 1 - pre;
                if (match >= 0 && chars[match] == '(') {
                    // 如果位置上是左括号, 则匹配成功
                    dp[i] += 2;
                    // 在匹配成功的情况下, 还应该继承左括号前面那个位置的结果
                    if (match > 0) {
                        dp[i] += dp[match - 1];
                    }
                }
                // 更新结果
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }

}
