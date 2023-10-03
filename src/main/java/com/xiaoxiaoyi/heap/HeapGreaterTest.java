package com.xiaoxiaoyi.heap;

public class HeapGreaterTest {

    public static void main(String[] args) {
        HeapGreater<Integer> heapGreater = new HeapGreater<>();
        heapGreater.add(5);
        heapGreater.add(3);
        heapGreater.add(7);
        heapGreater.add(8);
        heapGreater.add(1);
        heapGreater.add(2);
        heapGreater.add(6);
        heapGreater.add(4);
        heapGreater.add(8);
        System.out.println(heapGreater.contains(8));
        System.out.println(heapGreater.contains(10));
        System.out.println(heapGreater.size());
        heapGreater.remove(8);
        heapGreater.remove(2);
        heapGreater.remove(1);
        while (!heapGreater.isEmpty()) {
            System.out.println(heapGreater.poll());
        }
    }
}
