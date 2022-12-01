package com.xiaoxiaoyi.matrix;

import junit.framework.TestCase;

import java.util.Arrays;

public class RotationMatrixTest extends TestCase {

    public void testRotationMatrix() {
        int[][] matrix = {
                {1, 2, 3, 4},
                {12, 13, 14, 5},
                {11, 16, 15, 6},
                {10, 9, 8, 7}
        };
        RotationMatrix.rotationMatrix(matrix);
        Matrix.print(matrix);
    }

}
