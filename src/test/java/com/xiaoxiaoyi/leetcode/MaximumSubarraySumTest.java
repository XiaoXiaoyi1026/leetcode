package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class MaximumSubarraySumTest extends TestCase {

    public void testMaximumSubarraySum() {
        int[] array = {1, 2, -5, 6, 8, 23, -50, -3, 10, 7, 15, 5, -1};
        System.out.println(MaximumSubarraySum.maximumSubarraySum(array));
    }

}
