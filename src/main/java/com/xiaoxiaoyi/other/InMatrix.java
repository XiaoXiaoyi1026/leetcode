package com.xiaoxiaoyi.other;

/**
 * @author xiaoxiaoyi
 * 矩阵每一行从左到右, 每一列从上到下有序
 * 判断一个值是否在该矩阵中
 */
public class InMatrix {

    public static int[][] matrix;

    public static boolean inMatrix(int target) {
        // 从右上角开始遍历
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

}
