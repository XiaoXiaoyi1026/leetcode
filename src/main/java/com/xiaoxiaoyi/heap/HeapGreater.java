package com.xiaoxiaoyi.heap;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.HashSet;

/**
 * 加强堆
 */
public class HeapGreater<E> extends Heap<E> {

    /**
     * 反向索引表
     */
    final Map<E, Set<Integer>> indexMap;

    /**
     * @param initialCapacity 初始化堆容量
     * @param comparator      堆元素比较器
     */
    public HeapGreater(int initialCapacity, Comparator<E> comparator) {
        super(initialCapacity, comparator);
        indexMap = HashMap.newHashMap(initialCapacity);
    }

    /**
     * @param comparator 堆元素比较器
     */
    public HeapGreater(Comparator<E> comparator) {
        this(INITIAL_CAPACITY, comparator);
    }

    /**
     * @param data 原始数组
     */
    public HeapGreater(@NotNull E[] data) {
        indexMap = new HashMap<>();
        for (E e : data) {
            insert(e);
        }
    }

    public HeapGreater(int initialCapacity) {
        this(initialCapacity, null);
    }

    public HeapGreater() {
        this(INITIAL_CAPACITY);
    }

    /**
     * @param e 元素
     * @return 元素出现的下标
     */
    public Set<Integer> indexOf(E e) {
        return indexMap.get(e);
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
        Set<Integer> indexSet1 = indexMap.get(data.get(index1));
        Set<Integer> indexSet2 = indexMap.get(data.get(index2));
        if (indexSet1 != indexSet2) {
            indexSet1.remove(index1);
            indexSet2.add(index1);
            indexSet2.remove(index2);
            indexSet1.add(index2);
            super.swap(index1, index2);
        }
    }

    @Override
    public void insert(E e) {
        Set<Integer> indexSet = indexMap.get(e);
        if (indexSet == null) {
            indexSet = new HashSet<>();
        }
        indexSet.add(size);
        indexMap.put(e, indexSet);
        super.insert(e);
    }

    @Override
    public void offer(E e) {
        insert(e);
    }

    @Override
    public void add(E e) {
        insert(e);
    }

    /**
     * 移除堆上所有的元素e
     *
     * @param e 移除的元素
     */
    void remove(E e) {
        Set<Integer> indexSet = indexMap.get(e);
        if (indexSet != null) {
            for (int index : indexSet) {
                swap(index, --size);
                heapInsert(index);
                heapify(index);
            }
            indexMap.remove(e);
        }
    }

    @Override
    public E delete() {
        E e = super.delete();
        indexMap.get(e).remove(size);
        if (indexMap.get(e).isEmpty()) {
            indexMap.remove(e);
        }
        return e;
    }

    @Override
    public E remove() {
        return delete();
    }

    @Override
    public E poll() {
        return delete();
    }

}
