package com.xiaoxiaoyi.greedy;

/**
 * @author xiaoxiaoyi
 * 经典N皇后问题
 * 给你一个n*n大的棋盘，要求每一行都放一个皇后，且满足所有的皇后不同行，不共列，也不在同一条斜线上
 * 求一共有多少种摆法
 */
public class Queens {

    public static int queens(int n) {
        if (n < 1) {
            return 0;
        }
        // 记录每行皇后的位置record[0] = 1就代表第0行皇后在1位置
        int[] record = new int[n];
        // 深度优先遍历，从第0行开始
        return process(0, record, n);
    }

    /**
     * 深度优先遍历
     *
     * @param row    当前所在的行数
     * @param record 记录的每个皇后的位置
     * @param n      棋盘大小
     * @return 一共有多少种摆法
     */
    private static int process(int row, int[] record, int n) {
        if (row == n) {
            // 如果行数越界了，则说明有一种摆法可行，返回1即可
            return 1;
        }
        // 初始化返回值
        int res = 0;
        // 开始取当前行的每一种可能
        for (int col = 0; col < n; col++) {
            if (isValid(record, row, col)) {
                record[row] = col;
                // 继续遍历下一行的可能
                res += process(row + 1, record, n);
            }
        }
        return res;
    }

    /**
     * 判断当前坐标能不能放皇后
     *
     * @param record 之前的皇后的位置信息
     * @param row    行坐标
     * @param col    列坐标
     * @return 棋盘的[row, col]位置可否放置皇后
     */
    private static boolean isValid(int[] record, int row, int col) {
        // 行不用考虑，因为是按照行进行搜索的，取出所有用过的列
        for (int i = 0; i < row; i++) {
            int curCol = record[i];
            // 如果当前列和用过的列同列或者共斜线(列坐标差 = 横坐标差)，则不行
            if (col == curCol || Math.abs(col - curCol) == Math.abs(row - i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 常数时间优化后的n皇后
     *
     * @param n 棋盘大小
     * @return 摆法
     */
    public static int constantTimeOptimizationQueens(int n) {
        // 不能超过32皇后问题
        int maxN = 32;
        if (n < 1 || n > maxN) {
            return 0;
        }
        // limit为低位n的位数个1和高位32-n的位数个0组成的2进制数
        int limit = n == 32 ? -1 : (1 << n) - 1;
        // 初始情况下0行无任何限制
        return constantTimeOptimizationProcess(limit, 0, 0, 0);
    }

    /**
     * 常数时间优化后的遍历过程
     *
     * @param limit      限制信息，限制哪些位可以进行尝试
     * @param colLimit   列限制
     * @param leftLimit  左边的列限制
     * @param rightLimit 右边的列限制
     * @return 摆法数量
     */
    private static int constantTimeOptimizationProcess(int limit, int colLimit, int leftLimit, int rightLimit) {
        if (colLimit == limit) {
            // 列限制全部限制了，就说明所有位置都放了皇后了，这是一种摆法，直接返回1
            return 1;
        }
        /*
        计算所有可放的位置(1)
        colLimit | leftLimit | rightLimit为总限制(1代表不能放皇后)
        总限制取反(0代表不能放皇后)再与limit(0代表不能放皇后)，就去掉了所有不能放皇后的位置
        此时的pos中的1位置就是可以放皇后的位置
         */
        int pos = limit & (~(colLimit | leftLimit | rightLimit));
        // 记录返回值
        int res = 0;
        // 记录可以放皇后的最右侧的1
        int mostRightOne;
        // 当pos中还含有1的时候
        while (pos != 0) {
            // 取出其最右侧的1
            mostRightOne = pos & (~pos + 1);
            // 最右侧的1置0
            pos = pos - mostRightOne;
            /*
            统计之后的所有可能
            limit不用更新，因为它是作为基准存在的
            列限制colLimit更新为与当前选择的列相或的结果
            左列限制leftLimit更新为左列限制与当前列相或的结果左移1位
            右列限制rightLimit更新为当前右列限制与当前选择的列相或的结果右移1位
             */
            res += constantTimeOptimizationProcess(
                    limit,
                    colLimit | mostRightOne,
                    (leftLimit | mostRightOne) << 1,
                    (rightLimit | mostRightOne) >> 1
            );
        }
        return res;
    }

}
