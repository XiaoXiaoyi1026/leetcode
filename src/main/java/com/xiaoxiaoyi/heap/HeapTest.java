package com.xiaoxiaoyi.heap;

import org.jetbrains.annotations.NotNull;

public class HeapTest {

    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>(10);
        heap.insert(5);
        heap.insert(3);
        heap.insert(8);
        heap.insert(7);
        heap.insert(1);
        heap.insert(2);
        heap.insert(9);
        heap.insert(4);
        heap.insert(6);
        heap.insert(10);
        System.out.println(heap);
        heap.delete();
        System.out.println(heap);
        System.out.println(checkHeap(heap));
    }

    private static boolean checkHeap(@NotNull Heap<Integer> heap) {
        while (!heap.isEmpty()) {
            if (heap.size() == 1) {
                return true;
            }
            Integer parent = heap.remove();
            Integer child = heap.remove();
            if (parent > child) {
                return false;
            }
        }
        return true;
    }

}
