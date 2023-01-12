package com.xiaoxiaoyi.tree.orderedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoxiaoyi
 * 大根堆
 */
public class Heap<E extends Comparable<E>> {

    private int size;
    private final List<E> elements;

    public Heap() {
        size = 0;
        elements = new ArrayList<>();
    }

    @Override
    public String toString() {
        return elements.toString();
    }

    public void insert(E element) {
        elements.add(element);
        heapInsert(size++);
        heapIfy();
    }

    private void heapIfy() {
        heapIfy(0);
    }

    private void heapIfy(int index) {
        int left = (index << 1) + 1;
        while (left < size) {
            int maximum = elements.get(left)
                    .compareTo(elements.get(index)) > 0
                    ? left : index;
            if (left + 1 < size) {
                maximum = elements.get(left + 1)
                        .compareTo(elements.get(maximum)) > 0
                        ? left + 1 : maximum;
            }
            if (maximum == index) {
                break;
            }
            swap(maximum, index);
            index = maximum;
            left = (index << 1) + 1;
        }
    }

    protected void heapInsert(int index) {
        while (index > 0 && elements.get(index).compareTo(elements.get((index - 1) >> 1)) > 0) {
            swap(index, (index - 1) >> 1);
            index = (index - 1) >> 1;
        }
    }

    protected void swap(int index1, int index2) {
        E tmp = elements.get(index1);
        elements.set(index1, elements.get(index2));
        elements.set(index2, tmp);
    }

    public int size() {
        return size;
    }

}
