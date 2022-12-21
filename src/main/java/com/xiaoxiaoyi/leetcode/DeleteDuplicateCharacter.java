package com.xiaoxiaoyi.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoxiaoyi
 * 给定一个字符串, 要求其中所有的字符只保留1个, 返回字典序最小的结果
 */
public class DeleteDuplicateCharacter {

    public static String remove(String str) {
        if (str == null || str.length() < 2) {
            return str;
        }
        Map<Character, Integer> charFrequency = new HashMap<>();
        char[] chars = str.toCharArray();
        for (char c : chars) {
            // 统计每一个字符的出现频率
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }
        // 记录最小ASCII码字符出现的位置
        int minASCIICharIndex = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int frequency = charFrequency.get(c) - 1;
            charFrequency.put(c, frequency);
            if (frequency == 0) {
                // 出现了词频为0的节点
                break;
            } else {
                minASCIICharIndex = c < chars[minASCIICharIndex] ? i : minASCIICharIndex;
            }
        }
        return chars[minASCIICharIndex] + remove(str
                // 截取minASCIICharIndex后面的部分
                .substring(minASCIICharIndex)
                // 然后将后面部分中的左右最小ASCII码的字符去掉
                .replaceAll(String.valueOf(chars[minASCIICharIndex]), "")
        );
    }

}
