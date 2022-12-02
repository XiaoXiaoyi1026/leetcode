package com.xiaoxiaoyi.other;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoxiaoyi
 * 需求: 设计一个动态数据结构, 包含add(word)和getTop(k)方法
 * 用户可以随时添加或者获取出现次数top k的字符串, 要求时间复杂度较低
 * 词频相同返回任意一个都对
 * 1. 词频表 key: word value: times
 * 2. 小根堆 size: k heapsize = 0
 * 3. 堆位置map key: word value: word在堆上的位置(0~k-1)
 * 如果word不在堆上, 要么map中没有该word, 要么value = -1
 * process: word进入, 先统计词频, 然后看在不在堆上
 * 如果在堆上, 则根据词频heapify, 如果不在, 则判断堆是否满, 不满直接进
 * 满了就看能否替换掉堆顶, 如果可以, 则进堆, heapify, 原堆顶在堆中的位置更新为-1
 * 如果不能, 则更新当前元素在堆中的位置为-1
 */
public class TopStrings {

    /**
     * 词频
     */
    private final Map<String, Integer> wordFrequency;
    /**
     * 自己实现小根堆
     */
    private final String[] smallRootHeap;
    private int heapSize;
    /**
     * 堆位置
     */
    private final Map<String, Integer> heapLocation;
    /**
     * 维持前几个字符串
     */
    private final int k;

    TopStrings(int k) {
        this.k = k;
        wordFrequency = new HashMap<>(k);
        smallRootHeap = new String[k];
        heapSize = 0;
        heapLocation = new HashMap<>(k);
    }

    public void add(String word) {
        // 统计词频
        wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        if (!onHeap(word)) {
            // 如果不在堆上, 分为堆满和没满两种情况
            if (!headFull()) {
                // 如果堆没满, 则直接进堆, 然后自底向上heapInsert
                smallRootHeap[heapSize] = word;
                heapLocation.put(word, heapSize);
                heapInsert(heapSize++);
            } else {
                // 如果堆满了, 则看是否可以替换掉堆顶
                if (wordFrequency.get(word) > wordFrequency.get(smallRootHeap[0])) {
                    // 可以替换, 则替换掉堆顶的元素
                    heapLocation.put(smallRootHeap[0], -1);
                    heapLocation.put(word, 0);
                    smallRootHeap[0] = word;
                    heapify(0, heapSize);
                }
            }
        } else {
            // 如果已经在堆上, 向下调整
            heapify(heapLocation.get(word), heapSize);
        }
    }

    public Map<String, Integer> getTopStrings() {
        Map<String, Integer> res = new HashMap<>(k);
        for (String word : smallRootHeap) {
            res.put(word, wordFrequency.get(word));
        }
        return res;
    }

    private void heapInsert(int location) {
        // 防止越界
        if (location > 0) {
            int parent = (location - 1) >> 1;
            while (wordFrequency.get(smallRootHeap[location]) > wordFrequency.get(smallRootHeap[parent])) {
                swap(location, parent);
                location = parent;
            }
        }
    }

    private void heapify(int location, int heapSize) {
        int left = (location << 1) + 1;
        while (left < heapSize) {
            int smallest = wordFrequency.get(smallRootHeap[location]) <= wordFrequency.get(smallRootHeap[left]) ?
                    location : left;
            // 防治越界
            if (left + 1 < heapSize) {
                smallest = wordFrequency.get(smallRootHeap[left]) <= wordFrequency.get(smallRootHeap[left + 1]) ?
                        left : left + 1;
            }
            if (smallest == location) {
                break;
            }
            swap(location, smallest);
            location = smallest;
            left = (location << 1) + 1;
        }
    }

    private void swap(int location1, int location2) {
        // 更新位置
        heapLocation.put(smallRootHeap[location1], location2);
        heapLocation.put(smallRootHeap[location2], location1);
        // 小根堆中的元素对调
        String tmp = smallRootHeap[location1];
        smallRootHeap[location1] = smallRootHeap[location2];
        smallRootHeap[location2] = tmp;
    }

    private boolean onHeap(String word) {
        Integer location = heapLocation.get(word);
        // 如果不在位置map里或者堆上位置为-1, 则说明不在堆上
        return location != null && location != -1;
    }

    private boolean headFull() {
        return heapSize == k;
    }

}
