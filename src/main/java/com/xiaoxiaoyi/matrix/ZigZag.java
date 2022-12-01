package com.xiaoxiaoyi.matrix;

/**
 * @author xiaoxiaoyi
 * z型打印矩阵
 */
public class ZigZag {

    public static void printMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        // 行和列的终止值
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        // 定义2个点 a 和 b 开始都压中矩阵左上角
        int ar = 0, ac = 0, br = 0, bc = 0;
        boolean flag = false;
        while (ar < endR + 1) {
            printSlashes(matrix, ar, ac, br, bc, flag);
            System.out.println();
            // ac一直向右移, 如果知道不能移动就向下移(ar + 1)
            ar = ac == endC ? ar + 1 : ar;
            // ac一直向右移, 如果移不动就原地不动
            ac = ac == endC ? ac : ac + 1;
            // br一直向下移, 如果移不动就向右移(bc + 1)
            bc = br == endR ? bc + 1 : bc;
            // br一直向下移, 移不动就原地不懂
            br = br == endR ? br : br + 1;
            // 交替从左上到右下 -> 从右下到左上打印
            flag = !flag;
        }
        System.out.println();
    }

    /**
     * @param flag true: 右上往左下打印 false: 左下往右上打印
     */
    private static void printSlashes(int[][] matrix, int ar, int ac, int br, int bc, boolean flag) {
        if (flag) {
            // 右上往左下打印
            while (ar <= br) {
                System.out.print(matrix[ar++][ac--] + " ");
            }
        } else {
            // 左下往右上打印
            while (br >= ar) {
                System.out.print(matrix[br--][bc++] + " ");
            }
        }
    }

}
