package com.xiaoxiaoyi.tree.orderedlist;

import junit.framework.TestCase;

public class HeapTest extends TestCase {

    public void testInsert() {
        Heap<Integer> heap = new Heap<>();
        for (int i = 0; i < 10; i++) {
            heap.insert(i);
        }
        System.out.println(heap);
    }

}
