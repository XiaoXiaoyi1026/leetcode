package com.xiaoxiaoyi.matrix;

/**
 * @author xiaoxiaoyi
 * 打印矩阵
 */
public class Matrix {

    public static void print(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (j == 0) {
                    System.out.print("[");
                }
                System.out.print(ints[j] + (j == matrix[0].length - 1 ? "]" : ", "));
            }
            System.out.println();
        }
    }

    public static void print(Object[][] matrix) {
        for (Object[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (j == 0) {
                    System.out.print("[");
                }
                System.out.print(ints[j] + (j == matrix[0].length - 1 ? "]" : ", "));
            }
            System.out.println();
        }
    }

}
