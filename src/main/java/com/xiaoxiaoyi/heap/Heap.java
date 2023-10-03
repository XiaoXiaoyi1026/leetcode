package com.xiaoxiaoyi.heap;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 堆数据结构实现
 */
public class Heap<E> {

    /**
     * 默认初始容量
     */
    static final int INITIAL_CAPACITY = 16;

    /**
     * 数据
     */
    List<E> data;

    /**
     * 堆大小
     */
    int size;

    /**
     * 元素比较器
     */
    Comparator<E> comparator;

    /**
     * @param initialCapacity 堆容量
     * @param comparator      比较器
     */
    public Heap(int initialCapacity, Comparator<E> comparator) {
        this.comparator = comparator;
        data = new ArrayList<>(initialCapacity);
        size = 0;
    }

    /**
     * @param comparator 比较器
     */
    public Heap(Comparator<E> comparator) {
        this(INITIAL_CAPACITY, comparator);
    }

    /**
     * @param initialCapacity 堆容量
     */
    public Heap(int initialCapacity) {
        this(initialCapacity, null);
    }

    /**
     * @param arr        原始数组
     * @param comparator 比较器
     */
    public Heap(@NotNull E[] arr, Comparator<E> comparator) {
        this.comparator = comparator;
        for (E e : arr) {
            insert(e);
        }
    }

    /**
     * @param arr 原始数组
     */
    public Heap(@NotNull E[] arr) {
        this(arr, null);
    }

    /**
     * 默认创建堆
     */
    public Heap() {
        this(INITIAL_CAPACITY, null);
    }

    /**
     * 交换堆上的2个元素
     *
     * @param index1 下标1
     * @param index2 下标2
     */
    void swap(int index1, int index2) {
        E tmp = data.get(index1);
        data.set(index1, data.get(index2));
        data.set(index2, tmp);
    }

    /**
     * 向上调整
     *
     * @param curNode 当前节点
     */
    void heapInsert(int curNode) {
        while (curNode > 0 && less(curNode, parent(curNode))) {
            swap(curNode, parent(curNode));
            curNode = parent(curNode);
        }
    }

    /**
     * 向下调整
     *
     * @param curNode 当前节点
     */
    void heapify(int curNode) {
        int child = leftChild(curNode);
        while (child < size) {
            if (child + 1 < size && less(child + 1, child)) {
                child = child + 1;
            }
            if (less(curNode, child)) {
                break;
            }
            swap(curNode, child);
            curNode = child;
            child = leftChild(curNode);
        }
    }

    /**
     * @param curNode 当前节点下标
     * @return 左子结点下标
     */
    int leftChild(int curNode) {
        return (curNode << 1) + 1;
    }

    /**
     * @param child 子节点下标
     * @return 父节点下标
     */
    int parent(int child) {
        return (child - 1) >> 1;
    }

    /**
     * @return 堆是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return 堆大小
     */
    public int size() {
        return size;
    }

    /**
     * @param index1 下标1
     * @param index2 下标2
     * @return 下标1位置的元素是否小于下标2位置的元素
     */
    boolean less(int index1, int index2) {
        return compare(data.get(index1), data.get(index2)) < 0;
    }

    /**
     * @param e1 元素1
     * @param e2 元素2
     * @return 元素比较结果
     */
    @SuppressWarnings("unchecked")
    int compare(E e1, E e2) {
        return comparator == null ? ((Comparable<E>) e1).compareTo(e2) : comparator.compare(e1, e2);
    }

    /**
     * 插入元素
     *
     * @param e 元素
     */
    public void insert(E e) {
        data.add(e);
        heapInsert(size++);
    }

    /**
     * 插入元素
     *
     * @param e 元素
     */
    public void offer(E e) {
        insert(e);
    }

    public void add(E e) {
        insert(e);
    }

    /**
     * 删除堆顶节点
     *
     * @return 删除的节点
     */
    public E delete() {
        swap(0, --size);
        heapify(0);
        return data.get(size);
    }

    /**
     * 删除堆顶节点
     *
     * @return 删除的节点
     */
    public E remove() {
        return delete();
    }

    /**
     * 删除堆顶节点
     *
     * @return 删除的节点
     */
    public E poll() {
        return delete();
    }

    /**
     * @return 查看堆顶节点, 不删除
     */
    public E peek() {
        return data.get(0);
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
