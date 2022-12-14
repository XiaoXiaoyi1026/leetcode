package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

import java.util.Arrays;

public class InPlaceTest extends TestCase {

    public int[] generateArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = 1 + (int) (Math.random() * length);
        }
        return arr;
    }

    public void testInPlace() {
        int[] arr = generateArray(1 + (int) (Math.random() * 20));
        // 调整前
        System.out.println(Arrays.toString(arr));
        System.out.println(InPlace.inPlace(arr));
        // 调整后
        System.out.println(Arrays.toString(arr));
    }

}
