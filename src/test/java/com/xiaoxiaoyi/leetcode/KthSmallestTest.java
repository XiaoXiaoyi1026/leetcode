package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class KthSmallestTest extends TestCase {

    public void testKthSmallest() {
        int[] arr = {5, 1, 3, 2, 4, 6, 8, 12, 20};
        for (int i = 0; i < arr.length; i++) {
            System.out.println(KthSmallest.get(arr, i + 1));
        }
    }

}
