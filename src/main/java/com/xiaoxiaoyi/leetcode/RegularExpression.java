package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 正则表达式
 * 给定一个只包含小写字母的字符串str
 * 和一个只包含小写字符, 字符.和字符*的字符串exp
 * exp中的.代表任意一个小写字母, *前面必须有字符, 代表前面的字符出现0次或多次
 * 返回str是否能和exp匹配
 */
public class RegularExpression {

    public static boolean exp(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        return process(str.toCharArray(), exp.toCharArray(), 0, 0);
    }

    /**
     * @return 返回strChars[strIndex ~ 最后]与exp[expIndex~最后]能否匹配
     */
    private static boolean process(char[] strChars, char[] expChars, int strIndex, int expIndex) {
        // base case
        if (expIndex == expChars.length) {
            return strIndex == strChars.length;
        }
        // 可能性1: expIndex + 1不是*
        if (expIndex == expChars.length - 1 || expChars[expIndex + 1] != '*') {
            // 此情况下, str的当前位置字符必须与exp的当前位置字符相匹配
            return strIndex < strChars.length && (expChars[expIndex] == '.' || expChars[expIndex] == strChars[strIndex]) &&
                    process(strChars, expChars, strIndex + 1, expIndex + 1);
        }
        // 可能性2: expIndex + 1是*, 依次尝试*代表的每种可能
        while (strIndex != strChars.length && (expChars[expIndex] == '.' || expChars[expIndex] == strChars[strIndex])) {
            if (process(strChars, expChars, strIndex, expIndex + 2)) {
                // 如果尝试成功直接返回true
                return true;
            }
            // 尝试失败则让*代表的字符数+1, 再去尝试
            strIndex++;
        }
        // 尝试最后一种*的可能
        return process(strChars, expChars, strIndex, expIndex + 2);
    }

    public static boolean dp(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] strChars = str.toCharArray();
        char[] expChars = exp.toCharArray();
        int n = strChars.length;
        int m = expChars.length;
        boolean[][] dp = new boolean[n + 1][m + 1];
        // base case, 填时只需要考虑哪些位置可能是true
        dp[n][m] = true;
        /*
        由暴力递归的过程可以的出dp[strIndex][expIndex]依赖dp[strIndex+1][expIndex+1]
        还依赖dp[strIndex][expIndex+2], dp[strIndex+1][expIndex+2], ...
        但是base case并不全, 所以需要手动根据原题意带入补充base case到dp中
        由依赖关系可以知道dp至少需要先填好倒数2列和最后1行, 然后取余普遍位置即可从右往左, 从下往上根据依赖关系填完
        由原题意可知, 当expIndex==m(dp最后一列), 而strIndex!=n时, 结果都应该为false
        当expIndex==m-1(dp倒数第2列), 且expIndex位置上的字符不可能为*, 所以此时如果strIndex==n, 那么结果为false
        如果strIndex==n-1, 那么需要根据情况判断, 如果expIndex上的字符为.或者strIndex上的字符等于expIndex上的字符
        那么结果为true, 否则为false
         */
        if (n > 0 && m > 0) {
            dp[n - 1][m - 1] = (expChars[m - 1] == '.'
                    || expChars[m - 1] == strChars[n - 1]);
        }
        /*
        当expIndex==m-1, 而strIndex<n-1时, 说明exp不够匹配剩余字符, 结果为false
        当strIndex==n(最后一行), 而expIndex<m-1时, 由于*的存在, 只能根据exp是否符合
        字符*字符*字符* ... 的格式来判断位置上是否为true, 如果开始位置为*, 那么前一个位置必须是字符或者.才为true
        否则当前位置及其左边的所有位置都为false
        由于*不可能在exp的第1个位置, 所以下界为1
         */
        for (int expIndex = m - 2; expIndex >= 0; expIndex -= 2) {
            if (expChars[expIndex + 1] == '*') {
                dp[n][expIndex] = true;
            } else {
                break;
            }
        }
        // 填完dp最后一行和倒数两列后行从n-1开始, 列从m-2开始, 普遍位置依赖都可直接计算得出
        for (int strIndex = n - 1; strIndex >= 0; strIndex--) {
            for (int expIndex = m - 2; expIndex >= 0; expIndex--) {
                // 保证expIndex上不是*
                if (expChars[expIndex] != '*') {
                    // 情况1: expIndex+1位置不是*
                    if (expChars[expIndex + 1] != '*') {
                        dp[strIndex][expIndex] = (expChars[expIndex] == '.' || expChars[expIndex] == strChars[strIndex])
                                && dp[strIndex + 1][expIndex + 1];
                    }
                    // 情况2: expIndex+1位置是*
                    int tmp = strIndex;
                    while (tmp < n && (expChars[expIndex] == '.' || expChars[expIndex] == strChars[tmp])) {
                        if (dp[tmp][expIndex + 2]) {
                            dp[strIndex][expIndex] = true;
                            break;
                        }
                        tmp++;
                    }
                    // 如果前面没试出来, 再进行最后一次尝试
                    if (!dp[strIndex][expIndex]) {
                        dp[strIndex][expIndex] = dp[tmp][expIndex + 2];
                    }
                }
            }
        }
        return dp[0][0];
    }

}
