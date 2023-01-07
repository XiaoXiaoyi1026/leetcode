package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

import java.util.Arrays;

public class WiggleSortTest extends TestCase {

    public int[] generate(int length, int max) {
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

    public void testWiggleSort() {
        int[] arr = generate(21, 50);
        System.out.println(Arrays.toString(arr));
        WiggleSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
