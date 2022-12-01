package com.xiaoxiaoyi.matrix;

import junit.framework.TestCase;

public class InMatrixTest extends TestCase {

    public void testInMatrix() {
        InMatrix.matrix = new int[][]{
                {1, 12, 19, 27, 39},
                {3, 15, 21, 35, 44},
                {8, 17, 30, 40, 55},
                {10, 20, 33, 56, 66},
                {22, 29, 52, 60, 77},
        };
        System.out.println(InMatrix.inMatrix(33));
    }

}
