package com.xiaoxiaoyi.recursion;

/**
 * @author xiaoxiaoyi
 * 数字字符串(0~9)转化为字符串的种数
 * 规定1对应A，2对应B，一直到26对应Z
 * 111可以看成11K和1A，也可以看成1A和11K，及一共2种转化方式
 */
public class ConvertToLetterString {

    public static int convertToLetterString(String numberString) {
        char[] numberChars = numberString.toCharArray();
        // 从numberChars的0位置开始遍历所有可能
        return process(numberChars, 0);
    }

    /**
     * 递归遍历numberChars上的每一种可能
     *
     * @param numberChars 数字
     * @param i 当前遍历到的位置
     * @return 可能的组合数
     */
    private static int process(char[] numberChars, int i) {
        if (i == numberChars.length) {
            // i == numberChars.length时，说明前面0~i - 1都已经做好了决定，即有1种可能
            return 1;
        }
        if (numberChars[i] == '0') {
            // 当前数字'0'不能自己包括和后续任何字符去匹配字母
            return 0;
        }
        if (numberChars[i] == '1') {
            // 自己作为单独的部分后续的可能
            int res = process(numberChars, i + 1);
            if (i + 1 < numberChars.length) {
                // 自己和后续的那个一起匹配一个字符的可能
                res += process(numberChars, i + 2);
            }
            return res;
        }
        if (numberChars[i] == '2') {
            // 自己可以作为单独的部分
            int res = process(numberChars, i + 1);
            // 如果要和后续的一起，那么后续的那个字符要在0~6之间
            if (i + 1 < numberChars.length && numberChars[i + 1] >= '0' && numberChars[i + 1] <= '6') {
                res += process(numberChars, i + 2);
            }
            return res;
        }
        // 如果当前字符在'3'~'9'，则只能自己匹配
        return process(numberChars, i + 1);
    }

}
