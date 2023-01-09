package com.xiaoxiaoyi.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoxiaoyi
 * 包含目标字符串中每个字符的最短子串
 */
public class ShortestSubstring {

    public static int contains(String str1, String str2) {
        if (str1 == null || str2 == null) {
            throw new NullPointerException("String can not be null!");
        }
        if (str1.length() < str2.length()) {
            throw new RuntimeException("String length error!");
        }
        Map<Character, Integer> owed = new HashMap<>();
        // 一共欠allOwed个字符
        int allOwed = str2.length();
        for (int i = 0; i < allOwed; i++) {
            char cur = str2.charAt(i);
            owed.put(cur, owed.getOrDefault(cur, 0) + 1);
        }
        int n = str1.length();
        int res = n + 1;
        // 计算必须以每一个位置开始的情况, 开始为空窗口
        int begin = -1, end = 0;
        while (end < n) {
            // 在欠的字符总数>0的情况下右指针先动
            while (end < n && allOwed > 0) {
                char cur = str1.charAt(end++);
                if (owed.containsKey(cur)) {
                    int curOwed = owed.get(cur);
                    owed.put(cur, --curOwed);
                    if (curOwed >= 0) {
                        allOwed--;
                    }
                }
            }
            if (allOwed == 0) {
                // 如果窗口内的子串成功匹配, 则更新结果
                res = Math.min(res, end - begin - 1);
            } else {
                // 后面都不可能出结果
                break;
            }
            // 窗口内部还有字符时
            while (begin < end) {
                char cur = str1.charAt(++begin);
                if (owed.containsKey(cur)) {
                    int curOwed = owed.get(cur);
                    owed.put(cur, ++curOwed);
                    if (curOwed > 0) {
                        allOwed = 1;
                        break;
                    }
                }
            }
        }
        return res == n + 1 ? 0 : res;
    }

}
