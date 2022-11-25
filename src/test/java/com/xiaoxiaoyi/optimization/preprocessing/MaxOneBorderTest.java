package com.xiaoxiaoyi.optimization.preprocessing;

import junit.framework.TestCase;

public class MaxOneBorderTest extends TestCase {

    public void testMaxOneBorder() {
        int[][] matrix = {
                {0, 0, 1, 0, 0},
                {1, 1, 1, 1, 1},
                {0, 1, 0, 1, 0},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 1, 0},
        };
        System.out.println(MaxOneBorder.maxOneBorder(matrix));
    }

}
