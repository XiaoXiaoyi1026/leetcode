package com.xiaoxiaoyi.other;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author xiaoxiaoyi
 * 给定一个字符串列表, 求出其中出现次数最多的k个字符串
 */
public class MostOccurrences {
    /**
     * 统计词频
     */
    private static Map<String, Integer> wordFrequency;
    /**
     * 创建k个大小的小根堆, 根据词频进行比较
     */
    private static PriorityQueue<String> smallRootHeap;
    private static int k;

    public static String[] mostOccurrences(String[] words, int k) {
        MostOccurrences.k = k;
        wordFrequency = new HashMap<>(k);
        for (String word : words) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }
        smallRootHeap = new PriorityQueue<>(k,
                Comparator.comparingInt(wordFrequency::get)
        );
        for (String word : wordFrequency.keySet()) {
            add(word);
        }
        // 返回的结果
        String[] res = new String[k];
        for (int i = 0; i < res.length; i++) {
            res[i] = smallRootHeap.poll();
        }
        return res;
    }

    private static void add(String word) {
        if (smallRootHeap.size() < k) {
            smallRootHeap.add(word);
        } else {
            String peek = smallRootHeap.peek();
            if (wordFrequency.getOrDefault(word, 0) > wordFrequency.get(peek)) {
                smallRootHeap.poll();
                smallRootHeap.add(word);
            }
        }
    }

}
