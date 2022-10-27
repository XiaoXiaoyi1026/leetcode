package com.xiaoxiaoyi.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoxiaoyi
 * 设计一个数据结构，要求获取元素随机且等概率，删除、增加和获取元素的时间复杂度为O(1)
 */
public class RandomPool<K> {

    /**
     * 存储元素和它对应的下标位置
     */
    private final Map<K, Integer> valueToIndexMap;
    /**
     * 存储下标和它对应的元素值
     */
    private final Map<Integer, K> indexToValueMap;
    /**
     * 结构大小
     */
    private Integer size;

    /**
     * 初始化结构
     */
    RandomPool() {
        valueToIndexMap = new HashMap<>();
        indexToValueMap = new HashMap<>();
        size = 0;
    }

    /**
     * 等概率随机返回一个元素
     *
     * @return 元素
     */
    public K randomGet() {
        if (size == 0) {
            // 结构的大小为0，无元素返回
            return null;
        }
        // 等概率返回0~size-1上的元素
        return indexToValueMap.get((int) (Math.random() * size));
    }

    /**
     * 添加一个元素
     *
     * @param value 元素
     */
    public void insert(K value) {
        if (!valueToIndexMap.containsKey(value)) {
            // 没有每添加过才添加
            valueToIndexMap.put(value, size);
            // put完后size+1
            indexToValueMap.put(size++, value);
        }
    }

    /**
     * 删除一个元素
     *
     * @param value 元素
     */
    public void remove(K value) {
        if (valueToIndexMap.containsKey(value)) {
            // 有才进行删除
            // 获取最后一个元素的下标，顺便size-1
            int lastIndex = --size;
            // 先根据size的大小获取最后一个元素的值，用来填补空缺
            K lastValue = indexToValueMap.get(lastIndex);
            // 获取要删除元素的位置
            Integer index = valueToIndexMap.get(value);
            // 将要被删除元素的位置值替换成最后一个元素的值
            indexToValueMap.put(index, lastValue);
            // 将最后一个value的下标更新
            valueToIndexMap.put(lastValue, index);
            // 然后keyToValueMap中移除最后一个元素
            indexToValueMap.remove(lastIndex);
            // valueToKeyMap中也要移除value这条记录
            valueToIndexMap.remove(value);
        }
    }

}
