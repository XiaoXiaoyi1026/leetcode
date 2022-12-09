package com.xiaoxiaoyi.dynamic;

/**
 * @author xiaoxiaoyi
 * 判断一个字符串是否符合一般人的数字书写形式
 */
public class IsNumber {

    private static char[] chars;
    private static final char DECIMAL_POINT = '.';
    private static final char MINUS = '-';
    private static boolean decimalPointAppears;
    private static boolean ans;

    public static boolean isNumber(String str) {
        if (str == null || str.length() == 0 || str.charAt(0) == '0') {
            // 多排除一个结尾为小数点.的情况, 因为最终转入number的可能是number也可能是小数点
            return false;
        }
        ans = false;
        decimalPointAppears = false;
        chars = str.toCharArray();
        // 开始可以是数字或者减号
        minus();
        if (!ans) {
            number(0);
        }
        return ans;
    }

    private static void minus() {
        // 只有可能开头是减号
        if (chars[0] == MINUS) {
            if (chars.length > 1 && !charIsZero(chars[1])) {
                // 减号之后可以数字, 但不能是0
                number(1);
            }
        }
    }

    private static void number(int cur) {
        if (cur < chars.length - 1) {
            char curChar = chars[cur++];
            if (charIsNumber(curChar)) {
                number(cur);
                if (!ans) {
                    decimalPoint(cur);
                }
            }
        } else {
            // 正数部分不能以0开头, 小数部分不能以0结尾
            char curChar = chars[cur];
            // 如果小数点出现过1次, 则不能以0结尾, 如果没有出现过小数点, 则可以以任何数字结尾
            ans = decimalPointAppears ? charIsNumberWithoutZero(curChar) : charIsNumber(curChar);
        }
    }

    private static void decimalPoint(int cur) {
        // 判断之前出现过小数点没
        if (decimalPointAppears) {
            // 如果之前已经出现过小数点
            return;
        }
        // 小数点不能出现在最后
        if (cur < chars.length - 1) {
            // 当前元素是小数点
            if (chars[cur++] == DECIMAL_POINT) {
                // 标记小数点出现
                decimalPointAppears = true;
                // 小数点后的数字后续只能是数字
                number(cur);
            }
            // 如果不是小数点
        }
    }

    private static boolean charIsNumber(char curChar) {
        return charIsNumberWithoutZero(curChar) || charIsZero(curChar);
    }

    private static boolean charIsNumberWithoutZero(char curChar) {
        return curChar > '0' && curChar <= '9';
    }

    private static boolean charIsZero(char curChar) {
        return curChar == '0';
    }

}
