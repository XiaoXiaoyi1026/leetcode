package com.xiaoxiaoyi.matrix;

/**
 * @author xiaoxiaoyi
 * 螺旋打印矩阵
 */
public class SpiralPrintMatrix {
    private static class Coordinate {
        int x;
        int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void spiralPrintMatrix(int[][] matrix) {
        Coordinate upperLeft = new Coordinate(0, 0);
        Coordinate bottomRight = new Coordinate(matrix[0].length - 1, matrix.length - 1);
        while (upperLeft.x <= bottomRight.x && upperLeft.y <= bottomRight.y) {
            printMatrixBorder(matrix, upperLeft, bottomRight);
            upperLeft.x++;
            upperLeft.y++;
            bottomRight.x--;
            bottomRight.y--;
        }
    }

    private static void printMatrixBorder(int[][] matrix, Coordinate upperLeft, Coordinate bottomRight) {
        if (upperLeft.y == bottomRight.y) {
            // 在同一行
            for (int i = upperLeft.x; i <= bottomRight.x; i++) {
                System.out.print(matrix[upperLeft.y][i] + " ");
            }
        } else if (upperLeft.x == bottomRight.x) {
            // 在同一列
            for (int i = upperLeft.y; i <= bottomRight.y; i++) {
                System.out.print(matrix[i][upperLeft.x] + " ");
            }
        } else {
            // 标准情况
            int col = upperLeft.x;
            int row = upperLeft.y;
            while (col < bottomRight.x) {
                System.out.print(matrix[row][col++] + " ");
            }
            // col = bottomRight.x
            while (row < bottomRight.y) {
                System.out.print(matrix[row++][col] + " ");
            }
            // row = bottomRight.y
            while (col > upperLeft.x) {
                System.out.print(matrix[row][col--] + " ");
            }
            // col = upperLeft.x
            while (row > upperLeft.y) {
                System.out.print(matrix[row--][col] + " ");
            }
            // row = upperLeft.y
        }
    }

}
