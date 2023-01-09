package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.matrix.Matrix;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author xiaoxiaoyi
 * 判断一个字符串是不是另一个字符串你的旋变串
 * 假设字符串str1 = abcd, 对其进行拆分:
 *               abcd
 *              /    \
 *             a     bcd
 *                   / \
 *                  b  cd
 *                     / \
 *                    c   d
 * 这是一种拆法, 在这种拆法中, 一共有3层的兄弟节点{a, bcd}, {b, cd}, {c, d}
 * 每一层的兄弟节点可选择旋转与不旋转, 加入选择第3层选择旋转, 第2层不旋转, 第1层旋转
 * 那么可以得出abcd的一个旋变串bdca:
 *                    d   c
 *                    \   /
 *                  b  dc
 *                  \  /
 *                  bdc   a
 *                   \   /
 *                   bdca
 * 我们称abcd和bdca互为旋变串
 */
public class RotatingChangeString {

    public static boolean isRotatingChange(String str1, String str2) {
        if (str1 == null || str2 == null) {
            throw new NullPointerException("String can not be null!");
        }
        // 大过滤器: 要求str1和str2中的字符种类一致, 且每种字符的数量相等
        return str1.length() == str2.length()
                //O(n)
                && filter(str1, str2)
                && process(str1, str2, 0, 0, str1.length());
    }

    /**
     * 比较str1[begin1 ~ (begin + length - 1)]和str2[begin2 ~ (begin2 + length - 1)]范围的字符是否互为旋变串
     */
    private static boolean process(String str1, String str2, int begin1, int begin2, int length) {
        // base case: 当长度为1时, 只需要字符相等即可
        if (length == 1) {
            return str1.charAt(begin1) == str2.charAt(begin2);
        }
        // 普遍情况, 以第一刀切的左半部分长度进行分类
        for (int split = 1; split < length; split++) {
            if (
                    // str1左边匹配str2左边, str1右边匹配str2右边
                    (process(str1, str2, begin1, begin2, split)
                    && process(str1, str2, begin1 + split, begin2 + split, length - split))
                            // str1左边匹配str2右边, str1右边匹配str2左边(旋转)
                    || (process(str1, str2, begin1, begin2 + length - split, split)
                    && process(str1, str2, begin1 + split, begin2, length - split))
            ) {
                return true;
            }
        }
        return false;
    }

    public static boolean dp(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        if (filter(str1, str2)) {
            int n = str1.length();
            // 3个可变参数: begin1, begin2(0~n-1), length(1~n)
            Boolean[][][] dp = new Boolean[n + 1][n][n];
            // base case: length == 1
            for (int begin1 = 0; begin1 < n; begin1++) {
                for (int begin2 = 0; begin2 < n; begin2++) {
                    dp[1][begin1][begin2] = str1.charAt(begin1) == str2.charAt(begin2);
                }
            }
            Matrix.print(dp[1]);
            System.out.println("====================");
            for (int length = 2; length <= n; length++) {
                for (int begin1 = 0; begin1 <= n - length; begin1++) {
                    for (int begin2 = 0; begin2 <= n - length; begin2++) {
                        for (int split = 1; split < length; split++) {
                            if (
                                    (
                                            (dp[split][begin1][begin2] != null && dp[split][begin1][begin2])
                                            && (dp[length - split][begin1 + split][begin2 + split] != null && dp[length - split][begin1 + split][begin2 + split])
                            ) || (
                                            (dp[split][begin1][begin2 + length - split] != null && dp[split][begin1][begin2 + length - split])
                                            && (dp[length - split][begin1 + split][begin2] != null && dp[length - split][begin1 + split][begin2])
                                    )
                            ) {
                                // 找到一个可以的直接退出
                                dp[length][begin1][begin2] = true;
                                break;
                            }
                        }
                    }
                }
                Matrix.print(dp[length]);
                System.out.println("====================");
            }
            return dp[n][0][0];
        }
        return false;
    }

    private static boolean filter(String str1, String str2) {
        int n = str1.length();
        Map<Character, Integer> charMap1 = new HashMap<>();
        // 统计str1中每一个字符的出现次数
        for (int i = 0; i < n; i++) {
            char cur = str1.charAt(i);
            charMap1.put(cur, charMap1.getOrDefault(cur, 0) + 1);
        }
        Map<Character, Integer> charMap2 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char cur = str2.charAt(i);
            if (!charMap1.containsKey(cur)) {
                // 如果str2中出现了str1中未出现过的字符
                return false;
            }
            charMap2.put(cur, charMap2.getOrDefault(cur, 0) + 1);
        }
        for (Character c : charMap1.keySet()) {
            if (!Objects.equals(charMap1.get(c), charMap2.get(c))) {
                // 如果词频不等, 也返回false
                return false;
            }
        }
        return true;
    }

}
