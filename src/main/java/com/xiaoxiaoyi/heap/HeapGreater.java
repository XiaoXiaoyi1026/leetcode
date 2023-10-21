package com.xiaoxiaoyi.heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 加强堆, 无法插入相同元素
 */
public class HeapGreater<E> extends Heap<E> {

    /**
     * 反向索引表
     */
    final Map<E, Integer> indexMap;

    /**
     * 初始化容量
     */
    static final int INITIAL_CAPACITY = 10;

    /**
     * @param initialCapacity 初始化堆容量
     * @param comparator      堆元素比较器
     */
    public HeapGreater(int initialCapacity, Comparator<E> comparator) {
        super(initialCapacity, comparator);
        indexMap = HashMap.newHashMap(initialCapacity);
    }

    public HeapGreater(int initialCapacity) {
        this(initialCapacity, null);
    }

    public HeapGreater() {
        this(INITIAL_CAPACITY);
    }

    /**
     * @param e 目标元素
     * @return 堆中是否包含目标元素
     */
    public boolean contains(E e) {
        return indexMap.containsKey(e);
    }

    @Override
    void swap(int index1, int index2) {
        super.swap(index1, index2);
        indexMap.put(data.get(index1), index1);
        indexMap.put(data.get(index2), index2);
    }

    /**
     * 插入非重复元素
     *
     * @param e 元素
     * @return 插入是否成功
     */
    @Override
    public boolean insert(E e) {
        if (contains(e) || isFull()) {
            return false;
        }
        indexMap.put(e, size);
        super.insert(e);
        return true;
    }

    /**
     * 重新调整
     *
     * @param index 下标
     */
    void reAdjust(int index) {
        heapInsert(index);
        heapify(index);
    }

    /**
     * 移除堆上的元素e
     *
     * @param e 移除的元素
     */
    void remove(E e) {
        if (contains(e)) {
            int index = indexMap.get(e);
            indexMap.remove(e);
            swap(index, --size);
            reAdjust(index);
        }
    }

    @Override
    public E delete() {
        E delete = super.delete();
        indexMap.remove(delete);
        return delete;
    }

}
