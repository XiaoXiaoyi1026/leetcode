package com.xiaoxiaoyi.heap;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 堆数据结构实现
 */
public class Heap<E> {

    private final E[] data;

    private int size;

    private Comparator<E> comparator;

    @SuppressWarnings("unchecked")
    public Heap(int capacity, Comparator<E> comparator) {
        this.comparator = comparator;
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public Heap(Comparator<E> comparator) {
        this(16, comparator);
    }

    public Heap(int capacity) {
        this(capacity, null);
    }

    public Heap(@NotNull E[] arr) {
        this.data = arr;
        size = arr.length;
        for (int i = parent(size - 1); i >= 0; i--) {
            shiftDown(i);
        }
    }

    public Heap() {
        this(16, null);
    }

    private void swap(int node1, int node2) {
        E tmp = data[node1];
        data[node1] = data[node2];
        data[node2] = tmp;
    }

    private void shiftDown(int curNode) {
        int child;
        while (leftChild(curNode) < size) {
            child = leftChild(curNode);
            if (child + 1 < size && less(child + 1, child)) {
                child = child + 1;
            }
            if (less(curNode, child)) {
                break;
            }
            swap(curNode, child);
            curNode = child;
        }
    }

    private int leftChild(int curNode) {
        return (curNode << 1) + 1;
    }

    private int parent(int child) {
        return (child - 1) >> 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(E e) {
        data[size] = e;
        shiftUp(size++);
    }

    private boolean less(int node1, int node2) {
        return compare(data[node1], data[node2]) < 0;
    }

    @SuppressWarnings("unchecked")
    private int compare(E e1, E e2) {
        return comparator == null ? ((Comparable<E>) e1).compareTo(e2) : comparator.compare(e1, e2);
    }

    private void shiftUp(int curNode) {
        while (curNode > 0 && less(curNode, parent(curNode))) {
            swap(curNode, parent(curNode));
            curNode = parent(curNode);
        }
    }

    public E remove() {
        return delete();
    }

    public E delete() {
        E ret = data[0];
        swap(0, --size);
        shiftDown(0);
        return ret;
    }

    public E peek() {
        return data[0];
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }

    public void poll() {
        delete();
    }

    public void offer(E e) {
        insert(e);
    }
}
