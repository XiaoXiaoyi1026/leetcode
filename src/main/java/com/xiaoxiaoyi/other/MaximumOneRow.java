package com.xiaoxiaoyi.other;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoxiaoyi
 * 给定一个01矩阵, 矩阵每一行的0都在1的左边
 * 求1最多的行号
 */
public class MaximumOneRow {

    public static int[][] matrix;

    public static List<Integer> maximumOneRow() {
        // 从右上角开始遍历
        int row = 0, col = matrix[0].length - 1;
        List<Integer> ans = new ArrayList<>();
        int maximum = -1;
        while (row < matrix.length && col >= 0) {
            // 记录当前遍历到的行中1的个数
            int tmp = maximum;
            while (matrix[row][col] == 1) {
                tmp++;
                col--;
            }
            if (tmp > maximum) {
                ans.clear();
                ans.add(row);
                maximum = tmp;
            } else if (tmp == maximum) {
                ans.add(row);
            }
            row++;
        }
        return ans;
    }

}
