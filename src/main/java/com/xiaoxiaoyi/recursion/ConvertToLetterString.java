package com.xiaoxiaoyi.recursion;

import org.jetbrains.annotations.NotNull;

/**
 * @author xiaoxiaoyi
 * 数字字符串(0~9)转化为字符串的种数
 * 规定1对应A，2对应B，一直到26对应Z
 * 111可以看成11K和1A，也可以看成1A和11K，及一共2种转化方式
 */
public class ConvertToLetterString {

    public static int convertToLetterString(int num) {
        if (num < 1) {
            return 0;
        }
        char[] str = String.valueOf(num).toCharArray();
        // 从numberChars的0位置开始遍历所有可能
        return process(str, 0);
    }

    /**
     * 递归遍历numberChars上的每一种可能
     *
     * @param str   数字
     * @param index 当前遍历到的位置
     * @return 可能的组合数
     */
    private static int process(@NotNull char[] str, int index) {
        if (index == str.length) {
            // index == str.length时，说明前面0~index - 1都已经做好了决定，即有1种可能
            return 1;
        }
        if (str[index] == '0') {
            // 当前数字'0'不能自己包括和后续任何字符去匹配字母
            return 0;
        }
        int n = str.length;
        if (str[index] == '1') {
            // 自己作为单独的部分后续的可能
            int res = process(str, index + 1);
            if (index + 1 < n) {
                // 自己和后续的那个一起匹配一个字符的可能
                res += process(str, index + 2);
            }
            return res;
        }
        if (str[index] == '2') {
            // 自己可以作为单独的部分
            int res = process(str, index + 1);
            // 如果要和后续的一起，那么后续的那个字符要在0~6之间
            if (index + 1 < n && str[index + 1] >= '0' && str[index + 1] <= '6') {
                res += process(str, index + 2);
            }
            return res;
        }
        // 如果当前字符在'3'~'9'，则只能自己匹配
        return process(str, index + 1);
    }

    public static int convertToLetterString2(int num) {
        if (num < 1) {
            return 0;
        }
        char[] str = String.valueOf(num).toCharArray();
        // 从numberChars的0位置开始遍历所有可能
        return process2(str, 0);
    }

    private static int process2(@NotNull char[] str, int index) {
        if (index == str.length) {
            return 1;
        } else if (str[index] == '0') {
            return 0;
        }
        // 自己为一个字母
        int n = str.length, ans = process2(str, index + 1);
        // 如果还有后续字符且和后续字符的组合小于27
        if (index != n - 1 && (str[index] - '0') * 10 + (str[index + 1] - '0') < 27) {
            // 和后面的字符组成一个字母
            ans += process2(str, index + 2);
        }
        return ans;
    }

    public static int dpProcess(int num) {
        if (num < 1) {
            return 0;
        }
        char[] str = String.valueOf(num).toCharArray();
        int n = str.length;
        int[] dp = new int[n + 1];
        dp[n] = 1;
        if (str[n - 1] != '0') {
            // 如果最后一个字符不是0, 则dp[n - 1]就等于1
            dp[n - 1] = 1;
        }
        // 根据依赖关系从后往前求结果
        for (int index = n - 2; index >= 0; index--) {
            char cur = str[index];
            if (cur != '0') {
                dp[index] += dp[index + 1];
                if ((cur - '0') * 10 + (str[index + 1] - '0') < 27) {
                    // 前面的预处理是为了这里不出错
                    dp[index] += dp[index + 2];
                }
            }
        }
        return dp[0];
    }

    public static int dpProcess2(int num) {
        if (num < 1) {
            return 0;
        }
        char[] str = String.valueOf(num).toCharArray();
        // cur记录当前位置, next记录cur + 1的结果, behindNext记录cur + 2的结果
        int n = str.length, cur = 0, next = 0, behindNext = 1;
        if (str[n - 1] != '0') {
            next = 1;
        }
        for (int index = n - 2; index >= 0; index--) {
            char curChar = str[index];
            cur = 0;
            if (curChar != '0') {
                cur += next;
                if ((curChar - '0') * 10 + (str[index + 1] - '0') < 27) {
                    cur += behindNext;
                }
            }
            // 全部前移
            behindNext = next;
            next = cur;
        }
        return cur;
    }
}