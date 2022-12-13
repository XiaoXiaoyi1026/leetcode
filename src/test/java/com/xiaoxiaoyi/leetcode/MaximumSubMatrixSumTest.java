package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class MaximumSubMatrixSumTest extends TestCase {

    public void testMaximumSubMatrixSum() {
        int[][] matrix = {
                {-5, 3, 6, 4},
                {-7, 9, -5, 3},
                {-10, 1, -200, 4}
        };
        System.out.println(MaximumSubMatrixSum.maximumSubMatrixSum(matrix));
    }

}
