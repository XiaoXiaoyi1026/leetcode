package com.xiaoxiaoyi.other;

/**
 * @author xiaoxiaoyi
 * 给定一个合法的括号字符串, 规定不包含括号的串深度为0
 * 若合法串X的深度为x, 合法串Y的深度为y, 则XY的深度为max(x, y)
 */
public class MaxDeep {

    public static int maxDeep(String str) {
        int count = 0, ans = 0;
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            if (aChar == '(') {
                count++;
                ans = Math.max(ans, count);
            } else if (aChar == ')') {
                count--;
            }
        }
        return ans;
    }

}
