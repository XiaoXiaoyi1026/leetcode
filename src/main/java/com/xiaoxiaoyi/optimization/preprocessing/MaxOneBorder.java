package com.xiaoxiaoyi.optimization.preprocessing;

/**
 * @author xiaoxiaoyi
 * 给定一个01矩阵, 求以1为边的最长正方形的边长
 */
public class MaxOneBorder {

    public static int maxOneBorder(int[][] zeroOneMatrix) {
        System.out.println("zeroOneMatrix: ");
        printMatrix(zeroOneMatrix);
        int res = 0;
        // 确定一个正方形需要的信息: 左上角点以及边长(O(n^3))
        int n = zeroOneMatrix.length, m = zeroOneMatrix[0].length;
        // rightOne记录每个位置(包括自己)右边有多少个连续的1
        int[][] rightOne = new int[n + 1][m + 1];
        // downOne记录每个位置(包括自己)下边有多少个连续的1
        int[][] downOne = new int[n + 1][m + 1];
        int row, col;
        // 预处理过程(O(n^2)), 从右下往左上, 斜率优化
        for (row = n - 1; row >= 0; row--) {
            for (col = m - 1; col >= 0; col--) {
                if (zeroOneMatrix[row][col] == 1) {
                    // 如果当前位置为1, 那么取它的前一个位置+1
                    rightOne[row][col] = rightOne[row][col + 1] + 1;
                    downOne[row][col] = downOne[row + 1][col] + 1;
                } else {
                    // 如果当前位置为0, 则为0
                    rightOne[row][col] = downOne[row][col] = 0;
                }
            }
        }
        System.out.println("rightOne: ");
        printMatrix(rightOne);
        System.out.println("downOne: ");
        printMatrix(downOne);

        // 枚举判断每个正方形(O(n^3))
        for (row = 0; row < n; row++) {
            for (col = 0; col < m; col++) {
                for (int border = 1; border <= Math.min(n - row, m - col); border++) {
                    // 普通方法, 4个for循环判断边界是否全1(O(n))
                    // 预处理方法, 拿到自己点的下面和右边border个位置是否为1, 右边那个点的下面border是否为1, 下面那个点的border是否为1(O(1))
                    if (border <= rightOne[row][col]
                            && border <= downOne[row][col]
                            && border <= downOne[row][col + border - 1]
                            && border <= rightOne[row + border - 1][col]) {
                        // 如果符合条件, 则更新res
                        res = Math.max(res, border);
                    }
                }
            }
        }
        return res;
    }

    public static void printMatrix(int[][] matrix) {
        int m = matrix[0].length;
        for (int[] ints : matrix) {
            for (int col = 0; col < m; col++) {
                if (col == 0) {
                    System.out.print("[");
                }
                System.out.print(ints[col] + ((col == m - 1) ? "]" : ","));
            }
            System.out.println();
        }
    }
}
