package com.xiaoxiaoyi.leetcode;

import java.util.*;

/**
 * @author xiaoxiaoyi
 * 给你一个 不含重复 单词的字符串数组 words ，请你找出并返回 words 中的所有 连接词 。
 * 连接词 定义为：一个完全由给定数组中的至少两个较短单词组成的字符串。
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/concatenated-words">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code472 {

    /**
     * 存储所有word
     */
    private static final Set<Long> HASH_SET = new HashSet<>();

    /**
     * p和offset都是求辅助求hash的
     */
    private static final int P = 131, OFFSET = 128;

    public static List<String> findAllConcatenatedWordsInaDict(String[] words) {
        for (String word : words) {
            long hash = 0;
            for (char c : word.toCharArray()) {
                // 求出每个word的hash值
                hash = hash * P + (c - 'a') + OFFSET;
            }
            // set存储每个word的hash值
            HASH_SET.add(hash);
        }
        System.out.println(HASH_SET);
        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (check(word)) {
                // 判断当前字符串是否符合要求，符合则加入结果中
                res.add(word);
            }
        }
        return res;
    }

    private static boolean check(String word) {
        int n = word.length();
        // f[i]代表从word的0~i-1个字符中能够切分出的最大子字符串个数
        int[] f = new int[n + 1];
        // 开始都为-1
        Arrays.fill(f, -1);
        // 一个字符不能切分成任何子字符串
        f[0] = 0;
        // 从0~n开始遍历word的每一个char
        for (int i = 0; i <= n; i++) {
            if (f[i] == -1) {
                // 如果当前字符之前的字符串不能拆分，则直接跳过
                continue;
            }
            // 记录当前字符串的hash值
            long cur = 0;
            // 从i位置开始往后进行遍历
            for (int j = i + 1; j <= n; j++) {
                // 求出当前字符的hash
                cur = cur * P + (word.charAt(j - 1) - 'a') + OFFSET;
                if (HASH_SET.contains(cur)) {
                    // 如果hashset中包含cur，则说明这个字符串可以单独切分出来，去判断切还是不切
                    f[j] = Math.max(f[j], f[i] + 1);
                }
            }
            // 如果这个字符的0~n-1个字符可以切分成>1个子字符串，则返回true
            if (f[n] > 1) {
                return true;
            }
        }
        // 否则为false
        return false;
    }

}
