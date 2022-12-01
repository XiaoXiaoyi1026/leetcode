package com.xiaoxiaoyi.matrix;

import junit.framework.TestCase;

public class SpiralMatrixTest extends TestCase {

    public void testSpiralPrintMatrix() {
        int[][] matrix = {
                {0, 1, 2, 3},
                {9, 10, 11, 4},
                {8, 7, 6, 5},
        };
        SpiralPrintMatrix.spiralPrintMatrix(matrix);
    }

}
