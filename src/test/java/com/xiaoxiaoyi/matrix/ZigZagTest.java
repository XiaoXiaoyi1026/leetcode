package com.xiaoxiaoyi.matrix;

import junit.framework.TestCase;

public class ZigZagTest extends TestCase {

    public void testZigZag() {
        int[][] matrix = {
                {0, 1, 5, 6},
                {2, 4, 7, 13},
                {3, 8, 12, 14},
                {9, 11, 15, 18},
                {10, 16, 17, 19},
        };
        ZigZag.printMatrix(matrix);
    }

}
