package com.xiaoxiaoyi.matrix;

/**
 * @author xiaoxiaoyi
 * 使用有限几个变量将一个正方形的矩阵顺时针旋转90度
 */
public class RotationMatrix {

    /**
     * @param matrix 正方形矩阵
     */
    public static void rotationMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int leftUpperX = 0, leftUpperY = 0;
        int bottomRightX = matrix[0].length - 1, bottomRightY = matrix.length - 1;
        while (leftUpperX < bottomRightX) {
            frameRotation(matrix, leftUpperX++, leftUpperY++, bottomRightX--, bottomRightY--);
        }
    }

    private static void frameRotation(int[][] matrix, int leftUpperX, int leftUpperY, int bottomRightX, int bottomRightY) {
        // 4条边上对应的值分别为element1, element2, element3, element4
        for (int i = 0; i < bottomRightX - leftUpperX; i++) {
            // tmp = element1
            int tmp = matrix[leftUpperY][leftUpperX + i];
            // element4 -> element1
            matrix[leftUpperY][leftUpperX + i] = matrix[bottomRightY - i][leftUpperX];
            // element3 -> element4
            matrix[bottomRightY - i][leftUpperX] = matrix[bottomRightY][bottomRightX - i];
            // element2 -> element3
            matrix[bottomRightY][bottomRightX - i] = matrix[leftUpperY + i][bottomRightX];
            // tmp -> element2
            matrix[leftUpperY + i][bottomRightX] = tmp;
        }
    }

}
