package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

import java.util.Arrays;

public class HeapSortTest extends TestCase {

    public void testHeapSort() {
        int[] arr = generate(20, 50);
        System.out.println(Arrays.toString(arr));
        HeapSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public int[] generate(int length, int max) {
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

}
