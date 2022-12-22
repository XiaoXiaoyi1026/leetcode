package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 规定abc...z这个字符串为母字符串
 * 它的子字符串按照a1b2c3...z26
 * ab 27 ac 28 ... az ... bc ... 进行编号
 * 现在给你任意一个它的子字符串, 问其编号是多少
 */
public class GetSubstringNumber {

    public static int substringIndex(String substring) {
        char[] chars = substring.toCharArray();
        int length = chars.length;
        int sum = 0;
        // 算出它前面的所有长度比它小的子字符串个数
        for (int i = 1; i < length; i++) {
            sum += lengthIs(i);
        }
        // 获得开头字符的编号
        int startIndex = chars[0] - 'a' + 1;
        // 加上以它开头字符前面的所有开头的子字符串且长度为length的个数
        for (int i = 1; i < startIndex; i++) {
            sum += startWith(i, length);
        }
        // 之前遍历到的字符的编号
        int pre = startIndex;
        for (int i = 1; i < length; i++) {
            // 当前遍历到的字符的编号
            int cur = chars[i] - 'a' + 1;
            // 统计前面字符和当前字符之间的所有字符为开头, 长度为length-i的所有子字符串个数
            for (int j = pre + 1; j < cur; j++) {
                sum += startWith(j, length - i);
            }
            pre = cur;
        }
        // 子字符串前面的所有都被加到sum里了, 那么它自己就在sum+1位置
        return sum + 1;
    }

    /**
     * @param length 字符串长度
     * @return 长度为length的子字符串个数
     */
    public static int lengthIs(int length) {
        int sum = 0;
        for (int i = 1; i <= 26; i++) {
            // 统计该长度下以任意一个字符开头的总数
            sum += startWith(i, length);
        }
        return sum;
    }

    /**
     * @param start  以哪个字符开头, a:1, b:2 ... z:26
     * @param length 字符串长度
     * @return 以某个字符开头的, 长度为length的字符串的个数
     */
    public static int startWith(int start, int length) {
        if (length == 1) {
            // 指定了开头, 且长度只有1, 那么这种字符串只有1个
            return 1;
        }
        int sum = 0;
        // start已经确定, 所有后面length-1长度的字符串开头可以是start+1到26的每一个字符
        for (int i = start + 1; i <= 26; i++) {
            sum += startWith(i, length - 1);
        }
        return sum;
    }

}
