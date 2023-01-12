package com.xiaoxiaoyi.tree.orderedlist;

import junit.framework.TestCase;

import java.util.Comparator;

public class HeapTest extends TestCase {

    public void testInsert() {
        Heap<Integer> heap = new Heap<>(Comparator.comparingInt(o -> o));
        for (int i = 0; i < 10; i++) {
            heap.insert(i);
        }
        System.out.println(heap);
    }

}
