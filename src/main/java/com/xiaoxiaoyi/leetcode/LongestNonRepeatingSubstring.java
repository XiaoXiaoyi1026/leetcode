package com.xiaoxiaoyi.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoxiaoyi
 * 最长无重复子串问题, 凡是遇到子串/子数组相关问题都是看以每个位置位置结尾时的情况
 */
public class LongestNonRepeatingSubstring {

    public static int get(String str) {
        // 记录每一个字符最后一次出现的位置
        Map<Character, Integer> charMap = new HashMap<>();
        char[] chars = str.toCharArray();
        int n = chars.length;
        // tmp代表i-1位置的字符作为结尾的最长无重复字串, -1位置的结尾最长无重复字串为0
        int tmp = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            char c = chars[i];
            // 瓶颈1: 当前字符最后一次出现的位置, 如果没有出现过则返回-1
            int lastIndex = charMap.getOrDefault(c, -1);
            // 瓶颈2: i-1位置的最长无重复字串的长度tmp, 2个瓶颈谁距离i位置最近谁就是答案
            tmp = Math.min(tmp, i - lastIndex - 1) + 1;
            // 如果比结果大, 则更新结果
            ans = Math.max(ans, tmp);
            // 更新c最后1次出现的位置
            charMap.put(c, i);
        }
        return ans;
    }

}
