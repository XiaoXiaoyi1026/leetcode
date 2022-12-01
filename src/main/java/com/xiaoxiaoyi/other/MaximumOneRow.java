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
        int maximum = matrix[row][col] == 1 ? 1 : 0;
        while (row < matrix.length && col >= 0) {
            // 记录当前遍历到的行中1的个数, tmp继承之前的最大个数1
            int tmp = maximum;
            while (col > 0 && matrix[row][col - 1] == 1) {
                // 如果可以往左移则往左走
                tmp++;
                col--;
            }
            // 如果最大值又更新
            if (tmp > maximum) {
                ans.clear();
                ans.add(row);
                maximum = tmp;
            } else if (matrix[row][col] == 1 && tmp == maximum) {
                // 如果最大值没变, 且当前位置的元素为1, 则说明这一行也是当前最多1的行
                ans.add(row);
            }
            row++;
        }
        return ans;
    }

}
