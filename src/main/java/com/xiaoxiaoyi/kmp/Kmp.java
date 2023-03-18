package com.xiaoxiaoyi.kmp;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author xiaoxiaoyi
 * KMP算法
 */
public class Kmp {

    /**
     * @return str2在str1中出现的位置
     */
    public static int getIndexOf(String str1, String str2) {
        if (str1 == null || str2 == null || str2.length() < 1 || str1.length() < str2.length()) {
            return -1;
        }
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        // 指向两个字符串的初始字符
        int i = 0, j = 0;
        // O(M), M为str2的长度，获取str2的next数组，next[i]代表从str2的0~i-1字符中的前后缀相同的字符串最大长度，规定next[0] = -1
        int[] next = getNextArray(arr2);
        // O(N), N为str1的长度
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                // 当前字符匹配成功时，都向右移动
                i++;
                j++;
            } else {
                if (j == 0 || next[j] == -1) {
                    // 规定next[0] = -1, 所以此时j一定为0，代表j无法再向前移动，则i向后移动
                    i++;
                } else {
                    // 否则j移动到next中记录的0~j-1的最大相同前后缀的前缀的后一个字符
                    j = next[j];
                }
            }
        }
        // i 或者j 越界了
        return j == arr2.length ? i - j : -1;
    }

    /**
     * 获取字符串的next数组
     */
    @NotNull
    @Contract(pure = true)
    private static int[] getNextArray(@NotNull char[] arr) {
        if (arr.length == 1) {
            // 如果字符串长度等于1, 则直接返回-1, 因为0~i-1位置没有字符
            return new int[]{-1};
        }
        int n = arr.length;
        // 要返回的next数组, 长度等于原字符串的长度
        int[] next = new int[n];
        // 代表以第1个字符为开头的最长相等前后缀长度为-1
        next[0] = -1;
        // 以第2个字符开头的最长相等前后缀长度为0
        next[1] = 0;
        // cur代表求的是0~cur-1上的next值, length记录的是最长前后缀重复字符串的长度
        int cur = 2, length = 0;
        while (cur < n) {
            if (arr[cur - 1] == arr[length]) {
                // 如果cur - 1位置和length位置的相等，则i位置的最大相等前后缀长度为当前位置的length+1
                next[cur++] = ++length;
            } else if (length > 0) {
                // cur - 1和length不匹配，且当length还可以往前取最长前后缀相等的字符串长度，就往前尝试
                length = next[length];
            } else {
                // length不能再往前取最长前后缀相等的字符串长度时，说明i位置的最长相等前后缀长度为0
                next[cur++] = 0;
            }
        }
        return next;
    }

}
