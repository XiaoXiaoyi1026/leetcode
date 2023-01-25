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
        // pre代表cur-1位置的字符作为结尾的最长无重复字串, -1位置的结尾最长无重复字串为0
        int pre = 0, ans = 0;
        for (int cur = 0; cur < n; cur++) {
            char c = chars[cur];
            // 瓶颈1: 当前字符最后一次出现的位置, 如果没有出现过则返回-1
            int lastIndex = charMap.getOrDefault(c, -1);
            // 瓶颈2: cur-1位置的最长无重复字串的长度tmp, 2个瓶颈谁距离i位置最近谁就是答案
            pre = Math.min(pre, cur - lastIndex - 1) + 1;
            // 如果比结果大, 则更新结果
            ans = Math.max(ans, pre);
            // 更新c最后1次出现的位置
            charMap.put(c, cur);
        }
        return ans;
    }

}
